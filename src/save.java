import java.util.*;
import java.io.*;
import java.lang.*;
public class save {
    private BufferedWriter out;
    public void openFile() {
        try {
            FileWriter fstream = new FileWriter("save.txt",true);
            out = new BufferedWriter(fstream);

        }
        catch (Exception e) {
            System.out.print("Error");
        }
    }
    public void writeFile(int data){
        try {
            out.write(data+" ");
        }
        catch (Exception e){
            System.out.println("Error");
        }
    }
    public void closeFile(){
        try {
            out.close();
        }
        catch (Exception e){
            System.out.println("Error");
        }

    }

}

