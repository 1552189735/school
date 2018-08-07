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
    var roleadd;
    /*权限弹窗*/
    //读懂
    function change(data) {
        if(!data.length){
            data.text=data.name;
            if(data.children){
                change(data.children);
            }
        }else{
           $.each(data,function(i,v){
               change(v);
           })
        }
    }
    function openprivilege(rid) {
        this.roleadd=rid;
        $("#myprivilege").dialog("open").dialog("setTitle","分配权限")
        $("#tt").tree({
            //请求的后台地址
            url:'${pageContext.request.contextPath}/getjson?rid='+rid,
            checkbox:true,
            animate:true,
            cascadeCheck:false,
            loadFilter:
                function(data){
                change(data);
                $.each(data,function (i,v) {
                    v.iconCls="icon-folder";
                })
                    return data;
                },
              //将角色对应权限显示出来
             onLoadSuccess:function (){
             $.ajax({
             type:"post",
             url:"/roleprivilege",
             data:{"rid":row['rid']},
             datatype:"json",
             success:function(data){
             $.each(data,function(i,dom){
             var checkedNode=$('#tt').tree('find',dom.id);
             $('#tt').tree('check',checkedNode.treegrid());
             })

             }
             })
             }
        })
    }
    function getChecked()

    {

        var arr = [];

        var checkeds=$('#tt').tree('getChecked','checked');

        for (var i=0;checkeds.length>i;i++) {

            arr.push(checkeds[i].id);

        }

        $.ajax({
            traditional: true,
            url:"${pageContext.request.contextPath}/addrolep",
            type:"post",
            data:{"rid":roleadd,"pid":arr},
            success:function (data) {
                window.location.href='${pageContext.request.contextPath}/findrole'
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
    
    	<ul class="toolbar">
        <li class="click"><a href="${pageContext.request.contextPath}/background/form.jsp"><span><img src="${pageContext.request.contextPath}/background/images/t01.png" /></span>添加</a></li>
        <%--<li class="click"><span><img src="${pageContext.request.contextPath}/background/images/t02.png" /></span>修改</li>
        <li><span><img src="${pageContext.request.contextPath}/background/images/t03.png" /></span>删除</li>
        <li><span><img src="${pageContext.request.contextPath}/background/images/t04.png" /></span>统计</li>
        </ul>--%>
        
        
    <%--    <ul class="toolbar1">
        <li><span><img src="${pageContext.request.contextPath}/background/images/t05.png" /></span>设置</li>
        </ul>--%>
    
    </div>
        <Authorize:authorize URL="/role/addRole">
            <a href="/role/addRole">添加角色</a>
        </Authorize:authorize>
    
    <table class="tablelist">
    	<thead>
    	<tr>
        <th><input name="" type="checkbox" value="" checked="checked"/></th>
        <th>角色编号<i class="sort"><img src="${pageContext.request.contextPath}/background/images/px.gif" /></i></th>
        <th>角色姓名</th>

        <th>操作</th>

        </tr>
        </thead>

        <tbody>
        <c:forEach items="${rolelists}" var="item">
             <tr >
                 <td><input name="" type="checkbox" value="" checked="checked"/></td>
                 <td>${item.rid}</td>
                 <td>${item.roleName}</td>

                 <td><a href="javascript:openprivilege(${item.rid})" >权限分配</a>
                <a href="${pageContext.request.contextPath}/delonerole?rid=${item.rid}" >删除</a></td>
             </tr>
             </c:forEach>
        </tbody>

    </table>
    
   
    <div class="pagin">
    	<div class="message">共<i class="blue">1256</i>条记录，当前显示第&nbsp;<i class="blue">2&nbsp;</i>页</div>
        <ul class="paginList">
        <li class="paginItem"><a href="javascript:;"><span class="pagepre"></span></a></li>
        <li class="paginItem"><a href="javascript:;">1</a></li>
        <li class="paginItem current"><a href="javascript:;">2</a></li>
        <li class="paginItem"><a href="javascript:;">3</a></li>
        <li class="paginItem"><a href="javascript:;">4</a></li>
        <li class="paginItem"><a href="javascript:;">5</a></li>
        <li class="paginItem more"><a href="javascript:;">...</a></li>
        <li class="paginItem"><a href="javascript:;">10</a></li>
        <li class="paginItem"><a href="javascript:;"><span class="pagenxt"></span></a></li>
        </ul>
    </div>
    
    
    <div class="tip">
    	<div class="tiptop"><span>提示信息</span><a></a></div>
        
      <div class="tipinfo">
        <span><img src="${pageContext.request.contextPath}/background/images/ticon.png" /></span>
        <div class="tipright">
        <p>是否确认对信息的修改 ？</p>
        <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
        </div>
        </div>
        
        <div class="tipbtn">
        <input name="" type="button"  class="sure" value="确定" />&nbsp;
        <input name="" type="button"  class="cancel" value="取消" />
        </div>
    
    </div>
    
    
    
    
    </div>


    <%--权限的弹层--%>
    <div id="myprivilege" class="easyui-dialog" style="width: 350px;height: 500px;padding: 10px 20px" closed="true">
       权限分配：
        <ul id="tt"></ul>
        <a href="#" class="easyui-linkbutton" onclick="getChecked()">保存权限</a>
    </div>
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');

	</script>
</body>
</html>
