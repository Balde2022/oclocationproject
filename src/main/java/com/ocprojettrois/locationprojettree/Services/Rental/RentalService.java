package com.ocprojettrois.locationprojettree.Services.Rental;


import com.ocprojettrois.locationprojettree.Models.Rental.Dto.RentalResponseDto;
import com.ocprojettrois.locationprojettree.Models.Rental.Dto.RentalsResponseDto;
import com.ocprojettrois.locationprojettree.Models.Rental.Dto.UpdateRentalDto;
import com.ocprojettrois.locationprojettree.Models.Rental.Rental;
import com.ocprojettrois.locationprojettree.Models.Rental.RentalResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface RentalService {
    Stream<RentalsResponseDto> all();
    RentalResponseDto detail(Long id);
    RentalResponse create(MultipartFile file ,
                                String name ,
                                Integer surface,
                                Integer price,
                                String description) throws IOException;
    RentalResponse update(Long id , UpdateRentalDto rental);

    Optional<Rental> findById(Long id);



}
