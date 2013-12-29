package sortpom.processinstruction;

import java.util.regex.Pattern;

/**
 * @author bjorn
 * @since 2013-12-28
 */
enum InstructionType {
    IGNORE, RESUME;

    /** non-xml compliant pattern, see http://www.cs.sfu.ca/~cameron/REX.html#IV.2 */
    final static Pattern INSTRUCTION_PATTERN = Pattern.compile("(?i)<\\?sortpom\\s+([\\w\"'*= ]*)\\s*\\?>");
    final static Pattern IGNORE_SECTIONS_PATTERN = Pattern.compile(
            "(?is)<\\?sortpom\\s+" + IGNORE + "\\s*\\?>.*?<\\?sortpom\\s+" + RESUME + "\\s*\\?>");
    final static Pattern TOKEN_PATTERN = Pattern.compile("(?i)<\\?sortpom\\s+token='(\\d+)'\\s*\\?>");

    public InstructionType next() {
        if (this == IGNORE) {
            return RESUME;
        }
        return IGNORE;
    }

    public static boolean containsType(String instruction) {
        return IGNORE.name().equalsIgnoreCase(instruction) || RESUME.name().equalsIgnoreCase(instruction);
    }

    public boolean matches(String instruction) {
        return this.name().equalsIgnoreCase(instruction);
    }
}
