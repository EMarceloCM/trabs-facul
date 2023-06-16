/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package gui;

import classes.Produto;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
//import java.awt.Component;
import javax.swing.JOptionPane;
//import javax.swing.JTextField;

public class dialogProduto extends javax.swing.JDialog {

    /**
     * Creates new form dialogLivro
     */
    private Produto p;
    private List<Produto> produtos;
    private boolean isEditing;
    
    public dialogProduto(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        habilitaCampos(false);
        p = new Produto();
        produtos = new ArrayList<>();
        isEditing = false;
        load();
    }

    private void load() {
        String filePath = "ListagemProdutos.csv";
        File file = new File(filePath);

        if (file.exists()) {
            try (Scanner arquivoLido = new Scanner(file)) {
                arquivoLido.useDelimiter("\n");

                String linha;
                if (arquivoLido.hasNext()) linha = arquivoLido.next(); // ignora cabeçalho

                produtos = new ArrayList<>();
                while (arquivoLido.hasNext()) {
                    p = new Produto(); //reseta o ponteiro
                    linha = arquivoLido.next();
                    
                    p.CSVToObj(linha);
                    produtos.add(p);
                }
                attList();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(dialogProduto.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Arquivo não encontrado!");
        }
    }

    private void habilitaCampos(boolean flag) {
        panelCampos.setVisible(flag);
        //txtAreaList.setVisible(flag);
        btnSave.setVisible(flag);
        btnEdit.setVisible(flag);
        btnExcluir.setVisible(flag);
    }
    
    private void editando(boolean flag) {
        btnEdit.setVisible(!flag);
        btnExcluir.setVisible(!flag);
    }
    
    private boolean validaCampos() {
        boolean isValidated = false;
        /*for (Component comp : getContentPane().getComponents()) {
            if (comp instanceof JTextField) {
                JTextField textField = (JTextField) comp;
                if(textField.getText().isEmpty()) {
                   isValidated = false; //nao sei pq mas nao funciona esta verificacao
                   break;
                }
            }
        }*/
        if(!edtId.getText().isEmpty() && !edtNome.getText().isEmpty() && !edtPreco.getText().isEmpty() && !edtEstoque.getText().isEmpty()){
            isValidated = true;
        }
        return isValidated;
    }
    
    private void limparCampos() {
        edtId.setText("");
        edtNome.setText("");
        edtPreco.setText("");
        edtEstoque.setText("");
    }

    private void attList() {
        txtAreaList.setText("");
        StringBuilder sb = new StringBuilder();

        for (Produto produto : produtos) {
            Produto produtoAtualizado = new Produto();
            produtoAtualizado.CSVToObj(produto.objToCSV());

            sb.append(produtoAtualizado.toString()).append(" \n");
        }

        txtAreaList.setText(sb.toString());
    }
    
    private void cadProduto() {
        if(validaCampos()) {
            try {
                p = new Produto();
                p.setId(Integer.parseInt(edtId.getText().trim()));
                p.setNome(edtNome.getText());
                p.setEstoque(Integer.parseInt(edtEstoque.getText())); //favor nao colocar um double no campo de inteiros pois o try nao trata e da erro, =/
                p.setPreco(Double.parseDouble(edtPreco.getText()));
                produtos.add(p);
                limparCampos();
                salvarProduto(p.objToCSV());
                attList();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Formatos invalidos!");
            }
        }else {
            JOptionPane.showMessageDialog(null, "Alguns campos nao foram preenchidos!");
        }
    }
    
    public void salvarProduto(String texto) {
        FileWriter arq = null;
        try {
            arq = new FileWriter("ListagemProdutos.csv", true); // O segundo parâmetro "true" indica que você deseja abrir o arquivo em modo de apêndice
            PrintWriter gravaArq = new PrintWriter(arq);
            gravaArq.print(texto);
            gravaArq.flush();
        } catch (IOException ex) {
            Logger.getLogger(dialogProduto.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (arq != null) {
                try {
                    arq.close();
                } catch (IOException ex) {
                    Logger.getLogger(dialogProduto.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private void editProduto() {
        if(validaCampos()) {
            for(Produto produto : produtos) {
                if(produto.getNome().equalsIgnoreCase(p.getNome())) {
                    try {
                        produto.setId(Integer.parseInt(edtId.getText()));
                        produto.setNome(edtNome.getText());
                        produto.setEstoque(Integer.parseInt(edtEstoque.getText()));
                        produto.setPreco(Double.parseDouble(edtPreco.getText()));
                        limparCampos();
                        isEditing = false;
                        editando(isEditing);
                        
                        updateCSV();
                    } catch (NumberFormatException e) {
                        isEditing = true;
                        editando(isEditing);
                        JOptionPane.showMessageDialog(null, "Formatos invalidos, tente novamente!");
                    }
                    break;
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Alguns campos nao foram preenchidos!");
        }
    }
    
    private Produto findProduto(String nome) {
        for(Produto produto : produtos) {
            if(produto.getNome().equalsIgnoreCase(nome)) {
                return produto;
            }
        }
        return null;
    }
    
    private void removerProduto(Produto produtoRemover) {
        produtos.remove(produtoRemover);

        updateCSV();
    }
    
    private void updateCSV() {
        // Atualiza o arquivo CSV
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("ListagemProdutos.csv");
            try (PrintWriter printWriter = new PrintWriter(fileWriter)) {
                printWriter.print("id;nome;estoque;preco \n");
                // Escreve cada objeto da lista no arquivo CSV
                for (Produto produto : produtos) {
                    String linhaCSV = produto.objToCSV();
                    printWriter.print(linhaCSV);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(dialogProduto.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException ex) {
                    Logger.getLogger(dialogProduto.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        attList();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnNovo = new javax.swing.JButton();
        lblTitulo = new javax.swing.JLabel();
        panelCampos = new javax.swing.JPanel();
        lblNome = new javax.swing.JLabel();
        lblId = new javax.swing.JLabel();
        lblEstoque = new javax.swing.JLabel();
        lblPreco = new javax.swing.JLabel();
        edtId = new javax.swing.JTextField();
        edtPreco = new javax.swing.JTextField();
        edtNome = new javax.swing.JTextField();
        edtEstoque = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaList = new javax.swing.JTextArea();
        btnSave = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        btnNovo.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnNovo.setForeground(new java.awt.Color(0, 204, 0));
        btnNovo.setText("Novo");
        btnNovo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnNovo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNovoMouseClicked(evt);
            }
        });

        lblTitulo.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Cadastrar Produto");

        panelCampos.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lblNome.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblNome.setText("Nome:");

        lblId.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblId.setText("ID:");

        lblEstoque.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblEstoque.setText("Preço:");

        lblPreco.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblPreco.setText("Estoque:");

        edtId.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N

        edtPreco.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        edtPreco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtPrecoActionPerformed(evt);
            }
        });

        edtNome.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N

        edtEstoque.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N

        txtAreaList.setEditable(false);
        txtAreaList.setColumns(20);
        txtAreaList.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        txtAreaList.setRows(5);
        jScrollPane1.setViewportView(txtAreaList);

        javax.swing.GroupLayout panelCamposLayout = new javax.swing.GroupLayout(panelCampos);
        panelCampos.setLayout(panelCamposLayout);
        panelCamposLayout.setHorizontalGroup(
            panelCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCamposLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(panelCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblEstoque)
                    .addComponent(lblId)
                    .addComponent(lblNome)
                    .addComponent(lblPreco))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edtEstoque, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edtPreco, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edtId, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edtNome, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        panelCamposLayout.setVerticalGroup(
            panelCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCamposLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblId)
                    .addComponent(edtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNome)
                    .addComponent(edtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEstoque)
                    .addComponent(edtPreco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPreco)
                    .addComponent(edtEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnSave.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnSave.setForeground(new java.awt.Color(0, 0, 0));
        btnSave.setText("Salvar");
        btnSave.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSaveMouseClicked(evt);
            }
        });

        btnEdit.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnEdit.setForeground(new java.awt.Color(0, 0, 0));
        btnEdit.setText("Editar");
        btnEdit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEditMouseClicked(evt);
            }
        });

        btnExcluir.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnExcluir.setForeground(new java.awt.Color(0, 0, 0));
        btnExcluir.setText("Excluir");
        btnExcluir.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnExcluir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnExcluirMouseClicked(evt);
            }
        });

        btnSair.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnSair.setForeground(new java.awt.Color(255, 0, 0));
        btnSair.setText("Sair");
        btnSair.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnSair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSairMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelCampos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelCampos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnNovoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNovoMouseClicked
        habilitaCampos(true);
        limparCampos();
    }//GEN-LAST:event_btnNovoMouseClicked

