package krkr.board.service;

import krkr.board.domain.Member;
import krkr.board.dto.MemberRequest;
import krkr.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public void getMember(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NoSuchElementException("회원이 없습니다"));
    }

    public Member saveMember(Member member) {
        return memberRepository.save(member);
    }

    public static void validateMember(Member member, Long memberId) {
        if (!member.getId().equals(memberId)) {
            throw new IllegalStateException("잘못된 요청입니다");
        }
    }

    public static void validateMemberAndRole(Member member, Long memberId) {
        if (!member.getId().equals(memberId) && member.getRoles().contains("ROLE_ADMIN")) {
            throw new IllegalStateException("잘못된 요청입니다");
        }
    }
}
