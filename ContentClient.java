import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
public class ContentClient {
public static void main(String[] args) throws IOException{ 
Socket sock = new Socket("127.0.0.1", 4999);

System.out.println("Enter the filename ");
BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
String fname = reader.readLine();
OutputStream ostream = sock.getOutputStream();
PrintWriter pwriter = new PrintWriter(ostream,true);
pwriter.println(fname);
InputStream istream = sock.getInputStream();
BufferedReader toread = new BufferedReader(new InputStreamReader(istream));
String str ;
while((str=toread.readLine())!=null){
System.out.println(str);
}
pwriter.close();
sock.close();
toread.close();
}
}