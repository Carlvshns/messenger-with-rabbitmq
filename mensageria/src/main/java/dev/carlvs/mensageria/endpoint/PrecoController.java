package dev.carlvs.mensageria.endpoint;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import libs.domains.constantes.RabbitMQConstantes;
import libs.domains.dto.PrecoDto;

import dev.carlvs.mensageria.service.RabbitMQService;

@RestController
@RequestMapping(value = "/precos")
public class PrecoController {

    private RabbitMQService rabbitMQService;

    public PrecoController(RabbitMQService rabbitMQService) {
        this.rabbitMQService = rabbitMQService;
    }

    @PutMapping
    private <T> ResponseEntity <T> alteraPreco(@RequestBody PrecoDto precoDto) {
        
        this.rabbitMQService.enviaMensagem(RabbitMQConstantes.FILA_PRECO , precoDto);

        return new ResponseEntity<T>(HttpStatus.OK);
    }
}
