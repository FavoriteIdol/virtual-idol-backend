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

        if (userIdParam != null) {
            Long userId = Long.parseLong(userIdParam);

            if (userId.equals(currentUserId)) {
                // 쿼리 스트링의 userid가 현재 사용자의 ID와 일치하는 경우
                granted = true;  // 모든 요청(GET, POST, PUT, DELETE 등) 허용
            } else {
                // 쿼리 스트링의 userid가 일치하지 않는 경우
                granted = "GET".equalsIgnoreCase(method);  // GET 요청만 허용
            }
        } else {
            // userid 파라미터가 없으면 기본적으로 GET 요청만 허용
            granted = "GET".equalsIgnoreCase(method);
        }

        return new AuthorizationDecision(granted);
    }
}


