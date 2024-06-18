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
        List<Usuario> membros = new ArrayList<>();
        for(int i = 0; i < grupos.size(); i++) {
            if(i == 0){
                membros.addAll(grupos.get(i).getMembros());
            } else {
                for (Usuario usuario : grupos.get(i).getMembros()) {
                    if(!membros.contains(usuario)){
                        membros.add(usuario);
                    }
                }
            }
        }
        return membros.size();
    }

    public void receber(String mensagem) {
        List<Usuario> membros = new ArrayList<>();
        for(int i = 0; i < grupos.size(); i++) {
            if(i != 0) {
                for(Usuario membro : grupos.get(i).getMembros()){
                    if(!membros.contains(membro)){
                        membros.add(membro);
                        membro.receber(mensagem);
                    }
                }
            } else {
                grupos.get(i).receber(mensagem);
                membros.addAll(grupos.get(i).getMembros());
            }
        }
    }

    public String toString() {
        return nome;
    }
}
