import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;


public class Board implements Serializable {

    private int money;
    private int potion_hp;
    private int potion_mp;
    private boolean bln_tigs_entry;


    public boolean isBln_tigs_entry() {
        return bln_tigs_entry;
    }

    public void setBln_tigs_entry(boolean bln_tigs_entry) {
        this.bln_tigs_entry = bln_tigs_entry;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getPotion_hp() {
        return potion_hp;
    }

    public void setPotion_hp(int potion_hp) {
        this.potion_hp = potion_hp;
    }

    public int getPotion_mp() {
        return potion_mp;
    }

    public void setPotion_mp(int potion_mp) {
        this.potion_mp = potion_mp;
    }


    Board(int money, int potion_hp, int potion_mp) {
        this.money = money;
        this.potion_hp = potion_hp;
        this.potion_mp = potion_mp;
        this.setBln_tigs_entry(false);
    }

    static Scanner sc = new Scanner(System.in);
    final static Board board = new Board(111200, 0, 2);
    final static Character kyungseok = new Friends("Kyungseok", 10, 30, 100, 30, 15, 20, "Throwing his 300 kgs of Korean and US military uniforms from his closet", 0, 1, 800);
    final static Character kyungseok1 = new Friends("Kyungseok1", 10, 30, 100, 30, 15, 20, "Throwing his 300 kgs of Korean and US military uniforms from his closet", 0, 1, 800);
    final static Character kyungseok2 = new Friends("Kyungseok2", 10, 30, 100, 30, 15, 20, "Throwing his 300 kgs of Korean and US military uniforms from his closet", 0, 1, 800);
    final static Character kyungseok3 = new Friends("Kyungseok3", 10, 30, 100, 30, 15, 20, "Throwing his 300 kgs of Korean and US military uniforms from his closet", 0, 1, 800);
    final static Character kyungseok4 = new Friends("Kyungseok4", 10, 30, 100, 30, 15, 20, "Throwing his 300 kgs of Korean and US military uniforms from his closet", 0, 1, 800);
    final static Character kyungseok5 = new Friends("Kyungseok5", 0, 30, 100, 30, 15, 20, "Throwing his 300 kgs of Korean and US military uniforms from his closet", 0, 1, 800);
    final static Character kyungseok6 = new Friends("Kyungseok6", 0, 30, 100, 30, 15, 20, "Throwing his 300 kgs of Korean and US military uniforms from his closet", 0, 1, 800);
    final static Agit map = new Agit();
    static HashMap<Integer, Character> map_characters = new HashMap<Integer, Character>();
    static HashMap<Integer, Character> map_chosenMember = new HashMap<Integer, Character>();


    public static void main(String[] args) throws IOException {

        map_characters.put(1, kyungseok);
        map_characters.put(2, kyungseok1);
        map_characters.put(3, kyungseok2);
        map_characters.put(4, kyungseok3);
        map_characters.put(5, kyungseok4);
        map_characters.put(6, kyungseok5);
        map_characters.put(7, kyungseok6);


        board.loadAsk();
        board.choose_characters();


    }


    static void input() {
        while (!sc.hasNextInt()) {
            sc.next();
        }
    }


    static void loadAsk() {   //load_ask
        File f = new File("Nums.txt"); //change to saved
        if (f.exists() && !f.isDirectory()) {
            System.out.println("** You have a saved game. Do you want to continue?\n");
            System.out.println("1. Yes, I'd like to continue.");
            System.out.println("2. No, I'd like to restart.");
            /*introAsk1();   //load_ask_1*/

        } else {
            board.introAsk(); // intro_ask
        }

    }

     /*static void introAsk1() {
        input();
        int select = sc.nextInt();
        sc.nextLine();
        if (select == 1) {
            try {
                *//*loadGame();*//*  //check.
                agit();
            } catch (IOException e) {
                System.out.println("Cannot find a file");

                e.printStackTrace();
            }
        } else if (select == 2) {
            System.out.println("Are you sure you want to restart? (if you restart, the saved file will be overwritten)");
            System.out.println("1. Yes");
            System.out.println("2. No");
            doubleCheck();  //load_ask_2

        } else {
            System.out.println("Either 1 or 2");
            introAsk1();
        }

    }*/

    void doubleCheck() {
        input();
        int select = sc.nextInt();
        sc.nextLine();
        if (select == 1) {
            introAsk();
        } else if (select == 2) {
            System.out.println("\n");
            loadAsk();
        } else {
            System.out.println("Either 1 or 2");
            doubleCheck();
        }
    }


    void introAsk() {
        System.out.println("Is this your first time playing?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        input();
        int select = sc.nextInt();
        sc.nextLine();
        if (select == 1) {
            System.out.println("Please enter to continue");
            sc.nextLine();
            System.out.println("** ..It's your choice whether you believe the story or not..");
            sc.nextLine();


            System.out.println("...");
            sc.nextLine();
            System.out.println(".....");
            sc.nextLine();

            intro1();   //intro_first_story
            System.out.println("\n\n\n\n");
            sc.nextLine();

            System.out.println("Now I'm going to let you choose five members of our group.");
            sc.nextLine();
            System.out.println("Keep in mind that each character has own skill.");
            sc.nextLine();
            System.out.println(
                    "You will find out the skill when you play the character, which means you will never know unless you choose the character.\n");
            sc.nextLine();
            System.out.println("\n\nNow Game has begun.");
            sc.nextLine();
        } else if (select == 0 || select > 2) {
            System.out.println("**Either 1 or 2\n");
            introAsk();

        }

    }


    void intro1() {

        Path read = Paths.get("intro1.txt");

        try {
            BufferedReader reader = Files.newBufferedReader(read, Charset.forName("euc-kr"));

            String lineS = reader.readLine();

            while (lineS != null) {

                System.out.println(lineS);
                sc.nextLine();
                lineS = reader.readLine();

            }

        } catch (IOException e) {
            System.out.println("Cannot find the file");
            e.printStackTrace();
        }

    }

    void choose_characters() {  //choose_characters
        System.out.println(" ___________________________________ ");
        System.out.println("|          CHARACTER SELECT         ");
        System.out.println("|                                   ");
        System.out.println("|  (1) Kyungseok     (2) Hyungun    ");
        System.out.println("|  (3) Jongwoo       (4) Jungguen   ");
        System.out.println("|  (5) Taeguen       (6) Dongyeung ");
        System.out.println("|  (7) Daesung       (8) Byungsoo   ");
        System.out.println("|  (9) Daeguen       (10) Sean      ");
        System.out.println("|___________________________________");
        System.out.println("Choose five members\n");
        System.out.println("SELECT CHARACTER #1");
        System.out.println("?, ?, ?, ? & ?");


        int count = 1;
        while (count < 6) {
            choose_character(count);
            count++;
        }
        choose_character_2();

    }


    void choose_character(int count) {

        input();
        int select = sc.nextInt();
        sc.nextLine();

        choose_character_1(select, count);


    }


    void choose_character_1(int corresponding_character_map, int count) { //choose_character_1
        if (map_chosenMember.containsValue((map_characters.get(corresponding_character_map)))) {
            System.out.println("You've already chosen " + map_characters.get(corresponding_character_map).getName() + ", Choose different character");
            choose_character(count);
        } else {
            map_chosenMember.put(count, map_characters.get((corresponding_character_map)));
            System.out.println("You've chosen " + map_characters.get(corresponding_character_map).getName() + " !");


            Set<Integer> cha = map_chosenMember.keySet();
            for (int i : cha) {
                System.out.println(map_chosenMember.get(i).getName());
            }

        }

    }

    void choose_character_2() { //choose_character_2

        System.out.println("\nDo you want to proceed? (You can't change members in the middle of the game.)\n");

        System.out.println("1. Yes, lets do this!!");
        System.out.println("2. No, I just realized how bad those characters I've chosen are.\nLet me choose again.");
        input();
        int select = sc.nextInt();
        sc.nextLine();
        if (select == 1) {
            map.welcomePma();
            map.image_agit();
        } else if (select == 2) {
            for (int i = map_chosenMember.size(); i >= 0; i--) {
                map_chosenMember.remove(i);


            }

            choose_characters();
        } else {
            System.out.println("**Either 1 or 2\n");
            choose_character_2();

        }

    }


   /* public static void save() {
        System.out.println("Do you want to save game?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        input();
        int select = sc.nextInt();
        sc.nextLine();
        if (select == 1) {
            *//*  saveGame();*//*
            System.out.println("**Game has been saved");
            sc.nextLine();
            agit();
        } else if (select == 2) {
            agit();
        } else {
            System.out.println("Either 1 or 2");
            save();

        }
    }*/
  /* public static void ending() {
       System.out.println(".");
       sc.nextLine();
       System.out.println("..");
       sc.nextLine();

       Path read = Paths.get("ending.txt");
       int a = 0;
       try {
           BufferedReader reader = Files.newBufferedReader(read, Charset.forName("euc-kr"));

           String lineS = reader.readLine();

           while (lineS != null) {
               System.out.println(lineS);

               sc.nextLine();
               lineS = reader.readLine();
               a++;
           }

       } catch (IOException e) {
           System.out.println("Cannot find the file");
           e.printStackTrace();
       }
   }*/

}
