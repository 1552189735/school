<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="utf-8" language="java" isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${pageContext.request.contextPath}/background/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">表单</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle"><span>成绩查询</span></div>

        <div align="top" style="font-size: 20px">
            <form action="" method="post">
              姓名：<input type="text" name="xname" required placeholder="请输入要查询的学员的姓名" style="height: 30px;width: 150px;border: solid;color: #00a2d4"/>
              班级：<input type="text" name="gname" required placeholder="请输入要查询的学员的班级"style="height: 30px;width: 150px;border: solid;color: #00a2d4"/>
              时间：<input type="date" name="dname" style="height: 30px;width: 150px;border: solid;color: #00a2d4"/>
            <input type="submit" value="查询" style="height: 30px;width: 50px;background-color: #D4E7F0"/>
            </form>
        </div>
        <div style="height: 30px">

        </div>
        <div style="width: 700px;" >
        <div align="left" style="width: 200px;float: left">
            <form action="${pageContext.request.contextPath}/titlebyid" method="post">
    <ul>
        <li>查询历史成绩</li>
        <li>请选择班级<select name="ugradeid">
            <option>--请选择--</option>
             <c:forEach items="${classes}" var="item">
            <option value="${item.ugradeid}">
                 ${item.classes}
            </option>
             </c:forEach>
        </select>

        </li>
    </ul>
              <div style="height: 50px"></div>


                <input type="submit" value="生成" style="height: 30px;width: 50px;background-color: #D4E7F0"/>
            </form>
    </div>
        <div align="top" style="float: left" >
              <table width="500px">
                  <tr>
                      <td width="250px">测试标题</td>
                      <td width="250px">测试时间</td>
                  </tr>
                  <c:forEach items="${alltitles}" var="item">
                      <tr>
                          <td><a href="${pageContext.request.contextPath}/allres">${item.title}</a></td>
                          <td>${item.stroperatedatetime}</td>
                      </tr>
                  </c:forEach>
              </table>
        </div>
        </div>
    </div>
</body>
</html>