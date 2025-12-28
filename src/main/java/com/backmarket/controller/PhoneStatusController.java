package com.backmarket.controller;

import com.backmarket.dto.ApiResponse;
import com.backmarket.entity.PhoneStatus;
import com.backmarket.service.PhoneStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/phone_status")
public class PhoneStatusController {

    @Autowired
    private PhoneStatusService phoneStatusService;

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getAllStatus(
            @RequestParam(required = false) Integer id
    ) {
        if (id != null) {
            Optional<PhoneStatus> status = phoneStatusService.getStatusById(id);
            if (status.isPresent()) {
                return ResponseEntity.ok(new ApiResponse<>(
                        false, "ok", status
                ));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(
                        false, "Status not found.", null
                ));
            }
        }
        return ResponseEntity.ok(new ApiResponse<>(
                false, "ok", phoneStatusService.getAllStatus()
        ));
    }

}
