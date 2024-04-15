package com.ocprojettrois.locationprojettree.Controllers.Rental;
import com.ocprojettrois.locationprojettree.Models.Rental.Dto.CreateRentalDto;
import com.ocprojettrois.locationprojettree.Models.Rental.Dto.UpdateRentalDto;
import com.ocprojettrois.locationprojettree.Models.Rental.Rental;
import com.ocprojettrois.locationprojettree.Models.Rental.RentalResponse;
import com.ocprojettrois.locationprojettree.Services.Rental.RentalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path = "/api/rentals")
@Tag(name = "rentals")
@SecurityRequirement(name = "bearerAuth")
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
    public List<Rental> all(){
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
    public Optional<Rental> detail(@PathVariable Long id){
        return rentalService.detail(id);
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
    @PostMapping()
    public RentalResponse create(CreateRentalDto rental){
        return rentalService.create(rental);
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
}
