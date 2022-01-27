package com.edcode.easypoi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author eddie.lee
 * @date 2022-01-26 17:11
 * @description 教师信息
 */
@Data
@ExcelTarget("teacher")
public class Teacher {

  @Excel(name = "序号", width = 30, isColumnHidden = true)
  private Integer id;

  @Excel(name = "教师姓名", width = 30, groupName = "基本信息")
  private String empName;

  @Excel(name = "年龄", width = 30, type = 10, groupName = "基本信息")
  private Integer age;

  @Excel(name = "入职时间", width = 30, groupName = "工作信息", format = "yyyy/MM/dd HH:mm")
  private Date entryTime;

  @Excel(name = "薪酬", width = 30, type = 10, groupName = "工作信息")
  private BigDecimal salary;

}
