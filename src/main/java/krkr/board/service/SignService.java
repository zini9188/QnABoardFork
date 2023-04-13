package krkr.board.service;

import krkr.board.config.JwtTokenProvider;
import krkr.board.domain.Member;
import krkr.board.dto.MemberRequest;
import krkr.board.dto.MemberResponse;
import krkr.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignService {

    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final MemberService memberService;

    public void signUp(MemberRequest signUpDto) {
        Member member = signUpDto.toEntity();
        member.encodePassword(passwordEncoder);
        memberService.saveMember(member);
    }

    public MemberResponse signIn(MemberRequest signInDto) {
        Member member = memberRepository.findByEmail(signInDto.getEmail());

        if (!passwordEncoder.matches(signInDto.getPassword(), member.getPassword())) {
            throw new UsernameNotFoundException("이메일 또는 비밀번호를 확인해주세요");
        }
        return new MemberResponse(member, jwtTokenProvider.createToken(member.getEmail(), member.getRoles()));
    }

    public void enrollAdmin() {
        Member admin = MemberRequest.toAdminEntity();
        admin.encodePassword(passwordEncoder);
        memberRepository.save(admin);
    }
}
