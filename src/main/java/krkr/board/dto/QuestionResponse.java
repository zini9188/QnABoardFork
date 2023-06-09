package krkr.board.dto;

import krkr.board.constant.Scope;
import krkr.board.constant.Status;
import krkr.board.domain.Member;
import krkr.board.domain.Question;
import krkr.board.domain.QuestionImg;
import krkr.board.domain.Reply;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
public class QuestionResponse {

    private Long id;

    private String title;

    private String content;

    private Member member;

    private Status status;

    private Scope scope;

    private Integer view;

    private Reply reply;

    private List<QuestionImg> questionImgList;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public QuestionResponse(Question question) {
        this.id = question.getId();
        this.title = question.getTitle();
        this.content = question.getContent();
        this.member = question.getMember();
        this.status = question.getStatus();
        this.scope = question.getScope();
        this.view = question.getView();
        this.reply = question.getReply();
        this.questionImgList = question.getQuestionImgList();
        this.createdAt = question.getCreatedAt();
        this.updatedAt = question.getUpdatedAt();
    }
}
