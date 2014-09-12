package com.thefieldpanama.converters;

import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import org.primefaces.component.picklist.PickList;
import org.primefaces.model.DualListModel;
import com.thefieldpanama.beans.Equipo;

@FacesConverter("equiposConverter")
public class EquiposConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		return getObjectFromUIPickListComponent(arg1, arg2); 
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		String string;
		if (arg2 == null) {
			string = "";
		} else {
			try {
				string = String.valueOf(((Equipo) arg2).getId_equipo());
			} catch (ClassCastException cce) {
				throw new ConverterException();
			}
		}
		return string;
	}

	@SuppressWarnings({ "unchecked" })
	private Equipo getObjectFromUIPickListComponent(UIComponent component,
			String value) {
		final DualListModel<Equipo> dualList;
		try {
			dualList = (DualListModel<Equipo>) ((PickList) component)
					.getValue();
			Equipo team = getObjectFromList(dualList.getSource(),
					Integer.valueOf(value));
			if (team == null) {
				team = getObjectFromList(dualList.getTarget(),
						Integer.valueOf(value));
			}

			return team;
		} catch (ClassCastException cce) {
			throw new ConverterException();
		} catch (NumberFormatException nfe) {
			throw new ConverterException();
		}
	}

	private Equipo getObjectFromList(final List<?> list,
			final Integer identifier) {
		for (final Object object : list) {
			final Equipo team = (Equipo) object;
			if (team.getId_equipo().equals(identifier)) {
				return team;
			}
		}
		return null;
	}
}
