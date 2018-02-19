package pe.com.rc.mobile.model.clan;

public class ClanMembersResponse {

	private String steamName;
	private String steamId;
	private String steamAvatar;
	private String memberType;
	private String clanName;

	public String getSteamName() {
		return steamName;
	}

	public void setSteamName(String steamName) {
		this.steamName = steamName;
	}

	public String getSteamId() {
		return steamId;
	}

	public void setSteamId(String steamId) {
		this.steamId = steamId;
	}

	public String getSteamAvatar() {
		return steamAvatar;
	}

	public void setSteamAvatar(String steamAvatar) {
		this.steamAvatar = steamAvatar;
	}

	public String getMemberType() {
		return memberType;
	}

	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}

	public String getClanName() {
		return clanName;
	}

	public void setClanName(String clanName) {
		this.clanName = clanName;
	}

}
