# Use uma imagem base do OpenJDK
FROM openjdk:21-jdk-slim

# Definir diretório de trabalho
WORKDIR /app

# Copiar o driver PostgreSQL
COPY lib/postgresql-42.7.7.jar /app/lib/

# Copiar código fonte
COPY src/ /app/src/

# Compilar o projeto
RUN javac -cp "/app/lib/postgresql-42.7.7.jar:/app" /app/src/usuario/*.java /app/src/database/*.java

# Definir variáveis de ambiente para conexão com banco
ENV DB_HOST=localhost
ENV DB_PORT=5432
ENV DB_NAME=UserDB
ENV DB_USER=postgres
ENV DB_PASSWORD=1234

# Expor porta (se necessário para futuras funcionalidades web)
EXPOSE 8080

# Comando para executar a aplicação
CMD ["java", "-cp", "/app/lib/postgresql-42.7.7.jar:/app", "src.usuario.UsuarioMain"]