package application;

import entities.Calendario;
import entities.Funcionario;
import entities.FuncionarioClt;
import entities.Rh;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;


public class Program {

    public static void main(String[] args) throws ParseException, IOException, InterruptedException {
        System.out.println("Hello world");
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        Funcionario f = new FuncionarioClt("05258539252", "Gustavo", sdf1.parse("16/06/1994"), "Rua longedemais");
        Rh rh = new Rh();
        rh.addFunc(f);
        Calendario calendario = new Calendario("CAMPO_GRANDE", "MS", "2021");
        Map<Date, String> cal = calendario.getFeriados();
        rh.pagarFuncionario();
    }
}
