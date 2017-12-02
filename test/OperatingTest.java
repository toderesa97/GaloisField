import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;

public class OperatingTest {

    @Test
    public void addition_tests () {
        assertArrayEquals(new int[]{1,1}, Operating.add(2,3,new int[]{1}, new int[] {0,1}));
        assertArrayEquals(new int[]{1,0,1}, Operating.add(2,3,new int[]{1,1}, new int[] {0,1,1}));
        assertArrayEquals(new int[]{0,2,0,1,1,2}, Operating.add(3,7,new int[]{1,0,1,2},
                new int[] {2,2,2,2,1,2}));

    }

    @Test
    public void subtract_tests () {
        assertArrayEquals(new int[]{1,1}, Operating.subtract(2,3,new int[]{1}, new int[] {0,1}));
        assertArrayEquals(new int[]{1,0,1}, Operating.subtract(2,3,new int[]{1,1}, new int[] {0,1,1}));
        assertArrayEquals(new int[]{2,1,2,0,2,1}, Operating.subtract(3,7,new int[]{1,0,1,2},
                new int[] {2,2,2,2,1,2}));
        assertArrayEquals(new int[]{0,0,2,2}, Operating.subtract(3,4, new int[]{0,0,2,1},
                new int[]{0,0,0,2}));
    }


}
