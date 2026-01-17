package interfaceGrafica;

import javax.swing.*;
import java.awt.*;


import gerenciamentoPrograma.gerenciaLogin.LoginAut;
import excecoes.LoginInvalidoException;

public class TelaLogin extends JFrame {

    private JTextField txtLogin;
    private JPasswordField txtSenha;
    private JButton btnLogin, btnCadastrar;
    private JLabel lblTitulo, lblAindaNao;



    public TelaLogin() {
        //configs básicas
        setTitle("GameDex - Login");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        //elementos visuais
        lblTitulo = new JLabel("Efetuar Login");
        lblTitulo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(lblTitulo, gbc);

        gbc.gridwidth = 1; gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Username:"), gbc);

        txtLogin = new JTextField(15);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(txtLogin, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Senha:"), gbc);

        txtSenha = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(txtSenha, gbc);

        btnLogin = new JButton("Log in");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(btnLogin, gbc);
        btnLogin.addActionListener(e -> {
            String login = txtLogin.getText();
            String senha = new String(txtSenha.getPassword());
            try{
                LoginAut.realizarLogin(login, senha);
                JOptionPane.showMessageDialog(this, "Login bem-sucedido! Bem vindo, " + LoginAut.getUsuarioLogado().getNome());
                new TelaPrincipal();
                dispose();
            } catch (LoginInvalidoException ex){
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro de Login", JOptionPane.ERROR_MESSAGE);

                txtLogin.setText("");
                txtSenha.setText("");
                txtLogin.requestFocus();
            }
        });

        //botão de cadastro
        JPanel painelCadastro = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        lblAindaNao = new JLabel("Ainda não possui uma conta?");
        lblAindaNao.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 11));

        btnCadastrar = new JButton("Cadastre-se");
        btnCadastrar.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 11));
        btnCadastrar.setBorderPainted(false);
        btnCadastrar.setContentAreaFilled(false);
        btnCadastrar.setForeground(Color.BLUE);
        btnCadastrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCadastrar.addActionListener(e -> {
            new TelaCadastro();
            setVisible(true);
        });

        painelCadastro.add(lblAindaNao);
        painelCadastro.add(btnCadastrar);

        gbc.gridy = 4;
        add(painelCadastro, gbc);

        setVisible(true);
    }

}
