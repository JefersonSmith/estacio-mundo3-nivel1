package cadastropoo;

/**
 *
 * @author Smith
 */
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static java.lang.Integer.parseInt;
import java.util.List;
import java.util.Scanner;
import model.PessoaFisica;
import model.PessoaFisicaRepo;
import model.PessoaJuridica;
import model.PessoaJuridicaRepo;

public class Main2 {

    private static Scanner sc = new Scanner(System.in);
    private static PessoaFisicaRepo repoFisica = new PessoaFisicaRepo();
    private static PessoaJuridicaRepo repoJuridica = new PessoaJuridicaRepo();

    public static void main(String[] args) {
        int opcao;
        do {
            mostrarMenu();
            opcao = sc.nextInt();
            sc.nextLine();
            mostrarEscolha(opcao);
        } while (opcao != 0);
    }

    private static void mostrarMenu() {
        System.out.println("=-=-= CADASTRO POO =-=-=");
        System.out.println("Selecione uma opcao:");
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.println("1 - Incluir Pessoa");
        System.out.println("2 - Alterar Pessoa");
        System.out.println("3 - Excluir Pessoa");
        System.out.println("4 - Buscar pelo ID");
        System.out.println("5 - Exibir Todos");
        System.out.println("6 - Persistir Dados");
        System.out.println("7 - Recuperar Dados");
        System.out.println("0 - Finalizar Programa");
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.print("Digite uma opcao: ");
    }

    private static void mostrarEscolha(int opcao) {
        switch (opcao) {
            case 1 ->
                incluirPessoa();
            case 2 ->
                alterarPessoa();
            case 3 ->
                excluirPessoa();
            case 4 ->
                exibirPorId();
            case 5 ->
                exibirTodos();
            case 6 ->
                salvarDados();
            case 7 ->
                recuperarDados();
            case 0 ->
                System.out.println("Encerrando...");
            default ->
                System.out.println("\n Opcao invalida! ");
        }
    }

    private static void incluirPessoa() {
        System.out.println("Incluir Pessoa (1 - Fisica, 2 - Juridica): ");
        int tipo = sc.nextInt();
        sc.nextLine();

        switch (tipo) {
            case 1 -> {
                PessoaFisica pf = new PessoaFisica();
                System.out.println("Digite o ID: ");
                pf.setId(parseInt(sc.nextLine()));
                System.out.println("Digite o nome: ");
                pf.setNome(sc.nextLine());
                System.out.println("Digite o CPF: ");
                pf.setCpf(sc.nextLine());
                System.out.println("Digite a idade: ");
                int idade = sc.nextInt();
                sc.nextLine();
                pf.setIdade(idade);
                repoFisica.inserir(pf);
                System.out.println("\n Pessoa Fisica adicionada com sucesso! ");
            }
            case 2 -> {
                PessoaJuridica pj = new PessoaJuridica();
                System.out.println("Digite o ID: ");
                pj.setId(parseInt(sc.nextLine()));
                System.out.println("Digite o nome:");
                pj.setNome(sc.nextLine());
                System.out.println("Digite o CNPJ:");
                pj.setCnpj(sc.nextLine());
                repoJuridica.inserir(pj);
                System.out.println("\n Pessoa Juridica adicionada com sucesso! ");
            }
            default -> System.out.println("\n Opcao invalida. ");
        }
    }

    private static void alterarPessoa() {
        System.out.println("Alterar Pessoa (1 - Fisica, 2 - Juridica): ");
        int tipo = sc.nextInt();
        sc.nextLine();

        System.out.println("Digite o ID da pessoa:");
        int id = sc.nextInt();
        sc.nextLine();

        switch (tipo) {
            case 1 -> {
                PessoaFisica pf = repoFisica.obter(id);
                if (pf != null) {
                    System.out.println("Dados atuais: ");
                    pf.exibir();
                    
                    System.out.println("Digite o nome:");
                    String nome = sc.nextLine();
                    if (!nome.isEmpty()) {
                        pf.setNome(nome);
                    }
                    
                    System.out.println("Digite o CPF:");
                    String cpf = sc.nextLine();
                    if (!cpf.isEmpty()) {
                        pf.setCpf(cpf);
                    }
                    
                    System.out.println("Digite a idade:");
                    int idade = sc.nextInt();
                    sc.nextLine();
                    if (idade != 0) {
                        pf.setIdade(idade);
                    }
                    
                    repoFisica.alterar(pf);
                    System.out.println("\n Pessoa Fisica atualizada com sucesso!");
                } else {
                    System.out.println("\n Pessoa Fisica não encontrada.");
                }
            }
            case 2 -> {
                PessoaJuridica pj = repoJuridica.obter(id);
                if (pj != null) {
                    System.out.println("Dados atuais: ");
                    pj.exibir();
                    
                    System.out.println("Digite o nome:");
                    String nome = sc.nextLine();
                    if (!nome.isEmpty()) {
                        pj.setNome(nome);
                    }
                    
                    System.out.println("Digite o CNPJ:");
                    String cnpj = sc.nextLine();
                    if (!cnpj.isEmpty()) {
                        pj.setCnpj(cnpj);
                    }
                    
                    repoJuridica.alterar(pj);
                    System.out.println("\n Pessoa Juridica atualizada com sucesso! ");
                } else {
                    System.out.println("\n Pessoa Juridica não encontrada. ");
                }
            }
            default -> System.out.println("\n Opcao invalida. ");
        }
    }

