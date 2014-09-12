package com.thefieldpanama.beans;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "grupos")
public class Grupos {
	private int id_grupo;
	private String nombre;
//	private Integer formula_calculo;
	private int id_categoria;
	private Set<Equipo> equipos = new HashSet<Equipo>();
	private FormulasCalculo formula;

	@Id
	@Column(name = "id_grupo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId_grupo() {
		return id_grupo;
	}

	public void setId_grupo(Integer id_grupo) {
		this.id_grupo = id_grupo;
	}

	@Column(name = "nombre")
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

//	@Column(name = "formula_calculo")
//	public int getFormula_calculo() {
//		return formula_calculo;
//	}
//
//	public void setFormula_calculo(int formula_calculo) {
//		this.formula_calculo = formula_calculo;
//	}

	@Column(name = "id_categoria")
	public int getId_categoria() {
		return id_categoria;
	}

	public void setId_categoria(int id_categoria) {
		this.id_categoria = id_categoria;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "grupo")
	public Set<Equipo> getEquipos() {
		return equipos;
	}

	public void setEquipos(Set<Equipo> equipos) {
		this.equipos = equipos;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "FORMULA_CALCULO", nullable = false)
	public FormulasCalculo getFormula() {
		return formula;
	}

	public void setFormula(FormulasCalculo formula) {
		this.formula = formula;
	}
}
