package com.thefieldpanama.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.thefieldpanama.beans.Categoria;
import com.thefieldpanama.beans.Liga;
import com.thefieldpanama.beans.Partido;
import com.thefieldpanama.services.CategoriaService;
import com.thefieldpanama.services.LigaService;
import com.thefieldpanama.services.PartidoService;
import com.thefieldpanama.services.PeriodoService;

@ManagedBean(name = "ResultadosMB")
@ViewScoped
public class PeriodoManagedBean implements Serializable {
	private static final long serialVersionUID = -2168388622285931517L;
	@ManagedProperty(value = "#{PartidoServicio}")
	PartidoService partidoService;
	@ManagedProperty(value = "#{CategoriaServicio}")
	CategoriaService categoriaService;
	@ManagedProperty(value = "#{LigaServicio}")
	LigaService ligaService;
	@ManagedProperty(value = "#{PeriodoServicio}")
	PeriodoService periodoService;

	private Categoria selectedCategoria;
	private Partido selectedPartido;
	private List<Liga> listLigas;
	private List<Partido> listPartidos;
	private List<Categoria> listCategorias;
	private List<Categoria> listCategoriasFiltradas;

	private int form_pts_equipo_1;
	private int form_pts_equipo_2;
	private Date form_date_partido;
	private int form_filter_id_liga;
	private int form_filter_id_categoria;

	@PostConstruct
	public void init() {
		// Se inicializan las listas generales para filtrarlas despues
		this.getListCategorias();
		listCategoriasFiltradas = new ArrayList<Categoria>();
	}
	
	public int getForm_filter_id_liga() {
		return form_filter_id_liga;
	}

	public void setForm_filter_id_liga(int form_filter_id_liga) {
		this.form_filter_id_liga = form_filter_id_liga;
	}

	public int getForm_filter_id_categoria() {
		return form_filter_id_categoria;
	}

	public void setForm_filter_id_categoria(int form_filter_id_categoria) {
		this.form_filter_id_categoria = form_filter_id_categoria;
	}

	public PartidoService getPartidoService() {
		return partidoService;
	}

	public void setPartidoService(PartidoService partidoService) {
		this.partidoService = partidoService;
	}

	public CategoriaService getCategoriaService() {
		return categoriaService;
	}

	public void setCategoriaService(CategoriaService categoriaService) {
		this.categoriaService = categoriaService;
	}

	public LigaService getLigaService() {
		return ligaService;
	}

	public void setLigaService(LigaService ligaService) {
		this.ligaService = ligaService;
	}

	public PeriodoService getPeriodoService() {
		return periodoService;
	}

	public void setPeriodoService(PeriodoService periodoService) {
		this.periodoService = periodoService;
	}

	public Categoria getSelectedCategoria() {
		return selectedCategoria;
	}

	public void setSelectedCategoria(Categoria selectedCategoria) {
		this.selectedCategoria = selectedCategoria;
	}

	public Partido getSelectedPartido() {
		return selectedPartido;
	}

	public void setSelectedPartido(Partido selectedPartido) {
		this.selectedPartido = selectedPartido;
	}

	public List<Liga> getListLigas() {
		listLigas = new ArrayList<Liga>();
		listLigas.addAll(ligaService.listLigas());
		return listLigas;
	}

	public void setListLigas(List<Liga> listLigas) {
		this.listLigas = listLigas;
	}

	public List<Partido> getListPartidos() {
		listPartidos = new ArrayList<Partido>();
		listPartidos.addAll(partidoService.listPartidos());
		return listPartidos;
	}

	public void setListPartidos(List<Partido> listPartidos) {
		this.listPartidos = listPartidos;
	}

	/**
	 * Se carga la lista de las categorias (todas)
	 * 
	 * @return
	 */
	public List<Categoria> getListCategorias() {
		listCategorias = new ArrayList<Categoria>();
		listCategorias.addAll(categoriaService.listCategorias());
		return listCategorias;
	}

	public void setListCategorias(List<Categoria> listCategorias) {
		this.listCategorias = listCategorias;
	}

	public List<Categoria> getListCategoriasFiltradas() {
		return listCategoriasFiltradas;
	}

	public void setListCategoriasFiltradas(
			List<Categoria> listCategoriasFiltradas) {
		this.listCategoriasFiltradas = listCategoriasFiltradas;
	}

	public int getForm_pts_equipo_1() {
		return form_pts_equipo_1;
	}

	public void setForm_pts_equipo_1(int form_pts_equipo_1) {
		this.form_pts_equipo_1 = form_pts_equipo_1;
	}

	public int getForm_pts_equipo_2() {
		return form_pts_equipo_2;
	}

	public void setForm_pts_equipo_2(int form_pts_equipo_2) {
		this.form_pts_equipo_2 = form_pts_equipo_2;
	}

	public Date getForm_date_partido() {
		return form_date_partido;
	}

	public void setForm_date_partido(Date form_date_partido) {
		this.form_date_partido = form_date_partido;
	}

	public void onLigasChange() {
		listCategoriasFiltradas.clear();
		if (listCategorias.size() > 0) {
			for (Categoria c : listCategorias) {
				if (c.getLiga().getId() == this.getForm_filter_id_liga())
					listCategoriasFiltradas.add(c);
			}
		}

		if (listCategoriasFiltradas.size() == 0) {// Si la liga seleccionada no
													// contiene categorias
													// registradas
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Nada por aqui",
							"La liga seleccionada no tiene categorias"));
		}
	}

	public void onCategoriasChange() {

	}

	public void buscarPartidos() {
		try{
			listPartidos.clear();
			listPartidos = partidoService.getPartidosByCategoryAndDate(this.getForm_filter_id_categoria(), this.getForm_date_partido());
			
			if(listPartidos.size() <= 0) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_WARN,
								"Nada por aqui",
								"La categoria o fecha seleccionada no tiene partidos"));
			}
		}catch(Exception e){
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Lo sentimos",
							e.getMessage()));
		}
	}
}
