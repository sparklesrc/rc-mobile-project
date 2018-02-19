package pe.com.rc.mobile.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import pe.com.rc.mobile.model.clan.Clan;

@Entity
@Table(name = "clan_members")
 @AssociationOverrides({ @AssociationOverride(name = "primaryKey.user",
 joinColumns = @JoinColumn(name = "user_id")),
 @AssociationOverride(name = "primaryKey.clan", joinColumns = @JoinColumn(name
 = "clan_id")) })
public class ClanMembers implements Serializable {

	@EmbeddedId
	private UserClanId primaryKey = new UserClanId();

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_type_id")
	private MemberType memberType;

	@Column(name = "create_date")
	private Date createDate;
	@Column(name = "update_date")
	private Date updateDate;
	@Column(name = "active")
	private Integer active;

	public UserClanId getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(UserClanId primaryKey) {
		this.primaryKey = primaryKey;
	}

	public MemberType getMemberType() {
		return memberType;
	}

	public void setMemberType(MemberType memberType) {
		this.memberType = memberType;
	}

	 @Transient
	 public User getUser() {
	 return this.getPrimaryKey().getUser();
	 }
	
	 public void setUser(User user) {
	 this.getPrimaryKey().setUser(user);
	 }
	
	 @Transient
	 public Clan getClan() {
	 return this.getPrimaryKey().getClan();
	 }
	
	 public void setClan(Clan clan) {
	 this.getPrimaryKey().setClan(clan);
	 }

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

}
