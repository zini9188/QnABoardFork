package krkr.board.service;

import krkr.board.domain.Question;
import krkr.board.dto.QuestionRequest;
import krkr.board.dto.QuestionResponse;
import krkr.board.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class QuestionService {

    private final QuestionRepository questionRepository;

    public Question findQuestionById(Long questionId) {
        return questionRepository.findById(questionId)
                .orElseThrow(() -> new NoSuchElementException("해당 게시글이 없습니다"));
    }

    public QuestionResponse createQuestion(QuestionRequest request) {
        Question question = questionRepository.save(request.toEntity());
        return new QuestionResponse(question);
    }

    public QuestionResponse getQuestion(Long questionId) {
        Question question = findQuestionById(questionId);
        return new QuestionResponse(question);
    }

    public QuestionResponse updateQuestion(Long questionId, QuestionRequest request) {
        Question question = findQuestionById(questionId);

        question.setQuestion(request.getTitle(), request.getContent());
        return new QuestionResponse(question);
    }

    public void deleteQuestion(Long questionId) {
        Question question = findQuestionById(questionId);

        question.setStatusDeleted();
    }
}
