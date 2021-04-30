package com.owo.OwoDokan.service.registration;

import com.owo.OwoDokan.entity.qupon.Qupon;
import com.owo.OwoDokan.entity.qupon.TakenQupons;
import com.owo.OwoDokan.entity.registerAccount.ShopKeeperUser;
import com.owo.OwoDokan.entity.registerAccount.UserShopKeeper;
import com.owo.OwoDokan.exceptions.NoEnabledShops;
import com.owo.OwoDokan.exceptions.ShopKeeperUserNotFount;
import com.owo.OwoDokan.repository.shop.registraton.ShopKeeperUserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ShopKeeperRegistrationService {

    private final ShopKeeperUserRepo shopKeeperUserRepo;

    public ShopKeeperRegistrationService(ShopKeeperUserRepo shopKeeperUserRepo) {
        this.shopKeeperUserRepo = shopKeeperUserRepo;
    }


    public String addNewShopKeeper(UserShopKeeper userShopKeeper)
    {

        ShopKeeperUser shopKeeperUser = new ShopKeeperUser();

        shopKeeperUser.setImageUri(userShopKeeper.getImage());
        shopKeeperUser.setAccountEnabled(true);
        shopKeeperUser.setMobileNumber(userShopKeeper.getPhone());
        shopKeeperUser.setPin(userShopKeeper.getPin());
        shopKeeperUser.setName(userShopKeeper.getName());

        try
        {
            shopKeeperUserRepo.save(shopKeeperUser);
            return "Successfully Registered Shop User";
        }catch (Exception e)
        {
            log.error("Error occurred on ShopKeeperRegistrationService class, Error is: "+e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public ShopKeeperUser getShopKeeper(String mobile_number) {

        Optional<ShopKeeperUser> shopKeeperUserOptional = shopKeeperUserRepo.findByMobileNumber(mobile_number);

        if(shopKeeperUserOptional.isPresent())
        {
            return shopKeeperUserOptional.get();
        }
        else
        {
            throw new ShopKeeperUserNotFount(mobile_number);
        }
    }

    public List<ShopKeeperUser> findAllEnabledShopKeeper(int page) {
        int pageSize = 10;
        org.springframework.data.domain.Pageable pageable = PageRequest.of(page, pageSize);
        Optional<List<ShopKeeperUser>> optionalShopKeeperUsers = shopKeeperUserRepo.getAllEnabledShops(pageable);

        if(optionalShopKeeperUsers.isPresent())
        {
            List<ShopKeeperUser> shopKeeperUserList = optionalShopKeeperUsers.get();

            for(ShopKeeperUser shopKeeperUser : shopKeeperUserList) {
                shopKeeperUser.setPin(null);
            }
            return shopKeeperUserList;
        }
        else
        {
            throw new NoEnabledShops();
        }
    }

    public List<ShopKeeperUser> findAllDisabledShopKeeper(int page)
    {

        int pageSize = 10;

        org.springframework.data.domain.Pageable pageable = PageRequest.of(page, pageSize);
        Optional<List<ShopKeeperUser>> optionalShopKeeperUsers = shopKeeperUserRepo.getAllDisabledShopKeeper(pageable);

        if(optionalShopKeeperUsers.isPresent())
        {
            List<ShopKeeperUser> shopKeeperUserList = optionalShopKeeperUsers.get();

            for(ShopKeeperUser shopKeeperUser : shopKeeperUserList) {
                shopKeeperUser.setPin(null);
            }
            return shopKeeperUserList;
        }
        else
        {
            throw new NoEnabledShops();
        }
    }

    @Transactional
    public String disableShopKeeper(String mobileNumber) {
        Optional<ShopKeeperUser> shopKeeperUserOptional = shopKeeperUserRepo.findByMobileNumber(mobileNumber);

        if(shopKeeperUserOptional.isPresent())
        {
            ShopKeeperUser shopKeeperUser = shopKeeperUserOptional.get();
            shopKeeperUser.setAccountEnabled(false);

            try
            {
                shopKeeperUserRepo.save(shopKeeperUser);
                return "Shop Keeper Disabled Successfully";
            }catch (Exception e)
            {
                log.error("Error occurred, Error is: "+e.getMessage());
                throw new RuntimeException(e);
            }
        }
        else
        {
            throw new ShopKeeperUserNotFount(mobileNumber);
        }
    }

    @Transactional
    public String deleteShopKeeper(String mobileNumber) {

        Optional<ShopKeeperUser> shopKeeperUserOptional = shopKeeperUserRepo.findByMobileNumber(mobileNumber);

        if(shopKeeperUserOptional.isPresent())
        {
            ShopKeeperUser shopKeeperUser = shopKeeperUserOptional.get();
            shopKeeperUser.setAccountEnabled(false);

            try
            {
                shopKeeperUserRepo.delete(shopKeeperUser);
                return "Shop Keeper deleted Successfully";
            }catch (Exception e)
            {
                log.error("Error occurred, Error is: "+e.getMessage());
                throw new RuntimeException(e);
            }
        }
        else
        {
            throw new ShopKeeperUserNotFount(mobileNumber);
        }
    }

    @Transactional
    public String enableShopKeeper(String mobileNumber) {

        Optional<ShopKeeperUser> shopKeeperUserOptional = shopKeeperUserRepo.findByMobileNumber(mobileNumber);

        if(shopKeeperUserOptional.isPresent())
        {
            ShopKeeperUser shopKeeperUser = shopKeeperUserOptional.get();
            shopKeeperUser.setAccountEnabled(true);

            try
            {
                shopKeeperUserRepo.save(shopKeeperUser);
                return "Shop Keeper Enabled Successfully";
            }catch (Exception e)
            {
                log.error("Error occurred, Error is: "+e.getMessage());
                throw new RuntimeException(e);
            }
        }
        else
        {
            throw new ShopKeeperUserNotFount(mobileNumber);
        }
    }

    @Transactional
    public String updateShopInfo(ShopKeeperUser shopKeeperUser) {
        Optional<ShopKeeperUser> shopKeeperUserOptional = shopKeeperUserRepo.findById(shopKeeperUser.getShopKeeperId());

        if(shopKeeperUserOptional.isPresent())
        {
            ShopKeeperUser shopKeeperUser1 = shopKeeperUserOptional.get();

            shopKeeperUser1.setPin(shopKeeperUser.getPin());
            shopKeeperUser1.setImageUri(shopKeeperUser.getImageUri());
            shopKeeperUser1.setName(shopKeeperUser.getName());

            shopKeeperUserRepo.save(shopKeeperUser1);

            return "Shop Keeper Information Updated Successfully";
        }
        else
        {
            throw new ShopKeeperUserNotFount(shopKeeperUser.getMobileNumber());
        }
    }

    public void addNewCouponToUser( String mobileNumber, Qupon qupon ) {

        Optional<ShopKeeperUser> shopKeeperUserOptional = shopKeeperUserRepo.findByMobileNumber(mobileNumber);

        if(shopKeeperUserOptional.isPresent())
        {
            ShopKeeperUser shopKeeperUser = shopKeeperUserOptional.get();

            List<TakenQupons> takenQuponsList = shopKeeperUser.getTakenQuponsList();

            takenQuponsList.forEach(takenQupons -> {
                if(takenQupons.getQuponId().equals(qupon.getQuponId()))
                {
                    throw new RuntimeException("Coupon already exists");
                }
            });

            TakenQupons takenQupons = new TakenQupons();
            takenQupons.setShopKeeperUser(shopKeeperUser);
            takenQupons.setQuponId(qupon.getQuponId());

            shopKeeperUser.getTakenQuponsList().add(takenQupons);

            shopKeeperUserRepo.save(shopKeeperUser);
        }
        else
        {
            throw new RuntimeException("Shop Keeper with mobile number: "+mobileNumber + " does not exists");
        }
    }

    public ResponseEntity<String> checkTakenOrNotTaken( String mobileNumber, Long quponId ) {

        Optional<ShopKeeperUser> shopKeeperUserOptional = shopKeeperUserRepo.findByMobileNumber(mobileNumber);

        if(shopKeeperUserOptional.isPresent())
        {
            ShopKeeperUser shopKeeperUser = shopKeeperUserOptional.get();

            List<TakenQupons> takenQuponsList = shopKeeperUser.getTakenQuponsList();

            for (TakenQupons takenQupons : takenQuponsList)
            {
                if (takenQupons.getQuponId().equals(quponId))
                {
                    return new ResponseEntity<>("User taken coupon", HttpStatus.OK);
                }
            }

            throw new RuntimeException("User did not take coupon");
        }
        else
        {
            throw new RuntimeException("Shop Keeper with mobile number: "+mobileNumber + " does not exists");
        }
    }

    public void updatePin( UserShopKeeper userShopKeeper ) {

        Optional<ShopKeeperUser> shopKeeperUserOptional = shopKeeperUserRepo.findByMobileNumber(userShopKeeper.getPhone());

        if(shopKeeperUserOptional.isPresent())
        {
            ShopKeeperUser shopKeeperUser = shopKeeperUserOptional.get();

            shopKeeperUser.setPin(userShopKeeper.getPin());

            shopKeeperUserRepo.save(shopKeeperUser);
        }
        else
        {
            throw new RuntimeException("Can not find user with mobile number: "+userShopKeeper.getPhone());
        }
    }
}
