package com.ensolution.ensol.service.dashboard.impl;

import com.ensolution.ensol.common.util.StackCountCalculator;
import com.ensolution.ensol.repository.app.mybatis.BoardMapper;
import com.ensolution.ensol.service.dashboard.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BoardServiceImpl implements BoardService {
  private final BoardMapper boardMapper;

  @Autowired
  public BoardServiceImpl(BoardMapper boardMapper) {
      this.boardMapper = boardMapper;
    }

  @Override
  public Map<String, Map<String, Long>> getChartStackCount() {
    Map<String, Map<String, Long>> result = new HashMap<>();
    List<Integer> workplaceIds = new ArrayList<>();
    workplaceIds.add(1);
    StackCountCalculator sc = new StackCountCalculator(boardMapper, workplaceIds);

    result.put("allCnt", sc.stackCnt());
    result.put("allCompleteCnt", sc.completedStackCnt());

    return result;
  }

  @Override
  public Map<String, Map<String, Long>> getChartStackCountByWorkplace(List<Integer> workplaceIds) {
    Map<String, Map<String, Long>> result = new HashMap<>();
    StackCountCalculator sc = new StackCountCalculator(boardMapper, workplaceIds);

    result.put("workplaceCnt", sc.stackCntByWorkplace());
    result.put("workplaceCompleteCnt", sc.completedStackCntByWorkplace());

    return result;
  }
}
