// theofanis biniakou icsd13126


import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


public class main_client {
    public static void main(String[] args) {
            
        Socket sock = null;
        
        try{
            // dimiourgeite h sundesh me ton server
            sock = new Socket("localhost", 8080);
            System.out.println("Connecting to "+ sock.getInetAddress()+ " and port "+sock.getPort());
            
            // kalw ton kataskevasth NewJFrame1 sto opoio uparxei to arxiko grafiko perivallon
            // alla kai h ulopoihsh ths apostolhs tou arxeiou
            // h genikh idea pou eixa, htan na ftiaksw mia klash, opou tha dhmiourgousa ena antikeimeno ekei (to arxeio)
                    // kai tha stelnotan apo ekei
                    // gia na mhn ginotan h diadikasia apostolhs sthn ulopoihsh twn basikwn grafikwn
            NewJFrame1 nj = new NewJFrame1();
            // metaferw to socket ths sundeshs me ton server sto NewJFrame1
            nj.set_sock(sock);
            nj.setVisible(true);
            
        } catch(IOException e) {
            System.out.println("3");
            Logger.getLogger(main_client.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if(sock != null)
                    sock.close();
            } catch (IOException e){
                System.out.println("5");
                Logger.getLogger(main_client.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
    
}
