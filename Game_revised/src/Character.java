import java.io.Serializable;
import java.util.Random;
import java.util.Scanner;

public class Character implements Serializable {

    static Scanner sc = new Scanner(System.in);
    static boolean bln_died = false;
    private String name;
    private int hp;
    private int mp;
    private int maxHp;
    private int maxMp;
    private int mp_for_skill;
    private int att;
    private String skill;
    boolean blnBlock = false;
    private int count_slash;

    private int exp;
    private int lv;
    private int lvExp;
    private int money;

    Character(String name, int hp, int maxHp, int att, String skill, int exp) {

        this.setBlnBlock(false);

        this.setName(name);
        this.setHp(hp);
        this.setMaxHp(maxHp);
        this.setAtt(att);
        this.setSkill(skill);
        this.setExp(exp);

    }


    public int getCount_slash() {
        return count_slash;
    }

    public void setCount_slash(int count_slash) {
        this.count_slash = count_slash;
    }


    public int getMoney() {
        return money;
    }


    public void setMoney(int money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMp_for_skill() {
        return mp_for_skill;
    }

    public void setMp_for_skill(int mp_for_skill) {
        this.mp_for_skill = mp_for_skill;
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

    public boolean isBlnBlock() {
        return blnBlock;
    }

    public void setBlnBlock(boolean blnBlock) {
        this.blnBlock = blnBlock;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getLv() {
        return lv;
    }

    public void setLv(int lv) {
        this.lv = lv;
    }

    public int getLvExp() {
        return lvExp;
    }

    public void setLvExp(int lvExp) {
        this.lvExp = lvExp;
    }

    void attack_selected(Character target) {

        Random rand = new Random();
        int blockChance = rand.nextInt(10) + 1;
        if (target.isBlnBlock()) {
            if (blockChance < 3) {
                System.out.println("!!!Block failed!!");
                attack(target);
            } else {
                System.out.println(target.getName() + " blocked the attack!!");
                sc.nextLine();
            }
        } else {
            attack(target);
        }


        sc.nextLine();
    }


    void diedCheck(Character target) {
        for (int i = 1; i < 6; i++) {
            if (Board.map_chosenMember.get(i) == target) {
                Dungeon.player_died_count++;
                bln_died = true;
            }

        }
        if (!bln_died) {
            Dungeon.enemy_died_count++;
            Dungeon.bln_boss_killed = true;

        }


        if (Dungeon.player_died_count == 5 || Dungeon.enemy_died_count == 3 || Dungeon.bln_boss_killed == true) {

            Agit.ku.setBln_fight_done(true);
            Agit.la.setBln_fight_done(true);
            /* Agit.tigs.setBln_fight_done(true);*/
            Agit.vegas.setBln_fight_done(true);

        }
    }


    void attack(Character target) {
        bln_died = false;

        if (target.getHp() <= this.getAtt()) {
            target.setHp(0);
            System.out.println("** " + this.getName() + " attacked " + target.getName() + " by " + this.getAtt() + "!!");
            sc.nextLine();
            System.out.println("!!! " + target.getName() + " knocked down!");
            sc.nextLine();
            diedCheck(target);


        } else {
            target.setHp(target.getHp() - this.getAtt());
            System.out.println("** " + this.getName() + " attacked " + target.getName() + " by " + this.getAtt() + "!!");


        }


    }


    void skill_selected(Character target) {
        Random rand = new Random();
        int crit = rand.nextInt(10) + 1;
        this.setMp(this.getMp() - this.getMp_for_skill());
        System.out.println("** Used " + this.getMp_for_skill() + " MP");
        if (crit <= 9) {
            int att = (int) (this.getAtt() * 2);
            System.out.println("** " + this.getName() + "'s " + this.getSkill() + "!!");
            skill_attack(target, att);
        } else {
            int att = (int) (this.getAtt() * 2.5);
            System.out.println("** " + this.getName() + "'s " + this.getSkill() + "!!");
            System.out.println("** Critical!!");
            skill_attack(target, att);

        }
    }


    void skill_attack(Character target, int att) {
        bln_died = false;

        if (target.getHp() <= att) {
            target.setHp(0);
            System.out.println("** " + target.getName() + " got damaged, by " + att + "!!");
            sc.nextLine();
            System.out.println("** " + target.getName() + " knocked down!");
            diedCheck(target);

        } else {
            target.setHp(target.getHp() - att);
            System.out.println("** " + target.getName() + " got damaged by " + att + "!!");
        }
        sc.nextLine();


    }


    void skill_attack_by_boss(Character target, String name) {
        bln_died = false;
        System.out.println("**The " + name + " got super mad!!!!");
        sc.nextLine();
        int attBoss = (int) (this.getAtt() * 1.7);
        if (target.getHp() <= attBoss) {
            target.setHp(0);
            System.out.println("** " + this.getName() + " attacked " + target.getName() + " by " + attBoss + "!!");
            sc.nextLine();
            System.out.println("!!! " + target.getName() + " knocked down!");
            diedCheck(target);


        }
    }


}


class Friends extends Character implements Serializable {

    Friends(String name, int hp, int mp, int maxHp, int maxMp, int mp_for_skill, int att, String skill, int exp, int lv, int lvExp) {
        super(name, hp, maxHp, att, skill, exp);


        this.setMp(mp);
        this.setMaxMp(maxMp);
        this.setMp_for_skill(mp_for_skill);
        this.setLv(lv);
        this.setLvExp(lvExp);


    }
}

class Enemy extends Character implements Serializable {


    Enemy(String name, int hp, int maxHp, int att, String skill, int exp, int money, int count_slash) {
        super(name, hp, maxHp, att, skill, exp);
        this.setMoney(money);
        this.setCount_slash(count_slash);

    }


}




