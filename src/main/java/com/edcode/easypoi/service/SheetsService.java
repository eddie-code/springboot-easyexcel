package com.edcode.easypoi.service;

import java.util.List;
import java.util.Map;

/**
 * @author eddie.lee
 * @date 2022-01-26 17:35
 * @description
 */
public interface SheetsService {

  /**
   * 获取伪数据
   *
   * @return list
   */
  List<Map<String, Object>> getEasypoiData();

}
