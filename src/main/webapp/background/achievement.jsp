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
             <h2>xxxx班的xxxxc</h2>
             <table>
                 <tr>
                     <td>姓名</td>
                     <td>成绩</td>
                 </tr>
              <tr>
                  <c:forEach items="allreses" var="item">
                      <td>${item.name}</td>
                      <td>${item.achievenemt}</td>
                  </c:forEach>
              </tr>
             </table>

        </div>
    </div>
</body>
</html>