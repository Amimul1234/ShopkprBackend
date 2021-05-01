package com.shopKpr.repository.qupon;

import com.shopKpr.entity.qupon.Qupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuponRepo extends JpaRepository<Qupon, Long> {
}
