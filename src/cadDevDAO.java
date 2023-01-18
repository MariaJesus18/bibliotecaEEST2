import java.sql.*; // importa todas as classes necessaria do sql

public class cadDevDAO {

    public void save(cadDev devolucao) { // funcao que salva os dados no banco

        // insercao dos dados no bd
        String sql = " insert into tb_devolucoes (dev_use_matricula,dev_liv_isbn, dev_emp_id) values (?,?,?)";

        Connection dbconn = null; // variavel de conexao
        PreparedStatement pstm = null; // variavel que serve para montar a query sem a necessidade de concatenar

        // bloco de codigo que vai tratar as excecoes do codigo
        try {

            dbconn = conexaoMySQL.createConnectionToMySQL(); // reestabelece a conexao com o banco
            // vai inserir os dados no bd na ordem q esta aqui
            pstm = (PreparedStatement) dbconn.prepareStatement(sql);
            pstm.setObject(1, devolucao.getMatriculaAl());
            pstm.setObject(2, devolucao.getIsbn());
            pstm.setInt(3, devolucao.getIdEmp());

            pstm.execute();
        } catch (Exception error) {
            error.printStackTrace();
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
    }
}
