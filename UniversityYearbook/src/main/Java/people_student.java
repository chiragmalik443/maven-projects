

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

import Student.StudentDBMethods;
import Student.StudentDBObj;

/**
 * Servlet implementation class people_employee
 */
public class people_student extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String lDBUser = "root";
	String lDBPswd = "malik369";
	String lDBUrl = "jdbc:mysql://localhost:3306/yearbook";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public people_student() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("initializing controller servlet.");
		ServletContext context = config.getServletContext();
//		Class.forName("com.mysql.cj.jdbc.Driver");
		lDBUser = "root";
		lDBPswd = "malik369";
		lDBUrl = "jdbc:mysql://localhost:3306/yearbook";
		super.init(config);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		session.setAttribute("lErrorMsg",null);
		String target = "";
		String action = request.getParameter("action");
		String lDBOpr = "";
		StudentDBMethods studentDBMethods = new StudentDBMethods(lDBUser,lDBPswd,lDBUrl);
		lDBOpr = (String)request.getParameter("dbopr");
		if( (lDBOpr != null && lDBOpr.length() > 0) && (lDBOpr.equals("create")) ){
			target = "/student_insert.jsp";
		}
		else if( (lDBOpr != null && lDBOpr.length() > 0) && (lDBOpr.equals("edit")) ){
			target = "/student_search.jsp";
		}
		else if( (lDBOpr != null && lDBOpr.length() > 0) && (lDBOpr.equals("show")) ){
			ArrayList studentList = new ArrayList();
			String criteria = "";
			studentList = (ArrayList)studentDBMethods.selectStudentByCriteria(criteria);
			session.setAttribute("studentList",studentList);
			target = "/student_list.jsp";			
			} 
		else if( (lDBOpr != null && lDBOpr.length() > 0) && (lDBOpr.equals("detail")) ){
			String lstuId = "";
			String lstuFName = "";
			lstuId = (String)request.getParameter("stu_id");
			lstuFName = (String)request.getParameter("stu_f_name");
			StudentDBObj studentDBObj = new StudentDBObj();
			studentDBObj = (StudentDBObj)studentDBMethods.getRecordByPrimaryKey(lstuId,lstuFName);
			System.out.println("iiiii="+studentDBObj.stu_id+"ffff="+studentDBObj.stu_f_name);
			session.setAttribute("studentDBObj",studentDBObj);
			target = "/student_profile.jsp";
		}
		else if ( (lDBOpr != null && lDBOpr.length() > 0) && (lDBOpr.equals("delete")) ){
			String stu_Id = "";
			stu_Id = (String)request.getParameter("stu_id");
			studentDBMethods.deleteStudent(stu_Id);
			String criteria = "";
			ArrayList studentList = new ArrayList();
			studentList = (ArrayList)studentDBMethods.selectStudentByCriteria(criteria);
			session.setAttribute("studentList",studentList);
			target = "/student_list.jsp";
		}
		String action_submit = request.getParameter("action_submit");
	    	String action_edit = request.getParameter("action_edit");
    	System.out.println("action_submit=="+action_submit);
		if ( action_submit != null || action_edit != null ){
			if ( request.getParameter("submit").equals("Submit") ){
				System.out.println("in the  Submit");
				if ( action_submit.equals("people_student_insert_submit") ){
					System.out.println("in the people_student_insert_submit ");
					action = "people_student_insert_submit";
				}
				else
					if (action_submit.equals("login_pswd_change_submit")){
						action = "login_pswd_change_submit";
					}
					else
						if (action_submit.equals("student_employee_search_submit")){
							action = "people_student_search_submit";
						}
			}
			else 
				if ( request.getParameter("submit").equals("Edit") ){
					if ( action_edit.equals("people_student_edit_submit") ){
						action = "people_student_edit_submit";

						StudentDBObj popstudentDBObj = new StudentDBObj();
							popstudentDBObj = (StudentDBObj)studentDBMethods.populateStudentDBObjFromReq(request);
							int rval = studentDBMethods.updateStudentByPrimaryKey(popstudentDBObj);
							if ( rval > 0 ){   
								StudentDBObj studentDBObj = new StudentDBObj();
								studentDBObj = (StudentDBObj)studentDBMethods.getRecordByPrimaryKey(popstudentDBObj.stu_id,popstudentDBObj.stu_f_name);
								session.setAttribute("studentDBObj",studentDBObj);
								String lErrorMsg = "student is Updated!!"; 
								session.setAttribute("lErrorMsg",lErrorMsg);
								target = "/student_list.jsp";
							}
					}
				}
		}
		if (action!=null){
			System.out.println("in the  "+action);
			if (action.equals("people_student_search_submit")){
				String lstuId = "";
				String lstuFName = "";
				lstuId = (String)request.getParameter("stu_id");
				lstuFName = (String)request.getParameter("stu_f_name");
				StudentDBObj studentDBObj = new StudentDBObj();
				studentDBObj = (StudentDBObj)studentDBMethods.getRecordByPrimaryKey(lstuId,lstuFName);
				System.out.println("iiiii="+studentDBObj.stu_id+"ffff="+studentDBObj.stu_f_name);
				if ( (studentDBObj.stu_id != null && studentDBObj.stu_f_name != null) ){
					session.setAttribute("studentDBObj",studentDBObj);
					target = "/student_edit.jsp";
				}
				else{
					String lErrorMsg = "student doesn't Exist"; 
					session.setAttribute("lErrorMsg",lErrorMsg);
					System.out.println("student:" + lErrorMsg);
					target = "/people_default.jsp"; 
				    }
			}   
			else
				if (action.equals("people_change_pswd_submit")){
					target = "/people_user_login_pswd_change.jsp";
				}
				else
					if (action.equals("people_student_insert_submit") || request.getParameter("action_submit").equals("student_login")){
						StudentDBObj popstudentDBObj = new StudentDBObj(); 
						popstudentDBObj = (StudentDBObj)studentDBMethods.populateStudentDBObjFromReq(request);
						StudentDBObj studentDBObj = new StudentDBObj();
						studentDBObj = (StudentDBObj)studentDBMethods.getRecordByPrimaryKey(popstudentDBObj.stu_id,popstudentDBObj.stu_f_name);
						if ( (popstudentDBObj.stu_id).equals(studentDBObj) && (popstudentDBObj.stu_f_name).equals(studentDBObj.stu_f_name) ){
							String lErrorMsg = "student Already Exist"; 
							session.setAttribute("lErrorMsg",lErrorMsg);
							System.out.println("student:" + lErrorMsg);
							target = "/people_default.jsp";
						}
						else{
							try {
								int rval =  studentDBMethods.insertStudent(popstudentDBObj);
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							StudentDBObj sstudentDBObj = new StudentDBObj();
							sstudentDBObj = (StudentDBObj)studentDBMethods.getRecordByPrimaryKey(popstudentDBObj.stu_id,popstudentDBObj.stu_f_name);
							session.setAttribute("studentDBObj",sstudentDBObj);
							String lErrorMsg = "student is Added!!"; 
							session.setAttribute("lErrorMsg",lErrorMsg);
							target = "/people_inserted.jsp"; 
							}
		
      					}   
					else
						if (action.equals("people_student_edit_submit")){ 
							StudentDBObj popstudentDBObj = new StudentDBObj();
							popstudentDBObj = (StudentDBObj)studentDBMethods.populateStudentDBObjFromReq(request);
							int rval = studentDBMethods.updateStudentByPrimaryKey(popstudentDBObj);
							if ( rval > 0 ){   
								StudentDBObj studentDBObj = new StudentDBObj();
								studentDBObj = (StudentDBObj)studentDBMethods.getRecordByPrimaryKey(popstudentDBObj.stu_id,popstudentDBObj.stu_f_name);
								session.setAttribute("studentDBObj",studentDBObj);
								String lErrorMsg = "Employee is Updated!!"; 
								session.setAttribute("lErrorMsg",lErrorMsg);
								target = "/student_list.jsp";
							}
							
					else
						if (action.equals("people_student_detail")){        
								ArrayList studentList = new ArrayList();
								String criteria="";
								studentList = (ArrayList)studentDBMethods.selectStudentByCriteria(criteria);
								session.setAttribute("studentList",studentList);
								target = "/student_list.jsp";
							}
							else
								if (action.equals("people_employee_delete")){        
							}
				}
   
	}
		 RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(target);
		    requestDispatcher.forward(request, response);
}
}
