package com.thefieldpanama.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DualListModel;

import com.thefieldpanama.beans.Categoria;
import com.thefieldpanama.beans.Equipo;
import com.thefieldpanama.beans.FormulasCalculo;
import com.thefieldpanama.beans.Grupos;
import com.thefieldpanama.beans.Liga;
import com.thefieldpanama.services.CategoriaService;
import com.thefieldpanama.services.EquipoService;
import com.thefieldpanama.services.FormulasCalculoService;
import com.thefieldpanama.services.GruposService;
import com.thefieldpanama.services.LigaService;
import com.thefieldpanama.utilities.Utilities;

@ManagedBean(name = "GrupoMB")
@ViewScoped
public class GruposManagedBean extends AncientManagedBean implements Serializable{
	private static final long serialVersionUID = 7948546671224810832L;
	private int form_filter_id_liga;
	private int form_filter_id_categoria;
	@ManagedProperty(value = "#{EquipoServicio}")
	EquipoService equipoService;
	@ManagedProperty(value = "#{CategoriaServicio}")
	CategoriaService categoriaService;
	@ManagedProperty(value = "#{LigaServicio}")
	LigaService ligaService;
	@ManagedProperty(value = "#{FormulasCalculoServicio}")
	private FormulasCalculoService formulaService;
	@ManagedProperty(value = "#{GruposServicio}")
	private GruposService grupoService;
	private Logger log = Logger.getLogger(this.getClass());
	private List<Categoria> listCategorias;
	private List<Categoria> listCategoriasFiltradas;
	private List<Liga> listLigas;
	private Categoria selectedCategoria;
	private List<Equipo> listEquipos;
	private List<Equipo> listEquiposFiltrados;
	private String form_gr_nombre;
	private DualListModel<Equipo> pickListEquipos;
	private List<Equipo> pickedEquipos;
	private FormulasCalculo selectedFormula;
	private List<FormulasCalculo> formulasCalculo;
	private int form_id_formula;
	private List<Grupos> listGrupos;
	private Grupos selectedGrupo;
	
	public List<Grupos> getListGrupos() {
		return listGrupos;
	}

	public void setListGrupos(List<Grupos> listGrupos) {
		this.listGrupos = listGrupos;
	}

	public GruposService getGrupoService() {
		return grupoService;
	}

	public void setGrupoService(GruposService grupoService) {
		this.grupoService = grupoService;
	}

	public int getForm_id_formula() {
		return form_id_formula;
	}
	
	public void setForm_id_formula(int form_id_formula) {
		this.form_id_formula = form_id_formula;
	}
	
	@PostConstruct
	public void init() {
		// Se inicializan las listas generales para filtrarlas despues
		this.getListCategorias();
		this.getListEquipos();
		listGrupos = new ArrayList<Grupos>();
		listEquiposFiltrados = new ArrayList<Equipo>();
		listCategoriasFiltradas = new ArrayList<Categoria>();
		pickedEquipos = new ArrayList<Equipo>();
		pickListEquipos = new DualListModel<Equipo>();
	}
	
