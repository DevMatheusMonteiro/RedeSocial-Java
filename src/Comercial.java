public class Comercial extends Usuario{
    private String cnpj;
    public Comercial() {

    }
    public Comercial(String email, String nome, String senha, String cnpj) throws UsuarioExcecao {
        super(email, nome, senha);
        this.cnpj = cnpj;
    }
    public String getCnpj() {
        return cnpj;
    }
    public void setCnpj(String cnpj) throws UsuarioExcecao {
        UsuarioExcecao.verificarStringVaziaOuNula(cnpj, "CNPJ não pode ser vazio ou nulo");
        this.cnpj = cnpj;
    }
}
