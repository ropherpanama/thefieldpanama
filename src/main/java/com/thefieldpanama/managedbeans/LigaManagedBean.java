package com.thefieldpanama.managedbeans;

/**
 * Author: Rosendo Peña Hernandez
 * ropherpanama@gmail.com
 * Date: 23/06/2014
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.thefieldpanama.beans.Liga;
import com.thefieldpanama.services.LigaService;
import com.thefieldpanama.utilities.Utilities;

@ManagedBean(name = "LigaMB")
@ViewScoped
public class LigaManagedBean extends AncientManagedBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "resumen_ligas";
	private static final String ERROR = "error";
	@ManagedProperty(value = "#{LigaServicio}")
	LigaService ligaService;
	Liga selectedLiga;
	// Campos para rellenar el bean a partir del formulario
	private int id;
	private String nom_liga;
	private Date f_ini;
	private Date f_fin;
	List<Liga> listLigas;
	private Logger log = Logger.getLogger(this.getClass());

	/**
	 * Metodo invocado por el boton agregar nueva liga
	 * 
	 * @return estado de la transaccion
	 */
	public String agregarLiga() {
		try {
			Liga l = new Liga();
			l.setNom_liga(this.getNom_liga());
			l.setF_ini(getF_ini());
			l.setF_fin(getF_fin());
			ligaService.addLiga(l);
			return SUCCESS;
		} catch (DataAccessException d) {
			log.info(Utilities.stringStackTrace(d));
			return ERROR;
		}
	}

	/**
	 * Metodo invocado al presionar sobre el boton de eliminar del registro
	 * seleccionado
	 */
	public void eliminarLiga() {
		try {
			ligaService.removeLiga(selectedLiga.getId());
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							this.getProvider().getValue("msg_del"), selectedLiga.getNom_liga()));
		} catch (Exception e) {
			log.info(Utilities.stringStackTrace(e));
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL,
							this.getProvider().getValue("msg_sys_err"), e.getMessage()));
		}
	}

	/**
	 * Metodo invocado por la opcion Editar Liga
	 */
	public void editarLiga() {
		try {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							this.getProvider().getValue("msg_upt"), selectedLiga.getNom_liga()));
		} catch (Exception e) {
			log.info(Utilities.stringStackTrace(e));
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL,
							this.getProvider().getValue("msg_sys_err"), e.getMessage()));
		}
	}

	/**
	 * Metodo invocado para resetear los campos del formulario
	 */
	public void reset() {
		this.setNom_liga("");
		this.setF_fin(Utilities.fechahoy());
		this.setF_ini(Utilities.fechahoy());
	}

	public LigaService getLigaService() {
		return ligaService;
	}

	public void setLigaService(LigaService ligaService) {
		this.ligaService = ligaService;
	}

	public Liga getSelectedLiga() {
		return selectedLiga;
	}

	public void setSelectedLiga(Liga selectedLiga) {
		this.selectedLiga = selectedLiga;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom_liga() {
		return nom_liga;
	}

	public void setNom_liga(String nom_liga) {
		this.nom_liga = nom_liga;
	}

	public Date getF_ini() {
		return f_ini;
	}

	public void setF_ini(Date f_ini) {
		this.f_ini = f_ini;
	}

	public Date getF_fin() {
		return f_fin;
	}

	public void setF_fin(Date f_fin) {
		this.f_fin = f_fin;
	}

	/**
	 * Metodo usado por la pantalla de resumen de ligas Lista todas las ligas
	 * existentes en la base de datos
	 * 
	 * @return Ligas Lista
	 */
	public List<Liga> getListLigas() {
		listLigas = new ArrayList<Liga>();
		listLigas.addAll(ligaService.listLigas());
		return listLigas;
	}

	public void setListLigas(List<Liga> listLigas) {
		this.listLigas = listLigas;
	}
}
