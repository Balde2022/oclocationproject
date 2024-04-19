package com.ocprojettrois.locationprojettree.Models.Message.Dto;

import com.ocprojettrois.locationprojettree.Models.Rental.Rental;
import com.ocprojettrois.locationprojettree.Models.User.User;
import lombok.Data;

@Data
public class CreateMessage {
    private String message;
    private Long user_id;
    private Long rental_id;
}
