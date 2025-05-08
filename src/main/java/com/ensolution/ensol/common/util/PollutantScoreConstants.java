package com.ensolution.ensol.common.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PollutantScoreConstants {
  public static final double DUST_SCORE = 1.0;
  public static final double METAL_SCORE = 1.3;
  public static final double HG_SCORE = 1.2;
  public static final double AS_SCORE = 1.0;
  public static final double ETC_SCORE = 2.0;
  public static final double G1_SCORE = 1.0;
  public static final double G2_SCORE = 0.5;
  public static final double G3_SCORE = 0.3;

  public static final double PARTICLE_DELTA = 0.4;
  public static final double AS_METAL_DELTA = 0.6; // 중금속, 비소가 동시에 존재하는 경우
  public static final double HG_DELTA = 0.2;
  public static final double G1_DELTA = 0.5;
  public static final double G2_DELTA = 0.2;
  public static final Set<String> G3_LIST = new HashSet<>(List
      .of("현장측정", "카트리지", "흡착관(T)", "흡착관(A)", "테드라백"));
}
