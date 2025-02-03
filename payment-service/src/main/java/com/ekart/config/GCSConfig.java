/**
 * Author: BIKASH
 */
package com.ekart.config;

import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GCSConfig {

    Logger logger = LoggerFactory.getLogger(GCSConfig.class);


    @Value("${gcs.bucket.name}")
    private String bucketName;


    @Bean
    public Storage getStorage() {
        Storage storage = StorageOptions.getDefaultInstance().getService();
        Bucket existingBucket = storage.get(bucketName);
        if (existingBucket == null) {
            Bucket bucket = storage.create(Bucket.newBuilder(bucketName).build());
            logger.info("Bucket created Successfully with name : {}", bucketName);
        } else {
            logger.info("Bucket {}  already created", bucketName);
        }

        return storage;
    }

}
