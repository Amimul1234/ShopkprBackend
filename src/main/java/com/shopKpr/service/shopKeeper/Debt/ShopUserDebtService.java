package com.shopKpr.service.shopKeeper.Debt;

import com.shopKpr.ModelClass.debt.DebtDashBoardResponse;
import com.shopKpr.entity.shops.shopsData.Shops;
import com.shopKpr.entity.shops.shopsData.UserDebts;
import com.shopKpr.entity.shops.shopsData.User_debt_details;
import com.shopKpr.repository.admin.ShopRepository;
import com.shopKpr.repository.shop.shopUserRepo.UserDebt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ShopUserDebtService {

    private final UserDebt userDebtRepository;
    private final ShopRepository shopRepository;

    public ShopUserDebtService( UserDebt userDebtRepository, ShopRepository shopRepository) {
        this.userDebtRepository = userDebtRepository;
        this.shopRepository = shopRepository;
    }

    public ResponseEntity<String> addDebt( UserDebts userDebts, String shop_mobile_number) {

        Optional<Shops> shopsOptional = shopRepository.getByPhone(shop_mobile_number);

        if(shopsOptional.isPresent())
        {
            Shops shops = shopsOptional.get();

            List<UserDebts> userDebts1 = shops.getUserDebts();

            for(UserDebts userDebts2 : userDebts1)
            {
                if(userDebts2.getUserMobileNumber().equals(userDebts.getUserMobileNumber()))
                {
                    return new ResponseEntity<> ("User Record Already exists", HttpStatus.CONFLICT);
                }
            }

            shops.getUserDebts().add(userDebts);
            userDebts.setShops(shops);
            shopRepository.save(shops);

            return new ResponseEntity<>("User Successfully Added", HttpStatus.OK);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Can not find shop");
        }
    }

    public ResponseEntity<String> addDebtDetails( User_debt_details user_debt_details, Long user_id) {

        UserDebts userDebts1;

        try {
            userDebts1 = userDebtRepository.findByUserId(user_id);
        }catch (Exception e)
        {
            return new ResponseEntity<>("Debt record not found", HttpStatus.NOT_FOUND);
        }

        user_debt_details.setUserDebts(userDebts1);

        double debt = userDebts1.getUserTotalDebt();

        debt = debt + user_debt_details.getTaka();

        userDebts1.setUserTotalDebt(debt);

        userDebts1.getUserDebtDetails().add(user_debt_details);

        try {
            userDebtRepository.save(userDebts1);
            return new ResponseEntity<>("Debt record added", HttpStatus.CREATED);
        }catch (Exception e)
        {
            log.error("Error occurred, Error is: "+e.getMessage());
            return new ResponseEntity<>("Can not add record", HttpStatus.FAILED_DEPENDENCY);
        }

    }

    public ResponseEntity<String> deleteAdebtDetails(long id_of_debt_details, long user_id) {

        UserDebts userDebts1;

        try
        {
            userDebts1 = userDebtRepository.findByUserId(user_id);

        }catch (Exception e)
        {
            return new ResponseEntity<>("Can not find user debt record", HttpStatus.NOT_FOUND);
        }

        List<User_debt_details> userDebtDetailsList = new ArrayList<>(userDebts1.getUserDebtDetails());

        double debt = userDebts1.getUserTotalDebt();

        for(User_debt_details user_debt_details : userDebtDetailsList)
        {
            if(user_debt_details.getId() == id_of_debt_details)
            {
                userDebtDetailsList.remove(user_debt_details);
                debt = debt - user_debt_details.getTaka();
                break;
            }
        }

        userDebts1.setUserTotalDebt(debt);
        userDebts1.getUserDebtDetails().clear();
        userDebts1.getUserDebtDetails().addAll(userDebtDetailsList);

        try
        {
            userDebtRepository.save(userDebts1);
            return new ResponseEntity<>("Debt data saved successfully", HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity<>("Can not save debt data", HttpStatus.FAILED_DEPENDENCY);
        }

    }

    public ResponseEntity getAllDebtDetails(Long user_id) {

        UserDebts userDebts1;

        try
        {
            userDebts1 = userDebtRepository.findByUserId(user_id);

            return new ResponseEntity<>(userDebts1, HttpStatus.OK);

        }catch (Exception e)
        {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity getDebtDetailsForACustomer(Long user_id)
    {

        UserDebts userDebts1;

        try
        {
            userDebts1 = userDebtRepository.findByUserId(user_id);

            if(userDebts1.getUserDebtDetails().size() > 0)
            {
                return new ResponseEntity<>(userDebts1.getUserDebtDetails(), HttpStatus.OK);
            }
            else
            {
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }


        }catch (Exception e)
        {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    public List<User_debt_details> getAllDebtDetailsViaList(Long user_id)
    {
        UserDebts userDebts1;

        try
        {
            userDebts1 = userDebtRepository.findByUserId(user_id);
            return userDebts1.getUserDebtDetails();
        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public String getCustomerName(Long user_id) {
        return userDebtRepository.findByUserId(user_id).getUserName();
    }

    public ResponseEntity updateAdebtDetails(User_debt_details user_debt_details, long user_id) {

        UserDebts userDebts;
        int length;
        double debt;

        try {
            userDebts = userDebtRepository.findByUserId(user_id);

            length = userDebts.getUserDebtDetails().size();
            debt = userDebts.getUserTotalDebt();

            for(int i=0; i<length; i++)
            {
                if(user_debt_details.getId() == userDebts.getUserDebtDetails().get(i).getId())
                {
                    debt = debt - userDebts.getUserDebtDetails().get(i).getTaka() + user_debt_details.getTaka();
                    userDebts.setUserTotalDebt(debt);
                    userDebts.getUserDebtDetails().set(i, user_debt_details);
                    userDebtRepository.save(userDebts);
                    return new ResponseEntity(HttpStatus.OK);
                }
            }

            return new ResponseEntity(HttpStatus.NOT_FOUND);

        }catch (Exception e)
        {
            return new ResponseEntity(HttpStatus.FAILED_DEPENDENCY);
        }
    }

    public ResponseEntity clearAllDebtDetails(Long user_id) {

        UserDebts userDebts;

        try
        {
            userDebts = userDebtRepository.findByUserId(user_id);

            try
            {
                userDebtRepository.delete(userDebts);
                return new ResponseEntity(HttpStatus.OK);
            }catch (Exception e)
            {
                return new ResponseEntity(HttpStatus.FAILED_DEPENDENCY);
            }

        }catch (Exception e)
        {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity getAllDebts(int page, String shop_mobile_number) {

        int pageSize = 10;
        int fromIndex = page * pageSize;

        Optional<Shops> shopsOptional = shopRepository.getByPhone(shop_mobile_number);

        if(shopsOptional.isPresent())
        {
            Shops shops = shopsOptional.get();

            int size = shops.getUserDebts().size();

            if(fromIndex >= size)
            {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            else
            {
                return new ResponseEntity<>(shops.getUserDebts().subList(fromIndex, Math.min(fromIndex + pageSize, shops.getUserDebts().size())), HttpStatus.OK);
            }
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public DebtDashBoardResponse getDebtDashBoardEntries( String mobileNumber)
    {
        Optional<Shops> shopsOptional = shopRepository.getByPhone(mobileNumber);

        if(shopsOptional.isPresent())
        {
            Shops shops = shopsOptional.get();

            List<UserDebts> userDebtsList = shops.getUserDebts();

            double totalLoan = 0.0;
            double totalPaid = 0.0;

            for(UserDebts userDebts : userDebtsList)
            {
                totalLoan += userDebts.getUserTotalDebt();
                totalPaid += userDebts.getUserPaid();
            }

            DebtDashBoardResponse debtDashBoardResponse = new DebtDashBoardResponse();
            debtDashBoardResponse.setTotalPaid(totalPaid);
            debtDashBoardResponse.setTotalLoan(totalLoan);

            return debtDashBoardResponse;
        }
        else
        {
            log.error("Can not find shops with number: "+mobileNumber);
            throw new RuntimeException("Shops does not exists");
        }
    }

    public ResponseEntity<String> addPaidAmount( Long userId, Double userPaidAmount)
    {
        Optional<UserDebts> userDebtsOptional = userDebtRepository.findById(userId);

        if(userDebtsOptional.isPresent())
        {
            UserDebts userDebts = userDebtsOptional.get();
            userDebts.setUserPaid(userPaidAmount);

            userDebtRepository.save(userDebts);

            return new ResponseEntity<>("Paid amount added successfully", HttpStatus.OK);
        }
        else {
            throw new RuntimeException("User with id:- "+userId + " does not exists");
        }
    }

    public Double getPaidAmount(Long userId)
    {
        Optional<UserDebts> userDebtsOptional = userDebtRepository.findById(userId);

        if(userDebtsOptional.isPresent())
        {
            UserDebts userDebts = userDebtsOptional.get();
            return userDebts.getUserPaid();
        }
        else {
            return 0.0;
        }
    }

}
