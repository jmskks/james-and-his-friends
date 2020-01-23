import java.io.Serializable;
import java.util.Random;
import java.util.Scanner;

public class Character implements Serializable {
    private int hp;
    private int mp;
    private int att;
    private int maxHp;
    private int maxMp;
    private String skill;
    boolean blnBlock = false;
    private int mpAdj;


    private int exp;
    private int lv;
    private int lvExp;
    static int money = 500;
    static int hpNum = 3;
    static int mpNum = 3;
    private int moneyMonster;
    private Potion porHHContain;
    private Potion porMMContain;


    public int getMpAdj() {
        return mpAdj;
    }

    public void setMpAdj(int mpAdj) {
        this.mpAdj = mpAdj;
    }
    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public int getMaxMp() {
        return maxMp;
    }

    public void setMaxMp(int maxMp) {
        this.maxMp = maxMp;
    }

    public Potion getPorHHContain() {
        return porHHContain;
    }

    public void setPorHHContain(Potion porHHContain) {
        this.porHHContain = porHHContain;
    }

    public Potion getPorMMContain() {
        return porMMContain;
    }

    public void setPorMMContain(Potion porMMContain) {
        this.porMMContain = porMMContain;
    }

    public int getMoneyMonster() {
        return moneyMonster;
    }

    public void setMoneyMonster(int moneyMonster) {
        this.moneyMonster = moneyMonster;
    }

    public int getLvExp() {
        return lvExp;
    }

    public void setLvExp(int lvExp) {
        this.lvExp = lvExp;
    }

    public int getLv() {
        return lv;
    }

