package rn5.djs.stravalib.upload.request;

import retrofit2.Call;
import rn5.djs.stravalib.upload.api.UploadAPI;
import rn5.djs.stravalib.upload.model.UploadStatus;
import rn5.djs.stravalib.upload.rest.UploadRest;

public class CheckUploadStatusRequest {

    private final long id;
    private final UploadRest uploadRest;
    private final UploadAPI uploadAPI;

    public CheckUploadStatusRequest(long id, UploadRest uploadRest, UploadAPI uploadAPI) {
        this.id = id;
        this.uploadRest = uploadRest;
        this.uploadAPI = uploadAPI;
    }

    public UploadStatus execute() {
        Call<UploadStatus> call = uploadRest.checkUploadStatus(id);
        return uploadAPI.execute(call);
    }
}
