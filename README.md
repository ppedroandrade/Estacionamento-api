# Estacionamento

Um projeto do 3° periodo de eng. de sotftware da faculdade Uniamerica, onde desenvolvemos uma API em Spring Boot
## Descrição

O projeto consiste em um sistema de estacionamento que permite cadastrar e atualizar informações de Movimentações. Ele utiliza a arquitetura de serviço (Service) para implementar as funcionalidades relacionadas as regras de negocio.

## Funcionalidades

### Cadastrar Movimentação

A classe `MovimentacaoService` contém o método `cadastraMovimentacao` que permite cadastrar um novo veículo no sistema de estacionamento. Antes de salvar o veículo, são realizadas as seguintes validações:

- Verifica se o Id está sendo gerado pelo banco
- Verifica se existe um id de veiculo
- Verifica se existe um id de condutor
- Verifica se existe uma entrada
- Verifica se existe uma saida
- Verifica se houve entrada e saida depois ou antes do expediente
- Gera multas se houver um tempoMulta
- Gera um valor total baseado na somatoria e diminuição de descontos

### Atualizar Movimentação

A classe `VeiculoService` também possui o método `atualizaVeiculo` que permite atualizar as informações de um veículo existente. Antes de realizar a atualização, são feitas as seguintes verificações:

- Verifica se o Id está sendo gerado pelo banco
- Verifica se existe um id de veiculo
- Verifica se existe um id de condutor
- Verifica se existe uma entrada
- Verifica se existe uma saida
- Verifica se houve entrada e saida depois ou antes do expediente
- Gera multas se houver um tempoMulta
- Gera um valor total baseado na somatoria e diminuição de descontos

### Iniciar a aplicação

O arquivo `EstacionamentoApplication` é responsável por iniciar a aplicação Spring Boot. Ao executar o método `main`, a aplicação é inicializada e fica pronta para receber requisições e fornecer os serviços relacionados ao estacionamento.

## Executando a aplicação

Para executar a aplicação, siga os passos abaixo:

1. Certifique-se de ter o Java instalado na versão adequada.
2. Execute o método `main` na classe `EstacionamentoApplication`.
3. A aplicação será iniciada e estará pronta para uso.

## Tecnologias utilizadas

- Java
- Spring Boot
