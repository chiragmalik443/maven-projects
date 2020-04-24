package Faculty;
import java.sql.*;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.util.ArrayList;
import Faculty.FacultyDBObj;

public class FacultyDBMethods{
	public String DBUser;
	public String DBPswd;
	public String DBUrl;
	
	public FacultyDBMethods(){ }
	public FacultyDBMethods(String inDBUser, String inDBPswd, String inDBUrl ){
		DBUser = inDBUser ;
		DBPswd = inDBPswd;
		DBUrl  = inDBUrl;
	}
	public void initializeFacultyDBObj(FacultyDBObj inFacultyDBObj ){
		inFacultyDBObj.fac_id =  "";
		inFacultyDBObj.fac_f_name = "";
		inFacultyDBObj.fac_m_name = "";
		inFacultyDBObj.fac_l_name = "";
		inFacultyDBObj.org_id = "";
		inFacultyDBObj.level_id = "";
		inFacultyDBObj.dept_id= "";
		inFacultyDBObj.dob= "";
		inFacultyDBObj.dojoin= "";
		inFacultyDBObj.address_1= "";
		inFacultyDBObj.address_2= "";
		inFacultyDBObj.city= "";
		inFacultyDBObj.state= "";
		inFacultyDBObj.nationality= "";
	}
	public FacultyDBObj getRecordByPrimaryKey(String inFacId, String inFacFName){
		FacultyDBObj  facultyDBObj = new FacultyDBObj();
		java.sql.Date date;
		try{
			System.out.println("DBUser=="+DBUser+",DBPswd=="+DBPswd+",DBUrl=="+DBUrl);
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn= DriverManager.getConnection(DBUrl,DBUser,DBPswd);
			Statement stmt = conn.createStatement();
			String lSqlString =	"select * from PEOPLE_FACULTY  ";
			lSqlString = lSqlString + "where fac_id='"+inFacId+"' ";
			if( inFacFName != null && inFacFName.length() > 0)
			lSqlString = lSqlString + "and fac_f_name='"+inFacFName+"' ";
			ResultSet rs  = null;
			rs  = stmt.executeQuery(lSqlString);
			System.out.println("lSqlString====trtrt==within getRecordByPrimaryKey== "+lSqlString);
			if( rs.next()){
				System.out.println("fffff==="+rs.getString("fac_id"));
				facultyDBObj.fac_id =  rs.getString("fac_id");
				facultyDBObj.fac_f_name = rs.getString("fac_f_name");
				facultyDBObj.fac_m_name = rs.getString("fac_m_name");
				facultyDBObj.fac_l_name = rs.getString("fac_l_name");
				facultyDBObj.org_id = rs.getString("org_id");
				facultyDBObj.level_id = rs.getString("level_id");
				facultyDBObj.dept_id = rs.getString("dept_id");
				date=rs.getDate("dob");
				facultyDBObj.dob = date.toString();
				date=rs.getDate("dojoin");
				facultyDBObj.dojoin = date.toString();
				facultyDBObj.address_1 = rs.getString("address_1");
				facultyDBObj.address_2 = rs.getString("address_2");
				facultyDBObj.city = rs.getString("city");
				facultyDBObj.state = rs.getString("state");
				facultyDBObj.nationality = rs.getString("nationality");
				System.out.println("fffff==="+rs.getString("fac_id"));
			}
			else{
				System.out.println("Record Does N't exist");
				// initializeFacultyDBObj(FacultyDBObj);
			}
			System.out.println("fffff====="+facultyDBObj.fac_id);
		}
		catch(Exception  ex){
			ex.printStackTrace();
		}
		return facultyDBObj;
	}
	public int updateFacultyByPrimaryKey(FacultyDBObj inFacultyDBObj){
		int recupd = 0; 
		String lQuery = "";
		lQuery = lQuery +"update PEOPLE_FACULTY set fac_m_name='"+inFacultyDBObj.fac_m_name+"'  "; 
		lQuery = lQuery +" , fac_l_name='"+inFacultyDBObj.fac_l_name+"' ";
		lQuery = lQuery +" , org_id='"+inFacultyDBObj.org_id+"' ";
		lQuery = lQuery +" , level_id='"+inFacultyDBObj.level_id+"' ";
		lQuery = lQuery +" , dept_id='"+inFacultyDBObj.dept_id+"' ";
		lQuery = lQuery +" , dob=to_date('"+inFacultyDBObj.dob+"', 'yyyy-mm-dd') ";
		lQuery = lQuery +" , dojoin=to_date('"+inFacultyDBObj.dojoin+"', 'yyyy-mm-dd') ";
		lQuery = lQuery +" , address_1='"+inFacultyDBObj.address_1+"' ";
		lQuery = lQuery +" , address_2='"+inFacultyDBObj.address_2+"' ";
		lQuery = lQuery +" , city='"+inFacultyDBObj.city+"' ";
		lQuery = lQuery +" , state='"+inFacultyDBObj.state+"' ";
		lQuery = lQuery +" , nationality='"+inFacultyDBObj.nationality+"' ";
		lQuery = lQuery + "where fac_id='"+inFacultyDBObj.fac_id+"' ";
		lQuery = lQuery + "and fac_f_name='"+inFacultyDBObj.fac_f_name+"' ";
		System.out.println("lSqlString===:"+lQuery);
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn= DriverManager.getConnection(DBUrl,DBUser,DBPswd);
			Statement stmt = conn.createStatement();
			recupd  = stmt.executeUpdate(lQuery);
		}
		catch(Exception  ex){
			ex.printStackTrace();
		}
		return recupd;
	}
	public FacultyDBObj populateFacultyDBObjFromReq(HttpServletRequest inReq){
		FacultyDBObj  facultyDBObj = new FacultyDBObj();
		facultyDBObj.fac_id   =  (String)inReq.getParameter("fac_id");
		facultyDBObj.fac_f_name = (String)inReq.getParameter("fac_f_name"); 
		facultyDBObj.fac_m_name  = (String)inReq.getParameter("fac_m_name");
		facultyDBObj.fac_l_name  = (String)inReq.getParameter("fac_l_name");
		facultyDBObj.org_id = (String)inReq.getParameter("org_id");
		facultyDBObj.level_id = (String)inReq.getParameter("level_id");
		facultyDBObj.dept_id = (String)inReq.getParameter("dept_id");
		facultyDBObj.dob = (String)inReq.getParameter("dob");
		facultyDBObj.dojoin = (String)inReq.getParameter("dojoin");
		facultyDBObj.address_1 = (String)inReq.getParameter("address_1");
		facultyDBObj.address_2 = (String)inReq.getParameter("address_2");
		facultyDBObj.city = (String)inReq.getParameter("city");
		facultyDBObj.state = (String)inReq.getParameter("state");
		facultyDBObj.nationality = (String)inReq.getParameter("nationality");
		return facultyDBObj;
	}
	public ArrayList selectFacultyByCriteria(String inCriteria){
		ArrayList facultyList = new ArrayList();
		java.util.Date date=new java.util.Date();
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn= DriverManager.getConnection(DBUrl,DBUser,DBPswd);
			Statement stmt = conn.createStatement();
			String lSqlString ="select * from PEOPLE_FACULTY";
			if( inCriteria != null && inCriteria.length() > 0 ){
				lSqlString = lSqlString +" "+inCriteria ;
			}
			ResultSet rs  = null;
			lSqlString = lSqlString +" ORDER BY fac_id" ;
			rs  = stmt.executeQuery(lSqlString);
			while( rs.next()){
				FacultyDBObj  facultyDBObj = new FacultyDBObj();
				facultyDBObj.fac_id =  rs.getString("fac_id");
				facultyDBObj.fac_f_name = rs.getString("fac_f_name");
				facultyDBObj.fac_l_name = rs.getString("fac_l_name");
				facultyDBObj.level_id = rs.getString("level_id");
				date=rs.getDate("dob");
				if(date!=null)
					facultyDBObj.dob = date.toString();
				facultyDBObj.fac_m_name = rs.getString("fac_m_name");
				facultyDBObj.org_id = rs.getString("org_id");
				facultyDBObj.dept_id  = rs.getString("dept_id");
				facultyDBObj.dojoin = rs.getString("dojoin");
				facultyDBObj.address_1 = rs.getString("address_1");
				facultyDBObj.address_2  = rs.getString("address_2");
				facultyDBObj.city  = rs.getString("city");
				facultyDBObj.state  = rs.getString("state");
				facultyDBObj.nationality  = rs.getString("nationality");
				facultyList.add(facultyDBObj);
			}
		}
		catch(Exception  ex){
			ex.printStackTrace();
		}
		return facultyList;
	}
	public int insertFaculty(FacultyDBObj inFacultyDBObj) throws ParseException{
		int recupd = 0; 
		//Date date = new ;
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date myDate = formatter.parse(inFacultyDBObj.dob);
		java.util.Date join = formatter.parse(inFacultyDBObj.dojoin);
		Date sqlDate = new Date(myDate.getTime());
		Date joinDate = new Date(join.getTime());

		String lQuery = "";
		lQuery = lQuery +"insert into PEOPLE_FACULTY  values ( ";
		lQuery = lQuery +" '"+inFacultyDBObj.fac_id+"'  ";
		lQuery = lQuery +" , '"+inFacultyDBObj.fac_f_name+"'  ";
		lQuery = lQuery +" , '"+inFacultyDBObj.fac_m_name+"'  "; 
		lQuery = lQuery +" , '"+inFacultyDBObj.fac_l_name+"' ";
		lQuery = lQuery +" , '"+inFacultyDBObj.org_id+"' ";
		lQuery = lQuery +" , '"+inFacultyDBObj.level_id+"' ";
		lQuery = lQuery +" , '"+inFacultyDBObj.dept_id+"' ";
		//lQuery = lQuery +" , to_date('"+inFacultyDBObj.dob+"', 'yyyy-mm-dd')";
		lQuery = lQuery +" , '"+sqlDate+"' ";
		//lQuery = lQuery +" , to_date('"+inFacultyDBObj.dojoin+"','yyyy-mm-dd')";
		lQuery = lQuery +" , '"+joinDate+"' ";
		lQuery = lQuery +" , '"+inFacultyDBObj.address_1+"' ";
		lQuery = lQuery +" , '"+inFacultyDBObj.address_2+"' ";
		lQuery = lQuery +" , '"+inFacultyDBObj.city+"' ";
		lQuery = lQuery +" , '"+inFacultyDBObj.state+"' ";
		lQuery = lQuery +" , '"+inFacultyDBObj.nationality+"' ";
		lQuery = lQuery + " )";
	    System.out.println("lSqlString===:"+lQuery);
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn= DriverManager.getConnection(DBUrl,DBUser,DBPswd);
			Statement stmt = conn.createStatement();
			recupd  = stmt.executeUpdate(lQuery);
		}
		catch(Exception  ex){
			ex.printStackTrace();
		}
		return recupd;
	}
	public void deleteFaculty(String inFacId){
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn= DriverManager.getConnection(DBUrl,DBUser,DBPswd);
			Statement stmt = conn.createStatement();
			String lQuery = "";
			lQuery = lQuery +"delete from PEOPLE_FACULTY "; 
			lQuery = lQuery +" where fac_id='"+ inFacId +"' ";
			System.out.println("lSqlString===:"+lQuery);
			stmt.executeQuery(lQuery);
		}
		catch(Exception  ex){
			ex.printStackTrace();
		}
	}
}
	