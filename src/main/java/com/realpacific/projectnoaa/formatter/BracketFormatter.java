package com.realpacific.projectnoaa.formatter;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Helper class to escape brackets in regex
 */
public class BracketFormatter implements RegexFormatter {

    private final Map<String, String> replacementMap = new HashMap<>();

    public BracketFormatter() {
        replacementMap.put("(", "\\\\(");
        replacementMap.put(")", "\\\\)");
        replacementMap.put("[", "\\\\[");
        replacementMap.put("]", "\\\\]");
        replacementMap.put("{", "\\\\{");
        replacementMap.put("}", "\\\\}");
    }

    @Override
    public String format(String text) {
        String output = text;
        for (Map.Entry<String, String> entry : replacementMap.entrySet()) {
            // Escape brackets from being considered as regex
            output = output.replaceAll(Pattern.quote(entry.getKey()), entry.getValue());
        }
        return output;
    }
}