    public void setLv(int lv) {
        this.lv = lv;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public boolean isBlnBlock() {
        return blnBlock;
    }

    public void setBlnBlock(boolean blnBlock) {
        this.blnBlock = blnBlock;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMp() {
        return mp;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }

    public int getAtt() {
        return att;
    }

    public void setAtt(int att) {
        this.att = att;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String toString(String str) {
        return str;

    }

    public void attack(Character enemy) {
        Scanner sc = new Scanner(System.in);
        Random rand2 = new Random();
        int attChance = rand2.nextInt(10) + 1;
        if (attChance < 9) {
            if(Main.blnLa) {
                Main.boss1Count++;
            }if(Main.blnVegas) {
                Main.boss2Count++;
            }
            if (enemy.getHp() <= this.getAtt()) {
                enemy.setHp(0);
                System.out.println("** " + this + " attacked " + enemy + " by " + this.getAtt() + "!!");
                System.out.println("** " + enemy + " knocked down!");
                sc.nextLine();
                this.setExp(this.getExp() + enemy.getExp());
                System.out.println("** " + this + ", has earned " + enemy.getExp() + "exp from " + enemy + "!  Exp:"
                        + this.getExp() + "/" + this.getLvExp());
                sc.nextLine();
                Main.exp(this);
                Character.money = Character.money + enemy.getMoneyMonster();
                System.out.println("** $" + enemy.getMoneyMonster() + " acquired from " + enemy + "  +$"
                        + enemy.getMoneyMonster());
                Random rand = new Random();
                Random rand1 = new Random();
                int portChance = rand.nextInt(10) + 1;
                if (portChance >= 8) {
                    int twoChance = rand1.nextInt(2) + 1;
                    if (twoChance == 1) {
                        System.out.println("** You acquired 1 of " + enemy.porHHContain + ".");
                        Character.hpNum++;
                    } else {
                        System.out.println("** You acquired 1 of " + enemy.porMMContain + ".");
                        Character.mpNum++;
                    }
                    sc.nextLine();
                }
                if (Main.boss1.getHp() == 0) {
                    Main.blnLaBossKilled = true;
                }
                if (Main.boss2.getHp() ==0) {
                    Main.blnVegasBossKilled=true;
                }
                Main.deadEnemyCount++;
                if (Main.deadEnemyCount == 3) {
                    Main.blnDungeon = true;
                }

            } else {
                enemy.setHp(enemy.getHp() - this.getAtt());
                System.out.println("** " + this + " attacked " + enemy + " by " + this.getAtt() + "!!");

            }
            sc.nextLine();
        } else {
            System.out.println("!!" + this + " missed the target!");
            sc.nextLine();
        }

    }

    public void useSkill(Character enemy) {

        Random rand = new Random();
        Scanner sc = new Scanner(System.in);
        int crit = rand.nextInt(10) + 1;
        this.setMp(this.getMp() - this.getMpAdj());
        System.out.println("** Used "+this.getMpAdj()+" MP");
        if (crit <= 9) {
            int att = (int) (this.getAtt() * 2);
            System.out.println("** " + this + "'s " + this.getSkill() + "!!");
            skillAtt(enemy, att);
        } else {
            int att = (int) (this.getAtt() * 2.5);
            System.out.println("** " + this + "'s " + this.getSkill() + "!!");
            System.out.println("** Critical!!");
            skillAtt(enemy, att);

        }

    }

    private void skillAtt(Character enemy, int att) {
        Scanner sc = new Scanner(System.in);
        if (enemy.getHp() <= att) {
            enemy.setHp(0);
            System.out.println("** " + enemy + " got damaged, by " + att + "!!");
            sc.nextLine();
            System.out.println("** " + enemy + " knocked down!");
            sc.nextLine();
            if(Main.blnVegas) {
                Main.blnVegasBossKilled = true;
                Main.blnDungeon = true;
            }else if (Main.blnLa) {
                Main.blnLaBossKilled = true;
                Main.blnDungeon = true;
            }else {
                Main.deadEnemyCount++;
                if (Main.deadEnemyCount == 3) {
                    Main.blnDungeon = true;
                }
            }

        } else {
            enemy.setHp(enemy.getHp() - att);
            System.out.println("** " + enemy + " got damaged by " + att + "!!");
            sc.nextLine();
        }
    }

}

class Kyungseok extends Character  implements Serializable {

    Kyungseok() {
        this.setMaxHp(100);
        this.setMaxMp(30);
        this.setHp(100);
        this.setMp(30);
        this.setAtt(20);
        this.setSkill("Throwing his 300 kgs of Korean and US military uniforms from his closet");
        this.setExp(0);
        this.setLv(1);
        this.setLvExp(800);
        this.setMpAdj(15);

    }

    public String toString() {
        return "Kyungseok";

    }

}

class Hyungun extends Character   implements Serializable{

    Hyungun() {
        this.setMaxHp(100);
        this.setMaxMp(30);
        this.setHp(100);
        this.setMp(30);
        this.setAtt(20);
        this.setSkill("Throwing a bunch of his shoes");
        this.setExp(0);
        this.setLv(1);
        this.setLvExp(800);
        this.setMpAdj(15);
    }

    public String toString() {
        return "Hyungun";

    }

}

class Jongwoo extends Character   implements Serializable{

    Jongwoo() {
        this.setMaxHp(100);
        this.setMaxMp(30);
        this.setHp(100);
        this.setMp(30);
        this.setAtt(20);
        this.setSkill("Throwing 100kgs of his soccer shoes");
        this.setExp(0);
        this.setLv(1);
        this.setLvExp(800);
        this.setMpAdj(15);
    }

    public String toString() {
        return "Jongwoo";

    }

}

class Junggun extends Character   implements Serializable{

    Junggun() {
        this.setMaxHp(100);
        this.setMaxMp(30);
        this.setHp(100);
        this.setMp(30);
        this.setAtt(20);
        this.setSkill("Throwing 300 kgs of his computer hardwares");
        this.setExp(0);
        this.setLv(1);
        this.setLvExp(800);
        this.setMpAdj(15);
    }

    public String toString() {
        return "Jungguen";

    }

}

class Taegun extends Character   implements Serializable{

    Taegun() {
        this.setMaxHp(100);
        this.setMaxMp(30);
        this.setHp(100);
        this.setMp(30);
        this.setAtt(20);
        this.setSkill("Throwing 200 cartons of Marlbs");
        this.setExp(0);
        this.setLv(1);
        this.setLvExp(800);
        this.setMpAdj(15);
    }

    public String toString() {
        return "Taeguen";

    }

}

class Byungsoo extends Character  implements Serializable {

    Byungsoo() {
        this.setMaxHp(100);
        this.setMaxMp(30);
        this.setHp(100);
        this.setMp(30);
        this.setAtt(20);
        this.setSkill("Throwing a bunch of his smelly hair");
        this.setExp(0);
        this.setLv(1);
        this.setLvExp(800);
        this.setMpAdj(15);
    }

    public String toString() {
        return "Byungsoo";

    }

}

class Dongyung extends Character   implements Serializable{

    Dongyung() {
        this.setMaxHp(100);
        this.setMaxMp(30);
        this.setHp(100);
        this.setMp(30);
        this.setAtt(20);
        this.setSkill("Throwing 200 kgs of credit cards that he has sold to his customers");
        this.setExp(0);
        this.setLv(1);
        this.setLvExp(800);
        this.setMpAdj(15);
    }

    public String toString() {
        return "Dongyeung";

    }

}

class Daesung extends Character   implements Serializable{

    Daesung() {
        this.setMaxHp(100);
        this.setMaxMp(30);
        this.setHp(100);
        this.setMp(30);
        this.setAtt(20);
        this.setSkill("Throwing 400 kgs of gameCDs that he has bought since he was 2 months old");
        this.setExp(0);
        this.setLv(1);
        this.setLvExp(800);
        this.setMpAdj(15);

    }

    public String toString() {
        return "Daesung";

    }

}

class Daegun extends Character  implements Serializable {

    Daegun() {
        this.setMaxHp(100);
        this.setMaxMp(30);
        this.setHp(100);
        this.setMp(30);
        this.setAtt(20);
        this.setSkill("Throwing scaling tools that he used to poke his nose");
        this.setExp(0);
        this.setLv(1);
        this.setLvExp(800);
        this.setMpAdj(15);
    }

    public String toString() {
        return "Daeguen";

    }

}

class Sean extends Character  implements Serializable {

    Sean() {
        this.setMaxHp(100);
        this.setMaxMp(30);
        this.setHp(100);
        this.setMp(30);
        this.setAtt(20);
        this.setSkill("Throwing 150 kgs of sushis that he sold this morning");
        this.setExp(0);
        this.setLv(1);
        this.setLvExp(800);
        this.setMpAdj(15);
    }

    public String toString() {
        return "Sean";

    }

}

class Enemy1 extends Character  implements Serializable {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    Enemy1(int hp, int att, int exp, int moneyMonster, String name) {
        this.setAtt(att);
        this.setHp(hp);
        this.setMaxHp(hp);
        this.setExp(exp);
        this.name = name;
        this.setMoneyMonster(moneyMonster);
        Potion Hpo = new Potion("HP portion");
        Potion Mpo = new Potion("MP portion");

        this.setPorHHContain(Hpo);
        this.setPorMMContain(Mpo);

    }

    public String toString() {
        return this.name;

    }

    public void attack(Character chosenMember) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        int blockChance = rand.nextInt(10)+1;
        if (chosenMember.isBlnBlock() == true) {
            if(blockChance <3) {
                System.out.println("!!!Block failed!!");
                attackY(chosenMember);
            }else {
                System.out.println(chosenMember + " blocked the attack!!");
                sc.nextLine();
                chosenMember.setBlnBlock(false);
            }
        } else {
            if (this.name.equals("Drugged A")) {
                special1(chosenMember);
            } else {

                attackY(chosenMember);

            }

        }
        sc.nextLine();
    }

    private void special1(Character chosenMember) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        int chance = rand.nextInt(10) + 1;
        if (chance < 8) {
            int att = (int) this.getAtt() * 2;
            if (chosenMember.getHp() <= att) {
                chosenMember.setHp(0);
                System.out.println("** " + this + " just took another drug and became more powerful!!");
                sc.nextLine();
                System.out.println("** " + this + " attacked " + chosenMember + " by " + att + "!!");
                sc.nextLine();
                System.out.println("!!! " + chosenMember + " knocked down!");

                Main.deadTeamCount++;
                if (Main.deadTeamCount == 5) {
                    Main.blnDungeon = true;

                }

            } else {
                chosenMember.setHp(chosenMember.getHp() - att);
                System.out.println("** " + this + " just took another drug and became more powerful!!");
                System.out.println("** " + this + " attacked " + chosenMember + " by " + att + "!!");


            }
        } else {
            attackY(chosenMember);
        }
    }

    private void attackY(Character chosenMember) {
        Scanner sc = new Scanner(System.in);
        if(Main.blnLa&& Main.boss1Count>=10) {
            Main.boss1Count = Main.boss1Count-10;

            System.out.println("**The Owner got super mad!!!!");
            sc.nextLine();
            int attBoss = (int)(this.getAtt()*1.7);
            if (chosenMember.getHp() <= attBoss) {
                chosenMember.setHp(0);
                System.out.println("** " + this + " attacked " + chosenMember + " by " + attBoss + "!!");
                sc.nextLine();
                System.out.println("!!! " + chosenMember + " knocked down!");
                Main.deadTeamCount++;
                if (Main.deadTeamCount == 5) {
                    Main.blnDungeon = true;

                }

            } else {
                chosenMember.setHp(chosenMember.getHp() - attBoss);
                System.out.println("** " + this + " attacked " + chosenMember + " by " + attBoss + "!!");


            }
        }else if(Main.blnVegas&&Main.boss2Count>=7){
            Main.boss2Count = Main.boss2Count -7;
            System.out.println("**The Pervert got super mad!!!!");
            sc.nextLine();
            int attBoss = (int)(this.getAtt()*1.7);
            if (chosenMember.getHp() <= attBoss) {
                chosenMember.setHp(0);
                System.out.println("** " + this + " attacked " + chosenMember + " by " + attBoss + "!!");
                sc.nextLine();
                System.out.println("!!! " + chosenMember + " knocked down!");
                Main.deadTeamCount++;
                if (Main.deadTeamCount == 5) {
                    Main.blnDungeon = true;

                }

            } else {
                chosenMember.setHp(chosenMember.getHp() - attBoss);
                System.out.println("** " + this + " attacked " + chosenMember + " by " + attBoss + "!!");


            }



        }else  if(chosenMember.getHp() <= this.getAtt()) {
            chosenMember.setHp(0);
            System.out.println("** " + this + " attacked " + chosenMember + " by " + this.getAtt() + "!!");
            sc.nextLine();
            System.out.println("!!! " + chosenMember + " knocked down!");

            Main.deadTeamCount++;
            if (Main.deadTeamCount == 5) {
                Main.blnDungeon = true;

            }

        } else {
            chosenMember.setHp(chosenMember.getHp() - this.getAtt());
            System.out.println("** " + this + " attacked " + chosenMember + " by " + this.getAtt() + "!!");


        }
    }

}
