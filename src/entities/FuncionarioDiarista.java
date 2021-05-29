package entities;

import java.util.Comparator;
import java.util.Date;

public class FuncionarioDiarista extends Funcionario{
    private int diasTrabalhados = 0;
    private final double salario = 65.00;

    public FuncionarioDiarista(String cpfCnpj, String nome, Date dataNascimento, String endereco) {
        super(cpfCnpj, nome, dataNascimento, endereco);
    }

    public int getDiasTrabalhados() {
        return diasTrabalhados;
    }

    public void setDiasTrabalhados(int diasTrabalhados) {
        this.diasTrabalhados = diasTrabalhados;
    }

    public double getSalario() {
        return salario;
    }

    public void diaria(){
        diasTrabalhados += 1;
    }

    @Override
    public double salarioSemanal() {
        return diasTrabalhados * salario;
    }

    @Override
    public String toString() {
        String salario;
        if (salarioSemanal() > 0) salario = String.format("%.2f", getSalario());
        else salario = "Escala ainda não gerada";

        StringBuilder cpf = new StringBuilder(getCpfCnpj())
                .insert(3, ".")
                .insert(7, ".")
                .insert(11, "-");

        return "Nome: " + getNome()
                + "\nCPF: " + cpf.toString()
                + "\nData de Nascimento: " + sdf1.format(getDataNascimento())
                + "\nEndereço: " + getEndereco()
                + "\nTipo de Contrato: Diarista"
                + "\nSalário: " + salario;
    }
}
