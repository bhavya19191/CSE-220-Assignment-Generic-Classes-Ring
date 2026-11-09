package AbstractMath.Rings.ZMod;

public enum ErrorStrings {

    NULL_MODULUS("modulus must not be null"),
    INVALID_MODULUS("modulus must be greater than zero and less than 1,000"),
    INVALID_VALUE("value must be less than 1,000"),
    NULL_OPERAND("Other ZModElement must not be null"),
    MODULUS_MISMATCH("The modulus value of the two operands must be equal"),
    INCOMPATIBLE_TYPE("Cannot compare ZModElements with different types"),
    MODULUS_TYPE_MISMATCH("ZModElements can only be compared to ZModElements with the same modulus type");

    private final String message;

    ErrorStrings(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}