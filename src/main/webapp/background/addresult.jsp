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
    
    <div class="formtitle"><span>添加成绩</span></div>
        <div align="top" style="font-size: 20px">

                试题标题：<input type="text" name="xname" required placeholder="请输入试题标题" style="height: 30px;width: 150px;border: solid;color: #00a2d4"/>
                考试类型：<input type="text" name="gname" required placeholder="请输入要查询的学员的班级"style="height: 30px;width: 150px;border: solid;color: #00a2d4"/>
                考试时间： 开始时间：<input type="date" name="startime" style="height: 30px;width: 150px;border: solid;color: #00a2d4" />
            结束时间：<input type="date" name="endtime" style="height: 30px;width: 150px;border: solid;color: #00a2d4"/>
            <form action="" method="post">
            考试班级：<select>

            </select>
                <input type="submit" value="生成" style="height: 30px;width: 50px;background-color: #D4E7F0"/>
            </form>

                <input type="submit" value="查询" style="height: 30px;width: 50px;background-color: #D4E7F0"/>

        </div>

    </div>
</body>
</html>
