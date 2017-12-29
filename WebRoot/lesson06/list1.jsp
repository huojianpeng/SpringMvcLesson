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

function sendAjax(url,methodType,param,retnFunction){
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
		
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200){
				retnFunction(xmlhttp.responseText);
			}
		}
		
		if(methodType=="get" || methodType=="GET"){
		
			xmlhttp.open("GET", url+"?"+param, true);
			xmlhttp.send();
		}else{
			xmlhttp.open("POST", url, true);
			xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded;charset=UTF-8");
			xmlhttp.send(param);
		}
		
		
		
}


/*
使用ajax
  尽量使用true，异步模式（防假死）
  尽量建获取数据只有的逻辑处理（页面渲染）
*/




	function query() {
		var foodname=document.getElementsByName("foodName")[0].value;	
     	sendAjax("${pageContext.request.contextPath}/queryFood","GET","foodname="+foodname,function(responseText){
			//返回的是字符串的json
				var resutlJson=responseText;
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
					
					var td2=document.createElement("td");
					//删除按钮
				   var ib=document.createElement("button");
				   ib.innerText="X";
				   var ib1=document.createElement("button");
				   ib1.innerText="U";
				   td2.appendChild(ib);
				   td2.appendChild(ib1);
					
					
					var tr=document.createElement("tr");
					ib.foodObj=obj;
					ib.myLineTr=tr;
					ib.addEventListener("click",function(){
						var eventSrc=event.srcElement;
						//删除当前行+发送ajax请求到后台，删除数据库
						table.removeChild(eventSrc.myLineTr);
						sendAjax("${pageContext.request.contextPath}/food/"+ib.foodObj.foodid,"POST","_method=delete",function(responseText){
							if(responseText==1)
								alert("删除成功");
							else{
								alert("删除失败");
							}
						});
						
					});
					
					ib1.foodObj=obj;
			   		ib1.addEventListener("click",function(){
				       var eventSrc=event.srcElement;
				       document.getElementById('updateDiv').style.display='block';
				       document.getElementsByName("umyFoodName")[0].value=eventSrc.foodObj.foodname;
				       document.getElementsByName("umyFoodPrice")[0].value=eventSrc.foodObj.price;
				       document.getElementsByName("umyFoodId")[0].value=eventSrc.foodObj.foodid;
			       
			   })
					
					tr.setAttribute("name","dataTr");
					tr.appendChild(td);
					tr.appendChild(td1);
					tr.appendChild(td2);
					table.appendChild(tr);
					
				}
		})
			
				
			
		
		

		
		
	}
	
	/**
  新增的方法
**/
function saveFood(){
   var myFoodName=document.getElementsByName("myFoodName")[0].value;
   var myFoodPrice=document.getElementsByName("myFoodPrice")[0].value;
   sendAjax("${pageContext.request.contextPath}/food","POST","foodName="+myFoodName+"&price="+myFoodPrice,function(responseText){
			         if(responseText==1){
			            document.getElementById('addDiv').style.display='none';
			            query();
			            alert("新增成功");
			            
			         }else{
			            alert("新增失败");
			         }
	});
   
   
}

/**
  修改的方法
**/
function updateFood(){
   var myFoodName=document.getElementsByName("umyFoodName")[0].value;
   var myFoodPrice=document.getElementsByName("umyFoodPrice")[0].value;
   var myFoodId=document.getElementsByName("umyFoodId")[0].value;
   sendAjax("${pageContext.request.contextPath}/food/"+myFoodId,"POST","_method=put&foodName="+myFoodName+"&price="+myFoodPrice,function(responseText){
			         if(responseText==1){
			            document.getElementById('updateDiv').style.display='none';
			            query();
			            alert("修改成功");
			            
			         }else{
			            alert("修改失败");
			         }
	});
   
   
}
</script>

</head>

<body>
		<input type='text' name="foodName" />
		<input type='button' value="查询" onclick="query()"><input type='button' value="新增" onclick="document.getElementById('addDiv').style.display='block';">
		<table id="myTable">  
           <tr><th>菜品名</th><th>菜品价格</th><th>操作</th></tr>
		</table>
	</body>
	
	<div id="addDiv" style="display:none;position: absolute;left:40%;top:40%;z-index: 100;border:1px solid black; width:250px;height:100px ">
	 
	 菜品名：<input type="text" name="myFoodName"><br/>
	 价&nbsp;&nbsp;&nbsp;格：<input type="text" name="myFoodPrice"><br/>
	 <input type="button" value="保存" onclick="saveFood()">&nbsp;&nbsp;&nbsp;<input type="button" value="关闭" onclick="document.getElementById('addDiv').style.display='none';" ><br/>
	</div>
	
	
	<div id="updateDiv" style="display:none;position: absolute;left:40%;top:40%;z-index: 100;border:1px solid black; width:250px;height:100px ">
	 <input type="hidden" name="umyFoodId" >
	 菜品名：<input type="text" name="umyFoodName"><br/>
	 价&nbsp;&nbsp;&nbsp;格：<input type="text" name="umyFoodPrice"><br/>
	 <input type="button" value="修改" onclick="updateFood()">&nbsp;&nbsp;&nbsp;<input type="button" value="关闭" onclick="document.getElementById('updateDiv').style.display='none';" ><br/>
	</div>
</html>
