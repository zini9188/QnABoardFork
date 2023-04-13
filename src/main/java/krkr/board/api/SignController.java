package krkr.board.api;

import krkr.board.dto.MemberRequest;
import krkr.board.dto.MemberResponse;
import krkr.board.service.SignService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SignController {

    private final SignService signService;

    @PostMapping("/sign-up")
    public void signUp(@RequestBody MemberRequest signUpDto) {
        signService.signUp(signUpDto);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<MemberResponse> signIn(@RequestBody MemberRequest signInDto) {
        return new ResponseEntity<>(signService.signIn(signInDto), HttpStatus.OK);
    }

    @PostMapping("/admin")
    public void enrollAdmin() {
        signService.enrollAdmin();
    }
}

