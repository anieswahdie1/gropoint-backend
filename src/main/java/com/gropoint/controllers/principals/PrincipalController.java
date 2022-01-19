package com.gropoint.controllers.principals;

import com.gropoint.dto.PrincipalDTO;
import com.gropoint.responses.CommonResponse;
import com.gropoint.services.principals.PrincipalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PrincipalController {
    @Autowired
    private PrincipalService principalService;

    @PostMapping("/principal/add")
    public ResponseEntity<CommonResponse> addNewPrincpal(@RequestBody PrincipalDTO payload){
        return principalService.savePrincipal(payload);
    }
}
