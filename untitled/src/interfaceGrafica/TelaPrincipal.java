package interfaceGrafica;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

import gerenciamentoPrograma.bancoDados.BancoDados;
import gerenciamentoPrograma.gerenciaLogin.LoginAut;
import usuarios.Usuario;
import usuarios.moderador.Moderador;
import usuarios.moderador.superModerador.SuperModerador;
import obras.Obra;
import obras.jogos.Jogos;
import obras.expansao.Expansao;

public class TelaPrincipal extends JFrame {
    private JTable tabelaJogos;
    private DefaultTableModel modeloTabela;

    public TelaPrincipal() {
        setTitle("GameDex - Dashboard");
        setSize(1100, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(0, 122, 204));

        //sidebar (barra lateral)
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setPreferredSize(new Dimension(220, 0));
        //sidebar.setBackground(new Color(45, 45, 48));
        sidebar.setBorder(new EmptyBorder(20, 10, 20, 10));

        JLabel lblLogo = new JLabel(" GAMEDEX");
        lblLogo.setForeground(new Color(0, 122, 204));
        lblLogo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblLogo.setAlignmentX(Component.CENTER_ALIGNMENT);

        sidebar.add(lblLogo);
        sidebar.add(Box.createRigidArea(new Dimension(0, 40)));

        //botões na sidebar
        sidebar.add(criarBotaoMenu("Catálogo"));
        sidebar.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton btnMinhasAv = criarBotaoMenu("Minhas Avaliações");
        btnMinhasAv.addActionListener(e -> new TelaMinhasAvaliacoes());
        sidebar.add(btnMinhasAv);

        sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
        JButton btnSobreMim = criarBotaoMenu("Sobre Mim");
        btnSobreMim.addActionListener(e -> new TelaSobreMim());
        sidebar.add(btnSobreMim);

        Usuario userLogado = LoginAut.getUsuarioLogado();

        if (userLogado instanceof Moderador) {
            sidebar.add(Box.createRigidArea(new Dimension(0, 10)));


            JButton btnGerenciar = criarBotaoMenu("Adicionar Jogo");
            btnGerenciar.setText("Gerenciar Catálogo");

            btnGerenciar.addActionListener(e -> new TelaGerenciarCatalogo());

            sidebar.add(btnGerenciar);
        }
        if (userLogado instanceof SuperModerador) {
            sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
            JButton btnAdmin = criarBotaoMenu("Administração");
            btnAdmin.addActionListener(e -> new TelaGestaoUsuarios());
            sidebar.add(btnAdmin);
        }

        sidebar.add(Box.createVerticalGlue()); // Empurra o logout para o fim
        JButton btnLogout = new JButton("Sair");
        btnLogout.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnLogout.addActionListener(e -> { LoginAut.realizarLogout(); new TelaLogin(); dispose(); });
        sidebar.add(btnLogout);

        //área central
        JPanel contentArea = new JPanel(new BorderLayout(20, 20));
        contentArea.setBorder(new EmptyBorder(25, 25, 25, 25));

        JLabel lblTitulo = new JLabel("Catálogo de Obras");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitulo.setForeground(new Color(0, 122, 204));
        contentArea.add(lblTitulo, BorderLayout.NORTH);

        //tabela de jogos
        String[] colunas = {"Id", "Título", "Ano", "Desenvolvedor", "Gênero", "Plataforma", "Média"};
        modeloTabela = new DefaultTableModel(colunas, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };

        tabelaJogos = new JTable(modeloTabela);
        estilizarTabela(tabelaJogos);
        tabelaJogos.removeColumn(tabelaJogos.getColumnModel().getColumn(0)); //para ocultar a coluna dos IDs
        atualizarTabela();

        JScrollPane scrollPane = new JScrollPane(tabelaJogos);
        contentArea.add(scrollPane, BorderLayout.CENTER);

        //botão "ver detalhes"
        JButton btnVer = new JButton("Ver Detalhes da Obra");
        btnVer.setPreferredSize(new Dimension(200, 40));
        btnVer.setBackground(new Color(0, 122, 204));
        btnVer.setForeground(Color.WHITE);
        btnVer.setFocusPainted(false);
        btnVer.setFont(new Font("Segoe UI", Font.BOLD, 14));
        contentArea.add(btnVer, BorderLayout.SOUTH);

        btnVer.addActionListener(e -> {
            int linha = tabelaJogos.getSelectedRow();
            if (linha != -1) {
                int idBuscado = Integer.parseInt(modeloTabela.getValueAt(linha, 0).toString());
                for (Obra obra : BancoDados.getInstancia().getObras()) {
                    if (obra.getId() == idBuscado) {
                        if (obra instanceof Jogos) {
                            new TelaDetalhesJogo((Jogos) obra);
                        } else if (obra instanceof Expansao) {
                            Expansao dlc = (Expansao) obra;
                            String info = "DLC: " + dlc.getTitulo() +
                                    "\nAno: " + dlc.getAno() +
                                    "\nDesenvolvedora: " + dlc.getDesenvolvedor() +
                                    "\n\nESTA É UMA EXPANSÃO DE: " + dlc.getJogoBase().getTitulo();

                            JOptionPane.showMessageDialog(this, info, "Detalhes da DLC", JOptionPane.INFORMATION_MESSAGE);
                        }
                        return;
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecione uma obra na tabela!");
            }
        });

        //montagem
        add(sidebar, BorderLayout.WEST);
        add(contentArea, BorderLayout.CENTER);

        setVisible(true);

        //quando uma review é feita, o valor da média precisa ser atualizado.
        //o seguinte código atualiza os valores da tabela sempre que o foco alterna para a tela principal
        this.addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            @Override
            public void windowGainedFocus(java.awt.event.WindowEvent e) {
                atualizarTabela();
            }

            @Override
            public void windowLostFocus(java.awt.event.WindowEvent e) {}
        });
    }

    private JButton criarBotaoMenu(String texto) {
        JButton btn = new JButton(texto);
        btn.setMaximumSize(new Dimension(200, 40));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(0, 122, 204));
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private void estilizarTabela(JTable tabela) {
        tabela.setBackground(Color.WHITE);
        tabela.setForeground(Color.BLACK);
        tabela.setRowHeight(30);
        tabela.setGridColor(new Color(200, 200, 200));
        tabela.getTableHeader().setBackground(new Color(0, 122, 204));
        tabela.getTableHeader().setForeground(Color.WHITE);
        tabela.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tabela.setSelectionBackground(new Color(100, 100, 100));
    }

    private void atualizarTabela() {
        modeloTabela.setRowCount(0);
        ArrayList<Obra> obras = BancoDados.getInstancia().getObras();

        for (Obra o : obras) {
            String tipo = "";
            String generoPlataforma = "-";

            if (o instanceof Jogos j) {
                tipo = "Jogo";
                generoPlataforma = j.getGenero() + " / " + j.getPlataforma();
            } else if (o instanceof Expansao e) {
                tipo = "DLC (Base: " + e.getJogoBase().getTitulo() + ")";
                generoPlataforma = "Expansão";
            }

            Object[] row = {
                    o.getId(),
                    o.getTitulo(),
                    o.getAno(),
                    o.getDesenvolvedor(),
                    tipo,
                    generoPlataforma,
                    String.format("%.1f", o.getMedia())
            };
            modeloTabela.addRow(row);
        }
    }
}

