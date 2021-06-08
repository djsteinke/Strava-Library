package rn5.djs.stravalib.authentication.rest;

import rn5.djs.stravalib.authentication.model.AuthenticationResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface AuthRest {

    @POST("/oauth/token")
    @FormUrlEncoded
    Call<AuthenticationResponse> authenticate(
            @Field("client_id") int clientId,
            @Field("client_secret") String clientSecret,
            @Field("code") String code,
            @Field("grant_type") String grantType);

    @POST("/oauth/deauthorize")
    Call<AuthenticationResponse> deauthorize();

    @POST("/oauth/token")
    @FormUrlEncoded
    Call<AuthenticationResponse> refreshToken(
            @Field("client_id") int clientId,
            @Field("client_secret") String clientSecret,
            @Field("refresh_token") String refreshToken,
            @Field("grant_type") String grantType);
}
