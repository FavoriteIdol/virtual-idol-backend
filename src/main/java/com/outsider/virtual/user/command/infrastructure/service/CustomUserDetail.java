package com.outsider.virtual.user.command.infrastructure.service;

import com.outsider.virtual.user.command.domain.aggregate.User;
import com.outsider.virtual.user.command.domain.aggregate.embeded.Tier;
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
public class CustomUserDetail implements UserDetails  {
    private final User user;
    private Map<String, Object> attributes;
    public CustomUserDetail(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 유저 권한 추가해주고 전달
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new SimpleGrantedAuthority(this.user.getAuthority().name()));
        return collection;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }
    @Override
    public String getUsername() {
        return this.user.getEmail();
    }

    //    userId 가져오기
    public Long getId() {
        return this.user.getId();
    }

    //    계정이 만료되었는지
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    // 계정이 잠겨있는지
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //    계정의 자격 증명(비밀번호)이 만료되엇는지
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //    계쩡이 활성화되어 있는지 여부
    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getName() {
        return this.user.getUserName();
    }



}
