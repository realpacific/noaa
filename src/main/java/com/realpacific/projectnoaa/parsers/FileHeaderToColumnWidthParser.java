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
     * List of columns in same order as it occurs in the source
     */
    private List<String> columns;
    /**
     * The strategy used to format the header before finding its width
     */
    private RegexFormatter formatterStrategy;
    private Map<String, Pair<Integer, Integer>> spacingForHeadlineMap = new LinkedHashMap<>();
    private int lastMatchingIndex = -1;

    public FileHeaderToColumnWidthParser(List<String> columns, RegexFormatter formatterStrategy) {
        this.columns = columns;
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
        findWidthForEachColumnInHeaderRow(text);
        return spacingForHeadlineMap;
    }

    private void findWidthForEachColumnInHeaderRow(String headerRowText) {
        for (int i = 0; i < columns.size(); i++) {
            String column = columns.get(i);
            if (isLastColumn(i)) {
                // Starts from width range of second last column
                spacingForHeadlineMap.put(column, new Pair<>(lastMatchingIndex, Integer.MAX_VALUE));
            } else {
                // Accommodating the possibility of having a space in front of a column name (as in the gsod files) except in the first column
                Pattern patternForAccommodatingSpacesAroundColumnName =
                        Pattern.compile((isNotFirstColumn(i) ? "(\\s)" : "") + formatterStrategy.format(column) + "(\\s)+");
                Matcher matcher = patternForAccommodatingSpacesAroundColumnName.matcher(headerRowText);
                if (matcher.find()) {
                    spacingForHeadlineMap.put(column, new Pair<>(matcher.start(), matcher.end() - 1));
                    lastMatchingIndex = matcher.end() - 1;
                }
            }
        }
    }

    private boolean isLastColumn(int index) {
        return index == columns.size() - 1;
    }

    private boolean isFirstColumn(int index) {
        return index == 0;
    }

    private boolean isNotFirstColumn(int index) {
        return !isFirstColumn(index);
    }
}
