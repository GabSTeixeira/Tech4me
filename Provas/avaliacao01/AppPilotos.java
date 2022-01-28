import java.io.IOException;
import java.util.Scanner;

import Classe.Piloto;

public class AppPilotos {
    public static void main(String[] args) throws InterruptedException, IOException {
        final int MAX_ELEMENTOS = 20;
        int opcao, qtdCadastrados = 0, cont = 0 /* variavel de controle para os loops */;
        boolean TransferenciaConcluida = false; // variavel para checagem se é o antigo ou o novo array que esta sendo
                                                // trabalhado
        int EspacoPadrao = MAX_ELEMENTOS; // como o nome diz é o padrão até ser alterado
        int EspacoDisponivel = EspacoPadrao; // espaço disponivel no vetor atual

        // no inicio era um array de pessoas, porem alterei para pilotos
        Piloto[] pilotos = new Piloto[MAX_ELEMENTOS];
        Piloto[] NovaListaPilotos = {}; // o novo array vazio para não dar o erro (variavel não iniciada)
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
                // vetor
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

                System.out.println("\nBreve(String)");
                String b = in.nextLine();

                System.out.println("\nMatricula(String)");
                String m = in.nextLine();

                if (!TransferenciaConcluida) {
                    Piloto p1 = new Piloto(n, c, b, m);
                    pilotos[qtdCadastrados] = p1;

                    qtdCadastrados++;
                    EspacoDisponivel--;
                    System.out.println("\nPiloto cadastrado com sucesso.");
                    voltarMenu(in);
                } else {
                    Piloto p1 = new Piloto(n, c, b, m);
                    NovaListaPilotos[qtdCadastrados] = p1;

                    qtdCadastrados++;
                    EspacoDisponivel--;
                    System.out.println("\nPiloto cadastrado com sucesso.");
                    voltarMenu(in);
                }
            } else

            // Listar pilotos cadastrados
            if (opcao == 2) {

                System.out.println("\nPilotos cadastrados atualmente: " + qtdCadastrados);

                // Se não tem ninguém cadastrado no vetor, caio fora
                if (qtdCadastrados == 0) {
                    System.out.println("\nNão há motoristas cadastrados para exibir.");
                    voltarMenu(in);
                    continue;
                }
                // se a transferencia tiver ocorrido ele faz a exibição com o novo array, senão
                // ele faz com o antigo
                if (TransferenciaConcluida) {
                    while (cont != qtdCadastrados) {
                        System.out.printf("\nPiloto %s:|| nome: %s|| cpf: %s|| breve: %s|| matricula: %s||", (cont + 1),
                                NovaListaPilotos[cont].getNome(), NovaListaPilotos[cont].getCpf(),
                                NovaListaPilotos[cont].getBreve(), NovaListaPilotos[cont].getMatricula());
                        cont++;
                    }
                    cont = 0;
                } else {
                    while (cont != qtdCadastrados) {
                        System.out.printf("\nPiloto %s:|| nome: %s|| cpf: %s|| breve: %s|| matricula: %s||", (cont + 1),
                                pilotos[cont].getNome(), pilotos[cont].getCpf(), pilotos[cont].getBreve(),
                                pilotos[cont].getMatricula());
                        cont++;
                    }
                    cont = 0;
                }
                voltarMenu(in);
            }

            // Localizar piloto pelo CPF
            else if (opcao == 3) {

                System.out.println("Qual cpf deseja encontrar?");
                int PilotoProcurado = 0;
                int CpfProcurado = in.nextInt();
                in.nextLine();

                if (TransferenciaConcluida) {
                    while (cont != qtdCadastrados) {
                        if (NovaListaPilotos[cont].getCpf() == CpfProcurado) {
                            PilotoProcurado = cont + 1;
                            // encerra o loop se achar o cpf
                            cont = qtdCadastrados;
                        } else {
                            cont++;
                        }
                    }
                    cont = 0;
                } else {
                    while (cont != qtdCadastrados) {
                        if (pilotos[cont].getCpf() == CpfProcurado) {
                            PilotoProcurado += cont + 1;
                            cont = qtdCadastrados;
                        } else {
                            cont++;
                        }
                    }
                    cont = 0;
                }
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

                    // verifica se ja foi feita uma transferencia e caso tenha sido feita
                    // ele pega as informações bota em um array temporario e dps devolve pro
                    // novo array "NovaListaPilotos" que esta com tamanho novo. Caso não tenha sido
                    // feita ele faz a transferencia de Pilotos[] para NovaListaPilotos[]
                    if (TransferenciaConcluida) {
                        Piloto[] ArrayTemporario = new Piloto[NovaListaPilotos.length];

                        while (cont != qtdCadastrados) {
                            ArrayTemporario[cont] = NovaListaPilotos[cont];
                            cont++;
                        }
                        cont = 0;
                        NovaListaPilotos = new Piloto[NovoEspaço];
                        while (cont != qtdCadastrados) {
                            NovaListaPilotos[cont] = ArrayTemporario[cont];
                            cont++;

                        }
                        cont = 0;

                    } else {
                        NovaListaPilotos = new Piloto[NovoEspaço];
                        while (cont != qtdCadastrados) {
                            // transfere os dados de um array para outro
                            NovaListaPilotos[cont] = pilotos[cont];
                            cont++;
                        }
                        // confirma a transferencia
                        TransferenciaConcluida = true;
                        cont = 0;

                    }
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
