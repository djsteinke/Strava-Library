package rn5.djs.stravalib.authentication.request;

import rn5.djs.stravalib.authentication.rest.AuthRest;
import rn5.djs.stravalib.authentication.api.AuthenticationAPI;
import rn5.djs.stravalib.authentication.model.AuthenticationResponse;

import retrofit2.Call;

public class DeauthorizationRequest {

    private final AuthRest restService;
    private final AuthenticationAPI api;



    public DeauthorizationRequest(AuthRest restService, AuthenticationAPI api) {
        this.restService = restService;
        this.api = api;
    }

    public AuthenticationResponse execute() {
        Call<AuthenticationResponse> call = restService.deauthorize();
        return api.execute(call);
    }
}
