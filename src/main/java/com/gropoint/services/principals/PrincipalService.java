package com.gropoint.services.principals;

import com.gropoint.dto.LoginDTO;
import com.gropoint.dto.PrincipalDTO;
import com.gropoint.models.entities.principals.Principal;
import com.gropoint.models.repositories.principals.PrincipalRepos;
import com.gropoint.responses.CommonResponse;
import com.gropoint.responses.CustomField;
import com.gropoint.responses.ResponseGenerator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class PrincipalService {
    @Autowired
    private PrincipalRepos principalRepos;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ResponseGenerator responseGenerator;

    boolean validateName(String name){
        String regex = "[a-z A-Z]+";
        if (name.matches(regex)){
            return true;
        } else {
            return false;
        }
    }

    boolean validateUsername(String username){
        String regex = "^\\+[1-9]{1}[0-9]{3,14}$";
        if (username.matches(regex)){
            return true;
        } else {
            return false;
        }
    }

    boolean validatePassword(String password){
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[*!#$%\\-\\/:-@\\[-`{-~]).{6,64}$";
        if (password.matches(regex)){
            return true;
        } else {
            return false;
        }
    }

    public ResponseEntity<CommonResponse> savePrincipal(PrincipalDTO payload){
        boolean nameIsValid = validateName(payload.getNamePrincipal());
        boolean usernameIsValid = validateUsername(payload.getUsername());
        boolean passwordIsValid = validatePassword(payload.getPassword());

        Principal principal = modelMapper.map(payload,Principal.class);

        if (nameIsValid && usernameIsValid && passwordIsValid == true){
            if (principal.getId() != null){
                Principal currPrincipal = principalRepos.findById(principal.getId()).get();
                currPrincipal.setNamePrincipal(payload.getNamePrincipal());
                currPrincipal.setPassword(passwordEncoder.encode(payload.getPassword()));
                currPrincipal.setUsername(payload.getUsername());

                return ResponseEntity.status(HttpStatus.OK)
                        .body(responseGenerator.success(
                                principalRepos.update(currPrincipal.getNamePrincipal(),
                                        currPrincipal.getPassword(), currPrincipal.getUsername(),
                                        payload.getRole(), principal.getId()),
                                "Success update new principal."));
            } else {
                principal.setNamePrincipal(payload.getNamePrincipal());
                principal.setPassword(passwordEncoder.encode(payload.getPassword()));
                principal.setUsername(payload.getUsername());
                principal.setDeleted(false);

                return ResponseEntity.status(HttpStatus.OK)
                        .body(responseGenerator.success(principalRepos.saveNewPricipal(principal.getNamePrincipal(),
                                principal.getUsername(), principal.getPassword(),
                                payload.getRole(), principal.isDeleted()),
                                "Sucess save principal."));
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(responseGenerator.failed("Failed! Ceck your validation!"));
        }
    }

    public ResponseEntity<CommonResponse> findAllPrincipal(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseGenerator.success(principalRepos.getAllPrincipal(),
                        "Success get principal."));
    }

    public ResponseEntity<CommonResponse> findOnePrincipal(Long id){
        Optional<CustomField.getPrincipal> principal = Optional.ofNullable(principalRepos.getOnePrincipal(id));
        if (principal.isPresent()){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(responseGenerator.success(principal,"Success get one principal."));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(responseGenerator.failed("Principal with this id is doesnt exist."));
        }
    }

    public void delete(Long id){
        principalRepos.deleteById(id);
    }

    public ResponseEntity<CommonResponse> findDataByUsername(LoginDTO payload){
        Optional<CustomField.getPassword> passwordDb = principalRepos.getPasswordByUsername(payload.getUsername());

        if (!passwordDb.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(responseGenerator.notFound("Not data principal by this username."));
        } else {
            CustomField.getPassword password = passwordDb.get();
            if (passwordEncoder.matches(payload.getPassword(), password.getpassword())) {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(responseGenerator.success(
                                principalRepos.getDataByUsr(payload.getUsername()).get(),
                                "Login succes."));
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(responseGenerator.failed("Login Failed! Password doesnt match."));
            }
        }
    }

}
