package it.polito.tdp.poweroutages.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.model.Nerc;
import it.polito.tdp.poweroutages.model.NercIdMap;
import it.polito.tdp.poweroutages.model.PowerOutage;

public class PowerOutageDAO {
	
	public List<Nerc> getNercList() {

		String sql = "SELECT id, value FROM nerc";
		List<Nerc> nercList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Nerc n = new Nerc(res.getInt("id"), res.getString("value"));
				nercList.add(n);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return nercList;
	}
	/**
	 * Mi da una lista di BlackOut partendo da una NERC 
	 *  userò una NercIdMap  **
	 */
	public List<PowerOutage> getBlackOutList (NercIdMap nercIdMap){
		String sql="SELECT id,nerc_id, date_event_began, date_event_finished, customers_affected FROM poweroutages";
		List <PowerOutage> pw = new ArrayList<>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st= conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				Nerc n = nercIdMap.get(rs.getInt("id_nerc"));
				if(n== null) {
					System.err.println("Il DB è inconsistente manca l'oggetto Nerc corrispondente!");
					
				}else {
					PowerOutage p = new PowerOutage(rs.getInt("id"), n,
							rs.getTimestamp("date_event_began").toLocalDateTime(),
							rs.getTimestamp("date_event_finished").toLocalDateTime(),
							rs.getInt("customers_affected"));
					
					pw.add(p);
				}
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return pw;
		
		
	}
	
	

}
