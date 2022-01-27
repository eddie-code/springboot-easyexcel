package com.edcode.easyexcel.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.edcode.easyexcel.entity.Student;
import com.edcode.easyexcel.util.EasyExcelUtils;
import com.edcode.easyexcel.util.FileUtils;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author eddie.lee
 * @date 2022-01-26 15:11
 * @description EasyExcel导入导出测试
 */
@Controller
@RequestMapping("/easyExcel")
public class EasyExcelController {

  /**
   * 导出会员列表Excel
   *
   * @param response
   */
  @SneakyThrows(IOException.class)
  @GetMapping("/exportStudentList")
  public void exportStudentList(HttpServletResponse response) {
    String title = "学生列表";
    EasyExcelUtils.setExcelRespProp(response, title);
    List<Student> studentList = FileUtils.readJsonFile();
    // 写Excel
    EasyExcel.write(response.getOutputStream())
        .head(Student.class)
        .excelType(ExcelTypeEnum.XLSX)
        .sheet(title)
        .doWrite(studentList);
  }

  /**
   * 从Excel导入会员列表
   *
   * @param file
   * @return
   */
  @SneakyThrows(Exception.class)
  @PostMapping("/importStudentList")
  @ResponseBody
  public List<Student> importStudentList(@RequestPart("file") MultipartFile file) {
    return EasyExcel.read(file.getInputStream())
        .head(Student.class)
        .sheet()
        .doReadSync();
  }

}
