package com.lulugyda.models.enums;

public enum AllowedContentType {

    PNG("image/png");

    private final String mediaType;

    AllowedContentType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getMediaType() {
        return mediaType;
    }

    public static boolean isAllowed(String mediaType) {
        for (AllowedContentType type : values()) {
            if (type.getMediaType().equals(mediaType)) {
                return true;
            }
        }
        return false;
    }
}

