public class Utilizador {

    private int id;
    private String nome;
    private String contacto;

    public Utilizador(int id, String nome, String contacto) {
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
