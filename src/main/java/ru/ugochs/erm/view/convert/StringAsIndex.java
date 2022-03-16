package ru.ugochs.erm.view.convert;

import com.vaadin.flow.data.binder.Result;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.data.converter.Converter;
import ru.ugochs.erm.entity.Index;
import ru.ugochs.erm.service.crud.Db;
import ru.ugochs.erm.service.crud.GetIndexByName;
import java.util.Objects;

public class StringAsIndex implements Converter<String, Index> {
    private final Db db;

    public StringAsIndex(Db db) {
        this.db = db;
    }

    @Override
    public Result<Index> convertToModel(String indexName, ValueContext context) {
        return Result.ok(new GetIndexByName(indexName, this.db).perform().orElse(null));
    }

    @Override
    public String convertToPresentation(Index index, ValueContext context) {
        return Objects.isNull(index)
            ? ""
            : index.getName();
    }
}
