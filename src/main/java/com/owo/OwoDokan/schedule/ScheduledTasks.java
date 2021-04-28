package com.owo.OwoDokan.schedule;

import com.owo.OwoDokan.entity.offers.OffersEntity;
import com.owo.OwoDokan.entity.qupon.Qupon;
import com.owo.OwoDokan.repository.offers.OfferRepository;
import com.owo.OwoDokan.repository.qupon.QuponRepo;
import com.smattme.MysqlExportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.io.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Component
@Slf4j
public class ScheduledTasks
{

    private final OfferRepository offerRepository;
    private final QuponRepo quponRepo;

    public ScheduledTasks( OfferRepository offerRepository, QuponRepo quponRepo ) {
        this.offerRepository = offerRepository;
        this.quponRepo = quponRepo;
    }

    @Scheduled(fixedRate = 1000*60*60*5)
    public void reportCurrentTime()
    {
        updateOfferState();
        updateQuponState();
        schedule();
        zipImageDirectory();
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


    public void zipImageDirectory()
    {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
        String formattedDate = sdf.format(date);

        String sourceFile = "images";
        String output = "ImagesBackUp/" + formattedDate + ".zip";

        File directory = new File("ImagesBackUp");

        if (! directory.exists()){
            directory.mkdir();
        }

        try (FileOutputStream fos = new FileOutputStream(output); ZipOutputStream zipOut = new ZipOutputStream(fos)) {
            File fileToZip = new File(sourceFile);
            zipFile(fileToZip, fileToZip.getName(), zipOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void zipFile( File fileToZip, String fileName, ZipOutputStream zipOut)
            throws IOException
    {
        if (fileToZip.isHidden()) {
            return;
        }
        if (fileToZip.isDirectory()) {

            if (fileName.endsWith("/")) {
                zipOut.putNextEntry(new ZipEntry(fileName));
            } else {
                zipOut.putNextEntry(new ZipEntry(fileName + "/"));
            }
            zipOut.closeEntry();
            File[] children = fileToZip.listFiles();
            assert children != null;
            for (File childFile : children) {
                zipFile(childFile, fileName + "/" + childFile.getName(), zipOut);
            }
            return;
        }
        FileInputStream fis = new FileInputStream(fileToZip);
        ZipEntry zipEntry = new ZipEntry(fileName);
        zipOut.putNextEntry(zipEntry);
        byte[] bytes = new byte[1024];
        int length;

        while ((length = fis.read(bytes)) >= 0) {
            zipOut.write(bytes, 0, length);
        }
        fis.close();
    }


}