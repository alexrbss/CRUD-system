# Sistema CRUD de Usuários

Sistema de gerenciamento de usuários **containerizado com Docker** desenvolvido em Java utilizando arquitetura MVC (Model-View-Controller) para a disciplina de Arquitetura de Software.

## 📋 Descrição

Este projeto implementa um **sistema CRUD (Create, Read, Update, Delete) totalmente containerizado** para gerenciamento de usuários, utilizando **Docker** para orquestração de serviços. O sistema executa em containers isolados, com a aplicação Java e banco de dados PostgreSQL em ambientes separados e comunicação via rede interna do Docker.

**Principais características da containerização:**
- 🐳 **Aplicação Java containerizada** com OpenJDK 21
- 🗄️ **PostgreSQL em container dedicado** 
- 🔗 **Comunicação entre containers** via Docker Compose
- 🚀 **Deploy simplificado** com um único comando
- 🔧 **Ambiente isolado e reproduzível**

O sistema permite cadastrar, consultar, atualizar e excluir informações de usuários de forma interativa através de interface de console.

## 🐳 Arquitetura de Containers

### Composição dos Serviços:
```
┌─────────────────────────────────────────────────────────┐
│                   Docker Network                        │
│  ┌─────────────────┐    ┌──────────────────────────┐    │
│  │   crud-app      │    │       postgres           │    │
│  │  (Java App)     │◄──►│    (PostgreSQL 15)       │    │
│  │  - OpenJDK 21   │    │  - Port: 5432            │    │
│  │  - CRUD System  │    │  - Volume: postgres_data │    │
│  └─────────────────┘    └──────────────────────────┘    │
└─────────────────────────────────────────────────────────┘
```

## 🏗️ Arquitetura de Software

O projeto segue o padrão arquitetural **MVC (Model-View-Controller)**:

- **Model (`Usuario.java`)**: Representa a entidade usuário com seus atributos e comportamentos
- **View (`UsuarioView.java`)**: Gerencia a interface com o usuário via console
- **Controller (`UsuarioController.java`)**: Controla a lógica de negócio e validações
- **DAO (`UsuarioDAO.java`)**: Responsável pelo acesso aos dados no banco PostgreSQL
- **Main (`UsuarioMain.java`)**: Ponto de entrada da aplicação com menu principal

## 🚀 Tecnologias Utilizadas

- **Docker & Docker Compose**: Containerização e orquestração de serviços ⭐
- **Java 21**: Linguagem de programação principal
- **PostgreSQL 15**: Sistema de gerenciamento de banco de dados
- **JDBC**: Conectividade com banco de dados
- **Git**: Controle de versão

## 📁 Estrutura do Projeto

```
📁 projeto/
├── 📁 docs/                    
│   ├── README.md
│   └── DOCKER.md
├── 📁 src/                     
│   ├── 📁 database/
│   │   └── DataBaseConnection.java
│   └── 📁 usuario/
│       ├── Usuario.java        
│       ├── UsuarioView.java    
│       ├── UsuarioController.java 
│       ├── UsuarioDAO.java     
│       └── UsuarioMain.java    
├── 📁 lib/                     
│   └── postgresql-42.7.7.jar
├── 📁 docker/                  
│   ├── Dockerfile
│   └── init.sql
└── docker-compose.yml
```

## ⚙️ Requisitos

- **Docker** e **Docker Compose** (obrigatório - principal requisito da atividade)
- **Git** (para clonar o repositório)

> **Nota**: Não é necessário ter Java ou PostgreSQL instalados localmente, pois tudo executa em containers!

## 🔧 Como Executar

### 🐳 Execução com Docker

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/alexrbss/CRUD-system.git
   cd CRUD-system
   ```

2. **Execute com Docker Compose:**
   ```bash
   docker-compose up --build
   ```
   > Este comando irá:
   > - 🏗️ Construir a imagem da aplicação Java
   > - 🗄️ Inicializar o container PostgreSQL
   > - 🔗 Configurar a rede interna entre os containers
   > - 🚀 Executar o sistema automaticamente

3. **Acesse o container da aplicação:**
   ```bash
   docker exec -it crud-app /bin/bash
   ```

4. **Execute o sistema dentro do container:**
   ```bash
   java -cp "/app/lib/postgresql-42.7.7.jar:/app" src.usuario.UsuarioMain
   ```
   - Use o menu interativo para navegar pelas funcionalidades
   - Todos os dados são persistidos no container PostgreSQL

5. **Para parar o sistema:**
   ```bash
   docker-compose down
   ```

### 🔧 Execução Local (Alternativa - Para Desenvolvimento)

1. **Pré-requisitos:**
   - Java 21+ instalado localmente
   - PostgreSQL instalado e executando
   - Banco de dados `UserDB` criado
   - Tabela `usuarios` criada (veja `docker/init.sql`)

2. **Configure as variáveis de ambiente ou edite `DataBaseConnection.java`:**
   ```
   DB_HOST=localhost
   DB_PORT=5432
   DB_NAME=UserDB
   DB_USER=postgres
   DB_PASSWORD=sua_senha
   ```

3. **Compile e execute:**
   ```bash
   # Compilar
   javac -cp "lib/postgresql-42.7.7.jar:." src/database/*.java src/usuario/*.java
   
   # Executar
   java -cp "lib/postgresql-42.7.7.jar:." src.usuario.UsuarioMain
   ```

## 📊 Funcionalidades

### Menu Principal
1. **Cadastrar Usuário**: Adiciona um novo usuário ao sistema
2. **Buscar Usuário**: Localiza usuário por CPF
3. **Listar Todos os Usuários**: Exibe todos os usuários cadastrados
4. **Atualizar Usuário**: Modifica informações de um usuário existente
5. **Deletar Usuário**: Remove um usuário do sistema
6. **Sair**: Encerra a aplicação

### Validações Implementadas
- ✅ **CPF**: Formato e duplicidade
- ✅ **Data de Nascimento**: Formato válido (dd/MM/yyyy)
- ✅ **Campos obrigatórios**: Validação de entrada
- ✅ **Tratamento de erros**: Mensagens informativas


## 🐳 Docker

Para mais detalhes sobre a configuração Docker, consulte [DOCKER.md](DOCKER.md).

### Serviços Docker:
- **crud-app**: Aplicação Java
- **postgres**: Banco de dados PostgreSQL 15

### Portas:
- **5432**: PostgreSQL (acesso externo opcional)

## 🔄 Padrões Implementados

- **MVC Architecture**: Separação clara de responsabilidades
- **DAO Pattern**: Abstração do acesso a dados
- **Dependency Injection**: Injeção de dependências entre camadas
- **Exception Handling**: Tratamento robusto de erros
- **Environment Variables**: Configuração flexível via variáveis de ambiente

## 📝 Licença

Este projeto foi desenvolvido para fins acadêmicos na disciplina de Arquitetura de Software.

---