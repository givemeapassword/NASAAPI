package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NASAAnswer {
    String copyright;
    String date;
    String explanation;
    String hdurl;
    String media_type;
    String service_version;
    String title;
    String url;

    public NASAAnswer(@JsonProperty("copyright") String copyright,
                      @JsonProperty("date") String date,
                      @JsonProperty("explanation")String explanation,
                      @JsonProperty("url")String url,
                      @JsonProperty("title")String title,
                      @JsonProperty("media_type")String media_type,
                      @JsonProperty("service_version")String service_version,
                      @JsonProperty("hdurl")String hdurl) {
        this.copyright = copyright;
        this.date = date;
        this.explanation = explanation;
        this.url = url;
        this.title = title;
        this.media_type = media_type;
        this.service_version = service_version;
        this.hdurl = hdurl;
    }
}
