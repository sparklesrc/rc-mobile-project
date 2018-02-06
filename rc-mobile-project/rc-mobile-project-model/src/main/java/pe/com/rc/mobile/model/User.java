package pe.com.rc.mobile.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
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

	@OneToMany(mappedBy = "primaryKey.user", cascade = CascadeType.ALL)
	private Set<ClanMembers> clanMembers = new HashSet<ClanMembers>();

	public User() {
	}

	public User(Long id) {
		this.setId(id);
	}
	
	public void addClan(ClanMembers clan) {
		this.clanMembers.add(clan);
	}

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

	public Set<ClanMembers> getClanMembers() {
		return clanMembers;
	}

	public void setClanMembers(Set<ClanMembers> clanMembers) {
		this.clanMembers = clanMembers;
	}

	public void addClanMember(ClanMembers clanMember) {
		this.clanMembers.add(clanMember);
	}

}
