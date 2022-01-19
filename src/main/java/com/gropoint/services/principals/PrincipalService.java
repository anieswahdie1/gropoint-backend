package com.gropoint.services.principals;

import com.gropoint.dto.PrincipalDTO;
import com.gropoint.models.entities.principals.Principal;
import com.gropoint.models.repositories.principals.PrincipalRepos;
import com.gropoint.responses.CommonResponse;
import com.gropoint.responses.ResponseGenerator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PrincipalService {
    @Autowired
    private PrincipalRepos principalRepos;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ResponseGenerator responseGenerator;

    public ResponseEntity<CommonResponse> savePrincipal(PrincipalDTO payload){
        Principal principal = modelMapper.map(payload,Principal.class);

        principal.setNamePrincipal(payload.getNamePrincipal());
        principal.setPassword(payload.getPassword());
        principal.setDeleted(false);

        return ResponseEntity.status(HttpStatus.OK)
                .body(responseGenerator.success(principalRepos.saveNewPricipal(principal.getNamePrincipal(), principal.getPassword(), payload.getRole(), principal.isDeleted()),
                        "Sucess save principal."));
    }
}
