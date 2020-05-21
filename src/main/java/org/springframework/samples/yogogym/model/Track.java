
package org.springframework.samples.yogogym.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"id", "name", "preview_url", "uri" })
public class Track {

	@JsonProperty("id")
	private String id;

	@JsonProperty("name")
	private String name;
	
	@JsonProperty("preview_url")
	private String previewUrl;

	@JsonProperty("uri")
	private String uri;

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

	@JsonProperty("preview_url")
	public String getPreviewUrl() {
		return previewUrl;
	}

	@JsonProperty("preview_url")
	public void setPreviewUrl(String previewUrl) {
		this.previewUrl = previewUrl;
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
