import Classes.Produto;
import Classes.Venda;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class app {

  public static void main(String[] args) throws InterruptedException, IOException {
    ArrayList<Produto> ProdutosListados = new ArrayList<Produto>();
    ArrayList<Venda> VendasListadas = new ArrayList<Venda>();
    Scanner sc = new Scanner(System.in);
    
    boolean inputTest, temApenasDigitos, achou;
    int opcaoEscolhida;
    do {
      opcaoEscolhida = -1;
      inputTest = true;
      voltarMenu(sc, false);
      System.out.println("-----------------!MENU!-----------------");
      System.out.println("1 - Menu produto");
      System.out.println("2 - Menu venda");
      System.out.println("0 - Sair");
      System.out.print("Opção: ");
      try {
        opcaoEscolhida = sc.nextInt();
        sc.nextLine();
      } catch (InputMismatchException e) {
        voltarMenu(sc, true);
      }

      if (opcaoEscolhida == 0) {
        System.out.println("Programa finalizado!!");
        break;
      }
      if (opcaoEscolhida == 1) {
        // Menu produto
        voltarMenu(sc, false); // apaga a tela
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

        if (!inputTest) {
          System.out.println("\nOpção inválida!!");
          voltarMenu(sc, true);
        } else {         
          if (opcaoEscolhida == 0) {
            voltarMenu(sc, false);
          } else if (opcaoEscolhida == 1) {
            // incluir
            System.out.println("-----------CADASTRAR PRODUTO------------");
            int c, qtd;
            String n;
            double v;
            temApenasDigitos = false;

            do {
              System.out.print("Nome: ");
              n = sc.nextLine();
              if (n.length() > 1) {
                break;
              }
            } while (true);

            do {
              System.out.print("Código: ");
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

            //adicionar a arraylist
            ProdutosListados.add(new Produto(c, n, v, qtd));
            System.out.println("\nProduto cadastrado com sucesso!!");
            voltarMenu(sc, true);
          } else if (opcaoEscolhida == 2) {
            // buscar produto
            System.out.println("------------PRODUTO BUSCADO-------------");

            if (ProdutosListados.size() < 1) {
              System.out.println("\nERRO: não há produtos cadastrados!!");
              voltarMenu(sc, true);
            } else {
              achou = false;
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

              if (!achou) {
                System.out.println("\nERRO: Produto não encontrado!!");
                voltarMenu(sc, true);
              } else {
                System.out.println("\n" + produtoBuscado);
                voltarMenu(sc, true);
              }
            }
          } else if (opcaoEscolhida == 3) {
            // listar todos
            System.out.println("------------PRODUTOS LISTADOS-----------");

            if (ProdutosListados.size() < 1) {
              System.out.println("\nERRO: não há produtos cadastrados!!");
              voltarMenu(sc, true);
            } else {
              ProdutosListados.sort(null);
              ProdutosListados.forEach(p -> System.out.println(p));

              voltarMenu(sc, true);
            }
          } else {
            System.out.println("Opção invalida!!");
            voltarMenu(sc, true);
          }
        }
        opcaoEscolhida = -1;
      }
      if (opcaoEscolhida == 2) {
        // menu vendas
        voltarMenu(sc, false);
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

        if (!inputTest) {
          System.out.println("\nOpção invalida!!");
          voltarMenu(sc, true);
        } else {
          if (opcaoEscolhida == 0) {
            voltarMenu(sc, false);
          } else if (opcaoEscolhida == 1) {
            // realizar venda
            System.out.println("--------------REALIZAR VENDA------------");
            int codigoProcurado;
            String str;
            achou = false;
            temApenasDigitos = false;

            if (ProdutosListados.size() < 1) {
              System.out.println("\nERRO: Não há produtos cadastrados!!");
              voltarMenu(sc, true);
            } else {
              // input do codigo a ser buscado
              do {
                System.out.print("Código do item que foi vendido: ");
                str = sc.nextLine();

                temApenasDigitos = str.matches("[+-]?\\d*");
                if (temApenasDigitos && str.length() > 0) {
                  codigoProcurado = Integer.parseInt(str);
                  break;
                }
              } while (true);
              // procura pelo produto
              int indice = -1;
              for (Produto p : ProdutosListados) {
                if (p.getCodigo() == codigoProcurado) {
                  indice = ProdutosListados.indexOf(p);
                  achou = true;
                }
              }
              // tratar o produto encontrado
              if (achou) {
                System.out.println(
                  "\nProduto encontrado: " +
                  ProdutosListados.get(indice).getNome() +
                  "\n"
                );
                if (ProdutosListados.get(indice).getEstoque() < 1) {
                  System.out.println(
                    "ERRO: não é possivel cadastrar uma venda para um produto sem estoque"
                  );
                  voltarMenu(sc, true);
                } else {
                  
                  do {
                    System.out.println(
                      "Deseja registrar uma venda para este produto? (s ou n)"
                    );
                    str = sc.nextLine();

                    if (str.equalsIgnoreCase("s")) {
                      str = str.toLowerCase();
                      break;
                    }
                    if (str.equalsIgnoreCase("n")) {
                      str = str.toLowerCase();
                      break;
                    }
                  } while (true);

                  if (str.equals("n")) {
                    voltarMenu(sc, false);
                  } else if (str.equals("s")) {
                    // variaveis para cadastrar vendas
                    int qtdVendida = 0;
                    int dia = 0;
                    int mes = 0;
                    int ano = 0;
                    do {
                      System.out.println(
                        "Quantos itens deste produto foram vendidos? (" +
                        ProdutosListados.get(indice).getEstoque() +
                        " no estoque)"
                      );
                      str = sc.nextLine();

                      temApenasDigitos = str.matches("[+-]?\\d*");
                      if (temApenasDigitos && str.length() > 0) {
                        qtdVendida = Integer.parseInt(str);
                        if (qtdVendida<=ProdutosListados.get(indice).getEstoque()) {
                          break;
                        } else {
                          System.out.println(
                            "\nERRO: O número de produtos vendidos deve ser menor ou igual a quantidade disponível em estoque\n"
                          );
                        }
                      }
                    } while (true);

                    // tirando a quantidade que foi vendida
                    ProdutosListados.get(indice).retirarEstoque(qtdVendida);

                    System.out.println("\nData da venda:");
                    do {
                      System.out.print("Dia: ");
                      str = sc.nextLine();
                      temApenasDigitos = str.matches("[+-]?\\d*");

                      if (temApenasDigitos && str.length() > 0) {
                        dia = Integer.parseInt(str);
                        if (dia > 0 && dia < 32) {
                          break;
                        }
                      }
                    } while (true);

                    do {
                      System.out.print("Mês: ");
                      str = sc.nextLine();
                      temApenasDigitos = str.matches("[+-]?\\d*");

                      if (temApenasDigitos && str.length() > 0) {
                        mes = Integer.parseInt(str);
                        if (mes > 0 && mes < 13) {
                          break;
                        }
                      }
                    } while (true);

                    do {
                      System.out.print("Ano: ");
                      str = sc.nextLine();
                      temApenasDigitos = str.matches("[+-]?\\d*");

                      if (temApenasDigitos && str.length() > 0) {
                        ano = Integer.parseInt(str);
                        if (ano > 1899 && ano < 2201) {
                          break;
                        }
                      }
                    } while (true);
                    VendasListadas.add(
                      new Venda(
                        ProdutosListados.get(indice),
                        qtdVendida,
                        ano,
                        mes,
                        dia
                      )
                    );
                    voltarMenu(sc, false);
                  }
                }
              } else {
                System.out.println("\nProduto não encontrado!!");
                voltarMenu(sc, true);
              }
            }
          } else if (opcaoEscolhida == 2) {
            // periodo detalhado
            // variaveis para o input
            String str;
            int dia,mes,ano;
            int dataInicial,dataFinal;

            if (VendasListadas.size() < 1) {
              System.out.println(
                "\nERRO: não tem vendas registradas para mostrar"
              );
              voltarMenu(sc, true);
            } else {
              System.out.println("\nData inicial:");
              do {
                System.out.print("Dia: ");
                str = sc.nextLine();
                temApenasDigitos = str.matches("[+-]?\\d*");

                if (temApenasDigitos && str.length() > 0) {
                  dia = Integer.parseInt(str);
                  if (dia > 0 && dia < 32) {
                    break;
                  }
                }
              } while (true);

              do {
                System.out.print("Mês: ");
                str = sc.nextLine();
                temApenasDigitos = str.matches("[+-]?\\d*");

                if (temApenasDigitos && str.length() > 0) {
                  mes = Integer.parseInt(str);
                  if (mes > 0 && mes < 13) {
                    break;
                  }
                }
              } while (true);
              
              do {
                System.out.print("Ano: ");
                str = sc.nextLine();
                temApenasDigitos = str.matches("[+-]?\\d*");

                if (temApenasDigitos && str.length() > 0) {
                  ano = Integer.parseInt(str);
                  if (ano > 1899 && ano < 2201) {
                    break;
                  }
                }
              } while (true);

              str = ano + "" + mes + "" + dia;
              dataInicial = Integer.parseInt(str);

              System.out.println("\nData final:");
              do {
                System.out.print("Dia: ");
                str = sc.nextLine();
                temApenasDigitos = str.matches("[+-]?\\d*");

                if (temApenasDigitos && str.length() > 0) {
                  dia = Integer.parseInt(str);
                  if (dia > 0 && dia < 32) {
                    break;
                  }
                }
              } while (true);
              
              do {
                System.out.print("Mês: ");
                str = sc.nextLine();
                temApenasDigitos = str.matches("[+-]?\\d*");

                if (temApenasDigitos && str.length() > 0) {
                  mes = Integer.parseInt(str);
                  if (mes > 0 && mes < 13) {
                    break;
                  }
                }
              } while (true);
              
              do {
                System.out.print("Ano: ");
                str = sc.nextLine();
                temApenasDigitos = str.matches("[+-]?\\d*");

                if (temApenasDigitos && str.length() > 0) {
                  ano = Integer.parseInt(str);
                  if (ano > 1899 && ano < 2201) {
                    break;
                  }
                }
              } while (true);

              str = ano + "" + mes + "" + dia;
              dataFinal = Integer.parseInt(str);

              if (dataFinal < dataInicial) {
                System.out.println(
                  "\nERRO: a data final é menor que a data inicial"
                );
                voltarMenu(sc, true);
              } else {
                System.out.println("-----------VENDAS NO PERIODO------------");
                double totalPeriodo = 0;
                
                //loop pra exibição 
                for (Venda v : VendasListadas) {
                  if (dataInicial <= dataFinal) {
                    System.out.println(
                      "----------------------------------------"
                    );
                    totalPeriodo += v.getTotalVendido();
                    System.out.println(v);
                  }
                }
                System.out.println("----------------------------------------");
                System.out.println(
                  "Total em vendas no periodo: " + totalPeriodo
                );
                voltarMenu(sc, true);
              }
            }
          } else {
            voltarMenu(sc, false);
          }
        }
        opcaoEscolhida = -1;
      }
    } while (true);
  }

  private static void voltarMenu(Scanner sc, Boolean mensagem)
    throws InterruptedException, IOException {
    if (mensagem) {
      System.out.println("\nPressione ENTER para voltar ao menu.");
      sc.nextLine();
    }
    // Limpa toda a tela, deixando novamente apenas o menu

    if (System.getProperty("os.name").contains("Windows")) new ProcessBuilder(
      "cmd",
      "/c",
      "cls"
    )
      .inheritIO()
      .start()
      .waitFor(); else System.out.print("\033[H\033[2J");

    System.out.flush();
  }
}
