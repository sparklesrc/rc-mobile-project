package pe.com.rc.mobile.model.clan;

public class MMRSearch {

	public static class MMRBuildRequest {
		private Long mmrId;
		private Long clanAId;
		private Long clanBId;
		private Long userCreateId;
		private Long userAcceptId;
		private String tempDate;
		private String approvedDate;
		private String ipServ;
		private String description;
		private String phone;
		private String mail;
		private Integer hours;

		public Long getClanAId() {
			return clanAId;
		}

		public void setClanAId(Long clanAId) {
			this.clanAId = clanAId;
		}

		public Long getClanBId() {
			return clanBId;
		}

		public void setClanBId(Long clanBId) {
			this.clanBId = clanBId;
		}

		public Long getUserCreateId() {
			return userCreateId;
		}

		public void setUserCreateId(Long userCreateId) {
			this.userCreateId = userCreateId;
		}

		public Long getUserAcceptId() {
			return userAcceptId;
		}

		public void setUserAcceptId(Long userAcceptId) {
			this.userAcceptId = userAcceptId;
		}

		public String getTempDate() {
			return tempDate;
		}

		public void setTempDate(String tempDate) {
			this.tempDate = tempDate;
		}

		public String getApprovedDate() {
			return approvedDate;
		}

		public void setApprovedDate(String approvedDate) {
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

		public Long getMmrId() {
			return mmrId;
		}

		public void setMmrId(Long mmrId) {
			this.mmrId = mmrId;
		}

		public Integer getHours() {
			return hours;
		}

		public void setHours(Integer hours) {
			this.hours = hours;
		}

	}

	public static class MMRSearchRequest {
		private String byDay;
		private String byHour;

	}

	public static class MMRSearchResponse {
		private Long mmrId;
		private String tempDate;
		private String description;
		private String clanNameWhoCreates;
		private String userNameWhoCreate;
		private String game;
		private String ipServ;
		private String phone;
		private String mail;

		public Long getMmrId() {
			return mmrId;
		}

		public void setMmrId(Long mmrId) {
			this.mmrId = mmrId;
		}

		public String getTempDate() {
			return tempDate;
		}

		public void setTempDate(String tempDate) {
			this.tempDate = tempDate;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getClanNameWhoCreates() {
			return clanNameWhoCreates;
		}

		public void setClanNameWhoCreates(String clanNameWhoCreates) {
			this.clanNameWhoCreates = clanNameWhoCreates;
		}

		public String getUserNameWhoCreate() {
			return userNameWhoCreate;
		}

		public void setUserNameWhoCreate(String userNameWhoCreate) {
			this.userNameWhoCreate = userNameWhoCreate;
		}

		public String getGame() {
			return game;
		}

		public void setGame(String game) {
			this.game = game;
		}

		public String getIpServ() {
			return ipServ;
		}

		public void setIpServ(String ipServ) {
			this.ipServ = ipServ;
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

	}

	public static class MMRCancelRequest {
		private Long userId;
		private Long mmrId;

		public Long getUserId() {
			return userId;
		}

		public void setUserId(Long userId) {
			this.userId = userId;
		}

		public Long getMmrId() {
			return mmrId;
		}

		public void setMmrId(Long mmrId) {
			this.mmrId = mmrId;
		}

	}
}
