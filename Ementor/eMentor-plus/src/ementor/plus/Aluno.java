
package ementor.plus;

public class Aluno extends Pessoa{
    String matricula;
    int periodo;

    public Aluno() {
    }

    public Aluno(String matricula, int periodo) {
        this.matricula = matricula;
        this.periodo = periodo;
    }

    public Aluno(String matricula, int periodo, String nome, String dataNascimento, String CPF, String telefone) {
        super(nome, dataNascimento, CPF, telefone);
        this.matricula = matricula;
        this.periodo = periodo;
    }
    
    public void setDados(String matricula, int periodo, String nome, String dataNascimento, String CPF, String telefone) {
        
        super.setNome(nome);
        super.setDataNascimento(dataNascimento);
        super.setCPF(CPF);
        super.setTelefone(telefone);
        
        this.matricula = matricula;
        this.periodo = periodo;
    }
    
    
    public String getMatricula(){
        return this.matricula;
    }
    
    public int getPeriodo(){
        return this.periodo;
    }
    
    @Override
    public String getCPF(){
        return this.CPF;
    }

    public void imprime(){
        
        System.out.println("Aluno: " + super.getNome());
        System.out.println("Data de nascimento: " + super.getDataNascimento());
        System.out.println("CPF: " + super.getCPF());
        System.out.println("Telefone: " + super.getTelefone());
        System.out.println("Matricula: " + this.matricula);
        System.out.println("Periodo: " + this.periodo);
    
    }

    void setPeriodo(String periodo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
