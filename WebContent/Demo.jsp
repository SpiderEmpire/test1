<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script type="text/javascript">
alert("fdfd");
var name = '<%=session.getAttribute("name")%>';
	if (name != null) {

	} else {
		//location.
	}
</script>
</head>
<body>
	hello
	<%
	HttpSession session1 = request.getSession(false);
	if (session1.getAttribute("name") != null) {
		out.println(session1.getAttribute("name"));
	}
%>

</body>
</html>