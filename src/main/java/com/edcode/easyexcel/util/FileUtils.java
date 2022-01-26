package com.edcode.easyexcel.util;

import cn.hutool.json.JSONUtil;
import com.edcode.easyexcel.entity.Student;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author eddie.lee
 * @date 2022-01-26 15:53
 * @description
 */
public class FileUtils {

  /**
   * 读取 json 文件
   *
   * @throws IOException
   */
  public static List<Student> readJsonFile() throws IOException {
    FileReader fileReader = null;
    try {
      List<Student> users = null;
      fileReader = new FileReader("src/main/resources/data/Students.json");
      int num = 0;
      char[] buf = new char[1024];
      while ((num = fileReader.read(buf)) != -1) {
        users = JSONUtil.toList(new String(buf, 0, num), Student.class);
      }
      return users;
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      assert fileReader != null;
      fileReader.close();
    }
    return new ArrayList<>();
  }

}
