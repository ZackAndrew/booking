package com.zack.booking.dto;

import com.zack.booking.enums.ResourceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResourceUpdateDto {
    private String name;
    private String description;
    private ResourceType type;
}
