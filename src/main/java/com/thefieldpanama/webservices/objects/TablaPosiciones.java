package com.thefieldpanama.webservices.objects;

import java.io.Serializable;
import java.util.List;

public class TablaPosiciones implements Serializable, Comparable<TablaPosiciones>{
	private static final long serialVersionUID = -4919441633255596977L;
	private int idCategoria;
	private String nombreGrupo;
	private List<PosicionWS> posiciones;

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNombreGrupo() {
		return nombreGrupo;
	}

	public void setNombreGrupo(String nombreGrupo) {
		this.nombreGrupo = nombreGrupo;
	}

	public List<PosicionWS> getPosiciones() {
		if(posiciones != null){
			
		}
		
		return posiciones;
	}

	public void setPosiciones(List<PosicionWS> posiciones) {
		this.posiciones = posiciones;
	}

	@Override
	public int compareTo(TablaPosiciones o) {
		return String.CASE_INSENSITIVE_ORDER.compare(this.getNombreGrupo(), o.getNombreGrupo());
	}
}
