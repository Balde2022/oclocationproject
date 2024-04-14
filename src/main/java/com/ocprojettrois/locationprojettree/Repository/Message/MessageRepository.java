package com.ocprojettrois.locationprojettree.Repository.Message;

import com.ocprojettrois.locationprojettree.Models.Message.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message,Long> {
}
