import java.io.*;

public class ArquivoTexto {
    public void escrever(String path, String texto) throws IOException {
        BufferedWriter br = new BufferedWriter(new FileWriter(path, true));
        br.append(texto + "\n");
    }
}
