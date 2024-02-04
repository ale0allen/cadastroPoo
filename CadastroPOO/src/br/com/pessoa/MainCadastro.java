package br.com.pessoa;

import java.io.IOException;

import br.com.pessoa.model.PessoaFisica;
import br.com.pessoa.model.PessoaFisicaRepo;
import br.com.pessoa.model.PessoaJuridica;
import br.com.pessoa.model.PessoaJuridicaRepo;

public class MainCadastro {
    public static void main(String[] args) {
        // Instanciar um repositório de pessoas físicas (repo1).
        PessoaFisicaRepo repo1 = new PessoaFisicaRepo();

        // Adicionar duas pessoas físicas, utilizando o construtor completo.
        PessoaFisica pessoaFisica1 = new PessoaFisica(1, "Ana", "11111111111", 25);
        PessoaFisica pessoaFisica2 = new PessoaFisica(2, "Carlos", "22222222222", 52);

        repo1.inserir(pessoaFisica1);
        repo1.inserir(pessoaFisica2);

        try {
            // Invocar o método de persistência em repo1, fornecendo um nome de arquivo fixo, através do código.
            repo1.persistir("pessoasFisicas.dat");

            // Instanciar outro repositório de pessoas físicas (repo2).
            PessoaFisicaRepo repo2 = new PessoaFisicaRepo();

            // Invocar o método de recuperação em repo2, fornecendo o mesmo nome de arquivo utilizado anteriormente.
            repo2.recuperar("pessoasFisicas.dat");

            // Exibir os dados de todas as pessoas físicas recuperadas.
            System.out.println("Dados de Pessoa Fisica Recuperados:");
            repo2.obterTodos().forEach(PessoaFisica::exibir);

            // Instanciar um repositório de pessoas jurídicas (repo3).
            PessoaJuridicaRepo repo3 = new PessoaJuridicaRepo();

            // Adicionar duas pessoas jurídicas, utilizando o construtor completo.
            PessoaJuridica pessoaJuridica1 = new PessoaJuridica(3, "XPTO Sales", "33333333333333");
            PessoaJuridica pessoaJuridica2 = new PessoaJuridica(4, "XPTO Solutions", "44444444444444");

            repo3.inserir(pessoaJuridica1);
            repo3.inserir(pessoaJuridica2);

            // Invocar o método de persistência em repo3, fornecendo um nome de arquivo fixo, através do código.
            repo3.persistir("pessoasJuridicas.dat");

            // Instanciar outro repositório de pessoas jurídicas (repo4).
            PessoaJuridicaRepo repo4 = new PessoaJuridicaRepo();

            // Invocar o método de recuperação em repo4, fornecendo o mesmo nome de arquivo utilizado anteriormente.
            repo4.recuperar("pessoasJuridicas.dat");

            // Exibir os dados de todas as pessoas jurídicas recuperadas.
            System.out.println("\nDados de Pessoa Juridica Recuperados:");
            repo4.obterTodos().forEach(PessoaJuridica::exibir);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
