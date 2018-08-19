import java.util.Map;
import java.util.Scanner;

public class StartGenerator {
    private static Integer size;
    private static void sisetamine(){
        System.out.println("Kui faili suurust tahate muuta, siis sisestage 'yes', kui ei, siis sisestage suvalt midagi!");
        Scanner sc = new Scanner(System.in);
        String sisend = sc.next();
        if (sisend.equals("yes")) {
            System.out.println("Sisestage faili suurus - mb");
            size = sc.nextInt();
        }
        else size = null;

    }
    private static void andmed(String fileName) throws Exception {
        Main main = new Main("ee.txt",size);
        Map<Object,Long> map = main.getMap();
        System.out.println("graph\n");
        Long length = map.entrySet().stream().findFirst().get().getValue();
        for (long x = length; x > 0; x--){
            for (Map.Entry<Object,Long> entry : map.entrySet()){
                if (x <= entry.getValue()) System.out.print("x");
                else System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("graph values\n" + map + "\n");
        System.out.println("prime " + main.getPrime() + "\n");
        System.out.println("armstrong " + main.getArmstrong() + "\n");
        System.out.println("time " + main.getTime());


    }
    public static void main(String[] args) throws Exception {
        if (args.length != 1) throw new RuntimeException("Sisestage failinimi!");
        sisetamine();
        andmed(args[0]);
    }
}
