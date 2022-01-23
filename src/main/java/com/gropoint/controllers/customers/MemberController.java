package com.gropoint.controllers.customers;

import com.gropoint.dto.MemberDTO;
import com.gropoint.responses.CommonResponse;
import com.gropoint.services.customers.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @PostMapping("/member/add")
    public ResponseEntity<CommonResponse> addNewMember(@RequestBody MemberDTO payload){
        return memberService.saveMember(payload);
    }
}
