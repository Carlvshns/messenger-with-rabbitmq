package dev.carlvs.mensageria.endpoint;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import libs.domains.constantes.RabbitMQConstantes;
import libs.domains.dto.EstoqueDto;

import dev.carlvs.mensageria.service.RabbitMQService;

@RestController
@RequestMapping(value = "/estoques")
public class EstoqueController {

    private RabbitMQService rabbitMQService;
    
    public EstoqueController(RabbitMQService rabbitMQService) {

        this.rabbitMQService = rabbitMQService;
    }

    @PutMapping
    private <T> ResponseEntity <T> alteraEstoque(@RequestBody EstoqueDto estoqueDto) {
        
        this.rabbitMQService.enviaMensagem(RabbitMQConstantes.FILA_ESTOQUE , estoqueDto);
        return new ResponseEntity<T>(HttpStatus.OK);
    }
}
