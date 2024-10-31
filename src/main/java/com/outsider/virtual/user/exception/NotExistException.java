package com.outsider.virtual.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT) // 401 상태코드를 반환
public class NotExistException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "존재 하지 않습니다.";
    public NotExistException() {
        super(DEFAULT_MESSAGE);
    }
  public NotExistException(String msg) {
        super(msg);
  }

}
