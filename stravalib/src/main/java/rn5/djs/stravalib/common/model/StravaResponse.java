package rn5.djs.stravalib.common.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StravaResponse<B> {
    private B response;
    private int code;

    public StravaResponse(B response, int code) {
        this.response = response;
        this.code = code;
    }
}
