import java.sql.*; // importa todas as classes necessaria do sql

public class cadUserDAO {

    public void save(cadUser user) {
        // insercao dos dados no bd
        String sql = "insert into tb_users (use_matricula,use_nome) values (?,?)";

        Connection dbconn = null; // variavel de conexao
        PreparedStatement pstm = null; // variavel que serve para montar a query sem a necessidade de concatenar

        // bloco de codigo que vai tratar as excecoes do codigo
        try {

            dbconn = conexaoMySQL.createConnectionToMySQL(); // reestabelece a conexao com o banco
            // vai inserir os dados no bd na ordem q esta aqui
            pstm = (PreparedStatement) dbconn.prepareStatement(sql);
            pstm.setObject(1, user.getMatricula());
            pstm.setString(2, user.getNome());
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
