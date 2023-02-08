package cadastros;

import java.math.BigInteger;

public class cadEmp {

    private BigInteger matriculaAl;
    private BigInteger isbn;

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

    public void exibirDados() {

        System.out.println("----------DADOS DO EMPRESTIMO-----------");
        System.out.println("matriucla: " + getMatriculaAl());
        System.out.println("serie: " + getIsbn());
        System.out.println("--------------------------------");
    }
}
