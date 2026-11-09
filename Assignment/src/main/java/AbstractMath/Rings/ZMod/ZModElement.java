package AbstractMath.Rings.ZMod;

import AbstractMath.Rings.RingElement;

public class ZModElement<M extends Modulus>
        implements RingElement<ZModElement<M>> {

    private final int value;
    private final M modulus;

    public ZModElement(int value, M modulus) {

        if (modulus == null) {
            throw new NullPointerException(
                    ErrorStrings.NULL_MODULUS.getMessage());
        }

        if (modulus.value() <= 0 || modulus.value() >= 1000) {
            throw new IllegalArgumentException(
                    ErrorStrings.INVALID_MODULUS.getMessage());
        }

        if (value >= 1000) {
            throw new IllegalArgumentException(
                    ErrorStrings.INVALID_VALUE.getMessage());
        }

        this.modulus = modulus;

        int normalized = value % modulus.value();

        if (normalized < 0) {
            normalized += modulus.value();
        }

        this.value = normalized;
    }

    public int getValue() {
        return value;
    }

    public int getModulus() {
        return modulus.value();
    }

    @Override
    public boolean hasSameType(Object other) {

        if (!(other instanceof ZModElement<?> otherElement)) {
            return false;
        }

        return getClass() == otherElement.getClass()
                && modulus.getClass() == otherElement.modulus.getClass()
                && getModulus() == otherElement.getModulus();
    }

    @Override
    public ZModElement<M> add(ZModElement<M> other) {

        if (other == null) {
            throw new NullPointerException(
                    ErrorStrings.NULL_OPERAND.getMessage());
        }

        if (getModulus() != other.getModulus()) {
            throw new IllegalArgumentException(
                    ErrorStrings.MODULUS_MISMATCH.getMessage());
        }

        return new ZModElement<>((value + other.value) % getModulus(), modulus);
    }

    @Override
    public ZModElement<M> negate() {
        return new ZModElement<>(-value, modulus);
    }

    @Override
    public ZModElement<M> multiply(ZModElement<M> other) {

        if (other == null) {
            throw new NullPointerException(
                    ErrorStrings.NULL_OPERAND.getMessage());
        }

        if (getModulus() != other.getModulus()) {
            throw new IllegalArgumentException(
                    ErrorStrings.MODULUS_MISMATCH.getMessage());
        }

        return new ZModElement<>((value * other.value) % getModulus(), modulus);
    }

    @Override
    public ZModElement<M> zero() {
        return new ZModElement<>(0, modulus);
    }

    @Override
    public ZModElement<M> one() {
        return new ZModElement<>(1, modulus);
    }

    @Override
    public boolean equals(Object other) {

        if (!(other instanceof ZModElement<?> otherElement)) {
            throw new ClassCastException(
                    ErrorStrings.INCOMPATIBLE_TYPE.getMessage());
        }

        if (getModulus() != otherElement.getModulus()) {
            throw new IllegalArgumentException(
                    ErrorStrings.MODULUS_MISMATCH.getMessage());
        }

        if (!hasSameType(other)) {
            throw new ClassCastException(
                    ErrorStrings.MODULUS_TYPE_MISMATCH.getMessage());
        }

        return this.value == otherElement.value;
    }
}