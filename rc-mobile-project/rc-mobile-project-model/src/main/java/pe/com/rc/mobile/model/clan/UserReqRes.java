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
}
