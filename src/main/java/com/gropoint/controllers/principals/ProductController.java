package com.gropoint.controllers.principals;

import com.gropoint.dto.ProductDTO;
import com.gropoint.models.entities.principals.Product;
import com.gropoint.responses.CommonResponse;
import com.gropoint.services.principals.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/product/add")
    public ResponseEntity<CommonResponse> addNewProduct(@RequestBody ProductDTO payload){
        return productService.saveProduct(payload);
    }

    @PutMapping("/product/edit")
    public ResponseEntity<CommonResponse> editProduct(@RequestBody ProductDTO payload){
        return productService.saveProduct(payload);
    }

    @GetMapping("/products")
    public Iterable<Product> getAllProduct(){
        return productService.findAllProducts();
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<CommonResponse> getOneProduct(@PathVariable("id") Long id){
        return productService.findOneProduct(id);
    }

    @DeleteMapping("/product/delete/{id}")
    public void deleteOneProduct(@PathVariable("id") Long id){
        productService.removeProduct(id);
    }

    @GetMapping("/product/principal/{id}")
    public ResponseEntity<CommonResponse> getProductByPrincipalId(@PathVariable("id") Long id){
        return productService.findProductByPrincipalId(id);
    }

    @GetMapping("/product/category")
    public ResponseEntity<CommonResponse> getProductByCategoryId(@RequestParam("categoryId") Long categoryId,
                                                                 @RequestParam("principalId") Long principalId){
        return productService.findProductByCategory(categoryId, principalId);
    }

    @GetMapping("/product/dropdown")
    public ResponseEntity<CommonResponse> getProductDropdown(){
        return productService.findProductDropdown();
    }

}
