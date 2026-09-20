import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Emprestimo {

    private int id;
    private int idLivro;
    private int idUtilizador;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private String estado;

    public Emprestimo(int id, int idLivro, int idUtilizador) {
        this.id = id;
        this.idLivro = idLivro;
        this.idUtilizador = idUtilizador;
        this.dataEmprestimo = LocalDate.now();
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
        this.dataDevolucao = LocalDate.now();
        this.estado = "DEVOLVIDO";
    }

    public String getDataEmprestimoFormatada() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dataEmprestimo.format(formato);
    }

    public String getDataDevolucaoFormatada() {

        if (dataDevolucao == null) {
            return "-";
        }

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dataDevolucao.format(formato);
    }

    @Override
    public String toString() {

        return String.format(
            "ID Empréstimo: %d | Livro: %d | Utilizador: %d | Data: %s | Devolução: %s | Estado: %s",
            id,
            idLivro,
            idUtilizador,
            getDataEmprestimoFormatada(),
            getDataDevolucaoFormatada(),
            estado
        );
    }
}
