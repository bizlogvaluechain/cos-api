package com.bizlog.rms.searchSpecilizer;

import com.bizlog.rms.entities.BaseClientEntity;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class GenaricSpecificationBuilder<T extends BaseClientEntity> {

    private final List<SearchCriteria> params;

    public GenaricSpecificationBuilder() {
        this.params = new ArrayList<>();
    }

    public final GenaricSpecificationBuilder with(String key, String operation, Object value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public final GenaricSpecificationBuilder with(SearchCriteria searchCriteria) {
        params.add(searchCriteria);
        return this;
    }

    public Specification<T> build() {
        if (params.size() == 0) {
            return null;
        }

        Specification<T> result = new ClientProfileSpecification(params.get(0));
        for (int idx = 1; idx < params.size(); idx++) {
            SearchCriteria criteria = params.get(idx);
            result = SearchOperation.getDataOption(criteria.getDataOption()) == SearchOperation.ALL
                    ? Specification.where(result).and(new ClientProfileSpecification(criteria))
                    : Specification.where(result).or(new ClientProfileSpecification(criteria));
        }
        return result;
    }
}
