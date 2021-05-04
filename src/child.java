import java.util.Scanner;
public class child {
    public static Hero hero;
    public static Monster currectMonster;
        public static String[] table = new String[100];
        public static int[] ans= new int[100];
        public static void InitTable(){
            final int MIN = 1;
            final int MAXINDEX=10;
            final int MAXTABLE=10;
            final int STEP=1;
            int i, j;
            int count=0;
            for (j = MIN; j < MAXTABLE; j += STEP) {
                for (i = MIN; i <= MAXINDEX; i += STEP) {
                    table[count]=String.format("%3d * % 3d = ?\n",i,j);
                    ans[count]=i*j;
                    count+=1;
                }
            }
            for (i = MIN; i <= MAXINDEX; i += STEP){
                table[count]=String.format("%3d * % 3d = ?\n",i,MAXTABLE);
                ans[count]= i * MAXTABLE;
                count+=1;
            }
        }
        public static void ChkTable() {
            final int MIN = 1;
            final int MAXINDEX=10;
            final int MAXTABLE=10;
            final int STEP=1;
            int i, j;
            for (j = MIN; j < MAXTABLE; j += STEP) {
                for (i = MIN; i <= MAXINDEX; i += STEP) {
                    System.out.printf("%3d * %3d = %3d\n", i, j, i * j);
                }
                System.out.print("\n---------------\n\n");
            }
            for (i = MIN; i <= MAXINDEX; i += STEP){
                System.out.printf("%3d * %3d = %3d\n", i, MAXTABLE, i * MAXTABLE);
            }
        }
        public static void start(){
            System.out.println("1. Start New Game");
            System.out.println("2. Check multiplication table");
            System.out.println("3. Exit Game");
            while(true){
                String input=child.getinput();
                switch (input){
                    case "1": {
                        while (true) {
                            System.out.println("Please enter Hero's Name");
                            String name=child.getinput();
                            hero = new Hero(name);
                            System.out.println("Your name is "+ name +", is this alright? (Y/N)");
                            String ask=child.getinput();
                            if (ask.equals("Y")) {
                                break;
                            }
                        }
                        while (true) {
                            System.out.println("\nActions: ");
                            System.out.println("1: Challenge mobs");
                            System.out.println("2: Shop");
                            System.out.println("3: Check own's status");
                            System.out.println("4: Exit to main menu");
                            String act = child.getinput();
                            int actInt;
                            try {
                                actInt=Integer.parseInt(act);
                                hero.Actions(actInt);
                            }
                            catch (Exception e){
                                System.out.println("Please input appropriate numbers!");
                            }
                        }
                    }
                    case "2":
                    {child.ChkTable();
                        child.start();
                        break;
                    }
                    case "3": System.exit(0);
                    default: break;
                }
            }
        }
        public static String getinput(){
            System.out.print("Enter: ");
            Scanner input=new Scanner(System.in);
            return input.next();
    }
        public static int GenQ(){
            int Q= (int)(Math.random()*100);
            System.out.print(table[Q]);
            return Q;
        }
        public static boolean CheckAns(int Q,String input){
            boolean chk=false;
                if (Integer.parseInt(input)==ans[Q])
                    chk=true;
            return chk;
        }
        public static boolean game(){
            int Q;
            Q=child.GenQ();
            String answer=child.getinput();
            boolean success = child.CheckAns(Q, answer);
            if (success) {
                System.out.println("Success!");
            }
            else System.out.println("Failed...");
            return success;
        }

        public static void main(String args[]){
            child.InitTable();
            child.start();
        }
}
