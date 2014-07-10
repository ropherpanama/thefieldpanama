package com.thefieldpanama.webservices;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.thefieldpanama.services.LigaService;

public class JSONCore {

	@Autowired
	private LigaService ligaService;
	private ObjectMapper mapper = new ObjectMapper();
	public LigaService getLigaService() {
		return ligaService;
	}
	public void setLigaService(LigaService ligaService) {
		this.ligaService = ligaService;
	}
	public ObjectMapper getMapper() {
		return mapper;
	}
	public void setMapper(ObjectMapper mapper) {
		this.mapper = mapper;
	}
}
