package com.gropoint.services.customers;

import com.gropoint.dto.MemberDTO;
import com.gropoint.models.entities.customers.Member;
import com.gropoint.models.repositories.customers.MemberRepos;
import com.gropoint.responses.CommonResponse;
import com.gropoint.responses.ResponseGenerator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired
    private MemberRepos memberRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ResponseGenerator responseGenerator;

    public ResponseEntity<CommonResponse> saveMember(MemberDTO payload){
        Member member = modelMapper.map(payload, Member.class);
        member.setPrincipal(payload.getPrincipal());
        member.setRole(payload.getRole());
        member.setPoint(0);

        return ResponseEntity.status(HttpStatus.OK)
                .body(responseGenerator.success(
                        memberRepository.saveNewMember(member.getNameMember(), member.getPrincipal(),
                                member.getRole(), member.getPoint()),
                        "Success save member"));
    }
}
