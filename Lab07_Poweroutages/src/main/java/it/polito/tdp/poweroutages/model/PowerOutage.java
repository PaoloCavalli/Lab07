package it.polito.tdp.poweroutages.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class PowerOutage implements Comparable<PowerOutage>{

	private int id;
    private Nerc nerc;
    private LocalDateTime inizioBlackOut;
    private LocalDateTime fineBlackOut;
    private int clientiColpiti;
    private long durataBlackOut;//Modella X ore
    private int anno; //Modella Y anni
	/**
	 * @param id
	 * @param nerc
	 * @param inizioBlackOut
	 * @param fineBlackOut
	 * @param clientiColpiti
	 * @param durataBlackOut
	 * @param anni
	 */
	public PowerOutage(int id, Nerc nerc, LocalDateTime inizioBlackOut, LocalDateTime fineBlackOut, int clientiColpiti) {
		super();
		this.id = id;
		this.nerc = nerc;
		this.inizioBlackOut = inizioBlackOut;
		this.fineBlackOut = fineBlackOut;
		this.clientiColpiti = clientiColpiti;
		
		//ChronoUnit estrapola l'unità interessata! (dobbiamo ottenere un intervallo)
	    LocalDateTime ore = LocalDateTime.from(inizioBlackOut);
	    this.durataBlackOut = ore.until(fineBlackOut, ChronoUnit.HOURS);
		//non è un intervallo usiamo un getYear()
	    this.anno = fineBlackOut.getYear();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Nerc getNerc() {
		return nerc;
	}
	public void setNerc(Nerc nerc) {
		this.nerc = nerc;
	}
	public LocalDateTime getInizioBlackOut() {
		return inizioBlackOut;
	}
	public void setInizioBlackOut(LocalDateTime inizioBlackOut) {
		this.inizioBlackOut = inizioBlackOut;
	}
	public LocalDateTime getFineBlackOut() {
		return fineBlackOut;
	}
	public void setFineBlackOut(LocalDateTime fineBlackOut) {
		this.fineBlackOut = fineBlackOut;
	}
	public int getClientiColpiti() {
		return clientiColpiti;
	}
	public void setClientiColpiti(int clientiColpiti) {
		this.clientiColpiti = clientiColpiti;
	}
	public long getDurataBlackOut() {
		return durataBlackOut;
	}
	public void setDurataBlackOut(long durataBlackOut) {
		this.durataBlackOut = durataBlackOut;
	}
	public int getAnno() {
		return anno;
	}
	public void setAnno(int anno) {
		this.anno = anno;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PowerOutage other = (PowerOutage) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	//TODO compareTo e toString !
	//ordino per inizio Black Out crescente
	public int compareTo(PowerOutage p) {
		return this.getInizioBlackOut().compareTo(p.getInizioBlackOut());
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(id);
		return builder.toString();
	}
    
    
    
    
}
