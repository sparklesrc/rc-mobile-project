package pe.com.rc.mobile.model.clan;

import java.util.List;

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
		private String pais;
		private Integer edad;
		private boolean isUserSyncWithSteam;
		private List<UserTeams> userTeams;

		public Integer getEdad() {
			return edad;
		}

		public void setEdad(Integer edad) {
			this.edad = edad;
		}

		public Long getUserId() {
			return userId;
		}

		public void setUserId(Long userId) {
			this.userId = userId;
		}

		public boolean isUserSyncWithSteam() {
			return isUserSyncWithSteam;
		}

		public void setUserSyncWithSteam(boolean isUserSyncWithSteam) {
			this.isUserSyncWithSteam = isUserSyncWithSteam;
		}

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

		public List<UserTeams> getUserTeams() {
			return userTeams;
		}

		public void setUserTeams(List<UserTeams> userTeams) {
			this.userTeams = userTeams;
		}

		public String getPais() {
			return pais;
		}

		public void setPais(String pais) {
			this.pais = pais;
		}

	}

	public static class UserTeams {
		private Long gameId;
		private Long teamId;
		private Long memberTypeId;

		public Long getGameId() {
			return gameId;
		}

		public void setGameId(Long gameId) {
			this.gameId = gameId;
		}

		public Long getTeamId() {
			return teamId;
		}

		public void setTeamId(Long teamId) {
			this.teamId = teamId;
		}

		public Long getMemberTypeId() {
			return memberTypeId;
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

	public static class SignUpRequest {
		private String email;
		private String password;
		private Integer edad;
		private String pais;
		private SignUpGameProfile gameProfile;

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
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

		public SignUpGameProfile getGameProfile() {
			return gameProfile;
		}

		public void setGameProfile(SignUpGameProfile gameProfile) {
			this.gameProfile = gameProfile;
		}

	}

	public static class SignUpGameProfile {
		// extra values
		private Integer gameId;
		private String nickname;
		private String celular;
		private String description;
		private String[] roles;
		private Long userId;

		public Long getUserId() {
			return userId;
		}

		public void setUserId(Long userId) {
			this.userId = userId;
		}

		public Integer getGameId() {
			return gameId;
		}

		public void setGameId(Integer gameId) {
			this.gameId = gameId;
		}

		public String getNickname() {
			return nickname;
		}

		public void setNickname(String nickname) {
			this.nickname = nickname;
		}

		public String[] getRoles() {
			return roles;
		}

		public void setRoles(String[] roles) {
			this.roles = roles;
		}

		public String getCelular() {
			return celular;
		}

		public void setCelular(String celular) {
			this.celular = celular;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

	}

	public static class GenericResponse {
		private String msg;

		public GenericResponse(String msg) {
			this.msg = msg;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}
	}

	public static class SignUpCode {
		private String mail;
		private String code;

		public String getMail() {
			return mail;
		}

		public void setMail(String mail) {
			this.mail = mail;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

	}

	public static class UserGame {
		private Long userId;
		private Long gameId;

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

	}
}
