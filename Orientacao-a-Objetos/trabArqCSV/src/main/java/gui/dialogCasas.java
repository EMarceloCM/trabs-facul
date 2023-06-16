/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package gui;

import classes.Casa;
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

public class dialogCasas extends javax.swing.JDialog {

    /**
     * Creates new form dialogLivro
     */
    private Casa c;
    private List<Casa> casas;
    private boolean isEditing;
    
    public dialogCasas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        habilitaCampos(false);
        isEditing = false;
        c = new Casa();
        load();
    }
    
    private void load() {
        String filePath = "ListagemCasas.csv";
        File file = new File(filePath);

        if (file.exists()) {
            try (Scanner arquivoLido = new Scanner(file)) {
                arquivoLido.useDelimiter("\n");

                String linha = "";
                if (arquivoLido.hasNext()) linha = arquivoLido.next(); // ignora cabeçalho

                casas = new ArrayList();
                casas.removeAll(casas);
                while (arquivoLido.hasNext()) {
                    c = new Casa(); //reseta o ponteiro
                    linha = arquivoLido.next();
                    
                    c.CSVToObj(linha);
                    casas.add(c);
                }
                attList();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(dialogCasas.class.getName()).log(Level.SEVERE, null, ex);
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
        if(!edtEnd.getText().isEmpty() && !edtArea.getText().isEmpty() && !edtQuartos.getText().isEmpty() && !edtPreco.getText().isEmpty()){
            isValidated = true;
        }
        return isValidated;
    }
    
    private void limparCampos() {
        edtEnd.setText("");
        edtArea.setText("");
        edtQuartos.setText("");
        edtPreco.setText("");
    }
    
    private void attList() {
        txtAreaList.setText("");
        StringBuilder sb = new StringBuilder();

        for (Casa casa : casas) {
            Casa casaAtualizada = new Casa();
            casaAtualizada.CSVToObj(casa.objToCSV());

            sb.append(casaAtualizada.toString()).append(" \n");
        }

        txtAreaList.setText(sb.toString());
    }

    private void cadCasa() {
        if(validaCampos()) {
            try {
                c = new Casa();
                c.setArea(Double.parseDouble(edtArea.getText()));
                c.setEndereco(edtEnd.getText());
                c.setNum_quartos(Integer.parseInt(edtQuartos.getText())); //favor nao colocar um double no campo de inteiros pois o try nao trata e da erro, =/
                c.setPreco(Double.parseDouble(edtPreco.getText()));
                casas.add(c);
                limparCampos();
                salvarCasa(c.objToCSV());
                attList();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Formatos invalidos!");
            }
        }else {
            JOptionPane.showMessageDialog(null, "Alguns campos nao foram preenchidos!");
        }
    }
    
    public void salvarCasa(String texto) {
        FileWriter arq = null;
        try {
            arq = new FileWriter("ListagemCasas.csv", true); // O segundo parâmetro "true" indica que você deseja abrir o arquivo em modo de apêndice
            PrintWriter gravaArq = new PrintWriter(arq);
            gravaArq.print(texto);
            gravaArq.flush();
        } catch (IOException ex) {
            Logger.getLogger(dialogCasas.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (arq != null) {
                try {
                    arq.close();
                } catch (IOException ex) {
                    Logger.getLogger(dialogCasas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private void editCasa() {
        if(validaCampos()) {
            for(Casa casa : casas) {
                if(casa.getEndereco().equalsIgnoreCase(c.getEndereco())) {
                    try {
                        casa.setArea(Double.parseDouble(edtArea.getText()));
                        casa.setEndereco(edtEnd.getText());
                        casa.setNum_quartos(Integer.parseInt(edtQuartos.getText()));
                        casa.setPreco(Double.parseDouble(edtPreco.getText()));
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
    
    private Casa findCasa(String endereco) {
        for(Casa casa : casas) {
            if(casa.getEndereco().equalsIgnoreCase(endereco)) {
                return casa;
            }
        }
        return null;
    }
    
    private void removerCasa(Casa casaRemover) {
        casas.remove(casaRemover);

        updateCSV();
    }
    
    private void updateCSV() {
        // Atualiza o arquivo CSV
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("ListagemCasas.csv");
            try (PrintWriter printWriter = new PrintWriter(fileWriter)) {
                printWriter.print("endereco;area;num_quartos;valor \n");
                // Escreve cada objeto da lista no arquivo CSV
                for (Casa casa : casas) {
                    String linhaCSV = casa.objToCSV();
                    printWriter.print(linhaCSV);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(dialogCasas.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException ex) {
                    Logger.getLogger(dialogCasas.class.getName()).log(Level.SEVERE, null, ex);
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
        lblArea = new javax.swing.JLabel();
        lblEndereco1 = new javax.swing.JLabel();
        lblQuartos = new javax.swing.JLabel();
        lblPreco = new javax.swing.JLabel();
        edtEnd = new javax.swing.JTextField();
        edtQuartos = new javax.swing.JTextField();
        edtArea = new javax.swing.JTextField();
        edtPreco = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaList = new javax.swing.JTextArea();
        lblList = new javax.swing.JLabel();
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
        lblTitulo.setText("Cadastrar Casa");

        panelCampos.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lblArea.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblArea.setText("Area:");

        lblEndereco1.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblEndereco1.setText("Endereço:");

        lblQuartos.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblQuartos.setText("Quartos:");

        lblPreco.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblPreco.setText("Preço:");

        edtEnd.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N

        edtQuartos.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N

        edtArea.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N

        edtPreco.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N

        txtAreaList.setEditable(false);
        txtAreaList.setColumns(20);
        txtAreaList.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        txtAreaList.setRows(5);
        jScrollPane1.setViewportView(txtAreaList);

        lblList.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblList.setText("Dados:");

        javax.swing.GroupLayout panelCamposLayout = new javax.swing.GroupLayout(panelCampos);
        panelCampos.setLayout(panelCamposLayout);
        panelCamposLayout.setHorizontalGroup(
            panelCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCamposLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(panelCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelCamposLayout.createSequentialGroup()
                        .addComponent(lblList)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblQuartos)
                    .addGroup(panelCamposLayout.createSequentialGroup()
                        .addGroup(panelCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblEndereco1)
                            .addComponent(lblArea)
                            .addComponent(lblPreco))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(edtPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(edtQuartos, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(edtEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(edtArea, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(59, Short.MAX_VALUE))
        );
        panelCamposLayout.setVerticalGroup(
            panelCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCamposLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEndereco1)
                    .addComponent(edtEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblArea)
                    .addComponent(edtArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblQuartos)
                    .addComponent(edtQuartos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPreco)
                    .addComponent(edtPreco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                    .addGroup(panelCamposLayout.createSequentialGroup()
                        .addComponent(lblList)
                        .addGap(0, 0, Short.MAX_VALUE)))
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
                .addContainerGap(22, Short.MAX_VALUE))
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
            this.setVisible(false);
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
        if(!isEditing) cadCasa();
        else editCasa();
    }//GEN-LAST:event_btnSaveMouseClicked

    private void btnExcluirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExcluirMouseClicked
        if(edtEnd.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Informe o endereço da casa a ser excluida.");
        }else {
            c = new Casa();
            c = findCasa(edtEnd.getText());
            if(c == null) JOptionPane.showMessageDialog(null, "Casa não encontrada no endereço: "+edtEnd.getText());
            else {
                int result = JOptionPane.showOptionDialog(null, "Desja excluir esta Casa? \r\n \r\n"+c.toString(), "Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                switch (result) {
                    case JOptionPane.YES_OPTION:
                        removerCasa(c);
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
        String valor = JOptionPane.showInputDialog(null, "Informe o endereço da casa:");
        if(valor != null) {
            c = new Casa();
            c = findCasa(valor);
            if(c == null) {
                JOptionPane.showMessageDialog(null, "Casa não encontrada no endereço: "+edtEnd.getText());
            }else {
                JOptionPane.showMessageDialog(null, "Edite os dados e clique em salvar.");
                isEditing = true;
                edtEnd.setText(c.getEndereco());
                edtArea.setText(Double.toString(c.getArea()));
                edtQuartos.setText(Integer.toString(c.getNum_quartos()));
                edtPreco.setText(Double.toString(c.getPreco()));
                editando(isEditing);
            }
        }else {
            JOptionPane.showMessageDialog(null, "Nada informado.");
        }
    }//GEN-LAST:event_btnEditMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSave;
    private javax.swing.JTextField edtArea;
    private javax.swing.JTextField edtEnd;
    private javax.swing.JTextField edtPreco;
    private javax.swing.JTextField edtQuartos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblArea;
    private javax.swing.JLabel lblEndereco1;
    private javax.swing.JLabel lblList;
    private javax.swing.JLabel lblPreco;
    private javax.swing.JLabel lblQuartos;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panelCampos;
    private javax.swing.JTextArea txtAreaList;
    // End of variables declaration//GEN-END:variables
}
