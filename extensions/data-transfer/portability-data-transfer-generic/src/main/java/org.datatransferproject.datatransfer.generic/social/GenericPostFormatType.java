package org.datatransferproject.datatransfer.generic.social;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum GenericPostFormatType {
    @JsonProperty("default")
    DEFAULT("default"),

    @JsonProperty("standard")
    STANDARD("standard"),

    @JsonProperty("aside")
    ASIDE("aside"),

    @JsonProperty("chat")
    CHAT("chat"),

    @JsonProperty("gallery")
    GALLERY("gallery"),

    @JsonProperty("link")
    LINK("link"),

    @JsonProperty("image")
    IMAGE("image"),

    @JsonProperty("audio")
    AUDIO("audio"),

    @JsonProperty("status")
    STATUS("status"),

    @JsonProperty("quote")
    QUOTE("quote"),

    @JsonProperty("video")
    VIDEO("video");

    public String getFormat() {
        return format;
    }

    private String format;

    GenericPostFormatType(String format) {
        this.format = format;
    }
}
