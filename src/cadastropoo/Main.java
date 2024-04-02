package cadastropoo;

/**
 *
 * @author Smith
 */

import java.io.IOException;
import model.PessoaFisica;
import model.PessoaFisicaRepo;
import model.PessoaJuridica;
import model.PessoaJuridicaRepo;


public class Main {

    public static void main(String[] args) {
        PessoaFisicaRepo repo1 = new PessoaFisicaRepo();

        PessoaFisica pf1 = new PessoaFisica(1, "Jeferson Smith", "123.456.789-00", 18);
        PessoaFisica pf2 = new PessoaFisica(2, "Daniela Smith", "111.111.111-11", 18);

        repo1.inserir(pf1);
        repo1.inserir(pf2);

        String nomeArquivo = "pessoasFisicas.txt";
        try {
            repo1.persistir(nomeArquivo);
        } catch (IOException e) {
            System.err.println("Erro: " + e);
        }

        PessoaFisicaRepo repo2 = new PessoaFisicaRepo();

        try {
            repo2.recuperar(nomeArquivo);
            System.out.println("Dados de Pessoas Fisicas Armazenados.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro: " + e);
        }

        System.out.println("Dados de Pessoas Fisicas Recuperados.");
        for (PessoaFisica pf : repo2.obterTodos()) {
            pf.exibir();
        }

        PessoaJuridicaRepo repo3 = new PessoaJuridicaRepo();

        PessoaJuridica pj1 = new PessoaJuridica(3, "Empresa 1", "11.111.111/0001-01");
        PessoaJuridica pj2 = new PessoaJuridica(4, "Empresa 2", "11.111.111/0001-02");

        repo3.inserir(pj1);
        repo3.inserir(pj2);

        String nomeArquivoPJ = "pessoasJuridicas.txt";
        try {
            repo3.persistir(nomeArquivoPJ);
            System.out.println("\nDados de Pessoas Juridicas Armazenados.");
        } catch (IOException e) {
            System.err.println("Erro Encontrado: " + e);
        }

        PessoaJuridicaRepo repo4 = new PessoaJuridicaRepo();

        String nomeArquivoPJ2 = "pessoasJuridicas.txt"; 
        try {
            repo4.recuperar(nomeArquivoPJ2);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro Encontrado: " + e);
        }

        System.out.println("Dados de Pessoas Juridicas Recuperados.");
        for (PessoaJuridica pj : repo4.obterTodos()) {
            pj.exibir();
        }

    }
}