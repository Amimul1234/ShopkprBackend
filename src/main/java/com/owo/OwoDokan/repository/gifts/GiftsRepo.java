package com.owo.OwoDokan.repository.gifts;

import com.owo.OwoDokan.entity.gifts.Gifts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GiftsRepo extends JpaRepository<Gifts, Long> {

}
