package rn5.djs.stravalib.gear.rest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rn5.djs.stravalib.gear.model.Gear;

public interface GearRest {
    @GET("gear/{id}")
    Call<Gear> getGear(@Path("id") String id);
}
