import java.io.IOException;
import java.net.SocketException;
import java.util.Scanner;
import org.apache.commons.net.ftp.FTPClient;

public class App {
    public static void main(String[] args) throws SocketException, IOException {
        Scanner entry = new Scanner(System.in);
        int option = -1; // Controlar as operações do usuário enquanto a aplicação roda

        FTPClient ftp = new FTPClient(); // Conexão com o servidor
        Connection connection = new Connection(); // Configuração do servidor

        ftp.connect(connection.getActual_ip()); // Setando IP
        ftp.login(connection.getUsername(),connection.getPassword()); // Fazendo Login

        if(ftp.isConnected()) { // Valida se o usuário conseguiu conectar-se ao servidor ou não
            Controller controller = new Controller();
            while(option != 0){
                System.out.println("Digite a opção desejada");
                System.out.println("1 - Upar um arquivo");
                System.out.println("2 - Baixar um arquivo");
                System.out.println("3 - Criar um diretório");
                System.out.println("4 - Deletar um diretório");
                System.out.println("5 - Renomear um diretório");
                System.out.println("6 - Criar um arquivo");
                System.out.println("7 - Renomear um arquivo");
                System.out.println("8 - Deletar um arquivo");

                option = entry.nextInt();

                switch(option){
                case 1:
                    //Upload
                    System.out.println("Informe o local onde está o arquivo na sua máquina");
                    String path_origin = entry.next();
                    System.out.println("Informe o local para onde irá o arquivo no FTP");
                    String path_to_ftp = entry.next();
                    controller.uploadFile(ftp, path_to_ftp, path_origin);
                    break;
                case 2:
                    //Download
                    System.out.println("Informe o local onde está o arquivo no FTP");
                    String path_retrive = entry.next();
                    System.out.println("Informe o local para onde irá o arquivo na sua máquina");           
                    String path_download = entry.next();
                    controller.downloadFile(ftp, path_retrive, path_download);
                    break;
                case 3:
                    // Criar Diretório
                    System.out.println("Informe o nome do diretório à ser criado");
                    String name_file = entry.next();
                    controller.createDir(ftp, name_file);
                    break;
                case 4:
                    // Deletar Diretório
                    System.out.println("Informe o nome do diretório à ser deletado");
                    name_file = entry.next();
                    controller.deleteDir(ftp, name_file);
                    break;
                case 5:
                    // Renomear Diretório
                    System.out.println("Informe o nome atual do diretório à ser renomeado");
                    String old_name = entry.next();
                    System.out.println("Informe o novo nome do diretório à");
                    String new_name = entry.next();
                    controller.renameDir(ftp, old_name, new_name);
                    break;
                case 6:
                    // Criar Arquivo
                    System.out.println("Um novo arquivo será criado");
                    controller.createFile(ftp);
                    break;
                case 7:
                    // Renomear Arquivo
                    System.out.println("Informe o nome atual do arquivo à ser renomeado");
                    old_name = entry.next();
                    System.out.println("Informe o novo nome do diretório à");
                    new_name = entry.next();
                    controller.renameFile(ftp, old_name, new_name);
                    break;
                case 8:
                    // Deletar Arquivo
                    System.out.println("Informe o nome do arquivo à ser deletado");
                    name_file = entry.next();
                    controller.deletFile(ftp, name_file);
                    break;
                default:
                    System.out.println("As opções são entre 1 e 8, digite 0 para sair");  
                }
            }  
            System.out.println("Você saiu");
            entry.close();
            ftp.logout();
            ftp.disconnect();       
        } else {
            System.out.println("Erro ao conectar-se no servidor");
        }
    }
}
/*TODO: 
 * 1 - Realizar Download e Upload de um arquivo do servidor FTP (2/2) - OK
 * 2 - Possível Criar, renomear e remover diretórios do servidor FTP (3/3) - OK
 * 3 - Possível Criar, renomear e remover arquivos do servidor FTP (3/3) - OK
 * 4 - Listar os arquivos contidos no servidor FTP (0/1)
 * 5 - Listar os diretorios contidos no servidor FTP (0/1)
 * Regras - No máximo 5 pastas no servidor FTP, no máximo 3 niveis de subpastas e cada subpasta pode ter no máximo 2 arquivos.
 */