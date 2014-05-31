/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Interface;

import javax.swing.JOptionPane;

/**
 *
 * @author Rui Mendes
 */
public class Login extends javax.swing.JFrame {
    private AssociacaoEscolasFutebol a;
    /**
     * Creates new form Login
     */
    public Login(AssociacaoEscolasFutebol a) {
        this.a = a;
        initComponents();
        this.setVisible(true);
        Visitante_RadioButton.doClick();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        Dados_Panel = new javax.swing.JPanel();
        NomeDeUtilizador_Label = new javax.swing.JLabel();
        PalavraPasse_PasswordField = new javax.swing.JPasswordField();
        NomeDeUtilizador_TextField = new javax.swing.JTextField();
        PalavraPasse_Label = new javax.swing.JLabel();
        Login_Label = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        Gestor_RadioButton = new javax.swing.JRadioButton();
        Juiz_RadioButton = new javax.swing.JRadioButton();
        Visitante_RadioButton = new javax.swing.JRadioButton();
        Login_Button = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Associação de Escolas de Futebol - Época 13/14");
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N

        Dados_Panel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados de Login:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Trebuchet MS", 0, 12))); // NOI18N

        NomeDeUtilizador_Label.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        NomeDeUtilizador_Label.setText("Nome de Utilizador:");

        PalavraPasse_PasswordField.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N

        NomeDeUtilizador_TextField.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        NomeDeUtilizador_TextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NomeDeUtilizador_TextFieldActionPerformed(evt);
            }
        });

        PalavraPasse_Label.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        PalavraPasse_Label.setText("Palavra-Passe:");

        javax.swing.GroupLayout Dados_PanelLayout = new javax.swing.GroupLayout(Dados_Panel);
        Dados_Panel.setLayout(Dados_PanelLayout);
        Dados_PanelLayout.setHorizontalGroup(
            Dados_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Dados_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Dados_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Dados_PanelLayout.createSequentialGroup()
                        .addComponent(PalavraPasse_Label)
                        .addGap(41, 41, 41))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Dados_PanelLayout.createSequentialGroup()
                        .addComponent(NomeDeUtilizador_Label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(Dados_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(NomeDeUtilizador_TextField)
                    .addComponent(PalavraPasse_PasswordField))
                .addContainerGap())
        );
        Dados_PanelLayout.setVerticalGroup(
            Dados_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Dados_PanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(Dados_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NomeDeUtilizador_Label)
                    .addComponent(NomeDeUtilizador_TextField))
                .addGap(18, 18, 18)
                .addGroup(Dados_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PalavraPasse_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PalavraPasse_PasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        Login_Label.setFont(new java.awt.Font("Trebuchet MS", 0, 20)); // NOI18N
        Login_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Login_Label.setText("Login");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Fazer Login como:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Trebuchet MS", 0, 12))); // NOI18N

        buttonGroup1.add(Gestor_RadioButton);
        Gestor_RadioButton.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        Gestor_RadioButton.setText("Gestor");
        Gestor_RadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Gestor_RadioButtonActionPerformed(evt);
            }
        });

        buttonGroup1.add(Juiz_RadioButton);
        Juiz_RadioButton.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        Juiz_RadioButton.setText("Juíz");
        Juiz_RadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Juiz_RadioButtonActionPerformed(evt);
            }
        });

        buttonGroup1.add(Visitante_RadioButton);
        Visitante_RadioButton.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        Visitante_RadioButton.setText("Visitante");
        Visitante_RadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Visitante_RadioButtonActionPerformed(evt);
            }
        });

        Login_Button.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        Login_Button.setText("Login");
        Login_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Login_ButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Gestor_RadioButton)
                .addGap(18, 18, 18)
                .addComponent(Juiz_RadioButton)
                .addGap(18, 18, 18)
                .addComponent(Visitante_RadioButton)
                .addGap(18, 18, 18)
                .addComponent(Login_Button, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Visitante_RadioButton)
                    .addComponent(Juiz_RadioButton)
                    .addComponent(Gestor_RadioButton)
                    .addComponent(Login_Button))
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(Dados_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Login_Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(Login_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Dados_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void NomeDeUtilizador_TextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NomeDeUtilizador_TextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NomeDeUtilizador_TextFieldActionPerformed

    private void Gestor_RadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Gestor_RadioButtonActionPerformed
        // TODO add your handling code here:
        NomeDeUtilizador_TextField.setText("");
        NomeDeUtilizador_TextField.setEnabled(true);
        NomeDeUtilizador_Label.setEnabled(true);
        PalavraPasse_PasswordField.setText("");
        PalavraPasse_PasswordField.setEnabled(true);
        PalavraPasse_Label.setEnabled(true);
        Dados_Panel.setEnabled(true);
    }//GEN-LAST:event_Gestor_RadioButtonActionPerformed

    private void Juiz_RadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Juiz_RadioButtonActionPerformed
        // TODO add your handling code here:
        NomeDeUtilizador_TextField.setText("");
        NomeDeUtilizador_TextField.setEnabled(true);
        NomeDeUtilizador_Label.setEnabled(true);
        PalavraPasse_PasswordField.setText("");
        PalavraPasse_PasswordField.setEnabled(true);
        PalavraPasse_Label.setEnabled(true);
        Dados_Panel.setEnabled(true);
    }//GEN-LAST:event_Juiz_RadioButtonActionPerformed

    private void Visitante_RadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Visitante_RadioButtonActionPerformed
        // TODO add your handling code here:
        NomeDeUtilizador_TextField.setText("Visitante");
        NomeDeUtilizador_TextField.setEnabled(false);
        NomeDeUtilizador_Label.setEnabled(false);
        PalavraPasse_PasswordField.setText("******");
        PalavraPasse_PasswordField.setEnabled(false);
        PalavraPasse_Label.setEnabled(false);
        Dados_Panel.setEnabled(false);
    }//GEN-LAST:event_Visitante_RadioButtonActionPerformed

    private void Login_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Login_ButtonActionPerformed
        boolean OK=false;
        if(Visitante_RadioButton.isSelected()){
            this.a.setTipoUtilizador("Visitante");
            this.dispose();
            new Visitante(a).setVisible(true);
        }
        if(Gestor_RadioButton.isSelected()){
            this.a.setTipoUtilizador("Gestor");
            if(a.loginGestor(NomeDeUtilizador_TextField.getText(),new String(PalavraPasse_PasswordField.getPassword()))){
                OK=true;
                new Gestor(a).setVisible(true);
            }else {
                 JOptionPane.showMessageDialog(null,"Login Errado", "ERRO!", JOptionPane.ERROR_MESSAGE);
        }
        if(OK)
            this.dispose();
        }
        
        if(Juiz_RadioButton.isSelected()){
            this.a.setTipoUtilizador("Juíz");
            if(a.login(NomeDeUtilizador_TextField.getText(),new String(PalavraPasse_PasswordField.getPassword()))){
                OK=true;
                new Juiz(a).setVisible(true);
            }else {
                 JOptionPane.showMessageDialog(null,"Login Errado", "ERRO!", JOptionPane.ERROR_MESSAGE);
        }
        if(OK)
            this.dispose();
        }
      /*  this.dispose();*/
    }//GEN-LAST:event_Login_ButtonActionPerformed

    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Dados_Panel;
    private javax.swing.JRadioButton Gestor_RadioButton;
    private javax.swing.JRadioButton Juiz_RadioButton;
    private javax.swing.JButton Login_Button;
    private javax.swing.JLabel Login_Label;
    private javax.swing.JLabel NomeDeUtilizador_Label;
    private javax.swing.JTextField NomeDeUtilizador_TextField;
    private javax.swing.JLabel PalavraPasse_Label;
    private javax.swing.JPasswordField PalavraPasse_PasswordField;
    private javax.swing.JRadioButton Visitante_RadioButton;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}