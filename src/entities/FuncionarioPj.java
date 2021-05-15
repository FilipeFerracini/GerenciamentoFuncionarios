package entities;

import java.util.Date;

public class FuncionarioPj extends Funcionario{
    private double Salario;

    public FuncionarioPj(String cpfCnpj, String nome, Date dataNascimento, String endereco, double salario) {
        super(cpfCnpj, nome, dataNascimento, endereco);
        Salario = salario;
    }
}
