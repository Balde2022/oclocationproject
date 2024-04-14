package com.ocprojettrois.locationprojettree.Models.Rental.Dto;

import lombok.Data;

@Data
public class CreateRentalDto {
    private String name;
    private Integer surface;
    private Integer price;
    private String picture;
    private String description;
}
