package AbstractMath.Rings.Structures.Polynomial;

import AbstractMath.Rings.RingElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Polynomial<T extends RingElement<T>>
        implements RingElement<Polynomial<T>> {

    private final ArrayList<T> coefficients;

    public Polynomial(int degree, List<T> coefficients) {
        if (degree < 0 || degree == Integer.MAX_VALUE) {
            throw new IllegalArgumentException(
                    ErrorStrings.INVALID_DEGREE.getMessage());
        }

        Objects.requireNonNull(coefficients,
                ErrorStrings.NULL_COEFFICIENTS.getMessage());

        if (coefficients.isEmpty()) {
            throw new IllegalArgumentException(
                    ErrorStrings.EMPTY_COEFFICIENTS.getMessage());
        }

        if (coefficients.size() != degree + 1) {
            throw new IllegalArgumentException(
                    ErrorStrings.COEFFICIENT_COUNT.getMessage());
        }

        validateCoefficients(coefficients);

        this.coefficients = new ArrayList<>(coefficients);
        trimTrailingZeros();
    }

    public Polynomial(List<T> coefficients) {
        Objects.requireNonNull(coefficients,
                ErrorStrings.NULL_COEFFICIENTS.getMessage());

        if (coefficients.isEmpty()) {
            throw new IllegalArgumentException(
                    ErrorStrings.EMPTY_COEFFICIENTS.getMessage());
        }

        if (coefficients.size() == Integer.MAX_VALUE) {
            throw new IllegalArgumentException(
                    ErrorStrings.INVALID_DEGREE.getMessage());
        }

        validateCoefficients(coefficients);

        this.coefficients = new ArrayList<>(coefficients);
        trimTrailingZeros();
    }

    private static <T extends RingElement<T>> void validateCoefficients(
            List<T> coefficients) {

        T first = Objects.requireNonNull(
                coefficients.get(0),
                ErrorStrings.NULL_COEFFICIENT.getMessage());

        for (T coefficient : coefficients) {
            Objects.requireNonNull(
                    coefficient,
                    ErrorStrings.NULL_COEFFICIENT.getMessage());

            if (!first.hasSameType(coefficient)) {
                throw new IllegalArgumentException(
                        ErrorStrings.MIXED_COEFFICIENT_TYPES.getMessage());
            }
        }
    }

    private void trimTrailingZeros() {
        while (coefficients.size() > 1) {
            T last = coefficients.get(coefficients.size() - 1);

            if (!last.equals(last.zero())) {
                break;
            }

            coefficients.remove(coefficients.size() - 1);
        }
    }

    public static <T extends RingElement<T>> Builder<T> builder(int degree) {
        return new Builder<>(degree);
    }

    public int degree() {
        return coefficients.size() - 1;
    }

    public T coefficient(int degree) {
        if (degree < 0) {
            throw new IllegalArgumentException(
                    ErrorStrings.NEGATIVE_TERM_DEGREE.getMessage());
        }

        if (degree > this.degree()) {
            return coefficients.get(0).zero();
        }

        return coefficients.get(degree);
    }

    public T getValue(int degree) {
        return coefficient(degree);
    }

    @Override
    public boolean hasSameType(Object other) {
        return other instanceof Polynomial<?> polynomial
                && coefficients.get(0).hasSameType(
                polynomial.coefficients.get(0));
    }

    @Override
    public Polynomial<T> add(Polynomial<T> other) {
        Objects.requireNonNull(other,
                ErrorStrings.NULL_OPERAND.getMessage());

        int resultDegree = Math.max(this.degree(), other.degree());
        List<T> result = new ArrayList<>();

        for (int i = 0; i <= resultDegree; i++) {
            result.add(this.coefficient(i).add(other.coefficient(i)));
        }

        return new Polynomial<>(result);
    }

    @Override
    public Polynomial<T> negate() {
        List<T> result = new ArrayList<>();

        for (T coefficient : coefficients) {
            result.add(coefficient.negate());
        }

        return new Polynomial<>(result);
    }

    @Override
    public Polynomial<T> multiply(Polynomial<T> other) {
        Objects.requireNonNull(other,
                ErrorStrings.NULL_OPERAND.getMessage());

        List<T> result = new ArrayList<>();

        int resultSize = this.degree() + other.degree() + 1;

        for (int i = 0; i < resultSize; i++) {
            result.add(coefficients.get(0).zero());
        }

        for (int i = 0; i < coefficients.size(); i++) {
            for (int j = 0; j < other.coefficients.size(); j++) {
                T product = coefficients.get(i)
                        .multiply(other.coefficients.get(j));

                result.set(i + j, result.get(i + j).add(product));
            }
        }

        return new Polynomial<>(result);
    }

    @Override
    public Polynomial<T> zero() {
        return new Polynomial<>(List.of(coefficients.get(0).zero()));
    }

    @Override
    public Polynomial<T> one() {
        return new Polynomial<>(List.of(coefficients.get(0).one()));
    }

    @Override
    public boolean equals(Object other) {
        if (!hasSameType(other)) {
            throw new ClassCastException(
                    ErrorStrings.INCOMPATIBLE_TYPE.getMessage());
        }

        Polynomial<?> polynomial = (Polynomial<?>) other;

        if (this.coefficients.size() != polynomial.coefficients.size()) {
            return false;
        }

        for (int i = 0; i < coefficients.size(); i++) {
            if (!coefficients.get(i).equals(
                    polynomial.coefficients.get(i))) {
                return false;
            }
        }

        return true;
    }
}