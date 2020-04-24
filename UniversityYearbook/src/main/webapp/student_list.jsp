<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page session="true"%>
<%@ page import="Student.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Student List</title>
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
								<div align=center class=boldblack>List of Student</div>
								<hr bgcolor="#AAAAAA">
								<table border="0" width=100%>
									<%!StudentDBObj StudentDBObj;
									ArrayList studentList; // = new ArrayList();
									%>
									<%
										String dbopr = "";
										dbopr = (String) session.getAttribute("dbopr");
									%>
									<tr class="whitetext" height=20>
										<td bgcolor='#AAAAAA'>stu Id</td>
										<td bgcolor='#AAAAAA'>F Name</td>
										<td bgcolor='#AAAAAA'>L Name</td>
										<td bgcolor='#AAAAAA'>course</td>
										<td bgcolor='#AAAAAA'>DOB</td>
										<td bgcolor='#AAAAAA' align='center'>Edit</td>
										<td bgcolor='#AAAAAA' align='center'>Delete</td>
										<td bgcolor='#AAAAAA' align='center'>Detail</td>
									</tr>

									<%
									studentList = new ArrayList();
									studentList = (ArrayList) session.getAttribute("studentList");
										if (studentList != null && studentList.size() > 0) {
											for (int size = 1; size <= studentList.size(); size++) {
												StudentDBObj = new StudentDBObj();
												StudentDBObj = (StudentDBObj) studentList.get(size - 1);
												System.out.println(StudentDBObj.stu_f_name);
									%>
									<form name="form1" method="post">
										<tr bgcolor='#AAAAAA' height=18>
											<td align='left'><%=StudentDBObj.stu_id%></td>
											<td align='left'><%=StudentDBObj.stu_f_name%></td>
											<td align='left'><%=StudentDBObj.stu_l_name%></td>
											<td align='left'><%=StudentDBObj.course_id%></td>
											<td align='left'><%=StudentDBObj.dob%></td>
											<td align='center' bgcolor="#AAAAAA"><a
												href='/yearbook/people_student?dbopr=edit'
												class="yellowlink">Edit </a></td>
											<td align='center' bgcolor="#AAAAAA"><a
												href='/yearbook/people_student?dbopr=detail&&stu_id=<%=StudentDBObj.stu_id%>&&stu_f_name=<%=StudentDBObj.stu_f_name%>'
												class="yellowlink">Detail </a></td>
											<td align='center' bgcolor="#AAAAAA"><a
												href='/yearbook/people_student?dbopr=delete&&stu_id=<%=StudentDBObj.stu_id%>'
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