    private void btnSairMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSairMouseClicked
        if(!isEditing){
            this.setVisible(false); //nao sei se perderia os valores da List<> dando um dispose();
            this.limparCampos();
            this.habilitaCampos(false);
        }else {
            int result = JOptionPane.showOptionDialog(null, "Desja cancelar a edição? \r\n \r\n", "Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                switch (result) {
                    case JOptionPane.YES_OPTION:
                        isEditing = false;
                        editando(isEditing);
                        limparCampos();
                        break;
                    case JOptionPane.NO_OPTION:
                        break;
                    default:
                        break;
                }
        }
    }//GEN-LAST:event_btnSairMouseClicked

    private void btnSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveMouseClicked
        if(!isEditing) cadProduto();
        else editProduto();
    }//GEN-LAST:event_btnSaveMouseClicked

    private void btnExcluirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExcluirMouseClicked
        if(edtNome.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Informe o nome do produto a ser excluido.");
        }else {
            p = new Produto();
            p = findProduto(edtNome.getText());
            if(p == null) JOptionPane.showMessageDialog(null, "Produto ["+edtNome.getText()+"] não encontrado.");
            else {
                int result = JOptionPane.showOptionDialog(null, "Desja excluir este Produto? \r\n \r\n"+p.toString(), "Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                switch (result) {
                    case JOptionPane.YES_OPTION:
                        removerProduto(p);
                        break;
                    case JOptionPane.NO_OPTION:
                        break;
                    default:
                        break;
                }
            }
            limparCampos();
        }
    }//GEN-LAST:event_btnExcluirMouseClicked

    private void btnEditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditMouseClicked
        String valor = JOptionPane.showInputDialog(null, "Informe o nome do produto:");
        if(valor != null) {
            p = new Produto();
            p = findProduto(valor);
            if(p == null) {
                JOptionPane.showMessageDialog(null, "Produto ["+edtNome.getText()+"] não encontrado.");
            }else {
                JOptionPane.showMessageDialog(null, "Edite os dados e clique em salvar.");
                isEditing = true;
                edtNome.setText(p.getNome());
                edtId.setText(Integer.toString(p.getId()));
                edtPreco.setText(Integer.toString(p.getEstoque()));
                edtEstoque.setText(Double.toString(p.getPreco()));
                editando(isEditing);
            }
        }else {
            JOptionPane.showMessageDialog(null, "Nada informado.");
        }
    }//GEN-LAST:event_btnEditMouseClicked

    private void edtPrecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtPrecoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edtPrecoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSave;
    private javax.swing.JTextField edtEstoque;
    private javax.swing.JTextField edtId;
    private javax.swing.JTextField edtNome;
    private javax.swing.JTextField edtPreco;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblEstoque;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblPreco;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panelCampos;
    private javax.swing.JTextArea txtAreaList;
    // End of variables declaration//GEN-END:variables
}
