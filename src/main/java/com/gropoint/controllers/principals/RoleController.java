package com.gropoint.controllers.principals;

import com.gropoint.dto.RoleDTO;
import com.gropoint.responses.CommonResponse;
import com.gropoint.responses.ResponseGenerator;
import com.gropoint.services.principals.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private ResponseGenerator responseGenerator;

    @PostMapping("/role/add")
    public ResponseEntity<CommonResponse> addNewResponse(@RequestBody RoleDTO payload){
        return roleService.saveRole(payload);
    }

    @GetMapping("/roles")
    public ResponseEntity<CommonResponse> getAllRoles(){
        return roleService.findAllRoles();
    }

    @GetMapping("/role/{id}")
    public ResponseEntity<CommonResponse> getRoleByID(@PathVariable("id")Long id){
        return roleService.findRoleById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteRoleByID(@PathVariable("id")Long id){
        roleService.delete(id);
    }
}
