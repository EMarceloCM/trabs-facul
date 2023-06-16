/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import javax.swing.JOptionPane;

public class FrHome extends javax.swing.JFrame {

    /**
     * Creates new form FrHome
     */
    dialogCasas dc;
    dialogLivro dl;
    dialogPessoa dp;
    dialogPet dt;
    dialogProduto dd;
    public FrHome() {
        initComponents();
        dc = new dialogCasas(this, true);
        dl = new dialogLivro(this, true);
        dp = new dialogPessoa(this, true);
        dt = new dialogPet(this, true);
        dd = new dialogProduto(this, true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panHome = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        bntPessoa = new javax.swing.JButton();
        bntCasa = new javax.swing.JButton();
        btnLivro = new javax.swing.JButton();
        btnProduto = new javax.swing.JButton();
        bntPet = new javax.swing.JButton();
        bntEstetico = new javax.swing.JButton();
        mbHome = new javax.swing.JMenuBar();
        menuSair = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(51, 51, 51));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setUndecorated(true);
        setResizable(false);

        panHome.setBackground(new java.awt.Color(102, 102, 102));
        panHome.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lblTitulo.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setText("Cadastros");

        bntPessoa.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        bntPessoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/9035610_person_add_outline_icon.png"))); // NOI18N
        bntPessoa.setText("Pessoa");
        bntPessoa.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        bntPessoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bntPessoaMouseClicked(evt);
            }
        });

        bntCasa.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        bntCasa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/299061_house_icon.png"))); // NOI18N
        bntCasa.setText("Casa");
        bntCasa.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        bntCasa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bntCasa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bntCasaMouseClicked(evt);
            }
        });

        btnLivro.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnLivro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/285636_book_icon.png"))); // NOI18N
        btnLivro.setText("Livro");
        btnLivro.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnLivro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLivroMouseClicked(evt);
            }
        });

        btnProduto.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/6214719_box_dropbox_logo_icon.png"))); // NOI18N
        btnProduto.setText("Produto");
        btnProduto.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnProdutoMouseClicked(evt);
            }
        });

        bntPet.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        bntPet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/3775229_animal_canine_cat_dog_pet_icon.png"))); // NOI18N
        bntPet.setText("Pet");
        bntPet.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        bntPet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bntPetMouseClicked(evt);
            }
        });

        bntEstetico.setText("Versão 1.17");
        bntEstetico.setEnabled(false);

        javax.swing.GroupLayout panHomeLayout = new javax.swing.GroupLayout(panHome);
        panHome.setLayout(panHomeLayout);
        panHomeLayout.setHorizontalGroup(
            panHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panHomeLayout.createSequentialGroup()
                .addGroup(panHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panHomeLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblTitulo))
                    .addGroup(panHomeLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(panHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panHomeLayout.createSequentialGroup()
                                .addComponent(bntPet, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(bntEstetico, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panHomeLayout.createSequentialGroup()
                                .addGroup(panHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(bntCasa, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bntPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        panHomeLayout.setVerticalGroup(
            panHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panHomeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(panHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panHomeLayout.createSequentialGroup()
                        .addComponent(bntCasa, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bntPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panHomeLayout.createSequentialGroup()
                        .addComponent(btnLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bntPet, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bntEstetico, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49))
        );

        mbHome.setBackground(new java.awt.Color(102, 102, 102));

        menuSair.setBorder(new javax.swing.border.MatteBorder(null));
        menuSair.setForeground(new java.awt.Color(255, 255, 255));
        menuSair.setText("Sair");
        menuSair.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        menuSair.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        menuSair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuSairMouseClicked(evt);
            }
        });
        mbHome.add(menuSair);

        setJMenuBar(mbHome);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(panHome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panHome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void menuSairMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuSairMouseClicked
        int result = JOptionPane.showOptionDialog(null, "Desja Sair do Programa?", "Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        switch (result) {
            case JOptionPane.YES_OPTION:
                this.dispose();
                break;
            case JOptionPane.NO_OPTION:
                break;
            default:
                break;
        }
    }//GEN-LAST:event_menuSairMouseClicked

    private void bntCasaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bntCasaMouseClicked
        dc.setVisible(true);
    }//GEN-LAST:event_bntCasaMouseClicked

    private void btnLivroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLivroMouseClicked
        dl.setVisible(true);
    }//GEN-LAST:event_btnLivroMouseClicked

    private void bntPessoaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bntPessoaMouseClicked
        dp.setVisible(true);
    }//GEN-LAST:event_bntPessoaMouseClicked

    private void bntPetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bntPetMouseClicked
        dt.setVisible(true);
    }//GEN-LAST:event_bntPetMouseClicked

    private void btnProdutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProdutoMouseClicked
        dd.setVisible(true);
    }//GEN-LAST:event_btnProdutoMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntCasa;
    private javax.swing.JButton bntEstetico;
    private javax.swing.JButton bntPessoa;
    private javax.swing.JButton bntPet;
    private javax.swing.JButton btnLivro;
    private javax.swing.JButton btnProduto;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JMenuBar mbHome;
    private javax.swing.JMenu menuSair;
    private javax.swing.JPanel panHome;
    // End of variables declaration//GEN-END:variables
}
