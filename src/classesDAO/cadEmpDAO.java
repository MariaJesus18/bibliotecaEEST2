package classesDAO;

import java.sql.*; // importa todas as classes necessaria do sql

import cadastros.cadEmp;
import database.conexaoMySQL;

public class cadEmpDAO {

    public boolean save(cadEmp emprestimo) {

        // insercao dos dados no bd
        String sql = " insert into tb_emprestimos (emp_use_matricula,emp_liv_isbn) values (?,?)";

        Connection dbconn = null; // variavel de conexao
        PreparedStatement pstm = null; // variavel que serve para montar a query sem a necessidade de concatenar

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
}
