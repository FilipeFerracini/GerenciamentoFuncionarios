package application;

import entities.Funcionario;
import entities.FuncionarioClt;
import entities.Rh;

import java.text.ParseException;
import java.text.SimpleDateFormat;


public class Program {

    public static void main(String[] args) throws ParseException {
        System.out.println("Hello world");
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        Funcionario f = new FuncionarioClt("05258539252", "Gustavo", sdf1.parse("16/06/1994"), "Rua longedemais");
        Rh rh = new Rh();
        rh.addFunc(f);
    }
}
