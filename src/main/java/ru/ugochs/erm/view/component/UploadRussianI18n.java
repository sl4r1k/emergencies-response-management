package ru.ugochs.erm.view.component;

import com.vaadin.flow.component.upload.UploadI18N;
import java.util.List;

public class UploadRussianI18n extends UploadI18N {
    public UploadRussianI18n() {
        this.setDropFiles(
            new DropFiles()
                .setOne("Перетащите Excel-файл сюда")
                .setMany("Перетащите Excel-файлы сюда")
        );
        this.setAddFiles(
            new AddFiles()
                .setOne("Выберите Excel-файл...")
                .setMany("Выберите Excel-файлы...")
        );
        this.setError(
            new Error()
                .setTooManyFiles("Слишком много файлов.")
                .setFileIsTooBig("Файл слишком большой.")
                .setIncorrectFileType("Некорректный формат файла.")
        );
        this.setUploading(
            new Uploading()
                .setStatus(
                    new Uploading.Status()
                        .setConnecting("Соединение...")
                        .setStalled("Остановлено")
                        .setProcessing("Обработка...")
                        .setHeld("В очереди")
                )
                .setRemainingTime(
                    new Uploading.RemainingTime()
                        .setPrefix("Оставшееся время: ")
                        .setUnknown("Неизвестно оставшееся время")
                )
                .setError(
                    new Uploading.Error()
                        .setServerUnavailable("Сервер не отвечает")
                        .setUnexpectedServerError("Ошибка сервера")
                        .setForbidden("Отклонено")
                )
        );
        this.setUnits(
            new Units()
                .setSize(
                    List.of("Б", "КБ", "МБ", "ГБ", "ТБ", "ПБ", "ЭБ", "ЗБ", "ЙБ")
                )
        );
    }
}
