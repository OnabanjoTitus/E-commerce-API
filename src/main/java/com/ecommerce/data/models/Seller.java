package com.ecommerce.data.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


@Data
@Entity
public class Seller implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Integer sellerId;
    @Column
    private String sellerName;
    @Column
    private String sellerLocation;

    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn
    private List<Product> products;

    public void addProduct(Product product){
        if(this.products==null){
            this.products=new ArrayList<>();
        }
        this.products.add(product);
    }
    @Column
    private String sellerEmailAddress;

    @Column
    @JsonIgnore
    private String sellerPassword;

    @Column
    private Role Role;

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority simpleGrantedAuthority= new SimpleGrantedAuthority(getRole().name());
        return Collections.singletonList(simpleGrantedAuthority);
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return getSellerPassword();
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return getSellerEmailAddress();
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }
}
