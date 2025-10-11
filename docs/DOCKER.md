# 🐳 Docker Setup - Sistema CRUD

## Execução com Docker Compose (Recomendado)

### Pré-requisitos
- Docker
- Docker Compose

### Como executar:

```bash
# 1. Clonar o repositório
git clone https://github.com/alexrbss/CRUD-system.git
cd CRUD-system

# 2. Executar com Docker Compose
docker-compose up --build

# 3. Acessar o container da aplicação
docker exec -it crud-app /bin/bash

# 4. Dentro do container, executar:
java -cp "/app/lib/postgresql-42.7.7.jar:/app" src.usuario.UsuarioMain
```

## Execução apenas com Docker

### Construir a imagem:
```bash
docker build -t crud-system .
```

### Executar (com PostgreSQL externo):
```bash
docker run -it --rm \
  -e DB_HOST=host.docker.internal \
  -e DB_PORT=5432 \
  -e DB_NAME=UserDB \
  -e DB_USER=postgres \
  -e DB_PASSWORD=1234 \
  crud-system
```

## Variáveis de Ambiente

| Variável | Padrão | Descrição |
|----------|--------|-----------|
| DB_HOST | localhost | Host do PostgreSQL |
| DB_PORT | 5432 | Porta do PostgreSQL |
| DB_NAME | UserDB | Nome do banco |
| DB_USER | postgres | Usuário do banco |
| DB_PASSWORD | 1234 | Senha do banco |

## Estrutura

```
📁 projeto/
├── 🐳 Dockerfile
├── 🐳 docker-compose.yml
├── 🐳 .dockerignore
├── 🗄️ init.sql
├── 📁 src/
└── 📁 lib/
```