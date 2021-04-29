package com.owo.OwoDokan.backUp;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class BackUpAndRestoreController {

    @GetMapping("/admin/getImagesBackUp")
    public StreamingResponseBody getImageBackUp() throws IOException
    {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy");
        String formattedDate = sdf.format(date);

        final InputStream videoFileStream = new FileInputStream("ImagesBackUp/" + formattedDate + ".zip");

        return (os) -> readAndWrite(videoFileStream, os);

    }

    @GetMapping("/admin/getDbBackUp")
    public StreamingResponseBody getDbBackUp() throws IOException
    {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy");
        String formattedDate = sdf.format(date);

        final InputStream videoFileStream = new FileInputStream("DbBackUp/" + formattedDate + ".zip");

        return (os) -> readAndWrite(videoFileStream, os);

    }

    private void readAndWrite(final InputStream is, OutputStream os) throws IOException
    {

        byte[] data = new byte[500];

        int read;

        while ((read = is.read(data)) > 0) {
            os.write(data, 0, read);
        }

        os.flush();
    }

}
