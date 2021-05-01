package com.OwoDokan.service.gift;

import com.OwoDokan.entity.gifts.Gifts;
import com.OwoDokan.repository.gifts.GiftsRepo;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GiftService
{
    private final GiftsRepo giftsRepo;

    public GiftService( GiftsRepo giftsRepo ) {
        this.giftsRepo = giftsRepo;
    }

    public void createGiftsCard( Gifts gifts )
    {
        giftsRepo.save(gifts);
    }

    public List<Gifts> getAllGiftsCard() {
        return giftsRepo.findAll();
    }

    public void deleteGiftCard( Long giftCardId ) {
        giftsRepo.deleteById(giftCardId);
    }
}
