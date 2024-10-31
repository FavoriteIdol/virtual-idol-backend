package com.outsider.virtual.user.command.infrastructure.service;

import com.outsider.virtual.user.command.domain.aggregate.User;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

// security 파일
@Getter
@ToString
public class CustomUserDetail implements UserDetails {
    private final User user;
    private Map<String, Object> attributes;

    // 기존 생성자들
    public CustomUserDetail(User user) {
        this.user = user;
    }

    public CustomUserDetail(User user, Map<String, Object> attributes) {
        this.user = user;
        this.attributes = attributes;
    }

    // 새로 추가된 생성자
    public CustomUserDetail(Long userId, String email, String userName, String role) {
        this.user = new User();
        this.user.setId(userId); // User 클래스에 setId() 메서드가 있다고 가정
        this.user.setEmail(email); // User 클래스에 setEmail() 메서드가 있다고 가정
        this.user.setUserName(userName); // User 클래스에 setUserName() 메서드가 있다고 가정
        this.user.setAuthority(role); // User 클래스에 setAuthority() 메서드가 있다고 가정
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 유저 권한 추가해주고 전달
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new SimpleGrantedAuthority(this.user.getAuthority().name()));
        return collection;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getEmail();
    }

    // userId 가져오기
    public Long getId() {
        return this.user.getId();
    }

    // 계정이 만료되었는지
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정이 잠겨있는지
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 계정의 자격 증명(비밀번호)이 만료되었는지
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정이 활성화되어 있는지 여부
    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getName() {
        return this.user.getUserName();
    }



}
