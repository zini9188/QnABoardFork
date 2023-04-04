package krkr.board.dto;

import krkr.board.constant.Scope;
import krkr.board.constant.Status;
import krkr.board.domain.Member;
import krkr.board.domain.Question;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class QuestionResponse {

    private Long id;

    private String title;

    private String content;

    private Member member;

    private Integer like;

    private Status status;

    private Scope scope;

    public QuestionResponse(Question question) {
        this.id = question.getId();
        this.title = question.getTitle();
        this.content = question.getContent();
        this.member = question.getMember();
        this.like = question.getLike();
        this.status = question.getStatus();
        this.scope = question.getScope();
    }
}
