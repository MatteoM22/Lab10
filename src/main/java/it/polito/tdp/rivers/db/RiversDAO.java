package it.polito.tdp.rivers.db;

import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.rivers.model.Flow;
import it.polito.tdp.rivers.model.MediaEConteggio;
import it.polito.tdp.rivers.model.River;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class RiversDAO {

	public List<River> getAllRivers() {
		
		final String sql = "SELECT id, name FROM river";

		List<River> rivers = new LinkedList<River>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				rivers.add(new River(res.getInt("id"), res.getString("name")));
			}

			conn.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return rivers;
	}
	
	public MediaEConteggio getAllMediaEConteggio(int idRiver) {
		
		final String sql = "select river, COUNT(flow) as C, AVG(flow) as A "
				+ "from flow "
				+ "where river=?";
		
		MediaEConteggio m = null;

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, idRiver);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				m = new MediaEConteggio(res.getInt("river"), res.getInt("C"), res.getFloat("A"));
			}

			conn.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}
		
		return m;

	}
	
public List<String> getAllDate(int idRiver) {
		
		final String sql = "select day "
				+ "from flow "
				+ "where river=? "
				+ "order by day ASC";
		
		List<String> date= new LinkedList<String>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, idRiver);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				date.add(res.getString("day"));
			}

			conn.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}
		
		return date;

	}

public List<Flow> getFlow(int idRiver) {
	
	final String sql = "select day, flow, river "
			+ "from flow "
			+ "where river=?";

	List<Flow> flows = new LinkedList<Flow>();

	try {
		Connection conn = DBConnect.getConnection();
		PreparedStatement st = conn.prepareStatement(sql);
		st.setInt(1, idRiver);
		ResultSet res = st.executeQuery();

		while (res.next()) {
			flows.add(new Flow(res.getDate("day").toLocalDate(), res.getDouble("flow"), res.getInt("river")));
		}

		conn.close();
		
	} catch (SQLException e) {
		//e.printStackTrace();
		throw new RuntimeException("SQL Error");
	}

	return flows;
}
	
}
