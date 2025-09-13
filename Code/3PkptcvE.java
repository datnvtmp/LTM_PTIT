import java.io.*;
import java.net.Socket;
import java.util.*;

public class LTM {

    public static void main(String[] args) throws Exception {

        Socket socket = new Socket("203.162.10.109", 2208);
        socket.setSoTimeout(5000);
        String message = "B22DCCN199;3PkptcvE";

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        out.write(message);
        out.newLine();
        out.flush();

        String s = in.readLine();
        String[] res = s.split("\\s+");
        List<String> arr = Arrays.asList(res);

        arr.sort(Comparator.comparingInt(String::length));

        String result = String.join(", ", arr);
        out.write(result);
        out.newLine();
        out.flush();
        
        socket.close();
    }
}