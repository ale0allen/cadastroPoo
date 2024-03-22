package br.com.pessoa;

import java.io.IOException;
import java.util.Scanner;

import br.com.pessoa.model.PessoaFisica;
import br.com.pessoa.model.PessoaFisicaRepo;
import br.com.pessoa.model.PessoaJuridica;
import br.com.pessoa.model.PessoaJuridicaRepo;

public class MainCadastro {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PessoaFisicaRepo repoFisica = new PessoaFisicaRepo();
        PessoaJuridicaRepo repoJuridica = new PessoaJuridicaRepo();

        int opcao;

        do {
            System.out.println("Menu de Opções:");
            System.out.println("1 - Incluir");
            System.out.println("2 - Alterar");
            System.out.println("3 - Excluir");
            System.out.println("4 - Exibir pelo id");
            System.out.println("5 - Exibir todos");
            System.out.println("6 - Salvar dados");
            System.out.println("7 - Recuperar dados");
            System.out.println("0 - Finalizar");

            System.out.print("Escolha a opção desejada: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    incluir(scanner, repoFisica, repoJuridica);
                    break;
                case 2:
                    alterar(scanner, repoFisica, repoJuridica);
                    break;
                case 3:
                    excluir(scanner, repoFisica, repoJuridica);
                    break;
                case 4:
                    obter(scanner, repoFisica, repoJuridica);
                    break;
                case 5:
                    obterTodos(scanner, repoFisica, repoJuridica);
                    break;
                case 6:
                    salvar(scanner, repoFisica, repoJuridica);
                    break;
                case 7:
                    recuperar(scanner, repoFisica, repoJuridica);
                    break;
                case 0:
                    System.out.println("Finalizando execução do sistema.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        } while (opcao != 0);

        scanner.close();
    }

    private static void incluir(Scanner scanner, PessoaFisicaRepo repoFisica, PessoaJuridicaRepo repoJuridica) {
        System.out.println("Escolha o tipo (F - Pessoa Física, J - Pessoa Jurídica): ");
        char tipo = scanner.next().charAt(0);

        if (tipo == 'F' || tipo == 'f') {
            PessoaFisica pessoaFisica = lerDadosPessoaFisica(scanner);
            repoFisica.inserir(pessoaFisica);
            System.out.println("Pessoa Física incluída com sucesso.");
        } else if (tipo == 'J' || tipo == 'j') {
            PessoaJuridica pessoaJuridica = lerDadosPessoaJuridica(scanner);
            repoJuridica.inserir(pessoaJuridica);
            System.out.println("Pessoa Jurídica incluída com sucesso.");
        } else {
            System.out.println("Opção inválida. Tente novamente.");
        }
    }

    private static void alterar(Scanner scanner, PessoaFisicaRepo repoFisica, PessoaJuridicaRepo repoJuridica) {
        System.out.println("Escolha o tipo (1 - Pessoa Física, 2 - Pessoa Jurídica): ");
        int tipo = scanner.nextInt();

        if (tipo == 1) {
            System.out.print("Digite o ID da Pessoa Física que deseja alterar: ");
            int id = scanner.nextInt();
            PessoaFisica pessoaFisicaAtual = repoFisica.obter(id);

            if (pessoaFisicaAtual != null) {
                pessoaFisicaAtual.exibir();
                PessoaFisica novaPessoaFisica = lerDadosPessoaFisica(scanner);
                repoFisica.alterar(novaPessoaFisica);
                System.out.println("Pessoa Física alterada com sucesso.");
            } else {
                System.out.println("Pessoa Física não encontrada.");
            }
        } else if (tipo == 2) {
            System.out.print("Digite o ID da Pessoa Jurídica que deseja alterar: ");
            int id = scanner.nextInt();
            PessoaJuridica pessoaJuridicaAtual = repoJuridica.obter(id);

            if (pessoaJuridicaAtual != null) {
                pessoaJuridicaAtual.exibir();
                PessoaJuridica novaPessoaJuridica = lerDadosPessoaJuridica(scanner);
                repoJuridica.alterar(novaPessoaJuridica);
                System.out.println("Pessoa Jurídica alterada com sucesso.");
            } else {
                System.out.println("Pessoa Jurídica não encontrada.");
            }
        } else {
            System.out.println("Opção inválida. Tente novamente.");
        }
    }

    private static void excluir(Scanner scanner, PessoaFisicaRepo repoFisica, PessoaJuridicaRepo repoJuridica) {
        System.out.println("Escolha o tipo (1 - Pessoa Física, 2 - Pessoa Jurídica): ");
        int tipo = scanner.nextInt();

        if (tipo == 1) {
            System.out.print("Digite o ID da Pessoa Física que deseja excluir: ");
            int id = scanner.nextInt();
            repoFisica.excluir(id);
            System.out.println("Pessoa Física excluída com sucesso.");
        } else if (tipo == 2) {
            System.out.print("Digite o ID da Pessoa Jurídica que deseja excluir: ");
            int id = scanner.nextInt();
            repoJuridica.excluir(id);
            System.out.println("Pessoa Jurídica excluída com sucesso.");
        } else {
            System.out.println("Opção inválida. Tente novamente.");
        }
    }

    private static void obter(Scanner scanner, PessoaFisicaRepo repoFisica, PessoaJuridicaRepo repoJuridica) {
        System.out.println("Escolha o tipo (1 - Pessoa Física, 2 - Pessoa Jurídica): ");
        int tipo = scanner.nextInt();

        System.out.print("Digite o ID da entidade que deseja obter: ");
        int id = scanner.nextInt();

        if (tipo == 1) {
            PessoaFisica pessoaFisica = repoFisica.obter(id);

            if (pessoaFisica != null) {
                pessoaFisica.exibir();
            } else {
                System.out.println("Pessoa Física não encontrada.");
            }
        } else if (tipo == 2) {
            PessoaJuridica pessoaJuridica = repoJuridica.obter(id);

            if (pessoaJuridica != null) {
                pessoaJuridica.exibir();
            } else {
                System.out.println("Pessoa Jurídica não encontrada.");
            }
        } else {
            System.out.println("Opção inválida. Tente novamente.");
        }
    }

    private static void obterTodos(Scanner scanner, PessoaFisicaRepo repoFisica, PessoaJuridicaRepo repoJuridica) {
        System.out.println("Escolha o tipo (1 - Pessoa Física, 2 - Pessoa Jurídica): ");
        int tipo = scanner.nextInt();

        if (tipo == 1) {
            repoFisica.obterTodos().forEach(PessoaFisica::exibir);
        } else if (tipo == 2) {
            repoJuridica.obterTodos().forEach(PessoaJuridica::exibir);
        } else {
            System.out.println("Opção inválida. Tente novamente.");
        }
    }

    private static void salvar(Scanner scanner, PessoaFisicaRepo repoFisica, PessoaJuridicaRepo repoJuridica) {
        try {
            System.out.print("Digite o prefixo dos arquivos: ");
            String prefixo = scanner.next();

            repoFisica.persistir(prefixo + ".fisica.bin");
            repoJuridica.persistir(prefixo + ".juridica.bin");

            System.out.println("Dados salvos com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados: " + e.getMessage());
        }
    }

    private static void recuperar(Scanner scanner, PessoaFisicaRepo repoFisica, PessoaJuridicaRepo repoJuridica) {
        try {
            System.out.print("Digite o prefixo dos arquivos: ");
            String prefixo = scanner.next();

            repoFisica.recuperar(prefixo + ".fisica.bin");
            repoJuridica.recuperar(prefixo + ".juridica.bin");

            System.out.println("Dados recuperados com sucesso.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao recuperar os dados: " + e.getMessage());
        }
    }

    private static PessoaFisica lerDadosPessoaFisica(Scanner scanner) {
        System.out.print("Digite o ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Digite o Nome: ");
        String nome = scanner.next();
        scanner.nextLine();

        System.out.print("Digite o CPF: ");
        String cpf = scanner.next();
        scanner.nextLine();

        System.out.print("Digite a Idade: ");
        int idade = scanner.nextInt();
        scanner.nextLine();

        return new PessoaFisica(id, nome, cpf, idade);
    }

    private static PessoaJuridica lerDadosPessoaJuridica(Scanner scanner) {
        System.out.print("Digite o ID: ");
        int id = scanner.nextInt();

        System.out.print("Digite o Nome: ");
        String nome = scanner.next();
        scanner.nextLine();
        
        System.out.print("Digite o CNPJ: ");
        String cnpj = scanner.next();
        scanner.nextLine();

        return new PessoaJuridica(id, nome, cnpj);
    }
}
