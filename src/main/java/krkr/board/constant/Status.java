package krkr.board.constant;

import krkr.board.constant.config.EnumMapperType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
public enum Status implements EnumMapperType {
    QUESTION_REGISTRATION("질문등록"),
    QUESTION_ANSWERED("답변등록"),
    QUESTION_DELETE("삭제됨");

    @Getter
    private final String title;

    @Override
    public String getCode() {
        return name();
    }

    public static Status ofCode(String title) {
        return Arrays.stream(Status.values())
                .filter(v -> v.getTitle().equals(title))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상태입니다"));
    }
}
