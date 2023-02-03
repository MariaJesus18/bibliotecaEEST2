package classesDAO;

import java.sql.*; // importa todas as classes necessaria do sql
import cadastros.cadDev;
import cadastros.cadEmp;
import cadastros.cadLivro;
import descartados.livDescartado;
import updates.upLivro;
import database.conexaoMySQL;
import java.math.BigInteger;

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

    public void livIndisponivel(cadLivro livro, cadEmp emprestimo) {
        String sql = "update tb_livros set liv_status='Indisponivel' where liv_isbn=?";

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

    public void livDisponivel(cadLivro livro, cadDev devolucao) {
        String sql = "update tb_livros set liv_status='Disponivel' where liv_isbn=?";

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
        String sql = "update tb_livros set liv_status='Descartado' where liv_isbn=?";

        try {
            dbconn = conexaoMySQL.createConnectionToMySQL(); // reestabelece a conexao com o banco
            // vai inserir os dados no bd na ordem q esta aqui
            pstm = (PreparedStatement) dbconn.prepareStatement(sql);
            pstm.setObject(1, descartado.getLivDescartado());
            pstm.execute();
            int countDelLiv = pstm.getUpdateCount();

            if (countDelLiv == 0) {
                System.out.println(" O livro não existe!!");
            } else {
                System.out.println("livro deletado com sucesso!!");
            }

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

    public void upLivro(upLivro livroup) {
        String sql = "update tb_livros set liv_titulo = ? ,liv_autor = ?, liv_status = 'Disponivel', liv_cat_id = ? where liv_isbn= ?";

        try {
            dbconn = conexaoMySQL.createConnectionToMySQL(); // reestabelece a conexao com o banco
            // vai inserir os dados no bd na ordem q esta aqui
            pstm = (PreparedStatement) dbconn.prepareStatement(sql);
            pstm.setString(1, livroup.getTitulo());
            pstm.setObject(2, livroup.getAutor());
            pstm.setObject(3, livroup.getIdCategoria());
            pstm.setObject(4, livroup.getOldisbn());
            pstm.executeUpdate();
            int countupLiv = pstm.getUpdateCount();

            if (countupLiv == 0) {
                System.out.println(" O livro não existe!!");
            } else {
                System.out.println("livro editado ");
            }
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

    public void exibirLivros() {
        String sql = "SELECT * FROM tb_livros";
        try {
            dbconn = conexaoMySQL.createConnectionToMySQL();
            pstm = (PreparedStatement) dbconn.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                cadLivro livro = new cadLivro();
                livro.setIsbn(rs.getObject("liv_isbn", BigInteger.class));

                livro.setAutor(rs.getString("liv_autor"));
                livro.setStatus(rs.getString("liv_status"));
                livro.setTitulo(rs.getString("liv_titulo"));
                livro.setIdCategoria(rs.getInt("liv_cat_id"));

                System.out.println("| \u001b[32;1mTitulo:\u001b[m" + livro.getTitulo()
                        + " | \u001b[32;1mAutor:\u001b[m " + livro.getAutor() + " | \u001b[32;1mIsbn:\u001b[m "
                        + livro.getIsbn() + " | \u001b[32;1mcategoria:\u001b[m" + livro.getIdCategoria()
                        + " | \u001b[32;1mStatus:\u001b[m" + livro.getStatus() + " |");
            }

        } catch (Exception error) {

            error.printStackTrace();
        }
    }

}
