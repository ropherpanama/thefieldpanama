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

@Entity
@Table(name = "categorias")
public class Categoria implements Serializable{
	private static final long serialVersionUID = -2225788362906294841L;
	private Integer id_categoria;
	private String nom_categoria;
	//Relacion ManyToOne = Varias Categorias solo pertenecen a una Liga
	private Liga liga;
	
	private Set<Equipo> equiposCategoria = new HashSet<Equipo>(0);
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "categoria")
	public Set<Equipo> getEquiposCategoria() {
		return equiposCategoria;
	}

	public void setEquiposCategoria(Set<Equipo> equiposCategoria) {
		this.equiposCategoria = equiposCategoria;
	}

	@Id
	@Column(name = "ID_CATEGORIA")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId_categoria() {
		return id_categoria;
	}

	public void setId_categoria(Integer id_categoria) {
		this.id_categoria = id_categoria;
	}

	@Column(name = "NOM_CATEGORIA")
	public String getNom_categoria() {
		return nom_categoria;
	}

	public void setNom_categoria(String nom_categoria) {
		this.nom_categoria = nom_categoria;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_LIGA", nullable = false)
	public Liga getLiga() {
		return liga;
	}

	public void setLiga(Liga liga) {
		this.liga = liga;
	}
}
