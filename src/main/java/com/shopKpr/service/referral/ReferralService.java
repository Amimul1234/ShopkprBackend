package com.shopKpr.service.referral;

import com.shopKpr.entity.referral.Referral;
import com.shopKpr.repository.referral.ReferralRepo;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ReferralService {

    private final ReferralRepo referralRepo;

    public ReferralService( ReferralRepo referralRepo ) {
        this.referralRepo = referralRepo;
    }

    public void addNewPoint( String mobileNumber ) {
        Optional<Referral> referralOptional = referralRepo.findByReferrerNumber(mobileNumber);

        if(referralOptional.isPresent())
        {
            Referral referral = referralOptional.get();

            referral.setReferPoint(referral.getReferPoint() + 100);

            referralRepo.save(referral);
        }
        else {
            Referral referral = new Referral();

            referral.setReferrerNumber(mobileNumber);
            referral.setReferPoint(100L);
            referralRepo.save(referral);
        }
    }

    public Referral getReferral( String mobileNumber ) {
        Optional<Referral> referralOptional = referralRepo.findByReferrerNumber(mobileNumber);

        if(referralOptional.isPresent())
        {
            return referralOptional.get();
        }
        else
        {
            throw new RuntimeException("Can ot get referrer with id: " + mobileNumber);
        }
    }
}
