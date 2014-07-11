package com.thefieldpanama.webservices.objects;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.thefieldpanama.utilities.JSONDateSerializer;

/**
 * Objeto enviado a traves de los servicios web para la entidad Calendario
 * 
 * @author rospena
 * 
 */
public class CalendarioWS implements Serializable {

	private static final long serialVersionUID = 8733449483821973099L;
	private Date fecha;
	private String hora;
	private String equipo1;
	private String equipo2;
	private String lugar;
	private String categoria;
	private String liga;
	private int idPartido;

	@JsonSerialize(using=JSONDateSerializer.class) 
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getEquipo1() {
		return equipo1;
	}

	public void setEquipo1(String equipo1) {
		this.equipo1 = equipo1;
	}

	public String getEquipo2() {
		return equipo2;
	}

	public void setEquipo2(String equipo2) {
		this.equipo2 = equipo2;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
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

	public int getIdPartido() {
		return idPartido;
	}

	public void setIdPartido(int idPartido) {
		this.idPartido = idPartido;
	}
}
