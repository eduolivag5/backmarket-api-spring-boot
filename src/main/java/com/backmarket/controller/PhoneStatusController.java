package com.backmarket.controller;

import com.backmarket.dto.ApiResponseDto;
import com.backmarket.entity.PhoneStatus;
import com.backmarket.service.PhoneStatusService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@Tag(name="Status")
@RequestMapping("/phone_status")
public class PhoneStatusController {

    @Autowired
    private PhoneStatusService phoneStatusService;

    /* GET */
    @Operation(
            summary = "Get phone statuses",
            description = "Retrieve all phone statuses or a single status by its ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request completed successfully."),
            @ApiResponse(responseCode = "404", description = "Phone status not found.",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error.",
                    content = @Content)
    })
    @GetMapping
    public ResponseEntity<ApiResponseDto<?>> getAllStatus(
            @RequestParam(required = false) Integer id
    ) {
        if (id != null) {
            Optional<PhoneStatus> status = phoneStatusService.getStatusById(id);

            if (status.isPresent()) {
                return ResponseEntity.ok(
                        new ApiResponseDto<>(false, "OK", status)
                );
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ApiResponseDto<>(true, "Phone status not found.", null)
                );
            }
        }

        return ResponseEntity.ok(
                new ApiResponseDto<>(false, "OK", phoneStatusService.getAllStatus())
        );
    }
}
