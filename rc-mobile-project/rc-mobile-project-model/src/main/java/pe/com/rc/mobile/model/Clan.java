package pe.com.rc.mobile.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "clan")
public class Clan extends Record implements Serializable {

	@Column(name = "name")
	private String name;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "game_id")
	private Game game;
	@Column(name = "description")
	private String decription;
	@Column(name = "stars_num")
	private Integer starsNumber;
	@OneToMany(mappedBy = "clan", fetch = FetchType.LAZY)
	private List<ClanComments> comments;
	@ManyToMany
	@JoinTable(name = "clan_members", joinColumns = { @JoinColumn(name = "clan_id") }, inverseJoinColumns = {
			@JoinColumn(name = "user_id") })
	private List<User> members = new ArrayList<User>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public String getDecription() {
		return decription;
	}

	public void setDecription(String decription) {
		this.decription = decription;
	}

	public Integer getStarsNumber() {
		return starsNumber;
	}

	public void setStarsNumber(Integer starsNumber) {
		this.starsNumber = starsNumber;
	}

	public List<ClanComments> getComments() {
		return comments;
	}

	public void setComments(List<ClanComments> comments) {
		this.comments = comments;
	}

	public List<User> getMembers() {
		return members;
	}

	public void setMembers(List<User> members) {
		this.members = members;
	}

}
