package interfaceGrafica;

import javax.swing.*;
import java.awt.*;

import gerenciamentoPrograma.bancoDados.BancoDados;
import obras.jogos.Jogos;
import obras.expansao.Expansao;
import obras.Obra;

public class TelaFormularioDLC extends JFrame {
    private JTextField txtTitulo, txtDev, txtAno;
    private JComboBox<Jogos> comboJogoBase;
    private JButton btnSalvar;

    public TelaFormularioDLC() {
        setTitle("GameDex - Nova DLC");
        setSize(450, 350);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        adicionarCampo("Título da DLC:", txtTitulo = new JTextField(15), gbc, 0);
        adicionarCampo("Desenvolvedora:", txtDev = new JTextField(15), gbc, 1);
        adicionarCampo("Ano de Lançamento:", txtAno = new JTextField(15), gbc, 2);

        // Carregar apenas JOGOS para o combo box
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 1;
        add(new JLabel("Jogo Base:"), gbc);

        comboJogoBase = new JComboBox<>();
        for (Obra o : BancoDados.getInstancia().getObras()) {
            if (o instanceof Jogos) {
                comboJogoBase.addItem((Jogos) o);
            }
        }
        gbc.gridx = 1;
        add(comboJogoBase, gbc);

        btnSalvar = new JButton("Salvar DLC");
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        add(btnSalvar, gbc);

        btnSalvar.addActionListener(e -> salvar());
        setVisible(true);
    }

    private void adicionarCampo(String rotulo, JTextField campo, GridBagConstraints gbc, int linha) {
        gbc.gridwidth = 1; gbc.gridx = 0; gbc.gridy = linha;
        add(new JLabel(rotulo), gbc);
        gbc.gridx = 1;
        add(campo, gbc);
    }

    private void salvar() {
        try {
            Jogos jogoBase = (Jogos) comboJogoBase.getSelectedItem();
            if (jogoBase == null) {
                JOptionPane.showMessageDialog(this, "É necessário um jogo base para cadastrar uma DLC!");
                return;
            }

            String titulo = txtTitulo.getText();
            String dev = txtDev.getText();
            int ano = Integer.parseInt(txtAno.getText());

            int novoId = BancoDados.getInstancia().getObras().size() + 1;
            Expansao novaDLC = new Expansao(novoId, titulo, dev, ano, jogoBase);

            BancoDados.getInstancia().getObras().add(novaDLC);
            JOptionPane.showMessageDialog(this, "DLC cadastrada com sucesso!");
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ano inválido!");
        }
    }
}