package com.ocprojettrois.locationprojettree.Controllers.Message;

import com.ocprojettrois.locationprojettree.Models.Message.Dto.CreateMessage;
import com.ocprojettrois.locationprojettree.Models.Message.Dto.MessageResponse;
import com.ocprojettrois.locationprojettree.Services.Message.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/messages")
@Tag(name = "messages")
@SecurityRequirement(name = "bearerAuth")
@CrossOrigin(origins = "http://localhost:4200")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @Operation(
            description = "Get endpoint for create a comment",
            summary = "Créer un commentaire pour une annonce",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403"
                    )
            }
    )
    @PostMapping()
    public MessageResponse create(CreateMessage message){
        return messageService.create(message);
    }
}
