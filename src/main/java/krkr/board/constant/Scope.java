package krkr.board.constant;

import krkr.board.constant.config.EnumMapperType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
public enum Scope implements EnumMapperType {
    PUBLIC("공개"),
    SECRET("비공개");

    @Getter
    private final String title;

    @Override
    public String getCode() {
        return name();
    }

    public static Scope ofCode(String title) {
        return Arrays.stream(Scope.values())
                .filter(v -> v.getTitle().equals(title))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상태입니다"));
    }
}
