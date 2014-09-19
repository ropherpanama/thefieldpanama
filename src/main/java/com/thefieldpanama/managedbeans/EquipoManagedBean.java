package com.thefieldpanama.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import com.thefieldpanama.beans.Categoria;
import com.thefieldpanama.beans.Equipo;
import com.thefieldpanama.beans.Liga;
import com.thefieldpanama.services.CategoriaService;
import com.thefieldpanama.services.EquipoService;
import com.thefieldpanama.services.GruposService;
import com.thefieldpanama.services.LigaService;
import com.thefieldpanama.utilities.Utilities;

@ManagedBean(name = "EquipoMB")
@ViewScoped
public class EquipoManagedBean extends AncientManagedBean implements Serializable {
	private static final long serialVersionUID = 2792026524599271882L;
	@ManagedProperty(value = "#{EquipoServicio}")
	EquipoService equipoService;
	@ManagedProperty(value = "#{CategoriaServicio}")
	CategoriaService categoriaService;
	@ManagedProperty(value = "#{LigaServicio}")
	LigaService ligaService;
	@ManagedProperty(value = "#{GruposServicio}")
	private GruposService grupoService;
	Equipo selectedEquipo;
	Categoria selectedCategoria;
	List<Equipo> listEquipos;
	List<Categoria> listCategorias;
	List<Categoria> listCategoriasFiltradas;
	List<Liga> listLigas;
	private String form_nom_equipo;
	private String form_localidad;
	private int form_id_categoria;
	private int form_filter_id_liga;
	private Logger log = Logger.getLogger(this.getClass());

	@PostConstruct
	public void init() {
		this.getListCategorias();
		listCategoriasFiltradas = new ArrayList<Categoria>();
	}

	public List<Categoria> getListCategoriasFiltradas() {
		return listCategoriasFiltradas;
	}

	public void setListCategoriasFiltradas(
			List<Categoria> listCategoriasFiltradas) {
		this.listCategoriasFiltradas = listCategoriasFiltradas;
	}

	public LigaService getLigaService() {
		return ligaService;
	}

	public void setLigaService(LigaService ligaService) {
		this.ligaService = ligaService;
	}

	public int getForm_filter_id_liga() {
		return form_filter_id_liga;
	}

	public void setForm_filter_id_liga(int form_filter_id_liga) {
		this.form_filter_id_liga = form_filter_id_liga;
	}

	public List<Liga> getListLigas() {
		listLigas = new ArrayList<Liga>();
		listLigas.addAll(ligaService.listLigas());
		return listLigas;
	}

	public void setListLigas(List<Liga> listLigas) {
		this.listLigas = listLigas;
	}

	public int getForm_id_categoria() {
		return form_id_categoria;
	}

	public void setForm_id_categoria(int form_id_categoria) {
		this.form_id_categoria = form_id_categoria;
	}

	public void agregarEquipo() {
		try {
			Equipo e = new Equipo();
			e.setCategoria(categoriaService.getCategoriaById(this.getForm_id_categoria()));
			e.setLocalidad(this.getForm_localidad());
			e.setNom_equipo(this.getForm_nom_equipo());
			//Al parecer no tomaba como valido un registro de db con id = 0, por eso se cambio el id a 100
			e.setGrupo(grupoService.getById(100)); 
			equipoService.addEquipo(e);
			
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							this.getProvider().getValue("msg_add"), this.getForm_nom_equipo()));
		} catch (Exception e) {
			log.info(Utilities.stringStackTrace(e));
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL,
							this.getProvider().getValue("msg_sys_err"), e.getMessage()));
		}
	}

	public void editarEquipo() {
		try {
			FacesContext.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_INFO,
									this.getProvider().getValue("msg_upt"), selectedEquipo
											.getNom_equipo()));
		} catch (Exception e) {
			log.info(Utilities.stringStackTrace(e));
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL,
							this.getProvider().getValue("msg_sys_err"), e.getMessage()));
		}
	}

	public void eliminarEquipo() {
		try {
			equipoService.removeEquipo((selectedEquipo.getId_equipo()));
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							this.getProvider().getValue("msg_del"), selectedEquipo
									.getNom_equipo()));
		} catch (Exception e) {
			log.info(Utilities.stringStackTrace(e));
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL,
							this.getProvider().getValue("msg_sys_err"), e.getMessage()));
		}
	}

	public List<Equipo> getListEquipos() {
		listEquipos = new ArrayList<Equipo>();
		listEquipos.addAll(equipoService.listEquipos());
		return listEquipos;
	}

	public void setListEquipos(List<Equipo> listEquipos) {
		this.listEquipos = listEquipos;
	}

	public List<Categoria> getListCategorias() {
		listCategorias = new ArrayList<Categoria>();
		listCategorias.addAll(categoriaService.listCategorias());
		return listCategorias;
	}

	public void setListCategorias(List<Categoria> listCategorias) {
		this.listCategorias = listCategorias;
	}

	public EquipoService getEquipoService() {
		return equipoService;
	}

	public void setEquipoService(EquipoService equipoService) {
		this.equipoService = equipoService;
	}

	public CategoriaService getCategoriaService() {
		return categoriaService;
	}

	public void setCategoriaService(CategoriaService categoriaService) {
		this.categoriaService = categoriaService;
	}

	public Equipo getSelectedEquipo() {
		return selectedEquipo;
	}

	public void setSelectedEquipo(Equipo selectedEquipo) {
		this.selectedEquipo = selectedEquipo;
	}

	public Categoria getSelectedCategoria() {
		return selectedCategoria;
	}

	public void setSelectedCategoria(Categoria selectedCategoria) {
		this.selectedCategoria = selectedCategoria;
	}

	public String getForm_nom_equipo() {
		return form_nom_equipo;
	}

	public void setForm_nom_equipo(String form_nom_equipo) {
		this.form_nom_equipo = form_nom_equipo;
	}

	public String getForm_localidad() {
		return form_localidad;
	}

	public void setForm_localidad(String form_localidad) {
		this.form_localidad = form_localidad;
	}

	/**
	 * Este metodo se ejecuta al hacer click en el filtro de ligas, filtra la
	 * lista de categorias segun la liga elegida
	 */
	public void onLigasChange() {
		listCategoriasFiltradas.clear();
		if (listCategorias.size() > 0) {
			for (Categoria c : listCategorias) {
				if (c.getLiga().getId() == this.getForm_filter_id_liga()) 
					listCategoriasFiltradas.add(c);
			}
		} 
		
		if(listCategoriasFiltradas.size() == 0){//Si la liga seleccionada no contiene categorias registradas
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							this.getProvider().getValue("msg_nothing"), this.getProvider().getValue("liga_sin_cats")));
		}
	}

	public GruposService getGrupoService() {
		return grupoService;
	}

	public void setGrupoService(GruposService grupoService) {
		this.grupoService = grupoService;
	}
}
