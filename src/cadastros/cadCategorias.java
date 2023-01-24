package cadastros;
public class cadCategorias {

    private String categoria;

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public void exibirDados() { // printa as informacoes na tela

        System.out.println("----------DADOS DA DEVOLUCAO-----------");
        System.out.println("Categoria cadastrada: " + getCategoria());
        System.out.println("--------------------------------");
    }

}
