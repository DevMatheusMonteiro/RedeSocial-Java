import java.util.List;

public class GrupoExcecao extends Exception {
    public GrupoExcecao(String mensagem) {
        super(mensagem);
    }

    public static void verificarNomeVazioOuNulo(String nome) throws GrupoExcecao {
        if (nome == null || nome.isEmpty())
            throw new GrupoExcecao("Nome não pode ser vazio ou nulo");
    }
    public static void verificarMembroExistente(List<Usuario> membros, Usuario membro) throws GrupoExcecao {
        if(membros.contains(membro)){
            throw new GrupoExcecao("Já existe um membro com esse email no grupo!");
        }
    }
}
