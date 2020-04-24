<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page session="true"%>
<%@ page import="Faculty.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Faculty List</title>
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
								<div align=center class=boldblack>List of faculty</div>
								<hr bgcolor="#AAAAAA">
								<table border="0" width=100%>
									<%!FacultyDBObj FacultyDBObj;
									ArrayList facultyList; // = new ArrayList();
									%>
									<%
										String dbopr = "";
										dbopr = (String) session.getAttribute("dbopr");
									%>
									<tr class="whitetext" height=20>
										<td bgcolor='#AAAAAA'>fac Id</td>
										<td bgcolor='#AAAAAA'>F Name</td>
										<td bgcolor='#AAAAAA'>L Name</td>
										<td bgcolor='#AAAAAA'>Designation</td>
										<td bgcolor='#AAAAAA'>DOB</td>
										<td bgcolor='#AAAAAA' align='center'>Edit</td>
										<td bgcolor='#AAAAAA' align='center'>Delete</td>
										<td bgcolor='#AAAAAA' align='center'>Detail</td>
									</tr>

									<%
										facultyList = new ArrayList();
										facultyList = (ArrayList) session.getAttribute("facultyList");
										if (facultyList != null && facultyList.size() > 0) {
											for (int size = 1; size <= facultyList.size(); size++) {
												FacultyDBObj = new FacultyDBObj();
												FacultyDBObj = (FacultyDBObj) facultyList.get(size - 1);
												System.out.println(FacultyDBObj.fac_f_name);
									%>
									<form name="form1" method="post">
										<tr bgcolor='#AAAAAA' height=18>
											<td align='left'><%=FacultyDBObj.fac_id%></td>
											<td align='left'><%=FacultyDBObj.fac_f_name%></td>
											<td align='left'><%=FacultyDBObj.fac_l_name%></td>
											<td align='left'><%=FacultyDBObj.level_id%></td>
											<td align='left'><%=FacultyDBObj.dob%></td>
											<td align='center' bgcolor="#AAAAAA"><a
												href='/yearbook/people_faculty?dbopr=edit'
												class="yellowlink">Edit </a></td>
											<td align='center' bgcolor="#AAAAAA"><a
												href='/yearbook/people_faculty?dbopr=detail&&fac_id=<%=FacultyDBObj.fac_id%>&&fac_f_name=<%=FacultyDBObj.fac_f_name%>'
												class="yellowlink">Detail </a></td>
											<td align='center' bgcolor="#AAAAAA"><a
												href='/yearbook/people_faculty?dbopr=delete&&fac_id=<%=FacultyDBObj.fac_id%>'
												class="yellowlink">Delete </a></td>
											<%
												}
												}
											%>
										</tr>



									
								</table>
						</tr>
						</table>
						<%@include file="./people_footer.jsp"%>
				</div>
			</div>
		</div>
	</div>

</body>
</html>