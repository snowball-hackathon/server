package com.capstone.server.DTO.RequestDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JoinRequestDTO {
    private String id;
    private String email;
    private String nickname;
    private String password;
    private String role;

    @Builder
    public JoinRequestDTO(String id, String email, String nickname, String password, String role){
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.role = role;
    }
}
