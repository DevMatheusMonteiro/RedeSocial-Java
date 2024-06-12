public class UsuarioExcecao extends Exception {
    public UsuarioExcecao(String mensagem) {
        super(mensagem);
    }

    public static void verificarStringVaziaOuNula(String atributo, String mensagem) throws UsuarioExcecao {
        if (atributo == null || atributo.isEmpty())
            throw new UsuarioExcecao(mensagem);
    }
}
