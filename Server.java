import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Server {
    ServerSocket ss=null;
    Socket s=null;
    Vector<soc> soo=new Vector<>();

    //filename=index of vector+1; 

    public Server() throws IOException, InterruptedException
    {
        ss=new ServerSocket(1234);
        s=ss.accept();
        soc so=new soc();
        soo.add(so);
        data.connection.add(soo.size()+" Server");  //this will add list of server as well as client
        String filename=""+data.connection.size();
        so.start(s, filename);
        File file=new File("./chat/"+filename);
        if(!file.createNewFile())
        {
            file.delete();
            file.createNewFile();
        }
        

        
    }

    public void start(int a)
    {
        soo.get(a).resume();
    }

    public void exit() throws IOException
    {
        ss.close();
        s.close();

       
    }
}


class soc
{
    Thread t1=null;
    Thread t2=null;
    public void start(Socket s,String filename) throws IOException, InterruptedException
    {
        

        output o=new output(s,filename);
        input i=new input(s, filename);
        t1=new Thread(o);
        t2=new Thread(i);
        t1.start();
        t2.start();
        pause();
    }

    public void pause() throws InterruptedException
    {
        t1.wait();
    }

    public void resume()
    {
        t1.notify();
    }
}