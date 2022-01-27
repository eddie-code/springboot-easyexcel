package com.edcode.easypoi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * @author eddie.lee
 * @date 2022-01-26 21:43
 * @description
 */
@Data
@ExcelTarget("teacher2")
public class Teacher2 {

  @Excel(name = "序号")
  private Integer id;

  @Excel(name = "教师姓名")
  private String empName;

  @Excel(name = "年龄")
  private Integer age;

  @Excel(name = "入职时间", format = "yyyy/MM/dd HH:mm")
  private Date entryTime;

  @Excel(name = "薪酬")
  private BigDecimal salary;

}
