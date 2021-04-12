package com.owo.OwoDokan.controller.imageController;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Slf4j
@RestController
public class imageController
{
    //This method is for saving upcoming image in filesystem
    @PostMapping("/imageController/{directory}")
    public ResponseEntity saveImageInProject(@PathVariable("directory") String directory,
                                             @RequestPart(name = "multipartFile") MultipartFile multipartFile)
    {
        String filename = UUID.randomUUID().toString() + multipartFile.getOriginalFilename();

        try {

            File dir = new File("images" + "/" + directory);

            if(!dir.exists())//Make Directory if not exists
                dir.mkdirs();

            Files.copy(multipartFile.getInputStream(), Paths.get(dir+ "/"+ filename), StandardCopyOption.REPLACE_EXISTING);

            String response = "/getImageFromServer?path_of_image=images/" + directory + "/" + filename;

            multipartFile.getInputStream().close();

            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (IOException e) {
            String failed = "Failed to save image, Please try again";

            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(failed);
        }

    }

    @GetMapping(value = "/getImageFromServer", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> sendImageToClient(@RequestParam(name = "path_of_image") String path_of_image)
    {
        File file = new File(path_of_image);
        try
        {
            byte[] requested_image = IOUtils.toByteArray(new FileInputStream(file));
            return new ResponseEntity<>(requested_image, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Image not found");
        }
    }

    @DeleteMapping("/getImageFromServer")
    public ResponseEntity<String> deleteImage(@RequestParam(name = "path_of_image") String path_of_image)
    {
        File file = new File(path_of_image);

        if(file.exists())
        {
            try
            {
                file.delete();
                return ResponseEntity.status(HttpStatus.OK).body("Image Successfully Deleted");
            }
            catch (Exception e)
            {
                log.error("Failed to delete image");
                return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body("Failed To Delete Image");
            }
        }
        else
        {
            log.error("File does not exists, path is: "+path_of_image);
            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body("Image does not exists");
        }
    }

}
