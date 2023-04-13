package krkr.board.repository;

import krkr.board.domain.Question;
import krkr.board.domain.QuestionImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionImgRepository extends JpaRepository<QuestionImg, Long> {

    List<QuestionImg> findAllByQuestion(Question question);
}
