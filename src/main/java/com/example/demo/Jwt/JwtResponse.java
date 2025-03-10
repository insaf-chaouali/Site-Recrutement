package com.example.demo.Jwt;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class JwtResponse {
    private String token;
    private String type="Bearer";
    private String refreshToken;
    private Long id;
    private String username;
    private String email;
    private List<String> roles;
    private String redirectUrl;

    public JwtResponse(String accesToken,  String refreshToken, Long id, String username, String email, List<String> roles,String redirectUrl) {
        this.token=accesToken;
        this.refreshToken=refreshToken;
        this.id= id;
        this.username=username;
        this.email=email;
        this.roles=roles;
        this.redirectUrl = redirectUrl;
    }
    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }
}
