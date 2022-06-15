package org.datatransferproject.datatransfer.generic.social;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum GenericPostStatusType {
    @JsonProperty("publish")
    PUBLISH("publish"),

    @JsonProperty("draft")
    DRAFT("draft"),

    @JsonProperty("private")
    PRIVATE("private"),

    @JsonProperty("pending")
    PENDING("pending"),

    @JsonProperty("future")
    FUTURE("future"),

    @JsonProperty("auto-draft")
    AUTODRAFT("auto-draft"),

    @JsonProperty("trash")
    TRASH("trash");

    public String getStatus() {
        return status;
    }

    private String status;

    GenericPostStatusType(String status) {
        this.status = status;
    }
}
