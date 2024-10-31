package com.outsider.virtual.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED) // 401 상태코드를 반환
public class NotAuthenticationException extends RuntimeException {

  public NotAuthenticationException(String msg) {
        super(msg);
  }
  
}
