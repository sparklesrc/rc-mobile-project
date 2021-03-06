package pe.com.rc.mobile.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "member_type")
public class MemberType extends Record implements Serializable {

	@Column(name = "description")
	private String description;

	public MemberType() {

	}

	public MemberType(Long id) {
		this.setId(id);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
