// import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
// import java.util.Scanner;

public class output implements Runnable {
    private Socket s = null;
    private Boolean condition = true;
    String filename=null;
    FileWriter fw=null;
    private String text=null;

    public output(Socket s,String a) throws IOException {
        this.s = s;
        filename="./chat/"+a;
        fw=new FileWriter(filename);
    }

    public void exit() throws IOException
    {
        condition=false;
        s.close();
    }


    public void Text(String a)
    {
        text=a;
    }

    
    @Override
    public void run() {
        try
        {
            // Scanner sc=new Scanner(System.in);
            PrintWriter pr=new PrintWriter(s.getOutputStream());
            while(condition)
            {
                // String sstt=sc.nextLine();
                String sstt=text;
                text=null;
                if(sstt!=null)
                {
                    fw.write(sstt);
                    pr.println(sstt);
                    pr.flush();
                    data.refresh=true;
                }

                else
                {
                    exit();
                }
            }
        }

        catch(Exception e)
        {
            System.out.println(e);
        }
    }

}
