package peer;


import java.util.ArrayDeque;
import java.util.Queue;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class QueueingModule extends Thread{


    private static Queue<String> q = new ArrayDeque<>();

    public QueueingModule(){}

    private void addToQueue(String Data)
    {
        q.add(Data);
    }


    @Override
    public void run() {
        try {
            SaveToFile();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }



    public void getFromDataGen(String Data) throws IOException {
        addToQueue(Data);
    }

    public void getFromPeers(String Data) throws IOException {
        addToQueue(Data);
    }


    public void SaveToFile() throws IOException {

        int size = q.size();
        PrintWriter pw = new PrintWriter(new FileOutputStream (new File("DB.txt"),true ));
        for(int i = 0 ; i<size ; i++) {

            pw.append(q.poll()+"|");
        }
        pw.close();
    }


    public void print () {
        int size = q.size();
        for(int i =0 ; i< size; i++) {
            System.out.println(q.peek());
        }
    }
	    /*public static void main(String[] args) throws IOException
	    {
	        QueueingModule qq = new QueueingModule();
	        qq.addToQueue("A");
	        qq.addToQueue("B");
	        qq.addToQueue("C");
	        qq.addToQueue("D");
	        qq.addToQueue("E");


	        qq.SaveToFile();
	    }*/
}
