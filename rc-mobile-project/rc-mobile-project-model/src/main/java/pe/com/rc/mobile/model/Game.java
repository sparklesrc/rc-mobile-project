package pe.com.rc.mobile.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "game")
public class Game extends Record implements Serializable {

	@Column(name = "name")
	private String name;
	@Column(name = "steam_app")
	private Integer steamApp;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSteamApp() {
		return steamApp;
	}

	public void setSteamApp(Integer steamApp) {
		this.steamApp = steamApp;
	}

}
