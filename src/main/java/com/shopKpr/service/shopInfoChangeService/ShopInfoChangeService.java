package com.shopKpr.service.shopInfoChangeService;

import com.shopKpr.entity.shopInfoChange.ChangeShopInfo;
import com.shopKpr.entity.shops.shopsData.Shops;
import com.shopKpr.repository.admin.ShopRepository;
import com.shopKpr.repository.shopInfoChangeRepository.ShopInfoChangeRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ShopInfoChangeService {

    private final ShopInfoChangeRepo shopInfoChangeRepo;
    private final ShopRepository shopRepository;

    public ShopInfoChangeService( ShopInfoChangeRepo shopInfoChangeRepo, ShopRepository shopRepository ) {
        this.shopInfoChangeRepo = shopInfoChangeRepo;
        this.shopRepository = shopRepository;
    }


    public void makeChangeRequest( ChangeShopInfo changeShopInfo ) {
        shopInfoChangeRepo.save(changeShopInfo);
    }

    public List<ChangeShopInfo> getAllShopInfoChangeRequests() {
        return shopInfoChangeRepo.findAll();
    }

    @Transactional
    public void approveShopInfoChange( ChangeShopInfo changeShopInfo ) {

        Optional<Shops> shopsOptional = shopRepository.getByPhone(changeShopInfo.getShopOwnerMobileNumber());

        if(shopsOptional.isPresent())
        {
            Shops shops = shopsOptional.get();

            String temp1 = shops.getShop_image_uri().substring(34);
            String temp2 = shops.getShop_keeper_nid_front_uri().substring(34);
            String temp3 = null;


            if(shops.getTrade_license_url() != null)
                temp3 = shops.getTrade_license_url().substring(34);

            shops.setShop_image_uri(changeShopInfo.getNewShopImageURL());
            shops.setShop_keeper_nid_front_uri(changeShopInfo.getNewShopOwnerNidFront());
            shops.setTrade_license_url(changeShopInfo.getNewShopTradeLicenseURI());

            shops.setShop_name(changeShopInfo.getNewShopName());
            shops.setShop_address(changeShopInfo.getNewShopAddress());
            shops.setShop_owner_name(changeShopInfo.getNewShopOwnerName());
            shops.setShop_service_mobile(changeShopInfo.getNewShopServiceMobile());

            shopRepository.save(shops);

            shopInfoChangeRepo.delete(changeShopInfo);

            deleteImage(temp1);
            deleteImage(temp2);

            if(temp3!=null)
                deleteImage(temp3);

        }
        else {
            throw new RuntimeException("Shop with owner mobile number : "+
                    changeShopInfo.getShopOwnerMobileNumber() + " does not exists");
        }
    }

    private void deleteImage( String temp1 ) {
        try {
            Files.deleteIfExists(Path.of(temp1));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
