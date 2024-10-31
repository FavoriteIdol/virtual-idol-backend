package com.outsider.virtual.config;

import com.outsider.virtual.user.command.infrastructure.service.CustomUserDetail;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class WebSecurityService {

    /**
     * 사용자 ID와 현재 인증된 사용자 정보를 비교하여 권한을 확인합니다.
     *
     * @param authentication 현재 인증된 사용자 정보
     * @param userId 요청된 사용자 ID
     * @return 권한이 있는 경우 true, 그렇지 않은 경우 false
     */
    public boolean checkUserId(Authentication authentication, Long userId) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return false; // 인증되지 않은 사용자에 대해 false 반환
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof CustomUserDetail) {
            Long currentUserId = ((CustomUserDetail) principal).getId();
            return currentUserId.equals(userId);
        }

        return false;
    }

    public Long getCurrentUserId(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }

        Object principal = authentication.getPrincipal();
        if (principal instanceof CustomUserDetail) {
            // UserDetails에서 사용자 이름을 가져와 ID로 사용 (필요 시 커스터마이징)
            return ((CustomUserDetail) principal).getId();
        }

        // 기본적으로 사용자 이름을 ID로 간주
        return null;
    }
}
