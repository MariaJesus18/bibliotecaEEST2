import java.math.BigInteger;

public class cadLivro { 
    
    conexaoMySQL db = new conexaoMySQL();

    private String titulo;
    private BigInteger isbn;
    private String autor;
    private String status;
    private int idCategoria;

    public int getIdCategoria() {
        return idCategoria;
    }
    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public BigInteger getIsbn() {
        return isbn;
    }
    public void setIsbn(BigInteger isbn) {
        this.isbn = isbn;
    }
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }

    
    public void exibirDados(){
      
        System.out.println("----------DADOS DO LIVRO-----------");
        System.out.println("titulo: " +getTitulo());
        System.out.println("isbn: " +getIsbn());
        System.out.println("autor: " +getAutor());
        System.out.println("status: " +getStatus());
        System.out.println("--------------------------------");
    }
}
