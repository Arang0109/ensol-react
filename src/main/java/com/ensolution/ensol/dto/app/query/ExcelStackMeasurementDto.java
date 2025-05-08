package com.ensolution.ensol.dto.app.query;

public class ExcelStackMeasurementDto {
  private Integer workplace_id;
  private String stack_name;
  private String pollutant_name;
  private Double allow_value;
  private String cycle_type;

  public ExcelStackMeasurementDto() {}

  public ExcelStackMeasurementDto(Integer workplace_id, String stack_name, String pollutant_name, Double allow_value, String cycle_type) {
    this.workplace_id = workplace_id;
    this.stack_name = stack_name;
    this.pollutant_name = pollutant_name;
    this.allow_value = allow_value;
    this.cycle_type = cycle_type;
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

  public String getPollutant_name() {
    return pollutant_name;
  }

  public void setPollutant_name(String pollutant_name) {
    this.pollutant_name = pollutant_name;
  }

  public Double getAllow_value() {
    return allow_value;
  }

  public void setAllow_value(Double allow_value) {
    this.allow_value = allow_value;
  }

  public String getCycle_type() {
    return cycle_type;
  }

  public void setCycle_type(String cycle_type) {
    this.cycle_type = cycle_type;
  }

  @Override
  public String toString() {
    return "ExcelStackMeasurementDto{" +
        "workplace_id=" + workplace_id +
        ", stack_name='" + stack_name + '\'' +
        ", pollutant_name='" + pollutant_name + '\'' +
        ", allow_value=" + allow_value +
        ", cycle_type='" + cycle_type + '\'' +
        '}';
  }
}
