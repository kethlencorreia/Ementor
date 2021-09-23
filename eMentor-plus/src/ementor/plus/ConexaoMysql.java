
package ementor.plus;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
    
    public void cadastrarAluno(String nome, String CPF, String dataNascimento, String telefone,
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
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Algum imprevisto ocorreu: " + e, "erro", 0);
        }
        
        desconectaMySQL(conexao);
        
    }
    
    
}
