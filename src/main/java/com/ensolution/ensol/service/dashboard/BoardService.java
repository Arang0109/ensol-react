package com.ensolution.ensol.service.dashboard;


import java.util.List;
import java.util.Map;

public interface BoardService {
  Map<String, Map<String, Long>> getChartStackCount();
  Map<String, Map<String, Long>> getChartStackCountByWorkplace(List<Integer> workplaceIds);
}
