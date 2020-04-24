<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page session="true"%>
<%@ page import="Employee.*"%>
<%@ page import="TimeManagement.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Attendance</title>
<link rel="stylesheet" href="./css/mystyle.css" type="text/css" />
<link href="./css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="page-out">
		<div class="page-in">
			<div class="page">
				<div class="main">
					<%@ include file="./people_header.jsp"%>

					<table width="900" border="0" align="center">
						<tr>
							<td width="750" valign="top">
								<table border="0" width=100%>
									<p>&nbsp;</p>
									<hr width=100% color=#843F8B>
									<%
										String dbopr = "";
										dbopr = (String) session.getAttribute("dbopr");
										EmployeeDBObj employeeDBObj = new EmployeeDBObj();
										employeeDBObj = (EmployeeDBObj) session.getAttribute("employeeDBObj");

										DateYearMonthDayDBObj dateYearMonthDayDBObj = new DateYearMonthDayDBObj();
										dateYearMonthDayDBObj = (DateYearMonthDayDBObj) session.getAttribute("dateYearMonthDayDBObj");

										EmpDailyAttendanceDBObj empDailyAttendanceDBObj = new EmpDailyAttendanceDBObj();
										empDailyAttendanceDBObj = (EmpDailyAttendanceDBObj) session.getAttribute("empDailyAttendanceDBObj");
									%>
									<form action="time_management" name="form1" method="post">
										<tr>
											<td bgcolor='#AAAAAA' colspan='4' class=whitetext height=20
												align=center><b>Enter In/Out Time</b></td>
										</tr>
										<tr>
											<td>Employee Id</td>
											<td><%=employeeDBObj.emp_id%> <input type='hidden'
												name='emp_id' id='emp_id ' size='10'
												value='<%=employeeDBObj.emp_id%>' /></td>
											<td>Date</td>
											<td><%=dateYearMonthDayDBObj.today_date%> <input
												type='hidden' name='today_date' id='today_date ' size='10'
												value='<%=dateYearMonthDayDBObj.today_date%>' /></td>
										</tr>
										<tr>
											<td>Employee Name</td>
											<td><input type='hidden' name='emp_name' id='emp_name'
												size='10'
												value='<%=employeeDBObj.emp_f_name%> <%=employeeDBObj.emp_m_name%> <%=employeeDBObj.emp_l_name%>' />
												<%=employeeDBObj.emp_f_name%> 
												<%
 													if (employeeDBObj.emp_m_name != null)
 													out.print(employeeDBObj.emp_m_name);
 												%> 
 												<%=employeeDBObj.emp_l_name%></td>
											<td>Day</td>
											<td><%=dateYearMonthDayDBObj.day%> <input type='hidden'
												name='day' id='day' size='10'
												value='<%=dateYearMonthDayDBObj.day%>' /></td>
										</tr>
										<tr>
											<td>Department</td>
											<td><%=employeeDBObj.dept_id%></td>
											<td>Month</td>
											<td><input type='hidden' name='month' id='month'
												size='10' value='<%=dateYearMonthDayDBObj.month%>' /> <%=dateYearMonthDayDBObj.month%>
											</td>
										</tr>
										<tr>
											<td>&nbsp;</td>
											<td>&nbsp;</td>
											<td>Year</td>
											<td><%=dateYearMonthDayDBObj.year%> <input type='hidden'
												name='year' id='year' size='10'
												value='<%=dateYearMonthDayDBObj.year%>' /></td>
										</tr>
										<tr>
											<td>In Time</td>
											<%
												if (empDailyAttendanceDBObj.in_time != null)
													out.print("<td><input type='text' name='in_time' id='in_time' size ='10' value='"
															+ empDailyAttendanceDBObj.in_time + "'/>(HH:MM) </td>");
												else
													out.print(
															"<td align='left' ><input type='text' name='in_time' id='in_time' size ='10' value=''/>(HH:MM) </td>");
											%>
											<td>Out Time</td>
											<%
												if (empDailyAttendanceDBObj.out_time != null)
													out.print("<td><input type='text' name='out_time' id='out_time' size ='10' value='"
															+ empDailyAttendanceDBObj.out_time + "'/>(HH:MM)</td>");
												else
													out.print("<td><input type='text' name='out_time' id='out_time' size ='10' value=''/>(HH:MM) </td>");
											%>
										</tr>
										<tr>
											<td>Remark</td>
											<td colspan=3><input type='text' name='remark'
												id='remark' size='85' value='' /></td>
										</tr>
										<tr>
											<td colspan=4 align=center><input type='submit'
												name='submit' id='submit' size='10' value='Submit Detail' />
												<input type='hidden' name='action_submit' id='action_submit'
												size='10' value='emp_daily_att_dtl_submit' /></td>
										</tr>
								</table>
								<p>&nbsp;</p>
								<hr width=100% color=#AAAAAA>
							</td>
						</tr>
				</table>
						<%@include file="./people_footer.jsp"%>
				</div>
			</div>
		</div>
	</div>
</body>
</html>