public enum Items {
    Heal(0, "Healing Potion: Heals 5 HP",3),Sword(1, "Sword: Strength increases by 2",4),
    Peek(2, "Peek Potion: Being able to check the multiplication table",5);
    private final int ID;
    private final String name;
    private final int cost;
        Items(int ID, String name,int cost){
            this.ID=ID;
            this.name=name;
            this.cost=cost;
    }
    public String getname(){
        return this.name;
    }
    public static Items getItem(int id){
        switch (id){
            case 1: return Items.Heal;
            case 2: return Items.Sword;
            case 3: return Items.Peek;
        }
        return Items.Heal;
    }

    public int getCost(){
        return this.cost;
    }
}
