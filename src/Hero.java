import java.lang.*;
import java.util.ArrayList;
import java.util.List;

public class Hero {
    private int health,str,gold;
    private int Hcount,Scount,Pcount;
    private List<Items> items= new ArrayList<Items>();
    private String name;
    public Hero(String name){
        this.name=name;
        this.health=20;
        this.str=3;
        this.gold=5;
        this.items.add(Items.Heal);
        this.items.add(Items.Sword);
        this.items.add(Items.Peek);
    }
    public int getHcount(){
        return Hcount;
    }
    public void setHcount(int val){
        Hcount-=val;
    }
    public int getScount(){
        return Scount;
    }
    public void setScount(int val){
        Scount-=val;
    }
    public int getPcount(){
        return Pcount;
    }
    public void setPcount(int val){
        Pcount-=val;
    }
    public void RemovefromList(Items item){
        for (Items i: items){
            try {
                if (i == item) {
                    this.items.remove(item);
                    break;
                }
            }
            catch (Exception e){
                break;
            }
        }
    }
    public void returnCount(){
        Hcount=0;Scount=0;Pcount=0;
        for (Items i:items){
            if (i==Items.Heal){
                Hcount++;
            }
            else if(i==Items.Sword){
                Scount++;
            }
            else if(i==Items.Peek){
                Pcount++;
            }
        }
    }
    public List<Items> getItemsList(){
        return this.items;
    }
    public int getstr(){
        return this.str;
    }
    public int getHealth(){
        return this.health;
    }
    public int getGold(){
        return this.gold;
    }
    public String getName(){
        return this.name;
    }
    public int getItems(){
        return this.items.size();
    }
    public void checkitems(){
        int count=1;
        System.out.println("\nItem List:");
        for (Items i : items){
            System.out.print(count+") ");
            System.out.println(i.getname());
            count+=1;
        }
        String use= child.getinput();
        try {
            this.useItem(Integer.parseInt(use));
        }
        catch (Exception e){
            System.out.println("Cancelled...");
        }
        System.out.println();
    }
    public void checkstats(){
        System.out.println("\n"+this.name+"'s Status: ");
        System.out.print("Health: "+ this.health);
        System.out.println("  Strength: "+this.str);
        System.out.print("Gold: "+gold);
        System.out.println("  Items: "+items.size());
    }
    public void shop(){
        while (true) {
            System.out.println("\nItem List: ");
            System.out.println("Gold: "+this.gold);
            System.out.println("1: Healing Potion($3)");
            System.out.println("2: Sword ($5)");
            System.out.println("3: Peek Potion($4)");
            System.out.println("exit: exit shop");
            String shopping = child.getinput();
            switch (shopping) {
                case "1":
                    this.buy(1);
                    break;
                case "2":
                    this.buy(2);
                    break;
                case "3":
                    this.buy(3);
                    break;
                default:
                    System.out.println("Shop exited...");
                    return;
            }
        }
    }
    public void buy(int id){
        if (this.gold<Items.getItem(id).getCost()){
            System.out.println("\nYou do not have enough money!");
            System.out.println("Gold: "+this.gold);
        }
        else {this.items.add(Items.getItem(id));
        this.gold-=Items.getItem(id).getCost();
        System.out.println("\nSuccessfully bought "+Items.getItem(id).getname());
        System.out.println("Gold Left: "+this.gold);
        }
    }
    public void useItem(int id){
        id-=1;
        if (this.items.get(id)==(Items.Heal)){
            System.out.println("Healed 5 hp!");
            this.health+=5;
        }
        else if (this.items.get(id)==Items.Sword){
            System.out.println("Your strength has increased by 2!");
            this.str+=2;
        }
        else if (this.items.get(id)==Items.Peek){
            child.ChkTable();
        }
        this.items.remove(id);
    }
    public void fight(Monster monster){
        boolean success;
        success=child.game();
        if (success){
            System.out.println();
            System.out.println(this.name+" has dealt "+this.str+" damage to "+monster.getName()+"!");
            monster.setHealth(this.str);
            System.out.println();
            monster.printstats();
            System.out.println();
            System.out.println();
        }
    }

    public void block(Monster monster){
        System.out.println(monster.getName()+" has attacked!");
        System.out.println("Try to block it using your knowledge!");
        System.out.println();
        boolean success;
        success=child.game();
        System.out.println();
        if (success){
            System.out.println("You have successfully blocked the attack from "+ monster.getName()+"!");
        }
        else {
            System.out.println("You failed to block the attack...");
            this.health-=monster.getStr();
            System.out.println("Received "+monster.getStr()+" damage...");
        }
    }
    public void setGold(int gold){
        this.gold+=gold;
    }
    public void Regen(){
        this.health=20;
    }
    public void setHealth(int damage){
        this.health-=damage;
    }
    public void heal(int heal){
        this.health+=heal;
    }
    public void increaseStr(){
        this.str+=2;
    }
    public void battle(Monster monster){
        child.currectMonster=monster;
        while (true){

            System.out.println(this.name+"'s health: "+this.health);
            System.out.println();
            System.out.println("1: Attack");
            System.out.println("2: Use Items");
            System.out.println("3: Escape");
            String act= child.getinput();
            if (act.equals("1")){
                System.out.println("Attack: ");
                this.fight(monster);
                if (monster.getHealth()<=0){
                    System.out.println(monster.getName()+" has been slain!");
                    System.out.println(this.name+" has received "+monster.getGold()+" gold!");
                    this.gold+=monster.getGold();
                    break;
                }
                this.block(monster);
                if (this.health<=0){
                    System.out.println(this.name+" has fallen...");
                    break;
                }
            }
            else if (act.equals("2")){
                this.checkitems();
            }
            else if (act.equals("3")){
                System.out.println("You have successfully escaped...");
                break;
            }
        }
    }
    public void Actions(int id){

        switch (id){
            case 1: {
                System.out.println("\n1: Skull Soldier");
                System.out.println("2: Fire Dragon");
                System.out.println("3: Demon Lord");
                String mob=child.getinput();
                if (mob.equals("1")){
                    System.out.println("\nYou have encountered "+Monster.Skull.getName()+"!");
                    Monster.Skull.printstats();
                    System.out.println();
                    battle(Monster.Skull);
                }
                else if(mob.equals("2")){
                    System.out.println("You have encountered "+Monster.Dragon.getName()+"!");
                    Monster.Dragon.printstats();
                    System.out.println();
                    battle(Monster.Dragon);
                }
                else if(mob.equals("3")){
                    System.out.println("You have encountered "+Monster.Demon.getName()+"!");
                    Monster.Demon.printstats();
                    System.out.println();
                    battle(Monster.Demon);
                }
                else {
                    break;
                }
                break;}
            case 2: this.shop(); break;
            case 3: this.checkstats();break;
            case 4: child.start();break;
        }
    }
}
