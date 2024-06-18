import java.util.*;

public class Comunidade implements Ouvinte {
    private String nome;
    private String descricao;
    private List<Grupo> grupos;

    public Comunidade() {
        grupos = new ArrayList<>();
    }

    public Comunidade(String nome, String descricao) throws ComunidadeExcecao {
        this();
        setNome(nome);
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws ComunidadeExcecao {
        ComunidadeExcecao.verificarNomeVazioOuNulo(nome);
        this.nome = nome;
    }

    public List<Grupo> getGrupos() {
        return grupos;
    }

    public void adicionarGrupo(Grupo grupo) {
        grupos.add(grupo);
    }

    public void removerGrupo(Grupo grupo) {
        grupos.remove(grupo);
    }
    public int quantidadeMembros() {
        List<String> emails = new ArrayList<>();
        for(int i = 0; i < grupos.size(); i++) {
            for(Usuario membro : grupos.get(i).getMembros()){
                if(!emails.contains(membro.getEmail())){
                    emails.add(membro.getEmail());
                }
            }
        }
        return emails.size();
    }

    public void receber(String mensagem) {
        List<String> emails = new ArrayList<>();
        for(int i = 0; i < grupos.size(); i++) {
            if(i != 0) {
                for(Usuario membro : grupos.get(i).getMembros()){
                    if(!emails.contains(membro.getEmail())){
                        emails.add(membro.getEmail());
                        membro.receber(mensagem);
                    }
                }
            } else {
                grupos.get(i).receber(mensagem);
                for(Usuario membro : grupos.get(i).getMembros()) {
                    emails.add(membro.getEmail());
                }
            }
        }
    }

    public String toString() {
        return nome;
    }
}
