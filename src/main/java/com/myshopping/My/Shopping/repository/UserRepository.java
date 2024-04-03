package com.myshopping.My.Shopping.repository;

import com.myshopping.My.Shopping.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<AppUser, UUID> {
    public List<AppUser> findByEmail(String email);
}
