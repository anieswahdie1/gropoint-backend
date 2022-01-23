package com.gropoint.services.principals;

import com.gropoint.dto.RewardDTO;
import com.gropoint.models.entities.principals.Reward;
import com.gropoint.models.repositories.principals.RewardRepos;
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
public class RewardService {

    @Autowired
    private RewardRepos rewardRepository;

//    @Autowired
//    private MemberRepository memberRepository;

//    @Autowired
//    private ReturnRepository returnRepository;

//    @Autowired
//    private ClaimRewardRepository claimRewardRepository;

    @Autowired
    private ResponseGenerator responseGenerator;

    @Autowired
    private ModelMapper modelMapper;

    public ResponseEntity<CommonResponse> saveReward(RewardDTO payload){
        Reward reward = modelMapper.map(payload,Reward.class);

        return ResponseEntity.status(HttpStatus.OK)
                .body(responseGenerator.success(rewardRepository.save(reward),
                        "Success add new reward."));
    }

    public Iterable<Reward> findAllReward(){
        return rewardRepository.findAll();
    }

    public ResponseEntity<CommonResponse> findOneReward(Long id){
        Optional<CustomField.getReward> reward = Optional.ofNullable(rewardRepository.getOneRewardById(id));
        if (!reward.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(responseGenerator.notFound("Reward with this id not found."));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseGenerator.success(reward.get(),"" +
                        "Succes get reward."));
    }

    public void removeOneReward(Long id){
        rewardRepository.deleteById(id);
    }

    public ResponseEntity<CommonResponse> findRewardByPrincipalId(Long id){
        Iterable<CustomField.getReward> listReward = rewardRepository.getRewardByPrincipalId(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(responseGenerator.success(listReward,"Success get reward by ID."));
    }

//    public ResponseEntity<CommonResponse> claimReward(ClaimRewardDTO payload){
//        Timestamp dateNow = new Timestamp(System.currentTimeMillis());
//        ClaimReward claimReward = new ClaimReward();
//
//        Optional<Reward> reward = rewardRepository.findById(payload.getReward());
//        Optional<Member> member = memberRepository.findById(payload.getMember());
//
//        if (member.get().getPoint() > 0 && member.get().getPoint() >= reward.get().getRedeemPoint()){
//            int calcPoint = member.get().getPoint() - reward.get().getRedeemPoint();
//            memberRepository.updatePoint(calcPoint,member.get().getIdMember());
//            claimReward.setDateClaim(dateNow);
//            claimReward.setStatus("done");
//
//            return ResponseEntity.status(HttpStatus.OK)
//                    .body(commonResponseGenerator
//                            .responseSuccess(claimRewardRepository
//                                            .saveClaimReward(member.get().getIdMember(), reward.get().getId(),
//                                                    claimReward.getDateClaim(), claimReward.getStatus()),
//                                    "Success add claim reward."));
//        } else {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body(commonResponseGenerator.responseFailed("Failed."));
//        }
//    }
}
