package classesDAO;

import java.sql.*; // importa todas as classes necessaria do sql

import cadastros.cadEmp;
import database.conexaoMySQL;
import java.math.BigInteger;

public class cadEmpDAO {

    Connection dbconn = null; // variavel de conexao
    PreparedStatement pstm = null; // variavel que serve para montar a query sem a necessidade de concatenar

    public boolean save(cadEmp emprestimo) {

        // insercao dos dados no bd
        String sql = " insert into tb_emprestimos (emp_use_matricula,emp_liv_isbn) values (?,?)";

       

        // bloco de codigo que vai tratar as excecoes do codigo
        try {

            dbconn = conexaoMySQL.createConnectionToMySQL(); // reestabelece a conexao com o banco
            // vai inserir os dados no bd na ordem q esta aqui
            pstm = (PreparedStatement) dbconn.prepareStatement(sql);
            pstm.setObject(1, emprestimo.getMatriculaAl());
            pstm.setObject(2, emprestimo.getIsbn());
            pstm.execute();
        } catch (Exception error) {
            // error.printStackTrace();
            return false;
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (dbconn != null) {
                    dbconn.close();
                }
            } catch (Exception error) {
                error.printStackTrace();
            }
        }
        return true;
    }
    public void exibirEmp() {
        String sql = "SELECT * FROM tb_emprestimos";
        try {
            dbconn = conexaoMySQL.createConnectionToMySQL();
            pstm = (PreparedStatement) dbconn.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                cadEmp emprestimo = new cadEmp();
                
                emprestimo.setIsbn(rs.getObject("emp_liv_isbn", BigInteger.class));
                emprestimo.setMatriculaAl(rs.getObject("emp_use_matricula", BigInteger.class));
    
                System.out.println(
                        "| \u001b[32;1mAluno:\u001b[m " + emprestimo.getMatriculaAl() + " |  \u001b[32;1mLivro:\u001b[m "
                                + emprestimo.getIsbn() + " |");
            }

        } catch (Exception error) {

            error.printStackTrace();
        }
    }
}
