import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Classe.Aeronave;
import Classe.Pessoa;
import Classe.Piloto;

public class AppPilotos {
    public static void main(String[] args) throws InterruptedException, IOException {
        final int MAX_ELEMENTOS = 20;
        int opcao, qtdCadastrados = 0, cont = 0 /* variavel de controle para os loops */;
        int EspacoPadrao = MAX_ELEMENTOS; // como o nome diz é o padrão até ser alterado
        int EspacoDisponivel = EspacoPadrao; // espaço disponivel no vetor atual
        String regex = "[0-9]+"; // regex para permitir apenas digitos

        Pessoa[] pilotos = new Pessoa[MAX_ELEMENTOS];
        Pattern pa = Pattern.compile(regex);
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
                // por causa dos loops tive que fazer desse jeito.
                String n; // nome
                String c; // cpf
                String b; // breve
                String m; // matricula
                String am; // modelo
                String ac; // categoria
                String res; // resposta "s" ou "n"
                int tamanho = 0;

                // loop 1: verifica se tem algo digitado, e verifica se o nome passar de 30
                // caracteres
                do {
                    System.out.println("\nNome(Max 30 caracteres)");
                    n = in.nextLine();
                    tamanho = n.length();
                    if (tamanho <= 0 || tamanho > 30) {
                        System.out.println("\nNome invalido!!");
                    }
                } while (tamanho <= 0 || tamanho > 30);
                // loop 2: verifica se só tem numeros e também se são 12 numeros
                do {
                    System.out.println("\nCpf apenas digitos(12)");
                    c = in.nextLine();
                    Matcher ma = pa.matcher(c);
                    if (ma.matches() && c.length() == 11) {
                        tamanho = c.length();
                    } else {
                        System.out.println("\nCpf invalido!!");
                    }
                } while (tamanho != 11);
                // loop 3: verifica se foi digitado algo
                do {
                    System.out.println("\nBrevê");
                    b = in.nextLine();
                    tamanho = b.length();
                    if (tamanho < 1) {
                        System.out.println("\nBrevê invalido!!");
                    }
                } while (tamanho <= 0);
                // loop 4: verifica se foi digitado algo
                do {
                    System.out.println("\nMatricula");
                    m = in.nextLine();
                    tamanho = m.length();
                    if (tamanho < 1) {
                        System.out.println("\nMatricula invalido!!");
                    }
                } while (tamanho <= 0);

                // desafio
                String qtdDeAvioesString;
                int qtdDeAvioes = 0;
                tamanho = 0;
                Boolean tudoCerto = false;
                // loop 5: verifica se foi digitado "s" ou "n", se "n" a varivael qtdDeAvioes
                // fica em 0, se "s"
                // ela ganha uma nova quantidade
                do {
                    System.out.println("\nquer cadastrar aviões?(s ou n)");
                    res = in.nextLine();
                    if (res.equals("s")) {
                        do {
                            System.out.println("\nQuantos aviões quer cadastrar para este piloto?(max: 50)");
                            qtdDeAvioesString = in.nextLine();
                            Matcher ma = pa.matcher(qtdDeAvioesString);

                            // isso aqui verifica 3 tipos de erros diferentes
                            if (qtdDeAvioesString.length() > 0) {
                                if (ma.matches()) {
                                    qtdDeAvioes = Integer.parseInt(qtdDeAvioesString);
                                    if (qtdDeAvioes < 51) {
                                        tudoCerto = true;
                                        tamanho = 1;
                                    } else {
                                        System.out.println("\nQuantidade invalida!!: O valor deve ser no maximo 50");
                                    }
                                } else {
                                    System.out.println("\nQuantidade invalida!!: O valor deve conter apenas digitos");
                                }
                            } else {
                                System.out.println("\nQuantidade invalida!!: Por favor digite um valor");
                            }
                        } while (!tudoCerto);
                    } else if (res.equals("n")) {
                        tamanho = 1;
                    } else {
                        System.out.println("\nOpção invalida!!(use 's' ou 'n')");

                    }
                } while (tamanho <= 0);
                // verifica se vai cadastrar aviões ou não verificando a quantidade da variavel
                // qtdDeAvioes,
                // se Nâo ele cadastra normalmente, se sim ele bota os
                // aviões dentro de piloto
                if (qtdDeAvioes > 0) {

                    Pessoa p1 = new Piloto(n, c, b, m);
                    // loop para cadastrar a quantidade passado no loop 5
                    while (cont != qtdDeAvioes) {
                        Aeronave a1 = new Aeronave();
                        System.out.println("\nInformações do avião: " + (cont + 1));
                        // cadastrando modelo em algum dos aviões
                        do {
                            System.out.println("\nModelo do avião");
                            am = in.nextLine();

                            a1.setModelo(am);
                            p1.getPiloto().setAviao(a1, cont);
                            tamanho = am.length();
                            if (tamanho < 1) {
                                System.out.println("\nModelo invalido!!");
                            }
                        } while (tamanho <= 0);
                        // cadastrando categoria em algum dos aviões
                        do {
                            System.out.println("\nCategoria do avião");
                            ac = in.nextLine();

                            a1.setCategoria(ac);
                            p1.getPiloto().setAviao(a1, cont);
                            tamanho = ac.length();
                            if (tamanho < 1) {
                                System.out.println("\nCategoria invalido!!");
                            }
                        } while (tamanho <= 0);
                        p1.getPiloto().setAumentarQtdAviao();
                        cont++;
                    }
                    // cadastrando pilotos com aviões
                    pilotos[qtdCadastrados] = p1;
                    cont = 0;
                } else {
                    // cadastrando pilotos normalmente sem os aviões
                    Pessoa p1 = new Piloto(n, c, b, m);
                    pilotos[qtdCadastrados] = p1;
                }
                qtdCadastrados++;
                EspacoDisponivel--;
                System.out.println("\nPiloto cadastrado com sucesso.");
                voltarMenu(in);
            }

