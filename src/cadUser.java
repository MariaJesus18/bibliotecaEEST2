import java.math.BigInteger;

public class cadUser {

    private String nome;
    private BigInteger matricula;

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