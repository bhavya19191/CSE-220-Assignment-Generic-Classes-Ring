package AbstractMath.Rings.Structures.Polynomial;

public enum ErrorStrings {
    INVALID_DEGREE("Degree must be nonnegative and degree + 1 must fit in an int"),
    NULL_COEFFICIENTS("Coefficients must not be null"),
    EMPTY_COEFFICIENTS("Supply at least the constant coefficient"),
    COEFFICIENT_COUNT("Supply exactly degree + 1 coefficients"),
    NULL_COEFFICIENT("Coefficients must not contain null"),
    NEGATIVE_TERM_DEGREE("Degree must not be negative"),
    TERM_OUT_OF_BOUNDS("Term degree is above the builder's fixed degree"),
    LEADING_ZERO("The leading coefficient cannot be zero because the degree would be reduced"),
    NULL_OPERAND("Other polynomial must not be null"),
    INCOMPLETE("All polynomial coefficients must be set before building"),
    MIXED_COEFFICIENT_TYPES("All polynomial coefficients must have the same nested type"),
    INCOMPATIBLE_TYPE("Polynomials can only be compared to polynomials with the same nested coefficient type");

    private final String message;

    ErrorStrings(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}