package com.thefieldpanama.webservices;

/**
 * Importante mencionar que los servicios web contienen dependencias entre ellos, deben ser llamados 
 * en el orden de su aparicion (primero el de ligas, luego categorias, etc.)
 */
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.thefieldpanama.beans.Categoria;
import com.thefieldpanama.beans.Equipo;
import com.thefieldpanama.beans.Liga;
import com.thefieldpanama.beans.Partido;
import com.thefieldpanama.beans.Periodo;
import com.thefieldpanama.beans.Scores;
import com.thefieldpanama.utilities.Utilities;
import com.thefieldpanama.webservices.objects.CalendarioWS;
import com.thefieldpanama.webservices.objects.CategoriasWS;
import com.thefieldpanama.webservices.objects.EquiposWS;
import com.thefieldpanama.webservices.objects.LigasWS;
import com.thefieldpanama.webservices.objects.ResultadosWS;

/**
 * Esta clase contiene los Url´s y metodos asociados a los servicios web
 * 
 * @author rospena
 * 
 */
@Component
@Path("app")
public class JSONSupplier extends JSONCore {

	private List<CategoriasWS> catsWs = new ArrayList<CategoriasWS>();
	private List<LigasWS> listWs = new ArrayList<LigasWS>();
	private List<Liga> ligas = new ArrayList<Liga>();
	private List<EquiposWS> equiposWs = new ArrayList<EquiposWS>();
	private List<Partido> partidos = new ArrayList<Partido>();
	private List<CalendarioWS> calendWs = new ArrayList<CalendarioWS>();
	private List<ResultadosWS> scoresWs = new ArrayList<ResultadosWS>();
	private List<Scores> todayScoresWS = new ArrayList<Scores>();
	private Logger log = Logger.getLogger(this.getClass());

	/**
	 * Este metodo retorna la informacion sobre las ligas (id;nombre)
	 * 
	 * @return ligas
	 */
	@GET
	@Path("ligas")
	@Produces({ MediaType.APPLICATION_JSON })
	public String ligas() {
		try {
			catsWs.clear();
			listWs.clear();
			ligas = this.getLigaService().listLigas();

			for (Liga l : ligas) {
				LigasWS ws = new LigasWS();
				ws.setId(l.getId());
				ws.setNombre(l.getNom_liga());
				listWs.add(ws);
				ws = null;

				// Por cada liga existe un listado de categorias
				Iterator<Categoria> it = l.getCategoriasDeLiga().iterator();

				while (it.hasNext()) {
					CategoriasWS c = new CategoriasWS();
					Categoria in = it.next();
					c.setId(in.getId_categoria());
					c.setLiga(in.getLiga().getNom_liga());
					c.setNombre(in.getNom_categoria());
					catsWs.add(c);
				}
			}
			// Retorno informacion referente a las ligas unicamente (Nombre y id
			// de liga)
			return this.getMapper().writeValueAsString(listWs);
		} catch (Exception e) {
			log.info(Utilities.stringStackTrace(e));
			return null;
		}
	}

	/**
	 * Este metodo retorna la informacion de las categorias
	 * 
	 * @return categorias
	 */
	@GET
	@Path("cats")
	@Produces({ MediaType.APPLICATION_JSON })
	public String categorias() {
		try {
			return this.getMapper().writeValueAsString(catsWs);
		} catch (Exception e) {
			log.info(Utilities.stringStackTrace(e));
			return null;
		}
	}

	/**
	 * Este metodo retorna la informacion de los equipos
	 * 
	 * @return equipos
	 */
	@GET
	@Path("equipos")
	@Produces({ MediaType.APPLICATION_JSON })
	public String equipos() {
		try {
			equiposWs.clear();
			
			for (Liga l : ligas) {
				Iterator<Categoria> it = l.getCategoriasDeLiga().iterator();

				// Cada liga tiene un set de sus categorias
				while (it.hasNext()) {
					Categoria c = it.next();
					Iterator<Equipo> iteq = c.getEquiposCategoria().iterator();

					// Cada categoria tiene un set de los equipos inscritos en
					// ella
					while (iteq.hasNext()) {
						EquiposWS e = new EquiposWS();
						Equipo equipo = iteq.next();
						e.setId(equipo.getId_equipo());
						e.setCategoria(equipo.getCategoria().getNom_categoria());
						e.setLiga(equipo.getCategoria().getLiga().getNom_liga());
						e.setNombre(equipo.getNom_equipo());
						equiposWs.add(e);
					}
				}
			}
			return this.getMapper().writeValueAsString(equiposWs);
		} catch (Exception e) {
			log.info(Utilities.stringStackTrace(e));
			return null;
		}
	}

