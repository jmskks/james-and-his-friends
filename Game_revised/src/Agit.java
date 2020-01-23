import java.util.Scanner;

public class Agit {

    static Scanner sc = new Scanner(System.in);
    static Store store;
    static CharacterStatus characterStatus;
    static Rest rest;
    static Ku ku;
    static Fight fight;
    static Vacation vacation;
    static La la;
    static Vegas vegas;

    static void input() {
        while (!sc.hasNextInt()) {
            sc.next();
        }
    }


    Agit() {
        this.characterStatus = new CharacterStatus();
        this.store = new Store(200);
        this.rest = new Rest(0);
        this.ku = new Ku();
        this.fight = new Fight();
        this.vacation = new Vacation();
        this.la = new La();
        this.vegas = new Vegas();
    }

    void welcomePma() {
        System.out.println("***Welcome to Pma***");
        sc.nextLine();
        System.out.println("**This is your Agit now");
        sc.nextLine();
    }

    void image_agit() {

        System.out.println("_____________________________________________________________________");
        System.out.println("|                           ***Pma***                                ");
        System.out.println("|                                                Portions            ");
        System.out.println("|     Money: $" + Board.board.getMoney() + "                              HP:" + Board.board.getPotion_hp()
                + " MP:" + Board.board.getPotion_mp() + "                                                        ");
        System.out.println("|                                                                    ");
        System.out.println("|  (1) Character Status               (2) Store           ");
        System.out.println("|  (3) Ku (Dungeon 1)                 (4) Tigs  (Dungeon 2)                       ");
        System.out.println("|  (5) Rest                           (6) Vacation                   ");
        System.out.println("|  (7) Save Game                                                          ");
        System.out.println("|____________________________________________________________________");

        agit_ask_1();
    }

    void agit_ask_1() {//agit_ask_1


        System.out.println("\n**What do you want to do?**\n");
        input();
        int select = sc.nextInt();
        sc.nextLine();
        switch (select) {

            case 1:
                characterStatus.status();
                image_agit();
                break;
            case 2:
                store.store_intro();
                image_agit();
                break;
            case 3:
                ku.ku();
                fight.fight("Ku");
                image_agit();
                break;
            case 4:
                if (!Board.board.isBln_tigs_entry()) {
                    System.out.println("**Cannot enter");
                    agit_ask_1();

                } else {
                    /*  tigs();*/
                }
                break;
            case 5:
                rest.rest();
                image_agit();
                break;
            case 6:
                vacation.vacation();
                image_agit();
                break;
            case 7:


                /*   save();*/

                break;

            default:
                System.out.println("From 1 to 7");
                agit_ask_1();
        }


    }


    class Store {

        private boolean bln_store;


        private boolean bln_store_tigs;
        private int price;

        public boolean isBln_store_tigs() {
            return bln_store_tigs;
        }

        public void setBln_store_tigs(boolean bln_store_tigs) {
            this.bln_store_tigs = bln_store_tigs;
        }

        public boolean isBln_store() {
            return bln_store;
        }

