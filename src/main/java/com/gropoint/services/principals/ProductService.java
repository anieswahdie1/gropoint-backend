package com.gropoint.services.principals;

import com.gropoint.dto.ProductDTO;
import com.gropoint.dto.TransactionDTO;
import com.gropoint.models.entities.customers.Member;
import com.gropoint.models.entities.customers.Transaction;
import com.gropoint.models.entities.principals.Product;
import com.gropoint.models.entities.principals.Program;
import com.gropoint.models.repositories.customers.MemberRepos;
import com.gropoint.models.repositories.customers.TransactionRepos;
import com.gropoint.models.repositories.principals.ProductRepos;
import com.gropoint.models.repositories.principals.ProgramRepos;
import com.gropoint.responses.CommonResponse;
import com.gropoint.responses.CustomField;
import com.gropoint.responses.ResponseGenerator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class ProductService {
    @Autowired
    private ProductRepos productRepos;

    @Autowired
    private ProgramRepos programRepos;

    @Autowired
    private MemberRepos memberRepos;

    @Autowired
    private TransactionRepos transactionRepos;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ResponseGenerator responseGenerator;

    public ResponseEntity<CommonResponse> saveProduct(ProductDTO payload){
        Product product = modelMapper.map(payload,Product.class);

        if (product.getId() != null){
            Optional<Product> currProduct = productRepos.findById(product.getId());
            Double stock = payload.getQtyStock() + currProduct.get().getCurrStock();

            product.setNameProduct(payload.getNameProduct());
            product.setPrice(payload.getPrice());
            product.setCurrStock(stock);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(responseGenerator.success(
                            productRepos.update(product.getNameProduct(),
                                    product.getCurrStock(), payload.getCategory(), payload.getWarehouse(),
                                    product.getPrice(), product.getId()), "Success update product."));
        } else {
            product.setIdProduct(payload.getIdProduct());
            product.setNameProduct(payload.getNameProduct());
            product.setCurrStock(payload.getQtyStock());
            product.setPrice(payload.getPrice());
            product.setDeleted(false);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(responseGenerator.success(
                            productRepos.saveNewProduct(product.getIdProduct(), product.getNameProduct(),
                                    product.getCurrStock(), payload.getCategory(),
                                    payload.getWarehouse(), product.getPrice(), payload.getPrincipal(),
                                    product.isDeleted()), "Success add new product."));
        }
    }

    public Iterable<Product> findAllProducts(){
        return productRepos.findAll();
    }

    public ResponseEntity<CommonResponse> findOneProduct(Long id){
        Optional<CustomField.getProduct> product = Optional.ofNullable(productRepos.getOneProductById(id));
        if (!product.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(responseGenerator.notFound("Product with this id is not found."));
        }
        return ResponseEntity.ok(responseGenerator.success(product.get(),
                "Success get product by id."));
    }

    public void removeProduct(Long id){
        productRepos.deleteById(id);
    }

    public ResponseEntity<CommonResponse> findProductByPrincipalId(Long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseGenerator.success(productRepos.getProductByPrincipalId(id),
                        "Success get all product by principal id."));
    }

    public ResponseEntity<CommonResponse> findProductByCategory(Long categoryId, Long principalId){
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseGenerator.success(productRepos.getProductByCategory(categoryId, principalId),
                        "Success get all data product by category"));
    }

    public ResponseEntity<CommonResponse> findProductDropdown(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseGenerator.success(productRepos.getDropdownProduct(),
                        "Success"));
    }

    public ResponseEntity<CommonResponse> claimPoint(TransactionDTO payload){
        Optional<Product> prod = productRepos.findById(payload.getProduct().getId());

        if (prod.isPresent()){
            Transaction prodTrans = modelMapper.map(payload, Transaction.class);
            Optional<Program> program = programRepos.findById(prod.get().getProgram().getId());
            Optional<Member> member = memberRepos.findById(payload.getMember().getIdMember());

            Double totalAmount = prod.get().getPrice() * payload.getQtyProduct();

            prodTrans.setProduct(payload.getProduct());
            prodTrans.setMember(payload.getMember());
            prodTrans.setQtyProduct(payload.getQtyProduct());
            prodTrans.setPayAmount(prod.get().getPrice() * payload.getQtyProduct());

            if (totalAmount >= program.get().getAmountTransaction()){
                if (program.get().isMultiple() == true){
                    Double calcAmount = Math.floor(prodTrans.getPayAmount()/program.get().getAmountTransaction());
                    Double claimPoint = calcAmount*program.get().getPoint();
                    int point = claimPoint.intValue();
                    memberRepos.updatePoint(member.get().getPoint()+point,payload.getMember().getIdMember());
                } else {
                    int claimPoint = program.get().getPoint();
                    memberRepos.updatePoint(member.get().getPoint()+claimPoint,payload.getMember().getIdMember());
                }
            }
            return ResponseEntity.status(HttpStatus.OK)
                    .body(responseGenerator.success(transactionRepos.save(prodTrans),
                            "Transaction success."));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(responseGenerator.failed("Failed."));
        }
    }

}
