package rn5.djs.stravalib.authentication.api;

import rn5.djs.stravalib.authentication.model.AuthenticationResponse;
import rn5.djs.stravalib.authentication.model.AuthenticationType;
import rn5.djs.stravalib.common.api.StravaConfig;
import rn5.djs.stravalib.common.model.Token;
import rn5.djs.stravalib.exception.StravaAPIException;
import rn5.djs.stravalib.exception.StravaUnauthorizedException;

import static rn5.djs.stravalib.common.model.Constants.*;

public class StravaAuthentication {
    private AuthenticationType type;

    public StravaAuthentication (AuthenticationType type) {
        this.type = type;
    }

    public AuthenticationResponse authorize(String code) {
        StravaConfig config = StravaConfig.auth()
                .debug()
                .build();
        AuthenticationAPI api = new AuthenticationAPI(config);
        AuthenticationResponse result = null;

        try {
            switch (type) {
                case AUTHENTICATE:
                    result = api.getToken()
                            .forClientId(STRAVA_CLIENT_ID)
                            .withClientSecret(STRAVA_CLIENT_SECRET)
                            .withCode(code)
                            .withGrantType("authorization_code")
                            .execute();
                    break;
                case REFRESH_TOKEN:
                    result = api.refreshToken()
                            .forClientId(STRAVA_CLIENT_ID)
                            .withClientSecret(STRAVA_CLIENT_SECRET)
                            .withRefreshToken(code)
                            .withGrantType("refresh_token")
                            .execute();
                    break;
                case DEAUTHORIZE:
                    result = api.deauthorize()
                            .execute();
                    break;

            }
        } catch (StravaAPIException | StravaUnauthorizedException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void processResponse(AuthenticationResponse response) {
        if (response != null) {
            switch (type) {
                case AUTHENTICATE:
                    TOKEN = new Token()
                            .withTokenType(response.getTokenType())
                            .withAccessToken(response.getAccessToken())
                            .withRefreshToken(response.getRefreshToken())
                            .expiresAt(response.getExpiresAt())
                            .withUsername(response.getAthlete().getUsername())
                            .withFirstName(response.getAthlete().getFirstName())
                            .withLastName(response.getAthlete().getLastName());
                    TOKEN.save();
                    break;
                case REFRESH_TOKEN:
                    if (TOKEN != null) {
                        TOKEN.setAccessToken(response.getAccessToken());
                        TOKEN.setRefreshToken(response.getRefreshToken());
                        TOKEN.setExpirationDate(response.getExpiresAt());
                        TOKEN.save();
                    }
                    break;
            }
        } else {
            if (type == AuthenticationType.DEAUTHORIZE) {
                TOKEN.delete();
                TOKEN = null;
            }
        }
    }
}