        public void setBln_store(boolean bln_store) {
            this.bln_store = bln_store;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        Store(int price) {

            this.setBln_store(false);
            this.price = price;
            this.setBln_store_tigs(false);
        }


        void store_intro() { //store_intro
            if (!this.isBln_store()) {
                this.setBln_store(true);
                store();

            } else {
                in_the_store();
            }
        }

        void store() {  //store
            System.out.println("**Welcome to our Store**");
            sc.nextLine();
            System.out.println("** You can buy whatever you need, whatever you want in our store.");
            sc.nextLine();
            System.out.println("Would you like to enter?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            input();
            int select = sc.nextInt();
            sc.nextLine();
            if (select == 1) {
                in_the_store();
            } else if (select == 2) {

            } else {
                System.out.println("Either 1 or 2\n\n");
                store();
            }
        }

        void in_the_store() {

            if (Board.board.isBln_tigs_entry()) {
                this.setPrice(700);
                if (!Agit.store.isBln_store_tigs()) {
                    System.out.println("Man.. the inflation of economy caused all the price to go up..");
                    sc.nextLine();
                    System.out.println(
                            "You make a lot more money now.. so I'm sure you would understand us raising the price of potions..");
                    sc.nextLine();
                    Agit.store.setBln_store_tigs(true);
                }
            }
            System.out.println("**Welcome to our Store**\n");
            System.out.println("\n..Unfortunately these are all we got....");
            System.out.println("1. HP potion [$" + price + "]");
            System.out.println("2. MP potion [$" + price + "]");
            System.out.println("3. Exit");

            input();
            int select = sc.nextInt();
            sc.nextLine();
            switch (select) {
                case 1:

                    buy_potion("hp"); //buy_potion

                    break;
                case 2:
                    buy_potion("mp");

                    break;
                case 3:
                    System.out.println("..Going back to Agit..");
                    sc.nextLine();

                    break;
                default:
                    System.out.println("From 1 to 3\n");
                    in_the_store();
                    break;
            }

        }

        void buy_potion(String potion) {

            System.out.println("How many would you like to buy?");
            for (int i = 1; i < 6; i++) {
                System.out.println(i);
            }

            System.out.println("6. Go back");

            input();
            int select = sc.nextInt();
            if (select > 0 && select < 6) {
                selectPortion(select, potion);

            } else if (select == 6) {
                in_the_store();
            } else {
                System.out.println("From 1 to 6\n\n");
                buy_potion(potion);
            }
        }

        void selectPortion(int select, String potion) {

            if (Board.board.isBln_tigs_entry()) {
                this.setPrice(700);
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
                if (Board.board.getMoney() < total) {
                    System.out.println("** You don't have enough money to purchase the potions");
                    buy_potion(potion);
                } else {
                    System.out.println("\n-$" + total);
                    System.out.println("** You've purchased " + select + " of potion");
                    Board.board.setMoney(Board.board.getMoney() - total);

                    if (potion.equals("hp")) {
                        Board.board.setPotion_hp(Board.board.getPotion_hp() + select);
                        System.out.println("** You now have " + Board.board.getPotion_hp() + " potions");
                    } else {
                        Board.board.setPotion_mp(Board.board.getPotion_mp() + select);
                        System.out.println("** You now have " + Board.board.getPotion_mp() + " potions");
                    }
                    System.out.println("** Now you have " + "$" + Board.board.getMoney());
                    sc.nextLine();
                    System.out.println("..Going back to Agit..");
                    sc.nextLine();
                    Board.map.image_agit();
                }
            } else if (select1 == 2) {
                buy_potion(potion);
            } else if (select1 == 3) {
                in_the_store();
            } else {
                System.out.println("From 1 to 3\n\n");
                selectPortion(select, potion);
            }

        }

    }


    class CharacterStatus {
        void status() {

            System.out.println("_____________________________________");
            System.out.println("|              CHARACTER              ");
            System.out.println("|                                    ");
            System.out.println("|  (1) " + Board.map_chosenMember.get(1).getName());
            System.out.println("|  (2) " + Board.map_chosenMember.get(2).getName());
            System.out.println("|  (3) " + Board.map_chosenMember.get(3).getName());
            System.out.println("|  (4) " + Board.map_chosenMember.get(4).getName());
            System.out.println("|  (5) " + Board.map_chosenMember.get(5).getName());
            System.out.println("|  (6) Drink a portion                                   ");
            System.out.println("|  (7) Go Back                                  ");
            System.out.println("|____________________________________");
            status_ask();

        }


        void status_ask() {
            System.out.println("**Choose a character that you would like to check on\n");
            input();
            int select = sc.nextInt();
            sc.nextLine();
            if (select > 0 && select < 6) {
                stats_character(Board.map_chosenMember.get(select));
                status();
            } else if (select == 6) {
                which_potion();
            } else if (select == 7) {

            } else {
                status_ask();
            }


        }

        void stats_character(Character member) {
            System.out.println(member.getName() + "'s stats");
            System.out.println("Lv:" + member.getLv());
            System.out.println("Exp :" + member.getExp() + "/" + member.getLvExp());
            System.out.println("HP :" + member.getHp() + "/" + member.getMaxHp());
            System.out.println("MP :" + member.getMp() + "/" + member.getMaxMp());
            System.out.println("ATT :" + member.getAtt());
            System.out.println("\n");

        }

        void which_potion() {
            System.out.println("Which potion?");
            System.out.println("1.(80% heal of current HP) HP portion:" + Board.board.getPotion_hp());
            System.out.println("2.(80% heal of current MP) MP portion:" + Board.board.getPotion_mp());
            System.out.println("3. Go back");

            input();
            int select = sc.nextInt();
            if (select == 1) {
                if (Board.board.getPotion_hp() == 0) {
                    System.out.println("You don't have any HP potion to drink.");
                    sc.nextLine();
                    which_potion();
                } else {
                    ask_which_character_hp();
                    sc.nextLine();

                }

            } else if (select == 2) {
                if (Board.board.getPotion_mp() == 0) {
                    System.out.println("You don't have any MP potion to drink.");
                    sc.nextLine();
                    which_potion();
                } else {
                    ask_which_character_mp();
                    sc.nextLine();

                }


            } else if (select == 3) {
                Board.map.image_agit();
            } else {
                System.out.println("From 1 to 3\n\n");
                which_potion();
            }


        }


        void ask_which_character_hp() {


            System.out.println("\nto Who?");

            for (int i = 1; i < 6; i++) {
                System.out.println(i + ". " + Board.map_chosenMember.get(i).getName() + "   HP:" + Board.map_chosenMember.get(i).getHp() + "/" + Board.map_chosenMember.get(i).getMaxHp());
            }

            System.out.println("6.  Go back");

            input();
            int select = sc.nextInt();
            sc.nextLine();

            if (select > 0 && select < 6) {

                heals_hp(Board.map_chosenMember.get(select));


            } else if (select == 6) {
                which_potion();
            } else {

                ask_which_character_hp();


            }


        }


        void ask_which_character_mp() {


            System.out.println("\nto Who?");


            for (int i = 1; i < 6; i++) {
                System.out.println(i + ". " + Board.map_chosenMember.get(i).getName() + "   MP:" + Board.map_chosenMember.get(i).getMp() + "/" + Board.map_chosenMember.get(i).getMaxMp());
            }


            System.out.println("6.  Go back");

            input();
            int select = sc.nextInt();
            sc.nextLine();

            if (select > 0 && select < 6) {

                heals_mp(Board.map_chosenMember.get(select));


            } else if (select == 6) {
                which_potion();
            } else {

                ask_which_character_mp();


            }


        }


        void heals_hp(Character member) {


            if (member.getHp() == member.getMaxHp()) {
                System.out.println(member.getName() + "'s HP is full. Choose different character");
                sc.nextLine();
                ask_which_character_hp();
                return;
            }
            int a = (int) (member.getMaxHp() * .8);
            int b;
            if (member.getHp() != 0) {
                if (member.getHp() + a > member.getMaxHp()) {
                    b = member.getMaxHp() - member.getHp();
                    member.setHp(member.getMaxHp());
                    System.out.println("** " + member.getName() + "'s HP is healed by " + b);
                } else {
                    member.setHp(member.getHp() + a);
                    System.out.println("** " + member.getName() + "'s HP is healed by " + a);

                }
                sc.nextLine();
                Board.board.setPotion_hp(Board.board.getPotion_hp() - 1);
            } else {
                System.out.println("He is already knocked down. Choose different character");
                ask_which_character_hp();
            }
            sc.nextLine();


        }

        void heals_mp(Character member) {


            if (member.getMp() == member.getMaxMp()) {
                System.out.println(member.getName() + "'s MP is full. Choose different character");
                sc.nextLine();
                ask_which_character_mp();
                return;
            }
            int a = (int) (member.getMaxMp() * .8);
            int b;
            if (member.getMp() != 0) {
                if (member.getMp() + a > member.getMaxMp()) {
                    b = member.getMaxMp() - member.getMp();
                    member.setMp(member.getMaxMp());
                    System.out.println("** " + member.getName() + "'s MP is healed by " + b);

                } else {
                    member.setMp(member.getMp() + a);
                    System.out.println("** " + member.getName() + "'s MP is healed by " + a);

                }
                sc.nextLine();
                Board.board.setPotion_mp(Board.board.getPotion_mp() - 1);
            } else {
                System.out.println("He is already knocked down. Choose different character");
                ask_which_character_mp();
            }
            sc.nextLine();
        }

    }


    class Rest {

        private boolean blnRest_tigs;
        private int price;

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public boolean isBlnRest_tigs() {
            return blnRest_tigs;
        }

        public void setBlnRest_tigs(boolean blnRest_tigs) {
            this.blnRest_tigs = blnRest_tigs;
        }

        Rest(int price) {
            this.price = price;
            this.setBlnRest_tigs(false);
        }


        void rest() {

            if (Board.board.isBln_tigs_entry()) {
                this.setPrice(1200);
                if (!blnRest_tigs) {
                    System.out.println("Man.. the inflation of economy caused all the price to go up..");
                    sc.nextLine();
                    System.out.println(
                            "You make a lot more money now.. so I'm sure you would understand us raising the price of staying here..");
                    sc.nextLine();
                    this.setBlnRest_tigs(true);
                }
            }
            System.out.println("Do you want to go to hotel and get some sleep? [$" + price + "] per night");
            System.out.println("(Everyone's HP and MP will be healed by 40% of their original HP and MP)");
            System.out.println("1. Yes");
            System.out.println("2. No");
            input();
            int select = sc.nextInt();
            sc.nextLine();
            if (select == 1) {
                if (Board.board.getMoney() < price) {
                    System.out.println("You don't have enough money to get rest.. what a miserable life..");
                    sc.nextLine();
                    Board.map.image_agit();
                } else {
                    System.out.println("-$" + price);
                    sc.nextLine();
                    Board.board.setMoney(Board.board.getMoney() - price);
                    for (int i = 1; i < 6; i++) {
                        rest_heals(Board.map_chosenMember.get(i));
                    }

                    for (int i = 1; i < 6; i++) {
                        characterStatus.stats_character(Board.map_chosenMember.get(i));
                    }

                    System.out.println("** Members are all healed by 40% of their HP and MP");
                    sc.nextLine();


                }
            } else if (select == 2) {

            } else {
                System.out.println("Either 1 or 2\n\n");
                rest();
            }
        }

        void rest_heals(Character member) {
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


    }

    class Vacation {


        private boolean bln_boss_1;
        private boolean bln_boss_2;

        public boolean isBln_boss_1() {
            return bln_boss_1;
        }

        public void setBln_boss_1(boolean bln_boss_1) {
            this.bln_boss_1 = bln_boss_1;
        }

        public boolean isBln_boss_2() {
            return bln_boss_2;
        }

        public void setBln_boss_2(boolean bln_boss_2) {
            this.bln_boss_2 = bln_boss_2;
        }

        Vacation() {
            this.setBln_boss_1(false);
            this.setBln_boss_2(false);

        }

        void vacation() {

            System.out.println("_____________________________________");
            System.out.println("|            Vacation               ");
            System.out.println("|  Money: $" + Board.board.getMoney() + "                          ");
            System.out.println("|                                   ");
            System.out.println("|     (1) LA     (2) Las Vegas      ");
            System.out.println("|___________________________________");

            System.out.println("\nWhich city would you like to travel?\n");
            System.out.println("1. LA        ($5000)");
            System.out.println("2. Las Vegas ($12000)");
            System.out.println("3. Go back");
            vacationSwitch();

        }

        void vacationSwitch() {
            input();
            int select = sc.nextInt();
            sc.nextLine();
            switch (select) {

                case 1:
                    if (!this.isBln_boss_1()) {
                        vacationSwitch_1();

                    } else {
                        System.out.println("You don't feel you want to go there because of the owner you fought against");
                        vacationSwitch();
                    }

                    break;
                case 2:
                    vacationSwitch_2();

                    break;
                case 3:
                    image_agit();
                    break;

                default:
                    System.out.println("From 1 to 3");
                    vacationSwitch();
                    break;

            }

        }

        void vacationSwitch_1() {

            if (Board.board.getMoney() < 5000) {
                System.out.println("**You don't have enough money..");
                sc.nextLine();
                vacation();

            } else if (Board.board.isBln_tigs_entry()) {
                System.out.println("You don't feel like visiting there again.");
                sc.nextLine();
            } else {


                System.out.println("-$" + 5000);
                Board.board.setMoney(Board.board.getMoney() - 5000);
                sc.nextLine();
                Agit.la.la();
                Agit.fight.fightBoss_la("LA Bar");

            }
        }

        void vacationSwitch_2() {
            if (Board.board.getMoney() < 12000) {
                System.out.println("**You don't have enough money..");
                sc.nextLine();
                vacation();

            } else {
                if (!Board.board.isBln_tigs_entry()) {
                    System.out.println("** You have a feeling that you don't want to go to Las Vegas right now.");
                    sc.nextLine();
                    vacation();
                } else {
                    System.out.println("-$" + 12000);
                    Board.board.setMoney(Board.board.getMoney() - 12000);
                    sc.nextLine();
                    Agit.vegas.vegas();
                    Agit.fight.fightBoss_vegas("Vegas");
                }
            }
        }

    }


}








