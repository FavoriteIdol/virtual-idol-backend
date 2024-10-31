package com.outsider.virtual.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED) // 401 상태코드를 반환
public class NotMineException extends RuntimeException {

    // 기본 메시지 설정
    private static final String DEFAULT_MESSAGE = "이 자원은 해당 유저에 포함되지 않습니다.";

    // 기본 메시지를 사용하는 생성자
    public NotMineException() {
        super(DEFAULT_MESSAGE);
    }

    // 사용자 정의 메시지를 사용하는 생성자
    public NotMineException(String msg) {
        super(msg);
    }
}
