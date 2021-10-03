
package ementor.plus;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class ConexaoMysql {
    
    // Seção Atributos/Variaveis iniciais
    
    private String caminho = "localhost"; //  indica que usaremos o serve na maquina local
    private String porta = "3306"; // Porta padrão do MySQL
    private String nome = "ementor";
    private String usuario = "user";
    private String senha = "user";
    private String FusoHoraio = "?useTimeZone=true&serverTimezone=UTC"; /*
        ajustar horario com a Oracle
    */
    private String URL = "jdbc:mysql://"+caminho+":"+porta+"/"+nome+FusoHoraio;
    
    
    public ConexaoMysql(){
        
    }
    
    public Connection realizaConexaoMySQL(){
        try{
            return DriverManager.getConnection(URL, usuario, senha);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Algum imprevisto ocorreu: " + e, "erro", 0);
            return null;
        }
    }
    
    public void desconectaMySQL(Connection conexao){
         try{
            if(conexao != null)
                conexao.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Algum imprevisto ocorreu: " + e, "erro", 0);
        }
    }
    
    
    public Boolean logar(String nome, String senha){
        Connection conexao = realizaConexaoMySQL();
        Boolean encontrado = false;
        
        String StringSQL = "SELECT * FROM ementor.Usuarios WHERE (nome, senha) = (?,?)";
    
        try{
            PreparedStatement atuadorLogin = conexao.prepareStatement(StringSQL);
            
            atuadorLogin.setString(1, nome);
            atuadorLogin.setString(2, senha);
            
            ResultSet resultado = atuadorLogin.executeQuery();
          
            // verifica se houve resultados na busca
            if(resultado.isBeforeFirst())
                encontrado = true;
            
            resultado.close();
            atuadorLogin.close();
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Algum imprevisto ocorreu: " + e, "erro", 0);
        }
        
        return encontrado;
    }
    
    public boolean cadastrarAluno(String nome, String CPF, String dataNascimento, String telefone,
                                String matricula, int periodo){
        
        Connection conexao = realizaConexaoMySQL();
        
        String inserePessoa = "INSERT INTO ementor.Pessoas (nome, dataNascimento,"
                + "CPF, telefone) VALUES (?,?,?,?)";
        
        String insereAluno = "INSERT INTO ementor.Alunos (CPF, matricula, periodo)"
                + "VALUES (?,?,?)";
        
        try{
            
            PreparedStatement preparadorPessoa = conexao.prepareStatement(inserePessoa);
            PreparedStatement preparadorAluno = conexao.prepareStatement(insereAluno);
            
            preparadorPessoa.setString(1, nome);
            preparadorPessoa.setString(2, dataNascimento);
            preparadorPessoa.setString(3, CPF);
            preparadorPessoa.setString(4, telefone);
            
            preparadorAluno.setString(1, CPF);
            preparadorAluno.setString(2, matricula);
            preparadorAluno.setInt(3, periodo);
            
            preparadorPessoa.execute();
            preparadorAluno.execute();
            
            return true;
        }catch(java.sql.SQLIntegrityConstraintViolationException e){
            System.out.println(e.getMessage());
            if(e.getMessage().substring(41).equals("'Pessoas.PRIMARY'"))
                JOptionPane.showMessageDialog(null, "O CPF informado já foi cadastrado.","erro", 0);
            else{
            
                JOptionPane.showMessageDialog(null, "A matricula informada já foi cadastrada.","erro", 0);
                
                
                String removePessoa = "delete from ementor.Pessoas where CPF = '"+CPF+"'";
                
                try{
                    PreparedStatement preparadorPessoa = conexao.prepareStatement(removePessoa);
                    preparadorPessoa.execute();
                }catch(SQLException b){
                    JOptionPane.showMessageDialog(null, "Algum imprevisto ocorreu: " + b, "erro", 0);
                }
            
            }
            }catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Algum imprevisto ocorreu: " + e, "erro", 0);
        }
        
        desconectaMySQL(conexao);
        
        return false;
        
    }
    
    public boolean cadastrarProfessor(String nome, String CPF, String dataNascimento, String telefone,
                                double salario, String dataAdmissao){
        
        Connection conexao = realizaConexaoMySQL();
        
        String inserePessoa = "INSERT INTO ementor.Pessoas (nome, dataNascimento,"
                + "CPF, telefone) VALUES (?,?,?,?)";
        
        String insereProfessor = "INSERT INTO ementor.Professores (CPF, salario, dataAdmissao)"
                + "VALUES (?,?,?)";
        
        try{
            
            PreparedStatement preparadorPessoa = conexao.prepareStatement(inserePessoa);
            PreparedStatement preparadorProfessor = conexao.prepareStatement(insereProfessor);
            
            preparadorPessoa.setString(1, nome);
            preparadorPessoa.setString(2, dataNascimento);
            preparadorPessoa.setString(3, CPF);
            preparadorPessoa.setString(4, telefone);
            
            preparadorProfessor.setString(1, CPF);
            preparadorProfessor.setDouble(2, salario);
            preparadorProfessor.setString(3, dataAdmissao);
            
            preparadorPessoa.execute();
            preparadorProfessor.execute();
            
            return true;
        }catch(java.sql.SQLIntegrityConstraintViolationException e){
            JOptionPane.showMessageDialog(null, "O CPF informado já foi cadastrado.","erro", 0);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Algum imprevisto ocorreu: " + e, "erro", 0);
        }
        
        desconectaMySQL(conexao);
        
        return false;
        
    }
    
    public List<Aluno> ListarAlunos(){
        List<Aluno> alunos = new ArrayList<Aluno>();
        
        Connection conexao = realizaConexaoMySQL();
        
        String selecionaAlunos = "SELECT * FROM ementor.Alunos, ementor.Pessoas"
                + " WHERE Pessoas.CPF = Alunos.CPF";

        try {
            PreparedStatement preparadorAluno = conexao.prepareStatement(selecionaAlunos);

            ResultSet resultadoAluno = preparadorAluno.executeQuery();
            
            while(resultadoAluno.next()){

                Aluno aluno = new Aluno(resultadoAluno.getString("matricula"),
                        resultadoAluno.getInt("periodo"),
                        resultadoAluno.getString("nome"),
                        resultadoAluno.getString("dataNascimento"),
                        resultadoAluno.getString("CPF"),
                        resultadoAluno.getString("telefone"));
            
                alunos.add(aluno);
            }
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Algum imprevisto ocorreu: " + e, "erro", 0);
        }

        return alunos;
    }
    
    public List<Professor> ListarProfessores(){
        List<Professor> professores = new ArrayList<Professor>();
        
        Connection conexao = realizaConexaoMySQL();
        
        String selecionaProfessores = "SELECT * FROM ementor.Professores, ementor.Pessoas"
                + " Where Professores.CPF = Pessoas.CPF";

        try {
            PreparedStatement preparadorProfessor = conexao.prepareStatement(selecionaProfessores);

            ResultSet resultadoProfessor = preparadorProfessor.executeQuery();
            
            while(resultadoProfessor.next()){
                    Professor professor = new Professor(resultadoProfessor.getString("dataAdmissao"),
                                resultadoProfessor.getDouble("salario"),
                                resultadoProfessor.getString("nome"),
                                resultadoProfessor.getString("dataNascimento"),
                                resultadoProfessor.getString("CPF"),
                                resultadoProfessor.getString("telefone"));
                    professores.add(professor);
            }
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Algum imprevisto ocorreu: " + e, "erro", 0);
        }

        return professores;
    }
    
    public Aluno buscaAluno(String CPF){
        Aluno resultado =  new Aluno();
        resultado = null;
        Connection conexao = realizaConexaoMySQL();
        
        String sql = "SELECT * FROM ementor.Pessoas, ementor.Alunos WHERE Alunos.CPF = Pessoas.CPF AND Alunos.CPF='"+ CPF + "';";
        
        try{
            PreparedStatement preparador = conexao.prepareStatement(sql);

            ResultSet res = preparador.executeQuery();

            while (res.next()){
                Aluno aluno = new Aluno();
                aluno.setDados(
                        res.getString("matricula"),
                        res.getInt("periodo"),
                        res.getString("nome"),
                        res.getString("dataNascimento"),
                        res.getString("CPF"),
                        res.getString("telefone"));                                                                  
                resultado = aluno;
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Algum imprevisto ocorreu: "+e,"erro",0);
        }
        
        return resultado;
        
    }
    
    public boolean editarAluno(String nome, String CPF, String dataNascimento, String telefone,
                                String matricula, int periodo){
        
        Connection conexao = realizaConexaoMySQL();
        
        String sqlPessoa = "UPDATE ementor.Pessoas SET nome = ?, dataNascimento = ?, telefone = ? WHERE CPF=?";
        String sqlAluno =  "UPDATE ementor.Alunos SET matricula = ?, periodo = ? WHERE CPF=?";

        try{
            PreparedStatement preparadorPessoa = conexao.prepareStatement(sqlPessoa);
            PreparedStatement preparadorAluno = conexao.prepareStatement(sqlAluno);
            
            preparadorPessoa.setString(1, nome);
            preparadorPessoa.setString(2, dataNascimento);
            preparadorPessoa.setString(3, telefone);
            preparadorPessoa.setString(4, CPF);
            
            preparadorAluno.setString(1,matricula);
            preparadorAluno.setInt(2, periodo);
            preparadorAluno.setString(3, CPF);
            
            preparadorPessoa.execute();
            preparadorAluno.execute();
            
            
            return true;
            
        }catch(java.sql.SQLIntegrityConstraintViolationException e){
            
            }catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Algum imprevisto ocorreu: " + e, "erro", 0);
        }
        
        desconectaMySQL(conexao);
        
        return false;
        
    }
    public Professor buscaProfessor(String CPF){
        Professor resultado =  new Professor();
        Connection conexao = realizaConexaoMySQL();
        resultado = null;
        
        String sql = "SELECT * FROM ementor.Pessoas, ementor.Professores WHERE Professores.CPF = Pessoas.CPF AND Professores.CPF='"+ CPF + "';";
        
        try{
            PreparedStatement preparador = conexao.prepareStatement(sql);
            
            ResultSet res = preparador.executeQuery();
            
            while (res.next()){
                Professor professor = new Professor();
                professor.setDados(
                        res.getString("dataAdmissao"),
                        res.getDouble("salario"),
                        res.getString("nome"), 
                        res.getString("dataNascimento"), 
                        res.getString("CPF"), 
                        res.getString("telefone"));                                                                  
                resultado = professor;
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Algum imprevisto ocorreu: "+e,"erro",0);
        }
        
        
        return resultado;
        
    }
    
    public boolean editarProfessor(String nome, String CPF, String dataNascimento, String telefone,
                                String dataAdmissao, double salario){
        
        Connection conexao = realizaConexaoMySQL();
        
        String sqlPessoa = "UPDATE ementor.Pessoas SET nome = ?, dataNascimento = ?, telefone = ? WHERE CPF=?";
        String sqlProfessor =  "UPDATE ementor.Professores SET dataAdmissao = ?, salario = ? WHERE CPF=?";

        try{
            PreparedStatement preparadorPessoa = conexao.prepareStatement(sqlPessoa);
            PreparedStatement preparadorProfessor = conexao.prepareStatement(sqlProfessor);
            
            preparadorPessoa.setString(1, nome);
            preparadorPessoa.setString(2, dataNascimento);
            preparadorPessoa.setString(3, telefone);
            preparadorPessoa.setString(4, CPF);
            
            preparadorProfessor.setString(1,dataAdmissao);
            preparadorProfessor.setDouble(2, salario);
            preparadorProfessor.setString(3, CPF);
            
            preparadorPessoa.execute();
            preparadorProfessor.execute();
            
            
            return true;
            
        }catch(java.sql.SQLIntegrityConstraintViolationException e){
            
            }catch(SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Algum imprevisto ocorreu: " + e, "erro", 0);
        }
        
        desconectaMySQL(conexao);
        
        return false;
        
    }
    
}

