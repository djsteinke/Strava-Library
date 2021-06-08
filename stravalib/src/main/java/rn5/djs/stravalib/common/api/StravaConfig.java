package rn5.djs.stravalib.common.api;

import rn5.djs.stravalib.common.model.Token;

import okhttp3.Interceptor;
import retrofit2.Retrofit;

import static rn5.djs.stravalib.common.model.Constants.STRAVA_API_URL;
import static rn5.djs.stravalib.common.model.Constants.STRAVA_BASE_URL;

public class StravaConfig extends Config {

    public StravaConfig(Retrofit retrofit) {
        super(retrofit);
    }

    public static StravaConfig.Builder withToken(String token) {
        return new StravaConfig.Builder(token);
    }

    public static StravaConfig.Builder withToken(Token token) {
        return withToken(token.toString());
    }

    public static StravaConfig.Builder auth() {
        return new StravaConfig.Builder();
    }

    public static class Builder {

        private String token;
        private String baseURL;
        private boolean debug = false;
        private boolean auth = false;

        public Builder() {
            this.baseURL = STRAVA_BASE_URL;
            this.auth = true;
        }

        public Builder(String token) {
            this.baseURL = STRAVA_BASE_URL + STRAVA_API_URL;
            this.token = token;
        }

        public Builder debug() {
            debug = true;
            return this;
        }

        public Builder baseURL(String baseURL) {
            this.baseURL = baseURL;
            return this;
        }

        public StravaConfig build() {
            if (auth) {
                Interceptor[] interceptors = null;
                return new StravaConfig(createRetrofit(debug,baseURL, interceptors));
            }
            return new StravaConfig(createRetrofit(debug, baseURL, new AuthorizationInterceptor(token)));
        }
    }
}