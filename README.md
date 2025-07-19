<h1 align="center">🌳 TreeCount API</h1>

<p align="center">
  Uma API REST em Java com Spring Boot para contagem e gerenciamento de árvores.  
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-11%2B-blue.svg" />
  <img src="https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen.svg" />
  <img src="https://img.shields.io/badge/License-MIT-yellow.svg" />
</p>

---

## 📚 Tabela de Conteúdo

- [🚀 Sobre o Projeto](#-sobre-o-projeto)
- [🛠 Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [⚙️ Configuração do Projeto](#️-configuração-do-projeto)
- [▶️ Como Executar](#️-como-executar)
- [🧪 Endpoints da API](#-endpoints-da-api)
- [📦 Exemplos de Requisições](#-exemplos-de-requisições)
- [🔐 Segurança](#-segurança)
- [🧪 Testes](#-testes)
- [📘 Documentação](#-documentação)
- [🤝 Contribuindo](#-contribuindo)
- [📄 Licença](#-licença)

---

## 🚀 Sobre o Projeto

A **TreeCount API** permite registrar, consultar e gerenciar árvores e suas contagens por região. Ideal para aplicações de monitoramento ambiental, inventário florestal e gestão urbana verde.

---

## 🛠 Tecnologias Utilizadas

- Java 11+
- Spring Boot
- Spring Web
- Spring Security
- Spring Data JPA
- H2 (ou outro banco relacional)
- Swagger (OpenAPI)
- Docker

---

## ⚙️ Configuração do Projeto

Clone o repositório:

```bash
git clone https://github.com/Arthur-Sidor/API_Java_TreeCount.git
cd API_Java_TreeCount
```



🔧 application.properties (exemplo)
properties

spring.datasource.url=jdbc:h2:mem:treecountdb
spring.datasource.username=sa
spring.datasource.password=
spring.h2.console.enabled=true

jwt.secret=SEU_SEGREDO_JWT
🐳 Docker Compose (opcional)


docker-compose up -d
▶️ Como Executar
✅ Com Maven

mvn spring-boot:run
✅ Com Gradle

./gradlew bootRun
🐳 Com Docker

docker build -t treecount-api .
docker run -p 8080:8080 treecount-api


🧪 Endpoints da API
Método	Rota	Descrição
GET	/api/trees	Lista todas as árvores
GET	/api/trees/{id}	Retorna árvore por ID
POST	/api/trees	Cria uma nova árvore
PUT	/api/trees/{id}	Atualiza árvore existente
DELETE	/api/trees/{id}	Remove árvore
GET	/api/counts	Lista contagens de árvores
POST	/api/counts	Cria/atualiza contagem

📦 Exemplos de Requisições
📌 Criar uma árvore

curl -X POST http://localhost:8080/api/trees \
-H "Content-Type: application/json" \
-d '{"species":"Ipê Amarelo","latitude":-23.55,"longitude":-46.63,"plantingDate":"2023-03-20","height":4.2}'
📌 Registrar contagem

curl -X POST http://localhost:8080/api/counts \
-H "Content-Type: application/json" \
-d '{"treeId":1,"region":"Zona Sul","count":12,"date":"2025-07-19"}'

🔐 Segurança
Autenticação via JWT (ou outra estratégia configurada)

Configure o jwt.secret no application.properties

Proteção de endpoints sensíveis com Spring Security

🧪 Testes
Execute testes automatizados com:

mvn test
ou

./gradlew test
📘 Documentação
Acesse: http://localhost:8080/swagger-ui.html
