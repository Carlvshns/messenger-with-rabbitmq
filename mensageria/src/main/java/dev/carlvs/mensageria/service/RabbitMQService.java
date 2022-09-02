package dev.carlvs.mensageria.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQService {

    private RabbitTemplate rabbitTemplate;
    private ObjectMapper objectMapper;

    public RabbitMQService(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {

        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    public void enviaMensagem(String nomeFila, Object mensagem) {

        try {

            String mensagemJson = this.objectMapper.writeValueAsString(mensagem);

            this.rabbitTemplate.convertAndSend(nomeFila, mensagemJson);
        } catch (JsonProcessingException e) {

            e.printStackTrace();
        }
    }
}
