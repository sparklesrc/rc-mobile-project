package pe.com.rc.mobile.model.clan;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import pe.com.rc.mobile.model.ClanComments;
import pe.com.rc.mobile.model.ClanMembers;
import pe.com.rc.mobile.model.Game;
import pe.com.rc.mobile.model.Record;

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
	@OneToMany(mappedBy = "primaryKey.clan", cascade = CascadeType.ALL)
	private Set<ClanMembers> clanMembers = new HashSet<ClanMembers>();

	public Clan(Long id) {
		this.setId(id);
	}

	public Clan() {

	}

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

	public void addClanMember(ClanMembers clanMember) {
		this.clanMembers.add(clanMember);
	}

	public Set<ClanMembers> getClanMembers() {
		return clanMembers;
	}

	public void setClanMembers(Set<ClanMembers> clanMembers) {
		this.clanMembers = clanMembers;
	}

}
