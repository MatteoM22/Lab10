package it.polito.tdp.rivers.model;

public class MediaEConteggio {
	private int idRiver;
	private int count;
	private float avg;
	
	public MediaEConteggio(int idRiver, int count, float avg) {
		super();
		this.idRiver = idRiver;
		this.count = count;
		this.avg = avg;
	}

	public int getIdRiver() {
		return idRiver;
	}

	public void setIdRiver(int idRiver) {
		this.idRiver = idRiver;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public float getAvg() {
		return avg;
	}

	public void setAvg(float avg) {
		this.avg = avg;
	}
	
	
	
	

}
