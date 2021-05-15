package entities;

import java.util.Date;

public class FuncionarioDiarista extends Funcionario{
    private double SalarioDiario;
    private int DiasTrabalhados;

    public FuncionarioDiarista(String cpfCnpj, String nome, Date dataNascimento, String endereco, double salarioDiario, int diasTrabalhados) {
        super(cpfCnpj, nome, dataNascimento, endereco);
        SalarioDiario = salarioDiario;
        DiasTrabalhados = diasTrabalhados;
    }
}
