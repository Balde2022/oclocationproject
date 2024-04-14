package com.ocprojettrois.locationprojettree.Controllers.Message;

import com.ocprojettrois.locationprojettree.Models.Message.Dto.CreateMessage;
import com.ocprojettrois.locationprojettree.Models.Message.Dto.MessageResponse;
import com.ocprojettrois.locationprojettree.Models.Rental.Dto.CreateRentalDto;
import com.ocprojettrois.locationprojettree.Models.Rental.RentalResponse;
import com.ocprojettrois.locationprojettree.Services.Message.MessageService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/messages")
@Tag(name = "messages")
@SecurityRequirement(name = "bearerAuth")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping()
    public MessageResponse create(CreateMessage message){
        return messageService.create(message);
    }
}
