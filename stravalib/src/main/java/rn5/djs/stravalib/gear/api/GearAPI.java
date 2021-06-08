package rn5.djs.stravalib.gear.api;

import rn5.djs.stravalib.common.api.StravaAPI;
import rn5.djs.stravalib.common.api.StravaConfig;
import rn5.djs.stravalib.gear.request.GearRequest;
import rn5.djs.stravalib.gear.rest.GearRest;

public class GearAPI extends StravaAPI {

    public GearAPI(StravaConfig config) {
        super(config);
    }

    public GearRequest getGear(String gearId) {
        return new GearRequest(gearId, getAPI(GearRest.class), this);
    }
}
