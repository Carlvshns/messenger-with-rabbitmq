package dev.carlvs.consumerestoque.config;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.config.DirectRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.listener.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ErrorHandler;

import dev.carlvs.consumerestoque.exceptions.CustomErrorStrategy;
import dev.carlvs.consumerestoque.exceptions.TratamentoErroHandler;

@Configuration
public class RabbitMQConfig {
    
    @Bean
    public RabbitListenerContainerFactory<DirectMessageListenerContainer> rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        
        DirectRabbitListenerContainerFactory factory = new DirectRabbitListenerContainerFactory();

        factory.setConnectionFactory(connectionFactory);
        factory.setAcknowledgeMode(AcknowledgeMode.AUTO);

        factory.setPrefetchCount(2);
        //factory.setGlobalQos(true);
        //factory.setConsumersPerQueue(1);

        //factory.setErrorHandler(new TratamentoErroHandler());

        factory.setErrorHandler(errorHandler());

        return factory;
    }

    @Bean
    public FatalExceptionStrategy customErrorStrategy() {

        return new CustomErrorStrategy();
    }

    @Bean
    public ErrorHandler errorHandler() {
        return new ConditionalRejectingErrorHandler(customErrorStrategy());
    }
}
