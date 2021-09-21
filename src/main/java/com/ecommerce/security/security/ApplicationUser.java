package com.ecommerce.security.security;

import com.ecommerce.data.models.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationUser implements UserDetails {
    private  String id;

    private  String name;

    @JsonIgnore
    private  String email;

    //email == username
    @JsonIgnore
    private  String username;

    @JsonIgnore
    private  String password;

    private Collection<? extends GrantedAuthority> authorities;



    public static ApplicationUser create(Customer customer) {
        log.info("Authority " + customer.getRole().toString()); //user entity has no role. if you need a role, add field role

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(customer.getRole().toString())); //user entity does not have role. create one
        return new ApplicationUser(
               String.valueOf( customer.getId()),
                customer.getFirstName() + " " + customer.getLastName(),
                customer.getEmailAddress(),
                customer.getEmailAddress(),
                customer.getPassword(),
                authorities
        );
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

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
}
