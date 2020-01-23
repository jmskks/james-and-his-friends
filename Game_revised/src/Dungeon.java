import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Dungeon {

    static Scanner sc = new Scanner(System.in);

    static void input() {
        while (!sc.hasNextInt()) {
            sc.next();
        }
    }

    static int player_died_count = 0;
    static int enemy_died_count = 0;
    static boolean bln_escaped = false;
    static boolean bln_boss_killed = false;

    public boolean isBln_intro() {
        return bln_intro;
    }

    public void setBln_intro(boolean bln_intro) {
        this.bln_intro = bln_intro;
    }

    public boolean isBln_ending() {
        return bln_ending;
    }

    public void setBln_ending(boolean bln_ending) {
        this.bln_ending = bln_ending;
    }

    public boolean isBln_fight_done() {
        return bln_fight_done;
    }

    public void setBln_fight_done(boolean bln_fight_done) {
        this.bln_fight_done = bln_fight_done;
    }

    public boolean isBln_enemyFirst() {
        return bln_enemyFirst;
    }

    public void setBln_enemyFirst(boolean bln_enemyFirst) {
        this.bln_enemyFirst = bln_enemyFirst;
    }

    private boolean bln_intro;
    private boolean bln_ending;

    private boolean bln_fight_done;
    private boolean bln_enemyFirst;
    static int boss_slash_count = 0;

    Dungeon() {
        this.setBln_intro(false);
        this.setBln_ending(false);
        this.setBln_enemyFirst(false);
        this.setBln_fight_done(false);

    }

    static HashMap<Integer, Character> map_enemies = new HashMap<Integer, Character>();


    void create_enemies() {

        Character enemy;
        Random rand = new Random();

        if (Agit.la.bln_la_on) {
            for (int i = 1; i < 4; i++) {
                map_enemies.remove(i);

            }

            enemy = new Enemy("** Owner", 1, 1650, 80, "none", 1, 4000, 10);

            map_enemies.put(1, enemy);

        } else if (Agit.vegas.bln_vegas_on) {
            for (int i = 1; i < 4; i++) {
                map_enemies.remove(i);

            }

            enemy = new Enemy("** Pervert", 785000, 785000, 120, "none", 1/*1000*/, 5000, 7);

            map_enemies.put(1, enemy);
        } else {


            for (int i = 1; i < 4; i++) {

                int num = rand.nextInt(100) + 1;

                if (num < 7) {
                    enemy = new Enemy("Dog", 1, 10, 1, "None", 200, 70, 0);
                } else if (num > 8 && num < 20) {
                    enemy = new Enemy("Drunken", 12, 120, 30, "None", 120, 50, 0);
                } else {
                    enemy = new Enemy("Drunken", 9, 90, 20, "None", 100, 50, 0);
                }
                map_enemies.put(i, enemy);
            }
        }

    }


}


class Ku extends Dungeon {


    Ku() {
        super();
    }


    void ku() {

        if (!this.isBln_intro()) {
            /*  ku_prolog();*/
            this.setBln_intro(true);
        }

        map_ku();


    }


    /*public static void ku_prolog() {

        Path read = Paths.get("ku_prolog.txt");
        int a = 0;
        try {
            BufferedReader reader = Files.newBufferedReader(read, Charset.forName("euc-kr"));

            String lineS = reader.readLine();

            while (lineS != null) {
                if (a == 4 || a == 7 || a == 8 || a == 10 || a == 12 || a == 14) {
                    System.out.println(lineS);
                    sc.nextLine();

                } else {
                    fortalk();
                    System.out.println(chosenMember[rand_talk] + ":\n" + lineS);

                }
                sc.nextLine();
                lineS = reader.readLine();
                a++;
            }

        } catch (IOException e) {
            System.out.println("Cannot find the file");
            e.printStackTrace();
        }

    }*/

    void map_ku() {  //start_ku
       /* Path read = Paths.get("ku_intro.txt");
        int a = 0;
        try {
            BufferedReader reader = Files.newBufferedReader(read, Charset.forName("euc-kr"));

            String lineS = reader.readLine();

            while (lineS != null) {
                if (a == 0 || a == 2) {
                    System.out.println(lineS);

                } else {
                    fortalk();
                    System.out.println(chosenMember[rand_talk] + ":\n" + lineS);

                }
                sc.nextLine();
                lineS = reader.readLine();
                a++;
            }

        } catch (IOException e) {
            System.out.println("Cannot find the file");
            e.printStackTrace();
        }*/

        System.out.println("Going into the fight..");
        sc.nextLine();
        create_enemies();
        this.setBln_fight_done(false);

    }
     /* public static void KuEnd() {

        System.out.println("\n\n\n\n\n");
        Path read = Paths.get("KuEnd.txt");
        int a = 0;
        try {
            BufferedReader reader = Files.newBufferedReader(read, Charset.forName("euc-kr"));

            String lineS = reader.readLine();

            while (lineS != null) {
                if (a == 2 || a == 4 || a == 6 || a == 7 || a == 9) {
                    System.out.println(lineS);

                } else {
                    fortalk();
                    System.out.println(chosenMember[rand_talk] + ":\n" + lineS);

                }
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

class tigs extends Dungeon {
    /*public static void tigsTalk() {
        System.out.println("\n\n\n\n");
        Path read = Paths.get("tigsTalk.txt");
        int a = 0;
        try {
            BufferedReader reader = Files.newBufferedReader(read, Charset.forName("euc-kr"));

            String lineS = reader.readLine();

            while (lineS != null) {
                if (a == 7 || a == 9 || a == 11 || a == 13 || a == 15 || a == 18 || a == 20 || a == 22 || a == 24) {
                    System.out.println(lineS);

                } else {
                    fortalk();
                    System.out.println(chosenMember[rand_talk] + ":\n" + lineS);

                }
                sc.nextLine();
                lineS = reader.readLine();
                a++;
            }

        } catch (IOException e) {
            System.out.println("Cannot find the file");
            e.printStackTrace();
        }
    }*/

