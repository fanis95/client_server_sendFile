// theofanis biniakou icsd13126

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


public class main_server {

    
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket sock = null;
        BufferedReader br = null;
        InputStream is = null;
        
        ByteArrayOutputStream baos = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        
        try{
            //anoigw mia porta ston server gia na dexetai sindeseis
            ServerSocket ss = new ServerSocket(8080);
            System.out.println("Waiting...");
            sock = ss.accept();
            System.out.println("Client Connected!");
            
            // arxikopoiw enan buffer gia na diabasei thn ip kai to onoma tou arxeiou
            // pou dinei o client
            br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            
            // edw diabazei ta 2 string pou grafei o client
            String ip = br.readLine();
            String file_name = br.readLine();
            System.out.println("User with ip " + ip + " is sending a file with name " + "\"" + file_name + "\" ");
            
            // apo edw arxizei h diadikasia lhpshs tou arxeiou, opote emfanizw to katallhlo mhnuma
            System.out.println("Receiving file...");
            
            // arxikopoiw enan pinaka byte me enan arketa megalo int
            // wste na ginei h lhpsh tou antistoixou pinaka byte apo ton client
            byte[] file_bytes = new byte[8000];
            int each_byte;
            
            // mesw autou tou nhmatos tha ginei h lhpsh tou pinaka byte
            is = sock.getInputStream();
            
            // 
            baos = new ByteArrayOutputStream();
            
            bos = new BufferedOutputStream(new FileOutputStream(file_name));
            // dhmiourgei to arxeio tou client
            // den vazw path. otan pernei san orisma mono to onoma tou arxeiou
                // to apothikevei ston fakelo pou einai to project
                   
            // diabazei to 1o byte apo ton pinaka pou exei steilei o client
            each_byte = is.read(file_bytes);
            
            // gia ola ta bytes pou uparxoun sto ByteArrayOutputStream
            while (each_byte != -1){
                baos.write(file_bytes);
                // diabazei to epomeno byte apo ton ByteArrayOutputStream pou uparxoun ola ta bytes
                each_byte = is.read(file_bytes); 
            }
            
            // metatrepw ola ta bytes se array mesw tou buffer sto arxeio
            bos.write(baos.toByteArray());
            bos.flush();
            
            // se auto to shmeio tha exei ginei h lhpsh tou arxeiou
            System.out.println("File Received");
            
        } catch(IOException e){
            System.out.println("1");
            Logger.getLogger(main_server.class.getName()).log(Level.SEVERE, null, e);
        }finally {
            try {
                if (sock != null)
                    sock.close();
                if (br != null)
                    br.close();
                if (fos != null)
                    fos.close();
                if (bos != null)
                    bos.close();
                if (is != null)
                    is.close();
                if (baos != null)
                    baos.close();
                
            } catch (IOException e) {
                System.out.println("2");
                Logger.getLogger(main_server.class.getName()).log(Level.SEVERE, null, e);
            } 
        }
        
    }
    
}
