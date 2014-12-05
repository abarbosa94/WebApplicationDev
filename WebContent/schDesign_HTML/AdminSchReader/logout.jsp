<%
session.removeAttribute("loginok");
//session.removeAttribute("errormsg");
	if (session != null) {
		session.invalidate();
	}


%>

<h3>You're session has been terminated.</h3><p>  Make sure that you close the browser to ensure no personal data is kept on the computer.
