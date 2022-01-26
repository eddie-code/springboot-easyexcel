package com.edcode.easyexcel.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import javax.servlet.http.HttpServletResponse;

/**
 * @author eddie.lee
 * @date 2022-01-26 15:54
 * @description
 */
public class EasyExcelUtils {

  private static final String CONTENT_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

  private static final String CHARACTER_ENCODING = "utf-8";

  /**
   * 设置excel下载响应头属性
   */
  public static void setExcelRespProp(HttpServletResponse response, String rawFileName)
      throws UnsupportedEncodingException {
    response.setContentType(CONTENT_TYPE);
    response.setCharacterEncoding(CHARACTER_ENCODING);
    String fileName = URLEncoder.encode(rawFileName, CHARACTER_ENCODING).replaceAll("\\+", "%20");
    response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
  }

}
