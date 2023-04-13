package krkr.board.service;

import krkr.board.config.UserDetailsImpl;
import krkr.board.domain.Question;
import krkr.board.domain.Reply;
import krkr.board.dto.QuestionResponse;
import krkr.board.dto.ReplyRequest;
import krkr.board.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class ReplyService {

    private final QuestionService questionService;
    private final ReplyRepository replyRepository;

    public Reply findReplyById(Long replyId) {
        return replyRepository.findById(replyId)
                .orElseThrow(() -> new NoSuchElementException("해당 답변이 없음"));
    }

    public QuestionResponse createReply(UserDetailsImpl user, Long questionId, ReplyRequest request) {
        Question question = questionService.findQuestionById(questionId);
        request.setMember(user.getMember());
        Reply reply = request.toEntity();

        question.setReply(reply);
        return new QuestionResponse(question);
    }


    public QuestionResponse updateReply(Long questionId, ReplyRequest request) {
        Question question = questionService.findQuestionById(questionId);

        question.editReply(request.getContent());
        return new QuestionResponse(question);
    }

    public QuestionResponse deleteReply(Long questionId) {
        Question question = questionService.findQuestionById(questionId);
        replyRepository.deleteById(question.getReply().getId());
        question.setStatusRegistration();
        question.deleteRelation();
        return new QuestionResponse(question);
    }
}
