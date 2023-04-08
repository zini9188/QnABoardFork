package krkr.board.controller;

import krkr.board.dto.QuestionRequest;
import krkr.board.dto.QuestionResponse;
import krkr.board.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping("/new")
    public ResponseEntity<QuestionResponse> createQuestion(@RequestBody QuestionRequest request) {
        return new ResponseEntity<>(questionService.createQuestion(request), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionResponse> getQuestion(@PathVariable Long id) {
        return new ResponseEntity<>(questionService.getQuestion(id), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<QuestionResponse> updateQuestion(@PathVariable Long id, @RequestBody QuestionRequest request) {
        return new ResponseEntity<>(questionService.updateQuestion(id, request), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
    }
}
