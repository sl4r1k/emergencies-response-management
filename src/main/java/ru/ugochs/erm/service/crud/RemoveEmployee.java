package ru.ugochs.erm.service.crud;

import ru.ugochs.erm.entity.Employee;
import ru.ugochs.erm.view.component.ErrorDialog;

public class RemoveEmployee extends Remove<Employee> {
    public RemoveEmployee(Employee entity, Db db) {
        super(entity, db);
    }

    @Override
    public Void perform() {
        if (this.entity.getId().equals(1L)) {
            new ErrorDialog("Нельзя удалить главного администратора").show();
            return null;
        }
        return super.perform();
    }
}
