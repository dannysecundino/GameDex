package interfaceGrafica.telasDadosUsuarios;

import javax.swing.*;
import java.awt.*;

import gerenciamentoPrograma.adicionadores.AdicionadorUsuario;
import excecoes.CadastroInvalidoException;
import usuarios.Usuario;

public class TelaCadastro extends JFrame{

    private JTextField txtNome, txtEmail, txtUsername;
    private JPasswordField txtSenha;
    private JButton btnConfirmar, btnVoltar;
    private JLabel lblTitulo;

    public TelaCadastro() {
        //icone da tela
        Image icon = Toolkit.getDefaultToolkit()
                .getImage(getClass().getResource("/images/logo.png"));
        setIconImage(icon);

        //configs basicas
        setTitle("GameDex - Cadastro");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Fecha apenas esta janela
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        //elementos visuais
        lblTitulo = new JLabel("Cadastre-se");
        lblTitulo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(lblTitulo, gbc);

        adicionarCampo("Nome:", txtNome = new JTextField(15), gbc, 1);
        adicionarCampo("Email:", txtEmail = new JTextField(15), gbc, 2);
        adicionarCampo("Username:", txtUsername = new JTextField(15), gbc, 3);
        adicionarCampo("Senha:", txtSenha = new JPasswordField(15), gbc, 4);

        btnConfirmar = new JButton("Confirmar Cadastro");
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        add(btnConfirmar, gbc);

        btnConfirmar.addActionListener(e -> realizarCadastro());

        setVisible(true);

        //botão para voltar
        btnVoltar = new JButton("Voltar");
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        add(btnVoltar, gbc);

        btnVoltar.addActionListener(e -> {
            dispose();
        });
    }

    private void adicionarCampo(String rotulo, JComponent campo, GridBagConstraints gbc, int linha) {
        gbc.gridwidth = 1; gbc.gridx = 0; gbc.gridy = linha;
        add(new JLabel(rotulo), gbc);
        gbc.gridx = 1;
        add(campo, gbc);
    }

    private void realizarCadastro() {
        String nome = txtNome.getText();
        String email = txtEmail.getText();
        String user = txtUsername.getText();
        String senha = new String(txtSenha.getPassword());

        if(nome.isEmpty() || user.isEmpty() || email.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
            return;
        }

        Usuario novo = new Usuario(nome, email, user, senha);
        try{
            AdicionadorUsuario.adicionarUsuario(novo);
            JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso!");
            dispose();
        }
        catch(CadastroInvalidoException ex){
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro no Cadastro", JOptionPane.WARNING_MESSAGE);
        }

    }

}
