
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

public class LTM {

    public static void main(String[] args) throws Exception {
        
        Socket socket = new Socket("203.162.10.109", 2208);
        String code = "B22DCCN199;N51OnPWM";
            
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        
        out.write(code); 
        out.newLine();
        out.flush();
        
        
        String s = in.readLine();
        String data[] = s.split("\\s+");
        ArrayList<String> list = new ArrayList<>();
        
        
        for (String x : data) {
            String rev = new StringBuilder(x).reverse().toString();
            list.add(rev);
        }
        System.out.println(list);
        for(int i = 0; i < list.size(); i++) {
            String temp = list.get(i);
                            int dem = 1;

            StringBuilder str = new StringBuilder("");
            for(int j = 1; j < temp.length(); j++){
                if(temp.charAt(j) == temp.charAt(j-1)){
                    dem++;
                } else {
                    str.append(temp.charAt(j-1));
                    if(dem != 1) {
                        str.append(String.valueOf(dem)) ;
                        dem = 1;
                    } else if(j == temp.length() - 1) str.append(temp.charAt(j));
                }
            }
            list.set(i, str.toString());
        }
        out.write(String.join(" ", list));
        System.out.println(list);
        out.newLine();
        out.flush();
        socket.close();
    }
}
                                    