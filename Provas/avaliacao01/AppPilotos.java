import java.io.IOException;
import java.util.Scanner;

import Classe.Pessoa;

public class AppPilotos {
    public static void main(String[] args) throws InterruptedException, IOException {
        final int MAX_ELEMENTOS = 2;
        int opcao, qtdCadastrados = 0, cont = 0 /* variavel de controle para os loops */;
        int EspacoPadrao = MAX_ELEMENTOS; // como o nome diz é o padrão até ser alterado
        int EspacoDisponivel = EspacoPadrao; // espaço disponivel no vetor atual

        Pessoa[] pilotos = new Pessoa[MAX_ELEMENTOS];

        Scanner in = new Scanner(System.in);
        do {
            System.out.println("\n****\nMENU\n****\n");
            System.out.println("1 - Cadastrar piloto");
            System.out.println("2 - Listar pilotos cadastrados");
            System.out.println("3 - Localizar piloto pelo CPF");
            System.out.println("4 - Aumentar espaço de armazenamento");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");

            opcao = in.nextInt();
            in.nextLine(); // Tira o ENTER que ficou na entrada na instrução anterior

            // Cadastrar piloto
            if (opcao == 1) {
                // Se não tem mais espaço no vetor, caio fora e também considera se é um novo
                // vetor com tamanho diferente
                if (EspacoDisponivel <= 0) {
                    System.out.println("\nNão há espaço para cadastrar novos pilotos.");
                    voltarMenu(in);
                    continue;
                }

                System.out.println("Nome(String)");
                String n = in.nextLine();

                System.out.println("\ncpf(int)");
                int c = in.nextInt();
                in.nextLine();

                Pessoa p1 = new Pessoa(n, c);
                pilotos[qtdCadastrados] = p1;

                qtdCadastrados++;
                EspacoDisponivel--;
                System.out.println("\nPiloto cadastrado com sucesso.");
                voltarMenu(in);

            }

            // Listar pilotos cadastrados
            if (opcao == 2) {

                System.out.println("\nPilotos cadastrados atualmente: " + qtdCadastrados);

                // Se não tem ninguém cadastrado no vetor, caio fora
                if (qtdCadastrados == 0) {
                    System.out.println("\nNão há pilotos cadastrados para exibir.");
                    voltarMenu(in);
                    continue;
                }
                while (cont != qtdCadastrados) {
                    System.out.printf("\nPiloto %s:|| nome: %s cpf: %s||", (cont + 1),
                            pilotos[cont].getNome(), pilotos[cont].getCpf());
                    cont++;
                }

                cont = 0;
                voltarMenu(in);
            }
            // Localizar piloto pelo CPF
            else if (opcao == 3) {

                System.out.println("Qual cpf deseja encontrar?");
                int PilotoProcurado = 0;
                int CpfProcurado = in.nextInt();
                in.nextLine();

                while (cont != qtdCadastrados) {
                    if (pilotos[cont].getCpf() == CpfProcurado) {
                        PilotoProcurado = cont + 1;
                        cont = qtdCadastrados;
                    } else {
                        cont++;
                    }
                }
                cont = 0;
                if (PilotoProcurado == 0) {
                    System.out.println("\nCpf não encontrado");
                    voltarMenu(in);
                } else {
                    System.out.println("\nEste cpf pertence ao piloto: " + PilotoProcurado);
                    voltarMenu(in);
                }

            }

            // Aumentar espaço de armazenamento
            else if (opcao == 4) {
                int NovoEspaço = 0;
                System.out.printf("\nQuantidade cadastrada: %s\nEspaço maximo: %s", qtdCadastrados, EspacoPadrao);
                System.out.println("\n--------------------------------------");
                System.out.println("Para quanto de espaço deseja aumentar?");
                NovoEspaço = in.nextInt();
                in.nextLine();

                if (NovoEspaço > EspacoPadrao) {

                    EspacoPadrao = NovoEspaço;
                    EspacoDisponivel = EspacoPadrao - qtdCadastrados;

                    Pessoa[] ArrayTemporario = new Pessoa[pilotos.length];

                    while (cont != qtdCadastrados) {
                        ArrayTemporario[cont] = pilotos[cont];
                        cont++;
                    }
                    cont = 0;

                    pilotos = new Pessoa[NovoEspaço];
                    while (cont != qtdCadastrados) {
                        pilotos[cont] = ArrayTemporario[cont];
                        cont++;
                    }
                    cont = 0;
                    System.out.println("\nEspaço modificado com sucesso.");
                    voltarMenu(in);
                } else {
                    System.out.println("\nNão é possivel baixar o espaço disponivel");
                    voltarMenu(in);
                }

            } else if (opcao != 0) {
                System.out.println("\nOpção inválida!");
            }
        }
        // Sair
        while (opcao != 0);

        System.out.println("Fim do programa!");

        in.close();
    }

    private static void voltarMenu(Scanner in) throws InterruptedException, IOException {
        System.out.println("\n\nPressione ENTER para voltar ao menu.");
        in.nextLine();

        // Limpa toda a tela, deixando novamente apenas o menu

        if (System.getProperty("os.name").contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
            System.out.print("\033[H\033[2J");

        System.out.flush();

    }
}
