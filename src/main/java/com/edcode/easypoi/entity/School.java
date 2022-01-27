package com.edcode.easypoi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author eddie.lee
 * @date 2022-01-26 17:10
 * @description 学校信息
 */
@Data
@ExcelTarget("school")
public class School {

  @Excel(name = "编号", width = 30 , needMerge = true)
  private Integer id;

  @Excel(name = "教学科目", width = 30 , needMerge = true)
  private String subject;

  @ExcelCollection(name = "教师信息")
  private List<Teacher> teachers;

}
