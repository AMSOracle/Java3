import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.testng.Assert;
import java.util.Arrays;
import java.util.Collection;


@RunWith(Parameterized.class)
public class ParametrizedTest {

    private int[] valueA;
    private int[] valueB;
    private int[] resA;
    private boolean resB;

    public ParametrizedTest (int[] a, int[] res1, int[] b, boolean res2){
        this.valueA = a;
        this.resA = res1;
        this.valueB = b;
        this.resB = res2;
    }

    @Parameterized.Parameters
    public static Collection par(){
        return Arrays.asList(new Object[][]{
                {new int[]{1,2,3,4,5},new int[]{5}, new int[]{1,1,1,1}, false},
                {new int[]{1,2,4,5,5},new int[]{5,5}, new int[]{4,4,4,4}, false},
                {new int[]{1,4,3,4,5},new int[]{5}, new int[]{1,4,1,1}, true},
                {new int[]{1,2,3,4,4},new int[]{}, new int[]{1,1,1,4}, true},
        });
    }
    @Test
    public void array4() {
        Assert.assertEquals(MainClass.array4(valueA),resA);
    }

    @Test
    public void no14() {
        Assert.assertEquals(MainClass.no14(valueB),resB);
    }
}