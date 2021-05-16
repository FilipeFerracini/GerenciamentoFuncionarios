package entities;

import java.util.Comparator;
import java.util.Date;

public class FuncionarioDiarista extends Funcionario implements Comparable{
    protected int diasTrabalhados = 0;
    private final double salario = 65.00;

    public FuncionarioDiarista(String cpfCnpj, String nome, Date dataNascimento, String endereco, int diasTrabalhados) {
        super(cpfCnpj, nome, dataNascimento, endereco);
    }

    public int getDiasTrabalhados() {
        return diasTrabalhados;
    }

    public double getSalario() {
        return salario;
    }

    @Override
    public double salarioSemanal() {
        return diasTrabalhados * salario;
    }

    /*@Override
    public int compareTo(Funcionario func) {
        return Comparator.comparing(Funcionario::getCpfCnpj)
                .thenComparing(Funcionario::getNome)
                .thenComparing(Funcionario::getDataNascimento)
                .compare(this, func);
    }*/
}
