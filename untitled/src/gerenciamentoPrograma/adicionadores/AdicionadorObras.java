//Adicionar e Modificar obras

package gerenciamentoPrograma.adicionadores;
import excecoes.AcessoNegadoException;
import obras.Obra;
import usuarios.Usuario;
import usuarios.moderador.Moderador;
import gerenciamentoPrograma.bancoDados.BancoDados;
public class AdicionadorObras {
    public void adicionarObra(Usuario autor,Obra obra) throws AcessoNegadoException {
        if(autor instanceof Moderador){
            BancoDados.getInstancia().getObras().add(obra);
            System.out.println("Obra adicionado com sucesso!");
        }else{
            throw new AcessoNegadoException("Acesso Negado: Apenas Moderadores podem cadastrar obras.");
        }
    }
}
