package org.covid19.helper.curatedList.Service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;


@Service
@Slf4j
public class S3Service {

    @Value("${aws.s3.bucket}")
    private String defaultAwsBucket;
    @Value("${aws.s3.filepath}")
    private String s3FilePath;
    @Value("${file.download.path}")
    private String downloadPath;

    private AmazonS3 s3Client;

    @PostConstruct
    public void initialise(){
        AWSCredentials credentials = new BasicAWSCredentials(
                "dd", "dd");
        s3Client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.AP_SOUTH_1)
                .build();

    }


    public Boolean copyToS3(File file, String relativePath) {
        Boolean status = putFileObjectToS3(defaultAwsBucket, relativePath, file);
        return status;
    }

    private Boolean putFileObjectToS3(final String bucketName, String objectKey, File file) {
        try {
            PutObjectResult res = s3Client.putObject(bucketName, objectKey, file);
            log.info("File " + file.getAbsolutePath() + " copied to S3 in bucket " + bucketName);
            return true;
        } catch (AmazonServiceException ex) {
            log.error("Unable to upload file " + file.getAbsolutePath() + " to AWS S3 due to AmazonServiceException with " +
                    " exceptionMsg " + ex.getMessage() + "statusCode " + ex.getStatusCode() + " errorCode " +
                    ex.getErrorCode() + " errorMsg " + ex.getErrorMessage(), ex);
        } catch (SdkClientException ex) {
            log.error("Unable to upload file " + file.getAbsolutePath() + " to AWS S3 due to AmazonClientException " + ex.getMessage(), ex);
        } catch (Exception ex) {
            log.error("Unable to upload file " + file.getAbsolutePath() + " to AWS S3 due to Exception " + ex.getMessage(), ex);
        }
        return false;
    }

    public File downloadObjectFromS3(String fileName) throws IOException {
        //TODO: file path
        S3Object s3object = s3Client.getObject(defaultAwsBucket, fileName);
        S3ObjectInputStream inputStream = s3object.getObjectContent();
        File newFile = new File(downloadPath + fileName);
        FileUtils.copyInputStreamToFile(inputStream, newFile);
        return newFile;
    }


}
