package com.ocprojettrois.locationprojettree.Models.Message;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ocprojettrois.locationprojettree.Models.Rental.Rental;
import com.ocprojettrois.locationprojettree.Models.User.User;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;
@Entity
@Table(name = "user")
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "rental_id")
    private Rental rental;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String message;
    @CreatedDate
    @Column(nullable = false,updatable = false)
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date created_at;
    @LastModifiedDate
    @Column(insertable = false)
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date updated_at;
}
