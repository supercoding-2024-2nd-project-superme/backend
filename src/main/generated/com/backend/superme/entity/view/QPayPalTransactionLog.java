package com.backend.superme.entity.view;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPayPalTransactionLog is a Querydsl query type for PayPalTransactionLog
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPayPalTransactionLog extends EntityPathBase<PayPalTransactionLog> {

    private static final long serialVersionUID = -1362389543L;

    public static final QPayPalTransactionLog payPalTransactionLog = new QPayPalTransactionLog("payPalTransactionLog");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QPayPalTransactionLog(String variable) {
        super(PayPalTransactionLog.class, forVariable(variable));
    }

    public QPayPalTransactionLog(Path<? extends PayPalTransactionLog> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPayPalTransactionLog(PathMetadata metadata) {
        super(PayPalTransactionLog.class, metadata);
    }

}

