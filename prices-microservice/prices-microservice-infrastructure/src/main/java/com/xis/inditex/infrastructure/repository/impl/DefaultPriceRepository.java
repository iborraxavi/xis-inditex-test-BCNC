package com.xis.inditex.infrastructure.repository.impl;

import com.xis.inditex.domain.model.Price;
import com.xis.inditex.domain.model.PriceRequest;
import com.xis.inditex.domain.model.error.ErrorCode;
import com.xis.inditex.domain.model.exceptions.PriceRepositoryException;
import com.xis.inditex.domain.repository.PriceRepository;
import com.xis.inditex.infrastructure.mapper.PriceInfrastructureMapper;
import com.xis.inditex.infrastructure.repository.jpa.PriceReactiveCrudRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * Default price repository
 *
 * @author XIS
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class DefaultPriceRepository implements PriceRepository {

    private static final String ERROR_MESSAGE = "Repository unexpected error for application date: %s, product id: %s and brand id: %s";

    private final PriceInfrastructureMapper priceInfrastructureMapper;

    private final PriceReactiveCrudRepository priceReactiveCrudRepository;

    /**
     * Search price
     *
     * @param priceRequest Price request
     * @return Price
     */
    @Override
    public Mono<Price> search(final PriceRequest priceRequest) {
        return priceReactiveCrudRepository.search(priceRequest.applicationDate(), priceRequest.productId(), priceRequest.brandId())
                .map(priceInfrastructureMapper::toDomain)
                .onErrorResume(error -> {
                    log.error("Repository unexpected error: {}", error.getMessage(), error);
                    return Mono.error(new PriceRepositoryException(
                            String.format(ERROR_MESSAGE, priceRequest.applicationDate(), priceRequest.productId(), priceRequest.brandId()),
                            ErrorCode.PRICE_REPOSITORY_EXCEPTION,
                            new String[]{priceRequest.applicationDate().toString(), priceRequest.productId().toString(), priceRequest.brandId().toString()}));
                });
    }

}
