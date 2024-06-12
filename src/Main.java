import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try {
            Pessoal usuario1 = new Pessoal("elian@javeiros.com","Elian", "123", "999.999.999-99");
            Pessoal usuario2 = new Pessoal("ana.amelia@javeiros.com","Ana Amélia", "123", "888.888.888-88");
            Pessoal usuario3 = new Pessoal("jose.neto@javeiros.com","José Neto", "123", "777.777.777-77");

            Scanner sc = new Scanner(System.in);
            System.out.print("Informe o email: ");
            String email = sc.nextLine();
            System.out.print("Informe o nome: ");
            String nome = sc.nextLine();
            System.out.print("Informe a senha: ");
            String senha = sc.nextLine();
            System.out.print("Informe o cnpj: ");
            String cnpj = sc.nextLine();

            Comercial usuario4 = new Comercial(email,nome, senha, cnpj);

            Grupo grupo1 = new Grupo("Javeiros, guerreiros, destruidores", "Javeiros de corpo e alma");
            Grupo grupo2 = new Grupo("C raiz", "Porque qualquer outra coisa é overhead");

            System.out.print("Informe o nome do grupo: ");
            String nomeGrupo = sc.nextLine();
            System.out.print("Informe a descrição do grupo: ");
            String descricaoGrupo = sc.nextLine();

            Grupo grupo3 = new Grupo(nomeGrupo, descricaoGrupo);

            grupo1.adicionarMembro(usuario1);
            grupo1.adicionarMembro(usuario2);
            grupo1.adicionarMembro(usuario4);

            grupo2.adicionarMembro(usuario1);
            grupo2.adicionarMembro(usuario3);
            grupo2.adicionarMembro(usuario4);

            grupo3.adicionarMembro(usuario3);
            grupo3.adicionarMembro(usuario4);

            Comunidade comunidade = new Comunidade("Programadores do Iraque", "Programadores nascidos e criados em irajá");

            comunidade.adicionarGrupo(grupo1);
            comunidade.adicionarGrupo(grupo2);
            comunidade.adicionarGrupo(grupo3);

            usuario1.notificar(comunidade, "Salve, salve, galera!");

            usuario4.notificar(grupo1, "Olá, Javeiros");

            grupo1.exportarCSV();
            grupo2.exportarCSV();
            grupo3.exportarCSV();

            System.out.println("Quantidade de membros: " + comunidade.quantidadeMembros());

            /*Comunidade comunidade = new Comunidade("Programadores do Iraque", "Programadores nascidos e criados em irajá");

            Grupo javeiros = new Grupo("Javeiros guerreiros destruidores", "Javeiros de corpo e alma");
            javeiros.importarCSV();

            Grupo cRaiz = new Grupo("C raiz", "Porque qualquer outra coisa é overhead");
            cRaiz.importarCSV();

            comunidade.adicionarGrupo(javeiros);
            comunidade.adicionarGrupo(cRaiz);

            javeiros.membros.get(0).notificar(comunidade, "Salve salve, galerinhaaa");


            System.out.println("Quantidade de membros: " + comunidade.quantidadeMembros());*/
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}