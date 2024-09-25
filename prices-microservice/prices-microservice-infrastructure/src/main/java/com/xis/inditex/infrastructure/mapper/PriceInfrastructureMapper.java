package com.xis.inditex.infrastructure.mapper;

import com.xis.inditex.domain.model.Price;
import com.xis.inditex.infrastructure.entity.PriceEntity;
import org.mapstruct.Mapper;

/**
 * Price infrastructure mapper
 *
 * @author XIS
 */
@Mapper
public interface PriceInfrastructureMapper {

    /**
     * Map to domain
     *
     * @param priceEntity Price entity
     * @return Price
     */
    Price toDomain(PriceEntity priceEntity);

}
