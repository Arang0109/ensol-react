package com.ensolution.ensol.common.util;

import java.util.Map;

public class ChartDataResponse {
  private Map<String, Long> completeStats;
  private Map<String, Long> nonCompleteStats;

  public ChartDataResponse(Map<String, Long> completeStats, Map<String, Long> nonCompleteStats) {
    this.completeStats = completeStats;
    this.nonCompleteStats = nonCompleteStats;
  }

  public Map<String, Long> getCompleteStats() {
    return completeStats;
  }

  public void setCompleteStats(Map<String, Long> completeStats) {
    this.completeStats = completeStats;
  }

  public Map<String, Long> getNonCompleteStats() {
    return nonCompleteStats;
  }

  public void setNonCompleteStats(Map<String, Long> nonCompleteStats) {
    this.nonCompleteStats = nonCompleteStats;
  }

  @Override
  public String toString() {
    return "ChartDataResponse{" +
        "completeStats=" + completeStats +
        ", nonCompleteStats=" + nonCompleteStats +
        '}';
  }
}
