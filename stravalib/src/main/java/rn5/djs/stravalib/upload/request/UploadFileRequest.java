package rn5.djs.stravalib.upload.request;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import rn5.djs.stravalib.common.model.StravaResponse;
import rn5.djs.stravalib.upload.api.UploadAPI;
import rn5.djs.stravalib.upload.model.FileType;
import rn5.djs.stravalib.upload.model.UploadActivityType;
import rn5.djs.stravalib.upload.model.UploadStatus;
import rn5.djs.stravalib.upload.rest.UploadRest;

public class UploadFileRequest {

    private final File file;
    private final UploadRest restService;
    private final UploadAPI uploadAPI;
    private UploadActivityType activityType;
    private String name;
    private String description;
    private Boolean isPrivate;
    private Boolean hasTrainer;
    private Boolean isCommute;
    private FileType fileType;
    private String externalId;

    public UploadFileRequest(File file, UploadRest restService, UploadAPI uploadAPI) {
        this.file = file;
        this.restService = restService;
        this.uploadAPI = uploadAPI;
    }

    public UploadFileRequest withDataType(FileType fileType) {
        this.fileType = fileType;
        return this;
    }

    public UploadFileRequest withActivityType(UploadActivityType activityType) {
        this.activityType = activityType;
        return this;
    }

    public UploadFileRequest withName(String name) {
        this.name = name;
        return this;
    }

    public UploadFileRequest withDescription(String description) {
        this.description = description;
        return this;
    }

    public UploadFileRequest isPrivate(boolean isPrivate) {
        this.isPrivate = isPrivate;
        return this;
    }

    public UploadFileRequest hasTrainer(boolean hasTrainer) {
        this.hasTrainer = hasTrainer;
        return this;
    }

    public UploadFileRequest isCommute(boolean isCommute) {
        this.isCommute = isCommute;
        return this;
    }

    public UploadFileRequest withExternalId(String externalId) {
        this.externalId = externalId;
        return this;
    }

    public UploadStatus execute() {
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);

        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);

        this.fileType = (fileType == null?FileType.FIT:fileType);
        this.activityType = (activityType == null?UploadActivityType.RIDE:activityType);
        this.isCommute = (isCommute == null?false:isCommute);
        this.hasTrainer = (hasTrainer == null?false:hasTrainer);
        this.isPrivate = (isPrivate == null?false:isPrivate);
        this.description = (description == null?"":description);

        Call<UploadStatus> call = restService.upload(
                requestBodyFromString(activityType.toString()),
                requestBodyFromString(name),
                requestBodyFromString(description),
                booleanToInt(isPrivate),
                booleanToInt(hasTrainer),
                booleanToInt(isCommute),
                requestBodyFromString(fileType.toString()),
                requestBodyFromString(externalId),
                body);
        return uploadAPI.execute(call);
    }

    public StravaResponse<UploadStatus> executeWithResponse() {
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);

        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);

        this.fileType = (fileType == null?FileType.FIT:fileType);
        this.activityType = (activityType == null?UploadActivityType.RIDE:activityType);
        this.isCommute = (isCommute == null?false:isCommute);
        this.hasTrainer = (hasTrainer == null?false:hasTrainer);
        this.isPrivate = (isPrivate == null?false:isPrivate);
        this.description = (description == null?"":description);

        Call<UploadStatus> call = restService.upload(
                requestBodyFromString(activityType.toString()),
                requestBodyFromString(name),
                requestBodyFromString(description),
                booleanToInt(isPrivate),
                booleanToInt(hasTrainer),
                booleanToInt(isCommute),
                requestBodyFromString(fileType.toString()),
                requestBodyFromString(externalId),
                body);
        return uploadAPI.executeWithStravaResponse(call);
    }

    private RequestBody requestBodyFromString(String str) {
        return RequestBody.create(MultipartBody.FORM, str);
    }

    private Integer booleanToInt(boolean b) {
        return b ? 1 : 0;
    }
}
