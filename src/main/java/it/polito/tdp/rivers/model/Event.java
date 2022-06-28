package it.polito.tdp.rivers.model;

import java.time.LocalDate;




public class Event implements Comparable <Event> {
	
	public enum EventType{
		Flusso_in_entrata,
		Tracimazione,
	}
	
	private LocalDate time;
	private EventType type;
	private double fin;
	
	
	

	public Event(LocalDate time, EventType type, double fin) {
		super();
		this.time = time;
		this.type = type;
		this.fin = fin;
	}
	
	

	public LocalDate getTime() {
		return time;
	}



	public EventType getType() {
		return type;
	}



	public double getFin() {
		return fin;
	}


	@Override
	public int compareTo(Event other) {
		return this.time.compareTo(other.time);
	}



	
	
	

}
