<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search</title>
<link rel="stylesheet" href="./css/mystyle.css" type="text/css" />
<link href="./css/style.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="./bootstrap/css/bootstrap.min.css" >
</head>
<body>
	<div class="page-out">
		<div class="page-in">
			<div class="page">
				<div class="main">
					<%@ include file="./people_header.jsp"%>
					<table width="900" border="0" align="center">
						<tr>
							<td width="750" valign="top" align="center">
								<p>&nbsp;</p>
								<div align=center>Enter Faculty Id & First Name</div>
								<hr bgcolor="#AAAAAA" width=500>
								<table border="0" align="top" width=50% align="right">
									<form action="people_faculty" name="form1" method="post">
										<tr>
											<td>Faculty Id</td>
											<td align='left'><input type='text' name='fac_id'
												id='fac_id ' size='10' value='' /></td>
										</tr>
										<tr>
											<td>First Name</td>
											<td align='left'><input type='text' name='fac_f_name'
												id='fac_f_name ' size='10' value='' /></td>
										</tr>
										<td></td>													
						<td align='center' colspan='4' >
						<input type="submit" class="btn btn-dark" name="submit" id="submit" size='10' value="Submit" /> 
						<input type="hidden" name="action_submit" id="action_submit" size='10'value="people_faculty_submit" />
					   
						
						
									</form>
								</table>
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