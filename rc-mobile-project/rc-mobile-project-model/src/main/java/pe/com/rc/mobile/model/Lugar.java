package pe.com.rc.mobile.model;

import java.util.List;

public class Lugar {

	private String id;
	private String razonSocial;
	private String ruc;
	private String telefono;
	private String direccion;
	private String representanteLegal;
	private String email;
	private String paginaWeb;
	private MacroPlace macroPlace;
	private List<TipoLugar> tipoLugar;
	private Ubicacion ubicacion;
	private List<Amistad> amistad;
	private Integer cantEstrellas;
	private List<MusicaLugar> musicaLugar;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getRepresentanteLegal() {
		return representanteLegal;
	}

	public void setRepresentanteLegal(String representanteLegal) {
		this.representanteLegal = representanteLegal;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPaginaWeb() {
		return paginaWeb;
	}

	public void setPaginaWeb(String paginaWeb) {
		this.paginaWeb = paginaWeb;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	public List<TipoLugar> getTipoLugar() {
		return tipoLugar;
	}

	public void setTipoLugar(List<TipoLugar> tipoLugar) {
		this.tipoLugar = tipoLugar;
	}

	public List<Amistad> getAmistad() {
		return amistad;
	}

	public void setAmistad(List<Amistad> amistad) {
		this.amistad = amistad;
	}

	public MacroPlace getMacroPlace() {
		return macroPlace;
	}

	public void setMacroPlace(MacroPlace macroPlace) {
		this.macroPlace = macroPlace;
	}

	public Integer getCantEstrellas() {
		return cantEstrellas;
	}

	public void setCantEstrellas(Integer cantEstrellas) {
		this.cantEstrellas = cantEstrellas;
	}

	public List<MusicaLugar> getMusicaLugar() {
		return musicaLugar;
	}

	public void setMusicaLugar(List<MusicaLugar> musicaLugar) {
		this.musicaLugar = musicaLugar;
	}

}
