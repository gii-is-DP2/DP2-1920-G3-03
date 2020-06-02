
package org.springframework.samples.yogogym.model;

import java.text.DecimalFormat;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "name", "preview_url", "uri", "duration_ms" })
public class Track {

	@JsonProperty("id")
	private String id;

	@JsonProperty("duration_ms")
	private Double durationMs;

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

	@JsonProperty("duration_ms")
	public Double getDurationMs() {

		Double res = (int)(Math.round(durationMs/60000 * 100))/100.0;
		Integer entera = res.intValue();
		if(res-entera >0.6) {
			Double aux = entera-res;
			aux = aux-0.6;
			entera+=1;
			res = entera+aux;
		}
		return res; 
	}
	@JsonProperty("duration_ms")
	public void setDurationMs(Double durationMs) {
		this.durationMs = durationMs;
	}
}
