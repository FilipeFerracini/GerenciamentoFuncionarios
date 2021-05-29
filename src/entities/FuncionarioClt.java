package entities;

import java.util.Comparator;
import java.util.Date;

public class FuncionarioClt extends Funcionario {
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
        return salarioBruto * (1 - (fgts + inss));
    }

    @Override
    public String toString() {
        StringBuilder cpf = new StringBuilder(getCpfCnpj())
                .insert(3, ".")
                .insert(7, ".")
                .insert(11, "-");

        return "Nome: " + getNome()
                + "\nCPF: " + cpf.toString()
                + "\nData de Nascimento: " + sdf1.format(getDataNascimento())
                + "\nEndereço: " + getEndereco()
                + "\nTipo de Contrato: CLT"
                + "\nSalário: " + String.format("%.2f", salarioSemanal());
    }
}
