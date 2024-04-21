package com.ocprojettrois.locationprojettree.Services.Rental;


import com.ocprojettrois.locationprojettree.Models.Rental.Dto.RentalResponseDto;
import com.ocprojettrois.locationprojettree.Models.Rental.Dto.RentalsResponseDto;
import com.ocprojettrois.locationprojettree.Models.Rental.Dto.UpdateRentalDto;
import com.ocprojettrois.locationprojettree.Models.Rental.Rental;
import com.ocprojettrois.locationprojettree.Models.Rental.RentalResponse;
import com.ocprojettrois.locationprojettree.Models.User.User;
import com.ocprojettrois.locationprojettree.Repository.Rental.RentalRepository;
import com.ocprojettrois.locationprojettree.Services.User.UserDetailsServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class RentalServiceImpl implements RentalService {

    private final RentalRepository repository;
    private final UserDetailsServiceImp userService;

    @Override
    public Stream<RentalsResponseDto> all() {
        return repository.findAll().stream().map(rental -> new RentalsResponseDto(
                rental.getId() ,
                rental.getName(),
                rental.getSurface(),
                rental.getPrice(),
                rental.getPicture(),
                rental.getDescription(),
                rental.getUser().getId(),
                rental.getCreated_at(),
                rental.getUpdated_at()
        ));
    }

    @Override
    public RentalResponseDto detail(Long id) {

        Rental rental = repository.findById(id).orElseThrow();
        RentalResponseDto response = new RentalResponseDto();
        response.setId(rental.getId());
        response.setName(rental.getName());
        response.setSurface(rental.getSurface());
        response.setPrice(rental.getPrice());
        response.setPicture(rental.getPicture());
        response.setDescription(rental.getDescription());
        User user = new User();
        user = rental.getUser();
        response.setOwner_id(user.getId());
        response.setCreated_at(rental.getCreated_at());
        response.setUpdated_at(rental.getUpdated_at());
        return response;
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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(auth.getName());
        rentalPush.setUser(user);
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
