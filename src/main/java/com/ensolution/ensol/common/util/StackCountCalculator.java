package com.ensolution.ensol.common.util;

import com.ensolution.ensol.dto.app.query.BoardDto;
import com.ensolution.ensol.repository.app.mybatis.BoardMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class StackCountCalculator {
  private final String[] CYCLE_TYPE = {"monthly", "twiceamonth", "quarterly", "semiannual", "annual"};
  private final List<BoardDto> stacks;
  private final List<BoardDto> stacksByWorkplace;

  public StackCountCalculator(BoardMapper boardMapper, List<Integer> workplaceIds) {
    this.stacks = boardMapper.selectAll();
    this.stacksByWorkplace = boardMapper.selectByWorkplace(workplaceIds);
  }

  private Map<String, Long> nonComplete(List<BoardDto> stack) {
    Map<String, Long> stackCnt = new HashMap<>();
    for (String cycle : CYCLE_TYPE) {
      stackCnt.put(
          cycle, // 주기
          stack // 시설 수
              .stream()
              .filter(Objects::nonNull)
              .filter(s -> !s.is_completed())
              .filter(s -> s.getCycle_type().equals(cycle))
              .count());
    }
    return stackCnt;
  }

  private Map<String, Long> complete(List<BoardDto> stack) {
    Map<String, Long> stackCnt = new HashMap<>();
    for (String cycle : CYCLE_TYPE) {
      stackCnt.put(
          cycle, // 주기
          stack // 시설 수
              .stream()
              .filter(Objects::nonNull)
              .filter(BoardDto::is_completed)
              .filter(s -> s.getCycle_type().equals(cycle))
              .count());
    }
    return stackCnt;
  }

  public Map<String, Long> stackCnt() {
    return nonComplete(stacks);
  }

  public Map<String, Long> stackCntByWorkplace() {
    return nonComplete(stacksByWorkplace);
  }

  public Map<String, Long> completedStackCnt() {
    return complete(stacks);
  }

  public Map<String, Long> completedStackCntByWorkplace() {
    return complete(stacksByWorkplace);
  }
}
