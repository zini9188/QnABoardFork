package krkr.board.api;

import krkr.board.config.UserDetailsImpl;
import krkr.board.dto.QuestionRequest;
import krkr.board.dto.QuestionResponse;
import krkr.board.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/questions")
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping("/new")
    public ResponseEntity<QuestionResponse> createQuestion(@AuthenticationPrincipal UserDetailsImpl user,
                                                           @RequestPart(value = "file", required = false) List<MultipartFile> multipartFiles,
                                                           @RequestPart QuestionRequest request) {
        return new ResponseEntity<>(questionService.createQuestion(user, request, multipartFiles), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity getAllQuestions(@PageableDefault(size = 3, sort="id", direction = Sort.Direction.DESC) Pageable pageable) {
        return new ResponseEntity(questionService.getAllQuestions(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionResponse> getQuestion(@AuthenticationPrincipal UserDetailsImpl user,
                                                        @PathVariable Long id) {
        return new ResponseEntity<>(questionService.getQuestion(user, id), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<QuestionResponse> updateQuestion(@AuthenticationPrincipal UserDetailsImpl user,
                                                           @PathVariable Long id,
                                                           @RequestBody QuestionRequest request) {
        return new ResponseEntity<>(questionService.updateQuestion(user, id, request), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteQuestion(@AuthenticationPrincipal UserDetailsImpl user,
                               @PathVariable Long id) {
        questionService.deleteQuestion(user, id);
    }
}
