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
@Table(name = "equipos")
public class Equipo {
	private Integer id_equipo;
	private String nom_equipo;
	private Categoria categoria;
	private String localidad;
	//Un equipo tiene varios partidos (One to Many)
	private Set<Partido> partidos = new HashSet<Partido>();

	@Id
	@Column(name = "ID_EQUIPO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId_equipo() {
		return id_equipo;
	}

	public void setId_equipo(Integer id_equipo) {
		this.id_equipo = id_equipo;
	}

	@Column(name = "NOM_EQUIPO")
	public String getNom_equipo() {
		return nom_equipo;
	}

	public void setNom_equipo(String nom_equipo) {
		this.nom_equipo = nom_equipo;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_CATEGORIA", nullable = false)
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Column(name = "ORIGEN")
	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "equipo1")
	public Set<Partido> getPartidos() {
		return partidos;
	}

	public void setPartidos(Set<Partido> partidos) {
		this.partidos = partidos;
	}
}
