package com.thefieldpanama.beans;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "formulas_calculo")
public class FormulasCalculo {

	private int id_formula_calculo;
	private int jg;
	private int jp;
	private int je;
	
	Set<Grupos> grupos = new HashSet<Grupos>();

	@Id
	@Column(name = "id_formula_calculo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId_formula_calculo() {
		return id_formula_calculo;
	}

	public void setId_formula_calculo(int id_formula_calculo) {
		this.id_formula_calculo = id_formula_calculo;
	}

	@Column(name = "JG")
	public int getJg() {
		return jg;
	}

	public void setJg(int jg) {
		this.jg = jg;
	}

	@Column(name = "JP")
	public int getJp() {
		return jp;
	}

	public void setJp(int jp) {
		this.jp = jp;
	}

	@Column(name = "JE")
	public int getJe() {
		return je;
	}

	public void setJe(int je) {
		this.je = je;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "formula")
	public Set<Grupos> getGrupos() {
		return grupos;
	}

	public void setGrupos(Set<Grupos> grupos) {
		this.grupos = grupos;
	}
}
