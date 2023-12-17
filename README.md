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

### Clone o Repositório

```bash
git clone https://github.com/pietrorpedro/desafio-picpay-simplificado
```

### Configuração do Ambiente

Execute o arquivo "PicpayApplication".
