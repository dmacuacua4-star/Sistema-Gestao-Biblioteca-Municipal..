public class Livro {

    private int id;
    private String titulo;
    private String autor;
    private int anoPublicacao;
    private int quantidadeDisponivel;
    private int totalEmprestimos;

    public Livro(int id, String titulo, String autor,
                 int anoPublicacao, int quantidadeDisponivel) {

        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
        this.quantidadeDisponivel = quantidadeDisponivel;
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
            "ID: %d | Título: %s | Autor: %s | Ano: %d | Disponíveis: %d | Total de empréstimos: %d",
            id,
            titulo,
            autor,
            anoPublicacao,
            quantidadeDisponivel,
            totalEmprestimos
        );
    }
}
