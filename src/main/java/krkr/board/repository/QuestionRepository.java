package krkr.board.repository;

import krkr.board.constant.Status;
import krkr.board.domain.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    Page<Question> findAllByStatusNot(Pageable pageable, Status status);
}
