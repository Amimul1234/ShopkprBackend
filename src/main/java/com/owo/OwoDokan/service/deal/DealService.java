package com.owo.OwoDokan.service.deal;

import com.owo.OwoDokan.entity.deals.Deals;
import com.owo.OwoDokan.repository.deals.DealsRepo;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DealService {
    private final DealsRepo dealsRepo;

    public DealService( DealsRepo dealsRepo ) {
        this.dealsRepo = dealsRepo;
    }


    public void addNewDeal( Deals deals ) {
        dealsRepo.save(deals);
    }

    public List<Deals> getAllDeals() {
        return dealsRepo.findAll();
    }

    public void deleteDeal( Long dealsId ) {
        dealsRepo.deleteById(dealsId);
    }
}
