package cadastros;
import java.math.BigInteger;

public class cadUser {

    private String nome;
    private BigInteger matricula;
    private String status;

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
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
    public void exibirDados(){
      
        System.out.println("----------DADOS DO USUARIO-----------");
        System.out.println("nome: " +getNome());
        System.out.println("matriucla: " +getMatricula());
        System.out.println("--------------------------------");
    }

    




}