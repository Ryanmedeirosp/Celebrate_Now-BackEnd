# 🌹 Celebrate Back-End

> Este é o Back-End do "Celebrate Now", um site dedicado ao gerenciamento de cerimônias de casamento.

## 📝 Sobre o Projeto

A criação deste software de gerenciamento de casamento visa a melhoria da eficiência, organização e controle do trabalho dos cerimonialistas de casamento, visando otimizar e organizar as múltiplas tarefas envolvidas na organização de um evento.

## 🚀 Tecnologias Utilizadas  

Este projeto foi desenvolvido com as seguintes tecnologias:

- [Java 21](https://www.oracle.com/br/java/technologies/downloads/)
- [Spring Boot](https://spring.io/)

## ⚙️ Instalação  

### 🔧 Pré-requisitos  
Antes de começar, você precisa ter instalado na sua máquina:  
- **[Java](https://www.oracle.com/br/java/technologies/downloads/)**  
- **[Git](https://git-scm.com/)**  

### 📥 Clone o Repositório

```bash
git clone https://github.com/Ryanmedeirosp/Celebrate_Now-BackEnd.git
```

### 🛠 Configurações internas

Renomeie `main\java\com\celebrate\backend\models\Dto` para `main\java\com\celebrate\backend\models\dto`.

Altere os seguintes parâmetros para utilizar o envio de e-mails:

```properties
spring.mail.username=MY_EMAIL_HERE
spring.mail.password=MY_16_CHARACTER_PASSWORD_HERE
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
mail.debug=true
```

Busque o arquivo `BackendApplication.java` e rode na sua máquina.

Lembrando que esta é uma aplicação local, logo, você deve utilizar `http://localhost:8080` na sua URL para utilizá-la.

## 📌 Endpoints

Abaixo estão os principais endpoints disponíveis na API.

### 🏢 Supplier Controller
- **PUT** `/supplier/{supplierId}` - Atualiza um fornecedor.
- **POST** `/supplier` - Adiciona um novo fornecedor.
- **GET** `/supplier/{idCeremonialist}` - Busca fornecedores de um cerimonialista.

### 👤 Client Controller
- **PUT** `/client/{clientId}` - Atualiza um cliente.
- **POST** `/client` - Adiciona um novo cliente.
- **GET** `/client/{idCeremonialist}` - Busca clientes de um cerimonialista.

### 🎭 Ceremonialist Controller
- **PUT** `/ceremonialist/{ceremonialistId}` - Atualiza um cerimonialista.
- **POST** `/ceremonialist` - Adiciona um novo cerimonialista.
- **POST** `/ceremonialist/login` - Realiza login de um cerimonialista.

### 📦 Items Controller
- **POST** `/item` - Adiciona um item ao orçamento.
- **GET** `/item/{idBudget}` - Busca itens de um orçamento.

### ✉️ Email Controller
- **POST** `/email` - Envia um e-mail.

### 📜 Contract Controller
- **POST** `/contract` - Adiciona um novo contrato.
- **GET** `/contract/{budgetId}` - Busca contrato associado a um orçamento.

### 💰 Budget Controller
- **POST** `/budget` - Cria um orçamento.
- **GET** `/budget/{idClient}/{idCeremonialist}` - Busca orçamento por cliente e cerimonialista.
- **GET** `/budget/{idBudget}` - Busca um orçamento específico.

## 👥 Equipe de Desenvolvedores
- [Arthurdnl](https://github.com/Arthurdnl)
- [juneonju](https://github.com/juneonju)
- [Ryan Medeiros](https://github.com/Ryanmedeirosp)
- [WSgabri3l](https://github.com/WSgabri3l)

