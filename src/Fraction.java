public class Fraction implements Comparable<Fraction> {

    public static final Fraction ZERO = new Fraction(0, 1);
    public static final Fraction POSITIVE_INFINITE = new Fraction(1, 0);
    public static final Fraction NEGATIVE_INFINITE = new Fraction(-1, 0);

    private final int numerator;
    private final int denominator;

    public Fraction(int numerator, int denominator) {
        if (numerator == 0 && denominator == 0) {
            throw new IllegalArgumentException("Numerator and denominator can't both be 0!");
        } else if (denominator == 0) {  // infinite
            this.numerator = numerator < 0 ? -1 : 1;
            this.denominator = 0;
        } else if (numerator == 0) {  // zero
            this.numerator = 0;
            this.denominator = 1;
        } else {
            if (denominator < 0) {
                numerator = -numerator;
                denominator = -denominator;
            }
            int gcd = gcd(numerator, denominator);
            this.numerator = numerator / gcd;
            this.denominator = denominator / gcd;
        }
    }

    public Fraction add(Fraction f) {
        if (isInfinite() && f.isInfinite()) {
            if ((isPositive() && f.isPositive()) || (isNegative() && f.isNegative())) return this;
            else return ZERO;
        }
        int gcd = gcd(denominator, f.denominator);
        int lcm = denominator / gcd * f.denominator;
        int num = numerator * (f.denominator / gcd) + f.numerator * (denominator / gcd);
        return new Fraction(num, lcm);
    }

    public Fraction subtract(Fraction f) {
        return add(f.opposite());
    }

    public Fraction multiply(Fraction f) {
        int num = numerator * f.numerator;
        int den = num == 0 ? 1 : denominator * f.denominator;
        return new Fraction(num, den);
    }

    public Fraction divide(Fraction f) {
        return multiply(f.reciprocal());
    }

    public Fraction opposite() {
        return new Fraction(-numerator, denominator);
    }

    public Fraction reciprocal() {  // NegativeInfinite's reciprocal's reciprocal is PositiveInfinite
        return new Fraction(denominator, numerator);
    }

    public boolean isInfinite() {
        return denominator == 0;
    }
    
    public boolean isZero() {
        return numerator == 0;
    }

    public boolean isPositive() {
        return numerator > 0;
    }

    public boolean isNegative() {
        return numerator < 0;
    }

    @Override
    public int compareTo(Fraction that) {
        if (this.equals(that)) return 0;
        else if (this.numerator < 0 && that.numerator > 0) return -1;
        else if (this.numerator > 0 && that.numerator < 0) return 1;
        else if ((this.isInfinite() && this.isPositive()) || (that.isInfinite() && that.isNegative())) return 1;
        else if ((this.isInfinite() && this.isNegative()) || (that.isInfinite() && that.isPositive())) return -1;
        int gcd = gcd(this.denominator, that.denominator);
        return Integer.compare(this.numerator * (that.denominator / gcd),
                that.numerator * (this.denominator / gcd));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fraction that = (Fraction) o;
        return this.numerator == that.numerator &&
                this.denominator == that.denominator;
    }

    @Override
    public int hashCode() {
        return 31 * numerator + denominator;
    }

    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }

    public static int gcd(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        while (a != 0 && b != 0) {
            if (a > b) a %= b;
            else b %= a;
        }
        return a == 0 ? b : a;
    }

    public static void main(String[] args) {
        Fraction[] fs = new Fraction[5];
        fs[0] = new Fraction(2, 6);  // 1/3
        fs[1] = new Fraction(-1, 2);  // -1/2
        fs[2] = new Fraction(0, -9);  // zero
        fs[3] = new Fraction(3, 0);  // PositiveInfinite
        fs[4] = new Fraction(-5, 0);  // NegativeInfinite

        String answer, result;


        answer = " -1/3  1/2  0/1 -1/0  1/0";
        result = "";
        for (Fraction f : fs) {
            f = f.opposite();
            result += (f.isNegative() ? " " : "  ") + f;
        }
        System.out.println("---------opposite---------" + answer.equals(result));
        System.out.println(result);
        System.out.println();


        answer = "  3/1 -2/1  1/0  0/1  0/1";
        result = "";
        for (Fraction f : fs) {
            f = f.reciprocal();
            result += (f.isNegative() ? " " : "  ") + f;
        }
        System.out.println("--------reciprocal--------" + answer.equals(result));
        System.out.println(result);
        System.out.println();


        answer = "  2/3 -1/6  1/3  1/0 -1/0\n" +
                 " -1/6 -1/1 -1/2  1/0 -1/0\n" +
                 "  1/3 -1/2  0/1  1/0 -1/0\n" +
                 "  1/0  1/0  1/0  1/0  0/1\n" +
                 " -1/0 -1/0 -1/0  0/1 -1/0\n";
        result = "";
        for (Fraction f1 : fs) {
            for (Fraction f2 : fs) {
                Fraction f3 = f1.add(f2);
                result += (f3.isNegative() ? " " : "  ") + f3;
            }
            result += "\n";
        }
        System.out.println("-----------add-----------" + answer.equals(result));
        System.out.println(result);


        answer = "  2/3 -1/6  1/3  1/0 -1/0\n" +
                 " -1/6 -1/1 -1/2  1/0 -1/0\n" +
                 "  1/3 -1/2  0/1  1/0 -1/0\n" +
                 "  1/0  1/0  1/0  1/0  0/1\n" +
                 " -1/0 -1/0 -1/0  0/1 -1/0\n";
        result = "";
        for (Fraction f1 : fs) {
            for (Fraction f2 : fs) {
                Fraction f3 = f1.multiply(f2);
                result += (f3.isNegative() ? " " : "  ") + f3;
            }
            result += "\n";
        }
        System.out.println("---------multiply---------" + answer.equals(result));
        System.out.println(result);
    }
}
