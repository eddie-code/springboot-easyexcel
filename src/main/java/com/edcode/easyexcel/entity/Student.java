package com.edcode.easyexcel.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.edcode.easyexcel.service.converter.GenderConverter;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author eddie.lee
 * @date 2022-01-26 14:43
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

  @ExcelProperty("ID")
  @ColumnWidth(10)
  private Long id;

  @ExcelProperty("用户名")
  @ColumnWidth(20)
  private String username;

  @ExcelIgnore
  private String password;

  @ExcelProperty("昵称")
  @ColumnWidth(20)
  private String nickname;

  @ExcelProperty("出生日期")
  @ColumnWidth(20)
  @DateTimeFormat("yyyy-MM-dd")
  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date birthday;

  @ExcelProperty("手机号")
  @ColumnWidth(20)
  private String mobile;

  /** 0->男，1->女 **/
  @ExcelProperty(value = "性别", converter = GenderConverter.class)
  @ColumnWidth(10)
  private Integer gender;
}
