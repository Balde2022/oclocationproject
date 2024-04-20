package com.ocprojettrois.locationprojettree.Controllers.User;

import com.ocprojettrois.locationprojettree.Models.User.User;
import com.ocprojettrois.locationprojettree.Services.User.UserDetailsServiceImp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/user")
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "user")
public class UserController {
    private final UserDetailsServiceImp userService;

    public UserController(UserDetailsServiceImp userService) {
        this.userService = userService;
    }


    @Operation(
            description = "Get endpoint for user by id",
            summary = "Recuperer un utilisateur a travers son id",
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
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> user(@PathVariable Long id){
        return ResponseEntity.ok(userService.findById(id));
    }
}
