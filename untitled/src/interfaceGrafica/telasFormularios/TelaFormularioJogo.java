package interfaceGrafica.telasFormularios;

import gerenciamentoPrograma.bancoDados.BancoDados;
import obras.jogo.Jogo;

import javax.swing.*;
import java.awt.*;

public class TelaFormularioJogo extends JFrame {
    private JTextField txtTitulo, txtDev, txtAno, txtGen, txtPlat;
    private JComboBox<Jogo> comboJogoBase;
    private JButton btnSalvar;

    public TelaFormularioJogo() {
        //icone da tela
        Image icon = Toolkit.getDefaultToolkit()
                .getImage(getClass().getResource("/images/logo.png"));
        setIconImage(icon);

        setTitle("GameDex - Novo Jogo");
        setSize(450, 350);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        adicionarCampo("Título do Jogo:", txtTitulo = new JTextField(15), gbc, 0);
        adicionarCampo("Desenvolvedora:", txtDev = new JTextField(15), gbc, 1);
        adicionarCampo("Ano de Lançamento:", txtAno = new JTextField(15), gbc, 2);
        adicionarCampo("Gênero:", txtGen = new JTextField(15), gbc, 3);
        adicionarCampo("Plataforma:", txtPlat = new JTextField(15), gbc, 4);

        btnSalvar = new JButton("Salvar Jogo");
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
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
            String titulo = txtTitulo.getText();
            String dev = txtDev.getText();
            int ano = Integer.parseInt(txtAno.getText());
            String gen = txtGen.getText();
            String plat = txtPlat.getText();


            int novoId = BancoDados.getInstancia().getObras().size() + 1;
            Jogo novoJogo = new Jogo(novoId, titulo, dev, ano, gen, plat);

            BancoDados.getInstancia().getObras().add(novoJogo);
            JOptionPane.showMessageDialog(this, "Jogo cadastrado com sucesso!");
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ano inválido!");
        }
    }
}