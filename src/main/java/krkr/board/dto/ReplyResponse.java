package krkr.board.dto;

import krkr.board.domain.Member;
import krkr.board.domain.Reply;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class ReplyResponse {

    private Long id;

    private String content;

    private Member member;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public ReplyResponse(Reply reply) {
        this.id = reply.getId();
        this.content = reply.getContent();
        this.member = reply.getMember();
        this.createdAt = reply.getCreatedAt();
        this.updatedAt = reply.getUpdatedAt();
    }
}
