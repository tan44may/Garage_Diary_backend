package com.garagediary.garagediary.dto;

import com.garagediary.garagediary.entity.enums.Status;
import lombok.Data;

@Data
public class UpdateStatusRequest {
    private Status status;
}
