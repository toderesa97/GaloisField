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

    @Test
    public void multiplication_tests() {
        assertArrayEquals(new int[]{0,0,0,2,4}, Operating.multiply(3,4,new int[]{1,0,1},
                new int[]{0,0,0,2}, new int[]{1,2}));
        assertArrayEquals(new int[]{0,0,4,0,0,0,9,0,0,0,5}, Operating.multiply(7,7,new int[]{1,0,1},
                new int[]{0,0,4,0,0,0,5}, new int[]{1,0,0,0,1}));
    }

    @Test
    public void division_tests () {
        assertArrayEquals(null, Operating.divide(2,4,new int[]{1,1,1}, new int[]{1,0,1,1}));
        assertArrayEquals(new int[]{1,1}, Operating.divide(2,4,new int[]{1,1,1,1}, new int[]{1,0,1}));
        assertArrayEquals(new int[]{0,0,0,0,1}, Operating.divide(2,6,new int[]{1,0,0,0,0,1}, new int[]{0,1}));
        assertArrayEquals(new int[]{2,2}, Operating.divide(7,3,new int[]{-2,6,2}, new int[]{2,1}));
    }

}
