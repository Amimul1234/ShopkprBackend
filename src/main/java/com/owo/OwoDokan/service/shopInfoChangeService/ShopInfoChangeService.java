package com.owo.OwoDokan.service.shopInfoChangeService;

import com.owo.OwoDokan.entity.shopInfoChange.ChangeShopInfo;
import com.owo.OwoDokan.repository.shopInfoChangeRepository.ShopInfoChangeRepo;
import org.springframework.stereotype.Service;

@Service
public class ShopInfoChangeService {
    private final ShopInfoChangeRepo shopInfoChangeRepo;

    public ShopInfoChangeService( ShopInfoChangeRepo shopInfoChangeRepo ) {
        this.shopInfoChangeRepo = shopInfoChangeRepo;
    }


    public void makeChangeRequest( ChangeShopInfo changeShopInfo ) {
        shopInfoChangeRepo.save(changeShopInfo);
    }
}
