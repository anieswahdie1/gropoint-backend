package com.gropoint.controllers.principals;

import com.gropoint.dto.LoginDTO;
import com.gropoint.dto.PrincipalDTO;
import com.gropoint.responses.CommonResponse;
import com.gropoint.services.principals.PrincipalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PrincipalController {
    @Autowired
    private PrincipalService principalService;

    @PostMapping("/principal/add")
    public ResponseEntity<CommonResponse> addNewPrincipal(@RequestBody PrincipalDTO payload){
        return principalService.savePrincipal(payload);
    }

    @PutMapping("/principal/edit")
    public ResponseEntity<CommonResponse> updatePrincipal(@RequestBody PrincipalDTO payload){
        return principalService.savePrincipal(payload);
    }

    @GetMapping("/principals")
    public ResponseEntity<CommonResponse> getAllPrincipal(){
        return principalService.findAllPrincipal();
    }

    @GetMapping("/principal/{id}")
    public ResponseEntity<CommonResponse> getOnePrincipal(@PathVariable("id") Long id){
        return principalService.findOnePrincipal(id);
    }

    @DeleteMapping("/principal/delete/{id}")
    public void deletePrincipalByID(@PathVariable("id") Long id){
        principalService.delete(id);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<CommonResponse> login(@RequestBody LoginDTO payload){
        return principalService.findDataByUsername(payload);
    }

}
