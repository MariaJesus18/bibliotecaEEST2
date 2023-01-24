package classesDAO;
import java.sql.*; // importa todas as classes necessaria do sql
import cadastros.cadDev;
import cadastros.cadEmp;
import cadastros.cadLivro;
import descartados.livDescartado;
import database.conexaoMySQL;

public class cadLivroDAO {
    Connection dbconn = null; // variavel de conexao
    PreparedStatement pstm = null; // variavel que serve para montar a query sem a necessidade de concatenar

    public void save(cadLivro livro) {
        // insercao dos dados no bd
        String sql = "insert into tb_livros (liv_isbn,liv_titulo,liv_autor,liv_Status,liv_cat_id) values (?,?,?,?,?)";

        // bloco de codigo que vai tratar as excecoes do codigo
        try {

            dbconn = conexaoMySQL.createConnectionToMySQL(); // reestabelece a conexao com o banco
            // vai inserir os dados no bd na ordem q esta aqui
            pstm = (PreparedStatement) dbconn.prepareStatement(sql);
            pstm.setObject(1, livro.getIsbn());
            pstm.setString(2, livro.getTitulo());
            pstm.setString(3, livro.getAutor());
            pstm.setString(4, livro.getStatus());
            pstm.setInt(5, livro.getIdCategoria());
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

    public void livIndisponivel(cadLivro livro,cadEmp emprestimo) {
        String sql ="update tb_livros set liv_status='Indisponivel' where liv_isbn=?";

        try {
            dbconn = conexaoMySQL.createConnectionToMySQL(); // reestabelece a conexao com o banco
            // vai inserir os dados no bd na ordem q esta aqui
            pstm = (PreparedStatement) dbconn.prepareStatement(sql);
            pstm.setObject(1, emprestimo.getIsbn());
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
    public void livDisponivel(cadLivro livro,cadDev devolucao) {
        String sql ="update tb_livros set liv_status='Disponivel' where liv_isbn=?";

        try {
            dbconn = conexaoMySQL.createConnectionToMySQL(); // reestabelece a conexao com o banco
            // vai inserir os dados no bd na ordem q esta aqui
            pstm = (PreparedStatement) dbconn.prepareStatement(sql);
            pstm.setObject(1, devolucao.getIsbn());
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
    public void livDescartado(cadLivro livro, livDescartado descartado) {
        String sql ="update tb_livros set liv_status='Descartado' where liv_isbn=?";

        try {
            dbconn = conexaoMySQL.createConnectionToMySQL(); // reestabelece a conexao com o banco
            // vai inserir os dados no bd na ordem q esta aqui
            pstm = (PreparedStatement) dbconn.prepareStatement(sql);
            pstm.setObject(1, descartado.getLivDescartado());
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

    
    public void exibirDados2() {
        String sql ="select liv_isbn,liv_autor,liv_titulo,liv_status,cat_categoria as categoria from tb_livros join  tb_categorias on liv_cat_id = cat_id where liv_isbn = ?";

        try {

            
            dbconn = conexaoMySQL.createConnectionToMySQL(); // reestabelece a conexao com o banco
            // vai inserir os dados no bd na ordem q esta aqui
            pstm = (PreparedStatement) dbconn.prepareStatement(sql);
           
          
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
