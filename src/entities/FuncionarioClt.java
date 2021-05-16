package entities;

import java.util.Comparator;
import java.util.Date;

public class FuncionarioClt extends Funcionario implements Comparable {
    private final double fgts = 0.08;
    private final double inss = 0.075;
    private final double salarioBase = 375.00;

    public FuncionarioClt(String cpfCnpj, String nome, Date dataNascimento, String endereco) {
        super(cpfCnpj, nome, dataNascimento, endereco);
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    @Override
    public double salarioSemanal() {
        return salarioBase * (1 - fgts + inss);
    }

    /*@Override
    public int compareTo(Funcionario func) {
        return Comparator.comparing(Funcionario::getCpfCnpj)
                .thenComparing(Funcionario::getNome)
                .thenComparing(Funcionario::getDataNascimento)
                .compare(this, func);
    }*/
}