    private static void excluirPessoa() {
        System.out.println("Excluir Pessoa (1 - Fisica, 2 - Juridica): ");
        int tipo = sc.nextInt();
        sc.nextLine();

        System.out.println("Digite o ID da pessoa a ser excluida:");
        int id = sc.nextInt();
        sc.nextLine();

        switch (tipo) {
            case 1 -> {
                PessoaFisica pf = repoFisica.obter(id);
                if (pf != null) {
                    repoFisica.excluir(id);
                    System.out.println("\n Pessoa Fisica removida com sucesso! ");
                } else {
                    System.out.println("\n Pessoa Fisica não encontrada. ");
                }
            }
            case 2 -> {
                PessoaJuridica pj = repoJuridica.obter(id);
                if (pj != null) {
                    repoJuridica.excluir(id);
                    System.out.println("\n Pessoa Juridica removida com sucesso! ");
                } else {
                    System.out.println("\n Pessoa Juridica nao encontrada. ");
                }
            }
            default -> System.out.println("\n Opcao invalida. ");
        }
    }

    private static void exibirPorId() {
        System.out.println("Exibir dados de Pessoa (1 - Fisica, 2 - Juridica): ");
        int tipo = sc.nextInt();
        sc.nextLine();

        System.out.println("Digite o ID da pessoa:");
        int id = sc.nextInt();
        sc.nextLine();

        switch (tipo) {
            case 1 -> {
                PessoaFisica pf = repoFisica.obter(id);
                if (pf != null) {
                    System.out.println("Dados da Pessoa Fisica:");
                    pf.exibir();
                } else {
                    System.out.println("\n Pessoa Fisica nao encontrada. ");
                }
            }
            case 2 -> {
                PessoaJuridica pj = repoJuridica.obter(id);
                if (pj != null) {
                    System.out.println("Dados da Pessoa Juridica:");
                    pj.exibir();
                } else {
                    System.out.println("\n Pessoa Jurídica nao encontrada. ");
                }
            }
            default -> System.out.println("\n Opcao invalida. ");
        }
    }

    private static void exibirTodos() {
        System.out.println("Exibir todos (1 - Fisica, 2 - Juridica): ");
        int tipo = sc.nextInt();
        sc.nextLine();

        switch (tipo) {
            case 1 -> {
                System.out.println("Lista de Todas as Pessoas Fisicas:");
                for (PessoaFisica pf : repoFisica.obterTodos()) {
                    pf.exibir();
                    System.out.println("---------------------");
                }
            }
            case 2 -> {
                System.out.println("Lista de Todas as Pessoas Juridicas:");
                for (PessoaJuridica pj : repoJuridica.obterTodos()) {
                    pj.exibir();
                    System.out.println("---------------------");
                }
            }
            default -> System.out.println("\n Opcao invalida. ");
        }
    }

    private static void recuperarDados() {
        System.out.println("Digite o nome dos arquivos para recuperacao:");
        String nomeArquivo = sc.nextLine();

        // Recuperando dados de Pessoa Física
        try (ObjectInputStream oisPF = new ObjectInputStream(new FileInputStream(nomeArquivo + ".fisica.bin"))) {
            List<PessoaFisica> listaFisica = (List<PessoaFisica>) oisPF.readObject();
            repoFisica.setLista(listaFisica);
            System.out.println("\nDados de Pessoas Fisicas recuperados com sucesso. ");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("\n Erro ao recuperar dados de Pessoas Fisicas: " + e.getMessage());
        }

        // Recuperando dados de Pessoa Jurídica
        try (ObjectInputStream oisPJ = new ObjectInputStream(new FileInputStream(nomeArquivo + ".juridica.bin"))) {
            List<PessoaJuridica> listaJuridica = (List<PessoaJuridica>) oisPJ.readObject();
            repoJuridica.setLista(listaJuridica);
            System.out.println("\n  Dados de Pessoas Juridicas recuperados com sucesso. ");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("\n Erro ao recuperar dados de Pessoas Juridicas: " + e.getMessage());
        }
    }

    private static void salvarDados() {
        System.out.println("Digite o nome para salvar os arquivos:");
        String nomeArquivo = sc.nextLine();

        // Salvando dados de Pessoa Física
        try (ObjectOutputStream oosPF = new ObjectOutputStream(new FileOutputStream(nomeArquivo + ".fisica.bin"))) {
            oosPF.writeObject(repoFisica.obterTodos());
            System.out.println("\n Dados de Pessoas Fisicas salvos com sucesso.");
        } catch (IOException e) {
            System.err.println("\n Erro ao salvar dados de Pessoas Fisicas: " + e.getMessage());
        }

        // Salvando dados de Pessoa Jurídica
        try (ObjectOutputStream oosPJ = new ObjectOutputStream(new FileOutputStream(nomeArquivo + ".juridica.bin"))) {
            oosPJ.writeObject(repoJuridica.obterTodos());
            System.out.println("\n Dados de Pessoas Juridicas salvos com sucesso. ");
        } catch (IOException e) {
            System.err.println("\n Erro ao salvar dados de Pessoas Juridicas: " + e.getMessage());
        }
    }

}
