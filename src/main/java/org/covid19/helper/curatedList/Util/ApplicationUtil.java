package org.covid19.helper.curatedList.Util;


import lombok.extern.log4j.Log4j2;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.covid19.helper.curatedList.Constants.ApplicationConstants;
import org.covid19.helper.curatedList.DTO.RequestExtractorDTO;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Log4j2
public class ApplicationUtil {


    public static FileSystemResource convertMultiPartToFile(MultipartFile mFile) {
        // String path = "src/main/resources/" + mFile.getOriginalFilename();
        String path = ApplicationConstants.BASE_FILE_PATH + generateRandomInteger() + "_" + mFile.getOriginalFilename();
        try {
            byte[] byteArr = mFile.getBytes();
            InputStream inputStream = new ByteArrayInputStream(byteArr);
            File file = new File(path);
            OutputStream outputStream = new FileOutputStream(file);
            IOUtils.copy(inputStream, outputStream);
            FileSystemResource fr = new FileSystemResource(file);
            return fr;

        } catch (Exception e) {
            log.error("Error in creating file ", e.getMessage());
        }
        return null;
    }


    public static Integer generateRandomInteger(Integer range) {
        Random random = new Random();
        return random.nextInt(range);
    }

    public static Integer generateRandomInteger() {
        return generateRandomInteger(100000);
    }

    //6 Digit Integer
    public static Integer generateOtp() {
        return ThreadLocalRandom.current().nextInt(100000, 1000000);
    }

    public static String urlEncode(String message) {
        try {
            return URLEncoder.encode(message, "UTF-8");
        } catch (Exception ex) {
            log.error("Not able to encode the message " + message);
        }
        return null;
    }

    public static String char2hex(byte x) {
        char arr[] = {
                '0', '1', '2', '3',
                '4', '5', '6', '7',
                '8', '9', 'A', 'B',
                'C', 'D', 'E', 'F'
        };

        char c[] = {arr[(x & 0xF0) >> 4], arr[x & 0x0F]};
        return (new String(c));
    }

    public static void addFileToHttpResponse(File file, HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
        httpServletResponse.setHeader("Content-Type", "application/octet-stream");
        httpServletResponse.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName());
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();
        outputStream.write(Files.readAllBytes(file.toPath()));
        outputStream.flush();
    }

    public static String uuidGenerator() {
        String random = String.valueOf(ThreadLocalRandom.current().nextInt(100, 1000000))
                + String.valueOf(System.currentTimeMillis());
        return Base64.getEncoder().encodeToString(random.getBytes());
    }

    public static String entityUuidGenerator() {
        String random = String.valueOf(ThreadLocalRandom.current().nextInt(10, 100000))
                + String.valueOf(System.currentTimeMillis());
        return Base64.getEncoder().encodeToString(random.getBytes());
    }

    public static RequestExtractorDTO getRequestAttributes(HttpServletRequest httpServletRequest,
                                                           HttpServletResponse httpServletResponse) {
        RequestExtractorDTO requestExtractorDTO = new RequestExtractorDTO();

        String ipAddress = httpServletRequest.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = httpServletRequest.getRemoteAddr();
        }

        requestExtractorDTO.setIpAddress(ipAddress);

        Cookie[] cookies = httpServletRequest.getCookies();
        String cookie = null;
        if (cookies != null) {
            for (Integer i = 0; i < cookies.length; i++) {
                if (ApplicationConstants.SAMARITAN.equals(cookies[i].getName())) {
                    cookie = cookies[i].getValue();
                }
            }
        }

        if (cookie == null) {
            cookie = uuidGenerator();
            Cookie setCookie = new Cookie(ApplicationConstants.SAMARITAN, cookie);
            httpServletResponse.addCookie(setCookie);
        }
        requestExtractorDTO.setCookie(cookie);
        return requestExtractorDTO;
    }


}


