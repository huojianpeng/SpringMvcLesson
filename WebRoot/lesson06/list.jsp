<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'list.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<script type="text/javascript">

/*
使用ajax
  尽量使用true，异步模式（防假死）
  尽量建获取数据只有的逻辑处理（页面渲染）
*/




	function query() {
		//无刷新调用http://localhost:8080/SpringMvcLesson/queryFood 获取到数据，数据通过dom方式添加到table中
		//ajax（异步的ajax）+json
		var xmlhttp=null;
		//兼容所有的浏览器创建这个对象 XHR对象
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		//回调函数，当请求发送后，收到结果自动调用
		xmlhttp.onreadystatechange = function() {
		
			console.log(xmlhttp.readyState);
			/*
			存有 XMLHttpRequest的状态。从0到4发生变化
				0：请求未初始化  （没有调用send方法）
				1：服务器连接已建立  （Socket已连接）
				2：请求已接受  （获取到了参数，没有执行action方法）
				3：请求处理中  （已经在执行action方法，未执行完）
				4：请求已完成，且响应已就绪  （已经响应，并且能获取到最终的数据）
			status  响应的状态
				200  成功
			*/
		
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				//返回的是字符串的json
				var resutlJson=xmlhttp.responseText;
				//转换为js对象
				var resultObj=JSON.parse(resutlJson);
				//获取表格对象
				var table=document.getElementById("myTable");
				//将所有名字为dataTr的tr全部删除
				var allDataTr=document.getElementsByName("dataTr");
				var length=allDataTr.length;
				for(var i=0;i<length; i++){
					table.removeChild(allDataTr[0]);
				}
				
				//根据json的行数追加多个tr
				for(var i=0; i<resultObj.length; i++){
					var obj=resultObj[i];
					var td=document.createElement("td");
					td.innerText=obj.foodname;
					var td1=document.createElement("td");
					td1.innerText=obj.price;
					var tr=document.createElement("tr");
					tr.setAttribute("name","dataTr");
					tr.appendChild(td);
					tr.appendChild(td1);
					table.appendChild(tr);
					
				}
			}
		}
		
		var foodname=document.getElementsByName("foodName")[0].value;	
		//open方法表示，产生一个请求的关联（get 提交）
		//xmlhttp.open("GET", "${pageContext.request.contextPath}/queryFood?foodname="+foodname, true);
		//xmlhttp.send();
		
		//post提交
		 /*
		 一个ajax线程是否执行完成，可以通过回调函数 xmlhttp.onreadystatechange 是否执行完成来判断
		 异步：多个线程同时执行，无法判断，谁先执行  true
	         同步：一次只允许一个线程执行  false，页面会假死
		*/
		
		xmlhttp.open("POST", "${pageContext.request.contextPath}/queryFood", true);
		xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded;charset=UTF-8");
		xmlhttp.send("foodname="+foodname);
	}
</script>

</head>

<body>
	<input type='text' name="foodName" />
	<input type='button' value="查询" onclick="query()">
	<table id="myTable">
		<tr><th>菜品名</th><th>菜品价格</th></tr>

	</table>
</body>
</html>
