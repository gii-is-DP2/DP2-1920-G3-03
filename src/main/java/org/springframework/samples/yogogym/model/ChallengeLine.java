package org.springframework.samples.yogogym.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;

@Entity
@Table(name = "challenges_lines")
public class ChallengeLine extends BaseEntity{

	@Column(name = "photo_url")
	@URL
	private String photoURL;
	
	@Column(name = "status")
	@NotNull
	private ChallengeStatus status;
	
	// Relations
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "challenge_id")
	private Challenge challenge;

	public String getPhotoURL() {
		return photoURL;
	}

	public void setPhotoURL(String photoURL) {
		this.photoURL = photoURL;
	}

	public ChallengeStatus getStatus() {
		return status;
	}

	public void setStatus(ChallengeStatus status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((photoURL == null) ? 0 : photoURL.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChallengeLine other = (ChallengeLine) obj;
		if (photoURL == null) {
			if (other.photoURL != null)
				return false;
		} else if (!photoURL.equals(other.photoURL))
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ChallengeLine [photoURL=" + photoURL + ", status=" + status + "]";
	}
	
	
}
