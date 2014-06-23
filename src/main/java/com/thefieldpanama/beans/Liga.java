package com.thefieldpanama.beans;
/**
 * Author: Rosendo Peña Hernandez
 * ropherpanama@gmail.com
 * Date: 23/06/2014
 */
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
@Table(name = "LIGAS")
public class Liga {

	private int id;
	private String nom_liga;
	private java.util.Date f_ini;
	private java.util.Date f_fin;
	//Relacion OneToMany = una liga tiene varias categorias
	private Set<Categoria> categoriasDeLiga = new HashSet<Categoria>(0);
	
	@Id
	@Column(name = "ID_LIGA")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "NOM_LIGA")
	public String getNom_liga() {
		return nom_liga;
	}

	public void setNom_liga(String nom_liga) {
		this.nom_liga = nom_liga;
	}

	@Column(name = "F_INI")
	public java.util.Date getF_ini() {
		return f_ini;
	}

	public void setF_ini(java.util.Date f_ini) {
		this.f_ini = f_ini;
	}

	@Column(name = "F_FIN")
	public java.util.Date getF_fin() {
		return f_fin;
	}

	public void setF_fin(java.util.Date f_fin) {
		this.f_fin = f_fin;
	}

	//mappedBy hace referencia al objeto de tipo lista de la clase Categoria (nombre de la variable)
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "liga")
	public Set<Categoria> getCategoriasDeLiga() {
		return categoriasDeLiga;
	}

	public void setCategoriasDeLiga(Set<Categoria> categoriasDeLiga) {
		this.categoriasDeLiga = categoriasDeLiga;
	}
}
