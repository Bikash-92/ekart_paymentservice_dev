/**
 * Author: BIKASH
 */
package com.ekart.config;


import com.google.api.gax.rpc.ApiException;
import com.google.cloud.pubsub.v1.SubscriptionAdminClient;
import com.google.cloud.pubsub.v1.TopicAdminClient;
import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.integration.inbound.PubSubInboundChannelAdapter;
import com.google.pubsub.v1.ProjectSubscriptionName;
import com.google.pubsub.v1.ProjectTopicName;
import com.google.pubsub.v1.PushConfig;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Configuration
@Slf4j
public class PubsubConfig {
    Logger logger = LoggerFactory.getLogger(PubsubConfig.class);

    @Value("${pubsub.ekart.topic-name}")
    private String topicName;

    @Value("${pubsub.ekart.subscription-name}")
    private String subscriptionName;

    @Value("${spring.cloud.gcp.project-id}")
    private String projectId;



    @Bean
    public MessageChannel myInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel myOutputChannel() {
        return new DirectChannel();
    }




    @Bean
    public PubSubInboundChannelAdapter messageChannelAdapter(
            @Qualifier("myInputChannel") MessageChannel inputChannel,
            PubSubTemplate pubSubTemplate) {
        PubSubInboundChannelAdapter adapter = new PubSubInboundChannelAdapter(pubSubTemplate, subscriptionName);
        adapter.setOutputChannel(inputChannel);
        adapter.setPayloadType(String.class);
        //adapter.setPayloadConverter(new SimplePubSubMessageConverter());
        return adapter;
    }



    @PostConstruct
    public void loadCredentials() throws Exception {
        logger.info("INIT.....");
        createTopicIfNotExists();
        createSubscriptionIfNotExists();
        logger.info("COmplted");
    }



    public void createTopicIfNotExists() throws IOException {
        ProjectTopicName topic = ProjectTopicName.of(projectId, topicName);
        try (TopicAdminClient topicAdminClient = TopicAdminClient.create()) {
            // Check if the topic already exists
            try {
                topicAdminClient.getTopic(topic);
                System.out.println("Topic already exists: " + topicName);
            } catch (ApiException e) {
                // If topic doesn't exist, create it
                topicAdminClient.createTopic(topic);
                System.out.println("Topic created: " + topicName);
            }
        }
    }

    // Create the subscription if it doesn't exist
    public void createSubscriptionIfNotExists() throws IOException {
        ProjectTopicName topic = ProjectTopicName.of(projectId, topicName);
        ProjectSubscriptionName subscription = ProjectSubscriptionName.of(projectId, subscriptionName);

        try (SubscriptionAdminClient subscriptionAdminClient = SubscriptionAdminClient.create()) {
            // Check if the subscription already exists
            try {
                subscriptionAdminClient.getSubscription(subscription);
                System.out.println("Subscription already exists: " + subscriptionName);
            } catch (ApiException e) {
                // If subscription doesn't exist, create it
                subscriptionAdminClient.createSubscription(subscription,topic, PushConfig.newBuilder().build(), 10);
                System.out.println("Subscription created: " + subscriptionName);
            }
        }
    }
}
