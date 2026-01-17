package interfaceGrafica;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import obras.jogos.Jogo;
import avaliacao.Avaliacao;
import gerenciamentoPrograma.gerenciaLogin.LoginAut;
import usuarios.Usuario;
import excecoes.NotaInvalidaException;

public class TelaDetalhesJogo extends JFrame {
    private Jogo jogo;
    private Usuario userLogado;
    private JPanel painelComentarios;
    private JLabel lblMedia;

    public TelaDetalhesJogo(Jogo jogo) {
        this.jogo = jogo;
        this.userLogado = LoginAut.getUsuarioLogado();

        //principal
        setTitle("GameDex - Detalhes do Jogo");
        setSize(550, 750);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(240, 240, 240));

        //painel superior
        JPanel painelInfo = new JPanel(new GridLayout(0, 1, 5, 5));
        painelInfo.setBackground(Color.WHITE);
        painelInfo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 2, 0, Color.LIGHT_GRAY),
                new EmptyBorder(20, 25, 20, 25)
        ));

        JLabel lblTitulo = new JLabel(jogo.getTitulo().toUpperCase());
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 22));
        lblTitulo.setForeground(new Color(0, 102, 244));

        lblMedia = new JLabel(String.format("Nota Média: %.1f / 10.0 ⭐", jogo.getMedia()));
        lblMedia.setFont(new Font("SansSerif", Font.BOLD, 16));

        painelInfo.add(lblTitulo);
        painelInfo.add(new JLabel("Ano: " + jogo.getAno()));
        painelInfo.add(new JLabel("Desenvolvedora: " + jogo.getDesenvolvedor()));
        // Como plataforma é private em Jogos, usamos o exibirDetalhes para extrair ou use getter se criou
        painelInfo.add(new JLabel("Plataforma: " + jogo.exibirDetalhes().split("\n")[4].split(": ")[1]));
        painelInfo.add(lblMedia);

        add(painelInfo, BorderLayout.NORTH);

        //centro: lista das reviews
        painelComentarios = new JPanel();
        painelComentarios.setLayout(new BoxLayout(painelComentarios, BoxLayout.Y_AXIS));
        painelComentarios.setBackground(new Color(240, 240, 240));

        JScrollPane scroll = new JScrollPane(painelComentarios);
        scroll.setBorder(new EmptyBorder(10, 10, 10, 10));
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        add(scroll, BorderLayout.CENTER);

        //painel inferior, botão de avaliar
        JPanel painelBotao = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelBotao.setOpaque(false);
        JButton btnAcao = new JButton();
        configurarBotaoPrincipal(btnAcao);
        painelBotao.add(btnAcao);
        add(painelBotao, BorderLayout.SOUTH);

        atualizarLista();
        setVisible(true);
    }

    private void atualizarLista() {
        painelComentarios.removeAll();
        lblMedia.setText(String.format("Nota Média: %.1f / 10.0 ⭐", jogo.getMedia()));

        for (Avaliacao av : jogo.getAvaliacoes()) {
            JPanel card = new JPanel(new BorderLayout(10, 10));
            card.setBackground(Color.WHITE);
            card.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                    new EmptyBorder(10, 15, 10, 15)
            ));
            card.setMaximumSize(new Dimension(500, 100));

            //funcionalidade: se for a review do Autor, ganha destaquezinho.
            boolean isDoAutor = av.getAutor().getLogin().equals(userLogado.getLogin());
            String autorNome = isDoAutor ? "VOCÊ" : av.getAutor().getNome();

            JLabel lblAutor = new JLabel(autorNome + " deu nota " + av.getNota() + "/10");
            lblAutor.setFont(new Font("SansSerif", Font.BOLD, 13));
            if(isDoAutor) lblAutor.setForeground(new Color(76, 175, 80));

            JTextArea txtComent = new JTextArea(av.getComentario());
            txtComent.setEditable(false);
            txtComent.setLineWrap(true);
            txtComent.setWrapStyleWord(true);
            txtComent.setBackground(Color.WHITE);

            card.add(lblAutor, BorderLayout.NORTH);
            card.add(txtComent, BorderLayout.CENTER);

            painelComentarios.add(card);
            painelComentarios.add(Box.createRigidArea(new Dimension(0, 10)));
        }
        painelComentarios.revalidate();
        painelComentarios.repaint();
    }

    private void configurarBotaoPrincipal(JButton btn) {
        Avaliacao minhaReview = buscarReviewUsuario();

        if (minhaReview == null) {
            btn.setText("ESCREVER AVALIAÇÃO");
            btn.setBackground(new Color(33, 150, 243));
        } else {
            btn.setText("EDITAR MINHA AVALIAÇÃO");
            btn.setBackground(new Color(255, 152, 0));
        }

        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("SansSerif", Font.BOLD, 14));
        btn.setPreferredSize(new Dimension(250, 40));

        //remover o actionlistener caso já exista para evitar 2 simultaneamente
        //adicionar review e editar review ocorrem no mesmo botão
        for(java.awt.event.ActionListener al : btn.getActionListeners()) btn.removeActionListener(al);

        btn.addActionListener(e -> {
            abrirDialogoReview(minhaReview);
            configurarBotaoPrincipal(btn);
        });
    }

    private Avaliacao buscarReviewUsuario() {
        for (Avaliacao a : jogo.getAvaliacoes()) {
            if (a.getAutor().getLogin().equals(userLogado.getLogin())) return a;
        }
        return null;
    }

    private void abrirDialogoReview(Avaliacao existente) {

        //recuperar a avaliação já feita caso ela exista
        JTextField campoNota = new JTextField(existente != null ? String.valueOf(existente.getNota()) : "");
        JTextArea campoTexto = new JTextArea(existente != null ? existente.getComentario() : "", 5, 20);
        campoTexto.setLineWrap(true);

        Object[] corpo = { "Nota (0 a 10):", campoNota, "Seu comentário:", new JScrollPane(campoTexto) };

        int op = JOptionPane.showConfirmDialog(this, corpo, "Sua Avaliação", JOptionPane.OK_CANCEL_OPTION);

        if (op == JOptionPane.OK_OPTION) {
            try {
                int nota = Integer.parseInt(campoNota.getText()); //nota lida como string, mas processada como int
                String comentario = campoTexto.getText();

                if (existente != null) { //trocar uma avaliacao pela outra
                    jogo.removerAvaliacao(existente);
                }

                Avaliacao nova = new Avaliacao(nota, userLogado, comentario);
                jogo.adicionarAvaliacao(nova);
                atualizarLista();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "A nota precisa ser um número inteiro.");
            } catch (NotaInvalidaException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }
    }
}