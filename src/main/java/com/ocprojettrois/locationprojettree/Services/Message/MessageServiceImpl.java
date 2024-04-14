package com.ocprojettrois.locationprojettree.Services.Message;

import com.ocprojettrois.locationprojettree.Models.Message.Dto.CreateMessage;
import com.ocprojettrois.locationprojettree.Models.Message.Dto.MessageResponse;
import com.ocprojettrois.locationprojettree.Models.Message.Message;
import com.ocprojettrois.locationprojettree.Repository.Message.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService{

    private final MessageRepository repository;
    @Override
    public MessageResponse create(CreateMessage message) {
        Message messagePush = new Message();
        messagePush.setMessage(message.getMessage());
        messagePush.setUser(message.getUser_id());
        messagePush.setRental(message.getRental_id());
        repository.save(messagePush);
        MessageResponse response = new MessageResponse();
        response.setMessage("Message send with success");
        return response;
    }
}
