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
      var id= $("#ugradeid").val();
      var startime = $("[name=startime]").val();
      var endtime =$("[name=endtime]").val();
     // this.ugradeid=id;
     $.ajax({
         url:"${pageContext.request.contextPath}/classattendances",
         type:"POST",
         data:{"ugradeid":id,
             "startime":startime,
             "endtime":endtime
         },
         success:function (data) {
             $("#classatts").html('');
             $.each(data,function (i,dom) {

                 $("#classatts").append("<tr>");
                 $("#classatts").append("<td><input type='checkbox' value="+ +" class='deletecheck'/></td>");

                 $("#classatts").append("<td>"+dom.uid+"</td>");
                 $("#classatts").append("<td>"+dom.uname+"</td>");
                 if(dom.attendance==1){
                     $("#classatts").append("<td>出勤</td>");
                 }else if(dom.attendance==2){
                     $("#classatts").append("<td>请假</td>");
                 }else if(dom.attendance==3){
                     $("#classatts").append("<td>迟到</td>");
                 }else if(dom.attendance==4){
                     $("#classatts").append("<td>缺勤</td>");
                 }
                 $("#classatts").append("<td>"+dom.stroperatedatetime+" </td>");
                 $("#classatts").append("</tr>");
             })

     }
  })
  }

  option = {
      title : {
          text: '班级出勤情况表',
          subtext: '班级数据'
      },
      tooltip : {
          trigger: 'axis'
      },
      legend: {
          data:[
              '出勤','请假','',
              '迟到','缺勤'
          ]
      },
      toolbox: {
          show : true,
          feature : {
              mark : {show: true},
              dataView : {show: true, readOnly: false},
              magicType : {show: true, type: ['line', 'bar']},
              restore : {show: true},
              saveAsImage : {show: true}
          }
      },
      calculable : true,
      grid: {y: 70, y2:30, x2:20},
      xAxis : [
          {
              type : 'category',
              data : ['Line','Bar','Scatter','K','Map']
          },
          {
              type : 'category',
              axisLine: {show:false},
              axisTick: {show:false},
              axisLabel: {show:false},
              splitArea: {show:false},
              splitLine: {show:false},
              data : ['Line','Bar','Scatter','K','Map']
          }
      ],
      yAxis : [
          {
              type : 'value',
              axisLabel:{formatter:'{value} ms'}
          }
      ],
      series : [
          {
              name:'出勤',
              type:'bar',
              itemStyle: {normal: {color:'rgba(193,35,43,1)', label:{show:true}}},
              data:[40,155,95,75, 0]
          },
          {
              name:'请假',
              type:'bar',
              itemStyle: {normal: {color:'rgba(181,195,52,1)', label:{show:true,textStyle:{color:'#27727B'}}}},
              data:[100,200,105,100,156]
          },
          {
              name:'迟到',
              type:'bar',
              itemStyle: {normal: {color:'rgba(252,206,16,1)', label:{show:true,textStyle:{color:'#E87C25'}}}},
              data:[906,911,908,778,0]
          },
          {
              name:'缺勤',
              type:'bar',
              xAxisIndex:1,
              itemStyle: {normal: {color:'rgba(193,35,43,0.5)', label:{show:true,formatter:function(p){return p.value > 0 ? (p.value +'\n'):'';}}}},
              data:[96,224,164,124,0]
          }

      ]
  };





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
            班级：<select id="ugradeid">
            <c:forEach items="${classlists}" var="item">
                <option value="${item.ugradeid}">${item.classes}</option>
            </c:forEach>
        </select>
            开始时间：<input type="date" name="startime" style="border: solid;border-color: #5bc0de" />
            结束时间：<input type="date" name="endtime" style="border: solid;border-color: #5bc0de" />
            <input type="button" value="查询" style="border: solid;border-color: #5bc0de;width: 50px;height: 30px;" onclick="myclass()"/>

    </div>

    
    <table class="tablelist">
    	<thead>
    	<tr>
        <th><input name="" type="checkbox" value="" checked="checked"/></th>
        <th><i class="sort"><img src="${pageContext.request.contextPath}/background/images/px.gif" /></i>学号</th>

            <th>学员姓名</th>
            <th>出勤情况</th>
            <th>出勤时间</th>


        </tr>
        </thead>
      <tbody id="classatts">
          <%--<c:forEach items="${classatts}" var="item">
              <tr >
                  <td><input name="" type="checkbox" value="" checked="checked"/></td>
              &lt;%&ndash;    <td>${item.lid}</td>&ndash;%&gt;
                  <td></td>
                  <td>${item.uid}</td>
                  <td>${item.uname}</td>
                  <td>
                     <c:if test="${item.attendance==1}">
                         <input value="出勤"/>
                     </c:if>
                      <c:if test="${item.attendance==2}">
                          <input value="请假"/>
                      </c:if>
                      <c:if test="${item.attendance==3}">
                          <input value="迟到"/>
                      </c:if>
                      <c:if test="${item.attendance==4}">
                          <input value="缺勤"/>
                      </c:if>
                  </td>
                  <td>${item.stroperatedatetime}</td>

              </tr>
          </c:forEach>--%>
      </tbody>


    </table>
    
   
    <div class="pagin">
    	<%--<div class="message">共<i class="blue">1256</i>条记录，当前显示第&nbsp;<i class="blue">2&nbsp;</i>页</div>
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
        </ul>--%>
    </div>
    

    
    </div>
    
    
    
    
    </div>


    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');

	</script>
</body>
</html>
