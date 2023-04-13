package krkr.board.dto;

import krkr.board.constant.Role;
import krkr.board.domain.Member;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class MemberResponse {

    private Long id;

    private String email;

    private List<String> role;

    private String token;

    public MemberResponse(Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.role = member.getRoles();
    }

    public MemberResponse(Member member, String token) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.role = member.getRoles();
        this.token = token;
    }
}
