package pe.com.rc.mobile.model;

import java.util.List;

/**
 * Clase que representa la solicitud de busqueda predeterminada. Ubicacion de la
 * persona que utiliza la app. Preferencia, gustos de la persona rescatada desde
 * FB o BD. Amistad, lista de amistades de FB.
 * 
 * @author framirez
 * @since 26/09/2016
 *
 */
public class LugarRq {

	private Ubicacion ubicacion;
	private List<Preferencia> preferencia;
	private List<Amistad> amistad;
	private String idUsuario;

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	public List<Preferencia> getPreferencia() {
		return preferencia;
	}

	public void setPreferencia(List<Preferencia> preferencia) {
		this.preferencia = preferencia;
	}

	public List<Amistad> getAmistad() {
		return amistad;
	}

	public void setAmistad(List<Amistad> amistad) {
		this.amistad = amistad;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

}
