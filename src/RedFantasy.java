import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RedFantasy {
    List<String> monsters = new ArrayList<>();
    List<Integer> monstersPoint = new ArrayList<>();

    List<Integer> playerMonsters = new ArrayList<>(List.of(-1, -1, -1, -1, -1));
    List<Integer> playerMonstersPoint = new ArrayList<>(List.of(0, 0, 0, 0, 0));

    List<Integer> cpuMonsters = new ArrayList<>(List.of(-1, -1, -1, -1, -1));
    List<Integer> cpuMonstersPoint = new ArrayList<>(List.of(0, 0, 0, 0, 0));

    int playerHp = 50;
    int cpuHp = 50;
    int playerBonusPoint = 0;
    int cpuBonusPoint = 0;

    Random rnd = new Random();

    List<Integer> playerHistory = new ArrayList<>();
    List<Integer> cpuHistory = new ArrayList<>();

    public RedFantasy() {
        initializeHistories();
    }

    private void initializeHistories() {
        playerHistory.add(playerHp);
        cpuHistory.add(cpuHp);
        for (int i = 1; i < 100; i++) {
            playerHistory.add(-9999);
            cpuHistory.add(-9999);
        }
    }

    public void setMonsterZukan(List<String> monsters) {
        this.monsters = monsters;
    }

    public void setMonstersPoint(List<Integer> monstersPoint) {
        this.monstersPoint = monstersPoint;
    }

    public int getPlayerHp() {
        return playerHp;
    }

    public int getCpuHp() {
        return cpuHp;
    }

    public List<Integer> getPlayerHistory() {
        return playerHistory;
    }

    public List<Integer> getCpuHistory() {
        return cpuHistory;
    }

    public void startPhase() {
        drawMonsters(playerMonsters, playerMonstersPoint, "Player");
        drawMonsters(cpuMonsters, cpuMonstersPoint, "CPU");

        playerBonusPoint = rollDice(playerMonstersPoint, "Player");
        cpuBonusPoint = rollDice(cpuMonstersPoint, "CPU");

        int playerTotal = calculateTotalPoints(playerMonstersPoint, playerBonusPoint);
        int cpuTotal = calculateTotalPoints(cpuMonstersPoint, cpuBonusPoint);

        battle(playerTotal, cpuTotal);
    }

    private void drawMonsters(List<Integer> monsters, List<Integer> monstersPoint, String playerType) {
        for (int i = 0; i < monsters.size(); i++) {
            int cardIndex = rnd.nextInt(this.monsters.size());
            monsters.set(i, cardIndex);
            monstersPoint.set(i, this.monstersPoint.get(cardIndex));
            System.out.println(playerType + " draws " + this.monsters.get(cardIndex) + " with point " + this.monstersPoint.get(cardIndex));
        }
    }

    private int rollDice(List<Integer> monstersPoint, String playerType) {
        int bonusPoint = 0;
        int dice = rnd.nextInt(6) + 1;
        System.out.println(playerType + " rolled a dice: " + dice);

        if (dice == 1) {
            halvePoints(monstersPoint);
        } else if (dice == 6) {
            doublePoints(monstersPoint);
        } else {
            bonusPoint = dice;
        }
        return bonusPoint;
    }

    private void halvePoints(List<Integer> points) {
        points.replaceAll(point -> point / 2);
    }

    private void doublePoints(List<Integer> points) {
        points.replaceAll(point -> point * 2);
    }

    private int calculateTotalPoints(List<Integer> points, int bonus) {
        return points.stream().mapToInt(Integer::intValue).sum() + bonus;
    }

    private void battle(int playerTotal, int cpuTotal) {
        int pointDifference = Math.abs(playerTotal - cpuTotal);

        if (playerTotal > cpuTotal) {
            cpuHp -= pointDifference;
        } else if (cpuTotal > playerTotal) {
            playerHp -= pointDifference;
        }

        recordHistory();
        displayHp();
    }

    private void recordHistory() {
        playerHistory.add(playerHp);
        cpuHistory.add(cpuHp);
    }

    private void displayHp() {
        System.out.println("Player HP: " + playerHp);
        System.out.println("CPU HP: " + cpuHp);
    }
}

