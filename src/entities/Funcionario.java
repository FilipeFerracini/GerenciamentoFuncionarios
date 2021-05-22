package entities;

import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class Funcionario implements Comparable{

    private String cpfCnpj;
    private String nome;
    private Date dataNascimento;
    private String endereco;
    private Map<String, String> escala = new LinkedHashMap<>() {{
        put("DOMINGO", null);
        put("SEGUNDA-FEIRA", null);
        put("TERÇA-FEIRA", null);
        put("QUARTA-FEIRA", null);
        put("QUINTA-FEIRA", null);
        put("SEXTA-FEIRA", null);
        put("SÁBADO", null);
    }};

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

    public abstract double salarioSemanal();

    @Override
    public int compareTo(Funcionario func) {
        return getNome().compareTo(func.getNome());

    }

}
