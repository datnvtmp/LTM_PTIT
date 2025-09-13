
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

public class LTM {

    public static void main(String[] args) throws Exception {
        
        Socket socket = new Socket("203.162.10.109", 2206);
        String code = "B22DCCN199;uDYnVUuS";
        
        InputStream in =  socket.getInputStream();
        OutputStream out  =  socket.getOutputStream();
        
        out.write(code.getBytes());
        out.flush();
        
        byte[] data = new byte[1024];
        int len = in.read(data);
        String response = new String(data, 0, len);
        System.out.println(response);
        
        String[] temp = response.split(",");
        int num[] = new int[temp.length];
        
        int sum  = 0;
        for (int i = 0; i < num.length; i++) {
            num[i] = Integer.parseInt(temp[i].trim());
            sum += num[i];
        }
        sum = (int)sum*2/num.length;
        Arrays.sort(num);
        
        int l = 0, r = num.length - 1, a = 0, b = 0;
        int minDiff = Integer.MAX_VALUE;
        while(l < r){
            int currentSum = num[l] + num[r];
            int diff = Math.abs(currentSum - sum);
            if (diff < minDiff) {
                    minDiff = diff;
                    a = num[l];
                    b = num[r];
                }
            if(num[l] + num[r] < sum) {
                l++;
            }
            else if(num[l] + num[r] > sum ) {
                r--;
            } else {
                 a = num[l];
                b = num[r];
                l++;
                r--;
            }
        }
        String res = String.valueOf(a) + ',' + String.valueOf(b);
        out.write(res.getBytes());
        out.flush();
        
        System.out.println(a + " " + b);
        socket.close();
    }
}
                                    