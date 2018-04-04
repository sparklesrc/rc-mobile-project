package pe.com.rc.mobile.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User extends Record implements Serializable {

	@Column(name = "steam_id")
	private Long steamId;
	@Column(name = "last_login")
	private Date lastLogin;

	@OneToMany(mappedBy = "primaryKey.user", cascade = CascadeType.ALL)
	private Set<ClanMembers> clanMembers = new HashSet<ClanMembers>();

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rol_id")
	private Rol rol;

	@Column(name = "mail")
	private String mail;
	@Column(name = "password")
	private String password;

	@Column(name = "edad")
	private Integer edad;
	@Column(name = "pais")
	private String pais;

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

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

}
