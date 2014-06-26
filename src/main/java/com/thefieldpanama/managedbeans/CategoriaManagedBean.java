package com.thefieldpanama.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.thefieldpanama.beans.Categoria;
import com.thefieldpanama.beans.Liga;
import com.thefieldpanama.services.CategoriaService;
import com.thefieldpanama.services.LigaService;

@ManagedBean(name = "CategoriaMB")
@ViewScoped
public class CategoriaManagedBean implements Serializable {
	private static final long serialVersionUID = -2870050274909865404L;
	@ManagedProperty(value = "#{CategoriaServicio}")
	CategoriaService categoriaService;
	@ManagedProperty(value = "#{LigaServicio}")
	LigaService ligaService;

	Liga selectedLiga;
	Categoria selectedCategoria;
	List<Categoria> listCategorias;
	List<Liga> listLigas;

	private String form_nom_categoria;
	private int form_id_liga;
	
	public void agregarCategoria() {
		try {
			Categoria c = new Categoria();
			c.setLiga(ligaService.getLigaById(this.getForm_id_liga())); 
			c.setNom_categoria(this.getForm_nom_categoria());
			categoriaService.addCategoria(c);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Registro insertado", this.getForm_nom_categoria()));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL,
							"Error de sistema", e.getMessage()));
		}
	}

	public void editarCategoria() {
		try{FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Registro editado", selectedCategoria
								.getNom_categoria() + " de " + selectedCategoria.getLiga().getNom_liga()));
		}catch(Exception e){
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL,
							"Error de sistema", e.getMessage()));
		}
	}

	public void eliminarCategoria() {
		try {
			categoriaService.removeCategoria(selectedCategoria
					.getId_categoria());
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Registro eliminado", selectedCategoria
									.getNom_categoria()));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL,
							"Error de sistema", e.getMessage()));
		}
	}

	public CategoriaService getCategoriaService() {
		return categoriaService;
	}

	public void setCategoriaService(CategoriaService categoriaService) {
		this.categoriaService = categoriaService;
	}

	public Categoria getSelectedCategoria() {
		return selectedCategoria;
	}

	public void setSelectedCategoria(Categoria selectedCategoria) {
		this.selectedCategoria = selectedCategoria;
	}

	public Liga getSelectedLiga() {
		return selectedLiga;
	}

	public void setSelectedLiga(Liga selectedLiga) {
		this.selectedLiga = selectedLiga;
	}

	public List<Categoria> getListCategorias() {
		listCategorias = new ArrayList<Categoria>();
		listCategorias.addAll(categoriaService.listCategorias());
		return listCategorias;
	}

	public void setListCategorias(List<Categoria> listCategorias) {
		this.listCategorias = listCategorias;
	}

	public List<Liga> getListLigas() {
		listLigas = new ArrayList<Liga>();
		listLigas.addAll(ligaService.listLigas());
		return listLigas;
	}

	public void setListLigas(List<Liga> listLigas) {
		this.listLigas = listLigas;
	}

	public LigaService getLigaService() {
		return ligaService;
	}

	public void setLigaService(LigaService ligaService) {
		this.ligaService = ligaService;
	}

	public String getForm_nom_categoria() {
		return form_nom_categoria;
	}

	public void setForm_nom_categoria(String form_nom_categoria) {
		this.form_nom_categoria = form_nom_categoria;
	}

	public int getForm_id_liga() {
		return form_id_liga;
	}

	public void setForm_id_liga(int form_id_liga) {
		this.form_id_liga = form_id_liga;
	}
}
