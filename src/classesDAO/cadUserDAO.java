package classesDAO;

import database.conexaoMySQL;
import descartados.userDescartados;
import updates.upUser;
import java.math.BigInteger;

import java.sql.*; // importa todas as classes necessaria do sql

import cadastros.cadUser;

public class cadUserDAO {

    Connection dbconn = null; // variavel de conexao
    PreparedStatement pstm = null; // variavel que serve para montar a query sem a necessidade de concatenar

    public void save(cadUser user) {
        // insercao dos dados no bd
        String sql = "insert into tb_users (use_matricula,use_nome) values (?,?)";

        // bloco de codigo que vai tratar as excecoes do codigo
        try {

            dbconn = conexaoMySQL.createConnectionToMySQL(); // reestabelece a conexao com o banco
            // vai inserir os dados no bd na ordem q esta aqui
            pstm = (PreparedStatement) dbconn.prepareStatement(sql);
            pstm.setObject(1, user.getMatricula());
            pstm.setString(2, user.getNome());
            pstm.executeUpdate();
            System.out.println(pstm);
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

    public void userDeletado(userDescartados userDel) {
        String sql = "update tb_users set use_status='Inativo' where use_matricula=?";

        try {
            dbconn = conexaoMySQL.createConnectionToMySQL(); // reestabelece a conexao com o banco
            // vai inserir os dados no bd na ordem q esta aqui
            pstm = (PreparedStatement) dbconn.prepareStatement(sql);
            pstm.setObject(1, userDel.getUserDescartado());
            pstm.executeUpdate();
            int countDelUser = pstm.getUpdateCount();

            if (countDelUser == 0) {
                System.out.println("O usuario não existe!!");
            } else {
                System.out.println("O usuario deletado com sucesso!!");
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

    public void upUser(upUser userup) {
        String sql = "update tb_users set use_nome = ? , use_status = 'Ativo' where use_matricula= ?";

        try {
            dbconn = conexaoMySQL.createConnectionToMySQL(); // reestabelece a conexao com o banco
            // vai inserir os dados no bd na ordem q esta aqui
            pstm = (PreparedStatement) dbconn.prepareStatement(sql);
            pstm.setString(1, userup.getNome());
            pstm.setObject(2, userup.getOldMat());
            pstm.executeUpdate();
            int countupUser = pstm.getUpdateCount();

            if (countupUser == 0) {
                System.out.println(" O usuario não existe!!");
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

    public void exibirUsers() {
        String sql = "SELECT * FROM tb_users";
        try {
            dbconn = conexaoMySQL.createConnectionToMySQL();
            pstm = (PreparedStatement) dbconn.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                cadUser usuario = new cadUser();
                
                usuario.setMatricula(rs.getObject("use_matricula", BigInteger.class));
                usuario.setNome(rs.getString("use_nome"));
                usuario.setStatus(rs.getString("use_status"));

                System.out.println(
                        "| \u001b[32;1mMatricula:\u001b[m " + usuario.getMatricula() + " |  \u001b[32;1mNome:\u001b[m "
                                + usuario.getNome() + " | \u001b[32;1mStatus:\u001b[m " + usuario.getStatus() + " |");
            }

        } catch (Exception error) {

            error.printStackTrace();
        }
    }

}
