<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="Authorize" uri="http://www.springsecurity.org/jsp" %>
<%@page pageEncoding="utf-8" language="java" isELIgnored="false" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>青鸟信息平台</title>
<link href="${pageContext.request.contextPath}/background/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/background/js/jquery.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/background/jquery-easyui-1.3.3/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/background/jquery-easyui-1.3.3/themes/icon.css">
    <%--引入jquery--%>
    <script type="text/javascript" src="${pageContext.request.contextPath}/background/jquery-easyui-1.3.3/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/background/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/background/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
    function myclass() {
        var uname= $("[name=uname]").val();
        var startime = $("[name=startime]").val();
        var endtime =$("[name=endtime]").val();
        $.ajax({
            url:"${pageContext.request.contextPath}/oneattendances",
            type:"POST",
            data:{
                "uname":uname,
                "startime":startime,
                "endtime":endtime
            },
            success:function (data) {
                $("#oneattendance").html('');
                $.each(data,function (i,dom) {

                    $("#oneattendance").append("<tr>");
                    $("#oneattendance").append("<td><input type='checkbox' value="+ +" class='deletecheck'/></td>");

                    $("#oneattendance").append("<td>"+dom.uid+"</td>");
                    $("#oneattendance").append("<td>"+dom.uname+"</td>");
                    if(dom.attendance==1){
                        $("#oneattendance").append("<td>出勤</td>");
                    }else if(dom.attendance==2){
                        $("#oneattendance").append("<td>请假</td>");
                    }else if(dom.attendance==3){
                        $("#oneattendance").append("<td>迟到</td>");
                    }else if(dom.attendance==4){
                        $("#oneattendance").append("<td>缺勤</td>");
                    }
                    $("#classatts").append("<td>"+dom.stroperatedatetime+" </td>");
                    $("#classatts").append("</tr>");
                })

            }
        })
    }
$(document).ready(function(){
  $(".click").click(function(){
  $(".tip").fadeIn(200);
  });
  
  $(".tiptop a").click(function(){
  $(".tip").fadeOut(200);
});

  $(".sure").click(function(){
  $(".tip").fadeOut(100);
});

  $(".cancel").click(function(){
  $(".tip").fadeOut(100);
});

});
</script>


</head>


<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">数据表</a></li>
    <li><a href="#">基本内容</a></li>
    </ul>
    </div>
    
    <div class="rightinfo">
    
    <div class="tools">
            姓名：<input type="text" name="uname" style="border: solid;border-color: #5bc0de" />
            开始时间：<input type="date" name="startime" style="border: solid;border-color: #5bc0de" />
            结束时间：<input type="date" name="endtime" style="border: solid;border-color: #5bc0de" />
            <input type="button" value="查询" style="border: solid;border-color: #5bc0de;width: 50px;height: 30px;" onclick="myclass()"/>

    </div>

    
    <table class="tablelist">
    	<thead>
    	<tr>
        <th><input name="" type="checkbox" value="" checked="checked"/></th>
        <th><i class="sort"><img src="${pageContext.request.contextPath}/background/images/px.gif" /></i></th>
            <th>学号</th>
        <th>学员姓名</th>
            <th>出勤情况</th>
            <th>出勤时间</th>


        </tr>
        </thead>
      <tbody id="oneattendance">

      </tbody>


    </table>
    
   
    <div class="pagin">

    </div>
    

    
    </div>
    
    
    
    
    </div>


    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');

	</script>
</body>
</html>
