public enum Monster {
    Skull(0,"Skull Soldier",4,10,2),Dragon(1,"Fire Dragon",8,20,5
    ),Demon(2,"Demon Lord",10,40,10);
    private final int id;
    private final String name;
    private int str,health,gold;
    Monster(int id, String name,int str, int health, int gold){
        this.id=id;
        this.name=name;
        this.str=str;
        this.health=health;
        this.gold=gold;
    }
    public void Regen(Monster monster){
        if (monster==Monster.Skull){
            monster.health=10;
        }
        if (monster==Monster.Dragon){
            monster.health=20;
        }
        if (monster==Monster.Demon){
            monster.health=40;
        }
    }
    public int getHealth() {
        return this.health;
    }
    public void setHealth(int health){
        this.health-=health;
    }
    public void printstats(){
        System.out.println(this.name);
        System.out.print("Health: " + this.health);
    }
    public String getName(){
        return this.name;
    }
    public int getStr(){
        return this.str;
    }
    public int getGold(){
        return this.gold;
    }
}
