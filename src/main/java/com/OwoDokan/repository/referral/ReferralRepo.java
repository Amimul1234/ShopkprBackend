package com.OwoDokan.repository.referral;

import com.OwoDokan.entity.referral.Referral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ReferralRepo extends JpaRepository<Referral, String> {
    Optional<Referral> findByReferrerNumber( String mobileNumber );
}
