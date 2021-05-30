package entities;

import utils.ComparadorNome;
import utils.ComparadorSalario;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Rh {
    private Map<String, Funcionario> funcionarios = new HashMap<>();
    private Map<Date, String> cacheFeriados;
    private Date cacheInicioEscala;
    private boolean escalaGerada = false;

    public boolean getEscalaGerada() {
        return escalaGerada;
    }

    public boolean addFunc(Funcionario func){
        if(funcionarios.containsKey(func.getCpfCnpj()))
            return false;
        funcionarios.put(func.getCpfCnpj(), func);
        if (escalaGerada){
            gerarEscalaFuncionario(func);
        }
        return true;
    }

    public boolean removeFunc(Funcionario func){
        try {
            if(!funcionarios.containsKey(func.getCpfCnpj()))
                return false;
            funcionarios.remove(func.getCpfCnpj());
            return true;
        } catch (NullPointerException e) {
            return false;
        }
    }

    public Funcionario buscaFunc(String cpfCnpj){
        return funcionarios.get(cpfCnpj);
    }

    public void infoFunc(Funcionario func){
        if (func == null) {
            System.err.println("Funcionário não encontrado");
            return;
        }
        System.out.println(funcionarios.get(func.getCpfCnpj()));
    }

    public void pagarFuncionarios(){
        List<Funcionario> listFunc = ordenarFuncionarios();

        for (Funcionario func : listFunc) {
            if(func instanceof FuncionarioClt) {
                System.out.println(func.getNome() + "\nSalário Bruto: " + String.format("%.2f",((FuncionarioClt) func).getSalarioBruto())
                                               + "\nSalário Semanal: " + String.format("%.2f", func.salarioSemanal()));
                System.out.println();
                continue;
            }
            System.out.println(func.getNome() + "\nSalário Semanal: " + String.format("%.2f",func.salarioSemanal()));
            System.out.println();
            if(func instanceof FuncionarioDiarista)
                ((FuncionarioDiarista) func).setDiasTrabalhados(0);
        }

        escalaGerada = false;
        cacheFeriados.clear();
    }

    public void gerarEscala(Date inicio, Map<Date, String> feriados){
        cacheFeriados = new HashMap<>(feriados);
        cacheInicioEscala = inicio;
        List<Funcionario> listFunc = ordenarFuncionarios();

        System.out.println("DOM SEG TER QUA QUI SEX SÁB");

        for (Funcionario func : listFunc) {

            gerarEscalaFuncionario(func);

            for(int i = 0; i < 7; i++){
                if(i == 6) {
                    System.out.print(func.getSituacao()[i] + " - " + func.getNome() + "\n");
                    break;
                }
                System.out.print(func.getSituacao()[i] + " ");
            }
            System.out.println();
        }
        escalaGerada = true;
        System.out.println("F - Folga, T - Trabalha, A - Aniversário(Folga), R - Recesso(Feriado)\n");
    }

    public void gerarEscalaFuncionario(Funcionario func){
        DateFormat formatter = new SimpleDateFormat("E", Locale.forLanguageTag("pt"));
        Calendar c = Calendar.getInstance();

        c.setTime(cacheInicioEscala);
        boolean clt = func instanceof FuncionarioClt;
        boolean pj = func instanceof FuncionarioPj;
        boolean diarista = func instanceof FuncionarioDiarista;
        boolean feriado;
        boolean facultativo = false;
        String situacao;
        c.add(Calendar.DATE, -1);
        Calendar nascimento = Calendar.getInstance();
        nascimento.setTime(func.getDataNascimento());

        for(int i = 0; i < 7; i++){
            c.add(Calendar.DATE, 1);
            String dia = formatter.format(c.getTime()).toUpperCase();
            if(dia.length()>3)
                dia=dia.substring(0,3);
            boolean diaAniversario = c.get(Calendar.DAY_OF_MONTH) == nascimento.get(Calendar.DAY_OF_MONTH)
                    && c.get(Calendar.MONTH) == nascimento.get(Calendar.MONTH);
            feriado = cacheFeriados.containsKey(c.getTime());
            if(feriado) facultativo = cacheFeriados.get(c.getTime()).equals("Facultativo");

            switch (dia){
                case "DOM":
                case "SÁB":
                    if(clt || pj || diaAniversario) {
                        situacao = " F ";
                        if (feriado) situacao = " R ";
                        if (diaAniversario) situacao = " A ";
                        setEscala(func, dia, situacao, i);

                        if(diarista)
                            ((FuncionarioDiarista) func).diaria();
                        break;
                    }
                    situacao = " T ";
                    setEscala(func, dia, situacao, i);
                    ((FuncionarioDiarista) func).diaria();
                    break;
                case "SEG":
                case "TER":
                case "QUI":
                    if(diaAniversario || feriado && !facultativo && !diarista || diarista && !feriado){
                        situacao = " F ";
                        if (feriado) situacao = " R ";
                        if (diaAniversario) situacao = " A ";
                        setEscala(func, dia, situacao, i);
                        if (diarista && diaAniversario) ((FuncionarioDiarista) func).diaria();
                        break;
                    }
                    if (clt || pj || feriado && diarista) {
                        situacao = " T ";
                        setEscala(func, dia, situacao, i);
                        if(diarista)
                            ((FuncionarioDiarista) func).diaria();
                        break;
                    }
                case "QUA":
                    if(clt || diaAniversario || feriado && !facultativo){
                        situacao = " F ";
                        if (feriado) situacao = " R ";
                        if (diaAniversario) situacao = " A ";
                        setEscala(func, dia, situacao, i);
                        if(diarista)
                            ((FuncionarioDiarista) func).diaria();
                        break;
                    }
                    situacao = " T ";
                    setEscala(func, dia, situacao, i);
                    if(diarista)
                        ((FuncionarioDiarista) func).diaria();
                    break;
                case "SEX":
                    if(pj || diaAniversario || feriado && !facultativo){
                        situacao = " F ";
                        if (feriado) situacao = " R ";
                        if (diaAniversario) situacao = " A ";
                        setEscala(func, dia, situacao, i);
                        if(diarista)
                            ((FuncionarioDiarista) func).diaria();
                        break;
                    }
                    situacao = " T ";
                    setEscala(func, dia, situacao, i);
                    if(diarista)
                        ((FuncionarioDiarista) func).diaria();
                    break;
            }
        }
    }

    public void setEscala(Funcionario func, String dia, String situacao, int index){
        func.getDias()[index] = dia;
        func.getSituacao()[index] = situacao;
    }

    public void imprimirEscalaFunc(String cpf){
        Funcionario func = funcionarios.get(cpf);
        for(int i = 0; i < 7; i++){
            if(i == 6) {
                System.out.print(func.getDias()[i] + "\n");
                break;
            }

            System.out.print(func.getDias()[i] + " ");
        }
        for(int i = 0; i < 7; i++){
            if(i == 6) {
                System.out.print(func.getSituacao()[i] + " - " + func.getNome() + "\n");
                break;
            }
            System.out.print(func.getSituacao()[i] + " ");
        }
        System.out.println();
        System.out.println("F - Folga, T - Trabalha, A - Aniversário(Folga), R - Recesso(Feriado)\n");
    }

    public void imprimirEscala(){
        List<Funcionario> listFunc = ordenarFuncionarios();

        System.out.println("DOM SEG TER QUA QUI SEX SÁB");

        for (Funcionario func : listFunc) {
            for(int i = 0; i < 7; i++){
                if(i == 6) {
                    System.out.print(func.getSituacao()[i] + " - " + func.getNome() + "\n");
                    break;
                }
                System.out.print(func.getSituacao()[i] + " ");
            }
            System.out.println();
        }
        System.out.println("F - Folga, T - Trabalha, A - Aniversário(Folga), R - Recesso(Feriado)\n");
    }

    public void imprimirEscalaTipo(String className){
        List<Funcionario> listFunc = ordenarFuncionarios();

        System.out.println("DOM SEG TER QUA QUI SEX SÁB");

        for (Funcionario func: listFunc) {
            if (func.getClass().toString().equals(className)) {
                for(int i = 0; i < 7; i++){
                    if(i == 6) {
                        System.out.print(func.getSituacao()[i] + " - " + func.getNome() + "\n");
                        break;
                    }
                    System.out.print(func.getSituacao()[i] + " ");
                }
                System.out.println();
            }
        }
        System.out.println("F - Folga, T - Trabalha, A - Aniversário(Folga), R - Recesso(Feriado)\n");
    }


    public void relatorioTotal(String ordem){
        List<Funcionario> ordenado = new ArrayList<>(funcionarios.values());
        Collections.sort(ordenado, new ComparadorSalario());

        if (ordem == "decrescente"){
            Collections.reverse(ordenado);
        }

        double total = 0.0;
        for (Funcionario f : ordenado){
            System.out.println(f.getNome() + " - " + "Salário: " + String.format("%.2f", f.salarioSemanal()));
            total += f.salarioSemanal();
        }

        System.out.println("\n" + "TOTAL: " + String.format("%.2f", total) + "\n\n");
    }

    private List<Funcionario> ordenarFuncionarios(){
        List<Funcionario> ordenado = new ArrayList<>(funcionarios.values());
        Collections.sort(ordenado, new ComparadorNome());
        return ordenado;
    }
}