	public void mostrarGrupos() {
		listGrupos.clear();
		List<Grupos> gs = grupoService.list();
		
		for(Grupos g : gs) {
			g.setNomCategoria(categoriaService.getCategoriaById(g.getId_categoria()).getNom_categoria());
		}
		
//		listGrupos.addAll(grupoService.list());
		listGrupos.addAll(gs);
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
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							this.getProvider().getValue("msg_nothing"),
							this.getProvider().getValue("liga_sin_cats")));
		}
	}
	
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
							this.getProvider().getValue("msg_nothing"),
							this.getProvider().getValue("liga_sin_cats")));
		}else{
			pickListEquipos.setSource(listEquiposFiltrados);
			pickListEquipos.setTarget(pickedEquipos);
		}  
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

	public Logger getLog() {
		return log;
	}

	public void setLog(Logger log) {
		this.log = log;
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

	public void setListCategoriasFiltradas(List<Categoria> listCategoriasFiltradas) {
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

	public Categoria getSelectedCategoria() {
		return selectedCategoria;
	}

	public void setSelectedCategoria(Categoria selectedCategoria) {
		this.selectedCategoria = selectedCategoria;
	}

	/**
	 * Solo se cargan los equipos que no tienen grupo
	 * @return equipos sin grupo asociado
	 */
	public List<Equipo> getListEquipos() {
		listEquipos = new ArrayList<Equipo>();
		listEquipos.addAll(equipoService.getEquiposSinGrupo());
		return listEquipos;
	}

	public void setListEquipos(List<Equipo> listEquipos) {
		this.listEquipos = listEquipos;
	}

	public Grupos getSelectedGrupo() {
		return selectedGrupo;
	}

	public void setSelectedGrupo(Grupos selectedGrupo) {
		this.selectedGrupo = selectedGrupo;
	}

	public List<Equipo> getListEquiposFiltrados() {
		return listEquiposFiltrados;
	}

	public void setListEquiposFiltrados(List<Equipo> listEquiposFiltrados) {
		this.listEquiposFiltrados = listEquiposFiltrados;
	}
	
	public String getForm_gr_nombre() {
		return form_gr_nombre;
	}
	
	public void setForm_gr_nombre(String form_gr_nombre) {
		this.form_gr_nombre = form_gr_nombre;
	}
	
	/**
	 * Este metodo se encarga de realizar las operaciones
	 * de creacion del grupo
	 */
    public void crearGrupo() {
    	List<Equipo> seleccionados = pickListEquipos.getTarget();
    	
    	if(form_id_formula == -1 || seleccionados.size() == 0) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							this.getProvider().getValue("msg_sys_warn"), "Complete toda la informacion necesaria"));
    	}else{
    		//Primero crear el grupo
    		Set<Equipo> teams = new HashSet<Equipo>();
    		Grupos gr = new Grupos();
    		gr.setEquipos(teams);
    		gr.setFormula(formulaService.getById(form_id_formula));
    		gr.setId_categoria(seleccionados.get(0).getCategoria().getId_categoria());
//    		gr.setCategoria(seleccionados.get(0).getCategoria()); 
    		gr.setNombre(form_gr_nombre);
    		
    		grupoService.add(gr); 
    		
    		for(Equipo e : seleccionados) {//Actualizar el indicativo de grupos en cada equipo
    			e.setGrupo(gr);
    			equipoService.updateEquipo(e); 
    		}
    		
    		reset();
    		mostrarGrupos();//Autoupdate de grid
    		
    		FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Correcto: ",
							"El grupo se ha creado correctamente"));
    	}
    }

	public DualListModel<Equipo> getPickListEquipos() {
		return pickListEquipos;
	}

	public void setPickListEquipos(DualListModel<Equipo> pickListEquipos) {
		this.pickListEquipos = pickListEquipos;
	}

	public List<Equipo> getPickedEquipos() {
		return pickedEquipos;
	}

	public void setPickedEquipos(List<Equipo> pickedEquipos) {
		this.pickedEquipos = pickedEquipos;
	}
	
	public FormulasCalculoService getFormulaService() {
		return formulaService;
	}
	
	public void setFormulaService(FormulasCalculoService formulaService) {
		this.formulaService = formulaService;
	}
	
	public FormulasCalculo getSelectedFormula() {
		return selectedFormula;
	}
	
	public void setSelectedFormula(FormulasCalculo selectedFormula) {
		this.selectedFormula = selectedFormula;
	}
	
	public List<FormulasCalculo> getFormulasCalculo() {
		formulasCalculo = new ArrayList<FormulasCalculo>();
		formulasCalculo.addAll(formulaService.list());
		return formulasCalculo;
	}
	
	public void setFormulasCalculo(List<FormulasCalculo> formulasCalculo) {
		this.formulasCalculo = formulasCalculo;
	}
	
	public void reset() {
		this.getListEquipos();//Se carga nuevamente la lista de equipos actualizada
		this.pickListEquipos.getSource().clear();
		this.pickListEquipos.getTarget().clear();
		this.setForm_filter_id_categoria(-1);
		this.setForm_filter_id_liga(-1);
		this.setForm_gr_nombre("");
		this.setForm_id_formula(-1);
	}
	
	public void onEdit(RowEditEvent event) { 
		Grupos edited = (Grupos) event.getObject();
		grupoService.update(edited);  
        FacesMessage msg = new FacesMessage("Registro editado",((Grupos) event.getObject()).getNombre()); 
        FacesContext.getCurrentInstance().addMessage(null, msg); 
    } 
       
    public void onCancel(RowEditEvent event) { 
//        FacesMessage msg = new FacesMessage("Edicion cancelada");  
//        FacesContext.getCurrentInstance().addMessage(null, msg);
    	System.out.println("Edicion cancelada");
    }  
    
    public void eliminarGrupo() {
		try {
			listEquiposFiltrados.clear();
			listEquiposFiltrados = equipoService.getEquipoByGrupo(selectedGrupo.getId_grupo());
			grupoService.remove(selectedGrupo.getId_grupo());
			Grupos g = grupoService.getById(100);//Grupo Nulo
			//Asigno grupo nulo a los equipos que pertenecian al grupo eliminado
			for(Equipo e : listEquiposFiltrados) {
				e.setGrupo(g);
				equipoService.updateEquipo(e); 
			}
			
			mostrarGrupos();//Recargo la lista despues de la actualizacion
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							this.getProvider().getValue("msg_del"), selectedGrupo.getNombre()));
		} catch (Exception e) {
			log.info(Utilities.stringStackTrace(e));
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL,
							this.getProvider().getValue("msg_sys_err"), e.getMessage()));
		}
	}
}
