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
    
    <div class="formtitle"><span>基本信息</span></div>
    <form action="${pageContext.request.contextPath}/updateone" method="post">
    <ul class="forminfo">
    <input name="uid" type="hidden" class="dfinput" value="${onelist.uid}" />
    <li><label>姓名</label><input name="uname" type="text" class="dfinput" value="${onelist.uname}" required placeholder="请输入正确的姓名"/><i>${unamemsg}</i></li>
        <li><label>姓名拼音</label><input name="unickname" type="text" class="dfinput" value="${onelist.unickname}" /></li>
        <li><label>性别</label><cite><input name="ugender" type="radio" value="1" checked="checked" />男&nbsp;&nbsp;&nbsp;&nbsp;<input name="ugender" type="radio" value="2" />女</cite></li>
        <li>
           <c:if test="${onelist==null}">
            <label>密码</label><input name="upwd" type="text" class="dfinput" value="${onelist.upwd} " required placeholder="请输入密码"/><i>${upwdmsg}</i>
        </c:if>
           <c:if test="${onelist!=null}">
                <input name="upwd" type="hidden" class="dfinput" value="${onelist.upwd} " /><i>${upwdmsg}</i>
            </c:if>
        </li>
        <li><label>身份证号</label><input name="uidentityid" type="text" class="dfinput" value="${onelist.uidentityid}" required placeholder="请输入正确的身份证号"/><i>${message}</i></li>
        <li><label>邮箱</label><input name="uemail" type="text" class="dfinput" value="${onelist.uemail}" required placeholder="请输入正确的邮箱号"/><i>${message}</i></li>
        <li><label>手机号</label><input name="uphone" type="text" class="dfinput" value=" ${onelist.uphone}" required placeholder="请输入正确的手机号"/><i>${message}</i></li>
        <li><label>年级</label><input name="ugradeid" type="text" class="dfinput"  value="${onelist.ugradeid}" /></li>
        <li><label>出生日期</label><input name="ubirhtday" type="date" class="dfinput" value="${onelist.ubirhtday}"  required placeholder="请输入正确的出生日期"/><i>${message}</i></li>
        <li><label>类型</label><cite><input name="uroleid" type="radio" value="1" checked="checked" />管理员&nbsp;&nbsp;&nbsp;&nbsp;<input name="uroleid" type="radio" value="2" />老师&nbsp;&nbsp;&nbsp;&nbsp;<input name="uroleid" type="radio" value="3" />学生</cite></li>
    <li><label>&nbsp;</label><input name="" type="submit" class="btn" value="确认保存"/></li>
    </ul>
    </form>
    
    </div>
</body>
</html>
