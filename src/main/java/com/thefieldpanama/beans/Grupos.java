package com.thefieldpanama.beans;

import java.io.Serializable;
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
import javax.persistence.Transient;

@Entity
@Table(name = "grupos")
public class Grupos implements Serializable{
	private static final long serialVersionUID = -7594983741145877810L;
	private int id_grupo;
	private String nombre;
	private int id_categoria;
	private Set<Equipo> equipos = new HashSet<Equipo>(0);
	private FormulasCalculo formula;
	private String nomCategoria;

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

	@Transient 
	public String getNomCategoria() {
		return nomCategoria;
	}

	public void setNomCategoria(String nomCategoria) {
		this.nomCategoria = nomCategoria;
	}
}
