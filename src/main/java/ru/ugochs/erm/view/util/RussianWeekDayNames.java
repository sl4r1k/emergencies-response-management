package ru.ugochs.erm.view.util;

import org.apache.commons.lang3.StringUtils;
import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.*;
import java.util.stream.Collectors;

public class RussianWeekDayNames {
    private final TextStyle style;

    public RussianWeekDayNames(TextStyle style) {
        this.style = style;
    }

    public List<String> list() {
        List<String> withMondayAtFirst = Arrays.stream(DayOfWeek.values())
            .map(d -> d.getDisplayName(
                this.style,
                new Locale("ru")
            ))
            .map(StringUtils::capitalize)
            .collect(Collectors.toList());
        withMondayAtFirst.add(0, withMondayAtFirst.remove(withMondayAtFirst.size() - 1));
        return withMondayAtFirst;
    }
}
