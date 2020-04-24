<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Menu Page</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/CascadeMenu.css" type="text/css"></link>
<script type="text/javascript" src="<%=request.getContextPath()%>/css/CascadeMenu.js">	
</script>
</head>
<body onload="InitMenu()" onclick="HideMenu(menuBar)" ID="Bdy">
	<DIV Id="menuBar" class="menuBar">

		<DIV Id="Bar5" class="Bar" menu="menu9" title="About Us/Log Out">Hi,
			NCU</DIV>
		<DIV Id="Bar1" class="Bar" menu="menu1">Employee</DIV>
		<DIV Id="Bar2" class="Bar" menu="menu2">Recruitment</DIV>
		<DIV Id="Bar3" class="Bar" menu="menu3">Time Management</DIV>
		<DIV Id="Bar4" class="Bar" menu="menu4">PayRoll</DIV>
	</DIV>
	<div align="right">
		<a href="./people_default.jsp">Home Page</a>
	</div>

	<div Id="menu9" class="menu">
		<div Id="menuItem9_1" class="menuItem" title="About Us"
			cmd="./people_aboutus.jsp" class="whitelink">About Us</div>
		<div Id="menuItem9_2" class="menuItem" title="Log Out"
			cmd="/hr_user_login" class="whitelink">Log Out</div>
	</div>
	<div Id="menu1" class="menu">
		<div Id="menuItem1_1" class="menuItem" title="Add Employees"
			cmd="/people_employee?dbopr=create" class="whitelink">Add New
			Employee</div>
		<div Id="menuItem1_2" class="menuItem" title="Edit Employee"
			cmd="/people_employee?dbopr=edit" class="whitelink">Edit
			Employee</div>
		<div Id="menuItem1_3" class="menuItem" title="Employee Profile"
			cmd="/people_employee?dbopr=show" class="whitelink">Employee
			Profile</div>
	</div>
	<div Id="menu2" class="menu">
		<div Id="menuItem2_1" class="menuItem" title="Register Applicants"
			cmd="/people_applicant?dbopr=register" class="whitelink">New
			Applicant</div>
		<div Id="menuItem2_2" class="menuItem" title="Update Applicants"
			cmd="/people_applicant?dbopr=select" class="whitelink">Update
			Applicant</div>
		<div Id="menuItem2_3" class="menuItem" title="Shortlisted Candidate"
			menu="menu5">Written Round</div>
		<div Id="menuItem2_4" class="menuItem" menu="menu6"
			title="Technical Round">Technical Round</div>
		<div Id="menuItem2_5" class="menuItem" menu="menu7" title="HR Round">
			HR Round</div>
	</div>
	<div Id="menu3" class="menu">
		<div Id="menuItem3_1" class="menuItem" title="Enter/Update Attendance"
			cmd="/time_management?dbopr=daily_attendance_entry" class="whitelink">
			Update Attendance</div>
		<div Id="menuItem3_2" class="menuItem" title="Attendance Summary"
			cmd="/time_management?dbopr=daily_attendance_summary"
			class="whitelink">Attendance Summary</div>
		<div Id="menuItem3_3" class="menuItem" menu="menu8">Leave
			Management</div>
	</div>
	<div Id="menu4" class="menu">
		<div Id="menuItem4_1" class="menuItem" title="Employee Agreement"
			cmd="/people_payroll?dbopr=employee_agreement" class="whitelink">
			<br /> Salary BreakUp
		</div>
		<div Id="menuItem4_2" class="menuItem" title="employee Salary"
			cmd="/people_payroll?dbopr=calc_employee_salary" class="whitelink">
			Employee Salary</div>
	</div>
	<div id="menu5" class="menu">
		<div Id="menuItem5_1" class="menuItem" title="call for written"
			cmd="/applicant_test_dtl?dbopr=call_for_written" class="whitelink">Call
			for Written</div>
		<div Id="menuItem5_2" class="menuItem" title="Update Results"
			cmd="/applicant_test_dtl?dbopr=upd_wrtn_performance"
			class="whitelink">Results</div>
	</div>
	<div id="menu6" class="menu">
		<div Id="menuItem6_1" class="menuItem"
			title="Shortlisted For Tech.Round"
			cmd="/applicant_test_dtl?dbopr=shortlist_after_wrtn"
			class="whitelink">Shortlist For Tech. Round</div>
		<div Id="menuItem6_2" class="menuItem" title="Update Results"
			cmd="/applicant_test_dtl?dbopr=upd_tech_performance"
			class="whitelink">Update Result</div>
	</div>
	<div id="menu7" class="menu">
		<div Id="menuItem7_1" class="menuItem"
			title="Shortlisted for HR Rounds"
			cmd="/applicant_test_dtl?dbopr=shortlist_after_tech"
			class="whitelink">Shortlist for HR Round</div>
		<div Id="menuItem7_2" class="menuItem" title="Update Results"
			cmd="/applicant_test_dtl?dbopr=upd_hr_performance" class="whitelink">
			Update Result</div>
		<div Id="menuItem7_3" class="menuItem"
			title="Shortlisted for Selection"
			cmd="/applicant_test_dtl?dbopr=shortlist_after_hr" class="whitelink">
			Shortlist For Selection</div>
		<div Id="menuItem7_4" class="menuItem" title="Selected"
			cmd="/applicant_test_dtl?dbopr=final_selected" class="whitelink">
			Selected Candidate</div>
	</div>
	<div id="menu8" class="menu">
		<div Id="menuItem8_1" class="menuItem" title="Leave Application"
			cmd="/leave_management?dbopr=leave_request" class="whitelink">Leave
			Apply</div>
		<div Id="menuItem8_2" class="menuItem" title="Approval Status"
			cmd="/leave_management?dbopr=leave_approve" class="whitelink">Leave
			Approval</div>
		<div Id="menuItem8_3" class="menuItem" title="Approved"
			cmd="/leave_management?dbopr=approved_leave" class="whitelink">Approved
			Request</div>
		<div Id="menuItem8_4" class="menuItem" title="Rejected"
			cmd="/leave_management?dbopr=rejected_leave" class="whitelink">Rejected
			Request</div>
	</div>
</body>
</html>