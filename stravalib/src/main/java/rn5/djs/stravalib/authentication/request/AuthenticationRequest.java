package rn5.djs.stravalib.authentication.request;

import rn5.djs.stravalib.authentication.rest.AuthRest;
import rn5.djs.stravalib.authentication.api.AuthenticationAPI;
import rn5.djs.stravalib.authentication.model.AuthenticationResponse;

import retrofit2.Call;

public class AuthenticationRequest {
    private int clientId;
    private String clientSecret;
    private final AuthRest restService;
    private final AuthenticationAPI api;
    private String code;
    private String grantType;

    public AuthenticationRequest(AuthRest restService, AuthenticationAPI api) {
        this.restService = restService;
        this.api = api;
    }

    public AuthenticationRequest forClientId(int clientId) {
        this.clientId = clientId;
        return this;
    }

    public AuthenticationRequest withClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
        return this;
    }

    public AuthenticationRequest withCode(String code) {
        this.code = code;
        return this;
    }

    public AuthenticationRequest withGrantType(String grantType) {
        this.grantType = grantType;
        return this;
    }

    public AuthenticationResponse execute() {
        Call<AuthenticationResponse> call = restService.authenticate(clientId, clientSecret, code, grantType);
        return api.execute(call);
    }
}
