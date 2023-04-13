package krkr.board.api;

import krkr.board.config.UserDetailsImpl;
import krkr.board.dto.QuestionResponse;
import krkr.board.dto.ReplyRequest;
import krkr.board.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/replies")
@RequiredArgsConstructor
public class ReplyController {
    // 답변은 생성, 수정, 삭제
    private final ReplyService replyService;

    @PostMapping("/{questionId}")
    public ResponseEntity<QuestionResponse> createReply(@AuthenticationPrincipal UserDetailsImpl user,
                                      @RequestBody ReplyRequest request, @PathVariable Long questionId) {
        return new ResponseEntity<>(replyService.createReply(user, questionId, request), HttpStatus.OK);
    }


    @PatchMapping("/{questionId}")
    public ResponseEntity<QuestionResponse> updateReply(@AuthenticationPrincipal UserDetailsImpl user,
                            @RequestBody ReplyRequest request, @PathVariable Long questionId) {
        return new ResponseEntity<>(replyService.updateReply(questionId, request), HttpStatus.OK);
    }

    @DeleteMapping("/{questionId}")
    public ResponseEntity<QuestionResponse> deleteReply(@AuthenticationPrincipal UserDetailsImpl user, @PathVariable Long questionId) {
        return new ResponseEntity<>(replyService.deleteReply(questionId), HttpStatus.OK);
    }
}
