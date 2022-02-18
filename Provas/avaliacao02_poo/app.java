import Classes.Produto;
import Classes.Venda;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.DoubleSummaryStatistics;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.stream.Collectors;

public class app {

  public static void main(String[] args)
    throws InterruptedException, IOException, ParseException {
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

        while (true) {
          opcaoEscolhida = -1;
          inputTest = true;
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
              break;
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
                String inputString = sc.nextLine();
                Boolean codigoValido = true;

                temApenasDigitos = inputString.matches("[+-]?\\d*");
                if (temApenasDigitos && inputString.length() > 0) {
                  c = Integer.parseInt(inputString);
                  
                  for(Produto p : ProdutosListados){
                    if(p.getCodigo() == c) {
                      codigoValido = false;
                      break;
                    }
                  }
                  if(codigoValido) {
                    break;
                  } else {
                    System.out.println("\nERRO: Este código já foi usado para outro produto\n");
                  }

                }
              } while (true);

              do {
                System.out.print("Valor: ");
                String inputString = sc.nextLine();

                temApenasDigitos = inputString.matches("[+-]?\\d*(\\.\\d+)?");
                if (temApenasDigitos && inputString.length() > 0) {
                  v = Double.parseDouble(inputString);
                  break;
                }
              } while (true);

              do {
                System.out.print("Quantidade: ");
                String inputString = sc.nextLine();

                temApenasDigitos = inputString.matches("[+-]?\\d*");
                if (temApenasDigitos && inputString.length() > 0) {
                  qtd = Integer.parseInt(inputString);
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
                System.out.println("\nERRO: Não há produtos cadastrados!!");
                voltarMenu(sc, true);
              } else {
                System.out.println("Nome do produto que deseja buscar?");
                String nomeBuscado = sc.nextLine();
                
                System.out.println("-----------PRODUTO ENCONTRADO-----------");
                long contador = ProdutosListados.stream().filter(p->{
                    if(p.getNome().equalsIgnoreCase(nomeBuscado)&& p instanceof Produto){
                      System.out.println(p);
                      return true;
                    } else {
                      return false;
                    }
                  }
                ).collect(Collectors.counting());

                if(contador==0) {
                  System.out.println("\nProduto não encontrado!!");
                 }
                 voltarMenu(sc, true);
              }
            } else if (opcaoEscolhida == 3) {
              // listar todos
              System.out.println("------------PRODUTOS LISTADOS-----------");

              if (ProdutosListados.size() < 1) {
                System.out.println("\nERRO: Não há produtos cadastrados!!");
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
        }
      }
      if (opcaoEscolhida == 2) {
        // menu vendas

        while (true) {
          opcaoEscolhida = -1;
          inputTest = true;
          voltarMenu(sc, false);
          System.out.println("-------------!MENU  VENDAS!-------------");
          System.out.println("1 - Realizar venda");
          System.out.println("2 - Vendas por período - detalhado");
          System.out.println("3 - Listar todas as vendas");
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
              break;
            } else if (opcaoEscolhida == 1) {
              // realizar venda
              System.out.println("--------------REALIZAR VENDA------------");
              int codigoProcurado;
              String inputString;
              achou = false;
              temApenasDigitos = false;

              if (ProdutosListados.size() < 1) {
                System.out.println("\nERRO: Não há produtos cadastrados!!");
                voltarMenu(sc, true);
              } else {
                // input do codigo a ser buscado
                do {
                  System.out.print("Código do item que foi vendido: ");
                  inputString = sc.nextLine();

                  temApenasDigitos = inputString.matches("[+-]?\\d*");
                  if (temApenasDigitos && inputString.length() > 0) {
                    codigoProcurado = Integer.parseInt(inputString);
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
                      "ERRO: Não é possivel cadastrar uma venda para um produto sem estoque"
                    );
                    voltarMenu(sc, true);
                  } else {
                    do {
                      System.out.println(
                        "Deseja registrar uma venda para este produto? (s ou n)"
                      );
                      inputString = sc.nextLine();

                      if (inputString.equalsIgnoreCase("s")) {
                        inputString = inputString.toLowerCase();
                        break;
                      }
                      if (inputString.equalsIgnoreCase("n")) {
                        inputString = inputString.toLowerCase();
                        break;
                      }
                    } while (true);

                    if (inputString.equals("n")) {
                      voltarMenu(sc, false);
                    } else if (inputString.equals("s")) {
                      // variaveis para cadastrar vendas
                      int qtdVendida = 0;
                      Date data;
                      do {
                        System.out.println(
                          "Quantos itens deste produto foram vendidos? (" +
                          ProdutosListados.get(indice).getEstoque() +
                          " no estoque)"
                        );
                        inputString = sc.nextLine();

                        temApenasDigitos = inputString.matches("[+-]?\\d*");
                        if (temApenasDigitos && inputString.length() > 0) {
                          qtdVendida = Integer.parseInt(inputString);
                          if (
                            qtdVendida <=
                            ProdutosListados.get(indice).getEstoque()
                          ) {
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
                        System.out.println(
                          "Qual a data desta venda? (dd/mm/aaaa)"
                        );
                        inputString = sc.nextLine();

                        if (validarData(inputString)) {
                          data = converterStringPraDate(inputString);
                          break;
                        }

                        System.out.println("\nData invalida!!\n");
                      } while (true);

                      VendasListadas.add(
                        new Venda(
                          ProdutosListados.get(indice),
                          qtdVendida,
                          data
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
              System.out.println("-----------VENDAS NO PERIODO------------");
              String inputString;
              Date dataInicial, dataFinal;

              if (VendasListadas.size() < 1) {
                System.out.println(
                  "\nERRO: Não há vendas registradas para mostrar"
                );
                voltarMenu(sc, true);
              } else {
                System.out.println("\nData inicial:");

                do {
                  System.out.println(
                    "Qual a data inicial do periodo? (dd/mm/aaaa)"
                  );
                  inputString = sc.nextLine();

                  if (validarData(inputString)) {
                    dataInicial = converterStringPraDate(inputString);
                    break;
                  }

                  System.out.println("\nData invalida!!\n");
                } while (true);

                System.out.println("\nData final:");

                do {
                  System.out.println(
                    "Qual a data final do periodo? (dd/mm/aaaa)"
                  );
                  inputString = sc.nextLine();

                  if (validarData(inputString)) {
                    dataFinal = converterStringPraDate(inputString);
                    break;
                  }

                  System.out.println("\nData invalida!!\n");
                } while (true);

                if (dataFinal.compareTo(dataInicial) < 0) {
                  System.out.println(
                    "\nERRO: A data final é menor que a data inicial"
                  );
                  voltarMenu(sc, true);
                } else {
                  System.out.println(
                    "-----------VENDAS NO PERIODO------------"
                  );

                  // um loop mt loco em stream q imprime e calcula tudo, ou não faz nada se não achar a condição do filtro
                  DoubleSummaryStatistics InformacoesPeriodo = VendasListadas
                    .stream()
                    .filter( p -> {
                        if (
                          (dataFinal.compareTo(dataInicial) >= 0) &&
                          p instanceof Venda &&
                          p.comparaDataEntre(dataInicial, dataFinal)
                        ) {
                          System.out.println(
                            "--------------------------------------------------------------------------------------------------------------"
                          );
                          System.out.println(p);
                          return true;
                        } else {
                          return false;
                        }
                      }
                    )
                    .collect(
                      Collectors.summarizingDouble(Venda::getTotalVendido)
                    );

                  System.out.println(
                    "--------------------------------------------------------------------------------------------------------------"
                  );
                  if (InformacoesPeriodo.getCount() == 0) {
                    System.out.println(
                      "\nERRO: Não há vendas registradas nesse periodo\n"
                    );
                  } else {
                    // usar stream é mt daora
                    System.out.printf(
                      "Vendas: %s Media: %s Menor Venda($): %s Maior venda($): %s Total($): %s\n",
                      InformacoesPeriodo.getCount(),
                      InformacoesPeriodo.getAverage(),
                      InformacoesPeriodo.getMin(),
                      InformacoesPeriodo.getMax(),
                      InformacoesPeriodo.getSum()
                    );
                  }
                  voltarMenu(sc, true);
                }
              }
            } else if (opcaoEscolhida == 3) {
              System.out.println("------------TODAS AS VENDAS-------------");
      
              if(VendasListadas.size() < 1) {
                System.out.println("\nNão há vendas cadastradas");
              } else {
                VendasListadas.stream().forEach(System.out::println);  
              }
              voltarMenu(sc, true); 
            } else {
              voltarMenu(sc, false);
            }
          }
        }
      }  else {
        voltarMenu(sc, false);
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

  public static boolean validarData(String data) {
    boolean dataValida = data.matches("^(\\d{2})[/](\\d{2})[/](\\d{4})$");

    if (dataValida && data.length() > 0) {
      String[] arrayData = data.split("/");

      int dia = Integer.parseInt(arrayData[0]);
      int mes = Integer.parseInt(arrayData[1]);
      int ano = Integer.parseInt(arrayData[2]);

      if (
        (dia > 0 && dia <= 31) &&
        (mes > 0 && mes <= 12) &&
        (ano > 1990 && ano <= 2022)
      ) {
        return true;
      } else {
        return false;
      }
    } else {
      return false;
    }
  }

  public static Date converterStringPraDate(String data) throws ParseException {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    return sdf.parse(data);
  }
}
