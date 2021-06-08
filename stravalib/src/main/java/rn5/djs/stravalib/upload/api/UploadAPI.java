package rn5.djs.stravalib.upload.api;

import java.io.File;

import rn5.djs.stravalib.common.api.StravaAPI;
import rn5.djs.stravalib.common.api.StravaConfig;
import rn5.djs.stravalib.upload.request.CheckUploadStatusRequest;
import rn5.djs.stravalib.upload.request.UploadFileRequest;
import rn5.djs.stravalib.upload.rest.UploadRest;

public class UploadAPI extends StravaAPI {

    public UploadAPI(StravaConfig config) {
        super(config);
    }

    public UploadFileRequest uploadFile(File file) {
        return new UploadFileRequest(file, getAPI(UploadRest.class), this);
    }

    public CheckUploadStatusRequest checkUploadStatus(long id) {
        return new CheckUploadStatusRequest(id, getAPI(UploadRest.class), this);
    }
}
