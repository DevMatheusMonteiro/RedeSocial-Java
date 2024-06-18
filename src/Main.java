import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        try {
            Grupo grupo1 = importarGrupo("Javeiros, guerreiros, destruidores", "Javeiros de corpo e alma");
            Grupo grupo2 = importarGrupo("C raiz", "Por que qualquer coisa é overhead");
            Comunidade comunidade = new Comunidade("Programadores do Iraque", "Programadores nascidos e criados em irajá");
            Pessoal usuario1 = new Pessoal("elian@javeiros.com","Elian", "123","999.999.999-99");
            Pessoal usuario2 = new Pessoal("ana.amelia@javeiros.com","Ana Amélia", "123","888.888.888-88");
            Pessoal usuario3 = new Pessoal("jose.neto@javeiros.com","José Neto", "123","777.777.777-77");
            Usuario usuario4 = criarUsuario();
            adicionarMembro(grupo1, usuario1);
            adicionarMembro(grupo1, usuario2);
            adicionarMembro(grupo1, usuario4);
            adicionarMembro(grupo2, usuario1);
            adicionarMembro(grupo2, usuario3);
            adicionarMembro(grupo2, usuario4);
            comunidade.adicionarGrupo(grupo1);
            comunidade.adicionarGrupo(grupo2);
            usuario1.notificar(comunidade, "Salve, salve, galera!");
            usuario4.notificar(grupo1, "Olá, javeiros!");
            grupo1.exportarCSV();
            grupo2.exportarCSV();
            System.out.println("Quantidade de membros: " + comunidade.quantidadeMembros());
        } catch (GrupoExcecao e){
            e.printStackTrace();
        } catch (ComunidadeExcecao e){
            e.printStackTrace();
        } catch (UsuarioExcecao e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static Grupo importarGrupo(String nome, String descricao) throws GrupoExcecao, UsuarioExcecao {
        Grupo grupo = new Grupo(nome, descricao);
        try {
            grupo.importarCSV();
        } catch (IOException e){
            System.out.println("Grupo " + nome + " não existe!");
        }
        return grupo;
    }
    static void adicionarMembro(Grupo grupo, Usuario usuario) throws UsuarioExcecao {
        try {
            grupo.adicionarMembro(usuario);
        } catch (GrupoExcecao e){
            System.out.println(e.getMessage());
            for (Usuario membro : grupo.getMembros()){
                if(membro.equals(usuario)) {
                    usuario.setNome(membro.getNome());
                    break;
                }
            }
        }
    }
    static String entrarTexto(String mensagem) {
        Scanner sc = new Scanner(System.in);
        System.out.print(mensagem);
        return sc.nextLine();
    }
    static int entrarNumeroInteiro(String mensagem) {
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(mensagem);
                int numero = sc.nextInt();
                return numero;
            } catch (InputMismatchException e) {
                System.out.println("Formato inválido!");
            }
        } while (true);
    }
    static void entrarNomeUsuario(Usuario usuario) {
        do {
            try {
                usuario.setNome(entrarTexto("Entre com o nome: "));
                return;
            } catch (UsuarioExcecao e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }
    static void entrarEmailUsuario(Usuario usuario) {
        do {
            try {
                usuario.setEmail(entrarTexto("Entre com o email: "));
                return;
            } catch (UsuarioExcecao e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }
    static void entrarSenhaUsuario(Usuario usuario) {
        do {
            try {
                usuario.setSenha(entrarTexto("Entre com a senha: "));
                return;
            } catch (UsuarioExcecao e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }
    static void entrarCpfUsuario(Pessoal usuario) {
        do {
            try {
                usuario.setCpf(entrarTexto("Entre com o CPF: "));
                return;
            } catch (UsuarioExcecao e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }
    static void entrarCnpjUsuario(Comercial usuario) {
        do {
            try {
                usuario.setCnpj(entrarTexto("Entre com o CNPJ: "));
                return;
            } catch (UsuarioExcecao e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }
    static Usuario criarUsuario() {
        int opcao;
        Usuario usuario = null;
        do {
            opcao = entrarNumeroInteiro("1 - Conta Pessoal\n2 - Conta Comercial\nEscolha: ");
            switch (opcao){
                case 1:
                    usuario = new Pessoal();
                    entrarEmailUsuario(usuario);
                    entrarNomeUsuario(usuario);
                    entrarSenhaUsuario(usuario);
                    entrarCpfUsuario((Pessoal) usuario);
                    break;
                case 2:
                    usuario = new Comercial();
                    entrarEmailUsuario(usuario);
                    entrarNomeUsuario(usuario);
                    entrarSenhaUsuario(usuario);
                    entrarCnpjUsuario((Comercial) usuario);
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (!(opcao == 1 || opcao == 2));
        return usuario;
    }
}