package ementor.plus;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class ListarProfessores extends javax.swing.JFrame {

    public ListarProfessores() {
        initComponents();
        
        ConexaoMysql conexao = new ConexaoMysql();
        
        Professor professor_atual = new Professor();
        
        List<Professor> professores = new ArrayList<Professor>();
        
        DefaultTableModel Tabela = (DefaultTableModel) TabelaProfessores.getModel();
        Tabela.getDataVector().removeAllElements();//Para limpar as linhas da tabela

        professores = conexao.ListarProfessores();

        for (int i = 0; i < professores.size(); i++){
            professor_atual = professores.get(i);

            Tabela.addRow(new Object[]{professor_atual.getNome(), professor_atual.getDataNascimento(),
                                        professor_atual.getCPF(),professor_atual.getTelefone(),
                                        professor_atual.getSalarioBruto(), 
                                        professor_atual.getDataAdmissao()});

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jtable = new javax.swing.JScrollPane();
        TabelaProfessores = new javax.swing.JTable();
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

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("LISTA DE PROFESSORES");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jtable, javax.swing.GroupLayout.DEFAULT_SIZE, 802, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(302, 302, 302))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                .addComponent(jtable, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListarProfessores().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TabelaProfessores;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jtable;
    // End of variables declaration//GEN-END:variables
}
