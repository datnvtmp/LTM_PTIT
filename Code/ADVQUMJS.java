
import TCP.Address;
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
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LTM {

    public static void main(String[] args) throws Exception {
        
        Socket socket = new Socket("203.162.10.109", 2209);
        String code = "B22DCCN199;aDVquMJs";
            
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        
        out.writeObject(code);
        out.flush();
        
        Address ad = (Address)in.readObject();
        System.out.println(ad);
        
        String s = ad.addressLine;
        String data[] = s.split("\\s+");
        System.out.println(s);
        List<String> list = new ArrayList<>();
        for(int i = 0; i < data.length; i++) {
            
            String res = "";
            for (int j = 0; j < data[i].length(); j++) {
                char c = data[i].charAt(j);
                if(Character.isLetter(c) ||  Character.isDigit(c)) {
                    res += c;
                }
            }
            if(res.length() > 0) {
                res = Character.toUpperCase(res.charAt(0)) + res.substring(1).toLowerCase();
                list.add(res);
                System.out.println(res);
                System.out.println("-------");
            }
        }
        ad.addressLine = String.join(" ", list);
        System.out.println(ad.addressLine);
        
        
        String pos = ad.postalCode;
        String tmp = "";
        for(int i = 0; i < pos.length(); i++) {
            if(Character.isDigit(pos.charAt(i)) || pos.charAt(i) == '-') {
                tmp += pos.charAt(i);
                
            }
        }
        ad.postalCode = tmp;
        
        out.writeObject(ad);
        out.flush();
        socket.close();
    }
}



                 


