package rn5.djs.stravalib.authentication.model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import rn5.djs.stravalib.common.model.Athlete;

@Data
public class AuthenticationResponse {
    @SerializedName("token_type") private String tokenType;
    @SerializedName("access_token") private String accessToken;
    @SerializedName("refresh_token") private String refreshToken;
    @SerializedName("expires_at") private Long expiresAt;
    @SerializedName("state") private String state;
    @SerializedName("athlete") private Athlete athlete;

    public AuthenticationResponse() {}
}