            // Listar pilotos cadastrados
            if (opcao == 2) {
                int contAviao = 0;
                System.out.println("\nPilotos cadastrados atualmente: " + qtdCadastrados);

                // Se não tem ninguém cadastrado no vetor, caio fora
                if (qtdCadastrados == 0) {
                    System.out.println("\nNão há pilotos cadastrados para exibir.");
                    voltarMenu(in);
                    continue;
                }
                // loop para exibição das informações
                while (cont != qtdCadastrados) {

                    System.out.printf(
                            "\n\nPiloto %s:|| nome: %s cpf: %s breve: %s matricula: %s",
                            (cont + 1),
                            pilotos[cont].getNome(), pilotos[cont].getCpf(),
                            pilotos[cont].getPiloto().getBreve(), pilotos[cont].getPiloto().getMatricula());

                    // se tiver aviões ele vai exibir
                    if (pilotos[cont].getPiloto().checarSeTemAviao()) {
                        System.out.println("\nAviões do piloto:");
                        // loop para exibir totos os aviões do piloto atual que esta sendo trabalhado
                        while (contAviao != pilotos[cont].getPiloto().getQtdDeAvioesEspecifica()) {
                            System.out.printf("\nAvião %s|| modelo: %s categoria: %s", (contAviao + 1),
                                    pilotos[cont].getPiloto().getAviao(contAviao).getModelo(),
                                    pilotos[cont].getPiloto().getAviao(contAviao).getCategoria());
                            contAviao++;
                        }
                        contAviao = 0;
                    }
                    System.out.println("\n--------------------------------------------------------------------------");
                    cont++;
                }

                cont = 0;
                voltarMenu(in);
            }
            // Localizar piloto pelo CPF
            else if (opcao == 3) {
                String CpfProcurado;
                int PilotoProcurado = 0;
                int tamanho = 0;
                int contAviao = 0;
                // verifica se o cpf é valido para busca
                do {
                    System.out.println("\nQual cpf deseja encontrar?(12 digitos)");
                    CpfProcurado = in.nextLine();
                    Matcher ma = pa.matcher(CpfProcurado);
                    if (ma.matches() && CpfProcurado.length() == 11) {
                        tamanho = CpfProcurado.length();
                    } else {
                        System.out.println("\nCpf invalido!!");
                    }
                } while (tamanho != 11);

                while (cont != qtdCadastrados) {
                    if (pilotos[cont].getCpf().equals(CpfProcurado)) {
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
                    System.out.printf(
                            "\nEste cpf pertence ao piloto: %s|| nome: %s cpf: %s breve: %s matricula: %s",
                            (PilotoProcurado), pilotos[PilotoProcurado - 1].getNome(),
                            pilotos[PilotoProcurado - 1].getCpf(),
                            pilotos[PilotoProcurado - 1].getPiloto().getBreve(),
                            pilotos[PilotoProcurado - 1].getPiloto().getMatricula());

                    if (pilotos[PilotoProcurado - 1].getPiloto().checarSeTemAviao()) {
                        System.out.println("\nAviões do piloto:");
                        // loop para exibir totos os aviões do piloto atual que esta sendo trabalhado
                        while (contAviao != pilotos[PilotoProcurado - 1].getPiloto().getQtdDeAvioesEspecifica()) {
                            System.out.printf("\nAvião %s|| modelo: %s categoria: %s", (contAviao + 1),
                                    pilotos[PilotoProcurado - 1].getPiloto().getAviao(contAviao).getModelo(),
                                    pilotos[PilotoProcurado - 1].getPiloto().getAviao(contAviao).getCategoria());
                            contAviao++;
                        }
                        contAviao = 0;
                    }
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
                // esse if não deixa baixar o espaço do array atual
                if (NovoEspaço > EspacoPadrao) {

                    EspacoPadrao = NovoEspaço;
                    EspacoDisponivel = EspacoPadrao - qtdCadastrados;

                    Pessoa[] ArrayTemporario = new Pessoa[pilotos.length];
                    // passa os dados de pilotos para arrayTemporario
                    while (cont != qtdCadastrados) {
                        ArrayTemporario[cont] = pilotos[cont];
                        cont++;
                    }
                    cont = 0;

                    pilotos = new Pessoa[NovoEspaço];
                    // devolve os dados de arrayTemporario para pilotos
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
