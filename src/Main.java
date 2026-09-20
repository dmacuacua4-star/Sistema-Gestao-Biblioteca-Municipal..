
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {

    // =========================================================
    // CONFIGURAÇÕES
    // =========================================================

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Biblioteca biblioteca = new Biblioteca();

        int opcao;

        do {

            apresentarMenu();

            opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {

                case 1:
                    registarLivro(biblioteca);
                    break;

                case 2:
                    biblioteca.listarLivros();
                    pausar();
                    break;

                case 3:
                    pesquisarLivro(biblioteca);
                    break;

                case 4:
                    registarUtilizador(biblioteca);
                    break;

                case 5:
                    biblioteca.listarUtilizadores();
                    pausar();
                    break;

                case 6:
                    realizarEmprestimo(biblioteca);
                    break;

                case 7:
                    realizarDevolucao(biblioteca);
                    break;

                case 8:
                    biblioteca.listarHistorico();
                    pausar();
                    break;

                case 9:
                    biblioteca.apresentarEstatisticas();
                    pausar();
                    break;

                case 0:
                    System.out.println();
                    System.out.println("==============================================");
                    System.out.println("      SISTEMA ENCERRADO COM SUCESSO");
                    System.out.println("==============================================");
                    System.out.println("Obrigado por utilizar o sistema!");
                    break;

                default:
                    System.out.println();
                    System.out.println("ERRO: Opção inválida.");
                    System.out.println("Escolha uma opção entre 0 e 9.");
                    pausar();
            }

        } while (opcao != 0);

        scanner.close();
    }

    // =========================================================
    // MENU PRINCIPAL
    // =========================================================

    private static void apresentarMenu() {

        System.out.println();
        System.out.println("==============================================");
        System.out.println("       SISTEMA DE GESTÃO DA BIBLIOTECA");
        System.out.println("==============================================");
        System.out.println("1. Registar Livro");
        System.out.println("2. Listar Livros");
        System.out.println("3. Pesquisar Livro");
        System.out.println("4. Registar Utilizador");
        System.out.println("5. Listar Utilizadores");
        System.out.println("6. Efectuar Empréstimo");
        System.out.println("7. Efectuar Devolução");
        System.out.println("8. Consultar Histórico");
        System.out.println("9. Estatísticas");
        System.out.println("0. Sair");
        System.out.println("==============================================");
    }

    // =========================================================
    // REGISTAR LIVRO
    // =========================================================

    private static void registarLivro(Biblioteca biblioteca) {

        System.out.println();
        System.out.println("==============================================");
        System.out.println("              REGISTO DE LIVRO");
        System.out.println("==============================================");

        int id = lerInteiroPositivo(
                "Digite o ID do livro: "
        );

        String titulo = lerTextoObrigatorio(
                "Digite o título do livro: "
        );

        String autor = lerTextoObrigatorio(
                "Digite o nome do autor: "
        );

        int ano = lerInteiroPositivo(
                "Digite o ano de publicação: "
        );

        int quantidade = lerInteiroPositivo(
                "Digite a quantidade disponível: "
        );

        boolean sucesso = biblioteca.adicionarLivro(
                id,
                titulo,
                autor,
                ano,
                quantidade
        );

        if (sucesso) {

            System.out.println();
            System.out.println("Livro registado com sucesso!");
        }

        pausar();
    }

    // =========================================================
    // PESQUISAR LIVRO
    // =========================================================

    private static void pesquisarLivro(Biblioteca biblioteca) {

        System.out.println();
        System.out.println("==============================================");
        System.out.println("              PESQUISAR LIVRO");
        System.out.println("==============================================");
        System.out.println("1. Pesquisar por título");
        System.out.println("2. Pesquisar por autor");
        System.out.println("0. Voltar");
        System.out.println("==============================================");

        int opcao = lerInteiro("Escolha uma opção: ");

        switch (opcao) {

            case 1:

                String titulo = lerTextoObrigatorio(
                        "Digite o título ou parte do título: "
                );

                biblioteca.pesquisarPorTitulo(titulo);

                pausar();

                break;

            case 2:

                String autor = lerTextoObrigatorio(
                        "Digite o autor ou parte do nome: "
                );

                biblioteca.pesquisarPorAutor(autor);

                pausar();

                break;

            case 0:
                break;

            default:

                System.out.println(
                        "ERRO: Opção inválida."
                );

                pausar();
        }
    }

    // =========================================================
    // REGISTAR UTILIZADOR
    // =========================================================

    private static void registarUtilizador(
            Biblioteca biblioteca) {

        System.out.println();
        System.out.println("==============================================");
        System.out.println("            REGISTO DE UTILIZADOR");
        System.out.println("==============================================");

        int id = lerInteiroPositivo(
                "Digite o ID do utilizador: "
        );

        String nome = lerTextoObrigatorio(
                "Digite o nome do utilizador: "
        );

        String contacto = lerTextoObrigatorio(
                "Digite o contacto do utilizador: "
        );

        boolean sucesso =
                biblioteca.adicionarUtilizador(
                        id,
                        nome,
                        contacto
                );

        if (sucesso) {

            System.out.println();
            System.out.println(
                    "Utilizador registado com sucesso!"
            );
        }

        pausar();
    }

    // =========================================================
    // EFECTUAR EMPRÉSTIMO
    // =========================================================

    private static void realizarEmprestimo(
            Biblioteca biblioteca) {

        System.out.println();
        System.out.println("==============================================");
        System.out.println("              EFECTUAR EMPRÉSTIMO");
        System.out.println("==============================================");

        int idLivro = lerInteiroPositivo(
                "Digite o ID do livro: "
        );

        int idUtilizador = lerInteiroPositivo(
                "Digite o ID do utilizador: "
        );

        biblioteca.realizarEmprestimo(
                idLivro,
                idUtilizador
        );

        pausar();
    }

    // =========================================================
    // EFECTUAR DEVOLUÇÃO
    // =========================================================

    private static void realizarDevolucao(
            Biblioteca biblioteca) {

        System.out.println();
        System.out.println("==============================================");
        System.out.println("              EFECTUAR DEVOLUÇÃO");
        System.out.println("==============================================");

        int idEmprestimo = lerInteiroPositivo(
                "Digite o ID do empréstimo: "
        );

        biblioteca.realizarDevolucao(
                idEmprestimo
        );

        pausar();
    }

    // =========================================================
    // LEITURA DE NÚMEROS
    // =========================================================

    private static int lerInteiro(String mensagem) {

        while (true) {

            System.out.print(mensagem);

            String entrada = scanner.nextLine();

            try {

                return Integer.parseInt(
                        entrada.trim()
                );

            } catch (NumberFormatException e) {

                System.out.println(
                        "ERRO: Digite um número inteiro válido."
                );
            }
        }
    }

    private static int lerInteiroPositivo(
            String mensagem) {

        while (true) {

            int numero = lerInteiro(mensagem);

            if (numero > 0) {
                return numero;
            }

            System.out.println(
                    "ERRO: O valor deve ser maior que zero."
            );
        }
    }

    // =========================================================
    // LEITURA DE TEXTO
    // =========================================================

    private static String lerTextoObrigatorio(
            String mensagem) {

        while (true) {

            System.out.print(mensagem);

            String texto = scanner.nextLine().trim();

            if (!texto.isEmpty()) {
                return texto;
            }

            System.out.println(
                    "ERRO: Este campo não pode ficar vazio."
            );
        }
    }

    // =========================================================
    // PAUSA
    // =========================================================

    private static void pausar() {

        System.out.println();
        System.out.println(
                "Pressione ENTER para continuar..."
        );

        scanner.nextLine();
    }

    // =========================================================
    // CLASSE LIVRO
    // =========================================================

    static class Livro {

        private int id;
        private String titulo;
        private String autor;
        private int anoPublicacao;
        private int quantidadeDisponivel;
        private int totalEmprestimos;

        public Livro(
                int id,
                String titulo,
                String autor,
                int anoPublicacao,
                int quantidadeDisponivel) {

            this.id = id;
            this.titulo = titulo;
            this.autor = autor;
            this.anoPublicacao = anoPublicacao;
            this.quantidadeDisponivel =
                    quantidadeDisponivel;

            this.totalEmprestimos = 0;
        }

        public int getId() {
            return id;
        }

        public String getTitulo() {
            return titulo;
        }

        public String getAutor() {
            return autor;
        }

        public int getAnoPublicacao() {
            return anoPublicacao;
        }

        public int getQuantidadeDisponivel() {
            return quantidadeDisponivel;
        }

        public int getTotalEmprestimos() {
            return totalEmprestimos;
        }

        public void realizarEmprestimo() {

            if (quantidadeDisponivel > 0) {

                quantidadeDisponivel--;

                totalEmprestimos++;
            }
        }

        public void realizarDevolucao() {

            quantidadeDisponivel++;
        }

        public boolean estaDisponivel() {

            return quantidadeDisponivel > 0;
        }

        @Override
        public String toString() {

            return String.format(
                    "ID: %d | Título: %s | Autor: %s | Ano: %d | Disponíveis: %d | Empréstimos: %d",
                    id,
                    titulo,
                    autor,
                    anoPublicacao,
                    quantidadeDisponivel,
                    totalEmprestimos
            );
        }
    }

    // =========================================================
    // CLASSE UTILIZADOR
    // =========================================================

    static class Utilizador {

        private int id;
        private String nome;
        private String contacto;

        public Utilizador(
                int id,
                String nome,
                String contacto) {

            this.id = id;
            this.nome = nome;
            this.contacto = contacto;
        }

        public int getId() {
            return id;
        }

        public String getNome() {
            return nome;
        }

        public String getContacto() {
            return contacto;
        }

        @Override
        public String toString() {

            return String.format(
                    "ID: %d | Nome: %s | Contacto: %s",
                    id,
                    nome,
                    contacto
            );
        }
    }

    // =========================================================
    // CLASSE EMPRÉSTIMO
    // =========================================================

    static class Emprestimo {

        private int id;
        private int idLivro;
        private int idUtilizador;

        private LocalDate dataEmprestimo;
        private LocalDate dataDevolucao;

        private String estado;

        public Emprestimo(
                int id,
                int idLivro,
                int idUtilizador) {

            this.id = id;
            this.idLivro = idLivro;
            this.idUtilizador = idUtilizador;

            this.dataEmprestimo =
                    LocalDate.now();

            this.dataDevolucao = null;

            this.estado = "EMPRESTADO";
        }

        public int getId() {
            return id;
        }

        public int getIdLivro() {
            return idLivro;
        }

        public int getIdUtilizador() {
            return idUtilizador;
        }

        public LocalDate getDataEmprestimo() {
            return dataEmprestimo;
        }

        public LocalDate getDataDevolucao() {
            return dataDevolucao;
        }

        public String getEstado() {
            return estado;
        }

        public boolean estaEmprestado() {

            return estado.equals("EMPRESTADO");
        }

        public void realizarDevolucao() {

            dataDevolucao = LocalDate.now();

            estado = "DEVOLVIDO";
        }

        public String getDataEmprestimoFormatada() {

            DateTimeFormatter formato =
                    DateTimeFormatter.ofPattern(
                            "dd/MM/yyyy"
                    );

            return dataEmprestimo.format(formato);
        }

        public String getDataDevolucaoFormatada() {

            if (dataDevolucao == null) {
                return "-";
            }

            DateTimeFormatter formato =
                    DateTimeFormatter.ofPattern(
                            "dd/MM/yyyy"
                    );

            return dataDevolucao.format(formato);
        }
    }

    // =========================================================
    // CLASSE BIBLIOTECA
    // =========================================================

    static class Biblioteca {

        // -----------------------------------------------------
        // CAPACIDADE DOS ARRAYS
        // -----------------------------------------------------

        private static final int MAX_LIVROS = 100;
        private static final int MAX_UTILIZADORES = 100;
        private static final int MAX_EMPRESTIMOS = 500;

        // -----------------------------------------------------
        // ARRAYS
        // -----------------------------------------------------

        private Livro[] livros;
        private Utilizador[] utilizadores;
        private Emprestimo[] emprestimos;

        // -----------------------------------------------------
        // CONTADORES
        // -----------------------------------------------------

        private int totalLivros;
        private int totalUtilizadores;
        private int totalEmprestimos;

        // -----------------------------------------------------
        // CONSTRUTOR
        // -----------------------------------------------------

        public Biblioteca() {

            livros =
                    new Livro[MAX_LIVROS];

            utilizadores =
                    new Utilizador[MAX_UTILIZADORES];

            emprestimos =
                    new Emprestimo[MAX_EMPRESTIMOS];

            totalLivros = 0;
            totalUtilizadores = 0;
            totalEmprestimos = 0;
        }

        // =====================================================
        // LIVROS
        // =====================================================

        public boolean adicionarLivro(
                int id,
                String titulo,
                String autor,
                int anoPublicacao,
                int quantidade) {

            if (totalLivros >= MAX_LIVROS) {

                System.out.println(
                        "ERRO: Limite de livros atingido."
                );

                return false;
            }

            if (buscarLivroPorId(id) != null) {

                System.out.println(
                        "ERRO: Já existe um livro com esse ID."
                );

                return false;
            }

            Livro novoLivro =
                    new Livro(
                            id,
                            titulo,
                            autor,
                            anoPublicacao,
                            quantidade
                    );

            livros[totalLivros] =
                    novoLivro;

            totalLivros++;

            return true;
        }

        public Livro buscarLivroPorId(int id) {

            for (int i = 0;
                 i < totalLivros;
                 i++) {

                if (livros[i].getId() == id) {

                    return livros[i];
                }
            }

            return null;
        }

        public void listarLivros() {

            if (totalLivros == 0) {

                System.out.println();
                System.out.println(
                        "Não existem livros registados."
                );

                return;
            }

            System.out.println();
            System.out.println(
                    "======================================================================"
            );

            System.out.println(
                    "                         CATÁLOGO DE LIVROS"
            );

            System.out.println(
                    "======================================================================"
            );

            System.out.printf(
                    "%-6s %-28s %-22s %-8s %-10s%n",
                    "ID",
                    "TÍTULO",
                    "AUTOR",
                    "ANO",
                    "DISP."
            );

            System.out.println(
                    "----------------------------------------------------------------------"
            );

            for (int i = 0;
                 i < totalLivros;
                 i++) {

                Livro livro = livros[i];

                System.out.printf(
                        "%-6d %-28s %-22s %-8d %-10d%n",
                        livro.getId(),
                        limitarTexto(
                                livro.getTitulo(),
                                28
                        ),
                        limitarTexto(
                                livro.getAutor(),
                                22
                        ),
                        livro.getAnoPublicacao(),
                        livro.getQuantidadeDisponivel()
                );
            }

            System.out.println(
                    "======================================================================"
            );
        }

        public void pesquisarPorTitulo(
                String titulo) {

            boolean encontrado = false;

            System.out.println();
            System.out.println(
                    "=============== RESULTADOS ==============="
            );

            for (int i = 0;
                 i < totalLivros;
                 i++) {

                Livro livro = livros[i];

                if (livro.getTitulo()
                        .toLowerCase()
                        .contains(
                                titulo.toLowerCase()
                        )) {

                    System.out.println(livro);

                    encontrado = true;
                }
            }

            if (!encontrado) {

                System.out.println(
                        "Nenhum livro encontrado."
                );
            }
        }

        public void pesquisarPorAutor(
                String autor) {

            boolean encontrado = false;

            System.out.println();
            System.out.println(
                    "=============== RESULTADOS ==============="
            );

            for (int i = 0;
                 i < totalLivros;
                 i++) {

                Livro livro = livros[i];

                if (livro.getAutor()
                        .toLowerCase()
                        .contains(
                                autor.toLowerCase()
                        )) {

                    System.out.println(livro);

                    encontrado = true;
                }
            }

            if (!encontrado) {

                System.out.println(
                        "Nenhum livro encontrado."
                );
            }
        }

        // =====================================================
        // UTILIZADORES
        // =====================================================

        public boolean adicionarUtilizador(
                int id,
                String nome,
                String contacto) {

            if (totalUtilizadores >=
                    MAX_UTILIZADORES) {

                System.out.println(
                        "ERRO: Limite de utilizadores atingido."
                );

                return false;
            }

            if (buscarUtilizadorPorId(id)
                    != null) {

                System.out.println(
                        "ERRO: Já existe um utilizador com esse ID."
                );

                return false;
            }

            Utilizador novoUtilizador =
                    new Utilizador(
                            id,
                            nome,
                            contacto
                    );

            utilizadores[
                    totalUtilizadores
            ] = novoUtilizador;

            totalUtilizadores++;

            return true;
        }

        public Utilizador buscarUtilizadorPorId(
                int id) {

            for (int i = 0;
                 i < totalUtilizadores;
                 i++) {

                if (utilizadores[i].getId()
                        == id) {

                    return utilizadores[i];
                }
            }

            return null;
        }

        public void listarUtilizadores() {

            if (totalUtilizadores == 0) {

                System.out.println();
                System.out.println(
                        "Não existem utilizadores registados."
                );

                return;
            }

            System.out.println();
            System.out.println(
                    "=============================================="
            );

            System.out.println(
                    "              LISTA DE UTILIZADORES"
            );

            System.out.println(
                    "=============================================="
            );

            for (int i = 0;
                 i < totalUtilizadores;
                 i++) {

                System.out.println(
                        utilizadores[i]
                );
            }

            System.out.println(
                    "=============================================="
            );
        }

        // =====================================================
        // EMPRÉSTIMOS
        // =====================================================

        public boolean realizarEmprestimo(
                int idLivro,
                int idUtilizador) {

            if (totalEmprestimos >=
                    MAX_EMPRESTIMOS) {

                System.out.println(
                        "ERRO: Limite de empréstimos atingido."
                );

                return false;
            }

            Livro livro =
                    buscarLivroPorId(idLivro);

            if (livro == null) {

                System.out.println(
                        "ERRO: Livro não encontrado."
                );

                return false;
            }

            Utilizador utilizador =
                    buscarUtilizadorPorId(
                            idUtilizador
                    );

            if (utilizador == null) {

                System.out.println(
                        "ERRO: Utilizador não encontrado."
                );

                return false;
            }

            if (!livro.estaDisponivel()) {

                System.out.println(
                        "ERRO: Não existem exemplares disponíveis."
                );

                return false;
            }

            if (utilizadorPossuiLivro(
                    idLivro,
                    idUtilizador)) {

                System.out.println(
                        "ERRO: Este utilizador já possui este livro."
                );

                return false;
            }

            int novoId = gerarIdEmprestimo();

            Emprestimo novoEmprestimo =
                    new Emprestimo(
                            novoId,
                            idLivro,
                            idUtilizador
                    );

            emprestimos[
                    totalEmprestimos
            ] = novoEmprestimo;

            totalEmprestimos++;

            livro.realizarEmprestimo();

            System.out.println();
            System.out.println(
                    "=============================================="
            );

            System.out.println(
                    "       EMPRÉSTIMO REALIZADO COM SUCESSO"
            );

            System.out.println(
                    "=============================================="
            );

            System.out.println(
                    "ID do empréstimo: "
                            + novoId
            );

            System.out.println(
                    "Livro: "
                            + livro.getTitulo()
            );

            System.out.println(
                    "Utilizador: "
                            + utilizador.getNome()
            );

            System.out.println(
                    "Data: "
                            + novoEmprestimo
                            .getDataEmprestimoFormatada()
            );

            System.out.println(
                    "Quantidade disponível: "
                            + livro
                            .getQuantidadeDisponivel()
            );

            System.out.println(
                    "=============================================="
            );

            return true;
        }

        private boolean utilizadorPossuiLivro(
                int idLivro,
                int idUtilizador) {

            for (int i = 0;
                 i < totalEmprestimos;
                 i++) {

                Emprestimo emprestimo =
                        emprestimos[i];

                if (emprestimo.getIdLivro()
                        == idLivro
                        && emprestimo
                        .getIdUtilizador()
                        == idUtilizador
                        && emprestimo
                        .estaEmprestado()) {

                    return true;
                }
            }

            return false;
        }

        private int gerarIdEmprestimo() {

            if (totalEmprestimos == 0) {
                return 1;
            }

            return emprestimos[
                    totalEmprestimos - 1
            ].getId() + 1;
        }

        private Emprestimo buscarEmprestimoPorId(
                int id) {

            for (int i = 0;
                 i < totalEmprestimos;
                 i++) {

                if (emprestimos[i].getId()
                        == id) {

                    return emprestimos[i];
                }
            }

            return null;
        }

        // =====================================================
        // DEVOLUÇÃO
        // =====================================================

        public boolean realizarDevolucao(
                int idEmprestimo) {

            Emprestimo emprestimo =
                    buscarEmprestimoPorId(
                            idEmprestimo
                    );

            if (emprestimo == null) {

                System.out.println(
                        "ERRO: Empréstimo não encontrado."
                );

                return false;
            }

            if (!emprestimo
                    .estaEmprestado()) {

                System.out.println(
                        "ERRO: Este empréstimo já foi devolvido."
                );

                return false;
            }

            Livro livro =
                    buscarLivroPorId(
                            emprestimo.getIdLivro()
                    );

            if (livro == null) {

                System.out.println(
                        "ERRO: Livro não encontrado."
                );

                return false;
            }

            emprestimo.realizarDevolucao();

            livro.realizarDevolucao();

            System.out.println();
            System.out.println(
                    "=============================================="
            );

            System.out.println(
                    "       DEVOLUÇÃO REALIZADA COM SUCESSO"
            );

            System.out.println(
                    "=============================================="
            );

            System.out.println(
                    "ID do empréstimo: "
                            + emprestimo.getId()
            );

            System.out.println(
                    "Livro: "
                            + livro.getTitulo()
            );

            System.out.println(
                    "Data da devolução: "
                            + emprestimo
                            .getDataDevolucaoFormatada()
            );

            System.out.println(
                    "Quantidade disponível: "
                            + livro
                            .getQuantidadeDisponivel()
            );

            System.out.println(
                    "=============================================="
            );

            return true;
        }

        // =====================================================
        // HISTÓRICO
        // =====================================================

        public void listarHistorico() {

            if (totalEmprestimos == 0) {

                System.out.println();
                System.out.println(
                        "Não existem empréstimos registados."
                );

                return;
            }

            System.out.println();
            System.out.println(
                    "=========================================================================="
            );

            System.out.println(
                    "                     HISTÓRICO DE EMPRÉSTIMOS"
            );

            System.out.println(
                    "=========================================================================="
            );

            for (int i = 0;
                 i < totalEmprestimos;
                 i++) {

                Emprestimo emprestimo =
                        emprestimos[i];

                Livro livro =
                        buscarLivroPorId(
                                emprestimo.getIdLivro()
                        );

                Utilizador utilizador =
                        buscarUtilizadorPorId(
                                emprestimo
                                .getIdUtilizador()
                        );

                String nomeLivro =
                        livro != null
                                ? livro.getTitulo()
                                : "Desconhecido";

                String nomeUtilizador =
                        utilizador != null
                                ? utilizador.getNome()
                                : "Desconhecido";

                System.out.println(
                        "ID Empréstimo: "
                                + emprestimo.getId()
                );

                System.out.println(
                        "Livro: "
                                + nomeLivro
                );

                System.out.println(
                        "Utilizador: "
                                + nomeUtilizador
                );

                System.out.println(
                        "Data do empréstimo: "
                                + emprestimo
                                .getDataEmprestimoFormatada()
                );

                System.out.println(
                        "Data da devolução: "
                                + emprestimo
                                .getDataDevolucaoFormatada()
                );

                System.out.println(
                        "Estado: "
                                + emprestimo.getEstado()
                );

                System.out.println(
                        "----------------------------------------------------------------------"
                );
            }

            System.out.println(
                    "=========================================================================="
            );
        }

        // =====================================================
        // ESTATÍSTICAS
        // =====================================================

        public void apresentarEstatisticas() {

            System.out.println();
            System.out.println(
                    "=============================================="
            );

            System.out.println(
                    "                 ESTATÍSTICAS"
            );

            System.out.println(
                    "=============================================="
            );

            int totalExemplares =
                    0;

            int totalRequisitados =
                    0;

            int totalActivos =
                    0;

            for (int i = 0;
                 i < totalLivros;
                 i++) {

                totalExemplares +=
                        livros[i]
                        .getQuantidadeDisponivel();

                totalRequisitados +=
                        livros[i]
                        .getTotalEmprestimos();
            }

            for (int i = 0;
                 i < totalEmprestimos;
                 i++) {

                if (emprestimos[i]
                        .estaEmprestado()) {

                    totalActivos++;
                }
            }

            System.out.println(
                    "Total de títulos registados: "
                            + totalLivros
            );

            System.out.println(
                    "Total de exemplares actualmente disponíveis: "
                            + totalExemplares
            );

            System.out.println(
                    "Total de utilizadores registados: "
                            + totalUtilizadores
            );

            System.out.println(
                    "Total de empréstimos realizados: "
                            + totalRequisitados
            );

            System.out.println(
                    "Empréstimos actualmente activos: "
                            + totalActivos
            );

            Livro maisEmprestado =
                    obterLivroMaisEmprestado();

            System.out.println();

            if (maisEmprestado != null) {

                System.out.println(
                        "LIVRO MAIS EMPRESTADO"
                );

                System.out.println(
                        "Título: "
                                + maisEmprestado
                                .getTitulo()
                );

                System.out.println(
                        "Autor: "
                                + maisEmprestado
                                .getAutor()
                );

                System.out.println(
                        "Número de empréstimos: "
                                + maisEmprestado
                                .getTotalEmprestimos()
                );

            } else {

                System.out.println(
                        "Ainda não existem empréstimos."
                );
            }

            System.out.println(
                    "=============================================="
            );
        }

        private Livro obterLivroMaisEmprestado() {

            if (totalLivros == 0) {
                return null;
            }

            Livro maisEmprestado =
                    livros[0];

            for (int i = 1;
                 i < totalLivros;
                 i++) {

                if (livros[i]
                        .getTotalEmprestimos()
                        >
                        maisEmprestado
                        .getTotalEmprestimos()) {

                    maisEmprestado =
                            livros[i];
                }
            }

            if (maisEmprestado
                    .getTotalEmprestimos()
                    == 0) {

                return null;
            }

            return maisEmprestado;
        }

        // =====================================================
        // MÉTODO AUXILIAR
        // =====================================================

        private String limitarTexto(
                String texto,
                int tamanho) {

            if (texto == null) {
                return "";
            }

            if (texto.length() <= tamanho) {
                return texto;
            }

            return texto.substring(
                    0,
                    tamanho - 3
            ) + "...";
        }
    }
}
