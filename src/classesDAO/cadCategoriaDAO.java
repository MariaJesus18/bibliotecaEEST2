package classesDAO;

import java.sql.*; // importa todas as classes necessaria do sql

import cadastros.cadCategorias;
import database.conexaoMySQL;

public class cadCategoriaDAO {

    public void save(cadCategorias categoria) { // funcao que salva os dados no banco

        // insercao dos dados no bd
        String sql = " insert into tb_categorias (cat_categoria) values (?)";

        Connection dbconn = null; // variavel de conexao
        PreparedStatement pstm = null; // variavel que serve para montar a query sem a necessidade de concatenar

        // bloco de codigo que vai tratar as excecoes do codigo
        try {

            dbconn = conexaoMySQL.createConnectionToMySQL(); // reestabelece a conexao com o banco
            // vai inserir os dados no bd na ordem q esta aqui
            pstm = (PreparedStatement) dbconn.prepareStatement(sql);
            pstm.setString(1, categoria.getCategoria());
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
