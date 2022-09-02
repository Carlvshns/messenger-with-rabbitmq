package dev.carlvs.consumerestoque.exceptions;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;
import org.springframework.util.ErrorHandler;

public class TratamentoErroHandler implements ErrorHandler {

    @Override
    public void handleError(Throwable t) {

        String nomeFila = ((ListenerExecutionFailedException) t).getFailedMessage().getMessageProperties().getConsumerQueue();
        System.out.println(nomeFila);

        String mensagem = new String(((ListenerExecutionFailedException) t).getFailedMessage().getBody());
        System.out.println(mensagem);

        System.out.println(t.getCause().getMessage());

        throw new AmqpRejectAndDontRequeueException("Nao retorna a fila");
    }
}
