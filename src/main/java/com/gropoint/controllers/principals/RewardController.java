package com.gropoint.controllers.principals;

import com.gropoint.dto.RewardDTO;
import com.gropoint.models.entities.principals.Reward;
import com.gropoint.responses.CommonResponse;
import com.gropoint.services.principals.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RewardController {
    @Autowired
    private RewardService rewardService;

    @PostMapping("/reward/add")
    public ResponseEntity<CommonResponse> addNewReward(@RequestBody RewardDTO payload){
        return rewardService.saveReward(payload);
    }

    @GetMapping("/rewards")
    public Iterable<Reward> getAllReward(){
        return rewardService.findAllReward();
    }

    @GetMapping("/reward/{id}")
    public ResponseEntity<CommonResponse> findOneReward(@PathVariable("id") Long id){
        return rewardService.findOneReward(id);
    }

    @DeleteMapping("/reward/delete/{id}")
    public void deleteOneReward(@PathVariable("id") Long id){
        rewardService.removeOneReward(id);
    }

    @GetMapping("/reward/principal/{id}")
    public ResponseEntity<CommonResponse> getRewardByPrincipalId(@PathVariable("id") Long id){
        return rewardService.findRewardByPrincipalId(id);
    }
}
