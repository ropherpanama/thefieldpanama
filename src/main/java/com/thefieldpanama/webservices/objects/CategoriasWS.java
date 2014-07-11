package com.thefieldpanama.webservices.objects;

import java.io.Serializable;

/**
 * Objeto enviado a traves del servicio web para la entidad Categorias
 * @author rospena
 *
 */
public class CategoriasWS implements Serializable{
	
	private static final long serialVersionUID = 970723920206732017L;
	
	private int id;
	private String liga;
	private String nombre;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLiga() {
		return liga;
	}

	public void setLiga(String liga) {
		this.liga = liga;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
