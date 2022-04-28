package ru.ugochs.erm.view.component;

import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.html.Span;

@NpmPackage(value = "line-awesome", version = "1.3.0")
public class LineAwesomeIcon extends Span {
    public LineAwesomeIcon(String classNames) {
        this.addClassNames("me-s", "text-l");
        if (!classNames.isEmpty()) {
            this.addClassNames(classNames);
        }
    }
}
