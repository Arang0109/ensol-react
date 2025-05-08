package com.ensolution.ensol.dto.app.query;

import java.util.Objects;

public class BoardDto {
  private String stack_measurement_ids;
  private Integer stack_id;
  private String pollutant_ids;
  private Integer workplace_id;
  private String stack_name;
  private String cycle_type;
  private boolean is_completed;

  public BoardDto() {}

  public BoardDto(String stack_measurement_ids, Integer stack_id, String pollutant_ids, Integer workplace_id, String stack_name, String cycle_type, boolean is_completed) {
    this.stack_measurement_ids = stack_measurement_ids;
    this.stack_id = stack_id;
    this.pollutant_ids = pollutant_ids;
    this.workplace_id = workplace_id;
    this.stack_name = stack_name;
    this.cycle_type = cycle_type;
    this.is_completed = is_completed;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    BoardDto that = (BoardDto) o;
    return is_completed == that.is_completed && Objects.equals(stack_measurement_ids, that.stack_measurement_ids) && Objects.equals(stack_id, that.stack_id) && Objects.equals(pollutant_ids, that.pollutant_ids) && Objects.equals(workplace_id, that.workplace_id) && Objects.equals(stack_name, that.stack_name) && Objects.equals(cycle_type, that.cycle_type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(stack_measurement_ids, stack_id, pollutant_ids, workplace_id, stack_name, cycle_type, is_completed);
  }

  public String getStack_measurement_ids() {
    return stack_measurement_ids;
  }

  public void setStack_measurement_ids(String stack_measurement_ids) {
    this.stack_measurement_ids = stack_measurement_ids;
  }

  public Integer getStack_id() {
    return stack_id;
  }

  public void setStack_id(Integer stack_id) {
    this.stack_id = stack_id;
  }

  public String getPollutant_ids() {
    return pollutant_ids;
  }

  public void setPollutant_ids(String pollutant_ids) {
    this.pollutant_ids = pollutant_ids;
  }

  public Integer getWorkplace_id() {
    return workplace_id;
  }

  public void setWorkplace_id(Integer workplace_id) {
    this.workplace_id = workplace_id;
  }

  public String getStack_name() {
    return stack_name;
  }

  public void setStack_name(String stack_name) {
    this.stack_name = stack_name;
  }

  public String getCycle_type() {
    return cycle_type;
  }

  public void setCycle_type(String cycle_type) {
    this.cycle_type = cycle_type;
  }

  public boolean is_completed() {
    return is_completed;
  }

  public void setIs_completed(boolean is_completed) {
    this.is_completed = is_completed;
  }

  @Override
  public String toString() {
    return "StatisticsDto{" +
        "stack_measurement_ids='" + stack_measurement_ids + '\'' +
        ", stack_id=" + stack_id +
        ", pollutant_ids='" + pollutant_ids + '\'' +
        ", workplace_id=" + workplace_id +
        ", stack_name='" + stack_name + '\'' +
        ", cycle_type='" + cycle_type + '\'' +
        ", is_completed=" + is_completed +
        '}';
  }
}
