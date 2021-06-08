package rn5.djs.stravalib.upload.model;

public enum FileType {
    FIT("fit"),
    FIT_GZ("fit.gz"),
    TCX("tcx"),
    TCX_GZ("tcx.gz"),
    GPX("gpx"),
    GPX_GZ("gpx.gz");

    private String rawValue;

    FileType(String rawValue) {
        this.rawValue = rawValue;
    }

    @Override
    public String toString() {
        return rawValue;
    }
}
