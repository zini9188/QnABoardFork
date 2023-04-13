package krkr.board.dto;

import krkr.board.domain.Member;
import krkr.board.domain.Reply;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ReplyRequest {

    private String content;

    private Member member;

    public Reply toEntity() {
        return new Reply(content, member);
    }
}
