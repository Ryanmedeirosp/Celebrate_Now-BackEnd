﻿# 🌹 Celebrate Back-End

> Este é o Back-End do "Celebrate Now", um site dedicado ao gerenciamento de cerimonias de casamento.

## 📝 Sobre o Projeto

A criação deste software de gerenciamento de casamento visa a melhoria da eficiência, organização e controle do trabalho dos cerimonialista de casamento, visando otimizar e organizar as múltiplas tarefas envolvidas na organização de um evento.

## 🚀 Tecnologias Utilizadas  

Este projeto foi desenvolvido com as seguintes tecnologias:

- [Java 21]([https://tecnologia1.com](https://www.oracle.com/br/java/technologies/downloads/))
- [Springboot]([https://tecnologia2.com](https://spring.io/))

## ⚙️ Instalação  

### 🔧 Pré-requisitos  
Antes de começar, você precisa ter instalado na sua máquina:  
- **[Java](https://tecnologia1.com](https://www.oracle.com/br/java/technologies/downloads/))**  
- **[Git](https://git-scm.com/)**  

### 📥 Clone o Repositório

git clone https://github.com/Ryanmedeirosp/Celebrate_Now-BackEnd

### 🛠 Configurações internas

Renomeie main\java\com\celebrate\backend\models\ <em>Dto</em> para main\java\com\celebrate\backend\models\ <em>dto</em>.

Alterar os seguintes parâmetros para utilizar o envio de emails:

``` 
spring.mail.username=MY_EMAIL_HERE
spring.mail.password=MY_16_CHARACTER_PASSWORD_HERE
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
mail.debug=true
```

Busque o arquivo <em>BackendApplication.java</em> e rode na sua máquina.

Lembrando que esta é uma aplicação local, logo, você deve utlizar <em>http://localhost:8080</em> na sua URL para utilizá-la.

## EndPoints

### User

Esses Endpoints referem-se aos Usuários.

Rota: GET | /users

Descrição: Seleciona todos os usuaários registrados.

```
{
    "id": 0,
    "name": "string",
    "email": "string",
    "registerDate": "2024-11-21",
    "singleCard": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
    "status": true,
    "loans": [
      {
        "id": 0,
        "startDate": "2024-11-21",
        "devolutionDate": "2024-11-21",
        "status": "EM_ANDAMENTO",
        "books": [
          {
            "id": 0,
            "title": "string",
            "author": "string",
            "isbn": "string",
            "status": true,
            "yearOfPublication": 0
          }
        ]
      }
    ]
  }
```
