package com.ers.Service;

import java.util.List;


import com.ers.Dao.ReimbursementDao;
import com.ers.Model.Reimbursement;
import com.ers.Model.User;

public class ReimbursementService {
	static ReimbursementDao reimD = new ReimbursementDao();

	public ReimbursementService(ReimbursementDao fakeRDao) {
		// TODO Auto-generated constructor stub
	}

	public ReimbursementService() {
		// TODO Auto-generated constructor stub
	}

	public static List<Reimbursement> getAllReimbs() {

		List<Reimbursement> reim = reimD.findAll();

		return reim;

	}

	public static void createReim() {

		List<Reimbursement> reims = reimD.findAll();

	}

	public static Reimbursement updateReim() {

		Reimbursement r = reimD.findById(2);
		reimD.update(r);

		return r;
	}

//test save

//public static Reimbursement saveReim() {
//	
//	Date date = new Date();
//	Timestamp time = new Timestamp(date.getTime());
//	Reimbursement r = new Reimbursement(300, "test", time, time, 1, 2, 2, 3);
//	reimD.save(r);
//	
//	return r;
//}
//
	public static void updateStatus(int rid, int rstat) {

		Reimbursement r = reimD.findById(rid);

		r.setReimb_status_id(rstat);
		System.out.println("Reimb service ---------------------------"+ r.getReimb_status_id());

		reimD.update(r);

	}
}
