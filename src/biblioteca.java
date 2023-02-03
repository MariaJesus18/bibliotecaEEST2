import java.math.BigInteger;
import java.util.Scanner;
import cadastros.cadCategorias;
import cadastros.cadDev;
import cadastros.cadEmp;
import cadastros.cadLivro;
import cadastros.cadUser;
import classesDAO.cadCategoriaDAO;
import classesDAO.cadDevDAO;
import classesDAO.cadEmpDAO;
import classesDAO.cadLivroDAO;
import classesDAO.cadUserDAO;
import descartados.livDescartado;
import descartados.userDescartados;
import updates.upUser;
import updates.upLivro;

public class biblioteca {
  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(System.in);

    cadDev devolucao = new cadDev();
    cadEmp emprestimo = new cadEmp();
    cadLivro livro = new cadLivro();
    cadUser usuario = new cadUser();
    cadCategorias categoria = new cadCategorias();
    livDescartado livroDescartado = new livDescartado();
    userDescartados userDescartado = new userDescartados();
    upUser userup = new upUser();
    upLivro livroup = new upLivro();

    // instanciacao dos objetos do tipo DAO
    cadLivroDAO livroDAO = new cadLivroDAO();
    cadUserDAO userDAO = new cadUserDAO();
    cadEmpDAO emprestimoDAO = new cadEmpDAO();
    cadDevDAO devolucaoDAO = new cadDevDAO();
    cadCategoriaDAO categoriaDAO = new cadCategoriaDAO();

    System.out.println("1- Gerenciar Cadastros");
    System.out.println("2- Gerenciar Emprestimos");
    System.out.println("3- Deletar");
    System.out.println("4- Editar");
    System.out.println("5- Listar");
    System.out.println("6- Sair do menu\u001b[m");
    int aux1 = sc.nextInt();

