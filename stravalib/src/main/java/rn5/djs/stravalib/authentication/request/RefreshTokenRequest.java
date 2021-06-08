package rn5.djs.stravalib.authentication.request;

import rn5.djs.stravalib.authentication.rest.AuthRest;
import rn5.djs.stravalib.authentication.api.AuthenticationAPI;
import rn5.djs.stravalib.authentication.model.AuthenticationResponse;

import retrofit2.Call;

public class RefreshTokenRequest {
    private int clientId;
    private String clientSecret;
    private final AuthRest restService;
    private final AuthenticationAPI api;
    private String refreshToken;
    private String grantType;

    public RefreshTokenRequest(AuthRest restService, AuthenticationAPI api) {
        this.restService = restService;
        this.api = api;
    }

    public RefreshTokenRequest forClientId(int clientId) {
        this.clientId = clientId;
        return this;
    }

    public RefreshTokenRequest withClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
        return this;
    }

    public RefreshTokenRequest withRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
        return this;
    }

    public RefreshTokenRequest withGrantType(String grantType) {
        this.grantType = grantType;
        return this;
    }

    public AuthenticationResponse execute() {
        Call<AuthenticationResponse> call = restService.refreshToken(clientId, clientSecret, refreshToken, grantType);
        return api.execute(call);
    }
}
