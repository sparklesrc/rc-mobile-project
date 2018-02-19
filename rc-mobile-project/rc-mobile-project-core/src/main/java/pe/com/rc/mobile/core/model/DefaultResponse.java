package pe.com.rc.mobile.core.model;

public class DefaultResponse {

	private Integer codeResponse;
	private String messageResponse;

	public DefaultResponse(Integer code, String msg) {
		this.codeResponse = code;
		this.messageResponse = msg;
	}

	public Integer getCodeResponse() {
		return codeResponse;
	}

	public void setCodeResponse(Integer codeResponse) {
		this.codeResponse = codeResponse;
	}

	public String getMessageResponse() {
		return messageResponse;
	}

	public void setMessageResponse(String messageResponse) {
		this.messageResponse = messageResponse;
	}

}
