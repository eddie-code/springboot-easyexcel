package com.edcode.easypoi.service.impl;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.edcode.easypoi.entity.School;
import com.edcode.easypoi.entity.Teacher;
import com.edcode.easypoi.service.SheetsService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.springframework.stereotype.Service;

/**
 * @author eddie.lee
 * @date 2022-01-26 17:37
 * @description
 */
@Service
public class SheetsServiceImpl implements SheetsService {

  @Override
  public List<Map<String, Object>> getEasypoiData() {

    Random random=new Random();

    List<School> exportList = new LinkedList<>();

    School school = new School();
    school.setId(1);
    school.setSubject("计算机科");
    List<Teacher> ll = new LinkedList<>();
    for (int i = 0; i < 10; i++) {
      if (i % 2 == 0) {
        Teacher teacher = new Teacher();
        teacher.setId(i);
        teacher.setAge(random.nextInt(90)+10);
        teacher.setEmpName("张三" + i);
        teacher.setSalary(BigDecimal.valueOf(random.nextInt(100)+100));
        teacher.setEntryTime(new Date());
        ll.add(teacher);
      } else {
        Teacher teacher2 = new Teacher();
        teacher2.setId(i);
        teacher2.setAge(random.nextInt(90)+10);
        teacher2.setEmpName("王五" + i);
        teacher2.setSalary(BigDecimal.valueOf(random.nextInt(100)+100));
        teacher2.setEntryTime(new Date());
        ll.add(teacher2);
      }
    }
    school.setTeachers(ll);

    School school2 = new School();
    school2.setId(2);
    school2.setSubject("英语课");
    List<Teacher> lt = new LinkedList<>();
    for (int i = 0; i < 10; i++) {
      if (i % 2 == 0) {
        Teacher teacher = new Teacher();
        teacher.setId(i);
        teacher.setAge(random.nextInt(90)+10);
        teacher.setEmpName("张三" + i);
        teacher.setSalary(BigDecimal.valueOf(random.nextInt(100)+100));
        teacher.setEntryTime(new Date());
        lt.add(teacher);
      } else {
        Teacher teacher2 = new Teacher();
        teacher2.setId(i);
        teacher2.setAge(random.nextInt(90)+10);
        teacher2.setEmpName("王五" + i);
        teacher2.setSalary(BigDecimal.valueOf(random.nextInt(100)+100));
        teacher2.setEntryTime(new Date());
        lt.add(teacher2);
      }
    }
    school2.setTeachers(lt);

    exportList.add(school);
    exportList.add(school2);
    ExportParams exportParams = new ExportParams();
    // 设置sheet得名称
    exportParams.setSheetName("员工报表1");

    Map<String, Object> exportMap = new HashMap<>(16);
    // title的参数为ExportParams类型，目前仅仅在ExportParams中设置了sheetName
    exportMap.put("title", exportParams);
    // 模版导出对应得实体类型
    exportMap.put("entity", School.class);
    // sheet中要填充得数据
    exportMap.put("data", exportList);

    List<Map<String, Object>> sheetsList = new ArrayList<>();
    sheetsList.add(exportMap);
    return sheetsList;
  }

}
