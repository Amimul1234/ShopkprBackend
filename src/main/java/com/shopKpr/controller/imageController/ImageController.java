package com.shopKpr.controller.imageController;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Slf4j
@RestController
public class ImageController
{
    //This method is for saving upcoming image in filesystem
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PostMapping("/imageController/{directory}")
    public ResponseEntity saveImageInProject(@PathVariable("directory") String directory,
                                             @RequestPart(name = "multipartFile") MultipartFile multipartFile)
    {
        String filename = UUID.randomUUID() + multipartFile.getOriginalFilename();

        try {

            File dir = new File("images" + "/" + directory);

            if(!dir.exists())
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
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] requested_image = IOUtils.toByteArray(fileInputStream);
            fileInputStream.close();
            return new ResponseEntity<>(requested_image, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Image not found");
        }
    }


    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
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
