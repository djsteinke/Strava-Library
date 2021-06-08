package rn5.djs.stravalib.gear.request;

import retrofit2.Call;
import rn5.djs.stravalib.gear.api.GearAPI;
import rn5.djs.stravalib.gear.model.Gear;
import rn5.djs.stravalib.gear.rest.GearRest;

public class GearRequest {

    private final String gearId;
    private final GearRest restService;
    private final GearAPI api;

    public GearRequest(String gearId, GearRest restService, GearAPI api) {
        this.gearId = gearId;
        this.restService = restService;
        this.api = api;
    }

    public Gear execute() {
        Call<Gear> call = restService.getGear(gearId);
        return api.execute(call);
    }
}
