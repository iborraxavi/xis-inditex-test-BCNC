package com.xis.inditex.domain.service;

import com.xis.inditex.domain.model.Price;
import com.xis.inditex.domain.model.PriceRequest;
import com.xis.inditex.domain.model.error.ErrorCode;
import com.xis.inditex.domain.model.exceptions.SearchNotFoundException;
import com.xis.inditex.domain.repository.PriceRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

/**
 * Default price service
 *
 * @author XIS
 */
@Validated
@RequiredArgsConstructor
@Service
public class DefaultPriceService implements PriceService {

    private static final String ERROR_MESSAGE = "Price not found with application date: %s, product id: %s and brand id: %s";

    private final PriceRepository priceRepository;

    /**
     * Search price
     *
     * @param priceRequest Price request
     * @return Price
     */
    @Override
    public Mono<Price> searchPrice(@Valid final PriceRequest priceRequest) {
        return priceRepository.search(priceRequest)
                .switchIfEmpty(Mono.error(
                        new SearchNotFoundException(
                                buildErrorMessage(priceRequest),
                                ErrorCode.PRICE_SEARCH_NOT_FOUND,
                                new String[]{priceRequest.applicationDate().toString(), priceRequest.productId().toString(), priceRequest.brandId().toString()})));
    }

    private String buildErrorMessage(final PriceRequest priceRequest) {
        return String.format(ERROR_MESSAGE, priceRequest.applicationDate(), priceRequest.productId(), priceRequest.brandId());
    }

}
