package ementor.plus;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class ListarProfessores extends javax.swing.JFrame {

    public ListarProfessores() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jtable = new javax.swing.JScrollPane();
        TabelaProfessores = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        TabelaProfessores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nome", "Data de nascimento", "CPF", "Telefone", "Salario", "Data de admissão"
            }
        ));
        jtable.setViewportView(TabelaProfessores);

        jButton1.setText("Gerar Lista");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("LISTA DE PROFESSORES");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtable, javax.swing.GroupLayout.DEFAULT_SIZE, 599, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(217, 217, 217))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(7, 7, 7)
                .addComponent(jtable, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ConexaoMysql conexao = new ConexaoMysql();
        
        Professor professor_atual = new Professor();
        Pessoa pessoa_atual = new Pessoa();
        
        List<Professor> professores = new ArrayList<Professor>();
        List<Pessoa> pessoas = new ArrayList<Pessoa>();
        
        DefaultTableModel Tabela = (DefaultTableModel) TabelaProfessores.getModel();
        Tabela.getDataVector().removeAllElements();//Para limpar as linhas da tabela

        professores = conexao.ListarProfessores();
        pessoas = conexao.ListarPessoas();

        for (int i = 0; i < professores.size(); i++){
            professor_atual = professores.get(i);
            for(int j = 0; j< pessoas.size(); j++){
                pessoa_atual = pessoas.get(j);

                System.out.println(professor_atual.getCPF());
                System.out.println(pessoa_atual.getCPF());
                
                if(professor_atual.getCPF().equals(pessoa_atual.getCPF())){

                    Tabela.addRow(new Object[]{pessoa_atual.getNome(), pessoa_atual.getDataNascimento(),
                                                pessoa_atual.getCPF(),pessoa_atual.getTelefone()});
                    
                    Tabela.addRow(new Object[]{professor_atual.getSalarioBruto(), professor_atual.getDataAdmissao()});
                }
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListarProfessores().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TabelaProfessores;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jtable;
    // End of variables declaration//GEN-END:variables
}
