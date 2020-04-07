package com.aristosvo.example;

import com.azure.core.credential.TokenCredential;
import com.azure.identity.ManagedIdentityCredentialBuilder;
import com.azure.storage.blob.models.BlobItem;
import com.azure.storage.blob.specialized.BlockBlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class SampleApplication implements CommandLineRunner {


    @Value("${your.property.name}")
    private String mySecretProperty;

    @Value("${blobcontainer.name:storage}")
    private String name;
 
    @Value("${blobcontainer.endpoint}")
    private String endPoint;

    public static void main(String[] args) {
        SpringApplication.run(SampleApplication.class, args);
    }

    public void run(String... varl) throws Exception {
        System.out.println("property your-property-name in Azure Key Vault: " + mySecretProperty);

        TokenCredential tokenCredential = new ManagedIdentityCredentialBuilder().build();
        BlobContainerClient containerClient = new BlobContainerClientBuilder()
                    .endpoint(endPoint)
                    .credential(tokenCredential)
                    .containerName(name)
                    .buildClient();

        for (BlobItem blobItem : containerClient.listBlobs()) {
            System.out.println("\t" + blobItem.getName());
        }
    }

}