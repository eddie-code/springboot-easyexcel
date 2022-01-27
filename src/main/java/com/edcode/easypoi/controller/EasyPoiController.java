package com.edcode.easypoi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.edcode.easypoi.entity.Teacher2;
import com.edcode.easypoi.service.SheetsService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author eddie.lee
 * @date 2022-01-26 17:16
 * @description https://easypoi.mydoc.io/#text_202979
 */
@Controller
@RequestMapping("/easyPoi")
@RequiredArgsConstructor
public class EasyPoiController {

  private final SheetsService sheetsService;

  @GetMapping("export")
  public void export(HttpServletResponse response) throws IOException {
    try {
      List<Map<String, Object>> sheetsList = sheetsService.getEasypoiData();
      setWorkbook(response, sheetsList);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  private void setWorkbook(HttpServletResponse response, List<Map<String, Object>> sheetsList)
      throws IOException {
    // 导出显示文件名称
    String excelName = "excelName.xls";
    // 设置响应输出的头类型
    response.setHeader("content-Type", "application/vnd.ms-excel");
    // 下载文件的默认名称
    response.setHeader("Content-Disposition", "attachment;filename=" + excelName);
    // exportExcel 传 List<Map<String, Object>>
    Workbook workbook = ExcelExportUtil.exportExcel(sheetsList, ExcelType.HSSF);
    ServletOutputStream outputStream = response.getOutputStream();
    workbook.write(outputStream);
    outputStream.flush();
    outputStream.close();
  }

  /**
   * 复杂导入使用
   * @param file
   * @return
   * @throws Exception
   */
  @PostMapping("/importExcel")
  @ResponseBody
  public List<Map<String, Object>> importExcel(@RequestParam("file") MultipartFile file) throws Exception {
    ImportParams params = new ImportParams();
    params.setTitleRows(0);
    params.setHeadRows(3);
    params.setNeedVerify(true);
    List<Map<String, Object>> list = ExcelImportUtil.importExcel(
        file.getInputStream(),
        Map.class,
        params);
    System.out.println(JSONUtil.toJsonStr(list));
    return list;
  }

  /**
   * 简单的表格导入
   * @param file
   * @return
   */
  @PostMapping("/importExcel2")
  @ResponseBody
  public List<Teacher2> importExcel2(@RequestParam("file") MultipartFile file) {
    ImportParams params = new ImportParams();
    params.setTitleRows(0);
    //头行忽略的行数
    params.setHeadRows(1);
    //是否开启校验
    params.setNeedVerify(true);
    try {
      ExcelImportResult<Teacher2> result = ExcelImportUtil.importExcelMore(
          file.getInputStream(),
          Teacher2.class,
          params);
      List<Teacher2> list = result.getList().stream().map(item -> {
        Teacher2 t2 = new Teacher2();
        BeanUtil.copyProperties(item, t2);
        return t2;
      }).collect(Collectors.toList());
      System.out.println(JSONUtil.toJsonStr(list));
      return list;
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return new ArrayList<>();
  }
}
