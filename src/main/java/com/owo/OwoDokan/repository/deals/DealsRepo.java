package com.owo.OwoDokan.repository.deals;

import com.owo.OwoDokan.entity.deals.Deals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DealsRepo extends JpaRepository<Deals, Long> {
}
