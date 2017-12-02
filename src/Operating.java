import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Operating {

    public static void main(String[] args) {
        int r[] = multiply(null, new int[]{0,0,1}, new int[]{1,0,0,1});
        System.out.println(Arrays.toString(r));
    }

    /**
     * Addition of two elements belonging to the galois field p of m GF(p^m)
     * @return the addition modulo p
     */
    public static int[] add (int p, int m, int[] a, int[] b) {
        int min;
        int[] max;
        if (a.length > m || b.length > m) {
            return null; // no addition is possible using elements out of GF(p^m)
        }
        if (a.length > b.length) {
            min = b.length;
            max = a;
        } else {
            min = a.length;
            max = b;
        }
        int sum[] = new int[max.length];
        for (int i = 0; i < min; i++) {
            sum[i] = Math.floorMod(a[i]+b[i], p);
        }
        for (int i = min; i < max.length; i++) {
            sum[i] = (max[i]);
        }
        return sum;
    }

    public static int[] subtract (int p, int m, int[] a, int[] b) {
        int min;
        int[] max;
        if (a.length > m || b.length > m) {
            return null; // no addition is possible using elements out of GF(p^m)
        }
        boolean isSecond = false;
        if (a.length > b.length) {
            min = b.length;
            max = a;
        } else {
            min = a.length;
            max = b;
            isSecond = true;
        }
        int sum[] = new int[max.length];
        for (int i = 0; i < min; i++) {
            sum[i] = Math.floorMod(a[i]-b[i], p);
        }
        for (int i = min; i < max.length; i++) {
            sum[i] = Math.floorMod(isSecond ? -max[i] : max[i], p);
        }
        return sum;
    }

    /**
     *
     * @param p must be an irreducible polynomial
     * @return the remainder of the product a and b with p
     */
    public static int[] multiply (int[] p, int[] a, int[] b) {
        Map<Integer, Integer> map = new HashMap<>(); // (grade, value)
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                if (map.containsKey(i+j)) {
                    map.put(i+j, map.get(i+j)+(a[i]*b[j]));
                } else {
                    map.put(i+j, (a[i]*b[j]));
                }
            }
        }
        int[] result = new int[a.length+b.length-1];
        for (int i = 0; i < result.length; i++) {
            result[i] = map.get(i);
        }
        return result;
    }
}
