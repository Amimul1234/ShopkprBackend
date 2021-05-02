package com.shopKpr.repository.bakirKhata;

import com.shopKpr.entity.bakirRecord.BakirRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BakirKhataRepo extends JpaRepository<BakirRecord, Long>
{
    List<BakirRecord> getAllByShopMobileNumber( String mobileNumber);
}
