package ru.ugochs.erm.view.util;

import org.apache.commons.lang3.StringUtils;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.*;
import java.util.stream.Collectors;

public class RussianMonthNames {
    private final TextStyle style;

    public RussianMonthNames(TextStyle style) {
        this.style = style;
    }

    public List<String> list() {
        return Arrays.stream(Month.values())
            .map(m -> m.getDisplayName(
                this.style,
                new Locale("ru")
            ))
            .map(StringUtils::capitalize)
            .collect(Collectors.toList());
    }
}
