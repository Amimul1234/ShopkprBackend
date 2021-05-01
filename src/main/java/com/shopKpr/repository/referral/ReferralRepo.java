package com.shopKpr.repository.referral;

import com.shopKpr.entity.referral.Referral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ReferralRepo extends JpaRepository<Referral, String> {
    Optional<Referral> findByReferrerNumber( String mobileNumber );
}
