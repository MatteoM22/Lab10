package it.polito.tdp.rivers.model;


import java.util.List;
import java.util.PriorityQueue;

import it.polito.tdp.rivers.model.Event.EventType;


public class Simulatore {

	// Parametri di ingresso
	private int fattore;
	private float fMedia;
	private float capienza;
	private double fout;
	
	// Valori calcolati in uscita
	private int nInsodisfatti;
	private double occupazioneFinale;
	
	// Stato del mondo
	private double occupazione;
	private int contatore=0;
	private float occupazioneMedia=0;
	
	// Coda degli eventi
	private PriorityQueue<Event> queue ;


	public Simulatore(int fattore, float fMedia) {
		super();
		this.fattore = fattore;
		this.fMedia = fMedia;
		fout=0.8*fMedia*86400;
		capienza = fattore*fMedia*2592000;
		occupazione = capienza/2;
	}
	
	public void run() {
		while(!this.queue.isEmpty()) {
			Event e = this.queue.poll();
			processEvent(e);
		}
	}
	
	public void caricaEventi(List<Flow> flows) {
		this.queue = new PriorityQueue<>();
			for(Flow f: flows) {
				this.queue.add(new Event(f.getDay(),EventType.Flusso_in_entrata, f.getFlow()));
			}
		}
	

	private void processEvent(Event e) {
		switch(e.getType()) {
		case Flusso_in_entrata:
			double random = Math.random();
			if(random>=0.05) {
			if((e.getFin()*86400)>=fout) {
				if(occupazione+(e.getFin()*86400)>capienza) {
					this.queue.add(new Event(e.getTime(), EventType.Tracimazione, e.getFin()));
				}
				else {
					occupazione+=(e.getFin()*86400)-fout;
					occupazioneMedia+=occupazione;
					contatore++;
				}
			}
			else {
				if((occupazione+(e.getFin()*86400))-fout<0) {
					nInsodisfatti++;
					contatore++;
					occupazione=0;
				}
				else {
					occupazione=occupazione-(fout-(e.getFin()*86400));
					occupazioneMedia+=occupazione;
					contatore++;
				}
			}
			}
			else {
				if((e.getFin()*86400)>=fout*10) {
					if(occupazione+(e.getFin()*86400)>capienza) {
						this.queue.add(new Event(e.getTime(), EventType.Tracimazione, e.getFin()));
					}
					else {
						occupazione+=(e.getFin()*86400)-fout*10;
						occupazioneMedia+=occupazione;
						contatore++;
					}
				}
				else {
					if((occupazione+(e.getFin()*86400))-fout*10<0) {
						nInsodisfatti++;
						contatore++;
						occupazione=0;
					}
					else {
						occupazione=occupazione-(fout-(e.getFin()*86400));
						occupazioneMedia+=occupazione;
						contatore++;
					}
				}
				}
				
			break;
			
		case Tracimazione:
			if(capienza!=0) {
			occupazione=occupazione-fout;
			occupazioneMedia+=occupazione;
			contatore++;
			}
			else {
				nInsodisfatti++;
				contatore++;
			}
		}
		
		occupazioneFinale=occupazioneMedia/contatore;
		
	}

	public int getFattore() {
		return fattore;
	}

	public float getfMedia() {
		return fMedia;
	}

	public float getCapienza() {
		return capienza;
	}

	public double getFout() {
		return fout;
	}

	public int getnInsodisfatti() {
		return nInsodisfatti;
	}

	public float getOccupazioneMedia() {
		return occupazioneMedia;
	}

	public double getOccupazione() {
		return occupazione;
	}

	public PriorityQueue<Event> getQueue() {
		return queue;
	}

	public double getOccupazioneFinale() {
		return occupazioneFinale;
	}

	public int getContatore() {
		return contatore;
	}	
		
}



