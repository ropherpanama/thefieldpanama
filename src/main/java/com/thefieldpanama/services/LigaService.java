package com.thefieldpanama.services;

/**
 * Author: Rosendo Peña Hernandez
 * ropherpanama@gmail.com
 * Date: 23/06/2014
 */

import java.util.List;
import com.thefieldpanama.beans.Liga;

public interface LigaService {
	public void addLiga(Liga l);
	public List<Liga> listLigas();
	public void removeLiga(Integer id);
	public void updateLiga(Liga l);
	public Liga getLigaById(int id);
}
