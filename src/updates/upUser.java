package updates;

import java.math.BigInteger;

public class upUser {

    private String nome;
    private BigInteger matricula;
    private String status;
    private BigInteger oldMat;

    public BigInteger getOldMat() {
        return oldMat;
    }

    public void setOldMat(BigInteger oldMat) {
        this.oldMat = oldMat;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigInteger getMatricula() {
        return matricula;
    }

    public void setMatricula(BigInteger matricula) {
        this.matricula = matricula;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
