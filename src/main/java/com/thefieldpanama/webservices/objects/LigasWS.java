package com.thefieldpanama.webservices.objects;

import java.io.Serializable;

/**
 * Objeto que se envia a traves de los servicios web para la entidad Ligas
 * 
 * @author rospena
 * 
 */
public class LigasWS implements Serializable {

	private static final long serialVersionUID = -6938278093241661990L;
	private String nombre;
	private int id;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
