package pe.com.rc.mobile.model.clan;

import java.util.List;

public class TeamSearch {

	public static class TeamSearchResponse {
		private Long clanId;
		private Long gameId;
		private String clanName;
		private String gameName;
		List<TeamMembers> members;

		public Long getClanId() {
			return clanId;
		}

		public void setClanId(Long clanId) {
			this.clanId = clanId;
		}

		public Long getGameId() {
			return gameId;
		}

		public void setGameId(Long gameId) {
			this.gameId = gameId;
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

		public List<TeamMembers> getMembers() {
			return members;
		}

		public void setMembers(List<TeamMembers> members) {
			this.members = members;
		}

	}

	public static class TeamMembers {
		private Long userId;
		private String name;
		private String type;

		public Long getUserId() {
			return userId;
		}

		public void setUserId(Long userId) {
			this.userId = userId;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

	}

	public static class TeamSearchRequest {
		private Integer gameId;
		private String criteria;

		public Integer getGameId() {
			return gameId;
		}

		public void setGameId(Integer gameId) {
			this.gameId = gameId;
		}

		public String getCriteria() {
			return criteria;
		}

		public void setCriteria(String criteria) {
			this.criteria = criteria;
		}
	}

	public static class TeamBuildRequest {
		private Long gameId;
		private Long userId;
		private String nombre;
		private String abreviatura;
		private String descripcion;
		private String imgUrl;
		private String pais;

		public Long getGameId() {
			return gameId;
		}

		public void setGameId(Long gameId) {
			this.gameId = gameId;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public String getAbreviatura() {
			return abreviatura;
		}

		public void setAbreviatura(String abreviatura) {
			this.abreviatura = abreviatura;
		}

		public String getDescripcion() {
			return descripcion;
		}

		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}

		public String getImgUrl() {
			return imgUrl;
		}

		public void setImgUrl(String imgUrl) {
			this.imgUrl = imgUrl;
		}

		public String getPais() {
			return pais;
		}

		public void setPais(String pais) {
			this.pais = pais;
		}

		public Long getUserId() {
			return userId;
		}

		public void setUserId(Long userId) {
			this.userId = userId;
		}

	}

	public static class TeamDeleteRequest {
		private Long userId;
		private Long clanId;

		public Long getUserId() {
			return userId;
		}

		public void setUserId(Long userId) {
			this.userId = userId;
		}

		public Long getClanId() {
			return clanId;
		}

		public void setClanId(Long clanId) {
			this.clanId = clanId;
		}

	}

	public static class RecruitRequest {
		private Long userId;
		private Long clanId;
		private String description;

		public Long getUserId() {
			return userId;
		}

		public void setUserId(Long userId) {
			this.userId = userId;
		}

		public Long getClanId() {
			return clanId;
		}

		public void setClanId(Long clanId) {
			this.clanId = clanId;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

	}

	public static class AcceptMemberRequest {
		private Long clanId;
		private Long userId;
		private Long solicitudeId;
		private Long state;
		private String description;

		public Long getSolicitudeId() {
			return solicitudeId;
		}

		public void setSolicitudeId(Long solicitudeId) {
			this.solicitudeId = solicitudeId;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public Long getState() {
			return state;
		}

		public void setState(Long state) {
			this.state = state;
		}

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

	}

	public static class CandidatesRequest {
		private Long clanId;

		public Long getClanId() {
			return clanId;
		}

		public void setClanId(Long clanId) {
			this.clanId = clanId;
		}

	}

	public static class CandidatesResponse {
		private Long userId;
		private Long clanId;
		private String clanName;
		private String userName;

		public Long getUserId() {
			return userId;
		}

		public void setUserId(Long userId) {
			this.userId = userId;
		}

		public Long getClanId() {
			return clanId;
		}

		public void setClanId(Long clanId) {
			this.clanId = clanId;
		}

		public String getClanName() {
			return clanName;
		}

		public void setClanName(String clanName) {
			this.clanName = clanName;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

	}

	public static class DropMemberRequest {
		private Long leaderId;
		private Long memberId;
		private Long clanId;

		public Long getClanId() {
			return clanId;
		}

		public void setClanId(Long clanId) {
			this.clanId = clanId;
		}

		public Long getLeaderId() {
			return leaderId;
		}

		public void setLeaderId(Long leaderId) {
			this.leaderId = leaderId;
		}

		public Long getMemberId() {
			return memberId;
		}

		public void setMemberId(Long memberId) {
			this.memberId = memberId;
		}

	}

	public static class PostularRequest {
		private Long userId;
		private Long clanId;
		private String description;

		public Long getUserId() {
			return userId;
		}

		public void setUserId(Long userId) {
			this.userId = userId;
		}

		public Long getClanId() {
			return clanId;
		}

		public void setClanId(Long clanId) {
			this.clanId = clanId;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

	}

	public static class RankTeamRequest {
		private Long userId;
		private Long clanToRank;
		private Integer numStars;
		private String description;

		public Long getUserId() {
			return userId;
		}

		public void setUserId(Long userId) {
			this.userId = userId;
		}

		public Integer getNumStars() {
			return numStars;
		}

		public void setNumStars(Integer numStars) {
			this.numStars = numStars;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public Long getClanToRank() {
			return clanToRank;
		}

		public void setClanToRank(Long clanToRank) {
			this.clanToRank = clanToRank;
		}

	}

	public static class AssignRoleRequest {
		private Long leaderId;
		private Long memberId;
		private Long clanId;
		private Long newRolId;

		public Long getLeaderId() {
			return leaderId;
		}

		public void setLeaderId(Long leaderId) {
			this.leaderId = leaderId;
		}

		public Long getMemberId() {
			return memberId;
		}

		public void setMemberId(Long memberId) {
			this.memberId = memberId;
		}

		public Long getClanId() {
			return clanId;
		}

		public void setClanId(Long clanId) {
			this.clanId = clanId;
		}

		public Long getNewRolId() {
			return newRolId;
		}

		public void setNewRolId(Long newRolId) {
			this.newRolId = newRolId;
		}

	}

	public static class SearchRecruit {
		private Integer gameId;
		private String nickName;
		private String email;
		private Integer edad;
		private Integer estado;
		private String pais;
		private String[] rol;

		public String getNickName() {
			return nickName;
		}

		public void setNickName(String nickName) {
			this.nickName = nickName;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
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

		public String[] getRol() {
			return rol;
		}

		public void setRol(String[] rol) {
			this.rol = rol;
		}

		public Integer getGameId() {
			return gameId;
		}

		public void setGameId(Integer gameId) {
			this.gameId = gameId;
		}

		public Integer getEstado() {
			return estado;
		}

		public void setEstado(Integer estado) {
			this.estado = estado;
		}

	}

	public static class SearchRecruitResult {
		private Integer id;
		private String nickName;
		private String mail;
		private Integer edad;
		private String pais;
		private String roles;
		private Integer gameId;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getNickName() {
			return nickName;
		}

		public void setNickName(String nickName) {
			this.nickName = nickName;
		}

		public String getMail() {
			return mail;
		}

		public void setMail(String mail) {
			this.mail = mail;
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

		public String getRoles() {
			return roles;
		}

		public void setRoles(String roles) {
			this.roles = roles;
		}

		public Integer getGameId() {
			return gameId;
		}

		public void setGameId(Integer gameId) {
			this.gameId = gameId;
		}

	}
}
