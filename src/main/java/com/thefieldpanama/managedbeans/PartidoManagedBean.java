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
import com.thefieldpanama.beans.Equipo;
import com.thefieldpanama.beans.Liga;
import com.thefieldpanama.beans.Partido;
import com.thefieldpanama.services.CategoriaService;
import com.thefieldpanama.services.EquipoService;
import com.thefieldpanama.services.LigaService;
import com.thefieldpanama.services.PartidoService;

@ManagedBean(name = "PartidoMB")
@ViewScoped
public class PartidoManagedBean implements Serializable {
	private static final long serialVersionUID = -8883168995914955949L;
	@ManagedProperty(value = "#{PartidoServicio}")
	PartidoService partidoService;
	@ManagedProperty(value = "#{EquipoServicio}")
	EquipoService equipoService;
	@ManagedProperty(value = "#{CategoriaServicio}")
	CategoriaService categoriaService;
	@ManagedProperty(value = "#{LigaServicio}")
	LigaService ligaService;

	private Equipo selectedEquipo1;
	private Equipo selectedEquipo2;
	private Categoria selectedCategoria;// Ya esta asociada a la liga
	private Partido selectedPartido;
	private List<Equipo> listEquipos;
	private List<Equipo> listEquiposFiltrados;
	private List<Equipo> listEquiposSegundoFiltro;
	private List<Categoria> listCategorias;
	private List<Categoria> listCategoriasFiltradas;
	private List<Liga> listLigas;
	private List<Partido> listPartidos;
	private int form_filter_id_liga;
	private int form_filter_id_categoria;
	private int form_filter_eq1;
	private int form_filter_eq2;
	private Date form_fecha;
	private String form_hora;
	private String form_lugar;

	@PostConstruct
	public void init() {
		// Se inicializan las listas generales para filtrarlas despues
		this.getListCategorias();
		this.getListEquipos();
		listEquiposFiltrados = new ArrayList<Equipo>();
		listCategoriasFiltradas = new ArrayList<Categoria>();
	}

