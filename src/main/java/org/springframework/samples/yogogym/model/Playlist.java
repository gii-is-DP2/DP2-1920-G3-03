package org.springframework.samples.yogogym.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Playlist {
	
private String playlistName;

public Playlist() {
}

public String getPlaylistName() {
  return playlistName;
}

public void setPlaylistName(String playlistName) {
  this.playlistName = playlistName;
}


@Override
public String toString() {
  return "Playlist: "+playlistName;
}

}
