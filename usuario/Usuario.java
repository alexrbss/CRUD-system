import java.sql.Date;

public class Usuario {
    private long id;
    private String nome;
    private long cpf;
    private Date dataDeNascimento;
    private String cidade;

    // Construtor sem ID: usado para criar novos objetos antes de salvar no DB
    public Usuario(String nome, long cpf, Date dataDeNascimento, String cidade) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataDeNascimento = dataDeNascimento;
        this.cidade = cidade;
    }

    // Construtor com ID: usado para recuperar dados do DB
    public Usuario(long id, String nome, long cpf, Date dataDeNascimento, String cidade) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.dataDeNascimento = dataDeNascimento;
        this.cidade = cidade;
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public Date getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(Date dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

}