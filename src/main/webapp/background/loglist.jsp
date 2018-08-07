<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="utf-8" language="java" isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>网站后台管理系统HTML模板--模板之家 www.cssmoban.com</title>
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
    $(function () {
        $("#mysel").bind("click",function () {
            getdata(0);
        });
    })
 function getdata(index) {

     var uname= $("[name=uname]").val();
     var startime = $("[name=startime]").val();
     var endtime =$("[name=endtime]").val();
     //alert(uname)
        //发送一个Ajax请求
        $.ajax({
            url:"${pageContext.request.contextPath}/getpagesJson",
            type:"POST",
            data:{
                "indexpage":index,
                "pagesize":5,
                "uname":uname,
                "startime":startime,
                "endtime":endtime
            },
            success:function (data) {
                $("#loglists").html('');
                $.each(data.lists,function (i,dom) {
                    //每个dom就是一个用户对象

                    $("#loglists").append("<tr>");
                    $("#loglists").append("<td><input type='checkbox' value="+dom.uid+" class='deletecheck'/></td>");
                    $("#loglists").append("<td>"+dom.lid+"</td>");
                    $("#loglists").append("<td>"+dom.uid+"</td>");
                    $("#loglists").append("<td>"+dom.uname+"</td>");
                    $("#loglists").append("<td>"+dom.operateinfo+"</td>");
                    $("#loglists").append("<td>"+dom.stroperatedatetime+"</td>");
                   $("#loglists").append("<td><!--<a href='#' class='tablelink'>-->删除<!--</a> --></td>");
                    $("#loglists").append("</tr>");

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
                $("#loglists").html('没有检索到记录！！！');
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
          <li>  用户名：<input type="text" name="uname"/></li>
            <li>  开始时间：<input type="date" name="startime"/></li>
            <li>  结束时间：<input type="date" name="endtime"/></li>
            <li><input type="button" value="查询" onclick="getdata(0)" style="background-color: #F5F7F9;font-size: 18px;"/></li>
        </ul>
        <ul class="toolbar1">
        <li><a href="${pageContext.request.contextPath}/partExport">导出文件</a></li>
        </ul>
    </div>

      <%--  <div class="pagination" id="pagination" style="margin:4px 0 0 0"></div>--%>
    <table class="tablelist">
    	<thead>
    	<tr>
        <th><input name="" type="checkbox" value="" checked="checked"/></th>
        <th>流水号<i class="sort"><img src="${pageContext.request.contextPath}/background/images/px.gif" /></i></th>
        <th>登录用户编号</th>
        <th>登录用户名</th>
        <th>登录操作</th>
        <th>操作时间</th>
        <th>操作</th>
        </tr>
        </thead>

        <tbody id="loglists">
       <%-- <c:forEach items="${loglists}" var="item">
            <tr >
                <td><input name="" type="checkbox" value="" checked="checked"/></td>
                <td>${item.lid}</td>
                <td>${item.uid}</td>
                <td>${item.uname}</td>
                <td>${item.operateinfo}</td>
                <td>${item.stroperatedatetime}</td>
               &lt;%&ndash; <td><a href="${pageContext.request.contextPath}/findone?uid=${item.uid}" >修改</a></td>&ndash;%&gt;
                <td><a href="#" >删除</a></td>
            </tr>
        </c:forEach>--%>
        </tbody>

    </table>


    <div class="pagin">
    	<div class="message">共<i class="blue" id="totalRecords"></i>条记录，当前显示第&nbsp;<i class="blue">2&nbsp;</i>页</div>
        <div class="pagination" id="pagination" style="margin:4px 0 0 0"></div>

    </div>
    
    

    
    
    
    
    </div>
    
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>
</body>
</html>
