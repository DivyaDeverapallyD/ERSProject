package com.ers.Service;

import com.ers.Dao.UserDao;
import com.ers.Model.User;
import com.ers.Util.AES;

public class UserService {
	
	UserDao userDao= new UserDao();
	
	public UserService(UserDao fakeUserDao) {
		// TODO Auto-generated constructor stub
	}

	public UserService() {
		// TODO Auto-generated constructor stub
	}

	public boolean verifyLoginCredentials(String uName, String Pwd)
	{
		return userDao.validateUser(uName, Pwd);
		
		//return false;
		
	}
	
	public User findUser(String uName,String pwd)
	{
		
		System.out.println("Before ending pwd " + pwd);
		//String encPwd= AES.encrypt(uName, "ers");
		
		System.out.println("Emcoded pwd"+ pwd);
		
		return userDao.findByUser(uName,pwd);
	}
	
	public User registerUser(User user)
	{
		return userDao.save(user);
	}

}
