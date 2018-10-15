package com.shakti.githubissueapplication.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.Pattern;

@ConfigurationProperties("github")
public class GithubProperties {
    /**
     * github api-token(username :token)
     */
    @Pattern(regexp = "\\w+:\\w+")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
