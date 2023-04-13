package krkr.board.dto;

import krkr.board.constant.Role;
import krkr.board.domain.Member;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;

@Getter @Setter
public class MemberRequest {

    private String email;

    private String password;

    public Member toEntity() {
        return Member.builder()
                .email(email)
                .password(password)
                .roles(Collections.singletonList("ROLE_USER"))
                .build();
    }

    public static Member toAdminEntity() {
        return Member.builder()
                .email("admin@gmail.com")
                .password("1234")
                .roles(Collections.singletonList("ROLE_ADMIN"))
                .build();
    }
}
