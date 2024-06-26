package com.ocprojettrois.locationprojettree.Models.Rental;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ocprojettrois.locationprojettree.Models.Message.Message;
import com.ocprojettrois.locationprojettree.Models.User.User;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Entity
@Data
@Table(name = "rentals")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer surface;
    private Integer price;
    private String picture;
    private String description;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User user;
    @CreatedDate
    @Column(nullable = false,updatable = false)
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date created_at;
    //@LastModifiedDate
    //@Column(insertable = false)
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date updated_at;
}
