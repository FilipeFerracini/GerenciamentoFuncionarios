package entities;

import java.text.SimpleDateFormat;
import java.util.*;

public abstract class Funcionario {

    protected final SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");

    private String cpfCnpj;
    private String nome;
    private Date dataNascimento;
    private String endereco;
    private String[] dias = new String[7];
    private String[] situacao = new String[7];

    public Funcionario(String cpfCnpj, String nome, Date dataNascimento, String endereco) {
        this.cpfCnpj = cpfCnpj;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public String getNome() {
        return nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public String getEndereco() {
        return endereco;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String[] getDias() {
        return dias;
    }

    public String[] getSituacao() {
        return situacao;
    }

    public abstract double salarioSemanal();


}
