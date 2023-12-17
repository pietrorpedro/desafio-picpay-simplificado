# Desafio Back-end PicPay

Este é o meu projeto para o desafio Back-end do PicPay. Abaixo, você encontrará informações sobre a implementação, instruções para configurar e executar o projeto, e detalhes sobre as funcionalidades implementadas.

## Desafio

O desafio pode ser encontrado no link: [Desafio Back-end PicPay](https://github.com/PicPay/picpay-desafio-backend).

## Materiais Úteis

Consultei o Google, Stackoverflow e projetos particulares para obter referências e solucionar dúvidas durante o desenvolvimento.

## Funcionalidades Implementadas

### Cadastro de Usuários

- Nome Completo, CPF, e-mail e Senha são obrigatórios.
- CPF/CNPJ e e-mails devem ser únicos no sistema.

### Transferência de Dinheiro

- Usuários podem enviar dinheiro para lojistas e entre usuários.
- Lojistas apenas recebem transferências e não enviam dinheiro.
- A operação de transferência é tratada como uma transação, sendo revertida em qualquer caso de inconsistência. O dinheiro retorna para a carteira do usuário que envia em casos de falhas.

### Validação de Saldo

- Antes de finalizar a transferência, valida se o usuário tem saldo suficiente.

### Consulta a Serviço Autorizador Externo

- Consulta um serviço autorizador externo antes de finalizar a transferência.
- Mock para simulação.

## Execução do Projeto

### Pré-requisitos

- Certifique-se de ter o Java e o Maven instalados.
- O projeto é desenvolvido usando o Spring Boot em Java.

### Clone o Repositório

```bash
git clone https://github.com/pietrorpedro/desafio-picpay-simplificado
```

### Configuração e Execução

1. Abra o projeto em sua IDE favorita.
2. Execute o arquivo "PicpayApplication" como uma aplicação Spring Boot.
3. O projeto estará disponível em http://localhost:8080.

