package com.backend.superme.entity.view;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;

import java.math.BigDecimal;


/**
 * QOrder is a Querydsl query type for Order
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrder extends EntityPathBase<Order> {

    private static final long serialVersionUID = 387518126L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrder order = new QOrder("order1");

    public final StringPath deliveryAddress = createString("deliveryAddress");

    public final EnumPath<com.backend.superme.constant.base.DeliveryStatus> deliveryStatus = createEnum("deliveryStatus", com.backend.superme.constant.base.DeliveryStatus.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.util.Date> orderDate = createDateTime("orderDate", java.util.Date.class);

    public final ListPath<OrderItem, QOrderItem> orderItems = this.<OrderItem, QOrderItem>createList("orderItems", OrderItem.class, QOrderItem.class, PathInits.DIRECT2);

    public final EnumPath<com.backend.superme.constant.base.PaymentMethod> paymentMethod = createEnum("paymentMethod", com.backend.superme.constant.base.PaymentMethod.class);

    public final EnumPath<com.backend.superme.constant.base.OrderStatus> status = createEnum("status", com.backend.superme.constant.base.OrderStatus.class);

    public final NumberPath<java.math.BigDecimal> totalPrice = createNumber("totalPrice", java.math.BigDecimal.class);

    public final com.backend.superme.entity.user.QUserEntity user;

    public QOrder(String variable) {
        this(Order.class, forVariable(variable), INITS);
    }

    public QOrder(Path<? extends Order> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrder(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrder(PathMetadata metadata, PathInits inits) {
        this(Order.class, metadata, inits);
    }

    public QOrder(Class<? extends Order> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.backend.superme.entity.user.QUserEntity(forProperty("user")) : null;
    }

    public com.backend.superme.entity.user.QUserEntity getUser() {
        return user;
    }

    public NumberPath<BigDecimal> getTotalPrice() {
        return totalPrice;
    }
}

