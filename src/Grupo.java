import java.io.*;
import java.util.*;

public class Grupo implements Ouvinte {
    private String nome;
    private String descricao;

    private Map<String,Usuario> membros;

    public Grupo() {
        membros = new HashMap<>();
    }

    public Grupo(String nome, String descricao) throws GrupoExcecao{
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
    public void setNome(String nome) throws GrupoExcecao{
        GrupoExcecao.verificarNomeVazioOuNulo(nome);
        this.nome = nome;
    }
    public Map<String,Usuario> getMembros() {
        return membros;
    }

    public void adicionarMembro(Usuario membro) {
        if(!membros.containsKey(membro.getEmail())){
            membros.put(membro.getEmail(), membro);
        }
    }
    public void removerMembro(String email) {
        membros.remove(email);
    }

    public void receber(String mensagem) {
        if(!membros.isEmpty()) {
            for (Usuario membro : membros.values()) {
                membro.receber(mensagem);
            }
        }
    }

    public void exportarCSV() throws IOException {
        try {
            String[] nomeArquivo = nome.replace(",", "").replace(".", "").replace(";", "").split(" ");

            BufferedWriter bw = new BufferedWriter(new FileWriter(String.format("output/%s.csv", String.join("", nomeArquivo).toLowerCase())));

            for (Usuario membro : membros.values()) {
                if (membro instanceof Pessoal){
                    bw.append("Pessoal" +
                            "," + membro.getEmail() +
                            "," + membro.getNome() +
                            "," + membro.getSenha() +
                            "," + ((Pessoal) membro).getCpf() + "\n");
                } else {
                    bw.append("Comercial" +
                            "," + membro.getEmail() +
                            "," + membro.getNome() +
                            "," + membro.getSenha() +
                            "," + ((Comercial) membro).getCnpj() + "\n");
                }
            }
            bw.close();
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    public void importarCSV() throws UsuarioExcecao, IOException {
        try {
            String[] nomeArquivo = nome.replace(",", "").replace(".", "").replace(";", "").split(" ");
            BufferedReader br = new BufferedReader(new FileReader(String.format("output/%s.csv", String.join("", nomeArquivo).toLowerCase())));

            String linha;
            String[] colunas;
            while ((linha = br.readLine()) != null) {
                colunas = linha.split(",");

                if (colunas[0].equals("Pessoal")){
                    membros.put(colunas[1],new Pessoal(colunas[1], colunas[2], colunas[3], colunas[4]));
                } else {
                    membros.put(colunas[1],new Comercial(colunas[1], colunas[2], colunas[3], colunas[4]));
                }
            }

            br.close();
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        } catch (UsuarioExcecao e){
            throw new UsuarioExcecao(e.getMessage());
        }
    }

    public String toString() {
        return nome;
    }
}
