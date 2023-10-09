package com.bizlog.rms.api.impl;

import com.bizlog.rms.service.S3Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/api/v1/{clientId}")
public class S3Resource {
    @Autowired
    private S3Service s3Service;

    @PostMapping(value = "/{type}/upload", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE }, produces = {
            MediaType.APPLICATION_JSON_VALUE })
    public String uploadFileToS3(@PathVariable("type") String type, @RequestParam("file") MultipartFile file) {
        log.info("upload controller start................");
        String s3Key = s3Service.uploadFileToS3(file);
        return "File uploaded to S3 with key: " + s3Key;
    }

    @GetMapping("/download/{downloadKey}")
    public byte[] downloadFileFromS3(@PathVariable String s3Key) {
        return s3Service.retrieveFileFromS3(s3Key);
    }

}
