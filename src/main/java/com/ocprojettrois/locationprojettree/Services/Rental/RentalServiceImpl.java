package com.ocprojettrois.locationprojettree.Services.Rental;


import com.ocprojettrois.locationprojettree.Models.Rental.Dto.UpdateRentalDto;
import com.ocprojettrois.locationprojettree.Models.Rental.Rental;
import com.ocprojettrois.locationprojettree.Models.Rental.RentalResponse;
import com.ocprojettrois.locationprojettree.Repository.Rental.RentalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RentalServiceImpl implements RentalService {

    private final RentalRepository repository;

    @Override
    public List<Rental> all() {
        return repository.findAll();
    }

    @Override
    public Optional<Rental> detail(Long id) {
        return repository.findById(id);
    }

    @Override
    public RentalResponse create(MultipartFile file ,
                                 String name ,
                                 Integer surface,
                                 Integer price,
                                 String description) throws IOException {
        Path path = Paths.get(System.getProperty("user.home"), "rental-images","images");
        if(!Files.exists(path)){
            Files.createDirectories(path);
        }

        String rentalsImagesId = UUID.randomUUID().toString();

        Path filePath = Paths.get(System.getProperty("user.home"), "rental-images","images" ,rentalsImagesId+".jpg" );
        Files.copy(file.getInputStream(),filePath);
        Rental rentalPush= new Rental();

        rentalPush.setName(name);
        rentalPush.setSurface(surface);
        rentalPush.setPrice(price);
        rentalPush.setDescription(description);
        rentalPush.setPicture(filePath.toUri().toString());
        Date date = new Date();
        rentalPush.setCreated_at(date);
        rentalPush.setUpdated_at(date);
        rentalPush.setUser(rentalPush.getUser());
        repository.save(rentalPush);
        RentalResponse response = new RentalResponse();
        response.setMessage("Rental created !");
        return response;
    }

    @Override
    public RentalResponse update(Long id, UpdateRentalDto rental) {
        return repository.findById(id).map(
                p -> {
                    p.setName(rental.getName());
                    p.setSurface(rental.getSurface());
                    p.setPrice(rental.getPrice());
                    p.setDescription(rental.getDescription());
                    p.setPicture(p.getPicture());
                    Date date = new Date();
                    p.setCreated_at(p.getCreated_at());
                    p.setUpdated_at(date);
                    p.setUser(p.getUser());
                    p.setId(p.getId());
                    repository.save(p);
                    RentalResponse response = new RentalResponse();
                    response.setMessage("Rental updated !");
                    return response;
                }
        ).orElseThrow();
    }

    @Override
    public Optional<Rental> findById(Long id) {
        return repository.findById(id);
    }
}
