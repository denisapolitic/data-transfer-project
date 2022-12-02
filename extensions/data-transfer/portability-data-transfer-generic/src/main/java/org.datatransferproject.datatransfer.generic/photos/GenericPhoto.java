package org.datatransferproject.datatransfer.generic.photos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GenericPhoto {
    @JsonProperty("image")
    private String image;

    @JsonProperty("album")
    private String album;

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("exporter")
    private String exporter;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExporter() {
        return description;
    }

    public void setExporter(String description) {
        this.description = description;
    }
}
