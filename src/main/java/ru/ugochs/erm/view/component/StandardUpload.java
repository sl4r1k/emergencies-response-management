package ru.ugochs.erm.view.component;

import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.upload.SucceededEvent;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import java.util.function.Function;

public class StandardUpload extends Upload {
    public StandardUpload(
        MemoryBuffer buffer,
        Function<? super MemoryBuffer, ? extends ComponentEventListener<SucceededEvent>> event
    ) {
        super(buffer);
        this.setAcceptedFileTypes(".xls", ".xlsx");
        this.setMaxFiles(1);
        int twoMbSize = 2 * 1024 * 1024;
        this.setMaxFileSize(twoMbSize);
        this.addFileRejectedListener(rejectedEvent -> {
            new ErrorNotification(
                rejectedEvent.getErrorMessage()
            ).open();
        });
        this.setI18n(new UploadRussianI18n());
        this.addSucceededListener(event.apply(buffer));
    }
}
