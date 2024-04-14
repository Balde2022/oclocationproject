package com.ocprojettrois.locationprojettree.Models.User;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ocprojettrois.locationprojettree.Models.Role.Role;
import com.ocprojettrois.locationprojettree.Models.Token.Token;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
@Data
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    @Email(message = "Votre email est invalid")
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private  String password;
    @CreatedDate
    @Column(nullable = false,updatable = false)
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date created_at;
    @LastModifiedDate
    @Column(insertable = false)
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date updated_at;

    @Enumerated(value = EnumType.STRING)
    private Role role;


    @OneToMany(mappedBy = "user")
    private List<Token> tokens;

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }
}

