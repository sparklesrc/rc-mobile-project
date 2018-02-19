package pe.com.rc.mobile.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "match_making_comments")
public class MatchMakingComments extends Record implements Serializable {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "match_making_id")
	private MatchMaking matchMaking;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	@Column(name = "description")
	private String description;

	public MatchMaking getMatchMaking() {
		return matchMaking;
	}

	public void setMatchMaking(MatchMaking matchMaking) {
		this.matchMaking = matchMaking;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
