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

            Socket socket = new Socket("203.162.10.109", 2206);
            socket.setSoTimeout(5000);
            String message = "B22DCCN199;MI8ZBvYH"; 

            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();    

            out.write(message.getBytes());

            byte[] data = new byte[1024];
            int len = in.read(data);
            String response = new String(data, 0 , len);

            String arr[] = response.split(",");
            int res = 0;
            for(String x : arr) {
                int temp = Integer.parseInt(x);
                res += isPrime(temp) ? temp : 0;
            }

            out.write(String.valueOf(res).getBytes());
            System.out.println(res);
            out.flush();

            socket.close();
        }

        public static boolean isPrime(int n) {
            if (n <= 1) return false;
            for (int i = 2; i <= Math.sqrt(n); i++) {
                if (n % i == 0) return false;
            }
            return true;
        }
    }