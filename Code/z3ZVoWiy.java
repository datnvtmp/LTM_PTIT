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

            String message = "B22DCCN199;z3ZVoWiy"; 
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            outputStream.write(message.getBytes());
            outputStream.flush();

            byte[] buffer = new byte[1024];
            int bytesRead = inputStream.read(buffer);
            String response = new String(buffer, 0, bytesRead);
            response = response.replace("|", " ");
            String temp[] = response.split("\\s+");
            int res = 0;

            for(String s : temp) {
                s = s.trim();
                res += Integer.parseInt(s);
            }

            String result = String.valueOf(res);
            outputStream.write(result.getBytes());
            outputStream.flush();
            socket.close();
        }
    }