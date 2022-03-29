package ru.ugochs.erm.service.crud;

import lombok.experimental.Delegate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class Db {
    @Delegate(types = EntityManager.class)
    @PersistenceContext
    private EntityManager em;

    @Delegate(types = TransactionTemplate.class)
    private TransactionTemplate tt;

    public Db(PlatformTransactionManager ptm) {
        this.tt = new TransactionTemplate(ptm);
    }
}
