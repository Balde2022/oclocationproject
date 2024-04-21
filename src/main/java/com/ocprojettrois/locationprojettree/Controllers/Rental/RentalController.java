package com.ocprojettrois.locationprojettree.Controllers.Rental;
import com.ocprojettrois.locationprojettree.Models.Rental.Dto.CreateRentalDto;
import com.ocprojettrois.locationprojettree.Models.Rental.Dto.RentalResponseDto;
import com.ocprojettrois.locationprojettree.Models.Rental.Dto.RentalsResponseDto;
import com.ocprojettrois.locationprojettree.Models.Rental.Dto.UpdateRentalDto;
import com.ocprojettrois.locationprojettree.Models.Rental.Rental;
import com.ocprojettrois.locationprojettree.Models.Rental.RentalResponse;
import com.ocprojettrois.locationprojettree.Repository.Rental.RentalRepository;
import com.ocprojettrois.locationprojettree.Services.Rental.RentalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
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


@RestController
@RequestMapping(path = "/api/rentals")
@Tag(name = "rentals")
@SecurityRequirement(name = "bearerAuth")
@CrossOrigin(origins = "http://localhost:4200")
public class RentalController {
    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @Operation(
            description = "Get endpoint for get all rentals",
            summary = "Recuperer l'ensemble des annonces de location",
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
    @GetMapping()
    public Stream<RentalsResponseDto> all(){
        return rentalService.all();
    }

    @Operation(
            description = "Get endpoint for get rental",
            summary = "Recuperer une annonce de location a partir d'un id",
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
    @GetMapping("/{id}")
    public RentalResponseDto detail(@PathVariable Long id){
        return rentalService.detail(id);
    }

    @Operation(
            description = "Get endpoint for updated rental",
            summary = "Mettre a jour une annonce de location",
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
    @PutMapping("/{id}")
    public RentalResponse update(@PathVariable Long id, UpdateRentalDto rental){
        return rentalService.update(id,rental);
    }

    @Operation(
            description = "Get endpoint for create a rental",
            summary = "Créer une annonces de location",
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

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public RentalResponse create(@RequestParam MultipartFile file ,
                                 String name ,
                                 Integer surface,
                                 Integer price,
                                 String description) throws IOException
    {
        return rentalService.create(file , name , surface , price , description);
    }
}
