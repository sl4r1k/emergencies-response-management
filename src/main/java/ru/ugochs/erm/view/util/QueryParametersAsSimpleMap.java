package ru.ugochs.erm.view.util;

import com.vaadin.flow.router.QueryParameters;
import lombok.experimental.Delegate;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class QueryParametersAsSimpleMap implements Map<String, String> {
    @Delegate
    private final Map<String, String> map;

    public QueryParametersAsSimpleMap(QueryParameters parameters) {
        this.map = new HashMap<>(
            parameters.getParameters()
                .entrySet()
                .stream()
                .collect(
                    Collectors.toMap(
                        Map.Entry::getKey,
                        value -> value.getValue().get(0)
                    )
                )
        );
    }
}
