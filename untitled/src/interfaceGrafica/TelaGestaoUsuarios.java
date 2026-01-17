package interfaceGrafica;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import gerenciamentoPrograma.bancoDados.BancoDados;
import usuarios.Usuario;
import usuarios.moderador.superModerador.SuperModerador;
import usuarios.moderador.Moderador;
import gerenciamentoPrograma.gerenciaLogin.LoginAut;
import excecoes.AcessoNegadoException;

public class TelaGestaoUsuarios extends JFrame {
    private JTable tabela;
    private DefaultTableModel modelo;

    public TelaGestaoUsuarios() {
        setTitle("GameDex - Gestão de Usuários");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        String[] colunas = {"Nome", "Username", "Cargo"};
        modelo = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        tabela = new JTable(modelo);
        atualizarTabela();

        add(new JScrollPane(tabela), BorderLayout.CENTER);

        JPanel painelAcoes = new JPanel();
        JButton btnPromover = new JButton("Promover para Moderador");
        JButton btnRebaixar = new JButton("Rebaixar para Jogador");

        painelAcoes.add(btnPromover);
        painelAcoes.add(btnRebaixar);
        add(painelAcoes, BorderLayout.SOUTH);

        btnPromover.addActionListener(e -> realizarAcao(true));
        btnRebaixar.addActionListener(e -> realizarAcao(false));

        setVisible(true);
    }

    private void atualizarTabela() {
        modelo.setRowCount(0);
        for (Usuario u : BancoDados.getInstancia().getUsuarios()) {
            String cargo = "Jogador";
            if (u instanceof SuperModerador) cargo = "SuperModerador";
            else if (u instanceof Moderador) cargo = "Moderador";

            modelo.addRow(new Object[]{u.getNome(), u.getLogin(), cargo});
        }
    }

    private void realizarAcao(boolean promover) {
        int linha = tabela.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um usuário!");
            return;
        }

        String login = (String) modelo.getValueAt(linha, 1);
        BancoDados bd = BancoDados.getInstancia();
        Usuario alvo = null;
        int indexAlvo = -1;

        for (int i = 0; i < bd.getUsuarios().size(); i++) {
            if (bd.getUsuarios().get(i).getLogin().equals(login)) {
                alvo = bd.getUsuarios().get(i);
                indexAlvo = i;
                break;
            }
        }

        SuperModerador admin = (SuperModerador) gerenciamentoPrograma.gerenciaLogin.LoginAut.getUsuarioLogado();

        if (promover) {
            if (!(alvo instanceof Moderador)) {
                admin.promoverParaModerador(alvo);
                // IMPORTANTE: O admin cria um novo objeto, precisamos de o substituir na lista
                for (Usuario u : bd.getUsuarios()) {
                    if (u.getLogin().equals(login) && u instanceof Moderador) {
                        // O objeto já foi trocado pela lógica do SuperModerador
                        break;
                    }
                }
            }
        } else {
            if (alvo instanceof Moderador && !(alvo instanceof SuperModerador)) {
                admin.rebaixarParaJogador((Moderador) alvo);
            }
        }

        atualizarTabela();
        JOptionPane.showMessageDialog(this, "Operação concluída! O cargo foi atualizado.");
    }

    private void realizarRemocao() {
        try {
            Usuario userLogado = LoginAut.getUsuarioLogado();

            // Verificação de segurança (Ponto 3)
            if (!(userLogado instanceof Moderador)) {
                throw new AcessoNegadoException("Apenas moderadores podem remover obras!");
            }

            int linha = tabela.getSelectedRow();
            if (linha == -1) {
                JOptionPane.showMessageDialog(this, "Selecione uma obra!");
                return;
            }

            int confirmacao = JOptionPane.showConfirmDialog(this, "Excluir obra?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (confirmacao == JOptionPane.YES_OPTION) {
                int id = (int) modelo.getValueAt(linha, 0);
                BancoDados.getInstancia().getObras().removeIf(o -> o.getId() == id);
                atualizarTabela();
            }

        } catch (AcessoNegadoException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro de Permissão", JOptionPane.ERROR_MESSAGE);
        }
    }
}