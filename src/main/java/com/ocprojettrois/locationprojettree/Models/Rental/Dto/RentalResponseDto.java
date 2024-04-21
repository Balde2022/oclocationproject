package com.ocprojettrois.locationprojettree.Models.Rental.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.util.Date;
@Data
public class RentalResponseDto {
    private Long id;
    private String name;
    private Integer surface;
    private Integer price;
    private String picture;
    private String description;
    private Long owner_id;
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date created_at;
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date updated_at;
}
