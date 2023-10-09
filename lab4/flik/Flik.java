package flik;

/** An Integer tester created by Flik Enterprises.
 * @author Josh Hug
 * */
public class Flik {
    /** @param a Value 1
     *  @param b Value 2
     *  @return Whether a and b are the same */
    public static boolean isSameNumber(Integer a, Integer b) {
        return a.equals(b);
    }
}

/**
 * 在 Java 中，`Integer` 类型的对象在一定范围内的整数值（通常为 -128 到 127）会被缓存，以提高性能和减少内存占用。因此，当你创建 `Integer` 对象并赋予它们在这个范围内的值时，它们将引用相同的对象，因此 `a == b` 会返回 `true`。
 *
 * 但是，当 `a` 和 `b` 超出这个范围时，例如都为 `128`，它们将不再引用同一个缓存对象，而是分别创建新的 `Integer` 对象，因此 `a == b` 会返回 `false`。这是因为 `==` 运算符比较的是对象的引用是否相同，而不是它们的值。
 *
 * 如果要比较两个 `Integer` 对象的值是否相等，应该使用 `equals` 方法，如下所示：
 *
 * ```java
 * public static boolean isSameNumber(Integer a, Integer b) {
 *     return a.equals(b);
 * }
 * ```
 *
 * 这将比较 `a` 和 `b` 的值，而不仅仅是它们的引用。
 */