import java.io.DataInputStream;
import java.io.DataOutputStream; 
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

    
    public class LTM {
        public static void main(String[] args) throws Exception {

            Socket socket = new Socket("203.162.10.109", 2207);
            socket.setSoTimeout(5000);
            String message = "B22DCCN199;dIseLLWc"; 

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());    


            out.writeUTF(message);
            out.flush();

            int k = in.readInt();
            String response = in.readUTF();
            String[] data = response.split(",");

            List<String> result = new ArrayList<>();
            for(int i = 0; i < data.length; i += k) {
                List<String> group = new ArrayList<>();
                
                for(int j = i; j < Math.min(i+k, data.length); j++) {
                    group.add(data[j]);
                }
                Collections.reverse(group);

                result.addAll(group);

            }
            String res = String.join(",", result);

            out.writeUTF(res);
            out.flush();

            socket.close();
        }

      
    }