package com.ocprojettrois.locationprojettree.Services.Message;

import com.ocprojettrois.locationprojettree.Models.Message.Dto.CreateMessage;
import com.ocprojettrois.locationprojettree.Models.Message.Dto.MessageResponse;
import com.ocprojettrois.locationprojettree.Models.Rental.Dto.CreateRentalDto;

public interface MessageService{
    MessageResponse create(CreateMessage message);
}
