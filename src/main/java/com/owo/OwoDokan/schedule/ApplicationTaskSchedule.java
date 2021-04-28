package com.owo.OwoDokan.schedule;

import com.owo.OwoDokan.entity.offers.OffersEntity;
import com.owo.OwoDokan.entity.qupon.Qupon;
import com.owo.OwoDokan.repository.offers.OfferRepository;
import com.owo.OwoDokan.repository.qupon.QuponRepo;
import com.smattme.MysqlExportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

@Component
@Slf4j
public class ApplicationTaskSchedule
{

    private final OfferRepository offerRepository;
    private final QuponRepo quponRepo;

    public ApplicationTaskSchedule( OfferRepository offerRepository, QuponRepo quponRepo ) {
        this.offerRepository = offerRepository;
        this.quponRepo = quponRepo;
    }

    @Scheduled(fixedRate = 1000*60*60*5)
    public void reportCurrentTime()
    {
        updateOfferState();
        updateQuponState();
        schedule();
    }

    private void updateQuponState()
    {
        java.sql.Date today = new java.sql.Date(new java.util.Date().getTime());

        List<Qupon> quponList = quponRepo.findAll();

        if(!quponList.isEmpty())
        {
            for(Qupon qupon : quponList)
            {
                int value = today.compareTo(qupon.getQuponStartDate());

                if(value==0 || value > 0) //If it is already past the start date then enable offer
                {
                    qupon.setEnabled(true);

                    try
                    {
                        quponRepo.save(qupon);
                    }catch (Exception e)
                    {
                        log.error("Can not update qupon, which id is: " + qupon.getQuponId());
                    }
                }

                if(today.after(qupon.getQuponEndDate()))
                {
                    qupon.setEnabled(false);

                    try
                    {
                        quponRepo.save(qupon);
                    }
                    catch (Exception e)
                    {
                        log.error("Can not qupon state, id is: "+ qupon.getQuponId());
                    }
                }
            }
        }
    }

    private void updateOfferState()
    {
        java.sql.Date today = new java.sql.Date(new java.util.Date().getTime());

        List<OffersEntity> offersEntityList = offerRepository.findAll();

        if(!offersEntityList.isEmpty())
        {
            for(OffersEntity offersEntity : offersEntityList)
            {
                int value = today.compareTo(offersEntity.getOffer_start_date());

                if(value==0 || value > 0) //If it is already past the start date then enable offer
                {
                    offersEntity.setEnabled(true);
                    try
                    {
                        offerRepository.save(offersEntity);
                    }catch (Exception e)
                    {
                        log.error("Can not update offer, which id is: " + offersEntity.getOfferId());
                    }
                }

                if(today.after(offersEntity.getOffer_end_date()))
                {
                    offersEntity.setEnabled(false);

                    try
                    {
                        offerRepository.save(offersEntity);
                    }
                    catch (Exception e)
                    {
                        log.error("Can not update offer state, id is: "+ offersEntity.getOfferId());
                    }
                }
            }
        }
    }

    //Scheduling my-sql database backup
    public void schedule() {

        Properties properties = new Properties();

        properties.setProperty(MysqlExportService.DB_NAME, "shopKPR");
        properties.setProperty(MysqlExportService.DB_USERNAME, "root");
        properties.setProperty(MysqlExportService.DB_PASSWORD, "Rahi-8000");
        properties.setProperty(MysqlExportService.PRESERVE_GENERATED_ZIP, "true");

        properties.setProperty(MysqlExportService.TEMP_DIR,
                new File("DbBackUp").getPath());

        MysqlExportService mysqlExportService = new MysqlExportService(properties);

        try {
            mysqlExportService.export();
        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



}