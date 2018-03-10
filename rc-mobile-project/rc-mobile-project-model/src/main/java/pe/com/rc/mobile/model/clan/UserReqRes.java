package pe.com.rc.mobile.model.clan;

public class UserReqRes {

	public static class AcceptClanRequest {
		private Long clanId;
		private Long userId;
		private Long solicitudeId;
		private Long state;
		private String description;

		public Long getClanId() {
			return clanId;
		}

		public void setClanId(Long clanId) {
			this.clanId = clanId;
		}

		public Long getUserId() {
			return userId;
		}

		public void setUserId(Long userId) {
			this.userId = userId;
		}

		public Long getSolicitudeId() {
			return solicitudeId;
		}

		public void setSolicitudeId(Long solicitudeId) {
			this.solicitudeId = solicitudeId;
		}

		public Long getState() {
			return state;
		}

		public void setState(Long state) {
			this.state = state;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

	}

	public static class InvitationsToTeamRequest {
		private Long userId;
		private Long gameId;
		private Long solicitudeType;

		public Long getUserId() {
			return userId;
		}

		public void setUserId(Long userId) {
			this.userId = userId;
		}

		public Long getGameId() {
			return gameId;
		}

		public void setGameId(Long gameId) {
			this.gameId = gameId;
		}

		public Long getSolicitudeType() {
			return solicitudeType;
		}

		public void setSolicitudeType(Long solicitudeType) {
			this.solicitudeType = solicitudeType;
		}

	}

	public static class InvitationsToTeamResponse {
		private Long solicitudeId;
		private String clanName;
		private String gameName;
		private String tipoSolicitud;
		private String since;
		private String state;

		public Long getSolicitudeId() {
			return solicitudeId;
		}

		public void setSolicitudeId(Long solicitudeId) {
			this.solicitudeId = solicitudeId;
		}

		public String getClanName() {
			return clanName;
		}

		public void setClanName(String clanName) {
			this.clanName = clanName;
		}

		public String getGameName() {
			return gameName;
		}

		public void setGameName(String gameName) {
			this.gameName = gameName;
		}

		public String getTipoSolicitud() {
			return tipoSolicitud;
		}

		public void setTipoSolicitud(String tipoSolicitud) {
			this.tipoSolicitud = tipoSolicitud;
		}

		public String getSince() {
			return since;
		}

		public void setSince(String since) {
			this.since = since;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

	}

	public static class UserByMailReq {
		private String mail;

		public String getMail() {
			return mail;
		}

		public void setMail(String mail) {
			this.mail = mail;
		}

	}

	public static class UserByMailResp {
		private Long userId;
		private String steamId;
		private String mail;
		private String password;
		private String rol;
		private boolean isUserSyncWithSteam;

		public String getSteamId() {
			return steamId;
		}

		public void setSteamId(String steamId) {
			this.steamId = steamId;
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

		public String getRol() {
			return rol;
		}

		public void setRol(String rol) {
			this.rol = rol;
		}

		public boolean isUserSyncWithSteam() {
			return isUserSyncWithSteam;
		}

		public void setUserSyncWithSteam(boolean isUserSyncWithSteam) {
			this.isUserSyncWithSteam = isUserSyncWithSteam;
		}

		public Long getUserId() {
			return userId;
		}

		public void setUserId(Long userId) {
			this.userId = userId;
		}

	}

	public static class SyncSteamUser {
		private Long userId;
		private String steamId;

		public String getSteamId() {
			return steamId;
		}

		public void setSteamId(String steamId) {
			this.steamId = steamId;
		}

		public Long getUserId() {
			return userId;
		}

		public void setUserId(Long userId) {
			this.userId = userId;
		}

	}
}
