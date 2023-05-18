# Estacionamento

Este é um projeto de um sistema de estacionamento desenvolvido em Java utilizando o framework Spring Boot.

## Descrição

O projeto consiste em um sistema de estacionamento que permite cadastrar e atualizar informações de veículos. Ele utiliza a arquitetura de serviço (Service) para implementar as funcionalidades relacionadas aos veículos.

## Funcionalidades

### Cadastrar veículo

A classe `VeiculoService` contém o método `cadastraVeiculo` que permite cadastrar um novo veículo no sistema de estacionamento. Antes de salvar o veículo, são realizadas as seguintes validações:

- Verifica se a placa do veículo está preenchida.
- Verifica se o nome do modelo do veículo está preenchido.
- Verifica se o nome da marca do modelo do veículo está preenchido.
- Verifica se a placa do veículo possui no máximo 10 caracteres.
- Verifica se o nome do modelo do veículo possui no máximo 50 caracteres.
- Verifica se o nome da marca do modelo do veículo possui no máximo 50 caracteres.
- Verifica se o ano do veículo está preenchido.
- Verifica se a cor do veículo está preenchida.
- Verifica se o tipo do veículo está preenchido.
- Verifica se a placa do veículo já existe no sistema.

### Atualizar veículo

A classe `VeiculoService` também possui o método `atualizaVeiculo` que permite atualizar as informações de um veículo existente. Antes de realizar a atualização, são feitas as seguintes verificações:

- Verifica se o veículo existe no sistema.
- Verifica se a placa do veículo está preenchida.
- Verifica se o nome do modelo do veículo está preenchido.
- Verifica se o nome da marca do modelo do veículo está preenchido.
- Verifica se a cor do veículo está preenchida.
- Verifica se o tipo do veículo está preenchido.
- Verifica se a placa do veículo possui no máximo 10 caracteres.
- Verifica se o nome do modelo do veículo possui no máximo 50 caracteres.
- Verifica se o nome da marca do modelo do veículo possui no máximo 50 caracteres.
- Verifica se a placa do veículo já existe no sistema.

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
