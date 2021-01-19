import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
public class Server {
public static void main(String[] args) throws IOException,FileNotFoundException{
ServerSocket serverSocket = new ServerSocket(4999);
System.out.println("Server ready for the connection");
Socket sock = serverSocket.accept();
System.out.println("Connection was successful ");
//to Read the file name from the client
InputStream istream = sock.getInputStream() ;
BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(istream));
String fname = bufferedReader.readLine();
//Reading the contents
BufferedReader contentReader = new BufferedReader(new FileReader(fname));
//keeping output stream ready for sending the content
OutputStream ostream = sock.getOutputStream();
PrintWriter pwrite = new PrintWriter(ostream,true);
String str ;
while((str = contentReader.readLine())!=null)
{
pwrite.println(str);
}
sock.close();
serverSocket.close();
pwrite.close();
bufferedReader.close();
contentReader.close();
}
}