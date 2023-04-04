package krkr.board.domain;

import krkr.board.constant.Scope;
import krkr.board.constant.Status;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity @Getter
@NoArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    @ManyToOne
    private Member member;

    private Integer like;

    @Enumerated(EnumType.STRING)
    private Status status;

    private Scope scope;

    @Builder
    public Question(String title, String content, Member member, Integer like, Status status, Scope scope) {
        this.title = title;
        this.content = content;
        this.member = member;
        this.like = like;
        this.status = status;
        this.scope = scope;
    }

    public void setQuestion(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void setScopeSecret() {
        this.scope = Scope.SECRET;
    }

    public void setStatusAnswered() {
        this.status = Status.QUESTION_ANSWERED;
    }

    public void setStatusDeleted() {
        this.status = Status.QUESTION_DELETE;
    }

}
