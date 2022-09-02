const amqp = require('amqplib');

const fila = "PRECO";

amqp.connect( {
   host: 'localhost',
   port: 5672,
   username: 'guest',
   password: 'guest' 
}).then((conexao) => {
    
    conexao.createChannel()
        .then((canal) => {

            canal.consume(fila, (mensagem) => {

                console.log(mensagem.content.toString())
            }/*,{noAck: true}*/)
        })
        .catch((erro) => console.log(erro))
})
.catch((erro) => console.log(erro))

