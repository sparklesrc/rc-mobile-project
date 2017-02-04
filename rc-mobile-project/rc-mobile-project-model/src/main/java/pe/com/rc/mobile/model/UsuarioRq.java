package pe.com.rc.mobile.model;

public class UsuarioRq {

	private String idUsuario;
	private String nombre;
	private String tokenUser;
	private String lastSession;

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTokenUser() {
		return tokenUser;
	}

	public void setTokenUser(String tokenUser) {
		this.tokenUser = tokenUser;
	}

	public String getLastSession() {
		return lastSession;
	}

	public void setLastSession(String lastSession) {
		this.lastSession = lastSession;
	}

}
