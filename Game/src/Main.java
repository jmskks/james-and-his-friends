
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main implements Serializable {

    static Character kyungseok = new Kyungseok();
    static Character hyungun = new Hyungun();
    static Character jongwoo = new Jongwoo();
    static Character junggun = new Junggun();
    static Character taegun = new Taegun();
    static Character daesung = new Daesung();
    static Character daegun = new Daegun();
    static Character sean = new Sean();
    static Character dongyung = new Dongyung();
    static Character byungsoo = new Byungsoo();

    static Character boss1 = new Enemy1(1650, 80, 1000, 4000, "Owner");
    static Character boss2 = new Enemy1(785000, 120, 5000, 5000, "Pervert");
    static Potion hpp = new Potion("hpp");
    static Potion mpp = new Potion("mpp");

    static Main main = new Main();
    static Character chosen;
    static Scanner sc = new Scanner(System.in);
    static int select;
    static int i = 0;
    static Random rand = new Random();
    static int rand_talk;
    static int randtalk_save;
    static boolean blnEscape = false;
    static boolean blnDungeon = false;
    static int deadEnemyCount = 0;
    static int deadTeamCount = 0;
    static boolean blnKuIntro = false;
    static boolean blnKuEnd = false;
    static int boss1Count = 0;
    static int boss2Count = 0;
    static boolean blnVegasFun = false;
    static boolean blnTigs = false;
    static boolean blnTurn = false;
    static boolean blnLaTalk = false;
    static boolean blnLa = false;
    static boolean blnLaBoss = false;
    static boolean blnLaBossKilled = false;
    static boolean blnTigsTalk = false;
    static boolean blnTigsEnd = false;
    static boolean blnVegas = false;
    static boolean blnVegasBoss = false;
    static boolean blnVegasBossKilled = false;
    static boolean blnStoreIn = false;
    static boolean blnPrice = false;
    static boolean blnPrice2 = false;
    static boolean blnVegasTalk = false;
    static Character[] chosenMember = new Character[5];
    static Character[] savedMember = new Character[5];
    static int[] savedInt = new int[19];

    public static void main(String[] args) throws IOException {

        loadAsk();

        charChooseC();

        talk2_afterChoose();
        welcomePma();

        agit();

    }// main

    public static void loadAsk() {
        File f = new File("Nums.txt");
        if (f.exists() && !f.isDirectory()) {
            System.out.println("** You have a saved game. Do you want to load it?\n");
            System.out.println("1. Yes, I'd like to continue.");
            System.out.println("2. No, I'd like to restart.");
            introAsk1();

        } else {
            introAsk();
        }

    }

    public static void introAsk1() {
        input();
        int select = sc.nextInt();
        sc.nextLine();
        if (select == 1) {
            try {
                loadGame();
                agit();
            } catch (IOException e) {
                System.out.println("Cannot find a file");

                e.printStackTrace();
            }
        } else if (select == 2) {
            System.out.println("Are you sure you'd like to restart? (if you restart, the saved file will be overwritten");
            System.out.println("1. Yes");
            System.out.println("2. No");
            doubleCheck();

        } else {
            System.out.println("Either 1 or 2");
            introAsk1();
        }

    }
    public static void doubleCheck() {
        input();
        int select = sc.nextInt();
        sc.nextLine();
        if (select == 1) {
            introAsk();
        }else if(select ==2) {
            System.out.println("\n");
            loadAsk();
        }else {
            System.out.println("Either 1 or 2");
            doubleCheck();
        }
    }
    public static void saveGame() {

        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Members.txt"));

            savedMember[0] = chosenMember[0];
            savedMember[1] = chosenMember[1];
            savedMember[2] = chosenMember[2];
            savedMember[3] = chosenMember[3];
            savedMember[4] = chosenMember[4];

            for (int i = 0; i < savedMember.length; i++) {
                out.writeObject(chosenMember[i]);
            }
            out.close();
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }

        if (blnKuIntro) {
            savedInt[3] = 0;

        } else {
            savedInt[3] = 1;
        }
        if (blnKuEnd) {
            savedInt[4] = 0;
        } else {
            savedInt[4] = 1;
        }
        if (blnTigs) {
            savedInt[5] = 0;
        } else {
            savedInt[5] = 1;
        }
        if (blnLaTalk) {
            savedInt[6] = 0;
        } else {
            savedInt[6] = 1;
        }

        if (blnLaBoss) {
            savedInt[7] = 0;
        } else {
            savedInt[7] = 1;
        }
        if (blnLaBossKilled) {
            savedInt[8] = 0;
        } else {
            savedInt[8] = 1;
        }
        if (blnTigsTalk) {
            savedInt[9] = 0;
        } else {
            savedInt[9] = 1;
        }

        if (blnVegas) {
            savedInt[10] = 0;
        } else {
            savedInt[10] = 1;
        }
        if (blnVegasBoss) {
            savedInt[11] = 0;
        } else {
            savedInt[11] = 1;
        }
        if (blnVegasBossKilled) {
            savedInt[12] = 0;
        } else {
            savedInt[12] = 1;
        }
        if (blnStoreIn) {
            savedInt[13] = 0;
        } else {
            savedInt[13] = 1;
        }
        if (blnPrice) {
            savedInt[14] = 0;
        } else {
            savedInt[14] = 1;
        }
        if (blnPrice2) {
            savedInt[15] = 0;
        } else {
            savedInt[15] = 1;
        }
        if (blnVegasTalk) {
            savedInt[16] = 0;
        } else {
            savedInt[16] = 1;
        }
        if (blnTigsEnd) {
            savedInt[17] = 0;
        } else {
            savedInt[17] = 1;
        }
        if (blnLa) {
            savedInt[18] = 0;
        } else {
            savedInt[18] = 1;
        }

        File outFile;
        BufferedWriter outWriter;

        try {
            outFile = new File("Nums.txt");
            outWriter = new BufferedWriter(new FileWriter(outFile));

            savedInt[0] = Character.hpNum;
            savedInt[1] = Character.mpNum;
            savedInt[2] = Character.money;

            for (int i = 0; i < savedInt.length; i++) {
                outWriter.write(savedInt[i] + "\r\n");
            }
            outWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void loadGame() throws IOException {

        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("Members.txt"));

            try {

                for (int i = 0; i < savedMember.length; i++) {

                    Character obj = (Character) in.readObject();
                    savedMember[i] = obj;

                }

                chosenMember[0] = savedMember[0];
                chosenMember[1] = savedMember[1];
                chosenMember[2] = savedMember[2];
                chosenMember[3] = savedMember[3];
                chosenMember[4] = savedMember[4];

            } catch (ClassNotFoundException e) {

                e.printStackTrace();
            }
            in.close();
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Cannot find a file");

            e.printStackTrace();
        }

        File inFile;
        BufferedReader inReader;
        inFile = new File("Nums.txt");
        inReader = new BufferedReader(new FileReader(inFile));

        for (int i = 0; i < savedInt.length; i++) {
            try {
                savedInt[i] = Integer.parseInt(inReader.readLine());

            } catch (IOException e) {
                System.out.println("Cannot find a file");

                e.printStackTrace();

            }

        }

        Character.hpNum = savedInt[0];
        Character.mpNum = savedInt[1];
        Character.money = savedInt[2];

        if (savedInt[3] == 0) {
            blnKuIntro = true;

        } else {
            blnKuIntro = false;
        }

        if (savedInt[4] == 0) {
            blnKuEnd = true;
        } else {
            blnKuEnd = false;
        }
        if (savedInt[5] == 0) {
            blnTigs = true;
        } else {
            blnTigs = false;
        }
        if (savedInt[6] == 0) {
            blnLaTalk = true;
        } else {
            blnLaTalk = false;
        }
        if (savedInt[7] == 0) {
            blnLaBoss = true;
        } else {
            blnLaBoss = false;
        }
        if (savedInt[8] == 0) {
            blnLaBossKilled = true;
        } else {
            blnLaBossKilled = false;
        }
        if (savedInt[9] == 0) {
            blnTigsTalk = true;
        } else {
            blnTigsTalk = false;
        }
        if (savedInt[10] == 0) {
            blnVegas = true;
        } else {
            blnVegas = false;
        }
        if (savedInt[11] == 0) {
            blnVegasBoss = true;
        } else {
            blnVegasBoss = false;
        }
        if (savedInt[12] == 0) {
            blnVegasBossKilled = true;
        } else {
            blnVegasBossKilled = false;
        }

        if (savedInt[13] == 0) {
            blnStoreIn = true;
        } else {
            blnStoreIn = false;
        }

        if (savedInt[14] == 0) {
            blnPrice = true;
        } else {
            blnPrice = false;
        }
        if (savedInt[15] == 0) {
            blnPrice2 = true;
        } else {
            blnPrice2 = false;
        }
        if (savedInt[16] == 0) {
            blnVegasTalk = true;
        } else {
            blnVegasTalk = false;
        }
        if (savedInt[17] == 0) {
            blnTigsEnd = true;
        } else {
            blnTigsEnd = false;
        }
        if (savedInt[18] == 0) {
            blnLa = true;
        } else {
            blnLa = false;
        }
        inReader.close();

    }

    public static void introAsk() {
        System.out.println("Is this your first time playing?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        input();
        select = sc.nextInt();
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

            intro1();
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

    public static void intro1() {

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

    public static void input() {
        while (!sc.hasNextInt()) {
            sc.next();
        }
    }

    public static void charChooseC() {
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

        choose_character(0);
        System.out.println(chosenMember[0] + ", ?, ?, ? & ?");
        choose_character(1);
        System.out.println(chosenMember[0] + ", " + chosenMember[1] + ", ?, ? & ?");
        choose_character(2);
        System.out.println(chosenMember[0] + ", " + chosenMember[1] + ", " + chosenMember[2] + ", ? & ?");
        choose_character(3);
        System.out.println(
                chosenMember[0] + ", " + chosenMember[1] + ", " + chosenMember[2] + ", " + chosenMember[3] + ", & ?");
        choose_character(4);
        System.out.println(chosenMember[0] + ", " + chosenMember[1] + ", " + chosenMember[2] + ", " + chosenMember[3]
                + ", " + chosenMember[4]);
        System.out.println("\n\n\n\n\n**The following members will be your team members**");
        System.out.println("[" + chosenMember[0] + ", " + chosenMember[1] + ", " + chosenMember[2] + ", "
                + chosenMember[3] + ", " + chosenMember[4] + "]");
        charChoose_1_AskAgain();
    }


    public static void charChoose_1_AskAgain() {

        System.out.println("\nDo you want to proceed? (You can't change the member in the middle of the game.)\n");

        System.out.println("1. Yes, lets do this!!");
        System.out.println("2. No, I just realized how retard those characters I've chosen are.\nLet me choose again.");
        input();
        select = sc.nextInt();
        sc.nextLine();
        if (select == 2) {
            charChooseC();
        } else if (select <= 0 || select > 2) {
            System.out.println("**Either 1 or 2\n");
            charChoose_1_AskAgain();

        }

    }

    public static void choose_character(int count) {
        boolean wrongAnswer;
        do {
            Scanner sc = new Scanner(System.in);
            wrongAnswer = false;
            while (!sc.hasNextInt()) {
                sc.next();
            }
            int select = sc.nextInt();
            sc.nextLine();
            switch (select) {

                case 1:

                    chooseCharacter(kyungseok, count);

                    break;
                case 2:
                    chooseCharacter(hyungun, count);

                    break;
                case 3:
                    chooseCharacter(jongwoo, count);
                    break;
                case 4:
                    chooseCharacter(junggun, count);
                    break;
                case 5:
                    chooseCharacter(taegun, count);
                    break;
                case 6:
                    chooseCharacter(dongyung, count);
                    break;
                case 7:
                    chooseCharacter(daesung, count);
                    break;
                case 8:
                    chooseCharacter(byungsoo, count);
                    break;
                case 9:
                    chooseCharacter(daegun, count);
                    break;
                case 10:
                    chooseCharacter(sean, count);
                    break;

                default:
                    System.out.println("From 1 to 10");
                    wrongAnswer = true;
            }

        } while (wrongAnswer);

    }

    public static void chooseCharacter(Character character, int count) {
        if (count == 0) {
            Main.chosenMember[count] = character;
        } else if (count == 1) {
            chosenMember[count] = character;
            if (chosenMember[count] == chosenMember[0]) {
                System.out.println("You've already chosen " + character + ", Choose different character");
                choose_character(count);
            }
        } else if (count == 2) {
            chosenMember[count] = character;
            if (chosenMember[count] == chosenMember[0] || chosenMember[count] == chosenMember[1]) {
                System.out.println("You've already chosen " + character + ", Choose different character");

                choose_character(count);
            }
        } else if (count == 3) {
            chosenMember[count] = character;
            if (chosenMember[count] == chosenMember[0] || chosenMember[count] == chosenMember[1]
                    || chosenMember[count] == chosenMember[2]) {
                System.out.println("You've already chosen " + character + ", Choose different character");

                choose_character(count);
            }
        } else if (count == 4) {
            chosenMember[count] = character;
            if (chosenMember[count] == chosenMember[0] || chosenMember[count] == chosenMember[1]
                    || chosenMember[count] == chosenMember[2] || chosenMember[count] == chosenMember[3]) {
                System.out.println("You've already chosen " + character + ", Choose different character");

                choose_character(count);
            }
        }
    }

    public static void talk2_afterChoose() {

        System.out.println("\n\n\n\nAlright, lets get back to the conversation..");
        sc.nextLine();
        System.out.println("..");
        sc.nextLine();
        System.out.println("...");
        sc.nextLine();
        System.out.println("....");
        sc.nextLine();

        Path read = Paths.get("talk2_afterChoose.txt");
        int a = 0;
        try {
            BufferedReader reader = Files.newBufferedReader(read, Charset.forName("euc-kr"));

            String lineS = reader.readLine();

            while (lineS != null) {
                if (a == 11) {
                    System.out.println(lineS);

                } else {
                    fortalk();
                    System.out.println(chosenMember[rand_talk] + ":");
                    System.out.println(lineS);

                }
                lineS = reader.readLine();
                sc.nextLine();
                a++;
            }

        } catch (IOException e) {
            System.out.println("Cannot find the file");
            e.printStackTrace();
        }

    }

    public static void welcomePma() {
        System.out.println("***Welcome to Pma***");
        sc.nextLine();
        System.out.println("**This is your Agit now");
        sc.nextLine();
    }

    public static void agit() {

        System.out.println("_____________________________________________________________________");
        System.out.println("|                           ***Pma***                                ");
        System.out.println("|                                                Portions            ");
        System.out.println("|     Money: $" + Character.money + "                              HP:" + Character.hpNum
                + " MP:" + Character.mpNum + "                                                        ");
        System.out.println("|                                                                    ");
        System.out.println("|  (1) Character Status               (2) Store           ");
        System.out.println("|  (3) Ku (Dungeon 1)                 (4) Tigs  (Dungeon 2)                       ");
        System.out.println("|  (5) Rest                           (6) Vacation                   ");
        System.out.println("|  (7) Save Game                                                          ");
        System.out.println("|____________________________________________________________________");

        agit_ask1();

    }

    public static void boss() {
        if (!blnTigs) {
            System.out.println("Beat the boss if you'd like to be able to enter Tigs");
            sc.nextLine();
            System.out.println("Do you want to proceed?");
            sc.nextLine();
            System.out.println("1. Yes");
            System.out.println("2. No");
            bossEnter();

        }
    }

    public static void bossEnter() {
        input();
        select = sc.nextInt();
        sc.nextLine();
        if (select == 1) {
            bossStart();
        } else if (select == 2) {
            System.out.println("Scared..?");
            agit();
        }

    }

    public static void bossStart() {

        System.out.println("..");
        System.out.println("....");
    }

    public static void agit_ask1() {

        boolean wrongAnswer;
        do {
            Scanner sc = new Scanner(System.in);
            wrongAnswer = false;
            System.out.println("\n**What do you want to do?**\n");
            while (!sc.hasNextInt()) {
                sc.next();
            }
            int select = sc.nextInt();
            sc.nextLine();
            switch (select) {

                case 1:
                    status();
                    break;
                case 2:
                    store();

                    break;
                case 3:
                    ku();

                    break;
                case 4:
                    if (!blnTigs) {
                        System.out.println("**Cannot enter");
                        agit_ask1();

                    } else {
                        tigs();
                    }
                    break;
                case 5:
                    rest();
                    break;
                case 6:
                    vacation();
                    break;
                case 7:
                    System.out.println("Do you want to save game?");
                    System.out.println("1. Yes");
                    System.out.println("2. No");

                    save_ask();

                    break;

                default:
                    System.out.println("From 1 to 7");
                    wrongAnswer = true;
            }

        } while (wrongAnswer);
    }

    public static void save_ask() {
        input();
        int select = sc.nextInt();
        sc.nextLine();
        if (select == 1) {
            saveGame();
            System.out.println("**Game has been saved");
            sc.nextLine();
            agit();
        } else if (select == 2) {
            agit();
        } else {
            System.out.println("Either 1 or 2");
            save_ask();

        }
    }

    public static void tigsTalk() {
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
    }

    public static void tigsIn() {

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
    }
    public static void tigs() {
        if (!blnTigsTalk) {
            tigsTalk();
            blnTigsTalk = true;

        }
        tigsIn();
        System.out.println("Going into the fight..");
        sc.nextLine();
        Tigs tigs = new Tigs();

        fightStatus("Tigs");
        turnAskCoin();
        while (!blnDungeon) {

            if (blnTurn) {
                System.out.println("** Enemy turn has started!");
                sc.nextLine();
                enemyTurn(Map.enemy1);

                if (blnDungeon) {
                    break;
                }
                fightStatus("Tigs");
                enemyTurn(Map.enemy2);

                if (blnDungeon) {
                    break;
                }
                fightStatus("Tigs");
                enemyTurn(Map.enemy3);

                if (blnDungeon) {
                    break;
                }
                fightStatus("Tigs");

                System.out.println("** Enter to end the Enemy's turn");
                sc.nextLine();
                blnTurn = false;
            }

            System.out.println("** Your turn has started!");
            sc.nextLine();
            ourTurnReady(chosenMember[0]);
            if(!blnDungeon&&chosenMember[0].getHp()!=0) {
                fightStatus("Tigs");
            }
            if (blnDungeon) {
                break;
            }
            ourTurnReady(chosenMember[1]);
            if(!blnDungeon&&chosenMember[1].getHp()!=0) {
                fightStatus("Tigs");
            }
            if (blnDungeon) {
                break;
            }
            ourTurnReady(chosenMember[2]);
            if(!blnDungeon&&chosenMember[2].getHp()!=0) {
                fightStatus("Tigs");
            }
            if (blnDungeon) {
                break;
            }
            ourTurnReady(chosenMember[3]);
            if(!blnDungeon&&chosenMember[3].getHp()!=0) {
                fightStatus("Tigs");
            }
            if (blnDungeon) {
                break;
            }
            ourTurnReady(chosenMember[4]);
            if(!blnDungeon&&chosenMember[4].getHp()!=0) {
                fightStatus("Tigs");
            }
            if (blnDungeon) {
                break;
            }
            System.out.println("** Enter to end your turn");
            sc.nextLine();

            System.out.println("** Enemy turn has started!");
            sc.nextLine();
            enemyTurn(Map.enemy1);

            if (blnDungeon) {
                break;
            }fightStatus("Tigs");
            enemyTurn(Map.enemy2);

            if (blnDungeon) {
                break;
            }fightStatus("Tigs");
            enemyTurn(Map.enemy3);

            if (blnDungeon) {
                break;
            }fightStatus("Tigs");
            System.out.println("** Enter to end the Enemy's turn");
            sc.nextLine();
        }
        if (deadTeamCount == 5) {
            System.out.println("** Shamed to lose");
            sc.nextLine();
            System.out.println("** Your team has lost 10% of Money and each member's EXP");
            int moneyLost = Character.money / 10;
            Character.money = Character.money - moneyLost;

            System.out.println("You lost -$" + moneyLost);
            for (Character a : chosenMember) {
                int expLost = a.getExp() / 10;
                a.setExp(a.getExp() - expLost);
                System.out.println(a + " lost " + expLost+"exp");
            }

            sc.nextLine();

            System.out.println("..You're carried to Agit..\n\n\n\n");
            sc.nextLine();

        } else {
            System.out.println("** You beat the enemies! **\n");
            sc.nextLine();
            int expTotal = Map.enemy1.getExp() + Map.enemy2.getExp() + Map.enemy3.getExp();
            System.out.println("** Each member has earned " + expTotal + "exp !\n");
            sc.nextLine();
            for (Character a : chosenMember) {
                a.setExp(a.getExp() + expTotal);
                System.out.println(a + ": " + a.getExp() + "/" + a.getLvExp());
            }

            for (Character a : chosenMember) {
                exp(a);
            }

            sc.nextLine();
            if (!blnTigsEnd) {
                tigsEnd();
                blnTigsEnd = true;
            }
            Map.enemy1 = null;
            Map.enemy2 = null;
            Map.enemy3 = null;

            System.out.println("** Going back to Agit..");
            sc.nextLine();

            // exp
        }
        blnDungeon = false;
        deadEnemyCount = 0;
        deadTeamCount = 0;

        for (Character a : Main.chosenMember) {
            if (a.getHp() == 0) {
                a.setHp(1);
            }
        }

        agit();

    }

    public static void tigsEnd() {

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
    }

    public static void rest() {
        int price = 300;
        if (blnTigs) {
            price = 1200;
            if (!blnPrice2) {
                System.out.println("Man.. the inflation of economy caused all the price to go up..");
                sc.nextLine();
                System.out.println(
                        "You make a lot more money now.. so I'm sure you would understand us raising the price of staying here..");
                sc.nextLine();
                blnPrice2 = true;
            }
        }
        System.out.println("Do you want to go to hotel and get some sleep? [$" + price + "] per night");
        System.out.println("(Everyone's HP and MP will be healed by 40% of their original HP and MP)");
        System.out.println("1. Yes");
        System.out.println("2. No");
        input();
        select = sc.nextInt();
        sc.nextLine();
        if (select == 1) {
            if (Character.money < price) {
                System.out.println("You don't have enough money to get rest.. what a miserable life..");
                agit();
            } else {
                System.out.println("-$" + price);
                sc.nextLine();
                Character.money = Character.money - price;
                for (Character a : chosenMember) {
                    restHealed(a);
                }
                for (Character a : chosenMember) {
                    statSwitch1(a);
                }
                sc.nextLine();
                System.out.println("** Members are all healed by 40% of their HP and MP");
                sc.nextLine();


                agit();
            }
        } else if (select == 2) {
            agit();
        } else {
            System.out.println("Either 1 or 2\n\n");
            rest();
        }
    }

    public static void statSwitch1(Character member) {
        System.out.println("\n" + member);
        System.out.println("Lv :" + member.getLv());
        System.out.println("Exp :" + member.getExp() + "/" + member.getLvExp());
        System.out.println("HP :" + member.getHp());
        System.out.println("MP :" + member.getMp());
        System.out.println("ATT :" + member.getAtt());
    }

    public static void restHealed(Character member) {
        int hp = (int) (member.getMaxHp() * .4);
        int mp = (int) (member.getMaxMp() * .4);

        if (member.getHp() + hp >= member.getMaxHp()) {
            member.setHp(member.getMaxHp());
        } else {
            member.setHp(member.getHp() + hp);
        }
        if (member.getMp() + mp >= member.getMaxMp()) {
            member.setMp(member.getMaxMp());
        } else {
            member.setMp(member.getMp() + mp);
        }

    }

    public static void store() {
        if (!blnStoreIn) {
            blnStoreIn = true;
            storeIntro();

        } else {
            storeIn();
        }
    }

    public static void storeIntro() {
        System.out.println("**Welcome to our Store**");
        sc.nextLine();
        System.out.println("** You can buy whatever you need, whatever you want in our store.");
        sc.nextLine();
        System.out.println("Would you like to enter?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        input();
        select = sc.nextInt();
        sc.nextLine();
        if (select == 1) {
            storeIn();
        } else if (select == 2) {
            agit();
        } else {
            System.out.println("Either 1 or 2\n\n");
            storeIntro();
        }
    }

    public static void storeIn() {
        int price = 200;
        if (blnTigs) {
            price = 700;
            if (!blnPrice) {
                System.out.println("Man.. the inflation of economy caused all the price to go up..");
                sc.nextLine();
                System.out.println(
                        "You make a lot more money now.. so I'm sure you would understand us raising the price of portions..");
                sc.nextLine();
                blnPrice = true;
            }
        }
        System.out.println("**Welcome to our Store**\n");
        System.out.println("\n..Unfortunately these are all we got....");
        System.out.println("1. HP portion [$" + price + "]");
        System.out.println("2. MP portion [$" + price + "]");
        System.out.println("3. Exit");

        input();
        select = sc.nextInt();
        sc.nextLine();
        switch (select) {
            case 1:

                buyPortion(hpp);

                break;
            case 2:
                buyPortion(mpp);

                break;
            case 3:
                System.out.println("..Going back to Agit..");
                sc.nextLine();
                agit();
                break;
            default:
                System.out.println("From 1 to 3\n");
                storeIn();
                break;
        }

    }

    public static void buyPortion(Potion por) {

        System.out.println("How many would you like to buy?");
        System.out.println("1");
        System.out.println("2");
        System.out.println("3");
        System.out.println("4");
        System.out.println("5");
        System.out.println("6. Go back");

        input();
        int select = sc.nextInt();
        if (select < 0 || select > 7) {
            System.out.println("From 1 to 6\n\n");
            buyPortion(por);
        } else if (select == 6) {
            storeIn();
        } else {
            selectPortion(select, por);
        }
    }

    public static void selectPortion(int select, Potion por) {
        int price = 200;
        if (blnTigs) {
            price = 700;
        }

        int total = price * select;
        System.out.println("\n\n** Total price is $" + total + " for " + select);
        System.out.println("** Are you sure you'd like to purchase?\n");
        System.out.println("1. Yes");
        System.out.println("2. Let me choose different amount");
        System.out.println("3. Go back");

        input();
        sc.nextLine();
        int select1 = sc.nextInt();
        sc.nextLine();
        if (select1 == 1) {
            if (Character.money < total) {
                System.out.println("** You don't have enough money to purchase the portions");
                buyPortion(por);
            } else {
                System.out.println("\n-$" + total);
                System.out.println("** You've purchased " + select + " of portion");
                Character.money = Character.money - total;
                if (por.getName().equals("hpp")) {
                    Character.hpNum = Character.hpNum + select;
                    System.out.println("** You now have " + Character.hpNum + " portions");
                } else {
                    Character.mpNum = Character.mpNum + select;
                    System.out.println("** You now have " + Character.mpNum + " portions");
                }
                sc.nextLine();
                System.out.println("..Going back to Agit..");
                sc.nextLine();
                agit();
            }
        } else if (select1 == 2) {
            buyPortion(por);
        } else if (select1 == 3) {
            storeIn();
        } else {
            System.out.println("From 1 to 3\n\n");
            selectPortion(select, por);
        }

    }

    public static void status() {

        System.out.println("_____________________________________");
        System.out.println("|              CHARACTER              ");
        System.out.println("|                                    ");
        System.out.println("|  (1) " + chosenMember[0]   );
        System.out.println("|  (2) " + chosenMember[1]        );
        System.out.println("|  (3) " + chosenMember[2]       );
        System.out.println("|  (4) " + chosenMember[3]                   );
        System.out.println("|  (5) " + chosenMember[4]                    );
        System.out.println("|  (6) drink a portion                                   ");
        System.out.println("|  (7) Go Back                                  ");
        System.out.println("|____________________________________");
        status_ask();

    }

    public static void status_ask() {
        System.out.println("**Choose a character that you would like to check on\n");
        input();
        select = sc.nextInt();
        sc.nextLine();
        switch (select) {
            case 1:
                statSwitch(chosenMember[0]);
                break;
            case 2:
                statSwitch(chosenMember[1]);
                break;
            case 3:
                statSwitch(chosenMember[2]);
                break;
            case 4:
                statSwitch(chosenMember[3]);
                break;
            case 5:
                statSwitch(chosenMember[4]);
                break;
            case 6:
                whichPortion();

                break;
            case 7:
                agit();
                break;
            default:
                System.out.println("You have only 5 members");
                status_ask();
                break;

        }

    }

    public static void whichPortion() {
        System.out.println("Which portion?");
        System.out.println("1.(80% heal) HP portion:" + Character.hpNum);
        System.out.println("2.(80% heal) MP portion:" + Character.mpNum);
        System.out.println("3. Cancel");
        status_ask_portion_ask();
    }

    public static void status_ask_portion_ask() {
        input();
        int select = sc.nextInt();
        if (select == 1) {
            whichCharacter1();
            chooseHHCha1();
            sc.nextLine();

        } else if (select == 2) {
            whichCharacter2();
            chooseMMCha1();
            sc.nextLine();

        } else if (select == 3) {

        } else {
            System.out.println("From 1 to 3\n\n");
            status_ask_portion_ask();
        }

        agit();
    }

    public static void chooseMMCha(Character chosenMember) {
        if (chosenMember.getMp() == chosenMember.getMaxMp()) {
            System.out.println(chosenMember + "'s MP is full. Choose different character");
            chooseMMCha1();
            return;
        }
        int a = (int) (chosenMember.getMaxMp() * .8);
        int b;
        if (chosenMember.getMp() != 0) {
            if (chosenMember.getMp() + a > chosenMember.getMaxMp()) {
                b = chosenMember.getMaxMp() - chosenMember.getMp();
                chosenMember.setMp(chosenMember.getMaxMp());
                System.out.println("** " + chosenMember + "'s MP is healed by " + b);
            } else {
                chosenMember.setMp(chosenMember.getMp() + a);
                System.out.println("** " + chosenMember + "'s MP is healed by " + a);

            }
            Character.mpNum--;
        } else {
            System.out.println("He is already knocked down. Choose different character");
            chooseMMCha(chosenMember);
        }
        sc.nextLine();
    }

    public static void chooseMMCha1() {
        boolean wrongAnswer;
        do {
            wrongAnswer = false;
            Scanner sc = new Scanner(System.in);
            while (!sc.hasNextInt()) {
                sc.next();
            }
            int select = sc.nextInt();
            sc.nextLine();
            switch (select) {
                case 1:
                    chooseMMCha(chosenMember[0]);
                    break;
                case 2:
                    chooseMMCha(chosenMember[1]);
                    break;
                case 3:
                    chooseMMCha(chosenMember[2]);
                    break;
                case 4:
                    chooseMMCha(chosenMember[3]);
                    break;
                case 5:
                    chooseMMCha(chosenMember[4]);
                    break;
                case 6:
                    whichPortion();
                    break;

                default:
                    System.out.println("From 1 to 6");
                    wrongAnswer = true;
                    break;

            }
        } while (wrongAnswer);
    }

    public static void chooseHHCha1() {
        boolean wrongAnswer;
        do {
            wrongAnswer = false;
            Scanner sc = new Scanner(System.in);
            while (!sc.hasNextInt()) {
                sc.next();
            }
            int select = sc.nextInt();
            sc.nextLine();
            switch (select) {
                case 1:
                    chooseHHCha(chosenMember[0]);
                    break;
                case 2:
                    chooseHHCha(chosenMember[1]);
                    break;
                case 3:
                    chooseHHCha(chosenMember[2]);
                    break;
                case 4:
                    chooseHHCha(chosenMember[3]);
                    break;
                case 5:
                    chooseHHCha(chosenMember[4]);
                    break;
                case 6:
                    whichPortion();
                    break;

                default:
                    System.out.println("From 1 to 6");
                    wrongAnswer = true;
                    break;

            }
        } while (wrongAnswer);

    }

    public static void chooseHHCha(Character chosenMember) {
        if (chosenMember.getHp() == chosenMember.getMaxHp()) {
            System.out.println(chosenMember + "'s HP is full. Choose different character");
            chooseHHCha1();
            return;
        }
        int a = (int) (chosenMember.getMaxHp() * .8);
        int b;
        if (chosenMember.getHp() != 0) {
            if (chosenMember.getHp() + a > chosenMember.getMaxHp()) {
                b = chosenMember.getMaxHp() - chosenMember.getHp();
                chosenMember.setHp(chosenMember.getMaxHp());
                System.out.println("** " + chosenMember + "'s HP is healed by " + b);
            } else {
                chosenMember.setHp(chosenMember.getHp() + a);
                System.out.println("** " + chosenMember + "'s HP is healed by " + a);

            }
            Character.hpNum--;
        } else {
            System.out.println("He is already knocked down. Choose different character");
            chooseHHCha(chosenMember);
        }
        sc.nextLine();

    }

    public static void whichCharacter1() {
        System.out.println("\nto Who?");

        System.out.println(
                "1. " + chosenMember[0] + "   HP:" + chosenMember[0].getHp() + "/" + chosenMember[0].getMaxHp());
        System.out.println(
                "2. " + chosenMember[1] + "   HP:" + chosenMember[1].getHp() + "/" + chosenMember[1].getMaxHp());
        System.out.println(
                "3. " + chosenMember[2] + "   HP:" + chosenMember[2].getHp() + "/" + chosenMember[2].getMaxHp());
        System.out.println(
                "4. " + chosenMember[3] + "   HP:" + chosenMember[3].getHp() + "/" + chosenMember[3].getMaxHp());
        System.out.println(
                "5. " + chosenMember[4] + "   HP:" + chosenMember[4].getHp() + "/" + chosenMember[4].getMaxHp());
        System.out.println("6.  Go back");

    }

    public static void whichCharacter2() {
        System.out.println("\nto Who?");

        System.out.println(
                "1. " + chosenMember[0] + "   MP:" + chosenMember[0].getMp() + "/" + chosenMember[0].getMaxMp());
        System.out.println(
                "2. " + chosenMember[1] + "   MP:" + chosenMember[1].getMp() + "/" + chosenMember[1].getMaxMp());
        System.out.println(
                "3. " + chosenMember[2] + "   MP:" + chosenMember[2].getMp() + "/" + chosenMember[2].getMaxMp());
        System.out.println(
                "4. " + chosenMember[3] + "   MP:" + chosenMember[3].getMp() + "/" + chosenMember[3].getMaxMp());
        System.out.println(
                "5. " + chosenMember[4] + "   MP:" + chosenMember[4].getMp() + "/" + chosenMember[4].getMaxMp());
        System.out.println("6.  Go back");

    }



    public static void statSwitch(Character member) {
        System.out.println(member + "'s stats");
        System.out.println("Lv:" + member.getLv());
        System.out.println("Exp :" + member.getExp() + "/" + member.getLvExp());
        System.out.println("HP :" + member.getHp() + "/" + member.getMaxHp());
        System.out.println("MP :" + member.getMp() + "/" + member.getMaxMp());
        System.out.println("ATT :" + member.getAtt());

        status();
    }

    public static void fortalk() {
        Random rand1 = new Random();
        rand_talk = rand1.nextInt(5);
        if (rand_talk == randtalk_save) {
            fortalk();
        }
        randtalk_save = rand_talk;
    }

    public static void ku() {

        if (!blnKuIntro) {
            ku_prolog();
            blnKuIntro = true;
        }

        map_ku();
        fightStatus("Ku");
        turnAskCoin();
        while (!blnDungeon) {

            if (blnTurn) {
                System.out.println("** Enemy turn has started!");
                sc.nextLine();
                enemyTurn(Map.enemy1);
                if (blnDungeon) {
                    break;
                }
                fightStatus("Ku");
                enemyTurn(Map.enemy2);
                if (blnDungeon) {
                    break;
                }
                fightStatus("Ku");
                enemyTurn(Map.enemy3);
                if (blnDungeon) {
                    break;
                }
                fightStatus("Ku");

                System.out.println("** Enter to end the Enemy's turn");
                sc.nextLine();
                blnTurn = false;
            }

            System.out.println("** Your turn has started!");
            sc.nextLine();
            ourTurnReady(chosenMember[0]);
            if(!blnDungeon&&chosenMember[0].getHp()!=0) {
                fightStatus("Ku");
            }
            if (blnDungeon) {
                break;
            }
            ourTurnReady(chosenMember[1]);
            if(!blnDungeon&&chosenMember[1].getHp()!=0) {
                fightStatus("Ku");
            }
            if (blnDungeon) {
                break;
            }
            ourTurnReady(chosenMember[2]);
            if(!blnDungeon&&chosenMember[2].getHp()!=0) {
                fightStatus("Ku");
            }
            if (blnDungeon) {
                break;
            }
            ourTurnReady(chosenMember[3]);
            if(!blnDungeon&&chosenMember[3].getHp()!=0) {
                fightStatus("Ku");
            }
            if (blnDungeon) {
                break;
            }
            ourTurnReady(chosenMember[4]);
            if(!blnDungeon&&chosenMember[4].getHp()!=0) {
                fightStatus("Ku");
            }
            if (blnDungeon) {
                break;
            }
            System.out.println("** Enter to end your turn");
            sc.nextLine();

            System.out.println("** Enemy turn has started!");
            sc.nextLine();
            enemyTurn(Map.enemy1);

            if (blnDungeon) {
                break;
            }
            fightStatus("Ku");
            enemyTurn(Map.enemy2);

            if (blnDungeon) {
                break;
            }
            fightStatus("Ku");
            enemyTurn(Map.enemy3);

            if (blnDungeon) {
                break;
            }
            fightStatus("Ku");
            System.out.println("** Enter to end the Enemy's turn");
            sc.nextLine();
        }
        if (deadTeamCount == 5) {
            System.out.println("** Shamed to lose");
            sc.nextLine();
            System.out.println("** Your team has lost 10% of Money and each member's EXP");
            int moneyLost = Character.money / 10;
            Character.money = Character.money - moneyLost;

            System.out.println("You lost -$" + moneyLost);
            for (Character a : chosenMember) {
                int expLost = a.getExp() / 10;
                a.setExp(a.getExp() - expLost);
                System.out.println(a + " lost " + expLost+"exp.");
            }

            sc.nextLine();

            System.out.println("..You're carried to Agit..\n\n\n\n");
            sc.nextLine();

        } else {
            System.out.println("** You beat the enemies!");

            sc.nextLine();
            int expTotal = Map.enemy1.getExp() + Map.enemy2.getExp() + Map.enemy3.getExp();
            System.out.println("** Each member has earned " + expTotal + "exp !\n");
            sc.nextLine();

            for (Character a : chosenMember) {
                a.setExp(a.getExp() + expTotal);
                System.out.println(a + ": " + a.getExp() + "/" + a.getLvExp());
            }

            for (Character a : chosenMember) {
                exp(a);
            }

            sc.nextLine();
            if (!blnKuEnd) {
                KuEnd();
                blnKuEnd = true;
            }
            Map.enemy1 = null;
            Map.enemy2 = null;
            Map.enemy3 = null;

            System.out.println("** Going back to Agit..");
            sc.nextLine();

        }
        blnDungeon = false;
        deadEnemyCount = 0;
        deadTeamCount = 0;

        for (Character a : Main.chosenMember) {
            if (a.getHp() == 0) {
                a.setHp(1);
            }
        }

        agit();

    }

    public static void exp(Character chosenMember) {


        while(chosenMember.getExp() >= chosenMember.getLvExp()){
            int a = chosenMember.getLv();
            sc.nextLine();
            System.out.println("\n*** " + chosenMember + " Level up!!***");
            System.out.println("From "+chosenMember.getLv()+" -> "+(a+1));
            sc.nextLine();

            chosenMember.setLv(chosenMember.getLv() + 1);
            levelUp(chosenMember);
            if(!blnTigs) {
                chosenMember.setLvExp((chosenMember.getLvExp()) +700);
            }else {
                chosenMember.setLvExp((chosenMember.getLvExp()) +1500);
            }

            chosenMember.setMpAdj(chosenMember.getMpAdj()+2);
        }

    }

    public static void levelUp(Character chosenMember) {
        chosenMember.setMaxHp(chosenMember.getMaxHp() + 20);
        chosenMember.setMaxMp(chosenMember.getMaxMp() + 10);
        chosenMember.setAtt(chosenMember.getAtt() + 10);
        System.out.println(chosenMember + "'s HP has been increased by 20.");

        System.out.println(chosenMember + "'s MP has been increased by 10.");

        System.out.println(chosenMember + "'s ATT has been increased by 10.");
        sc.nextLine();
        System.out.println(
                "** I'm rolling a dice. You guess the number, and if it's correct, you're getting a bonus stat ");
        sc.nextLine();
        System.out.println("Choose from 1 to 4");
        levelUpAsk2(chosenMember);

    }

    public static void levelUpAsk2(Character chosenMember) {
        try {
            Random rand = new Random();
            Scanner sc2 = new Scanner(System.in);
            int percent = rand.nextInt(4) + 1;

            int num = sc2.nextInt();
            System.out.println("Dice:" + percent);
            if (num == percent) {
                System.out.println("** Congratulation!!");
                sc.nextLine();
                System.out.println("**Which one would you like to increase?");
                System.out.println("1. +20 HP");
                System.out.println("2. +10 MP");
                System.out.println("3. +5 ATT");

                levelUpAsk(chosenMember);
            } else if (num >= 5 || num < 1) {
                System.out.println("From 1 to 4");
                levelUpAsk2(chosenMember);
            } else {
                System.out.println("** Sorry..");
                sc.nextLine();

            }
        } catch (InputMismatchException e) {
            System.out.println("Numbers only");
            levelUpAsk2(chosenMember);
        }

        chosenMember.setHp(chosenMember.getMaxHp());
        chosenMember.setMp(chosenMember.getMaxMp());

    }

    public static void levelUpAsk(Character chosenMember) {
        try {
            Scanner sc = new Scanner(System.in);
            int select = sc.nextInt();
            sc.nextLine();
            switch (select) {

                case 1:
                    chosenMember.setMaxHp(chosenMember.getMaxHp() + 20);
                    System.out.println("** HP has increased by 20.");
                    break;
                case 2:
                    chosenMember.setMaxMp(chosenMember.getMaxMp() + 10);
                    System.out.println("** MP has increased by 10.");
                    break;
                case 3:
                    chosenMember.setAtt(chosenMember.getAtt() + 5);
                    System.out.println("** ATT has incrased by 5.");
                    break;
                default:
                    System.out.println("From 1 to 3~");
                    levelUpAsk(chosenMember);
                    break;

            }
        } catch (InputMismatchException e) {
            System.out.println("Numbers only");
            levelUpAsk(chosenMember);
        }
    }

    public static void ku_prolog() {

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

    }

    public static void map_ku() {
        Path read = Paths.get("ku_intro.txt");
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
        }

        System.out.println("Going into the fight..");
        sc.nextLine();
        Map ku = new Ku();

    }

    public static void KuEnd() {

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

    }

    public static void turnAskCoin() {
        System.out.println("I'm flipping a coin to decide who attacks first.");
        System.out.println("Choose one.");
        System.out.println("1. Front");
        System.out.println("2. Back");
        int coin = rand.nextInt(2) + 1;
        input();
        select = sc.nextInt();
        sc.nextLine();
        if (select == coin) {
            if (coin == 1) {
                System.out.println("Coin: Front!\n");
            } else {
                System.out.println("Coin: Back!\n");
            }
            System.out.println("You got the first turn.");
        } else if (select > 2 || select <= 0) {
            System.out.println("Either 1 or 2");
            turnAskCoin();
        } else {
            if (coin == 1) {
                System.out.println("Coin: Front!\n");
            } else {
                System.out.println("Coin: Back!\n");
            }
            System.out.println("** Enemy gets the first turn.");
            sc.nextLine();
            blnTurn = true;
        }

    }

    public static void fightStatus(String name) {
        System.out.println("____________________FIGHT__________________________");
        System.out.println("___________________________________________________");
        System.out.println("                    **"+name+"**                            ");
        System.out.println("                                                           ");
        System.out.println(chosenMember[0] + "                                                         ");
        System.out.println("HP:" + chosenMember[0].getHp() + "/" + chosenMember[0].getMaxHp()
                + "                                                        ");
        System.out.println("MP:" + chosenMember[0].getMp() + "/" + chosenMember[0].getMaxMp()
                + "                                                       ");
        System.out.println("                              " + Map.enemy1);
        System.out.println("                               HP:" + Map.enemy1.getHp() + "/" + Map.enemy1.getMaxHp());
        System.out.println(chosenMember[1] + "                                                         ");
        System.out.println("HP:" + chosenMember[1].getHp() + "/" + chosenMember[1].getMaxHp()
                + "                                                           ");
        System.out.println("MP:" + chosenMember[1].getMp() + "/" + chosenMember[1].getMaxMp()
                + "                                                          ");
        System.out.println("                              " + Map.enemy2);
        System.out.println("                               HP:" + Map.enemy2.getHp() + "/" + Map.enemy2.getMaxHp());
        System.out.println(chosenMember[2] + "                                                         ");
        System.out.println("HP:" + chosenMember[2].getHp() + "/" + chosenMember[2].getMaxHp()
                + "                                                          ");
        System.out.println("MP:" + chosenMember[2].getMp() + "/" + chosenMember[2].getMaxMp()
                + "                                                        ");
        System.out.println("                              " + Map.enemy3);
        System.out.println("                               HP:" + Map.enemy3.getHp() + "/" + Map.enemy3.getMaxHp());
        System.out.println(chosenMember[3] + "                                                         ");
        System.out.println("HP:" + chosenMember[3].getHp() + "/" + chosenMember[3].getMaxHp()
                + "                                                        ");
        System.out.println("MP:" + chosenMember[3].getMp() + "/" + chosenMember[3].getMaxMp()
                + "                                                          ");
        System.out.println("                                                                                      ");
        System.out.println(chosenMember[4] + "                                                         ");
        System.out.println("HP:" + chosenMember[4].getHp() + "/" + chosenMember[4].getMaxHp()
                + "                                                        ");
        System.out.println("MP:" + chosenMember[4].getMp() + "/" + chosenMember[4].getMaxMp()
                + "                                                         ");
        System.out.println("                                                                                      ");
        System.out.println("___________________________________________________\n");
    }

    public static void ourTurnReady(Character chosenMember) {

        if (chosenMember.getHp() != 0) {
            turn(chosenMember);
        }
        System.out.println("\n\n\n\n\n");


    }

    public static void turn(Character chosenMember) {

        System.out.println("\n** It's " + chosenMember + "'s turn:");
        System.out.println("_____________________________________________________________");
        System.out.println(" (1) Attack  (2) Block  (3) Skill  (4) Portion  (5) Escape");

        turn_ask(chosenMember);

    }

    public static void turn_ask(Character chosenMember) {
        input();
        select = sc.nextInt();
        sc.nextLine();
        switch (select) {
            case 1:
                if (blnLa) {
                    chosenMember.attack(boss1);
                } else if (blnVegas) {
                    chosenMember.attack(boss2);
                }

                else {
                    attackChoose(chosenMember);
                }
                break;
            case 2:
                block(chosenMember);
                break;
            case 3:
                if (chosenMember.getMp() < chosenMember.getMpAdj()) {
                    System.out.println("You don't have enough MP to use Skill.");
                    turn_ask(chosenMember);
                } else {
                    if (blnLa) {
                        chosenMember.useSkill(boss1);
                    } else if (blnVegas) {
                        chosenMember.useSkill(boss2);
                    } else {
                        skillChoose(chosenMember);
                    }
                }

                break;
            case 4:
                if (Character.hpNum <= 0 && Character.mpNum <= 0) {
                    System.out.println("You don't have a portion to drink");
                    turn_ask(chosenMember);

                } else {
                    System.out.println("Which portion would you like to drink?");
                    System.out.println("1.(80% heal) HP portion:" + Character.hpNum);
                    System.out.println("2.(80% heal) MP portion:" + Character.mpNum);
                    System.out.println("3. Cancel");
                    portion(chosenMember);
                }
                break;
            case 5:
                escape(chosenMember);
                break;
            default:
                System.out.println("From 1 to 5");
                turn_ask(chosenMember);
                break;

        }

    }

    public static void block(Character chosenMember) {
        System.out.println("** 20% chance of failing to block");
        System.out.println("1. Block");
        System.out.println("2. Go back");
        input();
        int select = sc.nextInt();
        if (select == 1) {
            chosenMember.setBlnBlock(true);
            System.out.println("\n** " + chosenMember + " takes Guard position.");
        } else if (select == 2) {
            turn(chosenMember);
        } else {
            block(chosenMember);
        }

    }

    public static void portion(Character chosenMember) {
        boolean wrongAnswer;
        do {
            Scanner sc = new Scanner(System.in);
            wrongAnswer = false;
            while (!sc.hasNextInt()) {
                sc.next();
            }
            int select = sc.nextInt();
            sc.nextLine();
            switch (select) {
                case 1:
                    if (Character.hpNum <= 0) {
                        System.out.println("You don't have HP portion.");
                        portion(chosenMember);

                    } else {
                        whichCharacter1();
                        chooseHHCha1(chosenMember);
                    }
                    break;
                case 2:
                    if (Character.mpNum <= 0) {
                        System.out.println("You don't have MP portion.");
                        portion(chosenMember);

                    } else {
                        whichCharacter2();
                        chooseMMCha1(chosenMember);
                    }
                    break;
                case 3:
                    turn(chosenMember);
                    break;
                default:
                    System.out.println("From 1 to 3");
                    wrongAnswer = true;
                    break;
            }

        } while (wrongAnswer);
    }



    public static void chooseHHCha1(Character member) {
        boolean wrongAnswer;
        do {
            wrongAnswer = false;
            Scanner sc = new Scanner(System.in);
            while (!sc.hasNextInt()) {
                sc.next();
            }
            int select = sc.nextInt();
            sc.nextLine();
            switch (select) {
                case 1:
                    chooseHHCha(chosenMember[0], member);
                    break;
                case 2:
                    chooseHHCha(chosenMember[1], member);
                    break;
                case 3:
                    chooseHHCha(chosenMember[2], member);
                    break;
                case 4:
                    chooseHHCha(chosenMember[3], member);
                    break;
                case 5:
                    chooseHHCha(chosenMember[4], member);
                    break;
                case 6:
                    turn(member);
                    break;

                default:
                    System.out.println("From 1 to 6");
                    wrongAnswer = true;
                    break;

            }
        } while (wrongAnswer);

    }

    public static void chooseMMCha1(Character member) {
        boolean wrongAnswer;
        do {
            wrongAnswer = false;
            Scanner sc = new Scanner(System.in);
            while (!sc.hasNextInt()) {
                sc.next();
            }
            int select = sc.nextInt();
            sc.nextLine();
            switch (select) {
                case 1:
                    chooseMMCha(chosenMember[0], member);
                    break;
                case 2:
                    chooseMMCha(chosenMember[1], member);
                    break;
                case 3:
                    chooseMMCha(chosenMember[2], member);
                    break;
                case 4:
                    chooseMMCha(chosenMember[3], member);
                    break;
                case 5:
                    chooseMMCha(chosenMember[4], member);
                    break;
                case 6:
                    turn(member);
                    break;

                default:
                    System.out.println("From 1 to 6");
                    wrongAnswer = true;
                    break;

            }
        } while (wrongAnswer);
    }

    public static void chooseHHCha(Character chosenMember, Character member) {
        if (chosenMember.getHp() == chosenMember.getMaxHp()) {
            System.out.println(chosenMember + "'s HP is full. Choose different character");
            chooseHHCha1(member);
            return;
        }
        int a = (int) (chosenMember.getMaxHp() * .8);
        int b;
        if (chosenMember.getHp() != 0) {
            if (chosenMember.getHp() + a > chosenMember.getMaxHp()) {
                b = chosenMember.getMaxHp() - chosenMember.getHp();
                chosenMember.setHp(chosenMember.getMaxHp());
                System.out.println("** " + chosenMember + "'s HP is healed by " + b);
            } else {
                chosenMember.setHp(chosenMember.getHp() + a);
                System.out.println("** " + chosenMember + "'s HP is healed by " + a);

            }
            Character.hpNum--;
        } else {
            System.out.println("He is already knocked down. Choose different character");
            chooseHHCha(chosenMember, member);
        }
        sc.nextLine();

    }

    public static void chooseMMCha(Character chosenMember, Character member) {
        if (chosenMember.getMp() == chosenMember.getMaxMp()) {
            System.out.println(chosenMember + "'s MP is full. Choose different character");
            chooseMMCha1(member);
            return;
        }
        int a = (int) (chosenMember.getMaxMp() * .8);
        int b;
        if (chosenMember.getMp() != 0) {
            if (chosenMember.getMp() + a > chosenMember.getMaxMp()) {
                b = chosenMember.getMaxMp() - chosenMember.getMp();
                chosenMember.setMp(chosenMember.getMaxMp());
                System.out.println("** " + chosenMember + "'s MP is healed by " + b);
            } else {
                chosenMember.setMp(chosenMember.getMp() + a);
                System.out.println("** " + chosenMember + "'s MP is healed by " + a);

            }
            Character.mpNum--;
        } else {
            System.out.println("He is already knocked down. Choose different character");
            chooseMMCha(chosenMember, member);
        }
        sc.nextLine();
    }

    public static void skillChoose(Character chosenMember) {
        System.out.println("\nto Who? (x2.0 damage, MP "+chosenMember.getMpAdj()+ " uses, 10% of Critical x2.5)");
        attackComment(chosenMember);
        skillChar(chosenMember);
    }

    public static void escape(Character chosenMember) {

        System.out.println("Do you really want to run away ? [30% of failing to escape]");
        System.out.println("1. Yes");
        System.out.println("2. No");
        escapeAsk(chosenMember);

    }

    public static void escapeAsk(Character chosenMember) {
        input();
        select = sc.nextInt();
        sc.nextLine();
        if (select == 1) {
            int percent = rand.nextInt(10) + 1;
            if (percent < 8) {
                System.out.println("** You have successfully escaped.");
                blnEscape = true;
                sc.nextLine();
                System.out.println("** Going back to Agit.");
                sc.nextLine();

                agit();
            } else {
                System.out.println("\n\n\n\n** Failed to escape hehehe");
                nextTurn(chosenMember);
            }
        } else if (select == 2) {

            turn(chosenMember);
        } else {
            System.out.println("Either 1 or 2");
            escapeAsk(chosenMember);
        }

    }

    public static void nextTurn(Character chosenMember) {
        System.out.println("** Hit enter for the next turn");
        sc.nextLine();
        if(blnLa) {
            bossFightStatus(boss1, boss1Count, 10, "LA bar");

        }else if(blnVegas) {
            bossFightStatus(boss2, boss2Count, 7, "Beach Club");

        }else {

        }
        Character next = null;

        for (int i = 0; i < Main.chosenMember.length - 1; i++) {
            if (Main.chosenMember[i] == chosenMember) {
                next = Main.chosenMember[i + 1];
                if (next.getHp() == 0) {
                    nextTurn(next);
                } else {
                    turn(next);
                }
            }
        }

    }

    public static void attackChoose(Character chosenMember) {
        System.out.println("\nWho?");
        attackComment(chosenMember);
        attackChar(chosenMember);
    }

    public static void skillChar(Character chosenMember) {
        boolean wrongAnswer;
        do {
            wrongAnswer = false;
            Scanner sc = new Scanner(System.in);
            while (!sc.hasNextInt()) {
                sc.next();
            }
            int select = sc.nextInt();
            sc.nextLine();
            switch (select) {

                case 1:
                    if (Map.enemy1.getHp() != 0) {
                        chosenMember.useSkill(Map.enemy1);
                    } else {
                        System.out.println("Knocked down");
                        skillChar(chosenMember);
                    }

                    break;
                case 2:
                    if (Map.enemy2.getHp() != 0) {
                        chosenMember.useSkill(Map.enemy2);
                    } else {
                        System.out.println("Knocked down");
                        skillChar(chosenMember);
                    }

                    break;
                case 3:
                    if (Map.enemy3.getHp() != 0) {
                        chosenMember.useSkill(Map.enemy3);
                    } else {
                        System.out.println("Knocked down");
                        skillChar(chosenMember);
                    }
                    break;
                case 4:
                    turn(chosenMember);
                    break;
                default:
                    System.out.println("From 1 to 3");
                    wrongAnswer = true;
                    break;

            }
        } while (wrongAnswer);

    }

    public static void attackChar(Character chosenMember) {
        boolean wrongAnswer;
        do {
            wrongAnswer = false;
            Scanner sc = new Scanner(System.in);
            while (!sc.hasNextInt()) {
                sc.next();
            }
            int select = sc.nextInt();
            sc.nextLine();
            switch (select) {

                case 1:
                    if (Map.enemy1.getHp() != 0) {
                        chosenMember.attack(Map.enemy1);
                    } else {
                        System.out.println("Knocked down");
                        attackChar(chosenMember);

                    }

                    break;
                case 2:
                    if (Map.enemy2.getHp() != 0) {
                        chosenMember.attack(Map.enemy2);
                    } else {
                        System.out.println("Knocked down");
                        attackChar(chosenMember);
                    }

                    break;
                case 3:
                    if (Map.enemy3.getHp() != 0) {
                        chosenMember.attack(Map.enemy3);
                    } else {
                        System.out.println("Knocked down");
                        attackChar(chosenMember);
                    }
                    break;

                case 4:
                    turn(chosenMember);
                    break;
                default:
                    System.out.println("From 1 to 4");
                    wrongAnswer = true;
                    break;

            }
        } while (wrongAnswer);
    }

    public static void attackComment(Character chosenMember) {
        if (Map.enemy1.getHp() != 0) {
            System.out.println("1. " + Map.enemy1);

        } else {
            System.out.println("1. Knocked down");

        }

        if (Map.enemy2.getHp() != 0) {
            System.out.println("2. " + Map.enemy2);

        } else {
            System.out.println("2. Knocked down");
        }
        if (Map.enemy3.getHp() != 0) {
            System.out.println("3. " + Map.enemy3);

        } else {
            System.out.println("3. Knocked down");

        }
        System.out.println("4. Go back");

    }

    public static void enemyTurn(Character enemy) {
        if (enemy.getHp() != 0) {
            System.out.println("** It's " + enemy + "'s turn.");
            sc.nextLine();
            enemyAttack(enemy);
            System.out.println("\n\n\n\n");

        }
    }

    public static void enemyAttack(Character enemy) {
        Random rand = new Random();
        int enemySelect = rand.nextInt(5) + 1;

        switch (enemySelect) {

            case 1:
                if (chosenMember[0].getHp() != 0) {
                    enemy.attack(chosenMember[0]);
                } else {
                    enemyAttack(enemy);
                }
                break;

            case 2:
                if (chosenMember[1].getHp() != 0) {
                    enemy.attack(chosenMember[1]);
                } else {
                    enemyAttack(enemy);
                }
                break;
            case 3:
                if (chosenMember[2].getHp() != 0) {
                    enemy.attack(chosenMember[2]);
                } else {
                    enemyAttack(enemy);
                }
                break;
            case 4:
                if (chosenMember[3].getHp() != 0) {
                    enemy.attack(chosenMember[3]);
                } else {
                    enemyAttack(enemy);
                }
                break;
            case 5:
                if (chosenMember[4].getHp() != 0) {
                    enemy.attack(chosenMember[4]);
                } else {
                    enemyAttack(enemy);
                }
                break;

        }

    }

    public static void vacation() {

        System.out.println("_____________________________________");
        System.out.println("|            Vacation               ");
        System.out.println("|Money: $" + Character.money + "                          ");
        System.out.println("|                                   ");
        System.out.println("|     (1) LA     (2) Las Vegas      ");
        System.out.println("|___________________________________");

        System.out.println("\nWhich city would you like to travel?\n");
        System.out.println("1. LA        ($2500)");
        System.out.println("2. Las Vegas ($7000)");
        System.out.println("3. Go back");

        vacationSwitch();

    }

    public static void vacationSwitch() {
        input();
        select = sc.nextInt();
        sc.nextLine();
        switch (select) {

            case 1:
                if (!blnLaBoss) {
                    vacationSwitch_1();
                    la();
                } else {
                    System.out.println("You don't want to go there because of the owner");
                    vacationSwitch();
                }

                break;
            case 2:
                vacationSwitch_2();
                vegas();
                break;
            case 3:
                agit();
                break;

            default:
                System.out.println("From 1 to 3");
                vacationSwitch();
                break;

        }

    }

    public static void vacationSwitch_1() {
        if (Character.money < 2500) {
            System.out.println("**You don't have enough money..");
            vacationSwitch();

        } else {

            System.out.println("-$" + 2500);
            Character.money = Character.money - 2500;
            sc.nextLine();

        }
    }

    public static void vacationSwitch_2() {
        if (Character.money < 7000) {
            System.out.println("**You don't have enough money..");
            vacationSwitch();

        } else {
            if (!blnLaBoss) {
                System.out.println("** You have a feeling that you don't want to go to Las Vegas right now.");
                vacationSwitch();
            } else {
                System.out.println("-$" + 7000);
                Character.money = Character.money - 7000;
                sc.nextLine();
            }
        }
    }

    public static void laTalk() {

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

    }

    public static void ourTurnReadyBoss(Character chosenMember) {

        if (chosenMember.getHp() != 0) {
            turn(chosenMember);
        }
        System.out.println("\n\n\n\n\n");

    }

    public static void la() {
        blnLa = true;
        if (!blnLaTalk) {
            laTalk();
            blnLaTalk = true;
        }
        bossFightStatus(boss1, boss1Count, 10, "LA bar");
        turnAskCoin();
        while (!blnLaBossKilled || !blnDungeon) {

            if (blnTurn) {
                System.out.println("** Enemy turn has started!");
                sc.nextLine();
                enemyTurnBoss(boss1, boss1Count, 10, "LA bar");
                if (blnLaBossKilled || blnDungeon) {
                    break;
                }

                System.out.println("** Enter to end the Enemy's turn");
                sc.nextLine();
                blnTurn = false;
            }

            System.out.println("** Your turn has started!");

            ourTurnReadyBoss(chosenMember[0]);
            if(!blnDungeon&&chosenMember[0].getHp()!=0) {
                bossFightStatus(boss1, boss1Count, 10, "LA bar");			}
            if (blnLaBossKilled) {
                break;
            }
            ourTurnReadyBoss(chosenMember[1]);
            if(!blnDungeon&&chosenMember[1].getHp()!=0) {
                bossFightStatus(boss1, boss1Count, 10, "LA bar");			}
            if (blnLaBossKilled) {
                break;
            }
            ourTurnReadyBoss(chosenMember[2]);
            if(!blnDungeon&&chosenMember[2].getHp()!=0) {
                bossFightStatus(boss1, boss1Count, 10, "LA bar");			}
            if (blnLaBossKilled) {
                break;
            }
            ourTurnReadyBoss(chosenMember[3]);
            if(!blnDungeon&&chosenMember[3].getHp()!=0) {
                bossFightStatus(boss1, boss1Count, 10, "LA bar");			}
            if (blnLaBossKilled) {
                break;
            }
            ourTurnReadyBoss(chosenMember[4]);
            if(!blnDungeon&&chosenMember[4].getHp()!=0) {
                bossFightStatus(boss1, boss1Count, 10, "LA bar");			}
            if (blnLaBossKilled) {
                break;
            }
            System.out.println("** Enter to end your turn");
            sc.nextLine();

            System.out.println("** Enemy turn has started!");
            sc.nextLine();
            enemyTurnBoss(boss1, boss1Count, 10, "LA bar");

            if (blnLaBossKilled || blnDungeon) {
                break;
            }

            System.out.println("** Enter to end the Enemy's turn");
            sc.nextLine();
        }
        if (deadTeamCount == 5) {
            System.out.println("** Shamed to lose");
            sc.nextLine();
            System.out.println("** You don't lose anything in a fight with a Boss.");
            sc.nextLine();
            System.out.println("** And I'm so nice enough to give you some of the travel fee back..");
            sc.nextLine();
            Character.money= Character.money+2000;
            System.out.println("+$2000");
            sc.nextLine();
            System.out.println("..You're carried to Agit..\n\n\n\n");
            sc.nextLine();


        } else {
            System.out.println("** You beat the Owner!");
            sc.nextLine();
            int expTotal = 5000;
            System.out.println("** Each member has earned " + expTotal + "exp !\n");
            sc.nextLine();
            for (Character a : chosenMember) {
                a.setExp(a.getExp() + expTotal);
                System.out.println(a + ": " + a.getExp() + "/" + a.getLvExp());
            }

            for (Character a : chosenMember) {
                exp(a);
            }

            sc.nextLine();
            blnLaBoss = true;
            laEnd();
            for (Character a : chosenMember) {
                a.setHp(a.getMaxHp());
                a.setMp(a.getMaxMp());

            }
            System.out.println("** Going back to Agit..");
            sc.nextLine();
            System.out.println("** Took a goood rest. Everyone's HP and MP is full now");
            sc.nextLine();
            System.out.println("** Now you can enter Tigs");
            sc.nextLine();
            blnTigs = true;


        }
        blnDungeon = false;
        deadEnemyCount = 0;
        deadTeamCount = 0;

        for (Character a : Main.chosenMember) {
            if (a.getHp() == 0) {
                a.setHp(1);
            }
        }
        boss1.setHp(boss1.getMaxHp());
        blnLa = false;
        agit();

    }

    public static void laEnd() {

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

    }

    public static void enemyTurnBoss(Character enemy, int count, int slash, String name) {
        if (enemy.getHp() != 0) {
            System.out.println("** It's " + enemy + "'s turn.");
            sc.nextLine();
            enemyAttack(enemy);
            System.out.println("\n\n\n\n");
            bossFightStatus(enemy, count, slash, name);
        }
    }

    public static void bossFightStatus(Character enemy, int count, int countSlash, String name) {

        System.out.println("____________________FIGHT__________________________");
        System.out.println("___________________________________________________");
        System.out.println("                 **" + name + "**                            ");
        System.out.println("                                                           ");
        System.out.println(chosenMember[0] + "                                                         ");
        System.out.println("HP:" + chosenMember[0].getHp() + "/" + chosenMember[0].getMaxHp()
                + "                                                           ");
        System.out.println("MP:" + chosenMember[0].getMp() + "/" + chosenMember[0].getMaxMp()
                + "                                                            ");
        System.out.println("                              ");
        System.out.println(chosenMember[1] + "                                                         ");
        System.out.println("HP:" + chosenMember[1].getHp() + "/" + chosenMember[1].getMaxHp()
                + "                                                           ");
        System.out.println("MP:" + chosenMember[1].getMp() + "/" + chosenMember[1].getMaxMp()
                + "                      Count:" + count + "/" + countSlash);
        System.out.println("                                " + enemy);
        System.out.println("                               HP:" + enemy.getHp() + "/" + enemy.getMaxHp());
        System.out.println(chosenMember[2] + "                                                         ");
        System.out.println("HP:" + chosenMember[2].getHp() + "/" + chosenMember[2].getMaxHp()
                + "                                                           ");
        System.out.println("MP:" + chosenMember[2].getMp() + "/" + chosenMember[2].getMaxMp()
                + "                                                           ");
        System.out.println("                              ");
        System.out.println(chosenMember[3] + "                                                         ");
        System.out.println("HP:" + chosenMember[3].getHp() + "/" + chosenMember[3].getMaxHp()
                + "                                                            ");
        System.out.println("MP:" + chosenMember[3].getMp() + "/" + chosenMember[3].getMaxMp()
                + "                                                            ");
        System.out.println("                                                                                      ");
        System.out.println(chosenMember[4] + "                                                         ");
        System.out.println("HP:" + chosenMember[4].getHp() + "/" + chosenMember[4].getMaxHp()
                + "                                                            ");
        System.out.println("MP:" + chosenMember[4].getMp() + "/" + chosenMember[4].getMaxMp()
                + "                                                           ");
        System.out.println("                                                                                      ");
        System.out.println("___________________________________________________\n");
    }

    public static void vegasTalk() {
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
    }
    public static void fun_ask(Character bose) {
        input();
        int select = sc.nextInt();
        sc.nextLine();
        if(select == 1) {
            System.out.println("** Alright, I will lower his HP for you");
            sc.nextLine();
            System.out.println("** Good luck!");
            sc.nextLine();
            bose.setHp(9000);
            bose.setMaxHp(9000);

        }else if (select ==2) {
            System.out.println("** Are you sure? I might do something for you that you should like :)");
            sc.nextLine();
            System.out.println("1. Okay, I'm super scared.");
            System.out.println("2. No, I don't need your help");

            fun_ask1(bose);
        }else {
            System.out.println("Either 1 or 2");
            fun_ask(bose);

        }
    }
    public static void fun_ask1(Character bose) {
        input();
        int select = sc.nextInt();
        sc.nextLine();
        if(select == 1) {
            System.out.println("** Haha, I will lower his HP for you");
            sc.nextLine();
            System.out.println("** Good luck!");
            sc.nextLine();
            bose.setHp(9000);
            bose.setMaxHp(9000);

        }else if (select ==2) {
            System.out.println("** Alright mang, good luck!");
        }else {
            System.out.println("Either 1 or 2");
            fun_ask1(bose);

        }
    }
    public static void vegas() {
        blnVegas = true;
        if (!blnVegasTalk) {
            vegasTalk();
            blnVegasTalk = true;
        }


        bossFightStatus(boss2, boss2Count, 7, "Beach Club");
        if(!blnVegasFun) {
            System.out.println("** Are you scared? hahahaha");
            System.out.println("1. Yes...");
            System.out.println("2. f No!");
            fun_ask(boss2);
            blnVegasFun = true;
        }
        bossFightStatus(boss2, boss2Count, 7, "Beach Club");
        turnAskCoin();
        while (!blnVegasBossKilled || !blnDungeon) {

            if (blnTurn) {
                System.out.println("** Enemy turn has started!");
                sc.nextLine();
                enemyTurnBoss(boss2, boss2Count, 7, "Beach Club");
                if (blnVegasBossKilled || blnDungeon) {
                    break;
                }

                System.out.println("** Enter to end the Enemy's turn");
                sc.nextLine();
                blnTurn = false;
            }

            System.out.println("** Your turn has started!");

            ourTurnReadyBoss1(chosenMember[0]);
            if(!blnDungeon&&chosenMember[0].getHp()!=0) {
                bossFightStatus(boss2, boss2Count, 7, "Beach Club");
            }
            if (blnVegasBossKilled) {
                break;
            }
            ourTurnReadyBoss1(chosenMember[1]);
            if(!blnDungeon&&chosenMember[1].getHp()!=0) {
                bossFightStatus(boss2, boss2Count, 7, "Beach Club");
            }
            if (blnVegasBossKilled) {
                break;
            }
            ourTurnReadyBoss1(chosenMember[2]);
            if(!blnDungeon&&chosenMember[2].getHp()!=0) {
                bossFightStatus(boss2, boss2Count, 7, "Beach Club");
            }
            if (blnVegasBossKilled) {
                break;
            }
            ourTurnReadyBoss1(chosenMember[3]);
            if(!blnDungeon&&chosenMember[3].getHp()!=0) {
                bossFightStatus(boss2, boss2Count, 7, "Beach Club");
            }
            if (blnVegasBossKilled) {
                break;
            }
            ourTurnReadyBoss1(chosenMember[4]);
            if(!blnDungeon&&chosenMember[4].getHp()!=0) {
                bossFightStatus(boss2, boss2Count, 7, "Beach Club");
            }
            if (blnVegasBossKilled) {
                break;
            }
            System.out.println("** Enter to end your turn");
            sc.nextLine();

            System.out.println("** Enemy turn has started!");
            sc.nextLine();
            enemyTurnBoss(boss2, boss2Count, 7, "Beach Club");

            if (blnVegasBossKilled || blnDungeon) {
                break;
            }

            System.out.println("** Enter to end the Enemy's turn");
            sc.nextLine();
        }
        if (deadTeamCount == 5) {
            System.out.println("** Shamed to lose");
            sc.nextLine();
            System.out.println("** You don't lose anything in a fight with a Boss.");
            sc.nextLine();
            System.out.println("** And I'm so nice enough to give you some of the travel fee back");
            sc.nextLine();
            Character.money= Character.money+4000;
            System.out.println("+$4000");
            sc.nextLine();
            System.out.println("..You're carried to Agit..\n\n\n\n");
            sc.nextLine();

        } else {
            System.out.println("** You beat the Pervert!");
            sc.nextLine();

            vegasEnd();
            for (Character a : chosenMember) {
                a.setHp(a.getMaxHp());
                a.setMp(a.getMaxMp());

            }
            ending();
            System.exit(0);
            return;
        }
        blnDungeon = false;
        deadEnemyCount = 0;
        deadTeamCount = 0;

        for (Character a : Main.chosenMember) {
            if (a.getHp() == 0) {
                a.setHp(1);
            }
        }
        blnVegasFun = false;
        blnVegas = false;
        boss2.setHp(boss2.getMaxHp());
        agit();


    }

    public static void ourTurnReadyBoss1(Character chosenMember) {

        if (chosenMember.getHp() != 0) {
            turn(chosenMember);
        }
        System.out.println("\n\n\n\n\n");
        bossFightStatus(boss2, boss2Count, 7, "Beach Club");

    }

    public static void vegasEnd() {
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
    }

    public static void ending() {
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
    }

}