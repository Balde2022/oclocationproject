package com.ocprojettrois.locationprojettree.Models.Rental.Dto;

import java.util.Date;

public record RentalsResponseDto(
        Long id,
        String name,
        Integer surface,
        Integer price,
        String picture,
        String description,
        Long owner_id,
        Date created_at,
        Date updated_at
) {
}
