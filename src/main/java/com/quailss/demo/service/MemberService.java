package com.quailss.demo.service;

import com.quailss.demo.domain.Member;
import com.quailss.demo.domain.enums.Provider;
import com.quailss.demo.repository.AuthRepository;
import com.quailss.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final AuthRepository authRepository;

    public Optional<Member> findById(Long member_id){
        return memberRepository.findById(member_id);
    }
    // 휴대폰 번호 변경
    public String changePhoneNumber(String memberSession, String phoneNumber, Provider provider){
        Optional<Member> memberPhoneNumber = authRepository.findByEmailAndProvider(memberSession, provider);

        if(memberPhoneNumber.isPresent()){
            Member member = memberPhoneNumber.get();
            member.setPhonenumber(phoneNumber);
            memberRepository.save(member);
            return member.getPhonenumber();
        }

        return null;
    }

    // 이름 변경
    public Long changeName(String memberSession, String name, Provider provider){
        Optional<Member> memberName = authRepository.findByEmailAndProvider(memberSession, provider);

        if(memberName.isPresent()){
            Member member = memberName.get();
            member.setName(name);
            memberRepository.save(member);
            return member.getId();
        }

        return null;
    }

    //생일 변경
    public Long changeBirthday(String memberSession, LocalDate birtyday, Provider provider){
        Optional<Member> memberBirthday = authRepository.findByEmailAndProvider(memberSession, provider);

        if(memberBirthday.isPresent()){
            Member member = memberBirthday.get();
            member.setBirthday(birtyday);
            memberRepository.save(member);
            return member.getId();
        }

        return null;
    }

}


