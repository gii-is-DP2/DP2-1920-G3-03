
package org.springframework.samples.yogogym.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "description",
    "href",
    "id",
    "tracks",
    "type",
    "uri"
})
public class Playlist {

    
    @JsonProperty("description")
    private String description;
  
    @JsonProperty("href")
    private String href;
    
    @JsonProperty("id")
    private String id;
   
    @JsonProperty("name")
    private String name;
       
    @JsonProperty("tracks")
    private Tracks tracks;
    
    @JsonProperty("type")
    private String type;

    @JsonProperty("uri")
    private String uri;


    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }


    @JsonProperty("href")
    public String getHref() {
        return href;
    }

    @JsonProperty("href")
    public void setHref(String href) {
        this.href = href;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }


    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("tracks")
    public Tracks getTracks() {
        return tracks;
    }

    @JsonProperty("tracks")
    public void setTracks(Tracks tracks) {
        this.tracks = tracks;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("uri")
    public String getUri() {
        return uri;
    }

    @JsonProperty("uri")
    public void setUri(String uri) {
        this.uri = uri;
    }
    

}
