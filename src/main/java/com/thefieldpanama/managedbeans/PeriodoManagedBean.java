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

import org.apache.log4j.Logger;

import com.thefieldpanama.beans.Categoria;
import com.thefieldpanama.beans.Liga;
import com.thefieldpanama.beans.Partido;
import com.thefieldpanama.beans.Periodo;
import com.thefieldpanama.services.CategoriaService;
import com.thefieldpanama.services.LigaService;
import com.thefieldpanama.services.PartidoService;
import com.thefieldpanama.services.PeriodoService;
import com.thefieldpanama.utilities.Utilities;

@ManagedBean(name = "ResultadosMB")
@ViewScoped
public class PeriodoManagedBean extends AncientManagedBean implements Serializable {
	private static final long serialVersionUID = -2168388622285931517L;
	@ManagedProperty(value = "#{PartidoServicio}")
	PartidoService partidoService;
	@ManagedProperty(value = "#{CategoriaServicio}")
	CategoriaService categoriaService;
	@ManagedProperty(value = "#{LigaServicio}")
	LigaService ligaService;
	@ManagedProperty(value = "#{PeriodoServicio}")
	PeriodoService periodoService;
	private Logger log = Logger.getLogger(this.getClass());
	private Categoria selectedCategoria;
	private Partido selectedPartido;
	private List<Liga> listLigas;
	private Periodo selectedPeriodo;
	private List<Partido> listPartidos;
	private List<Categoria> listCategorias;
	private List<Categoria> listCategoriasFiltradas;
	private List<Partido> partidosBuscados;
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
		listPartidos = new ArrayList<Partido>();
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

	public Periodo getSelectedPeriodo() {
		return selectedPeriodo;
	}

	public void setSelectedPeriodo(Periodo selectedPeriodo) {
		this.selectedPeriodo = selectedPeriodo;
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
							this.getProvider().getValue("msg_nothing"),
							this.getProvider().getValue("liga_sin_cats")));
		}
	}

	public void onCategoriasChange() {

	}

	public void buscarPartidos() {
		try {
			partidosBuscados = partidoService.getPartidosByCategoryAndDate(
					this.getForm_filter_id_categoria(),
					this.getForm_date_partido());

			if (partidosBuscados.size() <= 0) {
				FacesContext
						.getCurrentInstance()
						.addMessage(
								null,
								new FacesMessage(FacesMessage.SEVERITY_WARN,
										this.getProvider().getValue("msg_nothing"),
										this.getProvider().getValue("res_sin_partidos")));
			}else{
				for(Partido p : partidosBuscados){
					log.info(p.getEquipo1().getNom_equipo() + " vs " + p.getEquipo2().getNom_equipo()); 
				}
			}
		} catch (Exception e) {
			log.info(Utilities.stringStackTrace(e));
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							this.getProvider().getValue("msg_sys_err"), e.getMessage()));
		}
	}

	public void agregarPeriodos() {
		try {
			Periodo p = new Periodo();
			p.setPartido(this.getSelectedPartido());
			p.setPts_equipo_1(this.getForm_pts_equipo_1());
			p.setPts_equipo_2(this.getForm_pts_equipo_2());
			periodoService.addPeriodo(p);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							this.getProvider().getValue("msg_add"), selectedPartido
									.getEquipo1().getNom_equipo()
									+ " vs "
									+ selectedPartido.getEquipo2()
											.getNom_equipo()));
		} catch (Exception e) {
			log.info(Utilities.stringStackTrace(e));
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							this.getProvider().getValue("msg_sys_err"), e.getMessage()));
		}
	}

	public void eliminarPeriodo() {
		try {
			periodoService.removePeriodo(selectedPeriodo.getId_periodo());
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							this.getProvider().getValue("msg_del"), "ID: "
									+ selectedPeriodo.getId_periodo()));
		} catch (Exception e) {
			log.info(Utilities.stringStackTrace(e));
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							this.getProvider().getValue("msg_sys_err"), e.getMessage()));
		}
	}
	
	public void editarPeriodo() {
		try {
			periodoService.removePeriodo(selectedPeriodo.getId_periodo());
			Periodo p = new Periodo();
			p.setPartido(this.getSelectedPeriodo().getPartido());
			p.setPts_equipo_1(this.getForm_pts_equipo_1());
			p.setPts_equipo_2(this.getForm_pts_equipo_2());
			periodoService.addPeriodo(p);
		}catch (Exception e) {
			log.info(Utilities.stringStackTrace(e));
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							this.getProvider().getValue("msg_sys_err"), e.getMessage()));
		}
	}

	public List<Partido> getPartidosBuscados() {
		partidosBuscados = new ArrayList<Partido>();
		return partidosBuscados;
	}

	public void setPartidosBuscados(List<Partido> partidosBuscados) {
		this.partidosBuscados = partidosBuscados;
	}
	
	/**
	 * Muestra en el grid de partidos todos los partidos registrados
	 */
	public void listarTodosLosPartidos() {
		listPartidos.clear();
		listPartidos.addAll(partidoService.listPartidos());
	}
	
	/**
	 * Muestra en el grid de partidos, solo los partidos de hoy
	 */
	public void listarPartidosDelDia() {
		listPartidos.clear();
		listPartidos.addAll(partidoService.getPartidosToday());
		
		if(listPartidos.size() == 0) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							this.getProvider().getValue("msg_nothing"),
							"No hay partidos para hoy"));
		}
	}
}
