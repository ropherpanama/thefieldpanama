package com.thefieldpanama.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PARTIDOS")
public class Partido {
	private Integer id_partido;
	private Equipo equipo1;
	private Equipo equipo2;
	private Date fecha;
	private String hora;
	private String lugar;

	@Id
	@Column(name = "ID_PARTIDO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId_partido() {
		return id_partido;
	}

	public void setId_partido(Integer id_partido) {
		this.id_partido = id_partido;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_EQUIPO_1", nullable = false)
	public Equipo getEquipo1() {
		return equipo1;
	}

	public void setEquipo1(Equipo equipo1) {
		this.equipo1 = equipo1;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_EQUIPO_2", nullable = false)
	public Equipo getEquipo2() {
		return equipo2;
	}

	public void setEquipo2(Equipo equipo2) {
		this.equipo2 = equipo2;
	}

	@Column(name = "FECHA")
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Column(name = "HORA")
	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	@Column(name = "LUGAR")
	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
}
