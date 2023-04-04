package krkr.board.dto;

import krkr.board.constant.Scope;
import krkr.board.constant.Status;
import krkr.board.domain.Member;
import krkr.board.domain.Question;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter @Setter
public class QuestionRequest {

    @NotNull
    private String title;

    @NotNull
    private String content;

    private Member member;

    private Integer like;

    private Status status;

    private Scope scope;

    public Question toEntity() {
        return Question.builder()
                .title(title)
                .content(content)
                .member(member)
                .like(0)
                .status(Status.QUESTION_REGISTRATION)
                .scope(scope)
                .build();
    }
}
