package tests;

import entities.Funcionario;
import entities.FuncionarioClt;
import entities.Rh;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ProgramTest {

    @Test
    void addFunc() throws ParseException {
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        Funcionario f = new FuncionarioClt("05258539252", "Gustavo", sdf1.parse("16/06/1994"), "Rua longedemais");
        Rh rh = new Rh();
        assertTrue(rh.addFunc(f));
    }

    @Test
    void addNaoRealizado() throws ParseException {
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        Funcionario f = new FuncionarioClt("05258539252", "Gustavo", sdf1.parse("16/06/1994"), "Rua longedemais");
        Funcionario g = new FuncionarioClt("05258539252", "Gustavo", sdf1.parse("16/06/1994"), "Rua longedemais");
        Rh rh = new Rh();
        rh.addFunc(f);
        assertEquals(false,rh.addFunc(g));
    }

    @Test
    void removeFunc() throws ParseException {
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        Funcionario f = new FuncionarioClt("05258539252", "Gustavo", sdf1.parse("16/06/1994"), "Rua longedemais");
        Rh rh = new Rh();
        rh.addFunc(f);
        assertTrue(rh.removeFunc(f));
    }

    @Test
    void removeNaoRealizado() throws ParseException {
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        Funcionario f = new FuncionarioClt("05258539252", "Gustavo", sdf1.parse("16/06/1994"), "Rua longedemais");
        Funcionario g = new FuncionarioClt("05258539251", "Gustavo", sdf1.parse("16/06/1994"), "Rua longedemais");
        Rh rh = new Rh();
        rh.addFunc(f);
        assertEquals(false,rh.removeFunc(g));
    }

    @Test
    void buscaFunc() throws ParseException {
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        Funcionario f = new FuncionarioClt("05258539252", "Gustavo", sdf1.parse("16/06/1994"), "Rua longedemais");
        Rh rh = new Rh();
        rh.addFunc(f);
        assertEquals(f,rh.buscaFunc(f));
    }

    @Test
    void buscaFuncNaoEncontrada() throws ParseException {
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        Funcionario f = new FuncionarioClt("05258539252", "Gustavo", sdf1.parse("16/06/1994"), "Rua longedemais");
        Funcionario g = new FuncionarioClt("24234234234", "Fulano", sdf1.parse("16/06/1994"), "Rua longedemais");
        Rh rh = new Rh();
        rh.addFunc(f);
        assertEquals(null,rh.buscaFunc(g));
    }

}