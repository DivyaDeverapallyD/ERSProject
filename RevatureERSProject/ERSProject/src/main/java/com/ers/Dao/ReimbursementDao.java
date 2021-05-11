package com.ers.Dao;


import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ers.Model.Reimbursement;
import com.ers.Model.User;
import com.ers.Util.ConnectionUtil;

public class ReimbursementDao implements DAO<Reimbursement, Integer> {

	public final static Logger log = Logger.getLogger(UserDao.class);

	public List<Reimbursement> findAll() {
		// TODO Auto-generated method stub

		List<Reimbursement> reimb_List = new ArrayList<Reimbursement>();
		
		String sql = "select ers_reimbursement.reimb_id, ers_reimbursement.reimb_amount ,ers_reimbursement.reimb_submitted ,ers_reimbursement.reimb_resolved,ers_reimbursement.reimb_description ,ers_users.ers_username,ers_reimbursement.reimb_resolver, ers_reimbursement.reimb_status_id ,ers_reimbursement.reimb_type_id , ers_reimbursement.reimb_rec from  ers_reimbursement left join ers_users on\r\n"
				+ "				 ers_reimbursement.reimb_author = ers_users.ers_users_id order by REIMB_ID";

		try {
			Connection con = ConnectionUtil.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet res = ps.executeQuery();
			while (res.next()) {
				reimb_List.add(new Reimbursement(res.getInt(1), res.getInt(2), res.getDate(3), res.getDate(4),
						res.getString(5), res.getString(6), res.getInt(7), res.getInt(8), res.getInt(9),res.getString(10)));
			}

		}

		catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reimb_List;
	}

	public Reimbursement findById(Integer id) {
		// TODO Auto-generated method stub
		
		Reimbursement reimb = null;
	
		try {

			Connection con = ConnectionUtil.getConnection();
			String sql = "SELECT * FROM ERS_REIMBURSEMENT WHERE REIMB_ID = ?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,  id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {

				reimb = new Reimbursement();
				reimb.setReimb_id(rs.getInt(1));
				reimb.setReimb_amount(rs.getInt(2));
				reimb.setReimb_submitted(rs.getTimestamp(3));
				reimb.setReimb_resolved(rs.getTimestamp(4));
				reimb.setReimb_description(rs.getString(5));
				reimb.setReimb_author(rs.getInt(6));
				reimb.setReimb_resolver(rs.getInt(7));
				reimb.setReimb_status_id(rs.getInt(8));
				reimb.setReimb_type_id(rs.getInt(9));	
		
			}
		}
		
		catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// TODO Auto-generated catch block e.printStackTrace(); }
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		
		return reimb;
	}

	public Reimbursement save(Reimbursement obj) { // TODO Auto-generated methodstub

		// TODO Auto-generated method stub
System.out.println("Inside remim save dao");
System.out.println(obj);
		try {

			Connection con = ConnectionUtil.getConnection();
			String SQL = "{? = call INSERT_ERS_REIMBURSEMENT(?,?,?,?,?,?,?)}";
			String[] keyNames = { "reimb_id" };
			CallableStatement cs = con.prepareCall(SQL);
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setInt(2, obj.getReimb_amount());
			cs.setString(3, obj.getReimb_description());
			cs.setInt(4, obj.getReimb_author());
			cs.setInt(5, obj.getReimb_resolver());
			cs.setInt(6, obj.getReimb_status_id());
			cs.setInt(7, obj.getReimb_type_id());
			cs.setString(8, obj.getReimb_rec());

			cs.execute();
			obj.setReimb_id(cs.getInt(1));
			log.info(obj);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// TODO Auto-generated catch block e.printStackTrace(); }
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return obj;
	}
	// return null; }

	public List<Reimbursement> retrieveReimbursements(User user) {
		List<Reimbursement> list = new ArrayList<Reimbursement>();

		return list;

	}

	public Reimbursement update(Reimbursement obj) {	
		//log.trace("inside update");
	
	try{
		Connection conn = ConnectionUtil.getConnection();
		
		String sql = "UPDATE ERS_REIMBURSEMENT SET REIMB_STATUS_ID = ?,REIMB_RESOLVED = current_timestamp WHERE REIMB_ID = ?";
		
		log.trace("inside update, before prepared statement");
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, obj.getReimb_status_id());
		ps.setInt(2, obj.getReimb_id());
		ps.executeUpdate();
	
		log.trace("update executed");
		
		
	} catch (SQLException e) {
		
		e.printStackTrace();
	
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return null;
	}

	public void delete(Reimbursement obj) {
		// TODO Auto-generated method stub

	}

	
public List<Reimbursement> findReims(Integer id) {
		
		List<Reimbursement> reims = new ArrayList<Reimbursement>();
		
		try{
			Connection conn = ConnectionUtil.getConnection();
			
			String sql = "SELECT * FROM ERS_REIMBURSEMENT WHERE reimb_author = ? ORDER BY reimb_id";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,  id);
			ResultSet rs = ps.executeQuery();
			
			
			
			/*
			 * private int reimb_id; private int reimb_amount; private Timestamp
			 * reimb_submitted; private Timestamp reimb_resolved; private String
			 * reimb_description; private int reimb_author; private int reimb_resolver;
			 * private int reimb_status_id; private int reimb_type_id;
			 */
			
			while(rs.next()) {

				Reimbursement r = new Reimbursement();
				r.setReimb_id(rs.getInt(1));
				r.setReimb_amount(rs.getInt(2));
				r.setReimb_submitted(rs.getTimestamp(3));
				r.setReimb_resolved(rs.getTimestamp(4));
				r.setReimb_description(rs.getString(5));
				r.setReimb_author(rs.getInt(6));
				r.setReimb_resolver(rs.getInt(7));
				r.setReimb_status_id(rs.getInt(8));
				r.setReimb_type_id(rs.getInt(9));
				reims.add(r);

			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return reims;
		
		
	}




public List<Reimbursement> findByStatus(Integer id) {
	
	List<Reimbursement> reims = new ArrayList<Reimbursement>();
	
	try{
		Connection conn = ConnectionUtil.getConnection();
		//String sql = "SELECT * FROM ERS_REIMBURSEMENT WHERE reimb_status_id = ?";
		
		String sql = "select ers_reimbursement.reimb_id, ers_reimbursement.reimb_amount ,ers_reimbursement.reimb_submitted ,ers_reimbursement.reimb_resolved,ers_reimbursement.reimb_description ,ers_users.ers_username,ers_reimbursement.reimb_resolver, ers_reimbursement.reimb_status_id ,ers_reimbursement.reimb_type_id , ers_reimbursement.reimb_rec from  ers_reimbursement left join ers_users on\r\n"
				+ "				 ers_reimbursement.reimb_author = ers_users.ers_users_id where ers_reimbursement.reimb_status_id = ? order by REIMB_ID";		
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1,  id);
		ResultSet res = ps.executeQuery();
		
		
		
		/*
		 * private int reimb_id; private int reimb_amount; private Timestamp
		 * reimb_submitted; private Timestamp reimb_resolved; private String
		 * reimb_description; private int reimb_author; private int reimb_resolver;
		 * private int reimb_status_id; private int reimb_type_id;
		 */
		
		while (res.next()) {
			reims.add(new Reimbursement(res.getInt(1), res.getInt(2), res.getDate(3), res.getDate(4),
					res.getString(5), res.getString(6), res.getInt(7), res.getInt(8), res.getInt(9),res.getString(10)));
		}
		
	} catch (SQLException e) {
		
		e.printStackTrace();
	
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return reims;
	
	
}

	
	
	/*
	 * public List<Reimbursement> findAll() { // TODO Auto-generated method stub
	 * return null; }
	 */

	/*
	 * public Reimbursement save(Reimbursement obj) { // TODO Auto-generated method
	 * stub return null; }
	 */

}
