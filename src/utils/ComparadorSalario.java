package utils;

import entities.Funcionario;

import java.util.Comparator;

public class ComparadorSalario implements Comparator<Funcionario> {
    @Override
    public int compare(Funcionario func1, Funcionario func2) {
        if (func1.salarioSemanal() < func2.salarioSemanal())
            return -1;
        if (func1.salarioSemanal() > func2.salarioSemanal())
            return 1;
        return 0;
    }
}
