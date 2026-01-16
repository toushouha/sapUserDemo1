package com.example.demo;

// import org.springframework.batch.core.StepContribution;
// import org.springframework.batch.core.scope.context.ChunkContext;
// import org.springframework.batch.core.step.tasklet.Tasklet;
// import org.springframework.batch.repeat.RepeatStatus;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.http.HttpEntity;
// import org.springframework.http.HttpHeaders;
// import org.springframework.http.HttpMethod;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
// import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
// import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
// import org.springframework.stereotype.Component;
// import org.springframework.web.client.RestTemplate;
// import org.springframework.beans.factory.annotation.Autowired;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

// import org.apache.http.HttpResponse;
// import org.apache.http.client.HttpClient;
// import org.apache.http.client.methods.HttpGet;
// import org.apache.http.util.EntityUtils;

// import com.sap.cloud.sdk.cloudplatform.connectivity.Destination;
// import com.sap.cloud.sdk.cloudplatform.connectivity.DestinationAccessor;
// import com.sap.cloud.sdk.cloudplatform.connectivity.HttpClientFactory;


import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

import com.sap.cloud.sdk.cloudplatform.connectivity.Destination;
import com.sap.cloud.sdk.cloudplatform.connectivity.HttpDestination;
import com.sap.cloud.sdk.cloudplatform.connectivity.DestinationAccessor;
import com.sap.cloud.sdk.cloudplatform.connectivity.HttpClientAccessor;


@Component
public class ProductTasklet implements Tasklet {

    private static final Logger log = LoggerFactory.getLogger(ProductTasklet.class);

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        // 1. 获取 Destination（名称必须与 BTP Cockpit 中的 Destination Name 一致）
        HttpDestination destination = DestinationAccessor.getDestination("demo-batch-api-call").asHttp();

        // 2. 获取 HttpClient（Cloud SDK v4 自动处理 OAuth2 Token）
        HttpClient httpClient = HttpClientAccessor.getHttpClient(destination);

        // 3. 构造请求（例如调用 CAP 的 Products API）
        HttpGet request = new HttpGet("/odata/v4/Products");
        request.setHeader("Accept", "application/json");

        log.info("Calling CAP service via Destination...");

        // 4. 执行请求
        HttpResponse response = httpClient.execute(request);

        int statusCode = response.getStatusLine().getStatusCode();
        String body = EntityUtils.toString(response.getEntity());

        log.info("CAP Response Status: {}", statusCode);
        log.info("CAP Response Body: {}", body);

        if (statusCode != 200) {
            throw new RuntimeException("Failed to call CAP service: " + statusCode);
        }

        return RepeatStatus.FINISHED;
    }
}


// @Component
// public class ProductTasklet implements Tasklet {

//     // private final OAuth2AuthorizedClientManager clientManager;

//     // public ProductTasklet(OAuth2AuthorizedClientManager clientManager) {
//     //     this.clientManager = clientManager;
//     //     System.out.println("Call ProductTasklet Constructor");
//     // }

//     // @Override
//     // public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//     //     System.out.println("Tasklet is running!");
//     //     OAuth2AuthorizeRequest request = OAuth2AuthorizeRequest
//     //             .withClientRegistrationId("batch-client")
//     //             .principal("batch")
//     //             .build();

//     //     OAuth2AuthorizedClient client = clientManager.authorize(request);

//     //     String token = client.getAccessToken().getTokenValue();

//     //     System.out.println("TOKEN = " + token);

//     //     return RepeatStatus.FINISHED;
//     // }

//     private static final Logger log = LoggerFactory.getLogger(ProductTasklet.class);

//     @Override
//     public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

//         // 1. 获取 Destination（名称必须与 BTP Cockpit 中的 Destination Name 一致）
//         Destination destination = DestinationAccessor.getDestination("demo-batch-api-call");

//         // 2. 获取 HttpClient（Cloud SDK 会自动处理 OAuth2 Token）
//         HttpClient httpClient = HttpClientFactory.create(destination);

//         // 3. 构造请求（例如调用 CAP 的 Products API）
//         HttpGet request = new HttpGet("/odata/v4/Products");
//         request.setHeader("Accept", "application/json");

//         log.info("Calling CAP service via Destination...");

//         // 4. 执行请求
//         HttpResponse response = httpClient.execute(request);

//         int statusCode = response.getStatusLine().getStatusCode();
//         String body = EntityUtils.toString(response.getEntity());

//         log.info("CAP Response Status: {}", statusCode);
//         log.info("CAP Response Body: {}", body);

//         // 5. 根据业务逻辑处理结果
//         if (statusCode != 200) {
//             throw new RuntimeException("Failed to call CAP service: " + statusCode);
//         }

//         return RepeatStatus.FINISHED;
//     }
// }
