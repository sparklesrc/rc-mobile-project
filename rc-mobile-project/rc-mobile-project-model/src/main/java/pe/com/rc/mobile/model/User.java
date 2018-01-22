package pe.com.rc.mobile.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User extends Record implements Serializable {

	@Column(name = "steam_id")
	private Long steamId;
	@Column(name = "steam_name")
	private String name;
	@Column(name = "steam_link_avatar")
	private String steamLinkAvatar;
	@Column(name = "last_login")
	private Date lastLogin;
//	@ManyToMany(mappedBy = "user")
//	private List<Clan> clans;

	public Long getSteamId() {
		return steamId;
	}

	public void setSteamId(Long steamId) {
		this.steamId = steamId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSteamLinkAvatar() {
		return steamLinkAvatar;
	}

	public void setSteamLinkAvatar(String steamLinkAvatar) {
		this.steamLinkAvatar = steamLinkAvatar;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

//	public List<Clan> getClans() {
//		return clans;
//	}
//
//	public void setClans(List<Clan> clans) {
//		this.clans = clans;
//	}
}
