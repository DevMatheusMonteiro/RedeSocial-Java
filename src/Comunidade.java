import java.util.*;
import java.util.stream.Collectors;

public class Comunidade implements Ouvinte {
    private String nome;
    private String descricao;
    private Map<String,Grupo> grupos;

    public Comunidade() {
        grupos = new HashMap<>();
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

    public Map<String,Grupo> getGrupos() {
        return grupos;
    }

    public void adicionarGrupo(Grupo grupo) {
        grupos.put(grupo.getNome(),grupo);
    }

    public void removerGrupo(String nome) {
        grupos.remove(nome);
    }
    public int quantidadeMembros() {
        Set<Usuario> membros = new HashSet<>();
        grupos.values().forEach(grupo -> membros.addAll(grupo.getMembros().values()));
        return membros.size();
    }

    public void receber(String mensagem) {
        Set<Usuario> membros = new HashSet<>();
        grupos.values().forEach(grupo -> membros.addAll(grupo.getMembros().values()));
        membros.forEach(m -> m.receber(mensagem));
    }

    public String toString() {
        return nome;
    }
}
