package cadastros;
import java.math.BigInteger;

public class cadDev {

    private BigInteger matriculaAl;
    private BigInteger isbn;
    private int idEmp;

    public int getIdEmp() {
        return idEmp;
    }

    public void setIdEmp(int idEmp) {
        this.idEmp = idEmp;
    }

    public BigInteger getMatriculaAl() {
        return matriculaAl;
    }

    public void setMatriculaAl(BigInteger matriculaAl) {
        this.matriculaAl = matriculaAl;
    }

    public BigInteger getIsbn() {
        return isbn;
    }

    public void setIsbn(BigInteger isbn) {
        this.isbn = isbn;
    }

    public void exibirDados() { // printa as informacoes na tela

        System.out.println("----------DADOS DA DEVOLUCAO-----------");
        System.out.println("Id do emprestimo: " + getIdEmp());
        System.out.println("matriucla: " + getMatriculaAl());
        System.out.println("serie: " + getIsbn());
        System.out.println("--------------------------------");
    }

}
