public class Biblioteca {

    // Capacidade máxima do sistema
    private static final int MAX_LIVROS = 100;
    private static final int MAX_UTILIZADORES = 100;
    private static final int MAX_EMPRESTIMOS = 500;

    // Arrays que simulam a base de dados
    private Livro[] livros;
    private Utilizador[] utilizadores;
    private Emprestimo[] emprestimos;

    // Contadores
    private int totalLivros;
    private int totalUtilizadores;
    private int totalEmprestimos;

    // Construtor
    public Biblioteca() {

        livros = new Livro[MAX_LIVROS];
        utilizadores = new Utilizador[MAX_UTILIZADORES];
        emprestimos = new Emprestimo[MAX_EMPRESTIMOS];

        totalLivros = 0;
        totalUtilizadores = 0;
        totalEmprestimos = 0;
    }

    // =========================================================
    // GESTÃO DE LIVROS
    // =========================================================

    public boolean adicionarLivro(
            int id,
            String titulo,
            String autor,
            int anoPublicacao,
            int quantidade) {

        if (totalLivros >= MAX_LIVROS) {
            System.out.println("ERRO: Capacidade máxima de livros atingida.");
            return false;
        }

        if (buscarLivroPorId(id) != null) {
            System.out.println("ERRO: Já existe um livro com esse ID.");
            return false;
        }

        Livro novoLivro = new Livro(
                id,
                titulo,
                autor,
                anoPublicacao,
                quantidade
        );

        livros[totalLivros] = novoLivro;
        totalLivros++;

        return true;
    }

    public Livro buscarLivroPorId(int id) {

        for (int i = 0; i < totalLivros; i++) {

            if (livros[i].getId() == id) {
                return livros[i];
            }
        }

        return null;
    }

    public void listarLivros() {

        if (totalLivros == 0) {
            System.out.println("\nNão existem livros registados.");
            return;
        }

        System.out.println("\n==============================================================");
        System.out.println("                     CATÁLOGO DE LIVROS");
        System.out.println("==============================================================");

        System.out.printf(
                "%-6s %-30s %-25s %-8s %-10s%n",
                "ID",
                "TÍTULO",
                "AUTOR",
                "ANO",
                "DISP."
        );

        System.out.println("--------------------------------------------------------------");

        for (int i = 0; i < totalLivros; i++) {

            Livro livro = livros[i];

            System.out.printf(
                    "%-6d %-30s %-25s %-8d %-10d%n",
                    livro.getId(),
                    limitarTexto(livro.getTitulo(), 30),
                    limitarTexto(livro.getAutor(), 25),
                    livro.getAnoPublicacao(),
                    livro.getQuantidadeDisponivel()
            );
        }

        System.out.println("==============================================================");
    }

