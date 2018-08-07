<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="utf-8" language="java" isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>无标题文档</title>
    <link href="${pageContext.request.contextPath}/background/css/style.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/background/pagination/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/background/js/jquery.js"></script>
    <script type="text/javascript">
        var uidarr=[];

        var gradeid;
        function getid(){
            var id= $("#grade").val();
            this.gradeid=id;
            $.ajax({
                url:"${pageContext.request.contextPath}/alluser",
                type:"POST",
                data:{"ugradeid":id},
                success:function (data) {
                    $("#clist").html('');
                    $.each(data,function (i,dom) {
                        uidarr[i]=(dom.uid)
                        $("#clist").append("<tr id='mycolor'>");
                        $("#clist").append("<td><input type='checkbox' value="+dom.uid+" class='deletecheck'/></td>");
                        $("#clist").append("<td>"+dom.uid+"</td>");
                        $("#clist").append("<td>"+dom.uname+"</td>");
                        $("#clist").append("<td class='onecolor'>"+ +"</td>");
                        $("#clist").append("<td><a href='#' class='tablelink'>删除</a> </td>");
                        $("#clist").append("</tr>");
                    })
                    $(document).ready(function(){
                        $(".onecolor").css("background-color","green");
                        $(".onecolor").html("出勤")

                        $(".onecolor").toggle(function () {
                            $(this).attr("style","background:greenyellow")
                            $(this).html("迟到")
                        },function () {
                            $(this).attr("style","background:yellow")
                            $(this).html("请假")
                        },function () {
                            $(this).attr("style","background:red")
                            $(this).html("缺勤")
                        },function () {
                            $(this).attr("style","background:green")
                            $(this).html("出勤")
                        });

                    })
                }
            });
        }
        function getattendance() {
            attendance = [];
            //循环获取所有已选中的复选框值

            $.each($(".onecolor"), function (i, item) {
                if(item.style.backgroundColor=="red"){
                    attendance[i] = 4;
                }else if(item.style.backgroundColor=="yellow"){
                    attendance[i]=3;
                }else if(item.style.backgroundColor=="greenyellow") {
                    attendance[i] =2;
                }else if(item.style.backgroundColor=="green") {
                    attendance[i] =1;
                }
            });


            $.ajax({
                url:"${pageContext.request.contextPath}/countattendance",
                traditional: true,
                type:"POST",
                data:{"ugradeid":gradeid},
                success:function (data) {
                    if(data>0){
                        alert("已有出勤情况！")
                    }else{
                        $.ajax({
                            url:"${pageContext.request.contextPath}/addattendance",
                            traditional: true,
                            type:"POST",
                            data:{"uidarr":uidarr,"attendance":attendance},
                            success:function (data) {
                                if(data=="1"){
                                    alert("已成功保存出勤情况！")
                                }
                            }
                        })

                    }

                }
            })
            //alert("studentarr"+uidarr);
            // alert("attendance"+attendance);


        }



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

    </script>
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
    <div class="tools">
        <form action="${pageContext.request.contextPath}/findclass" method="post">
            所属班级：<select id="grade" >
            <c:forEach items="${classlists}" var="item">
                <option value="${item.ugradeid}">${item.classes}</option>
            </c:forEach>
        </select>
            <input type="button" value="生成" id="mystu" onclick="getid()" style="background-color: #F5F7F9;font-size: 15px;background-color: #F5F7F9;border: solid;border-color: #5bc0de"/>
        </form>
    </div>
    <table class="tablelist">
        <thead>
        <tr>
            <th><input name="" type="checkbox" value="" checked="checked"/></th>
            <th>学号<i class="sort"><img src="${pageContext.request.contextPath}/background/images/px.gif" /></i></th>
            <th>姓名</th>
            <th>出勤情况</th>
            <th>操作</th>
        </tr>
        </thead>

        <tbody id="clist">

        </tbody>

    </table>
    <input type="button" value="保存考勤状态" onclick="getattendance()" style="width: 100;height: 50px;font-size: 15px;background-color: #F5F7F9;border: solid;border-color: #5bc0de"/>
</div>
</body>
</html>
