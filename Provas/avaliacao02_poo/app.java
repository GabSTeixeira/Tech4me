import java.io.IOException;
import java.util.InputMismatchException;
import java.util.ArrayList;
import java.util.Scanner;
import Classes.Produto;

public class app {
    public static void main(String[] args) throws InterruptedException, IOException {

        ArrayList<Produto> ProdutosListados = new ArrayList<Produto>();
        Scanner sc = new Scanner(System.in);
        int opcaoEscolhida = -1;
        boolean inputTest;
        do {
            inputTest = true;
            // voltarMenu(sc,false);
            System.out.println("-----------------!MENU!-----------------");
            System.out.println("1 - Menu produto");
            System.out.println("2 - Menu venda");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");
            try {
                opcaoEscolhida = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Opção Invalida!!");
                voltarMenu(sc, true);
            }
            if (opcaoEscolhida == 0) {
                System.out.println("Programa finalizado!!");
                break;
            }
            if (opcaoEscolhida == 1) {
                // Menu produto
                voltarMenu(sc, false);
                opcaoEscolhida = -1;
                System.out.println("-------------!MENU PRODUTO!-------------");
                System.out.println("1 - Incluir produto");
                System.out.println("2 - Consultar produto");
                System.out.println("3 - Listar produtos");
                System.out.println("0 - Voltar menu");
                System.out.print("opção: ");

                try {
                    opcaoEscolhida = sc.nextInt();
                } catch (InputMismatchException e) {
                    inputTest = false; 
                }
                sc.nextLine();
                
                if(!inputTest) {
                    System.out.println("\nOpção invalida!!");
                    voltarMenu(sc, true);
                } else {
                    if (opcaoEscolhida == 0) {
                        voltarMenu(sc, true);
                    } else if (opcaoEscolhida == 1) {
                        // incluir
                        int c;
                        String n;
                        double v;
                        int qtd;
                        boolean temApenasDigitos = false;

                        System.out.print("Nome: ");
                        n = sc.nextLine();

                        do {
                            System.out.print("Codigo: ");
                            String str = sc.nextLine();

                            temApenasDigitos = str.matches("[+-]?\\d*");
                            if (temApenasDigitos && str.length() > 0) {
                                c = Integer.parseInt(str);
                                break;
                            }
                        } while (true);

                        do {
                            System.out.print("Valor: ");
                            String str = sc.nextLine();

                            temApenasDigitos = str.matches("[+-]?\\d*(\\.\\d+)?");
                            if (temApenasDigitos && str.length() > 0) {
                                v = Double.parseDouble(str);
                                break;
                            }
                        } while (true);

                        do {
                            System.out.print("Quantidade: ");
                            String str = sc.nextLine();

                            temApenasDigitos = str.matches("[+-]?\\d*");
                            if (temApenasDigitos && str.length() > 0) {
                                qtd = Integer.parseInt(str);
                                break;
                            }
                        } while (true);

                        ProdutosListados.add(new Produto(c, n, v, qtd));
                        System.out.println("\nProduto cadastrado com sucesso!!");
                        voltarMenu(sc, true);
                    } else if (opcaoEscolhida == 2) {
                        // buscar

                        if (ProdutosListados.size() < 1) {
                            System.out.println("Não tem produtos cadastrados!!");
                            voltarMenu(sc, true);
                        }

                        boolean achou = false;
                        String produtoBuscado = "";

                        System.out.println("Nome do produto que deseja buscar?");
                        String nomeBuscado = sc.nextLine();
                        for (Produto p : ProdutosListados) {
                            if (p.getNome().equalsIgnoreCase(nomeBuscado)) {
                                produtoBuscado = p.toString();
                                achou = true;
                                break;
                            }
                        }
                        System.out.println("------------PRODUTO BUSCADO-------------");
                        if (!achou) {
                            System.out.println("\nProduto não encontrado!!");
                        } else {
                            System.out.println("\n" + produtoBuscado);
                        }

                        voltarMenu(sc, true);
                    } else if (opcaoEscolhida == 3) {
                        // listar todos
                        System.out.println("------------PRODUTOS LISTADOS-----------");

                        if (ProdutosListados.size() < 1) {
                            System.out.println("\nNão há produtos cadastrados!!");
                        }
                        ProdutosListados.sort(null);
                        ProdutosListados.forEach(p -> System.out.println(p));

                        voltarMenu(sc, true);
                    } else {
                        System.out.println("Opção invalida!!");
                        voltarMenu(sc, true);
                    }
                }
            }
            if (opcaoEscolhida == 2) {
                // menu vendas
                voltarMenu(sc, false);
                opcaoEscolhida = -1;
                System.out.println("-------------!MENU  VENDAS!-------------");
                System.out.println("1 - Realizar venda");
                System.out.println("2 - Vendas por período - detalhado");
                System.out.println("0 - Voltar menu");
                System.out.print("opção: ");

                try {
                    opcaoEscolhida = sc.nextInt();
                } catch (InputMismatchException e) {
                    inputTest = false;  
                }
                
                sc.nextLine();

                if(!inputTest) {
                    System.out.println("\nOpção invalida!!");
                    voltarMenu(sc, true);
                } else {
                    if (opcaoEscolhida == 0) {
                        voltarMenu(sc, false);
                    } else if (opcaoEscolhida == 1) {
                        // realizar venda
                    } else if (opcaoEscolhida == 2) {
                        // periodo detalhado
                    } else {
                        System.out.println("\nOpção invalida!!");
                        voltarMenu(sc, true);
                    }
                }
            }

        } while (true);
    }

    private static void voltarMenu(Scanner sc, Boolean mensagem) throws InterruptedException, IOException {
        if (mensagem) {
            System.out.println("\nPressione ENTER para voltar ao menu.");
            sc.nextLine();
        }
        // Limpa toda a tela, deixando novamente apenas o menu

        if (System.getProperty("os.name").contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
            System.out.print("\033[H\033[2J");

        System.out.flush();
    }
}
