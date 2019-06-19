package com.realpacific.projectnoaa.parsers;

import com.realpacific.projectnoaa.entities.Pair;
import com.realpacific.projectnoaa.formatters.RegexFormatter;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileHeaderToColumnWidthParser implements Parser<Map<String, Pair<Integer, Integer>>> {
    /**
     * List of headers in same order as it occurs in the source
     */
    private List<String> headers;
    /**
     * The strategy used to format the header before finding its width
     */
    private RegexFormatter formatterStrategy;
    private Map<String, Pair<Integer, Integer>> spacingForHeadlineMap = new LinkedHashMap<>();

    public FileHeaderToColumnWidthParser(List<String> headers, RegexFormatter formatterStrategy) {
        this.headers = headers;
        this.formatterStrategy = formatterStrategy;
    }

    /**
     * Determines the width of the each column
     *
     * @param text the actual header text as read from the file
     * @return the {@link Map} containing the mapping between header and its corresponding start index and end index as a {@link Pair}. End index for the last column is equal to {@link Integer#MAX_VALUE}
     */
    @Override
    public Map<String, Pair<Integer, Integer>> parse(String text) {
        int lastMatchingIndex = -1;
        for (int i = 0; i < headers.size(); i++) {
            String header = headers.get(i);
            if (i == headers.size() - 1) {
                // Starts from width range of second last column
                spacingForHeadlineMap.put(header, new Pair<>(lastMatchingIndex, Integer.MAX_VALUE));
            } else {
                // Accommodating the possibility of having a space in front of a column name (as in the gsod files) except in the first column
                Pattern pattern = Pattern.compile((i != 0 ? "(\\s)" : "") + formatterStrategy.format(header) + "(\\s)+");
                Matcher matcher = pattern.matcher(text);
                if (matcher.find()) {
                    spacingForHeadlineMap.put(header, new Pair<>(matcher.start(), matcher.end() - 1));
                    lastMatchingIndex = matcher.end() - 1;
                }
            }
        }
        return spacingForHeadlineMap;
    }
}
