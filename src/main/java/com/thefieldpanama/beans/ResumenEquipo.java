package com.thefieldpanama.beans;

public class ResumenEquipo {
	private String nombreEquipo;
	private int posicion;
	private int pts1;
	private int pts2;
	private int idPartido;
	private int idCategoria;
	
	public ResumenEquipo(String nombreEquipo, int posicion, int pts1, int pts2, int idPartido) {
		this.nombreEquipo = nombreEquipo;
		this.posicion = posicion;
		this.pts1 = pts1;
		this.pts2 = pts2;
		this.idPartido = idPartido;
	}
	
	public int getIdPartido() {
		return idPartido;
	}

	public void setIdPartido(int idPartido) {
		this.idPartido = idPartido;
	}

	public ResumenEquipo() {
	}
	
	public String getNombreEquipo() {
		return nombreEquipo;
	}

	public void setNombreEquipo(String nombreEquipo) {
		this.nombreEquipo = nombreEquipo;
	}

	public int getPosicion() {
		return posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

	public int getPts1() {
		return pts1;
	}

	public void setPts1(int pts1) {
		this.pts1 = pts1;
	}

	public int getPts2() {
		return pts2;
	}

	public void setPts2(int pts2) {
		this.pts2 = pts2;
	}
	
	@Override
	public String toString() {
		return getNombreEquipo() + " - " + getPosicion() + " - " + getPts1() + " - " + getPts2() + " - " + getIdPartido();
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}
}
