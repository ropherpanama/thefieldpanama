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
import org.springframework.stereotype.Component;
import com.thefieldpanama.beans.Categoria;
import com.thefieldpanama.beans.Equipo;
import com.thefieldpanama.beans.Liga;
import com.thefieldpanama.webservices.objects.CategoriasWS;
import com.thefieldpanama.webservices.objects.EquiposWS;
import com.thefieldpanama.webservices.objects.LigasWS;

@Component
@Path("app")
public class JSONSupplier extends JSONCore {

	private List<CategoriasWS> catsWs = new ArrayList<CategoriasWS>();
	private List<LigasWS> listWs = new ArrayList<LigasWS>();
	private List<Liga> ligas = new ArrayList<Liga>();
	private List<EquiposWS> equiposWs = new ArrayList<EquiposWS>();

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
			e.printStackTrace();
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
			e.printStackTrace();
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
			e.printStackTrace();
			return null;
		}
	}
}
