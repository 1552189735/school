<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="utf-8" language="java" isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>青鸟信息管理平台</title>
<%--
<link href="${pageContext.request.contextPath}/background/css/style.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/background/pagenation/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/background/pagenation/jquery-1.12.4.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/background/pagenation/jquery.pagination.js"></script>
--%>
    <link href="${pageContext.request.contextPath}/background/css/style.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/background/pagination/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/background/js/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/background/pagination/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/background/pagination/jquery.pagination.js"></script>

<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/background/js/jquery.js"></script>--%>

<script type="text/javascript">

function check() {

}

    function getdata(index) {
        //发送一个Ajax请求
        $.ajax({
            url:"${pageContext.request.contextPath}/getpageJson",
            type:"POST",
            data:{"indexpage":index,"pagesize":2,"uname":$("[name=uname]").val()},
            success:function (data) {
                $("#userList").html('');
                $.each(data.lists,function (i,dom) {
                    //每个dom就是一个用户对象
                   /* var userInfo="<tr><td>"+dom+" "+dom.uid+"</td><td>"+dom.uname+"</td><td>"+dom.unickname+"</td><td>"+dom.ugender+"</td><td>"+dom.ugradeid+"</td></tr>";
                    $("#userList").append(userInfo);*/
                    $("#userList").append("<tr>");
                    $("#userList").append("<td><input type='checkbox' value="+dom.uid+" class='deletecheck'/></td>");
                    $("#userList").append("<td>"+dom.uid+"</td>");
                    $("#userList").append("<td>"+dom.uname+"</td>");
                    $("#userList").append("<td>"+dom.unickname+"</td>");
                    $("#userList").append("<td>"+dom.ugender+"</td>");
                    $("#userList").append("<td>"+dom.ugradeid+"</td>");
                  /*  $("#tabletbodyuser").append("<td>"+dom.strbirthday+"</td>");
                    $("#tabletbodyuser").append("<td>"+dom.uemail+"</td>");*/
                    $("#userList").append("<td><a href='${pageContext.request.contextPath}/findone?uid="+dom.uid+"' class='tablelink'>修改</a> </td>");
                    $("#userList").append("<td><a href='${pageContext.request.contextPath}/delone?uid="+dom.uid+"' class='tablelink'>删除</a> </td>");
                    $("#userList").append("</tr>");

                });
                //渲染分页
                if (data.totalRecords){
                $('#pagination').pagination(data.totalRecords, {
                    current_page : index,
                    items_per_page : data.pagesize,
                    num_display_entries : 1,
                    num_edge_entries:1,
                    ellipse_text:'...',
                    callback:getdata,
                    load_first_page : true,
                    prev_text : '上一页',
                    next_text : '下一页'
                });

            }else{
                $("#userList").html('没有检索到记录！！！');
            }
                //总页数
                $("#totalRecords").html(data.totalRecords);
            }
        });
    }

        $(document).ready(function(){

            getdata(0)
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

    	<ul class="toolbar">
        <li class="click"><a href="${pageContext.request.contextPath}/background/form.jsp"><span><img src="${pageContext.request.contextPath}/background/images/t01.png" /></span>添加</a></li>

            <li><span>姓名:<input type="text" name="uname"/></span><span><input id="mysel" type="button" value="查询" onclick=" getdata(0);" style="background-color: #F5F7F9;font-size: 18px;"/></span></li>
        <%-- <li class="click"><span><img src="${pageContext.request.contextPath}/background/images/t02.png" /></span>修改</li>
        <li><span><img src="${pageContext.request.contextPath}/background/images/t03.png" /></span>删除</li>m
        <li><span><img src="${pageContext.request.contextPath}/background/images/t04.png" /></span>统计</li>--%>
        </ul>
      <%--  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;姓名:<input type="text" name="uname"/><span><input type="button" value="查询"/></span>--%>
        <ul class="toolbar1">
        <li><a href="${pageContext.request.contextPath}/partExport">导出文件</a></li>
        <li><a href="${pageContext.request.contextPath}/PoiExcelinto">导入文件</a></li>
        </ul>
    </div>

      <%--  <div class="pagination" id="pagination" style="margin:4px 0 0 0"></div>--%>
    <table class="tablelist">
    	<thead>
    	<tr>
        <th><input name="" type="checkbox" value="" checked="checked"/></th>
        <th>学号<i class="sort"><img src="${pageContext.request.contextPath}/background/images/px.gif" /></i></th>
        <th>姓名</th>
        <th>姓名拼音</th>
        <th>性别</th>
        <th>班级</th>
        <th>操作</th>
            <th>操作</th>
        </tr>
        </thead>

        <tbody id="userList">
        <%--<c:forEach items="${userlist}" var="item">
            <tr >
                <td><input name="" type="checkbox" value="" checked="checked"/></td>
                <td>${item.uid}</td>
                <td>${item.uname}</td>
                <td>${item.unickname}</td>
                <td>${item.ugender}</td>
                <td>${item.ugradeid}</td>
                <td><a href="${pageContext.request.contextPath}/findone?uid=${item.uid}" >修改</a></td>
                <td><a href="${pageContext.request.contextPath}/delone?uid=${item.uid}" >删除</a></td>
            </tr>
        </c:forEach>--%>
        </tbody>

    </table>


    <div class="pagin">
    	<div class="message">共<i class="blue" id="totalRecords"></i>条记录，当前显示第&nbsp;<i class="blue">2&nbsp;</i>页</div>
        <div class="pagination" id="pagination" style="margin:4px 0 0 0"></div>

    </div>
    
    
    <div class="tip" id="files">
    	<div class="tiptop"><span>提示信息</span><a></a></div>
        
      <div class="tipinfo">
        <span><img src="${pageContext.request.contextPath}/background/images/ticon.png" /></span>
        <div class="tipright">
            <form action="" method="post" enctype="multipart/form-data">
                请选择文件：<input type="file" name="myfile"/>
            </form>
        <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
        </div>
        </div>
        
        <div class="tipbtn">

            <input name="" type="button"  class="sure" value="确定" onclick="check()"/>&nbsp;
            <input name="" type="button"  class="cancel" value="取消" />
        </div>
    
    </div>
    
    
    
    
    </div>
    
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>
</body>
</html>
