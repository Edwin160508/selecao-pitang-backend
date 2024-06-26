# Tecnologia Utilizada - Sistema de Usuários de Carros
- Java Versão 21
- SpringBoot 3.2.4
- Migrations com Flyway
- Testes Unitários com JUnit 5
- Mockito

## Passo a Passo
- Assim que baixar o projeto executar comando mvn clean package, irá ser gerado uma pasta target com jar.

# Casos de Uso - Sistema de Usuários de Carros

## Introdução
- Este documento descreve os casos de uso para um sistema de gerenciamento de usuários de carros, fornecendo uma API RESTful para interação com o sistema. O sistema permite que os usuários mantenham informações sobre os carros que possuem, com suporte para usuários que possuem mais de um carro.

## Casos de Uso
### UC1: Criar Usuário

#### Descrição:
- Este caso de uso descreve o processo de criação de um novo usuário no sistema.

#### Ator Principal:
- Usuário

#### Pré-Condições:
- Nenhuma

#### Fluxo Principal:
1. O usuário envia uma solicitação para criar um novo usuário fornecendo as informações necessárias, como nome, email e informações do carro.
2. O sistema valida os dados fornecidos.
3. O sistema cria um novo usuário no sistema com as informações fornecidas.
4. O sistema retorna uma resposta indicando o sucesso da operação.

#### Pós condições
- Um novo usuário é criado no sistema.

### UC2: Adicionar Carro

#### Descrição:
- Este caso de uso descreve o processo de adição de um novo carro ao perfil de um usuário existente.

#### Ator Principal:
- Usuário

#### Pré-Condições:
- O usuário deve estar autenticado e ter um perfil no sistema.

#### Fluxo Principal:
1. O usuário envia uma solicitação para adicionar um novo carro ao seu perfil, fornecendo as informações do carro, como marca, modelo e ano.
2. O sistema valida os dados fornecidos.
3. O sistema adiciona o carro ao perfil do usuário.
4. O sistema retorna uma resposta indicando o sucesso da operação.

#### Pós condições
- O carro é adicionado ao perfil do usuário.

### UC3: Listar Carros de um Usuário

#### Descrição:
- Este caso de uso descreve o processo de listar todos os carros pertencentes a um usuário específico.

#### Ator Principal:
- Usuário

#### Pré-Condições:
- O usuário deve estar autenticado e ter um perfil no sistema.

#### Fluxo Principal:
1. O usuário envia uma solicitação para listar todos os carros associados ao seu perfil.
2. O sistema recupera todos os carros associados ao perfil do usuário.
3. O sistema retorna uma resposta contendo a lista de carros.

#### Pós condições
- O usuário recebe uma lista de todos os carros associados ao seu perfil.

### UC4: Remover Carro

#### Descrição:
- Este caso de uso descreve o processo de remoção de um carro do perfil de um usuário.

#### Ator Principal:
- Usuário

#### Pré-Condições:
- O usuário deve estar autenticado e ter um perfil no sistema.

#### Fluxo Principal:
1. O usuário envia uma solicitação para remover um carro específico do seu perfil, fornecendo o identificador do carro.
2. O sistema valida o identificador fornecido.
3. O sistema remove o carro do perfil do usuário.
4. O sistema retorna uma resposta indicando o sucesso da operação.

#### Pós condições
- O carro é removido do perfil do usuário.

### Conclusão
Este documento descreve os casos de uso para um sistema de gerenciamento de usuários de carros, fornecendo uma API RESTful para interação com o sistema. Os casos de uso abrangem desde a criação de usuários e adição de carros até a listagem e remoção de carros associados aos perfis dos usuários.
