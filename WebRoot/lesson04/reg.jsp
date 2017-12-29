  <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'reg.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<script type="text/javascript">
		function checkSubmit(){
			
			
			document.forms[0].submit();
		}
	</script>

  </head>
  
  <body>
  <a href="${pageContext.request.contextPath }/mid?a=zh_CN">中文</a>&nbsp&nbsp&nbsp<a href="${pageContext.request.contextPath }/mid?a=en_GB">English</a>
    <form action="<%=path %>/myreg" method="post">
    	<s:message code="userName"></s:message>：<input type="text" name="userName"/><font color=red><form:errors path="userInfo.userName"></form:errors></font>
    	<br/>
    	<s:message code="password"></s:message>：<input type="password" name="password"/>
    	<font color=red><form:errors path="userInfo.password"></form:errors></font>
    	<br/>
    	<s:message code="repassword"></s:message>：<input type="password" name="repassword"/>
    	<font color=red><form:errors path="userInfo.repassword"></form:errors></font>
    	<br/>
    	<s:message code="email"></s:message>：<input type="text" name="email"/>
    	<font color=red><form:errors path="userInfo.email"></form:errors></font>
    	<br/>
    	<s:message code="age"></s:message>：<input type="text" name="age"/>
    	<font color=red><form:errors path="userInfo.age"></form:errors></font>
    	<br/>
    	<s:message code="phone"></s:message>：<input type="text" name="phone"/>
    	<font color=red><form:errors path="userInfo.phone"></form:errors></font>
    	<br/>
    	<s:message code="time"></s:message>：<input type="text" name="time"/>
    	<font color=red><form:errors path="userInfo.time"></form:errors></font>
    	<br/>
    	<s:message code="url"></s:message>：<input type="text" name="url"/>
    	<font color=red><form:errors path="userInfo.url"></form:errors></font>
    	<br/>
    	<input type="button" onclick="checkSubmit()" value="<s:message code='regist'></s:message>"/>
    	<br/>
    </form><br/>
  </body>
</html>
