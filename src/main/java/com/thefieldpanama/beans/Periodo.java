package com.thefieldpanama.beans;

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
@Table(name = "periodos")
public class Periodo {
	private Integer id_periodo;
	private int pts_equipo_1;
	private int pts_equipo_2;
	private Partido partido;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_PARTIDO", nullable = false)
	public Partido getPartido() {
		return partido;
	}

	public void setPartido(Partido partido) {
		this.partido = partido;
	}

	@Id
	@Column(name = "ID_PERIODO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId_periodo() {
		return id_periodo;
	}

	public void setId_periodo(Integer id_periodo) {
		this.id_periodo = id_periodo;
	}

	@Column(name = "PTS_EQUIPO_1")
	public int getPts_equipo_1() {
		return pts_equipo_1;
	}

	public void setPts_equipo_1(int pts_equipo_1) {
		this.pts_equipo_1 = pts_equipo_1;
	}

	@Column(name = "PTS_EQUIPO_2")
	public int getPts_equipo_2() {
		return pts_equipo_2;
	}

	public void setPts_equipo_2(int pts_equipo_2) {
		this.pts_equipo_2 = pts_equipo_2;
	}
}
