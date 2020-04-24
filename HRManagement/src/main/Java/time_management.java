

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Employee.EmployeeDBMethods;
import Employee.EmployeeDBObj;
import TimeManagement.DateYearMonthDayDBObj;
import TimeManagement.EmpDailyAttendanceDBObj;
import TimeManagement.TimeManagementDBMethods;

/**
 * Servlet implementation class time_management
 */
public class time_management extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String lDBUser = "";
	String lDBPswd = "";
	String lDBUrl = "";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	public void init(ServletConfig config) throws ServletException {
		System.out.println("initializing controller servlet.");
		ServletContext context = config.getServletContext();
//		Class.forName("com.mysql.cj.jdbc.Driver");
		lDBUser = "root";
		lDBPswd = "root@123";
		lDBUrl = "jdbc:mysql://localhost:3306/hr_management";
		super.init(config);
	}
    public time_management() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		session.setAttribute("lErrorMsg",null);
		String target = "";
		String action = request.getParameter("action");
		String lDBOpr = "";
		lDBOpr = (String)request.getParameter("dbopr");
		
		if( (lDBOpr != null && lDBOpr.length() > 0) && (lDBOpr.equals("daily_attendance_entry")) ){
			target = "/employee_search_attendance.jsp";
		}
		else
			if( (lDBOpr != null && lDBOpr.length() > 0) && (lDBOpr.equals("daily_attendance_summary")) ){
				action = "daily_attendance_summary";
			}
			else
				if( (lDBOpr != null && lDBOpr.length() > 0) && (lDBOpr.equals("edit")) ){
					action = "daily_attendance_summary_edit";
				}
		String action_submit = request.getParameter("action_submit");
		String action_edit = request.getParameter("action_edit");
		System.out.println("action_submit=="+action_submit);
		if ( action_submit != null || action_edit != null ){
			if ( request.getParameter("submit").equals("Submit") ){
				System.out.println("in the  Submit");
				if ( action_submit.equals("people_employee_search_submit") ){
					System.out.println("in the people_employee_insert_submit ");
					action = "people_employee_search_submit";
				}
			}
			else 
				if ( request.getParameter("submit").equals("Submit Detail") ){
					if ( action_submit.equals("emp_daily_att_dtl_submit") )
						action = "emp_daily_att_dtl_submit";
				}  
		}
		if (action!=null){
			System.out.println("in the  "+action);
			if (action.equals("people_employee_search_submit")){
				String lEmpId = "";
				String lEmpFName = "";
				lEmpId = (String)request.getParameter("emp_id");
				lEmpFName = (String)request.getParameter("emp_f_name");
				TimeManagementDBMethods timeManagementDBMethods  = new TimeManagementDBMethods(lDBUser,lDBPswd,lDBUrl);
				DateYearMonthDayDBObj dateYearMonthDayDBObj = new DateYearMonthDayDBObj();
				dateYearMonthDayDBObj = (DateYearMonthDayDBObj)timeManagementDBMethods.getCurDateYearMonthDayDBObj();
				session.setAttribute("dateYearMonthDayDBObj",dateYearMonthDayDBObj);
				EmployeeDBObj employeeDBObj = new EmployeeDBObj();
				EmployeeDBMethods employeeDBMethods = new EmployeeDBMethods(lDBUser,lDBPswd,lDBUrl);
				employeeDBObj = (EmployeeDBObj)employeeDBMethods.getRecordByPrimaryKey(lEmpId,lEmpFName);
				EmpDailyAttendanceDBObj empDailyAttendanceDBObj = new EmpDailyAttendanceDBObj();
				try {
					empDailyAttendanceDBObj = (EmpDailyAttendanceDBObj)timeManagementDBMethods.getRecordByPrimaryKey(lEmpId,dateYearMonthDayDBObj.today_date);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("iiiii="+employeeDBObj.emp_id+"ffff="+employeeDBObj.emp_f_name);
				if ( (employeeDBObj.emp_id != null && employeeDBObj.emp_f_name.equals(lEmpFName)  ) ){
					session.setAttribute("empDailyAttendanceDBObj",empDailyAttendanceDBObj);
					session.setAttribute("employeeDBObj",employeeDBObj);
					target = "/employee_daily_attendance.jsp";
				}
				else{
					String lErrorMsg = "Employee doesn't Exist"; 
					session.setAttribute("lErrorMsg",lErrorMsg);
					target = "/people_default.jsp";
				}
			}   
			else
			if (action.equals("daily_attendance_summary_edit")){
				String lEmpId = "";
				String lTodayDate = "";
				String lEmpFName = "";
				lEmpId = (String)request.getParameter("emp_id");
				lTodayDate = (String)request.getParameter("today_date");
				TimeManagementDBMethods timeManagementDBMethods  = new TimeManagementDBMethods(lDBUser,lDBPswd,lDBUrl);
				DateYearMonthDayDBObj dateYearMonthDayDBObj = new DateYearMonthDayDBObj();
				dateYearMonthDayDBObj = (DateYearMonthDayDBObj)timeManagementDBMethods.getCurDateYearMonthDayDBObj();
				EmployeeDBObj employeeDBObj = new EmployeeDBObj();
				EmployeeDBMethods employeeDBMethods = new EmployeeDBMethods(lDBUser,lDBPswd,lDBUrl);
				employeeDBObj = (EmployeeDBObj)employeeDBMethods.getRecordByPrimaryKey(lEmpId,lEmpFName);
				EmpDailyAttendanceDBObj empDailyAttendanceDBObj = new EmpDailyAttendanceDBObj();
				try {
					empDailyAttendanceDBObj = (EmpDailyAttendanceDBObj)timeManagementDBMethods.getRecordByPrimaryKey(lEmpId,lTodayDate);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				session.setAttribute("empDailyAttendanceDBObj",empDailyAttendanceDBObj);
				session.setAttribute("employeeDBObj",employeeDBObj);
				session.setAttribute("dateYearMonthDayDBObj",dateYearMonthDayDBObj);
				target = "/employee_daily_attendance.jsp";
			}   
			else
			if (action.equals("daily_attendance_summary")){
				TimeManagementDBMethods timeManagementDBMethods  = new TimeManagementDBMethods(lDBUser,lDBPswd,lDBUrl);
				DateYearMonthDayDBObj dateYearMonthDayDBObj = new DateYearMonthDayDBObj();
				dateYearMonthDayDBObj = (DateYearMonthDayDBObj)timeManagementDBMethods.getCurDateYearMonthDayDBObj();
				session.setAttribute("dateYearMonthDayDBObj",dateYearMonthDayDBObj);
				ArrayList empDailyAttendanceList = new ArrayList();
				String criteria = "";
				criteria = " where today_date='"+dateYearMonthDayDBObj.today_date+"'";
				try {
					empDailyAttendanceList = ( ArrayList)timeManagementDBMethods.selectEmpDailyAttendanceByCriteria(criteria);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				session.setAttribute("empDailyAttendanceList",empDailyAttendanceList);
				System.out.println("empDailyAttendanceList.size()"+empDailyAttendanceList.size());
				target = "/employee_daily_attendance_summary.jsp";
			}   
			else
			if (action.equals("emp_daily_att_dtl_submit")){
				EmpDailyAttendanceDBObj  popEmpDailyAttendanceDBObj = new EmpDailyAttendanceDBObj();
				TimeManagementDBMethods timeManagementDBMethods  = new TimeManagementDBMethods(lDBUser,lDBPswd,lDBUrl);
				popEmpDailyAttendanceDBObj = ( EmpDailyAttendanceDBObj)timeManagementDBMethods.populateEmpDailyAttendanceDBObjFromReq(request);
				System.out.println("popEmpDailyAttendanceDBObj.emp_id"+popEmpDailyAttendanceDBObj.emp_id);
				EmpDailyAttendanceDBObj  empDailyAttendanceDBObj = new EmpDailyAttendanceDBObj();

	//			int rval = timeManagementDBMethods.updateEmpDailyAttendanceDBObjByPrimaryKey(popEmpDailyAttendanceDBObj);
				try {
					empDailyAttendanceDBObj = (EmpDailyAttendanceDBObj)timeManagementDBMethods.getRecordByPrimaryKey(popEmpDailyAttendanceDBObj.emp_id,popEmpDailyAttendanceDBObj.today_date);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	//			DateYearMonthDayDBObj dateYearMonthDayDBObj = new DateYearMonthDayDBObj();
	//			dateYearMonthDayDBObj = (DateYearMonthDayDBObj)timeManagementDBMethods.getCurDateYearMonthDayDBObj();
	//			session.setAttribute("dateYearMonthDayDBObj",dateYearMonthDayDBObj);
	//			ArrayList empDailyAttendanceList = new ArrayList();
	//			String criteria = "";
	//			criteria = " where today_date='"+dateYearMonthDayDBObj.today_date+"'";
	//			empDailyAttendanceList = ( ArrayList)timeManagementDBMethods.selectEmpDailyAttendanceByCriteria(criteria);
	//			session.setAttribute("empDailyAttendanceList",empDailyAttendanceList);
// till here
				//empDailyAttendanceDBObj = (EmpDailyAttendanceDBObj)timeManagementDBMethods.getRecordByPrimaryKey(popEmpDailyAttendanceDBObj.emp_id,popEmpDailyAttendanceDBObj.today_date);
				//System.out.println("empDailyAttendanceDBObj.emp_id"+empDailyAttendanceDBObj.emp_id);
		
				if ( ( empDailyAttendanceDBObj.emp_id != null && (popEmpDailyAttendanceDBObj.emp_id).equals(empDailyAttendanceDBObj.emp_id)) && (popEmpDailyAttendanceDBObj.today_date).equals(empDailyAttendanceDBObj.today_date) ){
					try {
						int rval = timeManagementDBMethods.updateEmpDailyAttendanceDBObjByPrimaryKey(popEmpDailyAttendanceDBObj);
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						empDailyAttendanceDBObj = (EmpDailyAttendanceDBObj)timeManagementDBMethods.getRecordByPrimaryKey(popEmpDailyAttendanceDBObj.emp_id,popEmpDailyAttendanceDBObj.today_date);
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					session.setAttribute("empDailyAttendanceDBObj",empDailyAttendanceDBObj);
					target = "/employee_daily_attendance_summary.jsp";
				}
				else{
					try {
						int rval =  timeManagementDBMethods.insertEmpDailyAttendanceDBObj(popEmpDailyAttendanceDBObj);
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
					catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						empDailyAttendanceDBObj = (EmpDailyAttendanceDBObj)timeManagementDBMethods.getRecordByPrimaryKey(popEmpDailyAttendanceDBObj.emp_id,popEmpDailyAttendanceDBObj.today_date);
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					session.setAttribute("empDailyAttendanceDBObj",empDailyAttendanceDBObj);
					
					 //till here
				target = "/employee_daily_attendance_summary.jsp";
				} 
				DateYearMonthDayDBObj dateYearMonthDayDBObj = new DateYearMonthDayDBObj();
				dateYearMonthDayDBObj = (DateYearMonthDayDBObj)timeManagementDBMethods.getCurDateYearMonthDayDBObj();
				session.setAttribute("dateYearMonthDayDBObj",dateYearMonthDayDBObj);
				ArrayList empDailyAttendanceList = new ArrayList();
				String criteria = "";
				criteria = " where today_date='"+dateYearMonthDayDBObj.today_date+"'";
				try {
					empDailyAttendanceList = ( ArrayList)timeManagementDBMethods.selectEmpDailyAttendanceByCriteria(criteria);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				session.setAttribute("empDailyAttendanceList",empDailyAttendanceList);
				target = "/employee_daily_attendance_summary.jsp";
			}
						
    	} // (action== null )
		/* forwarding the request/response to the targeted view */
		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(target);
		requestDispatcher.forward(request, response);
	}

}
