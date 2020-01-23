

public class Potion {


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    private int price;
    private String name;
    Potion(String name){
        this.name = name;
    }

    public String toString() {
        return name;
    }

}