    public void pesquisarPorTitulo(String titulo) {

        boolean encontrado = false;

        System.out.println("\n================ PESQUISA POR TÍTULO ================");

        for (int i = 0; i < totalLivros; i++) {

            Livro livro = livros[i];

            if (livro.getTitulo()
                    .toLowerCase()
                    .contains(titulo.toLowerCase())) {

                System.out.println(livro);
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("Nenhum livro encontrado.");
        }
    }

    public void pesquisarPorAutor(String autor) {

        boolean encontrado = false;

        System.out.println("\n================ PESQUISA POR AUTOR ================");

        for (int i = 0; i < totalLivros; i++) {

            Livro livro = livros[i];

            if (livro.getAutor()
                    .toLowerCase()
                    .contains(autor.toLowerCase())) {

                System.out.println(livro);
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("Nenhum livro encontrado.");
        }
    }

    // =========================================================
    // GESTÃO DE UTILIZADORES
    // =========================================================

    public boolean adicionarUtilizador(
            int id,
            String nome,
            String contacto) {

        if (totalUtilizadores >= MAX_UTILIZADORES) {
            System.out.println("ERRO: Capacidade máxima de utilizadores atingida.");
            return false;
        }

        if (buscarUtilizadorPorId(id) != null) {
            System.out.println("ERRO: Já existe um utilizador com esse ID.");
            return false;
        }

        Utilizador novoUtilizador =
                new Utilizador(id, nome, contacto);

        utilizadores[totalUtilizadores] = novoUtilizador;
        totalUtilizadores++;

        return true;
    }

    public Utilizador buscarUtilizadorPorId(int id) {

        for (int i = 0; i < totalUtilizadores; i++) {

            if (utilizadores[i].getId() == id) {
                return utilizadores[i];
            }
        }

        return null;
    }

    public void listarUtilizadores() {

        if (totalUtilizadores == 0) {
            System.out.println("\nNão existem utilizadores registados.");
            return;
        }

        System.out.println("\n==============================================");
        System.out.println("             LISTA DE UTILIZADORES");
        System.out.println("==============================================");

        for (int i = 0; i < totalUtilizadores; i++) {
            System.out.println(utilizadores[i]);
        }

        System.out.println("==============================================");
    }

    // =========================================================
    // GESTÃO DE EMPRÉSTIMOS
    // =========================================================

    public boolean realizarEmprestimo(
            int idLivro,
            int idUtilizador) {

        if (totalEmprestimos >= MAX_EMPRESTIMOS) {
            System.out.println("ERRO: Capacidade máxima de empréstimos atingida.");
            return false;
        }

        Livro livro = buscarLivroPorId(idLivro);

        if (livro == null) {
            System.out.println("ERRO: Livro não encontrado.");
            return false;
        }

        Utilizador utilizador =
                buscarUtilizadorPorId(idUtilizador);

        if (utilizador == null) {
            System.out.println("ERRO: Utilizador não encontrado.");
            return false;
        }

        if (!livro.estaDisponivel()) {
            System.out.println("ERRO: Não existem exemplares disponíveis.");
            return false;
        }

        // Verificar se o utilizador já possui este livro
        if (utilizadorPossuiLivro(idLivro, idUtilizador)) {

            System.out.println(
                    "ERRO: Este utilizador já possui este livro emprestado."
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

        emprestimos[totalEmprestimos] = novoEmprestimo;
        totalEmprestimos++;

        livro.realizarEmprestimo();

        System.out.println("\n==============================================");
        System.out.println("       EMPRÉSTIMO REALIZADO COM SUCESSO");
        System.out.println("==============================================");
        System.out.println("ID do empréstimo: " + novoId);
        System.out.println("Livro: " + livro.getTitulo());
        System.out.println("Utilizador: " + utilizador.getNome());
        System.out.println("Data: " + novoEmprestimo.getDataEmprestimoFormatada());
        System.out.println("Quantidade disponível: "
                + livro.getQuantidadeDisponivel());
        System.out.println("==============================================");

        return true;
    }

    private boolean utilizadorPossuiLivro(
            int idLivro,
            int idUtilizador) {

        for (int i = 0; i < totalEmprestimos; i++) {

            Emprestimo emprestimo = emprestimos[i];

            if (emprestimo.getIdLivro() == idLivro
                    && emprestimo.getIdUtilizador() == idUtilizador
                    && emprestimo.estaEmprestado()) {

                return true;
            }
        }

        return false;
    }

    private int gerarIdEmprestimo() {

        if (totalEmprestimos == 0) {
            return 1;
        }

        return emprestimos[totalEmprestimos - 1].getId() + 1;
    }

    private Emprestimo buscarEmprestimoPorId(int id) {

        for (int i = 0; i < totalEmprestimos; i++) {

            if (emprestimos[i].getId() == id) {
                return emprestimos[i];
            }
        }

        return null;
    }

    public boolean realizarDevolucao(int idEmprestimo) {

        Emprestimo emprestimo =
                buscarEmprestimoPorId(idEmprestimo);

        if (emprestimo == null) {

            System.out.println(
                    "ERRO: Empréstimo não encontrado."
            );

            return false;
        }

        if (!emprestimo.estaEmprestado()) {

            System.out.println(
                    "ERRO: Este empréstimo já foi devolvido."
            );

            return false;
        }

        Livro livro =
                buscarLivroPorId(emprestimo.getIdLivro());

        if (livro == null) {

            System.out.println(
                    "ERRO: Livro associado ao empréstimo não encontrado."
            );

            return false;
        }

        emprestimo.realizarDevolucao();
        livro.realizarDevolucao();

        System.out.println("\n==============================================");
        System.out.println("       DEVOLUÇÃO REALIZADA COM SUCESSO");
        System.out.println("==============================================");
        System.out.println("ID do empréstimo: " + emprestimo.getId());
        System.out.println("Livro: " + livro.getTitulo());
        System.out.println("Data da devolução: "
                + emprestimo.getDataDevolucaoFormatada());
        System.out.println("Quantidade disponível: "
                + livro.getQuantidadeDisponivel());
        System.out.println("==============================================");

        return true;
    }

    // =========================================================
    // HISTÓRICO
    // =========================================================

    public void listarHistorico() {

        if (totalEmprestimos == 0) {

            System.out.println(
                    "\nNão existem empréstimos registados."
            );

            return;
        }

        System.out.println("\n==========================================================================");
        System.out.println("                       HISTÓRICO DE EMPRÉSTIMOS");
        System.out.println("==========================================================================");

        System.out.printf(
                "%-6s %-22s %-22s %-12s %-12s %-15s%n",
                "ID",
                "LIVRO",
                "UTILIZADOR",
                "EMPRÉSTIMO",
                "DEVOLUÇÃO",
                "ESTADO"
        );

        System.out.println("--------------------------------------------------------------------------");

        for (int i = 0; i < totalEmprestimos; i++) {

            Emprestimo emprestimo = emprestimos[i];

            Livro livro =
                    buscarLivroPorId(emprestimo.getIdLivro());

            Utilizador utilizador =
                    buscarUtilizadorPorId(emprestimo.getIdUtilizador());

            String nomeLivro =
                    livro != null ? livro.getTitulo() : "Desconhecido";

            String nomeUtilizador =
                    utilizador != null
                            ? utilizador.getNome()
                            : "Desconhecido";

            System.out.printf(
                    "%-6d %-22s %-22s %-12s %-12s %-15s%n",
                    emprestimo.getId(),
                    limitarTexto(nomeLivro, 22),
                    limitarTexto(nomeUtilizador, 22),
                    emprestimo.getDataEmprestimoFormatada(),
                    emprestimo.getDataDevolucaoFormatada(),
                    emprestimo.getEstado()
            );
        }

        System.out.println("==========================================================================");
    }

    // =========================================================
    // ESTATÍSTICAS
    // =========================================================

    public void apresentarEstatisticas() {

        System.out.println("\n==============================================");
        System.out.println("                ESTATÍSTICAS");
        System.out.println("==============================================");

        System.out.println(
                "Total de títulos registados: " + totalLivros
        );

        int totalExemplares = 0;
        int totalDisponiveis = 0;

        for (int i = 0; i < totalLivros; i++) {

            totalExemplares +=
                    livros[i].getQuantidadeDisponivel();

            totalDisponiveis +=
                    livros[i].getQuantidadeDisponivel();
        }

        int totalRequisitados = 0;

        for (int i = 0; i < totalLivros; i++) {

            totalRequisitados +=
                    livros[i].getTotalEmprestimos();
        }

        int totalEmprestimosActivos = 0;

        for (int i = 0; i < totalEmprestimos; i++) {

            if (emprestimos[i].estaEmprestado()) {
                totalEmprestimosActivos++;
            }
        }

        System.out.println(
                "Total de exemplares actualmente disponíveis: "
                        + totalDisponiveis
        );

        System.out.println(
                "Total de empréstimos realizados: "
                        + totalRequisitados
        );

        System.out.println(
                "Empréstimos actualmente activos: "
                        + totalEmprestimosActivos
        );

        System.out.println(
                "Total de utilizadores registados: "
                        + totalUtilizadores
        );

        Livro maisEmprestado = obterLivroMaisEmprestado();

        if (maisEmprestado != null) {

            System.out.println("\nLivro mais emprestado:");

            System.out.println(
                    "Título: " + maisEmprestado.getTitulo()
            );

            System.out.println(
                    "Autor: " + maisEmprestado.getAutor()
            );

            System.out.println(
                    "Número de empréstimos: "
                            + maisEmprestado.getTotalEmprestimos()
            );

        } else {

            System.out.println(
                    "\nAinda não existem empréstimos."
            );
        }

        System.out.println("==============================================");
    }

    private Livro obterLivroMaisEmprestado() {

        if (totalLivros == 0) {
            return null;
        }

        Livro maisEmprestado = livros[0];

        for (int i = 1; i < totalLivros; i++) {

            if (livros[i].getTotalEmprestimos()
                    > maisEmprestado.getTotalEmprestimos()) {

                maisEmprestado = livros[i];
            }
        }

        if (maisEmprestado.getTotalEmprestimos() == 0) {
            return null;
        }

        return maisEmprestado;
    }

    // =========================================================
    // MÉTODOS AUXILIARES
    // =========================================================

    private String limitarTexto(String texto, int tamanho) {

        if (texto == null) {
            return "";
        }

        if (texto.length() <= tamanho) {
            return texto;
        }

        return texto.substring(0, tamanho - 3) + "...";
    }

    public int getTotalLivros() {
        return totalLivros;
    }

    public int getTotalUtilizadores() {
        return totalUtilizadores;
    }

    public int getTotalEmprestimos() {
        return totalEmprestimos;
    }
}
