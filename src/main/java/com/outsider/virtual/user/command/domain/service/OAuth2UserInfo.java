package com.outsider.virtual.user.command.domain.service;

public interface OAuth2UserInfo {

    String getProviderId();
    String getProvider();
    String getEmail();
    String getName();
    String getPictureUrl();

}

