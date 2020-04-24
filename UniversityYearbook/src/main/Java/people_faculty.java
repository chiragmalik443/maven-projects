

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

import Faculty.FacultyDBMethods;
import Faculty.FacultyDBObj;

/**
 * Servlet implementation class people_employee
 */
public class people_faculty extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String lDBUser = "root";
	String lDBPswd = "malik369";
	String lDBUrl = "jdbc:mysql://localhost:3306/yearbook";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public people_faculty() {
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
		FacultyDBMethods facultyDBMethods = new FacultyDBMethods(lDBUser,lDBPswd,lDBUrl);
		lDBOpr = (String)request.getParameter("dbopr");
		if( (lDBOpr != null && lDBOpr.length() > 0) && (lDBOpr.equals("create")) ){
			target = "/faculty_insert.jsp";
		}
		else if( (lDBOpr != null && lDBOpr.length() > 0) && (lDBOpr.equals("edit")) ){
			target = "/faculty_search.jsp";
		}
		else if( (lDBOpr != null && lDBOpr.length() > 0) && (lDBOpr.equals("show")) ){
			ArrayList facultyList = new ArrayList();
			String criteria = "";
			facultyList = (ArrayList)facultyDBMethods.selectFacultyByCriteria(criteria);
			session.setAttribute("FacultyList",facultyList);
			target = "/faculty_list.jsp";			
			} 
		else if( (lDBOpr != null && lDBOpr.length() > 0) && (lDBOpr.equals("detail")) ){
			String lFacId = "";
			String lFacFName = "";
			lFacId = (String)request.getParameter("fac_id");
			lFacFName = (String)request.getParameter("fac_f_name");
			FacultyDBObj facultyDBObj = new FacultyDBObj();
			facultyDBObj = (FacultyDBObj)facultyDBMethods.getRecordByPrimaryKey(lFacId,lFacFName);
			System.out.println("iiiii="+facultyDBObj.fac_id+"ffff="+facultyDBObj.fac_f_name);
			session.setAttribute("facultyDBObj",facultyDBObj);
			target = "/faculty_profile.jsp";
		}
		else if ( (lDBOpr != null && lDBOpr.length() > 0) && (lDBOpr.equals("delete")) ){
			String fac_Id = "";
			fac_Id = (String)request.getParameter("fac_id");
			facultyDBMethods.deleteFaculty(fac_Id);
			String criteria = "";
			ArrayList facultyList = new ArrayList();
			facultyList = (ArrayList)facultyDBMethods.selectFacultyByCriteria(criteria);
			session.setAttribute("FacultyList",facultyList);
			target = "/faculty_list.jsp";
		}
		String action_submit = request.getParameter("action_submit");
	    	String action_edit = request.getParameter("action_edit");
    	System.out.println("action_submit=="+action_submit);
		if ( action_submit != null || action_edit != null ){
			if ( request.getParameter("submit").equals("Submit") ){
				System.out.println("in the  Submit");
				if ( action_submit.equals("people_faculty_insert_submit") ){
					System.out.println("in the people_faculty_insert_submit ");
					action = "people_faculty_insert_submit";
				}
				else
					if (action_submit.equals("login_pswd_change_submit")){
						action = "login_pswd_change_submit";
					}
					else
						if (action_submit.equals("faculty_employee_search_submit")){
							action = "people_faculty_search_submit";
						}
			}
			else 
				if ( request.getParameter("submit").equals("Edit") ){
					if ( action_edit.equals("people_faculty_edit_submit") ){
						action = "people_faculty_edit_submit";

						FacultyDBObj popFacultyDBObj = new FacultyDBObj();
							popFacultyDBObj = (FacultyDBObj)facultyDBMethods.populateFacultyDBObjFromReq(request);
							int rval = facultyDBMethods.updateFacultyByPrimaryKey(popFacultyDBObj);
							if ( rval > 0 ){   
								FacultyDBObj facultyDBObj = new FacultyDBObj();
								facultyDBObj = (FacultyDBObj)facultyDBMethods.getRecordByPrimaryKey(popFacultyDBObj.fac_id,popFacultyDBObj.fac_f_name);
								session.setAttribute("facultyDBObj",facultyDBObj);
								String lErrorMsg = "Faculty is Updated!!"; 
								session.setAttribute("lErrorMsg",lErrorMsg);
								target = "/faculty_list.jsp";
							}
					}
				}
		}
		if (action!=null){
			System.out.println("in the  "+action);
			if (action.equals("people_faculty_search_submit")){
				String lFacId = "";
				String lFacFName = "";
				lFacId = (String)request.getParameter("fac_id");
				lFacFName = (String)request.getParameter("fac_f_name");
				FacultyDBObj facultyDBObj = new FacultyDBObj();
				facultyDBObj = (FacultyDBObj)facultyDBMethods.getRecordByPrimaryKey(lFacId,lFacFName);
				System.out.println("iiiii="+facultyDBObj.fac_id+"ffff="+facultyDBObj.fac_f_name);
				if ( (facultyDBObj.fac_id != null && facultyDBObj.fac_f_name != null) ){
					session.setAttribute("facultyDBObj",facultyDBObj);
					target = "/faculty_edit.jsp";
				}
				else{
					String lErrorMsg = "Faculty doesn't Exist"; 
					session.setAttribute("lErrorMsg",lErrorMsg);
					System.out.println("Faculty:" + lErrorMsg);
					target = "/people_default.jsp"; 
				    }
			}   
			else
				if (action.equals("people_change_pswd_submit")){
					target = "/people_user_login_pswd_change.jsp";
				}
				else
					if (action.equals("people_faculty_insert_submit") || request.getParameter("action_submit").equals("faculty_login")
							)
							{
						FacultyDBObj popFacultyDBObj = new FacultyDBObj(); 
						popFacultyDBObj = (FacultyDBObj)facultyDBMethods.populateFacultyDBObjFromReq(request);
						FacultyDBObj facultyDBObj = new FacultyDBObj();
						facultyDBObj = (FacultyDBObj)facultyDBMethods.getRecordByPrimaryKey(popFacultyDBObj.fac_id,popFacultyDBObj.fac_f_name);
						if ( (popFacultyDBObj.fac_id).equals(facultyDBObj) && (popFacultyDBObj.fac_f_name).equals(facultyDBObj.fac_f_name) ){
							String lErrorMsg = "Faculty Already Exist"; 
							session.setAttribute("lErrorMsg",lErrorMsg);
							System.out.println("Faculty:" + lErrorMsg);
							target = "/people_default.jsp";
						}
						else{
							try {
								int rval =  facultyDBMethods.insertFaculty(popFacultyDBObj);
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} 
							FacultyDBObj sFacultyDBObj = new FacultyDBObj();
							sFacultyDBObj = (FacultyDBObj)facultyDBMethods.getRecordByPrimaryKey(popFacultyDBObj.fac_id,popFacultyDBObj.fac_f_name);
							session.setAttribute("facultyDBObj",sFacultyDBObj);
							String lErrorMsg = "Faculty is Added!!"; 
							session.setAttribute("lErrorMsg",lErrorMsg);
							target = "/people_inserted.jsp"; 
							}
		
      					}   
					else
						if (action.equals("people_faculty_edit_submit")){ 
							FacultyDBObj popFacultyDBObj = new FacultyDBObj();
							popFacultyDBObj = (FacultyDBObj)facultyDBMethods.populateFacultyDBObjFromReq(request);
							int rval = facultyDBMethods.updateFacultyByPrimaryKey(popFacultyDBObj);
							if ( rval > 0 ){   
								FacultyDBObj facultyDBObj = new FacultyDBObj();
								facultyDBObj = (FacultyDBObj)facultyDBMethods.getRecordByPrimaryKey(popFacultyDBObj.fac_id,popFacultyDBObj.fac_f_name);
								session.setAttribute("facultyDBObj",facultyDBObj);
								String lErrorMsg = "Employee is Updated!!"; 
								session.setAttribute("lErrorMsg",lErrorMsg);
								target = "/faculty_list.jsp";
							}
							
					else
						if (action.equals("people_faculty_detail")){        
								ArrayList facultyList = new ArrayList();
								String criteria="";
								facultyList = (ArrayList)facultyDBMethods.selectFacultyByCriteria(criteria);
								session.setAttribute("FacultyList",facultyList);
								target = "/faculty_list.jsp";
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
