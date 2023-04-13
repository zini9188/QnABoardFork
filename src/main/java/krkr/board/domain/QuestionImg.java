package krkr.board.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class QuestionImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_img_id")
    private Long id;

    @Column(nullable = false)
    private String imgName;

    @Column(nullable = false)
    private String imgUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "post_id")
    private Question question;

    public QuestionImg(String imgName, String imgUrl, Question question) {
        this.imgName = imgName;
        this.imgUrl = imgUrl;
        setQuestion(question);
    }

    public void setQuestion(Question question) {
        this.question = question;
        if (!question.getQuestionImgList().contains(this)) {
            question.getQuestionImgList().add(this);
        }
    }
}
