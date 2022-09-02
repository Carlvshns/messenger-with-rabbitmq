package dev.carlvs.consumerestoque.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import libs.domains.constantes.RabbitMQConstantes;
import libs.domains.dto.EstoqueDto;

@Component
public class EstoqueConsumer {
    
    @RabbitListener(queues = RabbitMQConstantes.FILA_ESTOQUE, containerFactory = "rabbitListenerContainerFactory")
    private void consumidor(EstoqueDto estoqueDto) {
        
        System.out.println(estoqueDto.codigoProduto);
        System.out.println("------------------------");
        System.out.println(estoqueDto.quantidade);
        System.out.println("------------------------");
    }
}
