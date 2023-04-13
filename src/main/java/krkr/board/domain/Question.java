package krkr.board.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import krkr.board.constant.Scope;
import krkr.board.constant.Status;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Getter
@NoArgsConstructor
public class Question extends BaseTime{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long id;

    private String title;

    private String content;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    private Status status;

    private Scope scope;

    private Integer view;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "reply_id")
    private Reply reply;

    @JsonManagedReference
    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<QuestionImg> questionImgList = new ArrayList<>();

    @Builder
    public Question(String title, String content, Member member, Status status, Scope scope) {
        this.title = title;
        this.content = content;
        this.member = member;
        this.status = status;
        this.scope = scope;
        this.view = 0;
    }

    public void setQuestion(String title, String content, Scope scope) {
        if (this.status != Status.QUESTION_ANSWERED) {
            this.title = title;
            this.content = content;
            if (scope != null) {
                this.scope = scope;
            }
        }
    }

    public void setStatusAnswered() {
        this.status = Status.QUESTION_ANSWERED;
    }

    public void setStatusRegistration() {
        this.status = Status.QUESTION_REGISTRATION;
    }

    public void setStatusDeleted() {
        this.status = Status.QUESTION_DELETE;
    }

    public void deleteRelation() {
        this.reply = null;
    }

    public void addView() {
        this.view += 1;
    }

    public void setReply(Reply reply) {
        this.reply = reply;
        setStatusAnswered();
    }

    public void editReply(String content) {
        this.reply.setReply(content);
    }

}
