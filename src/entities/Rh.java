package entities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Rh {
    Map<String, Funcionario> funcionarios = new HashMap<>();

    public boolean addFunc(Funcionario func){
        if(funcionarios.containsKey(func.getCpfCnpj()))
            return false;
        funcionarios.put(func.getCpfCnpj(), func);
        return true;
    }

    public boolean removeFunc(Funcionario func){
        if(!funcionarios.containsKey(func.getCpfCnpj()))
            return false;
        funcionarios.remove(func.getCpfCnpj());
        return true;
    }

    public Funcionario buscaFunc(Funcionario func){
        return funcionarios.get(func.getCpfCnpj());
    }

    /*public void pagarFuncionario(){

    }

    public List<Funcionario> escalaGeral(){

    }

    public List<?> escalaTipo(Class<?> func){

    }

    public void relatorioTotal(char ord){

    }*/
}