    if (aux1 == 1) {

      System.out.println("\u001b[32;1mQual ação voce deseja realizar?");
      System.out.println("1- Cadastrar Usuario");
      System.out.println("2- Cadastrar Livro");
      System.out.println("3- Cadastrar categoria\u001b[m");
      int aux = sc.nextInt();

      if (aux == 1) {

        System.out.println("Digite a matricula");
        BigInteger matricula = sc.nextBigInteger();
        sc.nextLine();

        System.out.println("Digite o nome do Usuario");
        String nome = sc.nextLine();

        usuario.setNome(nome);
        usuario.setMatricula(matricula);
        userDAO.save(usuario); // salva as informacoes no banco
        userDAO.exibirUsers();

      } else if (aux == 2) {

        System.out.println("Digite o numero de serie");
        BigInteger isbn = sc.nextBigInteger();
        sc.nextLine();

        System.out.println("Digite o titulo do Livro");
        String titulo = sc.nextLine();

        System.out.println("Digite o autor");
        String autor = sc.nextLine();

        System.out.println("Digite o id da categoria do livro");
        int cat = sc.nextInt();

        livro.setIsbn(isbn);
        livro.setTitulo(titulo);
        livro.setAutor(autor);
        livro.setIdCategoria(cat);
        livro.setStatus("Disponivel");
        livroDAO.save(livro); // salva as informacoes no banco
        livroDAO.exibirLivros();

      } else if (aux == 3) {

        System.out.println("Digite o nome da categoria");
        String category = sc.next();

        categoria.setCategoria(category);
        categoriaDAO.save(categoria);
        categoriaDAO.exibirCategorias();

      }
    } 
    else if (aux1 == 2) {
        System.out.println("\u001b[32;1mQual ação voce deseja realizar?");
        System.out.println("1- Cadastrar Emprestimo");
        System.out.println("2- Cadastrar Devolucao\u001b[m");
        int aux = sc.nextInt();

        if (aux == 1) {
            System.out.println("Digite a matricula do Aluno");
            BigInteger matriculaAl = sc.nextBigInteger();
            sc.nextLine();

            System.out.println("Digite o numero de serie do livro");
            BigInteger isbn = sc.nextBigInteger();

            emprestimo.setMatriculaAl(matriculaAl);
            emprestimo.setIsbn(isbn);
            boolean salvaEmprestimo = emprestimoDAO.save(emprestimo);
            if (salvaEmprestimo) {
              livroDAO.livIndisponivel(livro, emprestimo);
              emprestimo.exibirDados();
            }

        }
        else if (aux == 2) {

            System.out.println("Digite a matricula do Aluno");
            BigInteger matriculaAl = sc.nextBigInteger();
            sc.nextLine();

            System.out.println("Digite o id do emprestimo");
            int id = sc.nextInt();

            System.out.println("Digite o numero de serie do livro");
            BigInteger isbn = sc.nextBigInteger();

            devolucao.setIdEmp(id);
            devolucao.setMatriculaAl(matriculaAl);
            devolucao.setIsbn(isbn);
            devolucaoDAO.save(devolucao); // salva as informacoes no banco
            livroDAO.livDisponivel(livro, devolucao);
            devolucao.exibirDados();

        }

    } 
    else if (aux1 == 3) {
          System.out.println("\u001b[32;1mQual ação voce deseja realizar?");
          System.out.println("1- deletar livro");
          System.out.println("2- deletar Usuario\u001b[m");
          int aux = sc.nextInt();

          if (aux == 1) {

            System.out.println("Digite o isbn do livro que deseja deletar");
            int livDeletado = sc.nextInt();

            livroDescartado.setLivDescartado(livDeletado);
            livroDAO.livDescartado(livro, livroDescartado);

          } else if (aux == 2) {

            System.out.println("Digite a matricula do aluno que deseja deletar");
            BigInteger userDeletado = sc.nextBigInteger();

            userDescartado.setUserDescartado(userDeletado);
            userDAO.userDeletado(userDescartado);

          }
    } 
    else if (aux1 == 4) {
          System.out.println("\u001b[32;1mQual ação voce deseja realizar?");
          System.out.println("1- Editar usuario");
          System.out.println("2-Editar Livro\u001b[m");
          int aux = sc.nextInt();

          if (aux == 1) {

            System.out.println("Digite a matricula do aluno que deseja editar");
            BigInteger matriculaold = sc.nextBigInteger();
            sc.nextLine();

            System.out.println("Digite o novo nome do Usuario");
            String nomeup = sc.nextLine();

            userup.setOldMat(matriculaold);
            userup.setNome(nomeup);
            userDAO.upUser(userup); // salva as informacoes no banco
          } else if (aux == 2) {

            System.out.println("Digite o isbn do livro q deseja editar");
            BigInteger isbnold = sc.nextBigInteger();
            sc.nextLine();

            System.out.println("Digite o novo titulo do livro");
            String tituloup = sc.nextLine();

            System.out.println("Digite a nova categoria do livro");
            int categoriaup = sc.nextInt();

            livroup.setOldisbn(isbnold);
            livroup.setTitulo(tituloup);
            livroup.setIdCategoria(categoriaup);
            livroDAO.upLivro(livroup); // salva as informacoes no banco
          }
    } 
    else if (aux1 == 5) {
          System.out.println("\u001b[32;1mQual ação voce deseja realizar?");
          System.out.println("1- Listar usuarios");
          System.out.println("2- Listar Livros");
          System.out.println("3- Listar categorias\u001b[m");
          int aux = sc.nextInt();

          if (aux == 1) {
            userDAO.exibirUsers();
          } else if (aux == 2) {
            livroDAO.exibirLivros();
          } else if (aux == 3) {
            categoriaDAO.exibirCategorias();
          }
    }

    else if (aux1 == 6) {

      System.out.println("\u001b[31;1mSistema encerrado\u001b[m");
    }
  }
}
