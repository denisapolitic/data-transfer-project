package org.datatransferproject.datatransfer.generic.social;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GenericPost {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSiteId() {
        return siteId;
    }

    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public GenericPostStatusType getStatusType() {
        return statusType;
    }

    public void setStatusType(GenericPostStatusType statusType) {
        this.statusType = statusType;
    }

    public GenericPostFormatType getFormatType() {
        return formatType;
    }

    public void setFormatType(GenericPostFormatType formatType) {
        this.formatType = formatType;
    }

    public void setExporter(String exporter) {
        this.exporter = exporter;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getLinks() {
        return links;
    }

    public void setLinks(String links) {
        this.links = links;
    }

    @JsonProperty("ID")
    private int id;

    @JsonProperty("site_ID")
    private int siteId;

    @JsonProperty("title")
    private String title;

    @JsonProperty("content")
    private String content;

    @JsonProperty("date")
    private String date;

    @JsonProperty("location")
    private String location;

    @JsonProperty("published")
    private String published;

    @JsonProperty("format")
    private GenericPostFormatType formatType;

    @JsonProperty("status")
    private GenericPostStatusType statusType;

    @JsonProperty("exporter")
    private String exporter;

    @JsonProperty("images")
    private String images;

    @JsonProperty("links")
    private String links;
}
