import java.util.*;

public class Comunidade implements Ouvinte {
    private String nome;
    private String descricao;
    List<Grupo> grupos;

    public Comunidade() {
        grupos = new ArrayList<>();
    }

    public Comunidade(String nome, String descricao) {
        this();
        this.nome = nome;
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

    public void setNome(String nome) {
        this.nome = nome;
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
            for(Usuario membro : grupos.get(i).membros){
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
                for(Usuario membro : grupos.get(i).membros){
                    if(!emails.contains(membro.getEmail())){
                        emails.add(membro.getEmail());
                        membro.receber(mensagem);
                    }
                }
            } else{
                grupos.get(i).receber(mensagem);
                for(Usuario membro : grupos.get(i).membros) {
                    emails.add(membro.getEmail());
                }
            }
        }
    }

    public String toString() {
        return nome;
    }
}
