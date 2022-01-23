package com.gropoint.services.principals;

import com.gropoint.dto.WarehouseDTO;
import com.gropoint.models.entities.principals.Warehouse;
import com.gropoint.models.repositories.principals.WarehouseRepos;
import com.gropoint.responses.CommonResponse;
import com.gropoint.responses.CustomField;
import com.gropoint.responses.ResponseGenerator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class WarehouseService {
    @Autowired
    private WarehouseRepos warehouseRepos;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ResponseGenerator responseGenerator;

    public ResponseEntity<CommonResponse> saveWarehouse(WarehouseDTO payload){
        Warehouse warehouse = modelMapper.map(payload,Warehouse.class);

        if (warehouse.getId() != null){
            warehouse.setNameWarehouse(payload.getNameWarehouse());

            return ResponseEntity.status(HttpStatus.OK)
                    .body(responseGenerator.success(
                            warehouseRepos.update(warehouse.getNameWarehouse(), payload.getId()),
                            "Success update warehouse by ID."
                    ));
        } else {
            warehouse.setDeleted(false);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(responseGenerator.success(
                            warehouseRepos.saveWarehouse(payload.getIdWarehouse(), payload.getNameWarehouse(),
                                    payload.getPrincipal(), warehouse.isDeleted()),
                            "Success save new warehouse."));
        }
    }

    public ResponseEntity<CommonResponse> findAllWarehouseByPrincipalId(Long principal){
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseGenerator.success(
                        warehouseRepos.getWarehouseByPrincipalID(principal),
                        "Success get all warehouse by principal ID."
                ));
    }

    public ResponseEntity<CommonResponse> findWarehouseById(Long id){
        Optional<CustomField.getWarehouse> warehouse = Optional.ofNullable(warehouseRepos.getWarehouseByID(id));
        if (warehouse.isPresent()){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(responseGenerator.success(warehouse, "Success get warehouse by ID."));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(responseGenerator.notFound("Warehouse with this id is not found."));
        }
    }

    public void deleteByID(Long id){
        warehouseRepos.deleteById(id);
    }
}
