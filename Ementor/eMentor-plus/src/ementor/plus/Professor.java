
package ementor.plus;

public class Professor extends Pessoa{
    String dataAdmissao;
    double salarioBruto;

    public Professor() {
    }

    public Professor(String dataAdmissao, double salarioBruto) {
        this.dataAdmissao = dataAdmissao;
        this.salarioBruto = salarioBruto;
    }

    public Professor(String dataAdmissao, double salarioBruto, String nome, String dataNascimento, String CPF, String telefone) {
        super(nome, dataNascimento, CPF, telefone);
        this.dataAdmissao = dataAdmissao;
        this.salarioBruto = salarioBruto;
    }
    
    public void setDados(String dataAdmissao, double salarioBruto, String nome, String dataNascimento, String CPF, String telefone) {
        
        super.setNome(nome);
        super.setDataNascimento(dataNascimento);
        super.setCPF(CPF);
        super.setTelefone(telefone);
        
        this.dataAdmissao = dataAdmissao;
        this.salarioBruto = salarioBruto;
    }
    
    public String getDataAdmissao(){
        return this.dataAdmissao;
    }
    
    public double getSalarioBruto(){
        return this.salarioBruto;
    }
    
    public void imprimir(){
        
        // Desconto INSS
        double salarioLiquido = this.salarioBruto - (this.salarioBruto * 14)/100;
        
        // Desconto IRPF
        if(this.salarioBruto >= 5000)
            salarioLiquido -= (this.salarioBruto * 22.5)/100;
        
        
        System.out.println("Professor: " + super.getNome());
        System.out.println("Data de nascimento: " + super.getDataNascimento());
        System.out.println("CPF: " + super.getCPF());
        System.out.println("Telefone: " + super.getTelefone());
        System.out.println("Data admissao: " + this.dataAdmissao);
        System.out.println("Salário Bruto: R$" + this.salarioBruto);
        
    }
}
