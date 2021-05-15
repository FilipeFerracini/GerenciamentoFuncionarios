package entities;

import java.util.Date;

public abstract class Funcionario {

    protected String CpfCnpj;
    protected String Nome;
    protected Date DataNascimento;
    protected String Endereco;

    public Funcionario(String cpfCnpj, String nome, Date dataNascimento, String endereco) {
        CpfCnpj = cpfCnpj;
        Nome = nome;
        DataNascimento = dataNascimento;
        Endereco = endereco;
    }
}
