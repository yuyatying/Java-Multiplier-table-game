import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class readfile {
    Scanner read;
    static int total,correct,wrong;
    private List<Integer> stats= new ArrayList<Integer>();
    public void openFile() {
        try {
            total=0;
            read = new Scanner(new File("save.txt"));
        } catch (Exception g) {
            System.out.println("No records yet");
        }
    }
    public List<Integer> readFile(){
        while (read.hasNextInt()){
            stats.add(read.nextInt());
            if (stats.get(total)==1){
                correct++;
            }
            else if(stats.get(total)==0){
                wrong++;
            }
            total++;
        }

        return stats;
    }
    public void closeFile(){
        read.close();
    }

}
