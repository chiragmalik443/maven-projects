package TimeManagement;
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
import Employee.EmployeeDBObj;
import TimeManagement.EmpDailyAttendanceDBObj;
import TimeManagement.DateYearMonthDayDBObj;

public class TimeManagementDBMethods{
	public String DBUser;
	public String DBPswd;
	public String DBUrl ;
	
	public TimeManagementDBMethods(){ }
	
	public TimeManagementDBMethods(String inDBUser, String inDBPswd, String inDBUrl ){
		DBUser = inDBUser;
		DBPswd = inDBPswd;
		DBUrl  = inDBUrl;
	}
	public DateYearMonthDayDBObj getCurDateYearMonthDayDBObj(){
		DateYearMonthDayDBObj dateYearMonthDayDBObj = new DateYearMonthDayDBObj();
		GregorianCalendar calendar = new GregorianCalendar();
		String month = Integer.toString((calendar.get(Calendar.MONTH) + 1));
		String day   = Integer.toString(calendar.get(Calendar.DATE));
		String year  = Integer.toString(calendar.get(Calendar.YEAR));
		if( month != null && month.length() < 2 ) month = "0"+month; 
		if( day != null && day.length() < 2 ) day = "0"+day;
		String date = year+"-"+month+"-"+day;

		dateYearMonthDayDBObj.today_date = date;
		dateYearMonthDayDBObj.month = getMonth(calendar.get(Calendar.MONTH));
		dateYearMonthDayDBObj.day = getDay(calendar.get(Calendar.DAY_OF_WEEK));
		dateYearMonthDayDBObj.year = calendar.get(Calendar.YEAR);
		
		System.out.println("YEAR: " + calendar.get(Calendar.YEAR));
		System.out.println("MONTH: " + calendar.get(Calendar.MONTH));
		System.out.println("WEEK_OF_YEAR: " + calendar.get(Calendar.WEEK_OF_YEAR));
		System.out.println("DATE: " + calendar.get(Calendar.DATE));
		System.out.println("DAY_OF_WEEK: " + calendar.get(Calendar.DAY_OF_WEEK));
		return dateYearMonthDayDBObj;
	}
	public String getDay( int day ){
		String strDay= "";
		if(day == 1) strDay = "SUN";
		else if(day == 2) strDay = "MON";
		else if(day == 3) strDay = "TUS";
		else if(day == 4) strDay = "WED";
		else if(day == 5) strDay = "THU";
		else if(day == 6) strDay = "FRI";
		else if(day == 7) strDay = "SAT";
		return strDay;
	}
	public String getMonth( int month ){
		String strMonth = "";
		if(month == 0) strMonth = "JAN";
		else if(month == 1) strMonth = "FEB";
		else if(month == 2 ) strMonth = "MAR";
		else if(month == 3) strMonth = "APR";
		else if(month == 4) strMonth = "MAY";
		else if(month == 5) strMonth = "JUN";
		else if(month == 6) strMonth = "JUL";
		else if(month == 7) strMonth = "AUG";
		else if(month == 8) strMonth = "SEP"; 
		else if(month == 9) strMonth = "OCT";
		else if(month == 10) strMonth = "NOV";
		else if(month == 11) strMonth = "DEC";
		return strMonth;
	}
	public void initializeEmpDailyAttendanceDBObj(EmpDailyAttendanceDBObj inEmpDailyAttendanceDBObj ){
		inEmpDailyAttendanceDBObj.emp_id   =  "";
		inEmpDailyAttendanceDBObj.emp_name = ""; 
		inEmpDailyAttendanceDBObj.today_date  = "";
		inEmpDailyAttendanceDBObj.month  = "";
		inEmpDailyAttendanceDBObj.day = "";
		inEmpDailyAttendanceDBObj.year = 0;
		inEmpDailyAttendanceDBObj.in_time = "";
		inEmpDailyAttendanceDBObj.out_time = "";
		inEmpDailyAttendanceDBObj.remark = "";
	}
	public EmpDailyAttendanceDBObj getRecordByPrimaryKey(String inEmpId, String inTodayDate) throws ClassNotFoundException{
		EmpDailyAttendanceDBObj  empDailyAttendanceDBObj = new EmpDailyAttendanceDBObj();
		try{
			System.out.println("DBUser=="+DBUser+",DBPswd=="+DBPswd+",DBUrl=="+DBUrl);
			/* DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver()); */
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn= DriverManager.getConnection(DBUrl,DBUser,DBPswd);
			Statement stmt = conn.createStatement();
			String lSqlString =	"select * from EMPLOYEE_DAILY_ATTENDANCE  ";
			lSqlString = lSqlString + "where emp_id='"+inEmpId+"' ";
			lSqlString = lSqlString + "and today_date='"+inTodayDate+"' ";
			ResultSet rs  = null;
			rs  = stmt.executeQuery(lSqlString);
			System.out.println("lSqlString====trtrt==within getRecordByPrimaryKey== "+lSqlString);
			if( rs.next()){
				System.out.println("fffff==="+rs.getString("emp_id"));
				empDailyAttendanceDBObj.emp_id   =  (String)rs.getString("emp_id");
				empDailyAttendanceDBObj.emp_name = (String)rs.getString("emp_name"); 
				empDailyAttendanceDBObj.today_date  = (String)rs.getString("today_date");
				empDailyAttendanceDBObj.month  = (String)rs.getString("month");
				empDailyAttendanceDBObj.day = (String)rs.getString("day");
				empDailyAttendanceDBObj.year = rs.getLong("year");

				/*
				 * String intime=rs.getString("in_time"); if(intime!=null)
				 * empDailyAttendanceDBObj.in_time = intime.substring(11,16);
				 */
				empDailyAttendanceDBObj.in_time = rs.getString("in_time");
				/*
				 * String outtime=rs.getString("out_time"); if(outtime!=null)
				 * empDailyAttendanceDBObj.out_time = outtime.substring(11,16);
				 */
				empDailyAttendanceDBObj.out_time = rs.getString("out_time");

				empDailyAttendanceDBObj.remark = (String)rs.getString("remark");
				System.out.println("fffff==="+rs.getString("emp_id"));
			}
			else{
				initializeEmpDailyAttendanceDBObj(empDailyAttendanceDBObj);
			}
			System.out.println("fffff====="+empDailyAttendanceDBObj.emp_id);
		}
		catch(SQLException  ex){
			ex.printStackTrace();
		}
		return empDailyAttendanceDBObj;
	}
	public ArrayList selectEmpDailyAttendanceByCriteria(String inCriteria) throws ClassNotFoundException{
		ArrayList EmpDailyAttendanceList = new ArrayList();
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn= DriverManager.getConnection(DBUrl,DBUser,DBPswd);
			Statement stmt = conn.createStatement();
			String lSqlString =	"select * from EMPLOYEE_DAILY_ATTENDANCE  ";
			if( inCriteria != null && inCriteria.length() > 0 ){
				lSqlString = lSqlString +" "+inCriteria+"" ;
			}
			System.out.println("Criteria===== "+inCriteria+" and query="+lSqlString);
			ResultSet rs  = null;
			rs  = stmt.executeQuery(lSqlString);
			while( rs.next()){
				EmpDailyAttendanceDBObj  empDailyAttendanceDBObj = new EmpDailyAttendanceDBObj();
				empDailyAttendanceDBObj.emp_id   =  (String)rs.getString("emp_id");
				empDailyAttendanceDBObj.emp_name = (String)rs.getString("emp_name"); 

				empDailyAttendanceDBObj.today_date  = (String)rs.getString("today_date");
				empDailyAttendanceDBObj.month  = (String)rs.getString("month");
				empDailyAttendanceDBObj.day = (String)rs.getString("day");
				empDailyAttendanceDBObj.year = rs.getLong("year");

				empDailyAttendanceDBObj.in_time = rs.getString("in_time");
				empDailyAttendanceDBObj.out_time = rs.getString("out_time");
				/*
				 * String intime=rs.getString("in_time"); if(intime!=null)
				 * empDailyAttendanceDBObj.in_time = intime.substring(11,16); String
				 * outtime=rs.getString("out_time"); if(outtime!=null)
				 * empDailyAttendanceDBObj.out_time = outtime.substring(11,16);
				 */

				
				empDailyAttendanceDBObj.remark = (String)rs.getString("remark");
				EmpDailyAttendanceList.add(empDailyAttendanceDBObj);
			}
		}
		catch(SQLException  ex){
			ex.printStackTrace();
		}
		return EmpDailyAttendanceList;
	}
	public int updateEmpDailyAttendanceDBObjByPrimaryKey(EmpDailyAttendanceDBObj inEmpDailyAttendanceDBObj) throws ClassNotFoundException, ParseException{
		int recupd = 0;
		String lQuery = "";
		DateFormat formatter = new SimpleDateFormat("hh:mm");
		java.util.Date inTime = formatter.parse(inEmpDailyAttendanceDBObj.in_time);
		java.util.Date outTime = formatter.parse(inEmpDailyAttendanceDBObj.out_time);
		Time sqlInTime = new Time(inTime.getTime());
		Time sqlOutTime = new Time(outTime.getTime());
		
		lQuery = lQuery +"update EMPLOYEE_DAILY_ATTENDANCE set emp_name='"+inEmpDailyAttendanceDBObj.emp_name+"'  "; 
		lQuery = lQuery +" , month='"+inEmpDailyAttendanceDBObj.month+"' ";
		lQuery = lQuery +" , day='"+inEmpDailyAttendanceDBObj.day+"' ";
		lQuery = lQuery +" , year="+inEmpDailyAttendanceDBObj.year+" ";

		//lQuery = lQuery +" , in_time=to_date('"+inEmpDailyAttendanceDBObj.today_date+" "+inEmpDailyAttendanceDBObj.in_time+"','yyyy-mm-dd HH24:MI') ";
		//lQuery = lQuery +" , out_time=to_date('"+inEmpDailyAttendanceDBObj.today_date+" "+inEmpDailyAttendanceDBObj.out_time+"','yyyy-mm-dd HH24:MI') ";
		
		lQuery = lQuery +" , in_time='"+sqlInTime+"' ";
		lQuery = lQuery +" , out_time='"+sqlOutTime+"' ";
		lQuery = lQuery +" , remark='"+inEmpDailyAttendanceDBObj.remark+"' ";
		lQuery = lQuery + "where emp_id='"+inEmpDailyAttendanceDBObj.emp_id+"' ";
		lQuery = lQuery + "and today_date='"+inEmpDailyAttendanceDBObj.today_date+"' ";
		System.out.println("lSqlString===:"+lQuery);
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn= DriverManager.getConnection(DBUrl,DBUser,DBPswd);
			Statement stmt = conn.createStatement();
			recupd  = stmt.executeUpdate(lQuery);
		}
		catch(SQLException  ex){
			ex.printStackTrace();
		}
		return recupd;
	}
	public EmpDailyAttendanceDBObj populateEmpDailyAttendanceDBObjFromReq(HttpServletRequest inReq){
		EmpDailyAttendanceDBObj  empDailyAttendanceDBObj = new EmpDailyAttendanceDBObj();
		empDailyAttendanceDBObj.emp_id   =  (String)inReq.getParameter("emp_id");
		empDailyAttendanceDBObj.emp_name = (String)inReq.getParameter("emp_name"); 
		empDailyAttendanceDBObj.today_date  = (String)inReq.getParameter("today_date");
		empDailyAttendanceDBObj.month  = (String)inReq.getParameter("month");
		empDailyAttendanceDBObj.day = (String)inReq.getParameter("day");
		empDailyAttendanceDBObj.year = Long.parseLong((String)inReq.getParameter("year"));
		empDailyAttendanceDBObj.in_time = (String)inReq.getParameter("in_time");
		empDailyAttendanceDBObj.out_time = (String)inReq.getParameter("out_time");
		empDailyAttendanceDBObj.remark = (String)inReq.getParameter("remark");
		return empDailyAttendanceDBObj;
	}
	public int insertEmpDailyAttendanceDBObj(EmpDailyAttendanceDBObj inEmpDailyAttendanceDBObj) throws ClassNotFoundException, ParseException{
		int recupd = 0; 
		String lQuery = "";
		DateFormat formatter = new SimpleDateFormat("hh:mm");
		java.util.Date inTime = formatter.parse(inEmpDailyAttendanceDBObj.in_time);
		java.util.Date outTime = formatter.parse(inEmpDailyAttendanceDBObj.out_time);
		Time sqlInTime = new Time(inTime.getTime());
		Time sqlOutTime = new Time(outTime.getTime());
		
		lQuery = lQuery +"insert into EMPLOYEE_DAILY_ATTENDANCE  values ( ";
		lQuery = lQuery +" '"+inEmpDailyAttendanceDBObj.emp_id+"'  ";
		lQuery = lQuery +" , '"+inEmpDailyAttendanceDBObj.emp_name+"'  ";

		lQuery = lQuery +" , '"+inEmpDailyAttendanceDBObj.today_date+"'  "; 
		lQuery = lQuery +" , '"+inEmpDailyAttendanceDBObj.month+"' ";
		lQuery = lQuery +" , '"+inEmpDailyAttendanceDBObj.day+"' ";
		lQuery = lQuery +" , "+inEmpDailyAttendanceDBObj.year+" ";

		/*
		 * lQuery = lQuery +" , to_date('"+inEmpDailyAttendanceDBObj.today_date+" "
		 * +inEmpDailyAttendanceDBObj.in_time+"','yyyy-mm-dd HH24:MI') "; lQuery =
		 * lQuery +" , to_date('"+inEmpDailyAttendanceDBObj.today_date+" "
		 * +inEmpDailyAttendanceDBObj.out_time+"','yyyy-mm-dd HH24:MI') ";
		 */
		lQuery = lQuery +" , '"+inEmpDailyAttendanceDBObj.today_date+" "+sqlInTime+"'";
		lQuery = lQuery +" , '"+inEmpDailyAttendanceDBObj.today_date+" "+sqlOutTime+"'";
		lQuery = lQuery +" , '"+inEmpDailyAttendanceDBObj.remark+"' ";
		lQuery = lQuery + " )";
		System.out.println("lSqlString===:"+lQuery);
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn= DriverManager.getConnection(DBUrl,DBUser,DBPswd);
			Statement stmt = conn.createStatement();
			recupd  = stmt.executeUpdate(lQuery);
		}
		catch(SQLException  ex){
			ex.printStackTrace();
		}
		return recupd;
	}
}