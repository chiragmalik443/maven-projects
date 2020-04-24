<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Change Password</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link href="./css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="page-out">
		<div class="page-in">
			<div class="page">
				<div class="main">
					<div class="header">
						<div class="header-top">
							<h1>
								Human Resource <span>Management</span>
							</h1>
						</div>
						<div class="header-bottom">
							<h2>
								Deck your hearts with lights of success.....<br> Sure!!
								that you will reach your destiny.
							</h2>
						</div>
						<div class="topmenu">
							<ul>
								<li
									style="background: transparent none repeat scroll 0% 50%; -moz-background-clip: initial; -moz-background-origin: initial; -moz-background-inline-policy: initial; padding-left: 0px;"><a
									href="home.html"><span>Home</span></a></li>
								<li><a href="#"><span>About&nbsp;us</span></a></li>
								<li><a href="#"><span>Contact&nbsp;us</span></a></li>
								<li><a href="#" title="for new candidates only"><span>Employee
											Registration</span></a></li>
								<li><a href="#" title="register on behalf of your company"><span>Time
											Management</span></a></li>

							</ul>
						</div>
					</div>
					<div class="content" style="box-sizing: content-box !important;">
						<div class="content-left">
							<div class="row1">
								<h1 class="title">
									HR User <span>Login</span>
								</h1>
								<p>
									<strong>Enter your Credentials</strong> Dummy text goes here.
								</p>
							</div>
							<div class="row2">
								<h2 class="subtitle">
									Log <span>In</span>
								</h2>
								<form action="hr_user_login" name="form1" method="post">
									<table width="100%">
										<tr>
											<td colspan="2">
												<table border="0" align="center">
													<tr>
														<td>User Id</td>
														<td align="center"><input type="text" class="form-control" name="user_id"
															id="user_id" value="" /></td>
													</tr>
													<tr>
														<td>User Name</td>
														<td align="center"><input type="text" class="form-control" 
															name="user_name" id="user_name" value="" /></td>
													</tr>
													<tr>
														<td>Old Password</td>
														<td align="center"><input type="password" class="form-control" 
															name="old_pswd" id="old_pswd" value="" /></td>
													</tr>
													<tr>
														<td>New Password</td>
														<td align="center"><input type="password" class="form-control" 
															name="new_pswd" id="new_pswd" value="" /></td>
													</tr>
													<tr>
														<td>Retype Password</td>
														<td align="center"><input type="password" class="form-control" 
															name="retype_pswd" id="retype_pswd" value="" /></td>
													</tr>
													<tr>
														<td colspan="2" align="right"><input type="submit" class="btn btn-dark" 
															name="submit" id="submit" value="Submit" /> <input
															type="hidden" name="action_submit" id="action_submit"
															value="login_pswd_change_submit" /></td>
													</tr>
													<%
														String msg = (String) session.getAttribute("lErrorMsg");
														if (msg != null && msg.length() > 0) {
													%>
													<tr>
														<td colspan="2" align="right">
															<%
																out.println("<div class=boldred align=center>" + msg + "</div>");
															%>
														</td>
													</tr>
													<%
														}
													%>
												</table>
											</td>
										</tr>
										
									</table>
								</form>

							</div>

						</div>
						<div class="content-right">
							<div class="mainmenu">
								<h2 class="sidebar1">Main Menu</h2>
								<ul>
									<li><a href="./hr_user_login.jsp"
										title="for registered employee's only">Employee Login</a></li>
									<li><a href="#" title="for registered company's only">Company
											Login</a></li>
									<li><a href="#">Upload Question papers</a></li>
									<li><a href="#">Search E-books</a></li>
									<li><a href="#">this is a dummy link 5</a></li>
									<li><a href="#">this is a dummy link 6</a></li>
									<li><a href="#">this is a dummy link 7</a></li>
									<li><a href="#">this is a dummy link 8</a></li>
									<li><a href="#">this is a dummy link 9</a></li>
									<li><a href="#">this is a dummy link 10</a></li>
								</ul>
							</div>
							<div class="contact">
								<h2 class="sidebar2">Contact</h2>
								<div class="contact-detail">
									<p class="green">
										<strong>Lorem Ipsum is simply dummy text</strong>
									</p>
									<p>
										<strong>Adress :</strong> Lorem Ipsum has been the<br>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;dummy
										text the 1500s,
									</p>
									<p>
										<strong>E-mail :</strong> &nbsp;&nbsp;&nbsp;when an unknown
										printer took a
									</p>
									<p>
										<strong>Phone :</strong> &nbsp;&nbsp;&nbsp;00-0000000000<br>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;000-0000-0000
									</p>
								</div>
							</div>
						</div>
					</div>
					<div class="footer">

						<img src="./images/footer.gif" alt="html templates" border="0"
							height="1" width="1"></a>
						<p>
							&copy; Copyright 2019. Designed by NCU Developers<br>
							<!--DO NOT Remove The Footer Links-->
						</p>
						<ul>
							<li style="border-left: medium none;"><a href="/home.html"><span>Home</span></a></li>
							<li><a href="#"><span>About&nbsp;us</span></a></li>
							<li><a href="#"><span>What's&nbsp;new</span></a></li>
							<li><a href="#"><span>Services</span></a></li>
							<li><a href="#"><span>Contact</span></a></li>
							<li><a href="#"><span>Resources</span></a></li>
							<li style="padding-right: 0px;"><a href="#"><span>Links</span></a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>