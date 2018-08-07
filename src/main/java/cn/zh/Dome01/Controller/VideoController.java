package cn.zh.Dome01.Controller;


import cn.zh.Dome01.entity.Course;
import cn.zh.Dome01.entity.Video;
import cn.zh.Dome01.service.IuserInfoService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.nio.channels.FileChannel;
import java.util.*;

/**
 * Created by Dawn on 2018/5/26.
 */
@Controller
public class VideoController {

    @Resource(name = "userInfoService")
    private IuserInfoService iCourseService;

    //进入视频上传页面
    @RequestMapping("/govideoManagementJSP")
    public String govideoManagementJSP(Model model){

       // 可以查一下三级课程的信息
        try {
            List<Course> sys_courses = iCourseService.selectAllTypeThreeSys_course();
            model.addAttribute("sys_courses",sys_courses);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "videoManagement";
    }

    //增加视频
    @RequestMapping("/insertVideoItemForm")
    public String insertVideoItemForm(Video video){
        Integer flag=0;
        try {
            flag= iCourseService.insertVideoItemForm(video);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (flag>0){
            return "success";
        }else {
            return "error";
        }
    }












    private String serverPath = "F:/FileUploader";
    @RequestMapping("/FileUploadServlet")
    @ResponseBody
    public String FileUploadServlet(String fileMd5, String chunk, MultipartFile file, HttpSession session) throws IOException {

        String fileName=file.getOriginalFilename();
        System.out.println(fileName+"=====================");
        //进行路径拼接
        session.setAttribute("filename", fileName);
        File fullFile= new File(serverPath + "/" + fileMd5 + "/" + chunk);
        FileUtils.copyInputStreamToFile(file.getInputStream(), fullFile);
        return "1";

    }

    @RequestMapping("/UploadActionServlet")
    @ResponseBody
    public String UploadActionServlet(HttpServletRequest request, String fileMd5, String chunk, String chunkSize)  {
        try {
            System.out.println("进入合并后台...");
            String action = request.getParameter("action");
            if ("mergeChunks".equals(action)) {
                // 获得�?要合并的目录
                /*String fileMd5 = request.getParameter("fileMd5");*/

                // 读取目录�?有文�?
                File f = new File(serverPath + "/" + fileMd5);
                File[] fileArray = f.listFiles(new FileFilter() {

                    // 排除目录，只要文�?

                    public boolean accept(File pathname) {
                        if (pathname.isDirectory()) {
                            return false;
                        }
                        return true;
                    }
                });

                // 转成集合，便于排序
                List<File> fileList = new ArrayList<File>(Arrays.asList(fileArray));
                // 从小到大排序
                Collections.sort(fileList, new Comparator<File>() {

                    public int compare(File o1, File o2) {
                        if (Integer.parseInt(o1.getName()) < Integer.parseInt(o2.getName())) {
                            return -1;
                        }
                        return 1;
                    }

                });
                String attribute = (String) request.getSession().getAttribute("filename");
                // 新建保存文件
                File outputFile = new File(serverPath + "/" + attribute);

                // 创建文件

                outputFile.createNewFile();


                // 输出�?
                FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
                FileChannel outChannel = fileOutputStream.getChannel();

                // 合并
                FileChannel inChannel;
                for (File file : fileList) {
                    inChannel = new FileInputStream(file).getChannel();
                    inChannel.transferTo(0, inChannel.size(), outChannel);
                    inChannel.close();

                    // 删除分片
                    file.delete();
                }

                // 关闭�?
                fileOutputStream.close();
                outChannel.close();

                // 清除文件�?
                File tempFile = new File(serverPath + "/" + fileMd5);
                if (tempFile.isDirectory() && tempFile.exists()) {
                    tempFile.delete();
                }

                System.out.println("合并文件成功");

            } else if ("checkChunk".equals(action)) {
                // 校验文件是否已经上传并返回结果给前端

                // 文件唯一表示
                /*String fileMd5 = request.getParameter("fileMd5");*/
                // 当前分块下标
                /*String chunk = request.getParameter("chunk");*/
                // 当前分块大小
                /*String chunkSize = request.getParameter("chunkSize");*/

                // 找到分块文件
                File checkFile = new File(serverPath + "/" + fileMd5 + "/" + chunk);

                // �?查文件是否存在，且大小一�?
            /*response.setContentType("text/html;charset=utf-8");*/
                if (checkFile.exists() && checkFile.length() == Integer.parseInt((chunkSize))) {
                    return "{\"ifExist\":1}";
                } else {
                    return "{\"ifExist\":0}";
                }
            }
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return null;
    }
}
