<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page language="java"%>
<%@ page session="true"%>
<%@ page import="Faculty.*"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Enter Faculty Achievement</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
	
<link href="./css/style.css" rel="stylesheet" type="text/css">
<script language="javascript">
	function validateForm() {
		var myName = document.form1.name_achiv.value;
		if (myName == "") {
			alert("achivement name cannot be blank");
			document.form1.name_achiv.focus();
			return false;

		}
		myName = document.form1.event_parti.value;
		if (myName == "") {
			alert("not be blank");
			document.form1.name_achiv.focus();
			return false;
		}
		myName = document.form1.date.value;
		if (myName == "") {
			alert("not be blank");
			document.form1.date.focus();
			return false;
		}
		myName = document.form1.info.value;
		if (myName == "") {
			alert("provide some information");
			document.form1.info.focus();
			return false;
		}
		
		form1.submit();
	}
</script>
</head>
<body>
	<div class="page-out">
		<div class="page-in">
			<div class="page">
				<div class="main">
					<%@ include file="./people_header.jsp"%>

					<table width="900" border="0" align="center">
						<%-- <tr>
			<td colspan="2"><%@ include file="../jsp/people_header.jsp"%></td>
		</tr>
		<tr>
			<td width="900"><%@ include
					file="../jsp/people_default_menu.jsp"%></td>
		</tr> --%>
						<tr>
							<td width="750" valign="top" align="center">
								<p>&nbsp;</p> Enter Details of the achivements.
								<hr width=100% color=#AAAAAA>

								<table border="0" align="top" width=100%>
									<form action="people_achivement" name="form1" method="post">
									
									<tr>

										<td>Achievement name<sup>*</sup></td>
										<td><input type='text' name='name_achiv' id='name_achiv'
											size='10' value='' /></td>
									</tr>
									<tr>
										<td>Event participation<sup>*</sup></td>
										<td><input type='text' name='event_parti' id='event_parti '
											size='10' value='' /></td>
										
									</tr>
									
									<tr>
										
										<td>Date of achivement<sup>*</sup></td>
										<td align='left'><input type='date' name='date' id='date'
											size='10' value='' /> (yyyy-mm-dd)</td>
									</tr>
									
									<tr>
										<td>information about the event <sup>*</sup></td>
										<td align='left'><input type='text' name='state'
											id='state' size='10' value='' /></td>
										
									</tr>

									<tr>
										<td>All the ( <sup>*</sup>) marked are mandatory
										</td>
									</tr>
									<tr>
										<td align='center' colspan='4' > 
											<input align type="submit" class="btn btn-dark" name="submit" id="submit"  size='10' value="Submit" onClick="return validateForm()" /> 
										    <input type="hidden" name="action_submit" id="action_submit" size='10' value="people_user_login_submit" />
									</td>
									</tr>

								</table>
								<hr width=100% color=#AAAAAA>
							</td>
						</tr>

						<%-- <tr>
    <td colspan="2"><%@include file="../jsp/people_footer.jsp"%></td>
</tr> --%>
					</table>


					<%@include file="./people_footer.jsp"%>
				</div>
			</div>
		</div>
	</div>
</body>
</html>