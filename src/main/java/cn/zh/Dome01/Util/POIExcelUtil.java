package cn.zh.Dome01.Util;





import cn.zh.Dome01.entity.UserInfo;
import org.apache.poi.hssf.util.HSSFColor;


import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by Dawn on 2018/4/16.
 */
public class POIExcelUtil {
    public static String write(List<UserInfo> userinfolist, String loginuname, String path) {

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("0");

        CellStyle cellStyle = workbook.createCellStyle();
        // 设置这些样式
        cellStyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        //cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        /*cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
        cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
        cellStyle.setBorderRight(CellStyle.BORDER_THIN);
        cellStyle.setBorderTop(CellStyle.BORDER_THIN);
        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);*/
        Row row = sheet.createRow(0);
        row.createCell(0).setCellStyle(cellStyle);
        row.createCell(0).setCellValue("用户编号");

        row.createCell(1).setCellStyle(cellStyle);
        row.createCell(1).setCellValue("真实姓名");

        row.createCell(2).setCellStyle(cellStyle);
        row.createCell(2).setCellValue("用户拼音");

        row.createCell(3).setCellStyle(cellStyle);
        row.createCell(3).setCellValue("性别");

        row.createCell(4).setCellStyle(cellStyle);
        row.createCell(4).setCellValue("邮箱");

        row.createCell(5).setCellStyle(cellStyle);
        row.createCell(5).setCellValue("出生日期");

        row.createCell(6).setCellStyle(cellStyle);
        row.createCell(6).setCellValue("身份证号码");

        row.createCell(7).setCellStyle(cellStyle);
        row.createCell(7).setCellValue("联系电话");

        row.createCell(8).setCellStyle(cellStyle);
        row.createCell(8).setCellValue("用户类型");

        row.createCell(9).setCellStyle(cellStyle);
        row.createCell(9).setCellValue("班级");
        workbook.setSheetName(0, "信息");
        int i=0;


        for (UserInfo entry : userinfolist) {
            i++;
            row = sheet.createRow(i);

            row.createCell(0).setCellStyle(cellStyle);
            row.createCell(0).setCellValue(entry.getUid());

            row.createCell(1).setCellStyle(cellStyle);
            row.createCell(1).setCellValue(entry.getUname());

            row.createCell(2).setCellStyle(cellStyle);
            row.createCell(2).setCellValue(entry.getUnickname());

            row.createCell(3).setCellStyle(cellStyle);

           String ugender = entry.getUgender();
            if(ugender.equals("1")){
                row.createCell(3).setCellValue("男");
            }else {
                row.createCell(3).setCellValue("女");
            }

            row.createCell(4).setCellStyle(cellStyle);
            row.createCell(4).setCellValue(entry.getUemail());

            row.createCell(5).setCellStyle(cellStyle);
            row.createCell(5).setCellValue(entry.getUbirhtday());

            row.createCell(6).setCellStyle(cellStyle);
            row.createCell(6).setCellValue(entry.getUidentityid());

            row.createCell(7).setCellStyle(cellStyle);
            row.createCell(7).setCellValue(entry.getUphone());

            int uroleid=entry.getUroleid();
            if(uroleid==1){
                row.createCell(8).setCellValue("ROLE_USER");
            }else if (uroleid==2){
                row.createCell(8).setCellValue("教员");
            } else if (uroleid==3) {
                row.createCell(8).setCellValue("学员");
            } else if (uroleid==4) {
                row.createCell(8).setCellValue("学士后");

            }

            row.createCell(9).setCellStyle(cellStyle);
            row.createCell(9).setCellValue(entry.getUgradeid());
        }

        Date date=new Date();
        //System.out.println((date.getMonth()+1)+"月"+date.getDate()+"日"+date.getHours()+"时"+date.getMinutes()+"分");

        String filePath=path+"/"+loginuname+"B"+(date.getMonth()+1)+"M"+date.getDate()+"D"+date.getHours()+"H"+date.getMinutes()+"m"+".xlsx";
        try {
            File file = new File(filePath);
            FileOutputStream fileoutputStream = new FileOutputStream(file);
            workbook.write(fileoutputStream);
            fileoutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return filePath;

    }


    //文件下载
    public static void filedownutil(HttpServletResponse response, String returnpath) {




            /* 下载*/
        List<String> fileallname = Arrays.asList(returnpath);
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            ZipOutputStream zout = new ZipOutputStream(os);
            response.setHeader("content-disposition", "attachment;filename=" + "down.zip");
            for (String fileName : fileallname) {

                //String realPath=request.getSession().getServletContext().getRealPath("/upload");
                String txtPath = fileName;
                // realPath.concat("\\").concat(fileName);
                //显示的名字
                String xianshi = fileName.substring(fileName.lastIndexOf("/") + 1);
                //System.out.println(xianshi);
                FileInputStream fis = new FileInputStream(txtPath);
                zout.putNextEntry(new ZipEntry(URLEncoder.encode(xianshi, "utf-8")));


                byte[] bytes = new byte[1024];
                int data;
                while ((data = fis.read(bytes)) != -1) {
                    zout.write(bytes, 0, data);
                }
                fis.close();
            }
            zout.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void downGo(List<UserInfo> userinfolist, String loginuname, String path, HttpServletResponse response){
        String returnpath=write(userinfolist,loginuname,path);
        filedownutil(response,returnpath);
    }

}
