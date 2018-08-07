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
    
    <div class="formtitle"><span>密码修改</span></div>
    <form action="${pageContext.request.contextPath}/updatepwd" method="post">
    <ul class="forminfo">
    <input name="uid" type="hidden" class="dfinput" value="${onelist.uid}" />
    <li><label>姓名</label><input name="uname" type="text" class="dfinput" value="${onelist.uname}" /></li>

        <li><label>密码</label><input name="upwd" type="text" class="dfinput" value="${onelist.upwd} " /></li>

    <li><label>&nbsp;</label><input name="" type="submit" class="btn" value="确认保存"/></li>
    </ul>
    </form>
    
    </div>
</body>
</html>
