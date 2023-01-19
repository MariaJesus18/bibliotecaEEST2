import java.math.BigInteger;
import java.util.Scanner;

public class biblioteca {
  public static void main(String[] args) throws Exception{
      Scanner sc = new Scanner(System.in);
      conexaoMySQL db = new conexaoMySQL(); // objeto que faz a conexao com o bd

      cadDev devolucao = new cadDev();
      cadEmp emprestimo = new cadEmp();
      cadLivro livro = new cadLivro();
      cadUser usuario = new cadUser();
      cadCategorias categoria = new cadCategorias();
      delLivro delLivro = new delLivro();
      delUser delUser = new delUser();
        
      // instanciacao dos objetos do tipo DAO
      cadLivroDAO livroDAO = new cadLivroDAO();
      cadUserDAO userDAO = new cadUserDAO();
      cadEmpDAO emprestimoDAO = new cadEmpDAO();
      cadDevDAO devolucaoDAO = new cadDevDAO();
      cadCategoriaDAO categoriaDAO = new cadCategoriaDAO();

       System.out.println("Qual ação voce deseja realizar? ");
       System.out.println("1- Cadastrar Usuario");
       System.out.println("2- Cadastrar Livro");
       System.out.println("3- Cadastrar Emprestimo");
       System.out.println("4- Cadastrar Devolucao");
       System.out.println("5- Cadastrar categoria");
       System.out.println("6- deletar livro");
       System.out.println("7- deletar Usuario");
       int aux = sc.nextInt();

       if(aux == 1){
           
        System.out.println("Digite a matricula");
        BigInteger matricula = sc.nextBigInteger();
        sc.nextLine();

        System.out.println("Digite o nome do Usuario");
        String nome = sc.nextLine();

       usuario.setNome(nome);
       usuario.setMatricula(matricula);
       userDAO.save(usuario); // salva as informacoes no banco
       usuario.exibirDados();
  
    } else if(aux==2){

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
       livroDAO.save(livro);  // salva as informacoes no banco
       livroDAO.exibirDados2();
  
    }else if(aux == 3 ){

      
      System.out.println("Digite a matricula do Aluno");
      BigInteger matriculaAl = sc.nextBigInteger();
      sc.nextLine(); 
       
      System.out.println("Digite o numero de serie do livro");
      BigInteger isbn = sc.nextBigInteger();

      emprestimo.setMatriculaAl(matriculaAl);
      emprestimo.setIsbn(isbn);
      String disp = "Indisponivel";

      emprestimoDAO.save(emprestimo);  // salva as informacoes no banco 
      livroDAO.livIndisponivel(livro, emprestimo);
      emprestimo.exibirDados(); 

    }else if(aux == 4 ){
      
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
      devolucaoDAO.save(devolucao);  // salva as informacoes no banco
      livroDAO.livDisponivel(livro, devolucao);
      devolucao.exibirDados();    
    
    }else if(aux == 5){

      System.out.println("Digite o nome da categoria");
      String category = sc.next(); 
     

      categoria.setCategoria(category);
      categoriaDAO.save(categoria); 
      categoria.exibirDados();
   
  }else if(aux == 6){

    System.out.println("Digite o isbn do livro que deseja deletar");
    int idLivro = sc.nextInt(); 
   

    delLivro.setDelLivro(idLivro);
    livroDAO.deleteLiv(delLivro);
  
    } else if(aux == 7){

      System.out.println("Digite a matricula do aluno que deseja deletar");
      BigInteger idUser = sc.nextBigInteger(); 
     
  
      delUser.setDelUser(idUser);
      livroDAO.deleteUser(delUser);
    
      }  
  } 
}
