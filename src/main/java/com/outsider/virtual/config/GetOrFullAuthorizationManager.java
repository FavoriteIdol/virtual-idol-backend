package com.outsider.virtual.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
public class GetOrFullAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {

    private final WebSecurityService webSecurityService;

    public GetOrFullAuthorizationManager(WebSecurityService webSecurityService) {
        this.webSecurityService = webSecurityService;
    }

    @Override
    public AuthorizationDecision check(Supplier<Authentication> authenticationSupplier, RequestAuthorizationContext context) {
        // Supplier에서 Authentication 객체를 가져옴
        Authentication authentication = authenticationSupplier.get();

        // RequestAuthorizationContext에서 요청 객체를 가져옴
        HttpServletRequest request = context.getRequest();

        // 요청의 HTTP 메서드를 가져옴 (GET, POST, PUT, DELETE 등)
        String method = request.getMethod();

        // 쿼리 스트링에서 userid 파라미터를 가져옴
        String userIdParam = request.getParameter("userid");

        // 현재 인증된 사용자의 ID를 가져옴
        Long currentUserId = webSecurityService.getCurrentUserId(authentication);

        boolean granted;


            // userid 파라미터가 없으면 기본적으로 GET 요청만 허용
            if(currentUserId!=null)
            {
                granted = "GET".equalsIgnoreCase(method) || "POST".equalsIgnoreCase(method)|| "PUT".equalsIgnoreCase(method)|| "DELETE".equalsIgnoreCase(method);

            }else
            {
                granted = "GET".equalsIgnoreCase(method);
            }


        return new AuthorizationDecision(granted);
    }
}


