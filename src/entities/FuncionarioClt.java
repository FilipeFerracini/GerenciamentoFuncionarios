package entities;

import java.util.Comparator;
import java.util.Date;

public class FuncionarioClt extends Funcionario implements Comparable {
    private final double fgts = 0.08;
    private final double inss = 0.075;
    private final double salarioBruto = 375.00;

    public FuncionarioClt(String cpfCnpj, String nome, Date dataNascimento, String endereco) {
        super(cpfCnpj, nome, dataNascimento, endereco);
    }

    public double getSalarioBruto() {
        return salarioBruto;
    }

    @Override
    public double salarioSemanal() {
        return salarioBruto * (1 - fgts + inss);
    }

    @Override
    public String toString() {
        return getNome() + " - Tipo de Contrato: CLT";
    }

    /*@Override
    public int compareTo(Funcionario func) {
        return Comparator.comparing(Funcionario::getCpfCnpj)
                .thenComparing(Funcionario::getNome)
                .thenComparing(Funcionario::getDataNascimento)
                .compare(this, func);
    }*/
}
