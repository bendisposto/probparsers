package de.prob.translator.types;

public class Boolean implements BObject {
    private final java.lang.Boolean value;

    /**
     * Parses the string argument as a boolean.  The {@code boolean}
     * returned represents the value {@code true} if the string argument
     * is not {@code null} and is equal, ignoring case, to the string
     * {@code "true"}. <p>
     * Example: {@code Boolean.parseBoolean("True")} returns {@code true}.<br>
     * Example: {@code Boolean.parseBoolean("yes")} returns {@code false}.
     *
     * @param      s   the {@code String} containing the boolean
     *                 representation to be parsed
     * @return the boolean represented by the string argument
     * @since 1.5
     */
    public static boolean parseBoolean(java.lang.String s) {
        return java.lang.Boolean.parseBoolean(s);
    }

    /**
     * Returns a {@code java.lang.String} object representing the specified
     * boolean.  If the specified boolean is {@code true}, then
     * the string {@code "true"} will be returned, otherwise the
     * string {@code "false"} will be returned.
     *
     * @param b the boolean to be converted
     * @return the string representation of the specified {@code boolean}
     * @since 1.4
     */
    public static java.lang.String toString(boolean b) {
        return java.lang.Boolean.toString(b);
    }

    /**
     * Returns a {@code Boolean} instance representing the specified
     * {@code boolean} value.  If the specified {@code boolean} value
     * is {@code true}, this method returns {@code Boolean.TRUE};
     * if it is {@code false}, this method returns {@code Boolean.FALSE}.
     * If a new {@code Boolean} instance is not required, this method
     * should generally be used in preference to the constructor
     * {@link #Boolean(boolean)}, as this method is likely to yield
     * significantly better space and time performance.
     *
     * @param  b a boolean value.
     * @return a {@code Boolean} instance representing {@code b}.
     * @since 1.4
     */
    public static java.lang.Boolean valueOf(boolean b) {
        return java.lang.Boolean.valueOf(b);
    }

    /**
     * Compares this {@code Boolean} instance with another.
     *
     * @param   b the {@code Boolean} instance to be compared
     * @return zero if this object represents the same boolean value as the
     *          argument; a positive value if this object represents true
     *          and the argument represents false; and a negative value if
     *          this object represents false and the argument represents true
     * @throws NullPointerException if the argument is {@code null}
     * @see     Comparable
     * @since 1.5
     */
    public int compareTo(java.lang.Boolean b) {
        return value.compareTo(b);
    }

    /**
     * Returns a {@code Boolean} with a value represented by the
     * specified string.  The {@code Boolean} returned represents a
     * true value if the string argument is not {@code null}
     * and is equal, ignoring case, to the string {@code "true"}.
     *
     * @param   s   a string.
     * @return the {@code Boolean} value represented by the string.
     */
    public static java.lang.Boolean valueOf(java.lang.String s) {
        return java.lang.Boolean.valueOf(s);
    }

    /**
     * Compares two {@code boolean} values.
     * The value returned is identical to what would be returned by:
     * <pre>
     *    Boolean.valueOf(x).compareTo(Boolean.valueOf(y))
     * </pre>
     *
     * @param  x the first {@code boolean} to compare
     * @param  y the second {@code boolean} to compare
     * @return the value {@code 0} if {@code x == y};
     *         a value less than {@code 0} if {@code !x && y}; and
     *         a value greater than {@code 0} if {@code x && !y}
     * @since 1.7
     */
    public static int compare(boolean x, boolean y) {
        return java.lang.Boolean.compare(x, y);
    }

    /**
     * Returns {@code true} if and only if the system property
     * named by the argument exists and is equal to the string
     * {@code "true"}. (Beginning with version 1.0.2 of the
     * Java<small><sup>TM</sup></small> platform, the test of
     * this string is case insensitive.) A system property is accessible
     * through {@code getProperty}, a method defined by the
     * {@code System} class.
     * <p>
     * If there is no property with the specified name, or if the specified
     * name is empty or null, then {@code false} is returned.
     *
     * @param   name   the system property name.
     * @return the {@code boolean} value of the system property.
     * @see     System#getProperty(java.lang.String)
     * @see     System#getProperty(java.lang.String, java.lang.String)
     */
    public static boolean getBoolean(java.lang.String name) {
        return java.lang.Boolean.getBoolean(name);
    }

    /**
     * Returns the value of this {@code Boolean} object as a boolean
     * primitive.
     *
     * @return the primitive {@code boolean} value of this object.
     */
    public boolean booleanValue() {
        return value.booleanValue();
    }

    /**
     * Returns a {@code String} object representing this Boolean's
     * value.  If this object represents the value {@code true},
     * a string equal to {@code "true"} is returned. Otherwise, a
     * string equal to {@code "false"} is returned.
     *
     * @return a string representation of this object.
     */
    @Override
    public java.lang.String toString() {
        return value.toString();
    }

    /**
     * Returns a hash code for this {@code Boolean} object.
     *
     * @return the integer {@code 1231} if this object represents
     * {@code true}; returns the integer {@code 1237} if this
     * object represents {@code false}.
     */
    @Override
    public int hashCode() {
        return value.hashCode();
    }

    /**
     * Returns {@code true} if and only if the argument is not
     * {@code null} and is a {@code Boolean} object that
     * represents the same {@code boolean} value as this object.
     *
     * @param   obj   the object to compare with.
     * @return  {@code true} if the Boolean objects represent the
     *          same value; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        return value.equals(obj);
    }

    /**
     * Allocates a {@code Boolean} object representing the
     * {@code value} argument.
     * <p/>
     * <p><b>Note: It is rarely appropriate to use this constructor.
     * Unless a <i>new</i> instance is required, the static factory
     * {@link #valueOf(boolean)} is generally a better choice. It is
     * likely to yield significantly better space and time performance.</b>
     *
     * @param value the value of the {@code Boolean}.
     */
    public Boolean(boolean value) {
        this.value = new java.lang.Boolean(value);
    }

    /**
     * Allocates a {@code Boolean} object representing the value
     * {@code true} if the string argument is not {@code null}
     * and is equal, ignoring case, to the string {@code "true"}.
     * Otherwise, allocate a {@code Boolean} object representing the
     * value {@code false}. Examples:<p>
     * {@code new Boolean("True")} produces a {@code Boolean} object
     * that represents {@code true}.<br>
     * {@code new Boolean("yes")} produces a {@code Boolean} object
     * that represents {@code false}.
     *
     * @param s the string to be converted to a {@code Boolean}.
     */
    public Boolean(java.lang.String s) {
        this.value = new java.lang.Boolean(s);
    }
}
