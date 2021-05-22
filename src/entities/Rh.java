package entities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

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

    public void pagarFuncionario(){
        funcionarios.forEach((k,v) -> {
            if(v instanceof FuncionarioClt) {
                System.out.println(v.getNome() + "\nSalário Bruto: " + String.format("%.2f",((FuncionarioClt) v).getSalarioBruto())
                                               + "\nSalário Semanal: " + String.format("%.2f", v.salarioSemanal()));
                return;
            }
            System.out.println(v.getNome() + "\nSalário Semanal: " + v.salarioSemanal());
            if(v instanceof FuncionarioDiarista)
                ((FuncionarioDiarista) v).setDiasTrabalhados(0);
        });
    }

    public void gerarEscala(Date inicio, Map<Date, String> feriados){
        DateFormat formatter = new SimpleDateFormat("EEEE", Locale.forLanguageTag("pt"));
        Calendar c = Calendar.getInstance();

        funcionarios.forEach((k,v) -> {

            c.setTime(inicio);
            boolean clt = v instanceof FuncionarioClt;
            boolean pj = v instanceof FuncionarioPj;
            boolean diarista = v instanceof FuncionarioDiarista;
            boolean feriado;
            boolean facultativo = false;
            String situacao;
            c.add(Calendar.DATE, -1);

            System.out.println(v);

            for(int i = 0; i < 7; i++){
                c.add(Calendar.DATE, 1);
                String dia = formatter.format(c.getTime()).toUpperCase();
                boolean diaAniversario = c.getTime().compareTo(v.getDataNascimento()) == 0;
                feriado = feriados.containsKey(c.getTime());
                if(feriado) facultativo = feriados.get(c.getTime()).equals("Facultativo");

                switch (dia){
                    case "DOMINGO":
                    case "SÁBADO":
                        if(clt || pj || diaAniversario) {
                            situacao = "FOLGA";
                            System.out.println(dia + ": " + situacao);
                            if(diarista)
                                ((FuncionarioDiarista) v).diaria();
                            break;
                        }
                        situacao = "TRABALHA";
                        System.out.println(dia + ": " + situacao);
                        ((FuncionarioDiarista) v).diaria();
                        break;
                    case "SEGUNDA-FEIRA":
                    case "TERÇA-FEIRA":
                    case "QUINTA-FEIRA":
                        if(diaAniversario || feriado && !facultativo && !diarista || diarista && !feriado){
                            situacao = "FOLGA";
                            System.out.println(dia + ": " + situacao);
                        }
                        if (clt || pj || feriado && diarista) {
                            situacao = "TRABALHA";
                            System.out.println(dia + ": " + situacao);
                        }
                            break;
                    case "QUARTA-FEIRA":
                        if(clt || diaAniversario || feriado && !facultativo){
                            situacao = "FOLGA";
                            System.out.println(dia + ": " + situacao);
                            if(diarista)
                                ((FuncionarioDiarista) v).diaria();
                            break;
                        }
                        situacao = "TRABALHA";
                        System.out.println(dia + ": " + situacao);
                        if(diarista)
                            ((FuncionarioDiarista) v).diaria();
                        break;
                    case "SEXTA-FEIRA":
                        if(pj || diaAniversario || feriado && !facultativo){
                            situacao = "FOLGA";
                            System.out.println(dia + ": " + situacao);
                            if(diarista)
                                ((FuncionarioDiarista) v).diaria();
                            break;
                        }
                        situacao = "TRABALHA";
                        System.out.println(dia + ": " + situacao);
                        if(diarista)
                            ((FuncionarioDiarista) v).diaria();
                        break;
                }
            }
            System.out.println();
        });
    }

    /*public void imprimirEscala(Date inicio){}

    public List<?> escalaTipo(Class<?> func){

    }

    public void relatorioTotal(char ord){

    }*/
}
