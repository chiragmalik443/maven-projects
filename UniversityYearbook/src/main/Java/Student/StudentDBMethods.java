package Student;
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
import Student.StudentDBObj;

public class StudentDBMethods{
	public String DBUser;
	public String DBPswd;
	public String DBUrl;
	
	public StudentDBMethods(){ }
	public StudentDBMethods(String inDBUser, String inDBPswd, String inDBUrl ){
		DBUser = inDBUser ;
		DBPswd = inDBPswd;
		DBUrl  = inDBUrl;
	}
	public void initializeStudentDBObj(StudentDBObj inStudentDBObj ){
		inStudentDBObj.stu_id =  "";
		inStudentDBObj.stu_f_name = "";
		inStudentDBObj.stu_m_name = "";
		inStudentDBObj.stu_l_name = "";
		inStudentDBObj.org_id = "";
		inStudentDBObj.course_id = "";
		inStudentDBObj.dept_id= "";
		inStudentDBObj.dob= "";
		inStudentDBObj.address_1= "";
		inStudentDBObj.address_2= "";
		inStudentDBObj.city= "";
		inStudentDBObj.state= "";
		inStudentDBObj.nationality= "";
	}
	public StudentDBObj getRecordByPrimaryKey(String inStuId, String inStuFName){
		StudentDBObj  StudentDBObj = new StudentDBObj();
		java.sql.Date date;
		try{
			System.out.println("DBUser=="+DBUser+",DBPswd=="+DBPswd+",DBUrl=="+DBUrl);
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn= DriverManager.getConnection(DBUrl,DBUser,DBPswd);
			Statement stmt = conn.createStatement();
			String lSqlString =	"select * from PEOPLE_STUDENT  ";
			lSqlString = lSqlString + "where stu_id='"+inStuId+"' ";
			if( inStuFName != null && inStuFName.length() > 0)
			lSqlString = lSqlString + "and Stu_f_name='"+inStuFName+"' ";
			ResultSet rs  = null;
			rs  = stmt.executeQuery(lSqlString);
			System.out.println("lSqlString====trtrt==within getRecordByPrimaryKey== "+lSqlString);
			if( rs.next()){
				System.out.println("fffff==="+rs.getString("stu_id"));
				StudentDBObj.stu_id =  rs.getString("stu_id");
				StudentDBObj.stu_f_name = rs.getString("stu_f_name");
				StudentDBObj.stu_m_name = rs.getString("stu_m_name");
				StudentDBObj.stu_l_name = rs.getString("stu_l_name");
				StudentDBObj.org_id = rs.getString("org_id");
				StudentDBObj.course_id = rs.getString("course_id");
				StudentDBObj.dept_id = rs.getString("dept_id");
				date=rs.getDate("dob");
				StudentDBObj.dob = date.toString();
				StudentDBObj.address_1 = rs.getString("address_1");
				StudentDBObj.address_2 = rs.getString("address_2");
				StudentDBObj.city = rs.getString("city");
				StudentDBObj.state = rs.getString("state");
				StudentDBObj.nationality = rs.getString("nationality");
				System.out.println("fffff==="+rs.getString("stu_id"));
			}
			else{
				System.out.println("Record Does N't exist");
			}
			System.out.println("fffff====="+StudentDBObj.stu_id);
		}
		catch(Exception  ex){
			ex.printStackTrace();
		}
		return StudentDBObj;
	}
	public int updateStudentByPrimaryKey(StudentDBObj inStudentDBObj){
		int recupd = 0; 
		String lQuery = "";
		lQuery = lQuery +"update PEOPLE_STUDENT set stu_m_name='"+inStudentDBObj.stu_m_name+"'  "; 
		lQuery = lQuery +" , stu_l_name='"+inStudentDBObj.stu_l_name+"' ";
		lQuery = lQuery +" , org_id='"+inStudentDBObj.org_id+"' ";
		lQuery = lQuery +" , course_id='"+inStudentDBObj.course_id+"' ";
		lQuery = lQuery +" , dept_id='"+inStudentDBObj.dept_id+"' ";
		lQuery = lQuery +" , dob=to_date('"+inStudentDBObj.dob+"', 'yyyy-mm-dd') ";
		lQuery = lQuery +" , address_1='"+inStudentDBObj.address_1+"' ";
		lQuery = lQuery +" , address_2='"+inStudentDBObj.address_2+"' ";
		lQuery = lQuery +" , city='"+inStudentDBObj.city+"' ";
		lQuery = lQuery +" , state='"+inStudentDBObj.state+"' ";
		lQuery = lQuery +" , nationality='"+inStudentDBObj.nationality+"' ";
		lQuery = lQuery + "where fac_id='"+inStudentDBObj.stu_id+"' ";
		lQuery = lQuery + "and fac_f_name='"+inStudentDBObj.stu_f_name+"' ";
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
	public StudentDBObj populateStudentDBObjFromReq(HttpServletRequest inReq){
		StudentDBObj  studentDBObj = new StudentDBObj();
		studentDBObj.stu_id   =  (String)inReq.getParameter("stu_id");
		studentDBObj.stu_f_name = (String)inReq.getParameter("stu_f_name"); 
		studentDBObj.stu_m_name  = (String)inReq.getParameter("stu_m_name");
		studentDBObj.stu_l_name  = (String)inReq.getParameter("stu_l_name");
		studentDBObj.org_id = (String)inReq.getParameter("org_id");
		studentDBObj.course_id = (String)inReq.getParameter("course_id");
		studentDBObj.dept_id = (String)inReq.getParameter("dept_id");
		studentDBObj.dob = (String)inReq.getParameter("dob");
		studentDBObj.address_1 = (String)inReq.getParameter("address_1");
		studentDBObj.address_2 = (String)inReq.getParameter("address_2");
		studentDBObj.city = (String)inReq.getParameter("city");
		studentDBObj.state = (String)inReq.getParameter("state");
		studentDBObj.nationality = (String)inReq.getParameter("nationality");
		return studentDBObj;
	}
	public ArrayList selectStudentByCriteria(String inCriteria){
		ArrayList studentList = new ArrayList();
		java.util.Date date=new java.util.Date();
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn= DriverManager.getConnection(DBUrl,DBUser,DBPswd);
			Statement stmt = conn.createStatement();
			String lSqlString ="select * from PEOPLE_STUDENT";
			if( inCriteria != null && inCriteria.length() > 0 ){
				lSqlString = lSqlString +" "+inCriteria ;
			}
			ResultSet rs  = null;
			lSqlString = lSqlString +" ORDER BY stu_id" ;
			rs  = stmt.executeQuery(lSqlString);
			while( rs.next()){
				StudentDBObj  studentDBObj = new StudentDBObj();
				studentDBObj.stu_id =  rs.getString("stu_id");
				studentDBObj.stu_f_name = rs.getString("stu_f_name");
				studentDBObj.stu_l_name = rs.getString("stu_l_name");
				studentDBObj.course_id = rs.getString("course_id");
				date=rs.getDate("dob");
				if(date!=null)
					studentDBObj.dob = date.toString();
				studentDBObj.stu_m_name = rs.getString("stu_m_name");
				studentDBObj.org_id = rs.getString("org_id");
				studentDBObj.dept_id  = rs.getString("dept_id");
				studentDBObj.address_1 = rs.getString("address_1");
				studentDBObj.address_2  = rs.getString("address_2");
				studentDBObj.city  = rs.getString("city");
				studentDBObj.state  = rs.getString("state");
				studentDBObj.nationality  = rs.getString("nationality");
				studentList.add(studentDBObj);
			}
		}
		catch(Exception  ex){
			ex.printStackTrace();
		}
		return studentList;
	}
	public int insertStudent(StudentDBObj inStudentDBObj) throws ParseException{
		int recupd = 0; 
		//Date date = new ;
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date myDate = formatter.parse(inStudentDBObj.dob);
		Date sqlDate = new Date(myDate.getTime());

		String lQuery = "";
		lQuery = lQuery +"insert into PEOPLE_STUDENT  values ( ";
		lQuery = lQuery +" '"+inStudentDBObj.stu_id+"'  ";
		lQuery = lQuery +" , '"+inStudentDBObj.stu_f_name+"'  ";
		lQuery = lQuery +" , '"+inStudentDBObj.stu_m_name+"'  "; 
		lQuery = lQuery +" , '"+inStudentDBObj.stu_l_name+"' ";
		lQuery = lQuery +" , '"+inStudentDBObj.org_id+"' ";
		lQuery = lQuery +" , '"+inStudentDBObj.course_id+"' ";
		lQuery = lQuery +" , '"+inStudentDBObj.dept_id+"' ";
		lQuery = lQuery +" , '"+sqlDate+"' ";
		lQuery = lQuery +" , '"+inStudentDBObj.address_1+"' ";
		lQuery = lQuery +" , '"+inStudentDBObj.address_2+"' ";
		lQuery = lQuery +" , '"+inStudentDBObj.city+"' ";
		lQuery = lQuery +" , '"+inStudentDBObj.state+"' ";
		lQuery = lQuery +" , '"+inStudentDBObj.nationality+"' ";
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
	public void deleteStudent(String inStuId){
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn= DriverManager.getConnection(DBUrl,DBUser,DBPswd);
			Statement stmt = conn.createStatement();
			String lQuery = "";
			lQuery = lQuery +"delete from PEOPLE_STUDENT "; 
			lQuery = lQuery +" where stu_id='"+ inStuId +"' ";
			System.out.println("lSqlString===:"+lQuery);
			stmt.executeQuery(lQuery);
		}
		catch(Exception  ex){
			ex.printStackTrace();
		}
	}
}