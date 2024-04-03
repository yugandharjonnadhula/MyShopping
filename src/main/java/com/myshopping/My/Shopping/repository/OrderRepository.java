package com.myshopping.My.Shopping.repository;

import com.myshopping.My.Shopping.models.OrderTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<OrderTable, UUID> {
}
