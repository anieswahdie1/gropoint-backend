package com.gropoint.services.principals;

import com.gropoint.dto.RoleDTO;
import com.gropoint.models.entities.principals.Role;
import com.gropoint.models.repositories.principals.RoleRepos;
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
public class RoleService {
    @Autowired
    private RoleRepos roleRepos;

    @Autowired
    private ResponseGenerator responseGenerator;

    @Autowired
    private ModelMapper modelMapper;

    public ResponseEntity<CommonResponse> saveRole(RoleDTO payload){
        Role role = modelMapper.map(payload, Role.class);
        role.setRoleName(payload.getRoleName());
        role.setDeleted(false);
        roleRepos.saveNewRole(role.getRoleName(), role.isDeleted());
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseGenerator.success(role, "Sucess save role"));
    }

    public ResponseEntity<CommonResponse> findAllRoles(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseGenerator.success(roleRepos.allRoles(),"Success get all roles"));
    }

    public ResponseEntity<CommonResponse> findRoleById(Long id){
        Optional<CustomField.getRole> role = Optional.ofNullable(roleRepos.roleByID(id));
        if (role.isPresent()){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(responseGenerator.success(role.get(),
                            "Success get role by ID."));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(responseGenerator.failed("Role with ID is null."));
        }
    }

    public void delete(Long id){
        roleRepos.deleteById(id);
    }
}
