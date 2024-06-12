public class Pessoal extends Usuario{
    private String cpf;

    public Pessoal(String email, String nome, String senha, String cpf) throws UsuarioExcecao {
        super(email, nome, senha);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
