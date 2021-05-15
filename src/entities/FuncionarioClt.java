package entities;

import java.util.Date;

public class FuncionarioClt extends Funcionario{
    private double Salario;
    private double Impostos;

    public FuncionarioClt(String cpfCnpj, String nome, Date dataNascimento, String endereco, double salario, double impostos) {
        super(cpfCnpj, nome, dataNascimento, endereco);
        Salario = salario;
        Impostos = impostos;
    }
}
