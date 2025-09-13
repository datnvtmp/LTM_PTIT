
import TCP.Product;
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

public class LTM {

    public static void main(String[] args) throws Exception {
        
        Socket socket = new Socket("203.162.10.109", 2209);
        String code = "B22DCCN199;nQozmBqt";
            
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        
        out.writeObject(code);
        out.flush();
        
        Product pr = (Product)in.readObject();
        System.out.println(pr);
        int x = (int)pr.price;
        int sum = 0;
        while(x > 10) {
            sum += x%10;
            x /= 10;
        }
        sum += x;
        
        pr.discount = sum;
        
        out.writeObject(pr);
        out.flush();
        System.out.println(pr);
        
        socket.close();
    }
}
                 


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP;

import java.io.Serializable;

/**
 *
 * @author nguye
 */
public class Product implements Serializable{
    private static final long serialVersionUID = 20231107;
    public int id , discount;
    public  String name ;
    public double price;

    public Product(int id, int discount, String name, double price) {
        this.id = id;
        this.discount = discount;
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", discount=" + discount + ", name=" + name + ", price=" + price + '}';
    }
    
}

