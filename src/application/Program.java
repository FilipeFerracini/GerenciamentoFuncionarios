package application;

import entities.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;


public class Program {


    public static void main(String[] args) throws ParseException, IOException, InterruptedException, ClassNotFoundException {

        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        Scanner sc = new Scanner(System.in);
        Calendario calendario = new Calendario("CAMPO_GRANDE", "MS", "2021");
        Rh rh = new Rh();

        Funcionario f1 = new FuncionarioClt("05258539252", "Gustavo", sdf1.parse("17/06/1994"), "Rua longedemais");
        Funcionario f2 = new FuncionarioDiarista("05258539254", "Danilo", sdf1.parse("08/02/1999"), "Rua longedemais");
        Funcionario f3 = new FuncionarioClt("05258539251", "Filipe", sdf1.parse("23/04/1990"), "Rua longedemais");
        Funcionario f4 = new FuncionarioPj("13258149000117", "João", sdf1.parse("31/12/2000"), "Rua longedemais");
        Funcionario f5 = new FuncionarioPj("13258149000118", "Vitória", sdf1.parse("29/7/2001"), "Rua longedemais");
        rh.addFunc(f1);
        rh.addFunc(f2);
        rh.addFunc(f3);
        rh.addFunc(f4);
        rh.addFunc(f5);

        System.out.println("Bem vindo ao Gerenciador de Funcionários!");
        System.out.println("Você possui 5 funcionários cadastrados em sua empresa");

        while (true) {
            int acao = 0;
            System.out.println();
            System.out.println("Digite um número de 1-5 para acessar uma opção do menu");
            System.out.println("(1) Adicionar novo funcionário CLT");
            System.out.println("(2) Adicionar novo funcionário PJ");
            System.out.println("(3) Adicionar novo funcionário diarista");
            System.out.println("(4) Imprimir relatórios de escala");
            System.out.println("(5) Imprimir relatórios pagamento");
            System.out.println("(6) Finalizar Programa");
            System.out.print("Digite um número: ");

            if (sc.hasNextInt())
                acao = sc.nextInt();
            else {
                System.out.print("Opção inválida. ");
                sc.nextLine();
            }
            switch (acao){
                case(1):
                case(2):
                case(3):
                    rh.addFunc(ColetaDados(acao));
                    break;
                case(4):
                    System.out.print("Digite a data de um domingo para a qual deseja saber a escala semanal (DD/MM/AAAA): ");
                    Date dataEscala = sdf1.parse(sc.next());
                    sc.nextLine();
                    rh.gerarEscala(dataEscala, calendario.getFeriados());
                    break;
                case(5):
                    break;
            }
            if (acao == 6) {
                break;
            }
        }

        rh.gerarEscala(sdf1.parse("13/06/2021"), calendario.getFeriados());
//        rh.imprimirEscala();
//        rh.imprimirEscalaTipo("class entities.FuncionarioClt"); // No programa do usuário isso tem que ser mantido para funcionar, alternado apenas o nome final da classe
//        rh.imprimirEscalaFunc("13258149000118");
//        rh.relatorioTotal('c');
//        rh.relatorioTotal('d');
//        rh.pagarFuncionario();
    }

    static Funcionario ColetaDados(int tipoFuncionario) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Scanner input = new Scanner(System.in);

        System.out.println("Digite os dados do novo funcionário:");
        System.out.print("CPF ou CNPJ: ");
        String cpfCnpj = input.nextLine();
        System.out.print("Nome: ");
        String nome = input.nextLine();
        System.out.print("Data de nascimento (DD/MM/AAAA): ");
        Date dataNascimento = sdf.parse(input.next());
        input.nextLine();
        System.out.print("Endereco: ");
        String endereco = input.nextLine();

        switch (tipoFuncionario){
            case(1):
                return new FuncionarioClt(cpfCnpj,nome,dataNascimento,endereco);
            case(2):
                return new FuncionarioPj(cpfCnpj,nome,dataNascimento,endereco);
            case(3):
                return new FuncionarioDiarista(cpfCnpj,nome,dataNascimento,endereco);
        }
        return null;
    }

}
