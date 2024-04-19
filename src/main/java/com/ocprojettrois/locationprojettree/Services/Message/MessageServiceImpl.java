package com.ocprojettrois.locationprojettree.Services.Message;

import com.ocprojettrois.locationprojettree.Models.Message.Dto.CreateMessage;
import com.ocprojettrois.locationprojettree.Models.Message.Dto.MessageResponse;
import com.ocprojettrois.locationprojettree.Models.Message.Message;
import com.ocprojettrois.locationprojettree.Models.Rental.Rental;
import com.ocprojettrois.locationprojettree.Models.User.User;
import com.ocprojettrois.locationprojettree.Repository.Message.MessageRepository;
import com.ocprojettrois.locationprojettree.Services.Rental.RentalServiceImpl;
import com.ocprojettrois.locationprojettree.Services.User.UserDetailsServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService{

    private final MessageRepository repository;
    private final UserDetailsServiceImp userService;
    private final RentalServiceImpl rentalService;
    @Override
    public MessageResponse create(CreateMessage message) {
        Message messagePush = new Message();
        messagePush.setMessage(message.getMessage());
        Optional<User> user = userService.findById(message.getUser_id());
        messagePush.setUser(user.get());
        Optional<Rental> rental = rentalService.findById(message.getRental_id());
        messagePush.setRental(rental.get());
        Date date = new Date();
        messagePush.setCreated_at(date);
        messagePush.setUpdated_at(date);
        repository.save(messagePush);
        MessageResponse response = new MessageResponse();
        response.setMessage("Message send with success");
        return response;
    }
}
