package rn5.djs.stravalib.common.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Constants {
    public static Token TOKEN;

    public static final int STRAVA_CLIENT_ID = 24797;

    public static final String STRAVA_CLIENT_SECRET = "ab7df8c0eecb22a599600530c40b6067a2b74efa";

    public static final String STRAVA_API_URL = "api/v3/";
    public static final String STRAVA_BASE_URL = "https://www.strava.com/";

    public static <T> T getObjectFromJsonString(String val, Class<T> t) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(val, t);
    }
}
