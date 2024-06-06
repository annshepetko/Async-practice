import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {

        HashSet<Fraction> fractions = new HashSet<>();

        fractions.add(new Fraction(1,2));
        fractions.add(new Fraction(2,4));

        System.out.println(fractions.size());

        System.out.println(2/4);

    }

}

class Fraction{
    private int numerator;
    private int denominator;

    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public int getNumerator() {
        return numerator;
    }

    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public void setDenominator(int denominator) {
        this.denominator = denominator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fraction fraction = (Fraction) o;
        return this.numerator * fraction.denominator == this.denominator * fraction.numerator;
    }

    @Override
    public int hashCode() {
        // Спрощення дробу перед генерацією хеш-коду, щоб еквівалентні дроби мали однаковий хеш-код
        int gcd = gcd(numerator, denominator);
        return Objects.hash(numerator / gcd, denominator / gcd);
    }

    // Метод для знаходження найбільшого спільного дільника (НСД)
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

}