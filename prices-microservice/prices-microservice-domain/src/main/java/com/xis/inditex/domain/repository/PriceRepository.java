package com.xis.inditex.domain.repository;

import com.xis.inditex.domain.model.Price;
import com.xis.inditex.domain.model.PriceRequest;
import reactor.core.publisher.Mono;

/**
 * Price repository
 *
 * @author XIS
 */
public interface PriceRepository {

    /**
     * Search price
     *
     * @param priceRequest Price request
     * @return Price
     */
    Mono<Price> search(PriceRequest priceRequest);

}
