import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Operating {

    public static void main(String[] args) {
        int r [] = divide(5,7,new int[]{1,1,1,1}, new int[]{1,0,1});
        System.out.println(Arrays.toString(r));


    }

    /**
     * Addition of two elements belonging to the galois field p of m GF(p^m)
     * @return the addition divide p
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
     * @param polynomial must be an irreducible polynomial
     * @return the remainder of the product a and b with p
     */
    public static int[] multiply (int p, int m, int[] polynomial, int[] a, int[] b) {
        if (a.length > m || b.length > m || polynomial.length > m) {
            return null; // no multiplication is possible using elements out of GF(p^m)
        }

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

    /**
     * @return the quotient of dividend over divisor
     */
    public static int[] divide(int p, int m, int[] dividend, int[] divisor) {
        if (divisor.length > dividend.length) return null;
        int j = 0;
        int[] quotient = new int[dividend.length];
        while (dividend.length >= divisor.length) {
            int i = dividend.length-divisor.length;
            int[] temp = new int[i+1];
            if (dividend[i] < 0) {
                quotient[i] = (-1)*dividend[dividend.length-1];
            } else {
                quotient[i] = dividend[dividend.length-1];
            }
            temp[i] = quotient[i];
            int[]  signsInverted= changeSigns(multiply(p, m, new int[]{1}, divisor, temp));
            dividend = removeZeros(add(p, m, signsInverted, dividend));
        }
        return removeZeros(quotient);
    }

    private static int[] changeSigns(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] *= (-1);
        }
        return arr;
    }

    private static int[] removeZeros (int[] arr) {
        if (arr[arr.length-1] != 0) return arr;

        for (int i = arr.length-1; i >= 0 ; i--) {
            if (arr[i] != 0) {
                int[] r = new int[i+1];
                for (int j = 0; j < r.length; j++) {
                    r[j] = arr[j];
                }
                return r;
            }
        }
        return new int[]{0};
    }
}
