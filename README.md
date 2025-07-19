🌳 API Java TreeCount
Este projeto implementa uma API REST em Java/Spring Boot para gerenciamento de contagem de árvores. Pode ser usada para registrar, consultar e manipular dados de árvores em um sistema.

🔧 Technologies
Java 11+

Spring Boot

Spring Web

Spring Security

Spring Data JPA (com banco de dados H2 ou outro relacional)

Docker (para conteinerização)

(Opcional) Spring Validation, Lombok, MapStruct, etc.

🚀 Funcionalidades
Endpoints CRUD para entidades (árvores, regiões, contagens)

Autenticação e autorização via Spring Security (JWT ou Basic Auth)

Validação de dados de entrada

Documentação interativa com Swagger/OpenAPI (por exemplo em /swagger-ui.html)

Configuração para execução via Docker

🗃️ Entidades Principais
Tree – representa uma árvore; campos típicos: id, species, latitude, longitude, plantingDate, height

Count – contabiliza árvores em determinada região/data; campos: id, treeId, region, count, date

📦 Pré-requisitos
Java 11 ou superior

Maven ou Gradle

Docker & Docker Compose (opcional para ambiente conteinerizado)

⚙️ Configuração
Clone o repositório:

bash
Copiar
Editar
git clone https://github.com/Arthur-Sidor/API_Java_TreeCount.git
cd API_Java_TreeCount
Configure variáveis de ambiente em application.properties ou application.yml:

properties
Copiar
Editar
spring.datasource.url=jdbc:h2:mem:treecountdb
spring.datasource.username=sa
spring.datasource.password=
jwt.secret=SEU_SEGREDO_JWT
(Opcional) Inicie banco de dados via Docker Compose:

bash
Copiar
Editar
docker-compose up -d
▶️ Execução
Com Maven
bash
Copiar
Editar
mvn spring-boot:run
Com Gradle
bash
Copiar
Editar
./gradlew bootRun
Com Docker
bash
Copiar
Editar
docker build -t treecount-api .
docker run -p 8080:8080 treecount-api
🧪 API Endpoints
Método	Endpoint	Descrição
GET	/api/trees	Lista todas as árvores
GET	/api/trees/{id}	Retorna árvore por ID
POST	/api/trees	Cria nova árvore
PUT	/api/trees/{id}	Atualiza árvore existente
DELETE	/api/trees/{id}	Remove árvore
GET	/api/counts	Lista contagens de árvores
POST	/api/counts	Registra ou atualiza contagem
