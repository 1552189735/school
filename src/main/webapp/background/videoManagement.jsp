<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="utf-8" language="java" isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>上传视频</title>
<link href="${pageContext.request.contextPath}/background/css/style.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/background/layui/css/layui.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/background/jquery-easyui-1.3.3/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/background/duandian/js/webuploader.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/background/fileupdown/jquery-2.1.4.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/background/fileupdown/webuploader.js"></script>
    <style type="text/css">
        #dndArea {
            width: 200px;
            height: 100px;
            border-color: red;
            border-style: dashed;
        }
    </style>
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
        <!-- 2.创建页面元素 -->
        <div id="uploader">
            <!-- 创建用于拖拽的区域 -->
            <div id="dndArea" style="border: 3px solid red">可以往里拖</div>

            <!-- 用于显示文件列表 -->
            <div id="fileList"></div>
            <!-- 用于选择文件 -->
            <div id="filePicker">文件上传</div>
        </div>


    <ul class="forminfo">
        <form action="${pageContext.request.contextPath}/insertVideoItemForm" method="post">
        <li>

        </li>
        <li><input name="vvideourl" id="shangchuanvname" required  type="text" style="border: dodgerblue 1px solid" readonly="readonly" /></li>
        <li><label>请选择视频</label><input name="tVname" type="file" /></li>
        <li><label>所属分类</label>
            <select name="cid">
                <c:forEach items="${sys_courses}" var="item">
                    <option value="${item.cid}">${item.cname}</option>
                </c:forEach>
            </select>
        </li>
        <li><label>请输入视频名称</label><input name="vname" type="text" required  /></li>
        <li><label>&nbsp;</label><input type="submit" class="btn" value="上传"/></li>
        </form>
    </ul>

    
    </div>

    <script type="text/javascript">
        /*function  checkfile() {
            var path= $("[type=file]").val();
            if(path.substring(path.lastIndexOf('.')+1)!="mp4"&& path.substring(path.lastIndexOf('.')+1)!="mp4"){
                $("#msg").html("请选择一个mp4文件");
                alert(path.substring(path.lastIndexOf('.')+1));
                return false;
            }
            return true;
        }
        $(function () {
            $("form").submit(function () {
                return checkfile();
            });
        })*/
    </script>
    <script type="text/javascript">
        // 监听分块上传的时间点，断点续传
        var fileMd5;
        WebUploader.Uploader.register({
                "before-send-file":"beforeSendFile",
                "before-send":"beforeSend",
                "after-send-file":"afterSendFile"
            },{
                beforeSendFile:function(file) {
                    // 创建一个deffered,用于通知是否完成操作
                    var deferred = WebUploader.Deferred();

                    // 计算文件的唯一标识，用于断点续传和妙传
                    (new WebUploader.Uploader()).md5File(file, 0, 5*1024*1024)
                        .progress(function(percentage){
                            $("#"+file.id).find("span.state").text("正在获取文件信息...");
                        })
                        .then(function(val) {
                            fileMd5 = val;
                            $("#" + file.id).find("span.state").text("成功获取文件信息");
                            // 放行
                            deferred.resolve();
                        });
                    // 通知完成操作
                    return deferred.promise();
                },
                beforeSend:function(block) {
                    var deferred = WebUploader.Deferred();

                    // 支持断点续传，发送到后台判断是否已经上传过
                    $.ajax(
                        {
                            type:"POST",
                            url:"${pageContext.request.contextPath}/UploadActionServlet?action=checkChunk",
                            data:{
                                // 文件唯一表示
                                fileMd5:fileMd5,
                                // 当前分块下标
                                chunk:block.chunk,
                                // 当前分块大小
                                chunkSize:block.end-block.start
                            },
                            dataType:"json",
                            success:function(response) {
                                if(response.ifExist) {
                                    // 分块存在，跳过该分块
                                    deferred.reject();
                                } else {
                                    // 分块不存在或不完整，重新发送
                                    deferred.resolve();
                                }
                            }
                        }
                    );


                    // 发送文件md5字符串到后台
                    this.owner.options.formData.fileMd5 = fileMd5;
                    return deferred.promise();
                },
                afterSendFile:function() {
                    // 通知合并分块
                    $.ajax(
                        {
                            type:"POST",
                            url:"${pageContext.request.contextPath}/UploadActionServlet?action=mergeChunks",
                            data:{
                                fileMd5:fileMd5
                            },
                            success:function(response){

                            }
                        }
                    );
                }
            }
        );

        // 上传基本配置
        var uploader = WebUploader.create(
            {
                swf:"${pageContext.request.contextPath }/background/duandian/js/Uploader.swf",
                server:"${pageContext.request.contextPath }/FileUploadServlet",
                pick:"#filePicker",
                auto:true,
                dnd:"#dndArea",
                disableGlobalDnd:true,
                paste:"#uploader",

                // 分块上传设置
                // 是否分块
                chunked:true,
                // 每块文件大小（默认5M）
                chunkSize:5*1024*1024,
                // 开启几个并非线程（默认3个）
                threads:3,
                // 在上传当前文件时，准备好下一个文件
                prepareNextFile:true
            }
        );

        // 生成缩略图和上传进度
        uploader.on("fileQueued", function(file) {
                // 把文件信息追加到fileList的div中
                $("#fileList").append("<div id='" + file.id + "'><img/><span>" + file.name + "</span><div><span class='state'></span></div><div><span class='percentage'></span></div></div>");
                $("#shangchuanvname").val(file.name)
                // 制作缩略图
                // error：不是图片，则有error
                // src:代表生成缩略图的地址
                uploader.makeThumb(file, function(error, src) {
                    /* if (error) {
                     $("#" + file.id).find("img").replaceWith("<span>无法预览&nbsp;</span>");
                     } else {
                     $("#" + file.id).find("img").attr("src", src);
                     } */
                });
            }
        );

        // 监控上传进度
        // percentage:代表上传文件的百分比
        uploader.on("uploadProgress", function(file, percentage) {
            $("#" + file.id).find("span.percentage").text(Math.round(percentage * 100) + "%");
        });

    </script>
</body>
</html>
