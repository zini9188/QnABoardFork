package krkr.board.dto;

import krkr.board.constant.Scope;
import krkr.board.constant.Status;
import krkr.board.domain.Member;
import krkr.board.domain.Question;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class QuestionPageResponse {

    private Long id;

    private String title;

    private Member member;

    private Status status;

    private Scope scope;

    private Integer view;

    private LocalDateTime createdAt;

    public QuestionPageResponse(Question question) {
        this.id = question.getId();
        this.title = question.getTitle();
        this.member = question.getMember();
        this.status = question.getStatus();
        this.scope = question.getScope();
        this.view = question.getView();
        this.createdAt = question.getCreatedAt();
    }
}
