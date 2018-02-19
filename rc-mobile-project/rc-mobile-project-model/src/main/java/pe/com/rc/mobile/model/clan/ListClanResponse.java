package pe.com.rc.mobile.model.clan;

public class ListClanResponse {

	private String name;
	private String description;
	private Integer starsNum;
	private String game;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getStarsNum() {
		return starsNum;
	}

	public void setStarsNum(Integer starsNum) {
		this.starsNum = starsNum;
	}

	public String getGame() {
		return game;
	}

	public void setGame(String game) {
		this.game = game;
	}

}
