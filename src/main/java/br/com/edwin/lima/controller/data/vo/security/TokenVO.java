package br.com.edwin.lima.controller.data.vo.security;

import br.com.edwin.lima.controller.data.vo.CarVO;

import java.util.Date;
import java.util.List;

public class TokenVO {
    private String login;
    private Boolean authenticated;
    private Date created;
    private Date expiration;
    private String accessToken;
    private String refreshToken;

    private List<CarVO> cars;

    private Long keyUser;
    public TokenVO() {}

    public TokenVO(
            String login,
            Boolean authenticated,
            Date created,
            Date expiration,
            String accessToken,
            String refreshToken) {
        this.login = login;
        this.authenticated = authenticated;
        this.created = created;
        this.expiration = expiration;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Boolean getAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(Boolean authenticated) {
        this.authenticated = authenticated;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public List<CarVO> getCars() {
        return cars;
    }

    public void setCars(List<CarVO> cars) {
        this.cars = cars;
    }

    public Long getKeyUser() {
        return keyUser;
    }

    public void setKeyUser(Long keyUser) {
        this.keyUser = keyUser;
    }
}
