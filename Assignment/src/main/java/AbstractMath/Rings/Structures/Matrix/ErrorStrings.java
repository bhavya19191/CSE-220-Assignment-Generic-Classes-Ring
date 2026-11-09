package AbstractMath.Rings.Structures.Matrix;

public enum ErrorStrings {
    NONPOSITIVE_DIMENSIONS("Matrix dimensions must be greater than zero"),
    NOT_SQUARE("A ring matrix must be square"),
    NULL_VALUES("Default values must not be null"),
    NULL_ROW("Default row must not be null"),
    ROW_COUNT("Default values must match the number of rows"),
    COLUMN_COUNT("Each default row must match the number of columns"),
    NULL_ENTRY("Matrix entries must not be null"),
    INDEX_OUT_OF_BOUNDS("Matrix coordinates are outside the fixed dimensions"),
    NULL_OPERAND("Other matrix must not be null"),
    SIZE_MISMATCH("The matrices must have the same size"),
    INCOMPLETE("All matrix entries must be set before building"),
    MIXED_ENTRY_TYPES("All matrix entries must have the same nested type"),
    INCOMPATIBLE_TYPE("Matrices can only be compared to matrices with the same nested entry type");

    private final String message;

    ErrorStrings(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}