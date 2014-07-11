package com.thefieldpanama.managedbeans;

import com.thefieldpanama.utilities.MessageProvider;

/**
 * Esta clase es el papa de todos los managedBeans se creo para que herede a sus
 * hijos cosas que todos deben usar en comun
 * 
 * @author rospena
 * 
 */
public class AncientManagedBean {

	private MessageProvider provider = new MessageProvider();

	public MessageProvider getProvider() {
		return provider;
	}

	public void setProvider(MessageProvider provider) {
		this.provider = provider;
	}

}
