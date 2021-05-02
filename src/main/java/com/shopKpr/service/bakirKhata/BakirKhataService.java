package com.shopKpr.service.bakirKhata;

import com.shopKpr.entity.bakirRecord.BakirRecord;
import com.shopKpr.repository.bakirKhata.BakirKhataRepo;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BakirKhataService {

    private final BakirKhataRepo bakirKhataRepo;

    public BakirKhataService( BakirKhataRepo bakirKhataRepo ) {
        this.bakirKhataRepo = bakirKhataRepo;
    }

    public void createNewRecord( BakirRecord bakirRecord ) {
        bakirKhataRepo.save(bakirRecord);
    }

    public void deleteRecord( Long recordId )
    {
        bakirKhataRepo.deleteById(recordId);
    }

    public List<BakirRecord> getAllBakirRecord( String mobileNumber ) {
        return bakirKhataRepo.getAllByShopMobileNumber(mobileNumber);
    }
}
