package pe.com.rc.mobile.model;

import java.util.List;

public class Preferencia {

	private List<TipoLugar> tipoLugar;
	private List<MusicaLugar> musicaLugar;

	public List<TipoLugar> getTipoLugar() {
		return tipoLugar;
	}

	public void setTipoLugar(List<TipoLugar> tipoLugar) {
		this.tipoLugar = tipoLugar;
	}

	public List<MusicaLugar> getMusicaLugar() {
		return musicaLugar;
	}

	public void setMusicaLugar(List<MusicaLugar> musicaLugar) {
		this.musicaLugar = musicaLugar;
	}

}
