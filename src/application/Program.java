package application;

import entities.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;


public class Program {

    public static void main(String[] args) throws ParseException, IOException, InterruptedException {
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        Funcionario f1 = new FuncionarioClt("05258539252", "Gustavo", sdf1.parse("17/06/1994"), "Rua longedemais");
        Funcionario f2 = new FuncionarioDiarista("05258539254","Danilo", sdf1.parse("08/02/1999"), "Rua longedemais");
        Funcionario f3 = new FuncionarioClt("05258539251", "Filipe", sdf1.parse("23/04/1990"), "Rua longedemais");
        Funcionario f4 = new FuncionarioPj("13258149000117","João", sdf1.parse("31/12/2000"), "Rua longedemais");
        Funcionario f5 = new FuncionarioPj("13258149000118","Vitória", sdf1.parse("29/7/2001"), "Rua longedemais");
        Rh rh = new Rh();
        rh.addFunc(f1);
        rh.addFunc(f2);
        rh.addFunc(f3);
        rh.addFunc(f4);
        rh.addFunc(f5);
        Calendario calendario = new Calendario("CAMPO_GRANDE", "MS", "2021");
        rh.gerarEscala(sdf1.parse("13/06/2021"), calendario.getFeriados());
        rh.pagarFuncionario();
    }
}
