package org.covid19.helper.curatedList.Service;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Date;

@Service
public class FileStorageService {

    @Autowired
    S3Service s3Service;

    @Value("${aws.s3.filepath}")
    private String s3FilePath;

    public String uploadFile(File file, String uuid) throws Exception {
        String extension = FilenameUtils.getExtension(file.getAbsolutePath());
        String relativePath = s3FilePath + "/" + uuid + "/" +
                file.getName()  + "." + extension;
        Boolean uploadStatus = s3Service.copyToS3(file,relativePath);
        if(!uploadStatus) {
            throw new Exception("File was not saved");
        }
        return relativePath;
    }

}
