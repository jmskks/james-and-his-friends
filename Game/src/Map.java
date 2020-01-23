import java.util.Random;

public class Map {

    static Character enemy1;
    static Character enemy2;
    static Character enemy3;

}

class Ku extends Map {

    Ku() {

        Character enemy1 = new Enemy1(90, 20, 100, 130, "drunken A");
        Character enemy2 = new Enemy1(90, 20, 100, 130, "drunken A");
        Character enemy3 = new Enemy1(90, 20, 100, 130, "drunken A");

        Character enemy4 = new Enemy1(60, 10, 150, 100, "drugged A");
        Character enemy5 = new Enemy1(60, 10, 150, 100, "drugged A");
        Character enemy6 = new Enemy1(60, 10, 150, 100, "drugged A");

        Character enemy7 = new Enemy1(5, 1, 200, 150, "Cat");
        Character enemy8 = new Enemy1(5, 1, 200, 150, "Cat");
        Character enemy9 = new Enemy1(5, 1, 200, 150, "Cat");

        picked(enemy1, enemy4, enemy7);
        picked(enemy2, enemy5, enemy8);
        picked(enemy3, enemy6, enemy9);

    }

    public void picked(Character enemy1, Character enemy2, Character enemy3) {
        Character enemy;
        Random rand = new Random();
        int num = rand.nextInt(100) + 1;
        if (num <= 5) {
            enemy = enemy3;
        } else if (num > 5 && num >= 45) {
            enemy = enemy2;
        } else {
            enemy = enemy1;
        }

        if (Map.enemy1 == null) {
            Map.enemy1 = enemy;
        } else if (Map.enemy2 == null) {
            Map.enemy2 = enemy;
        } else if (Map.enemy3 == null) {
            Map.enemy3 = enemy;
        }

    }

}

class Tigs extends Map {
    Tigs() {

        Character enemy1 = new Enemy1(280, 70, 200, 400, "drunken A");
        Character enemy2 = new Enemy1(280, 70, 200, 400, "drunken A");
        Character enemy3 = new Enemy1(280, 70, 200, 400, "drunken A");

        Character enemy4 = new Enemy1(200, 50, 300, 300, "drugged A");
        Character enemy5 = new Enemy1(200, 50, 300, 300, "drugged A");
        Character enemy6 = new Enemy1(200, 50, 300, 300, "drugged A");

        Character enemy7 = new Enemy1(5, 1, 400, 500, "Dog");
        Character enemy8 = new Enemy1(5, 1, 400, 500, "Dog");
        Character enemy9 = new Enemy1(5, 1, 400, 500, "Dog");

        picked(enemy1, enemy4, enemy7);
        picked(enemy2, enemy5, enemy8);
        picked(enemy3, enemy6, enemy9);

    }

    public void picked(Character enemy1, Character enemy2, Character enemy3) {
        Character enemy;
        Random rand = new Random();
        int num = rand.nextInt(100) + 1;
        if (num <= 5) {
            enemy = enemy3;
        } else if (num > 5 && num >= 45) {
            enemy = enemy2;
        } else {
            enemy = enemy1;
        }

        if (Map.enemy1 == null) {
            Map.enemy1 = enemy;
        } else if (Map.enemy2 == null) {
            Map.enemy2 = enemy;
        } else if (Map.enemy3 == null) {
            Map.enemy3 = enemy;
        }

    }
}
