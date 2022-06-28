package it.polito.tdp.rivers.model;


import java.util.List;

import it.polito.tdp.rivers.db.RiversDAO;

public class Model {
	private RiversDAO dao;
	private List<River> rivers;
	private List<String> date;
	
	public Model() {
		dao = new RiversDAO();
	}
	
	public List<River> getRivers() {
		if(rivers==null) {
			rivers=dao.getAllRivers();
		}
		return rivers;
	}
	
	public MediaEConteggio getMediaECconteggio(int idRiver) {
		
		MediaEConteggio m = dao.getAllMediaEConteggio(idRiver);
		return m;
	}
	
	public List<String> getAllDate(int idRiver){
		date=dao.getAllDate(idRiver);
		return date;
	}
	
	public int simula(int fattore, float fMedia, int idRiver) {
		Simulatore s = new Simulatore(fattore, fMedia);
		s.caricaEventi(dao.getFlow(idRiver));
		s.run();
		return s.getnInsodisfatti();
	}
	
	public double simula2(int fattore, float fMedia, int idRiver) {
		Simulatore s = new Simulatore(fattore, fMedia);
		s.caricaEventi(dao.getFlow(idRiver));
		s.run();
		return s.getOccupazioneFinale();
	}
	
	
}
