import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Random;

import javax.swing.filechooser.FileFilter;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPFileFilter;

public class Controller {
    
    public void uploadFile(FTPClient ftp, String path_to_ftp, String path_origin){
        // Fazer upload de um arquivo - Tarefa 1 - Parte 01 (Example path_origin: "C:/FTP-TESTE-UPLOAD/Arquivo-01.txt", path_to_ftp: "/pasta_01/PrimeiroArquivo.txt")      
        try {
            FileInputStream path_origin_to_ftp = new FileInputStream(path_origin);     
            ftp.storeFile(path_to_ftp, path_origin_to_ftp);
        } catch (Exception e) {
            System.out.println("Erro ao fazer o upload do arquivo");
        } finally {
            System.out.println("Upload do arquivo foi feito corretamente");
        }
    }

    public void downloadFile(FTPClient ftp, String path_to_retrive, String path_to_download){
        // Fazer download de um arquivo - Tarefa 1 - Parte 02 (Example path_to_download: "C:/FTP-TESTE-DOWNLOAD/Arquivo-Download.txt", path_to_retrive: "/pasta_01/PrimeiroArquivo.txt")      
        try {
            FileOutputStream path_of_download = new FileOutputStream(path_to_download);
            ftp.retrieveFile(path_to_retrive, path_of_download);
        } catch (Exception e) {
            System.out.println("Erro ao fazer o downlaod do arquivo");
        } finally {
            System.out.println("Download do arquivo foi feito corretamente");
        }
    }

    public void createDir(FTPClient ftp, String name_of_directory){
        // Criar diretório - Tarefa 2 - Parte 01
        try {
            ftp.makeDirectory(name_of_directory);
        } catch (Exception e) {
            System.out.println("Erro ao criar diretório");
        } finally {
            System.out.println("Diretório criado");
        }
    }

    public void deleteDir(FTPClient ftp, String name_of_directory){
        // Deletar diretório - Tarefa 2 - Parte 02       
        try {
            ftp.removeDirectory(name_of_directory);
        } catch (Exception e) {
            System.out.println("Erro ao deletar diretório");
        } finally {
            System.out.println("Diretório deletado");
        }
    }

    public void renameDir(FTPClient ftp, String old_name_of_directory, String new_name_of_directory){
        // Renomear um diretório - Tarefa 2 - Parte 03    
        try {
            ftp.rename(old_name_of_directory, new_name_of_directory);
        } catch (Exception e) {
            System.out.println("Erro ao renomear diretório");
        } finally {
            System.out.println("Diretório renomeado");
        }
    }
    
    public void createFile(FTPClient ftp){
        // Criar arquivo - Tarefa 3 - Parte 01 (Dá pra usar o storeFileStream)
        Random random = new Random();
        int random_number = Math.abs(random.nextInt());

        try{
            FileWriter file = new FileWriter("C:/FTP-TESTE/NovoArquivo.txt");
            PrintWriter recordFile = new PrintWriter(file);
            // Para simulação e praticidade, cada arquivo .txt que é criado, só tem o conteúdo "Hola!" escrito.
            recordFile.print("Hola!");
            file.close();
            FileInputStream path_to_record = new FileInputStream("C:/FTP-TESTE/NovoArquivo.txt");
            ftp.storeFile("/pasta_01/NovoArquivo"+random_number+".txt", path_to_record);
        } catch(Exception e) {
            System.out.println("Não foi possível criar ");
        }  finally {
            System.out.println("Arquivo criado");
        }
    }

    public void renameFile(FTPClient ftp, String archive, String new_name){
        // Renomear arquivo - Tarefa 3 - Parte 02
        try{
            ftp.rename(archive, new_name);
        } catch(Exception e) {
            System.out.println("Não foi possível renomear arquivo");
        }  finally {
            System.out.println("Arquivo renomeado");
        }
    }    
     
    public void deletFile(FTPClient ftp, String archive){
        // Remover arquivo - Tarefa 3 - Parte 03
        try{
            ftp.deleteFile(archive);
        } catch(Exception e) {
            System.out.println("Não foi possível deletar o arquivo");
        }  finally {
            System.out.println("Arquivo deletado");
        }
    }  

    public void listDirectory(FTPClient ftp){
        // Listar os diretórios
        try{
            FTPFileFilter filter = new FTPFileFilter() {
                @Override
                public boolean accept(FTPFile ftpFile) {
                    return ftpFile.isDirectory();
                }
            };
            System.out.println("Lista de diretórios");
            FTPFile [] directory =  ftp.listFiles(null, filter);
            for(FTPFile d: directory){
                System.out.println(d.getName());
            }
        } catch(Exception e){
            System.out.println("Não foi possível listar os diretórios");
        }
    }
    
    public void listFiles(FTPClient ftp){
        // Listar os arquivos
        try{           
            FTPFileFilter filter = new FTPFileFilter() {
                @Override
                public boolean accept(FTPFile ftpFile) {
                    return ftpFile.isFile();
                }
            };
            System.out.println("Lista de arquivos");
            FTPFile [] file =  ftp.listFiles(null, filter);
            for(FTPFile f: file){
                System.out.println(f.getName());
            }
        } catch(Exception e){
            System.out.println("Não foi possível listar os arquivos");
        }
    }
        
}