   /* public static void tigsIn() {

        Path read = Paths.get("tigsIn.txt");
        int a = 0;
        try {
            BufferedReader reader = Files.newBufferedReader(read, Charset.forName("euc-kr"));

            String lineS = reader.readLine();

            while (lineS != null) {
                if (a == 0) {
                    System.out.println(lineS);

                } else {
                    fortalk();
                    System.out.println(chosenMember[rand_talk] + ":\n" + lineS);

                }
                sc.nextLine();
                lineS = reader.readLine();
                a++;
            }

        } catch (IOException e) {
            System.out.println("Cannot find the file");
            e.printStackTrace();
        }
    }*/


    /*public static void tigsEnd() {

        Path read = Paths.get("tigsEnd.txt");
        int a = 0;
        try {
            BufferedReader reader = Files.newBufferedReader(read, Charset.forName("euc-kr"));

            String lineS = reader.readLine();

            while (lineS != null) {
                if (a == 3 || a == 5 || a == 6 || a == 7 || a == 10 || a == 15) {
                    System.out.println(lineS);

                } else {
                    fortalk();
                    System.out.println(chosenMember[rand_talk] + ":\n" + lineS);

                }
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

class La extends Dungeon {

    static boolean bln_la_on;
    static boolean bln_la_talk;


    La() {
        super();
        this.bln_la_on = false;
        this.bln_la_talk = false;

    }


    void la() {
        Agit.la.bln_la_on = true;
        if (!this.isBln_intro()) {
            /*  ku_prolog();*/
            this.setBln_intro(true);
        }

        map_la();


    }

    void map_la() {
        System.out.println("Going into the fight..");
        sc.nextLine();
        create_enemies();
        this.setBln_fight_done(false);
    }





    /*public static void laTalk() {

        Path read = Paths.get("laTalk.txt");
        int a = 0;
        try {
            BufferedReader reader = Files.newBufferedReader(read, Charset.forName("euc-kr"));

            String lineS = reader.readLine();

            while (lineS != null) {
                if (a == 11 || a == 17 || a == 18 || a == 23 || a == 26 || a == 31 || a == 32 || (a >= 34 && a <= 39)
                        || a == 42 || (a >= 45 && a <= 51) || a == 53 || a == 56 || a == 58 || a == 62) {
                    System.out.println(lineS);

                } else {
                    fortalk();
                    System.out.println(chosenMember[rand_talk] + ":\n" + lineS);

                }
                sc.nextLine();
                lineS = reader.readLine();
                a++;
            }

        } catch (IOException e) {
            System.out.println("Cannot find the file");
            e.printStackTrace();
        }

    }*/

   /* public static void laEnd() {

        sc.nextLine();
        System.out.println("\n\n\n\n\n");
        Path read = Paths.get("laEnd.txt");
        int a = 0;
        try {
            BufferedReader reader = Files.newBufferedReader(read, Charset.forName("euc-kr"));

            String lineS = reader.readLine();

            while (lineS != null) {
                if (a == 2 || a == 4 || a == 6 || a == 7 || a == 10 || a == 14 || a == 18 || a == 19 || a == 30
                        || a == 31) {
                    System.out.println(lineS);

                } else {
                    fortalk();
                    System.out.println(chosenMember[rand_talk] + ":\n" + lineS);

                }
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

class Vegas extends Dungeon {

    static boolean bln_vegas_on;
    static boolean bln_vegas_talk;


    Vegas() {
        super();
        this.bln_vegas_on = false;
        this.bln_vegas_talk = false;

    }


    void vegas() {
        Agit.vegas.bln_vegas_on = true;
        if (!this.isBln_intro()) {
            /*  ku_prolog();*/
            this.setBln_intro(true);
        }

        map_vegas();


    }

    void map_vegas() {
        System.out.println("Going into the fight..");
        sc.nextLine();
        create_enemies();
        this.setBln_fight_done(false);
    }












    /*public static void vegasTalk() {
        Path read = Paths.get("vegasTalk.txt");
        int a = 0;
        try {
            BufferedReader reader = Files.newBufferedReader(read, Charset.forName("euc-kr"));

            String lineS = reader.readLine();

            while (lineS != null) {
                if (a == 9 || a == 15 || a == 20 || a == 22 || a == 24 || a == 26 || a == 30 || a == 31 || a == 33
                        || a == 35 || a == 36 || a == 39 || a == 41 || a == 43 || a == 45) {
                    System.out.println(lineS);

                } else {
                    fortalk();
                    System.out.println(chosenMember[rand_talk] + ":\n" + lineS);

                }
                sc.nextLine();
                lineS = reader.readLine();
                a++;
            }

        } catch (IOException e) {
            System.out.println("Cannot find the file");
            e.printStackTrace();
        }
    }*/

    /*public static void vegasEnd() {
        Path read = Paths.get("vegasEnd.txt");
        int a = 0;
        try {
            BufferedReader reader = Files.newBufferedReader(read, Charset.forName("euc-kr"));

            String lineS = reader.readLine();

            while (lineS != null) {
                if (a == 1 || a == 3 || a == 5 || a == 9 || a == 22 || a == 23 || a == 24 || a == 25) {
                    System.out.println(lineS);

                } else {
                    fortalk();
                    System.out.println(chosenMember[rand_talk] + ":\n" + lineS);

                }
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




