package com.ecommerce.data.models;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Data
@Entity
public class Buyer implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer buyerId;
    @Column
    private String buyerEmailAddress;
    @Column
    private String buyerPassword;
    @Column
    private Role Role;




    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority simpleGrantedAuthority= new SimpleGrantedAuthority(getRole().name());
        return Collections.singletonList(simpleGrantedAuthority);
    }

    @Override
    public String getPassword() {
        return buyerPassword;
    }

    @Override
    public String getUsername() {
        return buyerEmailAddress;
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
