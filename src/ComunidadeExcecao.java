public class ComunidadeExcecao extends Exception {
    public ComunidadeExcecao(String mensagem) {
        super(mensagem);
    }

    public static void verificarNomeVazioOuNulo(String nome) throws ComunidadeExcecao {
        if (nome == null || nome.isEmpty())
            throw new ComunidadeExcecao("Nome não pode ser vazio ou nulo");
    }
}
