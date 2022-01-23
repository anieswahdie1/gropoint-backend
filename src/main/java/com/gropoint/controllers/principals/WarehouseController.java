package com.gropoint.controllers.principals;

import com.gropoint.dto.WarehouseDTO;
import com.gropoint.responses.CommonResponse;
import com.gropoint.services.principals.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class WarehouseController {
    @Autowired
    private WarehouseService warehouseService;

    @PostMapping("/warehouse/add")
    public ResponseEntity<CommonResponse> addNewWarehouse(@RequestBody WarehouseDTO payload){
        return warehouseService.saveWarehouse(payload);
    }

    @PutMapping("/warehouse/edit")
    public ResponseEntity<CommonResponse> updateWarehouse(@RequestBody WarehouseDTO payload){
        return warehouseService.saveWarehouse(payload);
    }

    @GetMapping("/warehouse/principal/{id}")
    public ResponseEntity<CommonResponse> getWarehouseByPrincipalID(@PathVariable("id") Long principal){
        return warehouseService.findAllWarehouseByPrincipalId(principal);
    }

    @GetMapping("/warehouse/{id}")
    public ResponseEntity<CommonResponse> getWarehouseByID(@PathVariable("id") Long id){
        return warehouseService.findWarehouseById(id);
    }

    @DeleteMapping("/warehouse/delete/{id}")
    public void deleteWarehouseByID(@PathVariable("id") Long id){
        warehouseService.deleteByID(id);
    }
}
