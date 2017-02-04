package pe.com.rc.mobile.model;

public class Comentario {

	private String id;
	private Usuario usuario;
	private Lugar lugar;
	private String comentario;
	private Integer cantEstrellas;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Integer getCantEstrellas() {
		return cantEstrellas;
	}

	public void setCantEstrellas(Integer cantEstrellas) {
		this.cantEstrellas = cantEstrellas;
	}

	public Lugar getLugar() {
		return lugar;
	}

	public void setLugar(Lugar lugar) {
		this.lugar = lugar;
	}

}
