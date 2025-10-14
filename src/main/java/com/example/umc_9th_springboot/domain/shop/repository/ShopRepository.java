package com.example.umc_9th_springboot.domain.shop.repository;

import com.example.umc_9th_springboot.domain.shop.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ShopRepository extends JpaRepository<Shop, Long> {

}
