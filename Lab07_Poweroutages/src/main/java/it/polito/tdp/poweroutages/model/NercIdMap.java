package it.polito.tdp.poweroutages.model;

import java.util.HashMap;
import java.util.Map;

public class NercIdMap {
  private Map <Integer, Nerc> map;
  
  public NercIdMap() {
	  map = new HashMap<Integer, Nerc>();
  }
  //metodo per accedere a un valore Nerc inserendo un id_nerc
  public Nerc get(int id) {
	  return map.get(id);
  }
//metodo per accedere a un valore Nerc inserendo un oggetto Nerc
public Nerc get(Nerc nerc) {
	
	Nerc old = map.get(nerc.getId());
	if(old == null) {
		map.put(nerc.getId(), nerc);
		return nerc;	
	}else {
		return old;
	}
}


public void put(int id, Nerc oggetto) {
	map.put(id, oggetto);
}


}