public class Comercial extends Usuario{
    private String cnpj;

    public Comercial(String email, String nome, String senha, String cnpj) throws UsuarioExcecao {
        super(email, nome, senha);
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
