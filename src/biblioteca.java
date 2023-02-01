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
  public static void main(String[] args) throws Exception{
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

       System.out.println("Qual ação voce deseja realizar? ");
       System.out.println("1- Cadastrar Usuario");
       System.out.println("2- Cadastrar Livro");
       System.out.println("3- Cadastrar Emprestimo");
       System.out.println("4- Cadastrar Devolucao");
       System.out.println("5- Cadastrar categoria");
       System.out.println("6- deletar livro");
       System.out.println("7- deletar Usuario");
       System.out.println("8- Editar usuario");
       System.out.println("9-Editar Livro");
       System.out.println("10- Sair do menu");
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
      boolean salvaEmprestimo = emprestimoDAO.save(emprestimo);
      if(salvaEmprestimo){
        livroDAO.livIndisponivel(livro, emprestimo);
        emprestimo.exibirDados();
      }
     
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
    int livDeletado = sc.nextInt(); 
   

    livroDescartado.setLivDescartado(livDeletado);
    livroDAO.livDescartado(livro,livroDescartado);
  
    } else if(aux == 7){

      System.out.println("Digite a matricula do aluno que deseja deletar");
      BigInteger userDeletado = sc.nextBigInteger(); 
     
  
      userDescartado.setUserDescartado(userDeletado);
      userDAO.userDeletado(userDescartado);
      
    }else if (aux == 8) {

      System.out.println("Digite a matricula do aluno que deseja editar");
      BigInteger matriculaold = sc.nextBigInteger();
      sc.nextLine();

      System.out.println("Digite o novo nome do Usuario");
      String nomeup = sc.nextLine();

      userup.setOldMat(matriculaold);
      userup.setNome(nomeup);
      userDAO.upUser(userup); // salva as informacoes no banco
  }
  else if (aux == 9) {

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
   else if (aux == 10){
    System.exit(0);
  }
}
}

// private String getOriginalTaskId(final String taskId) {
//   String sql = String.format("SELECT original_task_id FROM %s WHERE task_id = '%s' and state='%s' LIMIT 1", TABLE_JOB_STATUS_TRACE_LOG, taskId, State.TASK_STAGING);
//   String result = "";