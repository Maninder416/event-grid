package io.example.eventgrid.azurefunctions;

import com.microsoft.azure.serverless.functions.ExecutionContext;
import com.microsoft.azure.serverless.functions.annotation.EventGridTrigger;
import com.microsoft.azure.serverless.functions.annotation.FunctionName;
import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.OperationContext;
import com.microsoft.azure.storage.blob.*;

import java.util.UUID;

public class EventGridTriggerJava3 {
    /**
     * This function will be invoked when an event is received from Event Grid.
     */
    @FunctionName("EventGridTriggerJava3")
    public void run(@EventGridTrigger(name = "eventGridEvent") String message, final ExecutionContext context) {
        try{
            context.getLogger().info("Java Event Grid trigger function executed.");
            context.getLogger().info(message);

            CloudStorageAccount storageAccount = CloudStorageAccount.parse("DefaultEndpointsProtocol=https;EndpointSuffix=core.windows.net;AccountName=robinazurestorageaccount;AccountKey=Iqd7cJgkjQYzzgWYVdt/v9lXBlPnng1coiw3GFOlfNBlbbXw/HTxAlB9ySAzJCSq84HfOeUZk1vj+AStqW28LQ==;BlobEndpoint=https://robinazurestorageaccount.blob.core.windows.net/;TableEndpoint=https://robinazurestorageaccount.table.core.windows.net/");
            CloudBlobClient blobClient = storageAccount.createCloudBlobClient();
            CloudBlobContainer container = blobClient.getContainerReference("eventgridsample");

            container.createIfNotExists(BlobContainerPublicAccessType.CONTAINER, new BlobRequestOptions(), new OperationContext());
            String blobname = "eg_" + UUID.randomUUID().toString().replaceAll("-", "") + ".txt";
            CloudBlockBlob blob = container.getBlockBlobReference(blobname);

            context.getLogger().info("......Uploading the sample text..........");
            //blob.uploadText(blobname + " content\n");
            blob.uploadText(message);


        } catch (Exception e) {
            context.getLogger().info("Exceptionnnnnn: " + e.toString());
        }

    }
}
