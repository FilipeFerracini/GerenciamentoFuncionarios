package utils;

import entities.Funcionario;

import java.util.Comparator;

public class ComparadorNome implements Comparator<Funcionario> {
    @Override
    public int compare(Funcionario func1, Funcionario func2) {
        return func1.getNome().compareTo(func2.getNome());
    }
}
