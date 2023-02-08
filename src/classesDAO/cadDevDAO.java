package classesDAO;

import java.sql.*; // importa todas as classes necessaria do sql

import cadastros.cadDev;
import database.conexaoMySQL;
import java.math.BigInteger;

public class cadDevDAO {

    Connection dbconn = null; // variavel de conexao
    PreparedStatement pstm = null; // variavel que serve para montar a query sem a necessidade de concatenar

    public boolean save(cadDev devolucao) { // funcao que salva os dados no banco

        // insercao dos dados no bd
        String sql = " insert into tb_devolucoes (dev_use_matricula,dev_liv_isbn, dev_emp_id) values (?,?,?)";
        // bloco de codigo que vai tratar as excecoes do codigo
        try {

            dbconn = conexaoMySQL.createConnectionToMySQL(); // reestabelece a conexao com o banco
            // vai inserir os dados no bd na ordem q esta aqui
            pstm = (PreparedStatement) dbconn.prepareStatement(sql);
            pstm.setObject(1, devolucao.getMatriculaAl());
            pstm.setObject(2, devolucao.getIsbn());
            pstm.setInt(3, devolucao.getIdEmp());
            // tinker -> php -> DB::user(1) | buscar um método de consultar os dados do
            // banco em Java
            pstm.execute();
        } catch (Exception error) {
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

    public void exibirDev() {
        String sql = "SELECT * FROM tb_devolucoes";
        try {
            dbconn = conexaoMySQL.createConnectionToMySQL();
            pstm = (PreparedStatement) dbconn.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                cadDev devolucao = new cadDev();

                devolucao.setIsbn(rs.getObject("dev_liv_isbn", BigInteger.class));
                devolucao.setMatriculaAl(rs.getObject("dev_use_matricula", BigInteger.class));
                devolucao.setIdEmp(rs.getInt("dev_emp_id"));

                System.out.println(
                        "| \u001b[32;1mAluno:\u001b[m " + devolucao.getMatriculaAl() + " |  \u001b[32;1mLivro:\u001b[m "
                                + devolucao.getIsbn() + "|  \u001b[32;1mId: \u001b[m" + devolucao.getIdEmp());
            }

        } catch (Exception error) {

            error.printStackTrace();
        }
    }
}
