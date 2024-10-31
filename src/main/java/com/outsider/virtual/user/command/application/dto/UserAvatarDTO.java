package com.outsider.virtual.user.command.application.dto;

import com.outsider.virtual.util.ResponseDTO;
import lombok.Data;

@Data
public class UserAvatarDTO extends ResponseDTO {

    public String avatar;

    public UserAvatarDTO(String avatar) {
        this.avatar = avatar;
    }
}
