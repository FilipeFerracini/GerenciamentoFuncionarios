package entities;

import java.util.Comparator;
import java.util.Date;

public class FuncionarioPj extends Funcionario{
    private final double salario = 500.00;

    public FuncionarioPj(String cpfCnpj, String nome, Date dataNascimento, String endereco) {
        super(cpfCnpj, nome, dataNascimento, endereco);
    }

    public double getSalario() {
        return salario;
    }

    @Override
    public double salarioSemanal() {
        return salario;
    }

    @Override
    public String toString() {
        StringBuilder cnpj = new StringBuilder(getCpfCnpj())
                .insert(2, ".")
                .insert(6, ".")
                .insert(10, "/")
                .insert(15, "-");

        return "Nome: " + getNome()
                + "\nCPF: " + cnpj.toString()
                + "\nData de Nascimento: " + sdf1.format(getDataNascimento())
                + "\nEndereço: " + getEndereco()
                + "\nTipo de Contrato: PJ"
                + "\nSalário: " + String.format("%.2f", salarioSemanal());
    }
}
