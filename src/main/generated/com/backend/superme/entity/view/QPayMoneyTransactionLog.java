package com.backend.superme.entity.view;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPayMoneyTransactionLog is a Querydsl query type for PayMoneyTransactionLog
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPayMoneyTransactionLog extends EntityPathBase<PayMoneyTransactionLog> {

    private static final long serialVersionUID = -1354527938L;

    public static final QPayMoneyTransactionLog payMoneyTransactionLog = new QPayMoneyTransactionLog("payMoneyTransactionLog");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QPayMoneyTransactionLog(String variable) {
        super(PayMoneyTransactionLog.class, forVariable(variable));
    }

    public QPayMoneyTransactionLog(Path<? extends PayMoneyTransactionLog> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPayMoneyTransactionLog(PathMetadata metadata) {
        super(PayMoneyTransactionLog.class, metadata);
    }

}

