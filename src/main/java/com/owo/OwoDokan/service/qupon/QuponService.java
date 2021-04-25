package com.owo.OwoDokan.service.qupon;

import com.owo.OwoDokan.entity.qupon.Qupon;
import com.owo.OwoDokan.repository.qupon.QuponRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class QuponService {

    private final QuponRepo quponRepo;

    public QuponService( QuponRepo quponRepo ) {
        this.quponRepo = quponRepo;
    }


    public void addNewQupon( Qupon qupon ) {
        quponRepo.save(qupon);
    }

    public List<Qupon> getAllQupons() {
        return quponRepo.findAll();
    }

    public void deleteQupon( Long quponId ) {
        quponRepo.deleteById(quponId);
    }

    public Qupon getQuponById( Long quponId ) {
        Optional<Qupon> quponOptional =  quponRepo.findById(quponId);

        if(quponOptional.isPresent())
        {
            return quponOptional.get();
        }
        else
        {
            log.debug("Qupon with id: "+quponId + " does not exists");
            throw new RuntimeException("Can not find qupon with id: "+quponId);
        }
    }
}
