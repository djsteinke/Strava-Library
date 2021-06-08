package rn5.djs.stravalib.authentication.api;

import rn5.djs.stravalib.authentication.rest.AuthRest;
import rn5.djs.stravalib.authentication.request.AuthenticationRequest;
import rn5.djs.stravalib.authentication.request.DeauthorizationRequest;
import rn5.djs.stravalib.authentication.request.RefreshTokenRequest;

import rn5.djs.stravalib.common.api.StravaAPI;
import rn5.djs.stravalib.common.api.StravaConfig;

public class AuthenticationAPI extends StravaAPI {

    public AuthenticationAPI(StravaConfig config) {
        super(config);
    }

    public AuthenticationRequest getToken() {
        return new AuthenticationRequest(getAPI(AuthRest.class), this);
    }

    public RefreshTokenRequest refreshToken() {
        return new RefreshTokenRequest(getAPI(AuthRest.class), this);
    }

    public DeauthorizationRequest deauthorize() {
        return new DeauthorizationRequest(getAPI(AuthRest.class), this);
    }
}
