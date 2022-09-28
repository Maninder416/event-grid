package io.example.eventgrid.config;
import com.azure.core.credential.AzureKeyCredential;
import com.azure.core.models.CloudEvent;
import com.azure.core.models.CloudEventDataFormat;
import com.azure.core.util.BinaryData;
import com.azure.messaging.eventgrid.EventGridEvent;
//import com.microsoft.azure.eventgrid.models.EventGridEvent;
import com.azure.messaging.eventgrid.EventGridPublisherAsyncClient;
import com.azure.messaging.eventgrid.EventGridPublisherClient;
import com.azure.messaging.eventgrid.EventGridPublisherClientBuilder;
import com.azure.messaging.eventhubs.EventHubClientBuilder;
import com.azure.messaging.eventhubs.EventHubConsumerAsyncClient;
import com.azure.storage.blob.BlobContainerClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.*;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.time.OffsetDateTime;
import java.util.*;

@Configuration
public class EventProducer {
    String endpoint="https://cloud-event-grid.centralus-1.eventgrid.azure.net/api/events";
    String accessKey="iJ9YwoYTRByVN/FhOvKEcttwn16HXtSlA1fZR3CXMlY=";

//    public static EventGridPublisherAsyncClient<EventGridEvent> test(){
//
//        return new EventGridPublisherClientBuilder()
//                .endpoint("https://cloud-event-grid.centralus-1.eventgrid.azure.net/api/events")
//                .credential(new AzureKeyCredential("XpwUQayv8LX2KJOBH1whSPmeQHP2Dn6N/BWzEZKqW5I="))
//                .buildEventGridEventPublisherAsyncClient();
//
//    }

    public static EventHubConsumerAsyncClient consumer1(){

        return new EventHubClientBuilder()
                .connectionString("Endpoint=sb://cloud-event-grid.centralus-1.eventgrid.azure.net/api/events/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=iJ9YwoYTRByVN/FhOvKEcttwn16HXtSlA1fZR3CXMlY=;EntityPath=test")
                .consumerGroup(EventHubClientBuilder.DEFAULT_CONSUMER_GROUP_NAME)
                .buildAsyncConsumerClient();

    }

    public static EventHubConsumerAsyncClient consumer(){
        final String consumerGroup = "";

        return new EventHubClientBuilder()
               // .connectionString("https://event-trigger-robin.azurewebsites.net/runtime/webhooks/EventGrid?functionName=EventGridTriggerJava2&code=4T0b_wQI-uD3gJj9mnW7uM4DnpJSOPstgaRAuHdkSLM8AzFu7p4W2Q==")
                .connectionString("Endpoint=sb://robin-topic.centralus-1.eventgrid.azure.net/api/events/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=JCwg0ndqjzN7MLYXxQfq3hlMNf266i1L0/zimzpxgO8=;EntityPath=test")
                .consumerGroup(EventHubClientBuilder.DEFAULT_CONSUMER_GROUP_NAME)
                .buildAsyncConsumerClient();

    }

    static class ConstoItemReceivedEventData{
        public String itemSku;
        public ConstoItemReceivedEventData (String itemSku) {
            this.itemSku =itemSku;
        }
    }

//    public static void consumer2(String data){
//        final String StorageBlobCreatedEvent = "Microsoft.Storage.BlobCreated";
//        final String CustomTopicEvent = "Contoso.Items.ItemReceived";
//        final Gson gson = new GsonBuilder().create();
//
//        EventGridEvent eventGridEvent = gson.fromJson(data, EventGridEvent.class);  // event grid from com.mircosoft imported and other commented
//
//        ConstoItemReceivedEventData eventData = gson.fromJson((String) eventGridEvent.data(), ConstoItemReceivedEventData.class);
//
//    }


    public static void main(String[] args) throws URISyntaxException, InvalidKeyException, StorageException, IOException {
//        List<EventGridEvent> events = new ArrayList<>();
//        User user= new User("Robin","Bajaj");
//        events.add(new EventGridEvent("100","exampleSubject", "Com.Example.ExampleEventType", BinaryData.fromObject(user), "0.1"));
//        System.out.println("test: "+test());
//        test().sendEvents(events);

//        System.out.println("====" + consumer().receive());
//        System.out.println("=========== " + consumer().getConsumerGroup());

        String namespaceConn = "Endpoint=sb://cloud-event-grid.centralus-1.eventgrid.azure.net/api/events/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=iJ9YwoYTRByVN/FhOvKEcttwn16HXtSlA1fZR3CXMlY=;EntityPath=test";

        CloudStorageAccount storageAccount = CloudStorageAccount.parse("DefaultEndpointsProtocol=https;EndpointSuffix=core.windows.net;AccountName=robinazurestorageaccount;AccountKey=Iqd7cJgkjQYzzgWYVdt/v9lXBlPnng1coiw3GFOlfNBlbbXw/HTxAlB9ySAzJCSq84HfOeUZk1vj+AStqW28LQ==;BlobEndpoint=https://robinazurestorageaccount.blob.core.windows.net/;TableEndpoint=https://robinazurestorageaccount.table.core.windows.net/");
        CloudBlobClient blobClient = storageAccount.createCloudBlobClient();
        CloudBlobContainer container = blobClient.getContainerReference("eventgridsample");

        while(true) {
            Iterable<ListBlobItem> lst = container.listBlobs();
            for (ListBlobItem blob : lst) {
                if (blob instanceof CloudBlob && ((CloudBlob) blob).exists()) {
                    CloudBlob cloudBlob = (CloudBlob) blob;
                    System.out.println(cloudBlob.getName());
                    CloudBlockBlob blockBlob = container.getBlockBlobReference(cloudBlob.getName());
                    System.out.println(blockBlob.downloadText());

                     blockBlob.delete();
                }
            }
            System.out.println(container.listBlobs().iterator());
        }

//        CloudBlockBlob blob = container.getBlockBlobReference(blobname);
//        blob.downloadText();
//        System.out.println(blob.downloadText());


//        consumer2("skuu");
//        System.out.println("====");





    }
}
