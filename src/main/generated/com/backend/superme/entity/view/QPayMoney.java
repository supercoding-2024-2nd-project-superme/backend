package com.backend.superme.entity.view;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPayMoney is a Querydsl query type for PayMoney
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPayMoney extends EntityPathBase<PayMoney> {

    private static final long serialVersionUID = -200383080L;

    public static final QPayMoney payMoney = new QPayMoney("payMoney");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QPayMoney(String variable) {
        super(PayMoney.class, forVariable(variable));
    }

    public QPayMoney(Path<? extends PayMoney> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPayMoney(PathMetadata metadata) {
        super(PayMoney.class, metadata);
    }

}

