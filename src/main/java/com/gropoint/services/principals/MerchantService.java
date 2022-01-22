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
        Optional<Merchant> merchant = merchantRepos.findById(payload.getId());
        Merchant currMerchant = modelMapper.map(payload,Merchant.class);

        if (merchant.get().getId() != null){
            currMerchant.setIdMerchant(payload.getIdMerchant());
            currMerchant.setNameMerchant(payload.getNameMerchant());
            currMerchant.setNameCity(payload.getNameCity());
            currMerchant.setLattitude(payload.getLattitude());
            currMerchant.setLongitude(payload.getLongitude());

            return ResponseEntity.status(HttpStatus.OK)
                    .body(responseGenerator.success(
                            merchantRepos.updateMerchant(currMerchant.getNameMerchant(), currMerchant.getNameCity(),
                                    currMerchant.getLattitude(), currMerchant.getLongitude(), merchant.get().getId()),
                            "Success update merchant by ID."
                    ));
        } else {
            currMerchant.setDeleted(false);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(responseGenerator.success(
                            merchantRepos.saveMerchant(payload.getIdMerchant(), payload.getNameMerchant(),
                                    payload.getNameCity(), payload.getLattitude(), payload.getLongitude(),
                                    payload.getPrincipal(), currMerchant.isDeleted()),
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
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(responseGenerator.failed("Merchant with id is doest exist."));
        }
    }

    public ResponseEntity<CommonResponse> findMerchantByPrincipalID(Long principal){
        List<CustomField.getMerchant> merchants = merchantRepos.getMerchantByPrincipalID(principal);

        if (!merchants.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(responseGenerator.success(merchants,"Success get merchants by Principal id"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(responseGenerator.failed("Merchant by principal ID is doesnt exist."));
        }
    }

    public void deleteMerchant(Long id){
        merchantRepos.deleteById(id);
    }
}
