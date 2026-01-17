package interfaceGrafica;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import gerenciamentoPrograma.bancoDados.BancoDados;
import gerenciamentoPrograma.gerenciaLogin.LoginAut;
import obras.Obra;
import avaliacao.Avaliacao;
import usuarios.Usuario;

public class TelaMinhasAvaliacoes extends JFrame {
    private JTable tabela;
    private DefaultTableModel modelo;

    public TelaMinhasAvaliacoes() {
        setTitle("GameDex - Minhas Avaliações");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        String[] colunas = {"Obra", "Minha Nota", "Meu Comentário"};
        modelo = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int r, int c) { return false; }
        };
        tabela = new JTable(modelo);
        carregarAvaliacoes();

        add(new JScrollPane(tabela), BorderLayout.CENTER);
        setVisible(true);
    }

    private void carregarAvaliacoes() {
        Usuario logado = LoginAut.getUsuarioLogado();
        for (Obra obra : BancoDados.getInstancia().getObras()) {
            for (Avaliacao av : obra.getAvaliacoes()) {
                if (av.getAutor().getLogin().equals(logado.getLogin())) {
                    modelo.addRow(new Object[]{obra.getTitulo(), av.getNota(), av.getComentario()});
                }
            }
        }
    }
}