	/**
	 * Este servicio retorna la informacion sobre el calendario de partidos
	 * 
	 * @return
	 */
	@GET
	@Path("calendar")
	@Produces({ MediaType.APPLICATION_JSON })
	public String calendario() {
		try {
			calendWs.clear();
			scoresWs.clear();
			partidos = this.getPartidoService().listPartidos();
			// Se cargan los datos del calendario a partir del listado de
			// partidos disponible
			for (Partido p : partidos) {
				CalendarioWS cws = new CalendarioWS();
				cws.setCategoria(p.getEquipo1().getCategoria().getNom_categoria());
				cws.setLiga(p.getEquipo1().getCategoria().getLiga().getNom_liga());
				cws.setEquipo1(p.getEquipo1().getNom_equipo());
				cws.setEquipo2(p.getEquipo2().getNom_equipo());
				cws.setFecha(p.getFecha());
				cws.setHora(p.getHora());
				cws.setLugar(p.getLugar());
				cws.setIdPartido(p.getId_partido()); 
				calendWs.add(cws);
				
				//Inicializar data de scores
				ResultadosWS rws = new ResultadosWS();//Por cada partido creas un objeto score
				ArrayList<String> periodos = new ArrayList<String>();
				
				rws.setCategoria(p.getEquipo1().getCategoria().getNom_categoria());
				rws.setLiga(p.getEquipo1().getCategoria().getLiga().getNom_liga());
				rws.setNomEquipo1(p.getEquipo1().getNom_equipo()); 
				rws.setNomEquipo2(p.getEquipo2().getNom_equipo());
				rws.setIdPartido(p.getId_partido());
				
				int total1 = 0;
				int total2 = 0;
				
				Iterator<Periodo> it = p.getPeriodosPartido().iterator();
				while(it.hasNext()) {
					Periodo pd = it.next();
					String str = String.format("%1$s : %2$-4d - %3$s : %4$d", p.getEquipo1().getNom_equipo(), pd.getPts_equipo_1(), p.getEquipo2().getNom_equipo(), pd.getPts_equipo_2());
					periodos.add(str);
					
					total1 += pd.getPts_equipo_1();
					total2 += pd.getPts_equipo_2();
				}
				rws.setPeriodos(periodos);
				rws.setTotalPtsEquipo1(total1);
				rws.setTotalPtsEquipo2(total2); 
				
				scoresWs.add(rws);
			}
			return this.getMapper().writeValueAsString(calendWs);
		} catch (Exception e) {
			log.info(Utilities.stringStackTrace(e));
			return null;
		}
	}
	
	/**
	 * Este metodo retorna la informacion de los scores
	 * 
	 * @return scores
	 */
	@GET
	@Path("scores")
	@Produces({ MediaType.APPLICATION_JSON })
	public String scores() {
		try {
			return this.getMapper().writeValueAsString(scoresWs);
		} catch (Exception e) {
			log.info(Utilities.stringStackTrace(e));
			return null;
		}
	}
	
	/**
	 * Este servicio retorna solo los resultados de los partidos del dia
	 * Para el tab Scores de la Android App
	 * @return today scores
	 */
	@GET
	@Path("today_scores")
	@Produces({ MediaType.APPLICATION_JSON })
	public String todayScores() {
		try {
			todayScoresWS.clear();
			List<Scores> temp = this.getPeriodoService().getTodayScores();
			
			log.info("TAMAÑO DE LA LISTA DE SCORES RETORNADO ::: " + temp.size()); 
				
			
			for (Scores sc : temp) {
				if (Utilities.compareHours(sc.getHora()) == 1)
					sc.setStatus("Por iniciar");
				else if (Utilities.compareHours(sc.getHora()) == 2)
					sc.setStatus("En curso");
				else if (Utilities.compareHours(sc.getHora()) == 3)
					sc.setStatus("Final");
				else
					sc.setStatus("Sin informacion");
				
				todayScoresWS.add(sc);
			}
			
			return this.getMapper().writeValueAsString(todayScoresWS);
		}catch(Exception e) {
			log.info(Utilities.stringStackTrace(e));
			return null;
		}
	}
}
