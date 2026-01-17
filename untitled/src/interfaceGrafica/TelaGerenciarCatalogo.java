package interfaceGrafica;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import gerenciamentoPrograma.bancoDados.BancoDados;
import obras.Obra;
import obras.jogos.Jogo;

public class TelaGerenciarCatalogo extends JFrame {
    private JTable tabela;
    private DefaultTableModel modelo;

    public TelaGerenciarCatalogo() {
        setTitle("GameDex - Gerenciar Catálogo");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        String[] colunas = {"ID", "Título", "Tipo", "Desenvolvedor"};
        modelo = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int r, int c) { return false; }
        };
        tabela = new JTable(modelo);
        atualizarTabela();

        add(new JScrollPane(tabela), BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel();
        JButton btnNovoJogo = new JButton("Cadastrar Jogo");

        JButton btnNovaDLC = new JButton("Cadastrar DLC");
        btnNovaDLC.addActionListener(e -> {
            new TelaFormularioDLC();});

        JButton btnRemover = new JButton("Remover Obra");

        painelBotoes.add(btnNovoJogo);
        painelBotoes.add(btnNovaDLC);
        painelBotoes.add(btnRemover);
        add(painelBotoes, BorderLayout.SOUTH);

        btnRemover.addActionListener(e -> realizarRemocao());

        // Os botões de cadastro abrirão novos diálogos ou telas futuramente
        btnNovoJogo.addActionListener(e -> JOptionPane.showMessageDialog(this, "Abrir formulário de Jogo"));
        btnNovaDLC.addActionListener(e -> JOptionPane.showMessageDialog(this, "Abrir formulário de DLC"));

        setVisible(true);
    }

    private void atualizarTabela() {
        modelo.setRowCount(0);
        for (Obra o : BancoDados.getInstancia().getObras()) {
            String tipo = (o instanceof Jogo) ? "Jogo" : "DLC";
            modelo.addRow(new Object[]{o.getId(), o.getTitulo(), tipo, o.getDesenvolvedor()});
        }
    }

    private void realizarRemocao() {
        int linha = tabela.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma obra para remover!");
            return;
        }

        int confirmacao = JOptionPane.showConfirmDialog(this,
                "Tem certeza que deseja remover esta obra?", "Confirmação", JOptionPane.YES_NO_OPTION);

        if (confirmacao == JOptionPane.YES_OPTION) {
            int id = (int) modelo.getValueAt(linha, 0);
            BancoDados.getInstancia().getObras().removeIf(o -> o.getId() == id);
            atualizarTabela();
            JOptionPane.showMessageDialog(this, "Obra removida com sucesso!");
        }
    }
}