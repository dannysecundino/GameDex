//Adicionar e Modificar obras

package gerenciamentoPrograma.adicionadores;
import obras.Obra;
import usuarios.Usuario;
import usuarios.moderador.Moderador;
import gerenciamentoPrograma.bancoDados.BancoDados;
public class AdicionadorObras {
public void adicionarObra(Usuario autor,Obra obra) throws Exception {
if(autor instanceof Moderador){
    BancoDados.getInstancia().getObras().add(obra);
    System.out.println("Obra adicionado com sucesso!");
}else{
    throw new Exception("Acesso Negado: Apenas Moderadores podem cadastrar obras.");
}
}
}
