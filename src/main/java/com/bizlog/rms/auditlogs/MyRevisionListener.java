package com.bizlog.rms.auditlogs;

import org.hibernate.envers.RevisionListener;

public class MyRevisionListener implements RevisionListener {
    @Override
    public void newRevision(Object revisionEntity) {
        MyRevisionEntity rev=(MyRevisionEntity)revisionEntity;
        rev.setUserName("vinay");
    }
}
