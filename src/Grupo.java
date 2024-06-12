import java.io.*;
import java.util.*;

public class Grupo implements Ouvinte {
    String nome;
    String descricao;

    List<Usuario> membros;

    public Grupo() {
        membros = new ArrayList<>();
    }

    public Grupo(String nome, String descricao) {
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

    public void adicionarMembro(Usuario membro) {
        membros.add(membro);
    }

    public void removerMembro(Usuario membro) {
        membros.remove(membro);
    }

    public void receber(String mensagem) {
        if(!membros.isEmpty()) {
            for (Usuario membro : membros) {
                membro.receber(mensagem);
            }
        }

    }

    public void exportarCSV() {
        try {
            String[] nomeArquivo = nome.replace(",", "").replace(".", "").replace(";", "").split(" ");

            System.out.println();

            BufferedWriter bw = new BufferedWriter(new FileWriter(String.format("output/%s.csv", String.join("", nomeArquivo)), true));

            for (Usuario membro : membros) {
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
            throw new RuntimeException(e);
        }
    }

    public void importarCSV() {
        try {
            String[] nomeArquivo = nome.replace(",", "").replace(".", "").replace(";", "").split(" ");
            BufferedReader br = new BufferedReader(new FileReader(String.format("output/%s.csv", String.join("", nomeArquivo))));

            String linha;
            String[] colunas;
            while ((linha = br.readLine()) != null) {
                colunas = linha.split(",");

                if (colunas[0].equals("Pessoal")){
                    adicionarMembro(new Pessoal(colunas[1], colunas[2], colunas[3], colunas[4]));
                } else {
                    adicionarMembro(new Comercial(colunas[1], colunas[2], colunas[3], colunas[4]));
                }
            }

            br.close();
        } catch (IOException | UsuarioExcecao e) {
            throw new RuntimeException(e);
        }
    }

    public String toString() {
        return nome;
    }
}
