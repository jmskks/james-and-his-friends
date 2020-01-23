import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Fight {
    static Scanner sc = new Scanner(System.in);

    static void input() {
        while (!sc.hasNextInt()) {
            sc.next();
        }
    }


    public static void turn_enemy(Character enemy, int slash) {

        System.out.println("** It's " + enemy.getName() + "'s turn.");
        sc.nextLine();
        if (Agit.la.bln_la_on) {
            enemyAttack_la(enemy, slash);

        } else {

            enemyAttack_vegas(enemy, slash);
        }
        System.out.println("\n\n\n\n");


    }

    public static void enemyAttack_la(Character enemy, int slash) {
        Random rand = new Random();
        int enemySelect = rand.nextInt(5) + 1;
        Character target = Board.map_chosenMember.get(enemySelect);
        if (target.getHp() != 0) {
            if (Agit.la.boss_slash_count >= slash) {
                Agit.la.boss_slash_count -= 10;
                enemy.skill_attack_by_boss(target, enemy.getName());
            } else {
                Agit.la.boss_slash_count++;
                enemy.attack_selected(target);
            }

        } else {
            enemyAttack_la(enemy, slash);

        }


    }

    public static void enemyAttack_vegas(Character enemy, int slash) {
        Random rand = new Random();
        int enemySelect = rand.nextInt(5) + 1;
        Character target = Board.map_chosenMember.get(enemySelect);
        if (target.getHp() != 0) {
            if (Agit.vegas.boss_slash_count >= slash) {
                Agit.vegas.boss_slash_count -= 7;
                enemy.skill_attack_by_boss(target, enemy.getName());
            } else {
                Agit.vegas.boss_slash_count++;
                enemy.attack_selected(target);
            }

        } else {
            enemyAttack_vegas(enemy, slash);

        }


    }



/*

    public static void ourTurnReadyBoss(Character chosenMember) {

        if (chosenMember.getHp() != 0) {
            turn(chosenMember);
        }
        System.out.println("\n\n\n\n\n");

    }*/


    public static void fightBoss_la(String name) {

        if (Agit.la.bln_la_on && !Agit.la.bln_la_talk) {
            /*laTalk();*/
            Agit.la.bln_la_talk = true;
        }


        bossFightStatus(Dungeon.map_enemies.get(1), Agit.la.boss_slash_count, Dungeon.map_enemies.get(1).getCount_slash(), name);


        while (!Agit.la.isBln_fight_done()) {


            System.out.println("** Your turn has started!");
            sc.nextLine();

            for (int i = 1; i < 6; i++) {

                if ((Board.map_chosenMember.get(i).getHp() != 0) && !Agit.la.isBln_fight_done()) {
                    bossFightStatus(Dungeon.map_enemies.get(1), Agit.la.boss_slash_count, Dungeon.map_enemies.get(1).getCount_slash(), name);
                    Board.map_chosenMember.get(i).setBlnBlock(false);
                    turn_characters(Board.map_chosenMember.get(i));

                }


            }

            if (Agit.la.isBln_fight_done()) {
                break;
            }


            System.out.println("** Enter to end your turn");
            sc.nextLine();

            System.out.println("** Enemy turn has started!");
            sc.nextLine();


            if (Dungeon.map_enemies.get(1).getHp() != 0 && !Agit.la.isBln_fight_done()) {
                turn_enemy(Dungeon.map_enemies.get(1), Dungeon.map_enemies.get(1).getCount_slash());
                bossFightStatus(Dungeon.map_enemies.get(1), Agit.la.boss_slash_count, Dungeon.map_enemies.get(1).getCount_slash(), name);


            }
            if (Agit.la.isBln_fight_done()) {
                break;
            }

            if (!Agit.la.isBln_fight_done()) {
                System.out.println("** Enter to end the Enemy's turn");
                sc.nextLine();
            }


        }
        if (!Dungeon.bln_escaped) {

            if (Dungeon.player_died_count == 5) {
                System.out.println("** Shamed to lose");
                sc.nextLine();
                System.out.println("** You don't lose anything in a fight with a Boss.");
                sc.nextLine();
                System.out.println("** And I'm so nice enough to give you some of the travel fee back..");
                sc.nextLine();
                Board.board.setMoney(Board.board.getMoney() + 3000);
                System.out.println("+$3000");
                sc.nextLine();
                System.out.println("..You're carried to Agit..\n\n\n\n");
                sc.nextLine();

                for (int i = 1; i < 6; i++) {
                    if (Board.map_chosenMember.get(i).getHp() == 0) {
                        Board.map_chosenMember.get(i).setHp(1);
                    }

                }

            } else {
                System.out.println("** You beat the Owner!");
                sc.nextLine();


                int collect_money = Dungeon.map_enemies.get(1).getMoney();


                System.out.println("You have earned $ " + collect_money + ".");
                Board.board.setMoney(Board.board.getMoney() + collect_money);

                sc.nextLine();


                int expTotal = Dungeon.map_enemies.get(1).getExp();
                System.out.println("** Each member has earned " + expTotal + "exp !\n");
                sc.nextLine();
                for (int i = 1; i < 6; i++) {

                    Board.map_chosenMember.get(i).setExp(Board.map_chosenMember.get(i).getExp() + expTotal);
                    System.out.println(Board.map_chosenMember.get(i).getName() + ": " + Board.map_chosenMember.get(i).getExp() + "/" + Board.map_chosenMember.get(i).getLvExp());
                }

                for (int i = 1; i < 6; i++) {
                    exp(Board.map_chosenMember.get(i));
                }


                sc.nextLine();
                Board.board.setBln_tigs_entry(true);

                /* laEnd();*/
                for (int i = 1; i < 6; i++) {

                    Board.map_chosenMember.get(i).setHp(Board.map_chosenMember.get(i).getMaxHp());
                    Board.map_chosenMember.get(i).setMp(Board.map_chosenMember.get(i).getMaxMp());

                }
                System.out.println("** Going back to Agit..");
                sc.nextLine();
                System.out.println("** Took a goood rest. Everyone's HP and MP is full now");
                sc.nextLine();
                System.out.println("** Now you can enter Tigs");
                sc.nextLine();


            }
        }

        Dungeon.player_died_count = 0;
        Dungeon.enemy_died_count = 0;
        Agit.la.boss_slash_count = 0;
        Agit.la.setBln_fight_done(false);
        Agit.la.bln_la_on = false;
        Dungeon.bln_escaped = false;
        Dungeon.bln_boss_killed = false;


    }

    public static void fightBoss_vegas(String name) {


        if (Agit.vegas.bln_vegas_on && !Agit.vegas.bln_vegas_talk) {
            /*laTalk();*/
            Agit.vegas.bln_vegas_talk = true;
        }

        bossFightStatus(Dungeon.map_enemies.get(1), Agit.vegas.boss_slash_count, Dungeon.map_enemies.get(1).getCount_slash(), name);


        while (!Agit.vegas.isBln_fight_done()) {


            System.out.println("** Your turn has started!");
            sc.nextLine();

            for (int i = 1; i < 6; i++) {

                if ((Board.map_chosenMember.get(i).getHp() != 0) && !Agit.vegas.isBln_fight_done()) {
                    bossFightStatus(Dungeon.map_enemies.get(1), Agit.vegas.boss_slash_count, Dungeon.map_enemies.get(1).getCount_slash(), name);
                    Board.map_chosenMember.get(i).setBlnBlock(false);
                    turn_characters(Board.map_chosenMember.get(i));

                }


            }

            if (Agit.vegas.isBln_fight_done()) {
                break;
            }


            System.out.println("** Enter to end your turn");
            sc.nextLine();

            System.out.println("** Enemy turn has started!");
            sc.nextLine();


            if (Dungeon.map_enemies.get(1).getHp() != 0 && !Agit.vegas.isBln_fight_done()) {
                turn_enemy(Dungeon.map_enemies.get(1), Dungeon.map_enemies.get(1).getCount_slash());
                bossFightStatus(Dungeon.map_enemies.get(1), Agit.vegas.boss_slash_count, Dungeon.map_enemies.get(1).getCount_slash(), name);


            }
            if (Agit.vegas.isBln_fight_done()) {
                break;
            }

            if (!Agit.vegas.isBln_fight_done()) {
                System.out.println("** Enter to end the Enemy's turn");
                sc.nextLine();
            }


        }
        if (!Dungeon.bln_escaped) {

            if (Dungeon.player_died_count == 5) {
                System.out.println("** Shamed to lose");
                sc.nextLine();
                System.out.println("** You don't lose anything in a fight with a Boss.");
                sc.nextLine();
                System.out.println("** And I'm so nice enough to give you some of the travel fee back..");
                sc.nextLine();
                Board.board.setMoney(Board.board.getMoney() + 3000);
                System.out.println("+$3000");
                sc.nextLine();
                System.out.println("..You're carried to Agit..\n\n\n\n");
                sc.nextLine();

                for (int i = 1; i < 6; i++) {
                    if (Board.map_chosenMember.get(i).getHp() == 0) {
                        Board.map_chosenMember.get(i).setHp(1);
                    }

                }

            } else {
                System.out.println("** You beat the Owner!");
                sc.nextLine();


                int collect_money = Dungeon.map_enemies.get(1).getMoney();


                System.out.println("You have earned $ " + collect_money + ".");
                Board.board.setMoney(Board.board.getMoney() + collect_money);

                sc.nextLine();


                int expTotal = Dungeon.map_enemies.get(1).getExp();
                System.out.println("** Each member has earned " + expTotal + "exp !\n");
                sc.nextLine();
                for (int i = 1; i < 6; i++) {

                    Board.map_chosenMember.get(i).setExp(Board.map_chosenMember.get(i).getExp() + expTotal);
                    System.out.println(Board.map_chosenMember.get(i).getName() + ": " + Board.map_chosenMember.get(i).getExp() + "/" + Board.map_chosenMember.get(i).getLvExp());
                }

                for (int i = 1; i < 6; i++) {
                    exp(Board.map_chosenMember.get(i));
                }


                sc.nextLine();
                Board.board.setBln_tigs_entry(true);
                /* laEnd();*/
                for (int i = 1; i < 6; i++) {

                    Board.map_chosenMember.get(i).setHp(Board.map_chosenMember.get(i).getMaxHp());
                    Board.map_chosenMember.get(i).setMp(Board.map_chosenMember.get(i).getMaxMp());

                }
                System.out.println("** Going back to Agit..");
                sc.nextLine();
                System.out.println("** Took a goood rest. Everyone's HP and MP is full now");
                sc.nextLine();
                System.out.println("** Now you can enter Tigs");
                sc.nextLine();


            }
        }

        Dungeon.player_died_count = 0;
        Dungeon.enemy_died_count = 0;
        Agit.vegas.boss_slash_count = 0;
        Agit.vegas.setBln_fight_done(false);
        Agit.vegas.bln_vegas_on = false;
        Dungeon.bln_escaped = false;
        Dungeon.bln_boss_killed = false;


    }






    /*




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
*/


    public static void bossFightStatus(Character enemy, int count, int countSlash, String name) {

        System.out.println("____________________FIGHT__________________________");
        System.out.println("___________________________________________________");
        System.out.println("                 **" + name + "**                            ");
        System.out.println("                                                           ");
        System.out.println(Board.map_chosenMember.get(1).getName() + "                                                         ");
        System.out.println("HP:" + Board.map_chosenMember.get(1).getHp() + "/" + Board.map_chosenMember.get(1).getMaxHp()
                + "                                                           ");
        System.out.println("MP:" + Board.map_chosenMember.get(1).getMp() + "/" + Board.map_chosenMember.get(1).getMaxMp()
                + "                                                            ");
        System.out.println("                              ");
        System.out.println(Board.map_chosenMember.get(2).getName() + "                                                         ");
        System.out.println("HP:" + Board.map_chosenMember.get(2).getHp() + "/" + Board.map_chosenMember.get(2).getMaxHp()
                + "                                                           ");
        System.out.println("MP:" + Board.map_chosenMember.get(2).getMp() + "/" + Board.map_chosenMember.get(2).getMaxMp()
                + "                        Count:" + count + "/" + countSlash);
        System.out.println("                                " + enemy.getName());
        System.out.println("                                 HP:" + enemy.getHp() + "/" + enemy.getMaxHp());
        System.out.println(Board.map_chosenMember.get(3).getName() + "                                                         ");
        System.out.println("HP:" + Board.map_chosenMember.get(3).getHp() + "/" + Board.map_chosenMember.get(3).getMaxHp()
                + "                                                           ");
        System.out.println("MP:" + Board.map_chosenMember.get(3).getMp() + "/" + Board.map_chosenMember.get(3).getMaxMp()
                + "                                                           ");
        System.out.println("                              ");
        System.out.println(Board.map_chosenMember.get(4).getName() + "                                                         ");
        System.out.println("HP:" + Board.map_chosenMember.get(4).getHp() + "/" + Board.map_chosenMember.get(4).getMaxHp()
                + "                                                            ");
        System.out.println("MP:" + Board.map_chosenMember.get(4).getMp() + "/" + Board.map_chosenMember.get(4).getMaxMp()
                + "                                                            ");
        System.out.println("                                                                                      ");
        System.out.println(Board.map_chosenMember.get(5).getName() + "                                                         ");
        System.out.println("HP:" + Board.map_chosenMember.get(5).getHp() + "/" + Board.map_chosenMember.get(5).getMaxHp()
                + "                                                            ");
        System.out.println("MP:" + Board.map_chosenMember.get(5).getMp() + "/" + Board.map_chosenMember.get(5).getMaxMp()
                + "                                                           ");
        System.out.println("                                                                                      ");
        System.out.println("___________________________________________________\n");
    }

    public static void fun_ask(Character bose) {
        input();
        int select = sc.nextInt();
        sc.nextLine();
        if (select == 1) {
            System.out.println("** Alright, I will lower his HP for you");
            sc.nextLine();
            System.out.println("** Good luck!");
            sc.nextLine();
            bose.setHp(9000);
            bose.setMaxHp(9000);

        } else if (select == 2) {
            System.out.println("** Are you sure? I might do something for you that you should like :)");
            sc.nextLine();
            System.out.println("1. Okay, I'm super scared.");
            System.out.println("2. No, I don't need your help");

            fun_ask1(bose);
        } else {
            System.out.println("Either 1 or 2");
            fun_ask(bose);

        }
    }

    public static void fun_ask1(Character bose) {
        input();
        int select = sc.nextInt();
        sc.nextLine();
        if (select == 1) {
            System.out.println("** Haha, I will lower his HP for you");
            sc.nextLine();
            System.out.println("** Good luck!");
            sc.nextLine();
            bose.setHp(9000);
            bose.setMaxHp(9000);

        } else if (select == 2) {
            System.out.println("** Alright mang, good luck!");
        } else {
            System.out.println("Either 1 or 2");
            fun_ask1(bose);

        }
    }


    void turnAskCoin() {
        System.out.println("I'm flipping a coin to decide who attacks first.");
        System.out.println("Choose one.");
        System.out.println("1. Front");
        System.out.println("2. Back");
        Random rand = new Random();
        int coin = rand.nextInt(2) + 1;
        input();
        int select = sc.nextInt();
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
            Agit.ku.setBln_enemyFirst(true);   //Enemy's first turn
        }

    }

    void fightStatus(String name) {
        System.out.println("____________________FIGHT__________________________");
        System.out.println("___________________________________________________");
        System.out.println("                    **" + name + "**                            ");
        System.out.println("                                                           ");
        System.out.println(Board.map_chosenMember.get(1).getName() + "                                                         ");
        System.out.println("HP:" + Board.map_chosenMember.get(1).getHp() + "/" + Board.map_chosenMember.get(1).getMaxHp()
                + "                                                        ");
        System.out.println("MP:" + Board.map_chosenMember.get(1).getMp() + "/" + Board.map_chosenMember.get(1).getMaxMp()
                + "                                                       ");
        System.out.println("                              " + Dungeon.map_enemies.get(1).getName());
        System.out.println("                               HP:" + Dungeon.map_enemies.get(1).getHp() + "/" + Dungeon.map_enemies.get(1).getMaxHp());
        System.out.println(Board.map_chosenMember.get(2).getName() + "                                                         ");
        System.out.println("HP:" + Board.map_chosenMember.get(2).getHp() + "/" + Board.map_chosenMember.get(2).getMaxHp()
                + "                                                           ");
        System.out.println("MP:" + Board.map_chosenMember.get(2).getMp() + "/" + Board.map_chosenMember.get(2).getMaxMp()
                + "                                                          ");
        System.out.println("                              " + Dungeon.map_enemies.get(2).getName());
        System.out.println("                               HP:" + Dungeon.map_enemies.get(2).getHp() + "/" + Dungeon.map_enemies.get(2).getMaxHp());
        System.out.println(Board.map_chosenMember.get(3).getName() + "                                                         ");
        System.out.println("HP:" + Board.map_chosenMember.get(3).getHp() + "/" + Board.map_chosenMember.get(3).getMaxHp()
                + "                                                          ");
        System.out.println("MP:" + Board.map_chosenMember.get(3).getMp() + "/" + Board.map_chosenMember.get(3).getMaxMp()
                + "                                                        ");
        System.out.println("                              " + Dungeon.map_enemies.get(3).getName());
        System.out.println("                               HP:" + Dungeon.map_enemies.get(3).getHp() + "/" + Dungeon.map_enemies.get(3).getMaxHp());
        System.out.println(Board.map_chosenMember.get(4).getName() + "                                                         ");
        System.out.println("HP:" + Board.map_chosenMember.get(4).getHp() + "/" + Board.map_chosenMember.get(4).getMaxHp()
                + "                                                        ");
        System.out.println("MP:" + Board.map_chosenMember.get(4).getMp() + "/" + Board.map_chosenMember.get(4).getMaxMp()
                + "                                                          ");
        System.out.println("                                                                                      ");
        System.out.println(Board.map_chosenMember.get(5).getName() + "                                                         ");
        System.out.println("HP:" + Board.map_chosenMember.get(5).getHp() + "/" + Board.map_chosenMember.get(5).getMaxHp()
                + "                                                        ");
        System.out.println("MP:" + Board.map_chosenMember.get(5).getMp() + "/" + Board.map_chosenMember.get(5).getMaxMp()
                + "                                                         ");
        System.out.println("                                                                                      ");
        System.out.println("___________________________________________________\n");
    }


    public static void turn_characters(Character member) {

        System.out.println("\n** It's " + member.getName() + "'s turn:");
        System.out.println("_____________________________________________________________");
        System.out.println(" (1) Attack  (2) Block  (3) Skill  (4) Potion  (5) Escape");

        turn_ask(member);

    }

    public static void turn_enemy(Character enemy) {

        System.out.println("** It's " + enemy.getName() + "'s turn.");
        sc.nextLine();
        enemyAttack(enemy);
        System.out.println("\n\n\n\n");


    }

    public static void enemyAttack(Character enemy) {
        Random rand = new Random();
        int enemySelect = rand.nextInt(5) + 1;
        Character target = Board.map_chosenMember.get(enemySelect);
        if (target.getHp() != 0) {
            enemy.attack_selected(target);
        } else {
            enemyAttack(enemy);

        }


    }

    void enemy_turn_setup(String name) {

        System.out.println("** Enemy turn has started!");
        sc.nextLine();

        for (int i = 1; i < 4; i++) {
            if (Dungeon.map_enemies.get(i).getHp() != 0 && !Agit.ku.isBln_fight_done()) {
                turn_enemy(Dungeon.map_enemies.get(i));
                fightStatus(name);

            }


        }
        if (!Agit.ku.isBln_fight_done()) {
            System.out.println("** Enter to end the Enemy's turn");
            sc.nextLine();
        }


    }


    void fight(String name) {

        fightStatus(name);
        turnAskCoin();

        while (!Agit.ku.isBln_fight_done()) {

            if (Agit.ku.isBln_enemyFirst()) {
                enemy_turn_setup(name);
                Agit.ku.setBln_enemyFirst(false);
            }

            System.out.println("** Your turn has started!");
            sc.nextLine();

            for (int i = 1; i < 6; i++) {

                if ((Board.map_chosenMember.get(i).getHp() != 0) && !Agit.ku.isBln_fight_done()) {
                    fightStatus(name);
                    Board.map_chosenMember.get(i).setBlnBlock(false);
                    turn_characters(Board.map_chosenMember.get(i));

                }


            }

            if (Agit.ku.isBln_fight_done()) {
                break;
            }


            System.out.println("** Enter to end your turn");
            sc.nextLine();

            enemy_turn_setup(name);
            if (Agit.ku.isBln_fight_done()) {
                break;
            }

        }
        if (!Dungeon.bln_escaped) {
            if (Dungeon.player_died_count == 5) {
                System.out.println("** Shamed to lose");
                sc.nextLine();
                System.out.println("** Your team has lost 10% of Money and each member's EXP");
                int moneyLost = Board.board.getMoney() / 10;
                Board.board.setMoney(Board.board.getMoney() - moneyLost);

                System.out.println("You lost -$" + moneyLost);
                for (int i = 1; i < 6; i++) {
                    int expLost = Board.map_chosenMember.get(i).getExp() / 10;
                    Board.map_chosenMember.get(i).setExp(Board.map_chosenMember.get(i).getExp() - expLost);
                    System.out.println(Board.map_chosenMember.get(i) + " lost " + expLost + "exp");
                }


                sc.nextLine();

                System.out.println("..You're carried to Agit..\n\n\n\n");
                sc.nextLine();

            } else {
                System.out.println("** You beat the enemies! **\n");
                sc.nextLine();

                Random rand = new Random();
                int potion_chance = rand.nextInt(10) + 1;
                int which_potion_chance = rand.nextInt(100) + 1;


                if (potion_chance == 1) {
                    if (which_potion_chance < 6) {
                        System.out.println("You have earned 1 HP and 1 MP potions");
                        Board.board.setPotion_hp(Board.board.getPotion_hp() + 1);
                        Board.board.setPotion_mp(Board.board.getPotion_mp() + 1);

                    } else if (which_potion_chance > 5 && which_potion_chance < 53) {
                        System.out.println("You have earned 1 HP potion");
                        Board.board.setPotion_hp(Board.board.getPotion_hp() + 1);
                    } else {
                        System.out.println("You have earned 1 MP potion");
                        Board.board.setPotion_mp(Board.board.getPotion_mp() + 1);
                    }

                }
                sc.nextLine();

                int collect_money = 0;
                for (int i = 1; i < 4; i++) {
                    collect_money = collect_money + Dungeon.map_enemies.get(i).getMoney();
                }

                System.out.println("You have earned $ " + collect_money + ".");
                Board.board.setMoney(Board.board.getMoney() + collect_money);

                sc.nextLine();

                int expTotal = 0;
                for (int i = 1; i < 4; i++) {
                    expTotal = expTotal + Dungeon.map_enemies.get(i).getExp();

                }
                System.out.println("** Each member has earned " + expTotal + " exp !\n");
                sc.nextLine();
                for (int i = 1; i < 6; i++) {

                    Board.map_chosenMember.get(i).setExp(Board.map_chosenMember.get(i).getExp() + expTotal);
                    System.out.println(Board.map_chosenMember.get(i).getName() + ": " + Board.map_chosenMember.get(i).getExp() + "/" + Board.map_chosenMember.get(i).getLvExp());
                }

                for (int i = 1; i < 6; i++) {
                    exp(Board.map_chosenMember.get(i));
                }


                sc.nextLine();
           /* if (!blnTigsEnd) {
                tigsEnd();
                blnTigsEnd = true;
            }*/

                System.out.println("\n** Going back to Agit..");
                sc.nextLine();

                // exp
            }
        }
        Dungeon.player_died_count = 0;
        Dungeon.enemy_died_count = 0;
        Agit.ku.setBln_fight_done(false);
        Dungeon.bln_escaped = false;

        for (int i = 1; i < 6; i++) {
            if (Board.map_chosenMember.get(i).getHp() == 0) {
                Board.map_chosenMember.get(i).setHp(1);
            }

        }


    }

    public static void turn_ask(Character member) {
        input();
        int select = sc.nextInt();
        sc.nextLine();
        switch (select) {
            case 1:
                    /*if (blnLa) {
                        member.attack(boss1);
                    } else if (blnVegas) {
                        member.attack(boss2);
                    } else {
                        attackChoose(member);
                    }*/
                attackChoose(member);
                break;
            case 2:
                block(member);
                break;
            case 3:

                if (member.getMp() < member.getMp_for_skill()) {
                    System.out.println("You don't have enough MP to use Skill.");
                    sc.nextLine();
                    turn_characters(member);
                } else {
                        /*if (blnLa) {
                            member.useSkill(boss1);
                        } else if (blnVegas) {
                            member.useSkill(boss2);
                        } else {
                            skillChoose(member);
                        }*/
                    skill(member);
                }

                break;
            case 4:
                if (Board.board.getPotion_hp() <= 0 && Board.board.getPotion_mp() <= 0) {
                    System.out.println("You don't have a portion to drink");
                    sc.nextLine();
                    turn_characters(member);

                } else {

                    potion(member);
                }
                break;
            case 5:
                escape(member);
                break;
            default:
                System.out.println("From 1 to 5");
                turn_ask(member);
                break;

        }

    }


    public static void exp(Character member) {


        while (member.getExp() >= member.getLvExp()) {
            int a = member.getLv();
            sc.nextLine();
            System.out.println("\n*** " + member + " Level up!!***");
            System.out.println("From " + member.getLv() + " -> " + (a + 1));
            sc.nextLine();

            member.setLv(member.getLv() + 1);
            levelUp(member);
            if (!Board.board.isBln_tigs_entry()) {
                member.setLvExp((member.getLvExp()) + 900);
            } else {
                member.setLvExp((member.getLvExp()) + 1900);
            }

            member.setMp_for_skill(member.getMp_for_skill() + 2);
        }

    }

    public static void levelUp(Character member) {
        member.setMaxHp(member.getMaxHp() + 20);
        member.setMaxMp(member.getMaxMp() + 10);
        member.setAtt(member.getAtt() + 10);
        System.out.println(member.getName() + "'s HP has been increased by 20.");

        System.out.println(member.getName() + "'s MP has been increased by 10.");

        System.out.println(member.getName() + "'s ATT has been increased by 10.");
        sc.nextLine();
        System.out.println(
                "** I'm rolling a dice. You guess the number, and if it's correct, you're getting a bonus stat ");
        sc.nextLine();
        System.out.println("Choose from 1 to 4");
        levelUpAsk2(member);

    }


    public static void levelUpAsk2(Character member) {
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

                levelUpAsk(member);
            } else if (num >= 5 || num < 1) {
                System.out.println("From 1 to 4");
                levelUpAsk2(member);
            } else {
                System.out.println("** Sorry..");
                sc.nextLine();

            }
        } catch (InputMismatchException e) {
            System.out.println("Numbers only");
            levelUpAsk2(member);
        }

        member.setHp(member.getMaxHp());
        member.setMp(member.getMaxMp());

    }

    public static void levelUpAsk(Character member) {
        try {

            int select = sc.nextInt();
            sc.nextLine();
            switch (select) {

                case 1:
                    member.setMaxHp(member.getMaxHp() + 20);
                    System.out.println("** HP has increased by 20.");
                    break;
                case 2:
                    member.setMaxMp(member.getMaxMp() + 10);
                    System.out.println("** MP has increased by 10.");
                    break;
                case 3:
                    member.setAtt(member.getAtt() + 5);
                    System.out.println("** ATT has incrased by 5.");
                    break;
                default:
                    System.out.println("From 1 to 3");
                    levelUpAsk(member);
                    break;

            }
        } catch (InputMismatchException e) {
            System.out.println("Numbers only");
            levelUpAsk(member);
        }
    }


    public static void block(Character member) {
        System.out.println("** 20% chance of failing to block");
        System.out.println("1. Block");
        System.out.println("2. Go back");
        input();
        int select = sc.nextInt();
        if (select == 1) {
            member.setBlnBlock(true);
            System.out.println("\n** " + member.getName() + " takes Guard position.");
        } else if (select == 2) {
            turn_characters(member);
        } else {
            block(member);
        }

    }

    public static void potion(Character member) {
        System.out.println("Which portion would you like to drink?");
        System.out.println("1.(80% heal) HP portion:" + Board.board.getPotion_hp());
        System.out.println("2.(80% heal) MP portion:" + Board.board.getPotion_mp());
        System.out.println("3. Cancel");
        input();
        int select = sc.nextInt();
        sc.nextLine();


        switch (select) {
            case 1:
                if (Board.board.getPotion_hp() <= 0) {
                    System.out.println("You don't have HP potion.");
                    sc.nextLine();
                    potion(member);

                } else {
                    Agit.fight.ask_which_character_hp(member);
                }
                break;
            case 2:
                if (Board.board.getPotion_mp() <= 0) {
                    System.out.println("You don't have MP potion.");
                    sc.nextLine();
                    potion(member);

                } else {
                    Agit.fight.ask_which_character_mp(member);
                }
                break;
            case 3:
                turn_characters(member);
                break;
            default:
                System.out.println("From 1 to 3");
                potion(member);

        }


    }


    void ask_which_character_hp(Character member) {


        System.out.println("\nto Who?");


        for (int i = 1; i < 6; i++) {
            if (Board.map_chosenMember.get(i).getHp() != 0) {
                System.out.println(i + ". " + Board.map_chosenMember.get(i).getName() + "   HP:" + Board.map_chosenMember.get(i).getHp() + "/" + Board.map_chosenMember.get(i).getMaxHp());
            } else {
                System.out.println(i + ". Knocked down");
            }

        }

        System.out.println("6.  Go back");

        input();
        int select = sc.nextInt();
        sc.nextLine();

        if (select > 0 && select < 6) {

            heals_hp(Board.map_chosenMember.get(select));


        } else if (select == 6) {
            turn_characters(member);
        } else {

            ask_which_character_hp(member);


        }


    }


    void ask_which_character_mp(Character member) {


        System.out.println("\nto Who?");


        for (int i = 1; i < 6; i++) {
            if (Board.map_chosenMember.get(i).getHp() != 0) {
                System.out.println(i + ". " + Board.map_chosenMember.get(i).getName() + "   MP:" + Board.map_chosenMember.get(i).getMp() + "/" + Board.map_chosenMember.get(i).getMaxMp());
            } else {
                System.out.println(i + ". Knocked down");
            }

        }


        System.out.println("6.  Go back");

        input();
        int select = sc.nextInt();
        sc.nextLine();

        if (select > 0 && select < 6) {

            heals_mp(Board.map_chosenMember.get(select));


        } else if (select == 6) {
            turn_characters(member);
        } else {

            ask_which_character_mp(member);


        }


    }


    void heals_hp(Character member) {


        if (member.getHp() == member.getMaxHp()) {
            System.out.println(member.getName() + "'s HP is full. Choose different character");
            ask_which_character_hp(member);
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
            ask_which_character_hp(member);
        }

    }

    void heals_mp(Character member) {


        if (member.getMp() == member.getMaxMp()) {
            System.out.println(member.getName() + "'s MP is full. Choose different character");
            ask_which_character_mp(member);
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
            Board.board.setPotion_mp(Board.board.getPotion_mp() - 1);
        } else {
            System.out.println("He is already knocked down. Choose different character");
            ask_which_character_mp(member);
        }

    }


    public static void skill(Character member) {
        System.out.println("\nto Who? (x2.0 damage, MP " + member.getMp_for_skill() + " uses, 10% of Critical x2.5)");


        choose_enemy_image();
        choose_enemy_for_skill(member);
    }

    public static void escape(Character member) {

        System.out.println("Do you really want to run away ? [30% of failing to escape]");
        System.out.println("1. Yes");
        System.out.println("2. No");
        input();
        Random rand = new Random();
        int select = sc.nextInt();
        sc.nextLine();

        if (select == 1) {
            int percent = rand.nextInt(10) + 1;
            if (percent < 8) {
                System.out.println("** You have successfully escaped.");
                sc.nextLine();
                System.out.println("** Going back to Agit.");
                sc.nextLine();
                Dungeon.bln_escaped = true;
                Agit.ku.setBln_fight_done(true);
                Agit.la.setBln_fight_done(true);
           /*Agit.tigs.setBln_fight_done(true);
           Agit.vegas.setBln_fight_done(true);*/
            } else {
                System.out.println("\n\n\n\n** Failed to escape hehehe");

                /* nextTurn(member);*/

            }
        } else if (select == 2) {

            turn_characters(member);
        } else {
            System.out.println("Either 1 or 2");
            escape(member);
        }

    }


   /* public static void nextTurn(Character member) {
        sc.nextLine();
        System.out.println("** Hit enter for the next turn");
        sc.nextLine();
      *//*  if (blnLa) {
            bossFightStatus(boss1, boss1Count, 10, "LA bar");

        } else if (blnVegas) {
            bossFightStatus(boss2, boss2Count, 7, "Beach Club");

        } else {

        }*//*


        Character next;

        for (int i = 1; i < 5; i++) {
            if (Board.map_chosenMember.get(i).getName() == member.getName()) {
              *//*  bln_five=true;*//*
                next = Board.map_chosenMember.get(++i);

                int a = 1;
                while (next.getHp() == 0 && a!=0) {
                    a = ++i % 5;
                    if(a==0){
                        a=5;
                    }
                    next = Board.map_chosenMember.get(a);
                }

                turn_characters(next);



            }
        }



}*/


    public static void attackChoose(Character member) {
        System.out.println("\nWho?");
        choose_enemy_image();
        choose_enemy_for_attack(member);
    }

    public static void choose_enemy_image() {
        if (Agit.la.bln_la_on || Agit.vegas.bln_vegas_on) {
            if (Dungeon.map_enemies.get(1).getHp() != 0) {
                System.out.println("1. " + Dungeon.map_enemies.get(1).getName());
                System.out.println("2. Go back");

            } else {


                for (int i = 1; i < 4; i++) {

                    if (Dungeon.map_enemies.get(i).getHp() != 0) {
                        System.out.println(i + ". " + Dungeon.map_enemies.get(i).getName());
                    } else {
                        System.out.println(i + ". Knocked down");
                    }


                }
                System.out.println("4. Go back");
            }
        }
    }

    public static void choose_enemy_for_skill(Character member) {

        input();
        int select = sc.nextInt();
        sc.nextLine();
        if (Agit.la.bln_la_on || Agit.vegas.bln_vegas_on) {
            if (select == 1) {
                member.skill_selected(Dungeon.map_enemies.get(select));
            } else if (select == 2) {
                turn_characters(member);
            } else {
                System.out.println("1 or 2");
                choose_enemy_for_skill(member);
            }

        } else {


            if (select > 0 && select < 4) {
                if (Dungeon.map_enemies.get(select).getHp() != 0) {
                    member.skill_selected(Dungeon.map_enemies.get(select));
                } else {
                    System.out.println("Already knocked down, choose different enemy");
                    choose_enemy_for_skill(member);
                }

            } else if (select == 4) {
                turn_characters(member);
            } else {
                System.out.println("1 to 4");
                choose_enemy_for_skill(member);
            }

        }
    }

    public static void choose_enemy_for_attack(Character member) {
        input();
        int select = sc.nextInt();
        sc.nextLine();
        if (Agit.la.bln_la_on || Agit.vegas.bln_vegas_on) {
            if (select == 1) {
                member.attack_selected(Dungeon.map_enemies.get(select));
            } else if (select == 2) {
                turn_characters(member);
            } else {
                System.out.println("1 or 2");
                choose_enemy_for_attack(member);
            }


        } else {


            if (select > 0 && select < 4) {
                if (Dungeon.map_enemies.get(select).getHp() != 0) {
                    member.attack(Dungeon.map_enemies.get(select));


                } else {
                    System.out.println("Already knocked down, choose different enemy");
                    choose_enemy_for_attack(member);
                }
            } else if (select == 4) {
                turn_characters(member);
            } else {
                System.out.println("1 to 4");
                choose_enemy_for_attack(member);
            }

        }

    }
}
