package com.gropoint.controllers.principals;

import com.gropoint.dto.MerchantDTO;
import com.gropoint.responses.CommonResponse;
import com.gropoint.services.principals.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MerchantController {
    @Autowired
    private MerchantService merchantService;

    @PostMapping("/merchant/add")
    public ResponseEntity<CommonResponse> addNewMerchant(@RequestBody MerchantDTO payload){
        return merchantService.saveMerchant(payload);
    }

    @PutMapping("/merchant/edit")
    public ResponseEntity<CommonResponse> editMerchant(@RequestBody MerchantDTO payload){
        return merchantService.saveMerchant(payload);
    }

    @GetMapping("/merchant/{id}")
    public ResponseEntity<CommonResponse> getMerchantByID(@PathVariable("id") Long id){
        return merchantService.findMerchantByID(id);
    }

    @GetMapping("/merchant/principal/{id}")
    public ResponseEntity<CommonResponse> getMerchantByPrincipalID(@PathVariable("id") Long principal){
        return merchantService.findMerchantByPrincipalID(principal);
    }

    @DeleteMapping("/merchant/delete/{id}")
    public void deleteMerchantByID(@PathVariable("id") Long id){
        merchantService.deleteMerchant(id);
    }
}