	public String agregarPartido() {
		try {
			Partido p = new Partido();
			p.setEquipo1(equipoService.getEquipoById(this.getForm_filter_eq1()));
			p.setEquipo2(equipoService.getEquipoById(this.getForm_filter_eq2()));
			p.setFecha(this.getForm_fecha());
			p.setHora(this.getForm_hora());
			p.setLugar(this.getForm_lugar());
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Registro insertado", p.getEquipo1()
									.getNom_equipo()
									+ " vs "
									+ p.getEquipo2().getNom_equipo()));
			partidoService.addPartido(p);
			return "calendario";
		} catch (Exception e) {
			return "error";
		}
	}

	public void editarPartido() {
		try {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Registro editado", "ID: " + selectedPartido.getId_partido()));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL,
							"Error de sistema", e.getMessage()));
		}
	}

	public void eliminarPartido() {
		try {
			partidoService.removePartido(selectedPartido.getId_partido());
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Registro eliminado", "ID: " + selectedPartido.getId_partido()));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL,
							"Error de sistema", e.getMessage()));
		}
	}

	public PartidoService getPartidoService() {
		return partidoService;
	}

	public void setPartidoService(PartidoService partidoService) {
		this.partidoService = partidoService;
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

	public LigaService getLigaService() {
		return ligaService;
	}

	public void setLigaService(LigaService ligaService) {
		this.ligaService = ligaService;
	}

	public Equipo getSelectedEquipo1() {
		return selectedEquipo1;
	}

	public void setSelectedEquipo1(Equipo selectedEquipo1) {
		this.selectedEquipo1 = selectedEquipo1;
	}

	public Equipo getSelectedEquipo2() {
		return selectedEquipo2;
	}

	public void setSelectedEquipo2(Equipo selectedEquipo2) {
		this.selectedEquipo2 = selectedEquipo2;
	}

	public Categoria getSelectedCategoria() {
		return selectedCategoria;
	}

	public void setSelectedCategoria(Categoria selectedCategoria) {
		this.selectedCategoria = selectedCategoria;
	}

	public List<Equipo> getListEquipos() {
		listEquipos = new ArrayList<Equipo>();
		listEquipos.addAll(equipoService.listEquipos());
		return listEquipos;
	}

	public void setListEquipos(List<Equipo> listEquipos) {
		this.listEquipos = listEquipos;
	}

	public List<Equipo> getListEquiposFiltrados() {
		return listEquiposFiltrados;
	}

	public void setListEquiposFiltrados(List<Equipo> listEquiposFiltrados) {
		this.listEquiposFiltrados = listEquiposFiltrados;
	}

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

	public List<Liga> getListLigas() {
		listLigas = new ArrayList<Liga>();
		listLigas.addAll(ligaService.listLigas());
		return listLigas;
	}

	public void setListLigas(List<Liga> listLigas) {
		this.listLigas = listLigas;
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

	public int getForm_filter_eq1() {
		return form_filter_eq1;
	}

	public void setForm_filter_eq1(int form_filter_eq1) {
		this.form_filter_eq1 = form_filter_eq1;
	}

	public int getForm_filter_eq2() {
		return form_filter_eq2;
	}

	public void setForm_filter_eq2(int form_filter_eq2) {
		this.form_filter_eq2 = form_filter_eq2;
	}

	public Date getForm_fecha() {
		return form_fecha;
	}

	public void setForm_fecha(Date form_fecha) {
		this.form_fecha = form_fecha;
	}

	public String getForm_hora() {
		return form_hora;
	}

	public void setForm_hora(String form_hora) {
		this.form_hora = form_hora;
	}

	public String getForm_lugar() {
		return form_lugar;
	}

	public void setForm_lugar(String form_lugar) {
		this.form_lugar = form_lugar;
	}

	public List<Partido> getListPartidos() {
		listPartidos = new ArrayList<Partido>();
		listPartidos.addAll(partidoService.listPartidos());
		return listPartidos;
	}

	public void setListPartidos(List<Partido> listPartidos) {
		this.listPartidos = listPartidos;
	}
	
	public List<Equipo> getListEquiposSegundoFiltro() {
		return listEquiposSegundoFiltro;
	}

	public void setListEquiposSegundoFiltro(
			List<Equipo> listEquiposSegundoFiltro) {
		this.listEquiposSegundoFiltro = listEquiposSegundoFiltro;
	}

	public Partido getSelectedPartido() {
		return selectedPartido;
	}

	public void setSelectedPartido(Partido selectedPartido) {
		this.selectedPartido = selectedPartido;
	}

	/**
	 * Este metodo se ejecuta al hacer click sobre el filtro de ligas
	 */
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
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Nada por aqui",
							"La liga seleccionada no tiene categorias"));
		}
	}

	/**
	 * Este metodo se ejecuta al seleccionar el filtro de categorias
	 */
	public void onCategoriasChange() {
		listEquiposFiltrados.clear();
		if (listEquipos.size() > 0) {
			for (Equipo e : listEquipos) {
				if (e.getCategoria().getId_categoria() == this
						.getForm_filter_id_categoria())
					listEquiposFiltrados.add(e);
			}
		}

		if (listEquiposFiltrados.size() == 0) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Nada por aqui",
							"La categoria seleccionada no contiene equipos"));
		}
	}

	/**
	 * Implementar filtro para el segundo list de equipos, no debe aparecer el equipo seleccionado
	 * en el primer list de equipos
	 */
	public void onPrimerEquipoElegido() {
//		listEquiposSegundoFiltro = new ArrayList<Equipo>();
//		listEquiposSegundoFiltro = listEquiposFiltrados;
//		System.out.println("SE FILTRO ALGO " + listEquiposSegundoFiltro.size() + " le meto " + listEquiposFiltrados.size());
//		int index = 0;
//		
//		for (int i = 0; i < listEquiposSegundoFiltro.size(); i++) {
//			if (listEquiposSegundoFiltro.get(i).getId_equipo() == this.getForm_filter_eq1())
//				index = i;
//		}
//		listEquiposSegundoFiltro.remove(index);
	}
}
