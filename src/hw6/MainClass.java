import java.util.Arrays;

public class MainClass {
    public static int[] array4(int[] a) throws RuntimeException {
        int x = -1;
        for (int i = a.length-1; i >= 0; i--) {
            if (a[i] == 4) {
                x = i;
                break;
            }
        }
        if (x == -1) throw new RuntimeException("Missing number 4");
        return Arrays.copyOfRange(a,x+1,a.length);
    }

    public static boolean no14(int[] a)  {
        boolean x1 = false, x4 = false;
        for (int i = 0; i < a.length ; i++) {
            if (a[i] == 1) x1 = true;
            if (a[i] == 4) x4 = true;
            if (x1 && x4 ) break;
        }
        return (x1 && x4);
    }
    public static void main(String[] args) {
        int[] a = new int[]{0,1,2,3,4,5,4};
        System.out.println(Arrays.toString(MainClass.array4(a)));
        int[] b = new int[]{1,4,4,1,1};
        System.out.println(MainClass.no14(b));
    }
}
