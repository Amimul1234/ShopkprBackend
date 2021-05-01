package com.shopKpr.controller.admin.backUp;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class BackUpAndRestoreController
{
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin/getImagesBackUp")
    public StreamingResponseBody getImageBackUp( HttpServletResponse response) throws IOException
    {

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy");
        String formattedDate = sdf.format(date);

        response.setContentType("application/zip");

        final InputStream inputStream = new FileInputStream("ImagesBackUp/" + formattedDate + ".zip");

        return (os) -> readAndWrite(inputStream, os);

    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin/getDbBackUp")
    public StreamingResponseBody getDbBackUp( HttpServletResponse response) throws IOException
    {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy");
        String formattedDate = sdf.format(date);

        response.setContentType("application/zip");
        final InputStream inputStream = new FileInputStream("DbBackUp/" + formattedDate + ".zip");

        return (os) -> readAndWrite(inputStream, os);

    }

    private void readAndWrite(final InputStream is, OutputStream os) throws IOException
    {

        byte[] data = new byte[1024];

        int read;

        while ((read = is.read(data)) > 0)
        {
            os.write(data, 0, read);
        }

        os.flush();
    }

}
