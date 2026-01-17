package gerenciamentoPrograma.interfaces;

import javax.swing.*;
import java.awt.*;
import gerenciamentoPrograma.gerenciaLogin.LoginAut;
import usuarios.Usuario;
import usuarios.moderador.Moderador;
import usuarios.moderador.superModerador.SuperModerador;

public class TelaSobreMim extends JFrame {
    private JTextField txtNomeCompleto, txtEmail;
    private JLabel lblUsername, lblCargo;
    private JButton btnSalvar, btnVoltar;
    private Usuario userLogado;

    public TelaSobreMim() {
        this.userLogado = LoginAut.getUsuarioLogado();

        setTitle("GameDex - Sobre Mim");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Username:"), gbc);
        lblUsername = new JLabel(userLogado.getLogin());
        gbc.gridx = 1;
        add(lblUsername, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Cargo:"), gbc);
        String cargoNome = "Jogador";
        if (userLogado instanceof SuperModerador) cargoNome = "SuperModerador";
        else if (userLogado instanceof Moderador) cargoNome = "Moderador";
        lblCargo = new JLabel(cargoNome);
        gbc.gridx = 1;
        add(lblCargo, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("Nome Completo:"), gbc);
        txtNomeCompleto = new JTextField(userLogado.getNome(), 15);
        gbc.gridx = 1;
        add(txtNomeCompleto, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        add(new JLabel("E-mail:"), gbc);
        txtEmail = new JTextField(userLogado.getEmail(), 15);
        gbc.gridx = 1;
        add(txtEmail, gbc);

        btnSalvar = new JButton("Salvar Alterações");
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        add(btnSalvar, gbc);

        btnSalvar.addActionListener(e -> {
            userLogado.setNome(txtNomeCompleto.getText());
            userLogado.setEmail(txtEmail.getText());
            JOptionPane.showMessageDialog(this, "Dados atualizados com sucesso!");
            dispose();
        });

        setVisible(true);
    }
}