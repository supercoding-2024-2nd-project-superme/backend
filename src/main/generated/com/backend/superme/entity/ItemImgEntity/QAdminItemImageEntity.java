package com.backend.superme.entity.ItemImgEntity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAdminItemImageEntity is a Querydsl query type for AdminItemImageEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAdminItemImageEntity extends EntityPathBase<AdminItemImageEntity> {

    private static final long serialVersionUID = 2125591904L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAdminItemImageEntity adminItemImageEntity = new QAdminItemImageEntity("adminItemImageEntity");

    public final com.backend.superme.constant.base.QBaseEntity _super = new com.backend.superme.constant.base.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createAt = _super.createAt;

    //inherited
    public final NumberPath<Long> createdBy = _super.createdBy;

    public final StringPath imageUrl = createString("imageUrl");

    public final com.backend.superme.entity.view.QItem item;

    public final NumberPath<Long> itemImageId = createNumber("itemImageId", Long.class);

    //inherited
    public final NumberPath<Long> updateBy = _super.updateBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QAdminItemImageEntity(String variable) {
        this(AdminItemImageEntity.class, forVariable(variable), INITS);
    }

    public QAdminItemImageEntity(Path<? extends AdminItemImageEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAdminItemImageEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAdminItemImageEntity(PathMetadata metadata, PathInits inits) {
        this(AdminItemImageEntity.class, metadata, inits);
    }

    public QAdminItemImageEntity(Class<? extends AdminItemImageEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.item = inits.isInitialized("item") ? new com.backend.superme.entity.view.QItem(forProperty("item"), inits.get("item")) : null;
    }

}

