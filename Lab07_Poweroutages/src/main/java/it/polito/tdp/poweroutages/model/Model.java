package it.polito.tdp.poweroutages.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	//oggetti utili
	private PowerOutageDAO dao;
	private NercIdMap nercIdMap;
	
	//Li prendi dal dao
	private List <Nerc> nercList;
	private List <PowerOutage> blackOutList;
	
	//Le usi per la ricorsione
	private List<PowerOutage> blackOutValidi;
	private List <PowerOutage> migliore;
    private  int clientiColpiti;

	
	public Model() {
		dao = new PowerOutageDAO();
		nercIdMap = new NercIdMap();
		
	    nercList = dao.getNercList(nercIdMap);
	    System.out.println(nercList);
	    blackOutList = dao.getBlackOutList(nercIdMap);
	    System.out.println(blackOutList);
	    
	}
	
	public List <PowerOutage> getCasiPeggiori( int maxAnni,int maxOre, Nerc nerc){
		
		migliore = new ArrayList<>();
		
		clientiColpiti=0;
		 
		 
		 
		 blackOutValidi = new ArrayList<>();
		 for(PowerOutage evento : blackOutList) {
			 if(evento.getNerc().equals(nerc)) {
				 blackOutValidi.add(evento);
			 }
		 }
		 Collections.sort(blackOutValidi);
		 
		 System.out.println("Numero di black out validi: " + blackOutValidi.size());
		 ricorri(new ArrayList<PowerOutage>(), maxOre, maxAnni);
		 return migliore;
	}
	

	//calcola la somma di clienti colpiti
	public int sommaClientiColpiti(List<PowerOutage> parziale ) {
		int somma= 0;
		for(PowerOutage evento : parziale) {
			somma += evento.getClientiColpiti();
		}
		return somma;
	}
	//calcola la somma delle ore
	public int sommaOreBlackOut(List <PowerOutage> parziale ) {
		int somma = 0;
		for(PowerOutage evento: parziale) {
			somma += evento.getDurataBlackOut();
		}
		return somma;
	}
	//controllo se le ore sono massime 
	public boolean controlloMaxOre(List <PowerOutage> parziale, int maxOre) {
		int temp = sommaOreBlackOut(parziale);
		if(temp> maxOre)
			return false;
		else 
			return true;
	}
	
    public List<Integer> getListaAnni(){
    	Set <Integer> anno = new HashSet<Integer>();
    	for(PowerOutage evento : blackOutList) {
    		anno.add(evento.getAnno());
    	}
    	List<Integer> anni = new ArrayList<Integer>(anno);
    	anni.sort(new Comparator <Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				
				return o2.compareTo(o1);
			}
    		
    	});
    	
    	return anni;
    }
	//controllo anni massimo
    public boolean controlloMaxAnni(List <PowerOutage> parziale, int maxAnni) {
    	if(parziale.size()>=2) {
    		
    		int y1= parziale.get(0).getAnno();
    		int y2= parziale.get(parziale.size()-1).getAnno();
    		
    		if((y2 - y1 + 1)> maxAnni) {
    			return false;
    		}
    	}
    	return true;
    }
	private void ricorri(List<PowerOutage> parziale, int maxOre, int maxAnni) {

		// Aggiorna soluzione
		if(sommaClientiColpiti(parziale)> clientiColpiti) {
			clientiColpiti = sommaClientiColpiti(parziale);
			migliore= new ArrayList<>(parziale);
		}
		
		for(PowerOutage evento : blackOutValidi) {
			
			if(!parziale.contains(evento)) {
				
				parziale.add(evento);
				
				if(controlloMaxOre(parziale, maxOre) && controlloMaxAnni(parziale, maxAnni)) {
					ricorri(parziale, maxOre, maxAnni);
				}
				
				parziale.remove(evento);
			}
			
		}
		
		
	}
	
	public List<Nerc> getNercList() {
		return this.nercList;
	}

	
	
	
}
