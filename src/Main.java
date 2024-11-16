import java.util.ArrayList;
import java.util.List;

public class Main {
    static RedFantasy rf = new RedFantasy();

    public static void main(String[] args) {
        setMonsters();

        while (true) {
            try {
                if (rf.getPlayerHp() > 0 && rf.getCpuHp() > 0) {
                    Thread.sleep(3000);
                    rf.startPhase();
                } else {
                    displayResult();
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        displayHistory("Player History:", rf.getPlayerHistory());
        displayHistory("CPU History:", rf.getCpuHistory());
    }

    public static void setMonsters() {
        List<String> tempMonsters = List.of(
            "イガキン", "ナマチュウ", "イノウエン", "リョージィ", "アキモトン", "ゴージマ", "チュウデン",
            "ヅカホン", "ニシムラー", "サコーデン", "ウッチー", "ハヤッシー", "キーチー", "リョクン",
            "デコポン", "カミサン", "シスイ", "ジョナ", "ギダギダ", "ミッツー", "ゾエサン", "キタバー"
        );
        List<Integer> tempMonstersPoint = List.of(
            9, 3, 1, 2, 5, 4, 6, 8, 7, 2, 5, 4, 3, 1, 6, 5, 1, 7, 2, 8, 5, 3
        );
        rf.setMonsterZukan(tempMonsters);
        rf.setMonstersPoint(tempMonstersPoint);
    }

    private static void displayResult() {
        if (rf.getPlayerHp() <= 0) {
            System.out.println("Playerは死んでしまった");
        } else if (rf.getCpuHp() <= 0) {
            System.out.println("CPUは死んでしまった");
        }
    }

    private static void displayHistory(String title, List<Integer> history) {
        System.out.println(title);
        history.stream()
            .filter(hp -> hp != -9999)
            .forEach(hp -> System.out.print(hp + "\\t"));
        System.out.println();
    }
}

