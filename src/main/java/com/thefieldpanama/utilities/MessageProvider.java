package com.thefieldpanama.utilities;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

/**
 * Esta clase obtiene el properties de mensajes para la aplicacion
 * 
 * @author rospena
 * 
 */
public class MessageProvider {
	private ResourceBundle bundle;
	private Logger log = Logger.getLogger(this.getClass());

	public ResourceBundle getBundle() {
		if (bundle == null) {
			FacesContext context = FacesContext.getCurrentInstance();
			bundle = context.getApplication()
					.getResourceBundle(context, "msgs");
		}
		return bundle;
	}

	public String getValue(String key) {

		String result = null;
		try {
			result = getBundle().getString(key);
		} catch (MissingResourceException e) {
			result = key + " not found";
			log.info(Utilities.stringStackTrace(e)); 
		}
		return result;
	}
}
