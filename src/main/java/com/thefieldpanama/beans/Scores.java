package com.thefieldpanama.beans;

public class Scores {
	private String equipo1;
	private String equipo2;
	private Long pts1;
	private Long pts2;
	private String status;
	private String hora;
	private String categoria;

	public Scores(String equipo1, String equipo2, Long pts1, Long pts2, String status, String hora, String categoria) {
		this.equipo1 = equipo1;
		this.equipo2 = equipo2;
		this.pts1 = pts1;
		this.pts2 = pts2;
		this.status = status;
		this.hora = hora;
		this.categoria = categoria;
	}

	public Scores() {
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

	public Long getPts1() {
		return pts1;
	}

	public void setPts1(Long pts1) {
		this.pts1 = pts1;
	}

	public Long getPts2() {
		return pts2;
	}

	public void setPts2(Long pts2) {
		this.pts2 = pts2;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
}
