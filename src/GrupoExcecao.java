public class GrupoExcecao extends Exception {
    public GrupoExcecao(String mensagem) {
        super(mensagem);
    }

    public static void verificarNomeVazioOuNulo(String nome) throws GrupoExcecao {
        if (nome == null || nome.isEmpty())
            throw new GrupoExcecao("Nome não pode ser vazio ou nulo");
    }
}
