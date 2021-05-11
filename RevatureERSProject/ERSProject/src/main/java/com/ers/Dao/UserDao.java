package com.ers.Dao;

import java.io.IOException;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ers.Model.User;
import com.ers.Util.AES;
import com.ers.Util.ConnectionUtil;

public class UserDao implements DAO<User, Integer> {

	public final static Logger log = Logger.getLogger(UserDao.class);

	// private static final String URL =
	// "jdbc:postgresql://database-1.cvyq3mlvbt7u.us-east-2.rds.amazonaws.com:5432/erssample";
	// private static final String username = "ersdbuser";
	// private static final String password = "ersdbpassword";

	
	public List<User> findAll() {

		// TODO Auto-generated method stub

		List<User> usersList = new ArrayList<User>();
		String sql = "select * from ERS_USERS";

		try {
			Connection con = ConnectionUtil.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet res = ps.executeQuery();
			while (res.next()) {

				usersList.add(new User(res.getInt(1), res.getString(2), res.getString(3), res.getString(4),
						res.getString(5), res.getString(6), res.getInt(7)));
			}

		}

		catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usersList;
	}
	  
	 
	public User findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public User save(User user) { // TODO Auto-generated method stub

		try {

			Connection con = ConnectionUtil.getConnection();
			String SQL = "{? = call INSERT_ERS_USERS(?,?,?,?,?,?)}";
			String[] keyNames = { "ers_users_id" };
			CallableStatement cs = con.prepareCall(SQL);
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, user.getUserName());
			cs.setString(3, user.getUserPwd());
			cs.setString(4, user.getUserFirstName());
			cs.setString(5, user.getUserLastName());
			cs.setString(6, user.getUserEmail());
			cs.setInt(7, user.getUserRoleId());

			cs.execute();
			user.setUserId(cs.getInt(1));
			log.info(user);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
		}
		// TODO Auto-generated catch block e.printStackTrace(); }
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return user;
	}

	public boolean validateUser(String uname, String pwd) {
		// TODO Auto-generated method stub

		
	//	String encPwd= AES.encrypt(pwd, "ers");
		
		System.out.println("invalidate  dao"+ pwd);
		String SQL = "select * from ERS_USERS where ers_username = ?  and ers_password =?";
		User user = new User();
		// no record false
		try {
			Connection con = ConnectionUtil.getConnection();
			// Connection con = ConnectionUtil.getConnection();
			Statement st = con.createStatement();
			// ResultSet rs = st.executeQuery( "SELECT * FROM bankuser;" );
			PreparedStatement pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, uname);
			pstmt.setString(2, pwd);
			ResultSet res = pstmt.executeQuery();

			if (res.next()) {

				return true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} /*
			 * catch (Exception e) { // TODO Auto-generated catch block e.printStackTrace();
			 * }
			 */ catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}
	
	
public User findByUser(String usr,String pwd) {
		
	System.out.println("Inside user Dao Findby user"+ usr+"-----"+ pwd);
		User user = null;
		
		try {
			Connection con = ConnectionUtil.getConnection();
			
			String sql = "SELECT * FROM ERS_USERS WHERE ERS_USERNAME = ? and ERS_PASSWORD = ?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,  usr);
			ps.setString(2,  pwd);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				user = new User();
				user.setUserId(rs.getInt(1));
				user.setUserName(usr);
				user.setUserPwd(rs.getString(3));
				user.setUserFirstName(rs.getString(4));
				user.setUserLastName(rs.getString(5));	
				user.setUserEmail(rs.getString(6));
				user.setUserRoleId(rs.getInt(7));
				
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
		
		return user;
	}
	
	
	
	

	public User update(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(User user) {
		// TODO Auto-generated method stub

	}



	



}
