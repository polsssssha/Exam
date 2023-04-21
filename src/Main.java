import java.util.function.BiFunction;
import java.util.function.BiPredicate;


public class Main {
    public static void main(String[] args) {
        firstTask();
        secondTask();
        thirdTask();

    }
    private static void firstTask() {
        BiPredicate<Integer, Integer> condition = Integer::equals;
        BiFunction<Integer, Integer, String> ifTrue = (a, b) -> "Equal";
        BiFunction<Integer, Integer, String> ifFalse = (a, b) -> "Not equal";
        System.out.println(ternaryOperator(condition, ifTrue, ifFalse).apply(5, 5));
    }

    private static void secondTask() {
        BiPredicate<String, String> condition = String::contains;
        BiFunction<String, String, String> ifTrue = (a, b) -> a.replace(b, "");
        BiFunction<String, String, String> ifFalse = (a, b) -> a + b;
        System.out.println(ternaryOperator(condition, ifTrue, ifFalse).apply("wasd", "as"));
    }

    private static void thirdTask() {
        BiPredicate<Integer, Integer> condition = (a, b) -> a % b == 0;
        BiFunction<Integer, Integer, Integer> ifTrue = Integer::sum;
        BiFunction<Integer, Integer, Integer> ifFalse = (a, b) -> {
            int sum = a + b;
            int result = 0;
            while (sum > 0) {
                result += sum % 10;
                sum /= 10;
            }

            return result;
        };
        System.out.println(ternaryOperator(condition, ifTrue, ifFalse).apply(14, 5));
    }

    private static <F, S, R> BiFunction<F, S, R> ternaryOperator(
            BiPredicate<? super F, ? super S> condition,
            BiFunction<? super F, ? super S, ? extends R> ifTrue,
            BiFunction<? super F, ? super S, ? extends R> ifFalse) {
        if (condition == null
                || ifFalse == null
                || ifTrue == null) {
            throw new NullPointerException();
        }
        return (a, b) -> condition.test(a, b) ? ifTrue.apply(a, b) : ifFalse.apply(a, b);
    }
}