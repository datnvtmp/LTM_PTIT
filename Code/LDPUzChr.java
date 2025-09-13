    import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
    import java.io.InputStreamReader;
    import java.io.OutputStream;
    import java.io.OutputStreamWriter;
    import java.net.Socket;
    import java.util.ArrayList;
    import java.util.List;

    public class LTM {
        public static void main(String[] args) throws Exception {

            Socket socket = new Socket("203.162.10.109", 2207);
            //Socket socket = new Socket("localhost", 2207);

            String message = "B22DCCN199;LDPUzChr"; 
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            out.writeUTF(message);
            System.out.println(message);
            out.flush();

            int a = in.readInt();
            int b = in.readInt();
            System.out.println(a + " " + b);
            int sum =  a + b;
            int mul = a * b;    
            out.writeInt(sum);
            out.writeInt(mul);
            out.flush();

            socket.close();
        }
    }