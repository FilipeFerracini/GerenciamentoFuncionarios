package application;

import entities.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class Program {


    public static void main(String[] args) throws ParseException, IOException, InterruptedException {

        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        Scanner sc = new Scanner(System.in);


        Rh rh = new Rh();

        Funcionario f1 = new FuncionarioClt("11111222222", "Gustavo", sdf1.parse("17/06/1994"), "Rua longedemais");
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

        loop:
        while (true) {
            int acao = 0;
            System.out.println();
            System.out.println("Digite um número de 1-5 para acessar uma opção do menu");
            System.out.println("(1) Adicionar novo funcionário CLT");
            System.out.println("(2) Adicionar novo funcionário PJ");
            System.out.println("(3) Adicionar novo funcionário diarista");
            System.out.println("(4) Imprimir escala");
            System.out.println("(5) Imprimir relatórios de pagamento em ordem crescente");
            System.out.println("(6) Imprimir relatórios de pagamento em ordem decrescente");
            System.out.println("(7) Pagar funcionários");
            System.out.println("(8) Informações sobre o funcionário");
            System.out.println("(9) Remover funcionário");
            System.out.println("(10) Finalizar Programa");
            System.out.print("Digite um número: ");

            if (sc.hasNextInt()) {
                acao = sc.nextInt();
                sc.nextLine();
            }
            else {
                System.err.print("Opção inválida.\n");
                sc.nextLine();
            }
            switch (acao){
                case(1):
                case(2):
                case(3):
                    rh.addFunc(ColetaDados(acao));
                    break;
                case(4):
                    escalas(rh);
                    break;
                case(5):
                    rh.relatorioTotal("crescente");
                    System.out.println("\nTecle ENTER para voltar ao menu principal");
                    sc.nextLine();
                    break;
                case(6):
                    rh.relatorioTotal("decrescente");
                    System.out.println("\nTecle ENTER para voltar ao menu principal");
                    sc.nextLine();
                    break;
                case(7):
                    rh.pagarFuncionarios();
                    System.out.println("\nTecle ENTER para voltar ao menu principal");
                    sc.nextLine();
                    break;
                case(8):
                    System.out.println("\nDigite o CPF ou CNPJ do funcionário: ");
                    String cpfCnpj = sc.nextLine().replaceAll("\\W", "");
                    rh.infoFunc(rh.buscaFunc(cpfCnpj));
                    System.out.println("\nTecle ENTER para voltar ao menu principal");
                    sc.nextLine();
                    break;
                case(9):
                    System.out.println("\nDigite o CPF ou CNPJ do funcionário: ");
                    cpfCnpj = sc.nextLine().replaceAll("\\W", "");
                    if (rh.removeFunc(rh.buscaFunc(cpfCnpj)) == true)
                        System.out.println("Funcionário removido");
                    else
                        System.err.println("Funcionário não encontrado");

                    System.out.println("\nTecle ENTER para voltar ao menu principal");
                    sc.nextLine();
                    break;
                case(10):
                    break loop;
            }

            System.out.println();
        }
    }

    public static Funcionario ColetaDados(int tipoFuncionario) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Scanner input = new Scanner(System.in);

        System.out.println("Digite os dados do novo funcionário:");
        System.out.print("CPF ou CNPJ: ");
        String cpfCnpj = input.nextLine().replaceAll("\\W", "");
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

    public static void escalas(Rh rh) throws ParseException, IOException, InterruptedException {
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        Calendar data = Calendar.getInstance();
        Calendario calendario = new Calendario("CAMPO_GRANDE", "MS", "2021");
        Scanner sc = new Scanner(System.in);
        int opcao;

        if (!rh.getEscalaGerada()) {
            System.out.println("A escala semanal ainda não foi gerada, deseja gerá-la?");
            System.out.println("Digite \"sim\" para gerar ou tecle ENTER para sair deste menu: ");

            if (sc.nextLine().equals("sim")) {
                System.out.print("Digite a data para a qual deseja saber a escala semanal (DD/MM/AAAA): ");
                Date dataEscala = sdf1.parse(sc.next());
                data.setTime(dataEscala);
                if(data.get(Calendar.DAY_OF_WEEK) > 1){
                    int diasAmais = data.get(Calendar.DAY_OF_WEEK) - 1;
                    data.add(Calendar.DATE, -diasAmais);
                    dataEscala = data.getTime();
                }
                sc.nextLine();

                rh.gerarEscala(dataEscala, calendario.getFeriados());
                System.out.println("\nAgora você terá mais opções de escalas para serem impressas!");
                System.out.println("Tecle ENTER para voltar ao menu principal");
                sc.nextLine();
            }
            return;

        }

        while (true) {
            System.out.println();
            System.out.println("Digite um número de 1-5 para acessar uma opção do menu");
            System.out.println("(1) Imprimir escala geral");
            System.out.println("(2) Imprimir escala de funcionários CLT");
            System.out.println("(3) Imprimir escala de funcionários PJ");
            System.out.println("(4) Imprimir escala de funcionários Diaristas");
            System.out.println("(5) Imprimir escala de um funcionário específico");
            System.out.println("(6) Sair");
            System.out.print("Digite um número: ");

            if (sc.hasNextInt()) {
                opcao = sc.nextInt();
                sc.nextLine();
                break;
            }
            else {
                System.err.print("Opção inválida.\n");
                System.out.println();
                sc.nextLine();
            }
        }

        switch(opcao){
            case 1:
                rh.imprimirEscala();
                System.out.println("\nTecle ENTER para voltar ao menu principal");
                sc.nextLine();
                break;
            case 2:
                rh.imprimirEscalaTipo("class entities.FuncionarioClt");
                System.out.println("\nTecle ENTER para voltar ao menu principal");
                sc.nextLine();
                break;
            case 3:
                rh.imprimirEscalaTipo("class entities.FuncionarioPj");
                System.out.println("\nTecle ENTER para voltar ao menu principal");
                sc.nextLine();
                break;
            case 4:
                rh.imprimirEscalaTipo("class entities.FuncionarioDiarista");
                System.out.println("\nTecle ENTER para voltar ao menu principal");
                sc.nextLine();
                break;
            case 5:
                System.out.println("Digite o CPF ou CNPJ do Funcionário: ");
                rh.imprimirEscalaFunc(sc.next());
                System.out.println("\nTecle ENTER para voltar ao menu principal");
                sc.nextLine();
                break;
        }

    }
}
