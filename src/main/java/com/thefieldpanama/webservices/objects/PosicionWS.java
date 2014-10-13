package com.thefieldpanama.webservices.objects;

import java.io.Serializable;

public class PosicionWS implements Serializable, Comparable<PosicionWS> {
	private static final long serialVersionUID = 5406280170112542483L;
	private String nombreEquipo;
	private String nombreGrupo;
	private int cantJG;
	private int cantJP;
	private int cantJE;
	private int cantPts;
	private int cantJuegos;
	private int idCategoria;
	
	//Campos para regla de diferencia de goles
	private int gFavor;
	private int gContra;
	private int diferencia;

	public String getNombreEquipo() {
		return nombreEquipo;
	}

	public void setNombreEquipo(String nombreEquipo) {
		this.nombreEquipo = nombreEquipo;
	}

	public int getCantJG() {
		return cantJG;
	}

	public void setCantJG(int cantJG) {
		this.cantJG = cantJG;
	}

	public int getCantJP() {
		return cantJP;
	}

	public void setCantJP(int cantJP) {
		this.cantJP = cantJP;
	}

	public int getCantJE() {
		return cantJE;
	}

	public void setCantJE(int cantJE) {
		this.cantJE = cantJE;
	}

	public int getCantPts() {
		return cantPts;
	}

	public void setCantPts(int cantPts) {
		this.cantPts = cantPts;
	}

	public int getCantJuegos() {
		return cantJuegos;
	}

	public void setCantJuegos(int cantJuegos) {
		this.cantJuegos = cantJuegos;
	}

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

	@Override
	public int compareTo(PosicionWS o) {
		int tablePosition = ((PosicionWS)o).getCantPts();
        return tablePosition - this.cantPts;
	}

	public int getgFavor() {
		return gFavor;
	}

	public void setgFavor(int gFavor) {
		this.gFavor = gFavor;
	}

	public int getgContra() {
		return gContra;
	}

	public void setgContra(int gContra) {
		this.gContra = gContra;
	}

	public int getDiferencia() {
		return diferencia;
	}

	public void setDiferencia(int diferencia) {
		this.diferencia = diferencia;
	}
}
