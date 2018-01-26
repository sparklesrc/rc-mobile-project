package pe.com.rc.mobile.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import pe.com.rc.mobile.model.clan.Clan;

@Entity
@Table(name = "match_making")
public class MatchMaking extends Record implements Serializable {

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "team_a")
	private Clan teamA;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "team_b")
	private Clan teamB;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_create")
	private User userCreate;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_accept")
	private User userAccept;
	@Column(name = "temp_date")
	private Date tempDate;
	@Column(name = "approved_date")
	private Date approvedDate;
	@Column(name = "ip_serv")
	private String ipServ;
	@Column(name = "description")
	private String description;
	@Column(name = "phone")
	private String phone;
	@Column(name = "mail")
	private String mail;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "state_id")
	private State state;
	@OneToMany(mappedBy = "match_making", fetch = FetchType.LAZY)
	private List<MatchMakingComments> comments;

	public Clan getTeamA() {
		return teamA;
	}

	public void setTeamA(Clan teamA) {
		this.teamA = teamA;
	}

	public Clan getTeamB() {
		return teamB;
	}

	public void setTeamB(Clan teamB) {
		this.teamB = teamB;
	}

	public User getUserCreate() {
		return userCreate;
	}

	public void setUserCreate(User userCreate) {
		this.userCreate = userCreate;
	}

	public User getUserAccept() {
		return userAccept;
	}

	public void setUserAccept(User userAccept) {
		this.userAccept = userAccept;
	}

	public Date getTempDate() {
		return tempDate;
	}

	public void setTempDate(Date tempDate) {
		this.tempDate = tempDate;
	}

	public Date getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}

	public String getIpServ() {
		return ipServ;
	}

	public void setIpServ(String ipServ) {
		this.ipServ = ipServ;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

}
