/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package gui;

import classes.Livro;
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

public class dialogLivro extends javax.swing.JDialog {

    /**
     * Creates new form dialogLivro
     */
    private Livro l;
    private List<Livro> livros;
    private boolean isEditing;
    
    public dialogLivro(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        habilitaCampos(false);
        l = new Livro();
        isEditing = false;
        load();
    }
    
    private void load() {
        String filePath = "ListagemLivros.csv";
        File file = new File(filePath);

        if (file.exists()) {
            try (Scanner arquivoLido = new Scanner(file)) {
                arquivoLido.useDelimiter("\n");

                String linha = "";
                if (arquivoLido.hasNext()) linha = arquivoLido.next(); // ignora cabeçalho

                livros = new ArrayList<>();
                while (arquivoLido.hasNext()) {
                    l = new Livro(); //reseta o ponteiro
                    linha = arquivoLido.next();
                    
                    l.CSVToObj(linha);
                    livros.add(l);
                }
                attList();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(dialogLivro.class.getName()).log(Level.SEVERE, null, ex);
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
        if(!edtTitulo.getText().isEmpty() && !edtAutor.getText().isEmpty() && !edtPaginas.getText().isEmpty() && !edtPreco.getText().isEmpty()){
            isValidated = true;
        }
        return isValidated;
    }
    
    private void limparCampos() {
        edtTitulo.setText("");
        edtAutor.setText("");
        edtPaginas.setText("");
        edtPreco.setText("");
    }
    
    private void attList() {
        txtAreaList.setText("");
        StringBuilder sb = new StringBuilder();

        for (Livro livro : livros) {
            Livro LivroAtualizado = new Livro();
            LivroAtualizado.CSVToObj(livro.objToCSV());

            sb.append(LivroAtualizado.toString()).append(" \n");
        }

        txtAreaList.setText(sb.toString());
    }
    
    private void cadLivro() {
        if(validaCampos()) {
            try {
                l = new Livro();
                l.setAutor(edtAutor.getText());
                l.setTitulo(edtTitulo.getText());
                l.setNum_pag(Integer.parseInt(edtPaginas.getText())); //favor nao colocar um double no campo de inteiros pois o try nao trata e da erro, =/
                l.setPreco(Double.parseDouble(edtPreco.getText()));
                livros.add(l);
                limparCampos();
                salvarLivro(l.objToCSV());
                attList();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Formatos invalidos!");
            }
        }else {
            JOptionPane.showMessageDialog(null, "Alguns campos nao foram preenchidos!");
        }
    }

    public void salvarLivro(String texto) {
        FileWriter arq = null;
        try {
            arq = new FileWriter("ListagemLivros.csv", true); // O segundo parâmetro "true" indica que você deseja abrir o arquivo em modo de apêndice
            PrintWriter gravaArq = new PrintWriter(arq);
            gravaArq.print(texto);
            gravaArq.flush();
        } catch (IOException ex) {
            Logger.getLogger(dialogLivro.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (arq != null) {
                try {
                    arq.close();
                } catch (IOException ex) {
                    Logger.getLogger(dialogLivro.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    private void editLivro() {
        if(validaCampos()) {
            for(Livro livro : livros) {
                if(livro.getTitulo().equalsIgnoreCase(l.getTitulo())) {
                    try {
                        livro.setAutor(edtAutor.getText());
                        livro.setTitulo(edtTitulo.getText());
                        livro.setNum_pag(Integer.parseInt(edtPaginas.getText()));
                        livro.setPreco(Double.parseDouble(edtPreco.getText()));
                        limparCampos();
                        txtAreaList.setText("Dados alterados! \r\n"+livro.toString());
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
    
    private void removerLivro(Livro livroRemover) {
        livros.remove(livroRemover);

        updateCSV();
    }
    
    private void updateCSV() {
        // Atualiza o arquivo CSV
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("ListagemLivros.csv");
            try (PrintWriter printWriter = new PrintWriter(fileWriter)) {
                printWriter.print("titulo;autor;paginas;preco \n");
                // Escreve cada objeto da lista no arquivo CSV
                for (Livro livro : livros) {
                    String linhaCSV = livro.objToCSV();
                    printWriter.print(linhaCSV);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(dialogLivro.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException ex) {
                    Logger.getLogger(dialogLivro.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        attList();
    }
    
    private Livro findLivro(String titulo) {
        for(Livro livro : livros) {
            if(livro.getTitulo().equalsIgnoreCase(titulo)) {
                return livro;
            }
        }
        return null;
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
        lblTopo = new javax.swing.JLabel();
        panelCampos = new javax.swing.JPanel();
        lblAutor = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        lblPaginas = new javax.swing.JLabel();
        lblPreco = new javax.swing.JLabel();
        edtTitulo = new javax.swing.JTextField();
        edtPaginas = new javax.swing.JTextField();
        edtAutor = new javax.swing.JTextField();
        edtPreco = new javax.swing.JTextField();
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

        lblTopo.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        lblTopo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTopo.setText("Cadastrar Livro");

        panelCampos.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lblAutor.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblAutor.setText("Autor:");

        lblTitulo.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblTitulo.setText("Titulo:");

        lblPaginas.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblPaginas.setText("Páginas:");

        lblPreco.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblPreco.setText("Preço:");

        edtTitulo.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N

        edtPaginas.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N

        edtAutor.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N

        edtPreco.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N

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
                    .addComponent(lblPaginas)
                    .addComponent(lblTitulo)
                    .addComponent(lblAutor)
                    .addComponent(lblPreco))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edtPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edtPaginas, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edtAutor, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        panelCamposLayout.setVerticalGroup(
            panelCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCamposLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTitulo)
                    .addComponent(edtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAutor)
                    .addComponent(edtAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPaginas)
                    .addComponent(edtPaginas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPreco)
                    .addComponent(edtPreco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(lblTopo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelCampos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(lblTopo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        if(!isEditing) cadLivro();
        else editLivro();
    }//GEN-LAST:event_btnSaveMouseClicked

    private void btnExcluirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExcluirMouseClicked
        if(edtTitulo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Informe o titulo do livro a ser excluido.");
        }else {
            l = new Livro();
            l = findLivro(edtTitulo.getText());
            if(l == null) JOptionPane.showMessageDialog(null, "Livro ["+edtTitulo.getText()+"] não encontrado.");
            else {
                int result = JOptionPane.showOptionDialog(null, "Desja excluir este Livro? \r\n \r\n"+l.toString(), "Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                switch (result) {
                    case JOptionPane.YES_OPTION:
                        removerLivro(l);
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
        String valor = JOptionPane.showInputDialog(null, "Informe o titulo do livro:");
        if(valor != null) {
            l = new Livro();
            l = findLivro(valor);
            if(l == null) {
                JOptionPane.showMessageDialog(null, "Livro ["+edtTitulo.getText()+"] não encontrado.");
            }else {
                JOptionPane.showMessageDialog(null, "Edite os dados e clique em salvar.");
                isEditing = true;
                edtTitulo.setText(l.getTitulo());
                edtAutor.setText(l.getAutor());
                edtPaginas.setText(Integer.toString(l.getNum_pag()));
                edtPreco.setText(Double.toString(l.getPreco()));
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
    private javax.swing.JTextField edtAutor;
    private javax.swing.JTextField edtPaginas;
    private javax.swing.JTextField edtPreco;
    private javax.swing.JTextField edtTitulo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAutor;
    private javax.swing.JLabel lblPaginas;
    private javax.swing.JLabel lblPreco;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTopo;
    private javax.swing.JPanel panelCampos;
    private javax.swing.JTextArea txtAreaList;
    // End of variables declaration//GEN-END:variables
}
