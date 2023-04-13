package krkr.board.dto;

import krkr.board.domain.Question;
import krkr.board.domain.QuestionImg;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class QuestionImgDto {

    private String imgName;

    private String imgUrl;

    private Question question;

    public QuestionImgDto(String imgName, String imgUrl, Question question) {
        this.imgName = imgName;
        this.imgUrl = imgUrl;
        this.question = question;
    }

    public QuestionImg toEntity() {
        return new QuestionImg(imgName, imgUrl, question);
    }
}
