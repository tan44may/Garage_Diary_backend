package com.garagediary.garagediary.Controller;

import com.garagediary.garagediary.dto.UserDto;
import com.garagediary.garagediary.service.VehicleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/api/service-center/vehicle")
@RestController
@AllArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getVehicleOwner(@PathVariable UUID id)
    {
        return ResponseEntity.ok(vehicleService.getOwner(id));
    }


}
