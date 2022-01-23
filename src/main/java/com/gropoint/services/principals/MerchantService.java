package com.gropoint.services.principals;

import com.gropoint.dto.MerchantDTO;
import com.gropoint.models.entities.principals.Merchant;
import com.gropoint.models.repositories.principals.MerchantRepos;
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
public class MerchantService {

    @Autowired
    private MerchantRepos merchantRepos;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ResponseGenerator responseGenerator;

    public ResponseEntity<CommonResponse> saveMerchant(MerchantDTO payload){
        Merchant merchant = modelMapper.map(payload,Merchant.class);

        if (merchant.getId() != null){
            merchant.setNameMerchant(payload.getNameMerchant());
            merchant.setNameCity(payload.getNameCity());
            merchant.setLattitude(payload.getLattitude());
            merchant.setLongitude(payload.getLongitude());

            return ResponseEntity.status(HttpStatus.OK)
                    .body(responseGenerator.success(
                            merchantRepos.updateMerchant(merchant.getNameMerchant(), merchant.getNameCity(),
                                    merchant.getLattitude(), merchant.getLongitude(), merchant.getId()),
                            "Success update merchant by ID."
                    ));
        } else {
            merchant.setIdMerchant(payload.getIdMerchant());
            merchant.setNameMerchant(payload.getNameMerchant());
            merchant.setNameCity(payload.getNameCity());
            merchant.setLattitude(payload.getLattitude());
            merchant.setLongitude(payload.getLongitude());
            merchant.setDeleted(false);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(responseGenerator.success(
                            merchantRepos.saveMerchant(merchant.getIdMerchant(), merchant.getNameMerchant(),
                                    merchant.getNameCity(), merchant.getLattitude(), merchant.getLongitude(),
                                    payload.getPrincipal(), merchant.isDeleted()),
                            "Success add new merchant."
                    ));
        }
    }

    public ResponseEntity<CommonResponse> findMerchantByID(Long id){
        Optional<CustomField.getMerchant> merchant = Optional.ofNullable(merchantRepos.getMerchantByID(id));
        if (merchant.isPresent()){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(responseGenerator.success(merchant,"Success get merchant by ID."));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(responseGenerator.notFound("Merchant with id is not found."));
        }
    }

    public ResponseEntity<CommonResponse> findMerchantByPrincipalID(Long principal){
        List<CustomField.getMerchant> merchants = merchantRepos.getMerchantByPrincipalID(principal);

        if (!merchants.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(responseGenerator.success(merchants,"Success get merchants by Principal id"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(responseGenerator.notFound("Merchant by principal ID is not found."));
        }
    }

    public void deleteMerchant(Long id){
        merchantRepos.deleteById(id);
    }
}
