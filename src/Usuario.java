import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Usuario implements Emissor, Ouvinte {
    private String nome;
    private String email;
    private String senha;

    public Usuario() {}

    public Usuario(String email, String nome, String senha) throws UsuarioExcecao {
        setEmail(email);
        setNome(nome);
        setSenha(senha);
    }

    public boolean equals(Object usuario) {
        return this.email.equals(((Usuario) usuario).getEmail());
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws UsuarioExcecao {
        UsuarioExcecao.verificarStringVaziaOuNula(email,"Email não pode ser vazio ou nulo!");
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws UsuarioExcecao {
        UsuarioExcecao.verificarStringVaziaOuNula(nome, "Nome não pode ser vazio ou nulo!");
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) throws UsuarioExcecao {
        UsuarioExcecao.verificarStringVaziaOuNula(senha, "Senha não pode ser vazia ou nula!");
        this.senha = senha;
    }

    public void notificar(Ouvinte ouvinte, String mensagem) {
        if (ouvinte == null ||
            ((ouvinte instanceof Grupo) && ((Grupo) ouvinte).getMembros().isEmpty()) ||
            ((ouvinte instanceof Comunidade) && ((Comunidade) ouvinte).getGrupos().isEmpty())) {
            return;
        }
        ouvinte.receber(mensagem);
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("output/log.txt", true));
            Date data = new Date();
            SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String dataFormatada = formatador.format(data);
            bw.append(dataFormatada + " - " + nome + " - " + ouvinte + " - " + mensagem + "\n");
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void receber(String mensagem) {
        System.out.println(mensagem);
    }

    public String toString() {
        return nome;
    }
}
