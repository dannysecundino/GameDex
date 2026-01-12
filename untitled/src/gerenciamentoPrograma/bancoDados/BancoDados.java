package gerenciamentoPrograma.bancoDados;

import obras.Obra;
import usuarios.Usuario;

import java.util.ArrayList;

public class BancoDados {
    private static BancoDados instancia;
    private static ArrayList<Obra> obrasCatalogadas;
    private static ArrayList<Usuario> usuarios;
    public static BancoDados getInstancia() {
        if (instancia == null) instancia = new BancoDados();
        return instancia;
    }

    public ArrayList<Usuario> getUsuarios() { return usuarios; }
    public ArrayList<Obra> getObras() { return obrasCatalogadas; }

}
