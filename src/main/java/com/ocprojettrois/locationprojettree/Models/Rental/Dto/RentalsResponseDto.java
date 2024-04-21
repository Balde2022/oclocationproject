package com.ocprojettrois.locationprojettree.Models.Rental.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public record RentalsResponseDto(
        Long id,
        String name,
        Integer surface,
        Integer price,
        String picture,
        String description,
        Long owner_id,
        @JsonFormat(pattern = "yyyy/MM/dd")
        Date created_at,
        @JsonFormat(pattern = "yyyy/MM/dd")
        Date updated_at
) {
}
