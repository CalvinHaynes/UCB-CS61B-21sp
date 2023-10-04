/** Class that prints the Collatz sequence starting from a given number.
 * Collatz sequence（也称为Collatz problem、3n + 1 problem）是一个数学问题，
 * 它以德国数学家Lothar Collatz的名字命名。问题的描述如下：
 * 1. 选择任何正整数 n 作为初始值。
 * 2. 如果 n 是偶数，则将 n 除以 2。
 * 3. 如果 n 是奇数，则将 n 乘以 3 并加 1。
 * 4. 重复步骤 2 和 3，直到 n 变为 1。
 * Collatz sequence 是从初始值开始按照上述规则生成的一系列整数。
 * 这个问题的有趣之处在于，无论选择哪个正整数作为初始值，最终都会得到 n = 1，
 * 并进入一个循环：1 → 4 → 2 → 1。这个问题虽然简单，但在数学界引发了广泛的研究和讨论。
 * Collatz sequence 也被称为“3n + 1 问题”，因为它的规则可以表示为一个递归公式：
 * 如果 n 是奇数，下一个数是 3n + 1；如果 n 是偶数，下一个数是 n / 2。
 * Collatz sequence 在计算机科学中也常被用作算法性能测试的一部分，因为它可以产生复杂的序列，涉及到奇数和偶数的交替变化。
 * 虽然它是一个简单的问题，但它的行为仍然具有许多未解决的数学谜题。
 *  @author CalvinHaynes
 */
public class Collatz {

    public static int nextNumber(int n) {
        if (n % 2 == 0 ){
            return n / 2;
        }else if (n == 1){
            return n;
        }else {
            return 3 * n + 1;
        }
    }

    public static void main(String[] args) {
        int n = 5;  // Set n equal to any positive integer.
        System.out.print(n + " ");
        while (n != 1) {
            n = nextNumber(n);
            System.out.print(n + " ");
        }
        System.out.println();
    }
}

