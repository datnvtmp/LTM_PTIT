
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class LTM {

    public static void main(String[] args) throws Exception {
        
        Socket socket = new Socket("203.162.10.109", 2207);
        String code = "B22DCCN199;v2r6eDjn";
        
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out  = new DataOutputStream(socket.getOutputStream());
        
        out.writeUTF(code);
        out.flush();
        
        String s = in.readUTF();
        System.out.println(s);
        String data[] = s.split(",");
        int number[] = new int[data.length];
        
        for (int i = 0; i < data.length; i++) {
            number[i] = Integer.parseInt(data[i].trim());
        }
        
        int doiChieu = 0;
        int tongBienThien = 0;

        int prevDiff = 0; 
        for(int i = 1; i < number.length; i++) {
            int diff = number[i] - number[i-1];
            tongBienThien += Math.abs(diff);
            
            if(diff > 0 && prevDiff < 0) doiChieu++;
            else if(diff < 0 && prevDiff > 0) doiChieu++;
            
            if(diff != 0) prevDiff = diff;
            
        }
        out.writeInt(doiChieu);
        out.writeInt(tongBienThien);
        out.flush();
        System.out.println(String.valueOf(doiChieu) + "--" + String.valueOf(tongBienThien));
        
        socket.close();
    }
}
                                    