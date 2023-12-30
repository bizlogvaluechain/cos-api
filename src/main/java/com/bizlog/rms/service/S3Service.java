package com.bizlog.rms.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@Service
public class S3Service {
    public static final String BUCKET_NAME = "";
    @Autowired
    private AmazonS3 amazonS3Client;

    public String uploadFileToS3(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        try {
            String key = UUID.randomUUID() + originalFilename;
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());

            PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKET_NAME, key, file.getInputStream(), metadata);
            PutObjectResult putObjectResult = amazonS3Client.putObject(putObjectRequest);
            log.info("File Uploaded to s3 successfully  with key {} and eTag:{}", key, putObjectResult.getETag());

            return key;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public byte[] retrieveFileFromS3(String s3Key) {
        try {
            log.info("Requesting s3 for file with key:{}", s3Key);
            S3Object s3Object = amazonS3Client.getObject(BUCKET_NAME, s3Key);
            return IOUtils.toByteArray(s3Object.getObjectContent());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
