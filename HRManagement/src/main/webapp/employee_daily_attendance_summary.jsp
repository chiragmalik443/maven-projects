<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page session="true"%>
<%@ page import="TimeManagement.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Attendance Summary</title>
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

								<p>&nbsp;</p>
								<div align=center class=boldblack>Daily Attendance Summary</div>
								<hr width=100% color=#AAAAAA>
								<table border="0" width=100%>
									<%
										String dbopr = "";
										dbopr = (String) session.getAttribute("dbopr");
									%>
									<tr>
										<td bgcolor='#AAAAAA' align='center' class=whitetext>Employee
											Id</td>
										<td bgcolor='#AAAAAA' align='center' class=whitetext>Employee
											Name</td>
										<td bgcolor='#AAAAAA' align='center' class=whitetext>In
											Time</td>
										<td bgcolor='#AAAAAA' align='center' class=whitetext>Out
											Time</td>
										<td bgcolor='#AAAAAA' align='center' class=whitetext>Remark</td>
										<td bgcolor='#AAAAAA' align='center' class=whitetext>Opr</td>
									</tr>
									<%
										ArrayList empDailyAttendanceList = new ArrayList();
										empDailyAttendanceList = (ArrayList) session.getAttribute("empDailyAttendanceList");
										if (empDailyAttendanceList != null && empDailyAttendanceList.size() > 0) {
											for (int size = 1; size <= empDailyAttendanceList.size(); size++) {
												EmpDailyAttendanceDBObj empDailyAttendanceDBObj = new EmpDailyAttendanceDBObj();
												empDailyAttendanceDBObj = (EmpDailyAttendanceDBObj) empDailyAttendanceList.get(size - 1);
									%>
									<form name="form1" method="post">

										<tr bgcolor='#AAAAAA'>
											<td align='center'><%=empDailyAttendanceDBObj.emp_id%></td>
											<td align='center'><%=empDailyAttendanceDBObj.emp_name%>
											</td>
											<td align='center'><%=empDailyAttendanceDBObj.in_time%></td>
											<td align='center'><%=empDailyAttendanceDBObj.out_time%></td>
											<td align='center'>
												<%
													if (empDailyAttendanceDBObj.remark != null)
																out.print(empDailyAttendanceDBObj.remark);
															else
																out.print("--");
												%> <td align='center' bgcolor=#AAAAAA>
												<a
												href='/HRManagement/time_management?dbopr=edit&&emp_id=<%=empDailyAttendanceDBObj.emp_id%>&&today_date=<%=empDailyAttendanceDBObj.today_date%>'
												class=yellowlink>Edit</a>
		  									</td>
		 							 </tr>
							  <%
		  							}
		  							} else {
		  									out.println("Employee does not exist!!!");
		  								}
		  						%>

								</table>

						</tr>
						</td>
					
					</table>
				<%@include file="./people_footer.jsp"%></div>
											
			</div>
		</div>
	</div>
</body>
</html>