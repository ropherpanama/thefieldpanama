package com.thefieldpanama.webservices.objects;

import java.io.Serializable;

/**
 * Objeto enviado a traves del servicio web para la entidad Equipos
 * 
 * @author rospena
 * 
 */
public class EquiposWS implements Serializable {
	private static final long serialVersionUID = -1126316627175573996L;
	private int id;
	private String nombre;
	private String categoria;
	private String liga;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getLiga() {
		return liga;
	}

	public void setLiga(String liga) {
		this.liga = liga;
	}
}
