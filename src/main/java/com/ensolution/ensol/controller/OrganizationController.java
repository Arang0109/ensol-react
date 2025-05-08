package com.ensolution.ensol.controller;

import com.ensolution.ensol.dto.app.entity.oranization.StaffDto;
import com.ensolution.ensol.dto.app.entity.oranization.TeamDto;
import com.ensolution.ensol.dto.app.entity.oranization.VehicleDto;
import com.ensolution.ensol.service.organization.StaffService;
import com.ensolution.ensol.service.organization.TeamService;
import com.ensolution.ensol.service.organization.VehicleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/management/organization")
@RequiredArgsConstructor
class OrganizationController {
  
  private final TeamService teamService;
  private final StaffService staffService;
  private final VehicleService vehicleService;
  
  @Operation(
    summary = "측정팀 목록 조회",
    description = "등록된 모든 측정팀 정보를 조회합니다.")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "정상 응답",
      content = @Content(mediaType = "application/json",
      schema = @Schema(implementation = TeamDto.class))),
    @ApiResponse(responseCode = "500", description = "서버 오류")})
  @GetMapping("/teams")
  public List<TeamDto> getTeamList() { return teamService.findAllTeams(); }
  
  @Operation(
    summary = "측정인력 목록 조회",
    description = "등록된 모든 측정인력 정보를 조회합니다.")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "정상 응답",
      content = @Content(mediaType = "application/json",
      schema = @Schema(implementation = StaffDto.class))),
    @ApiResponse(responseCode = "500", description = "서버 오류")})
  @GetMapping("/staffs")
  public List<StaffDto> getStaffList() { return staffService.findAllStaffs(); }
  
  @Operation(
    summary = "차량 목록 조회",
    description = "등록된 모든 차량 정보를 조회합니다.")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "정상 응답",
      content = @Content(mediaType = "application/json",
      schema = @Schema(implementation = VehicleDto.class))),
    @ApiResponse(responseCode = "500", description = "서버 오류")})
  @GetMapping("/vehicles")
  public List<VehicleDto> getVehicleList() { return vehicleService.findAllVehicles(); }
}
