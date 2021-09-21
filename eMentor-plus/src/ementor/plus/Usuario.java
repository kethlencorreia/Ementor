
package ementor.plus;

public class Usuario extends Pessoa{
    private String nomeUser, senha;
    private int nivelAcesso;

    public Usuario() {
    }

    public Usuario(String nomeUser, String senha, int nivelAcesso) {
        this.nomeUser = nomeUser;
        this.senha = senha;
        this.nivelAcesso = nivelAcesso;
    }

    public Usuario(String nomeUser, String senha, int nivelAcesso, String nome, String dataNascimento, String CPF, String telefone) {
        super(nome, dataNascimento, CPF, telefone);
        this.nomeUser = nomeUser;
        this.senha = senha;
        this.nivelAcesso = nivelAcesso;
    }

    
    public void setDados(String nomeUser, String senha, int nivelAcesso, String nome, String dataNascimento, String CPF, String telefone){
    
        super.setNome(nome);
        super.setDataNascimento(dataNascimento);
        super.setCPF(CPF);
        super.setTelefone(telefone);
        this.nomeUser = nomeUser;
        this.senha = senha;
        this.nivelAcesso = nivelAcesso;
   
    }
    
    public String getNomeUsuario(){
        return this.nomeUser;
    }
    
    public String getSenha(){
        return this.senha;
    }
    
    
    
}
