package krkr.board.service;

import krkr.board.config.UserDetailsImpl;
import krkr.board.constant.Scope;
import krkr.board.constant.Status;
import krkr.board.domain.Question;
import krkr.board.dto.QuestionPageResponse;
import krkr.board.dto.QuestionRequest;
import krkr.board.dto.QuestionResponse;
import krkr.board.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final MemberService memberService;
    private final QuestionImgService questionImgService;

    public Question findQuestionById(Long questionId) {
        return questionRepository.findById(questionId)
                .orElseThrow(() -> new NoSuchElementException("해당 게시글이 없습니다"));
    }

    public QuestionResponse createQuestion(UserDetailsImpl user, QuestionRequest request, List<MultipartFile> multipartFiles) {
        request.setMember(user.getMember());
        Question question = questionRepository.save(request.toEntity());
        if (multipartFiles != null){
            questionImgService.createQuestionImgList(multipartFiles, question);
        }
        return new QuestionResponse(question);
    }

    public QuestionResponse getQuestion(UserDetailsImpl user, Long questionId) {
        Question question = findQuestionById(questionId);
        if (question.getScope() == Scope.SECRET) {
            //비밀글..
            MemberService.validateMemberAndRole(question.getMember(), user.getId());
        }
        if (question.getStatus() == Status.QUESTION_DELETE) {
            throw new NoSuchElementException("삭제된 질문입니다..");
        }
        question.addView(); // 조회수 증가
        return new QuestionResponse(question);
    }

    public QuestionResponse updateQuestion(UserDetailsImpl user, Long questionId, QuestionRequest request) {
        Question question = findQuestionById(questionId);
        memberService.validateMember(question.getMember(), user.getId());

        question.setQuestion(request.getTitle(), request.getContent(), request.getScope());
        return new QuestionResponse(question);
    }

    public void deleteQuestion(UserDetailsImpl user, Long questionId) {
        Question question = findQuestionById(questionId);
        memberService.validateMember(question.getMember(), user.getId());

        if (question.getStatus() == Status.QUESTION_DELETE) {
            throw new NoSuchElementException("이미 삭제된 질문입니다");
        }
        questionImgService.deleteQuestionImgList(question);
        question.setStatusDeleted();
    }

    public Page<QuestionPageResponse> getAllQuestions(Pageable pageable) {
        return questionRepository.findAllByStatusNot(pageable, Status.QUESTION_DELETE).map(QuestionPageResponse::new);
    }
}
