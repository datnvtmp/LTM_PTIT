import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class LTM {
    public static void main(String[] args) throws Exception {
        Socket socket = null;   
        socket = new Socket("203.162.10.109", 2208);
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        String message = "B22DCCN199;uCQTxXBT";

        writer.write(message);
        writer.newLine();  
        writer.flush();

        String receive = reader.readLine();
        String temp[] = receive.split(",");
        String res = "";
        for (String s : temp) {
            if(s.trim().contains(".edu")) {
                res += s.trim() + ", ";
                //System.out.println(s.trim());
            }
        }
        res = res.substring(0, res.length() - 2);
        //System.out.println(res);
        writer.write(res);    
        writer.newLine();  
        writer.flush();
        writer.close();
        reader.close();
        socket.close();
    }
}
