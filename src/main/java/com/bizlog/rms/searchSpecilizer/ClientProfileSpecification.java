package com.bizlog.rms.searchSpecilizer;

import com.bizlog.rms.entities.BaseClientEntity;
import com.bizlog.rms.entities.Client;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

public class ClientProfileSpecification <T extends BaseClientEntity> implements Specification<T> {


    private final SearchCriteria searchCriteria;

    public ClientProfileSpecification(final SearchCriteria searchCriteria){
        super();
        this.searchCriteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {



        String strToSearch = searchCriteria.getValue().toString().toLowerCase();

        switch(Objects.requireNonNull(SearchOperation.getSimpleOperation(searchCriteria.getOperation()))){
            case CONTAINS:
                if(searchCriteria.getFilterKey().equals("deptName")){
                    return cb.like(cb.lower(departmentJoin(root).<String>get(searchCriteria.getFilterKey())), "%" + strToSearch + "%");
                }
                return cb.like(cb.lower(root.get(searchCriteria.getFilterKey())), "%" + strToSearch + "%");

            case DOES_NOT_CONTAIN:
                if(searchCriteria.getFilterKey().equals("deptName")){
                    return cb.notLike(cb.lower(departmentJoin(root).<String>get(searchCriteria.getFilterKey())), "%" + strToSearch + "%");
                }
                return cb.notLike(cb.lower(root.get(searchCriteria.getFilterKey())), "%" + strToSearch + "%");

            case BEGINS_WITH:
                if(searchCriteria.getFilterKey().equals("deptName")){
                    return cb.like(cb.lower(departmentJoin(root).<String>get(searchCriteria.getFilterKey())), strToSearch + "%");
                }
                return cb.like(cb.lower(root.get(searchCriteria.getFilterKey())), strToSearch + "%");

            case DOES_NOT_BEGIN_WITH:
                if(searchCriteria.getFilterKey().equals("deptName")){
                    return cb.notLike(cb.lower(departmentJoin(root).<String>get(searchCriteria.getFilterKey())), strToSearch + "%");
                }
                return cb.notLike(cb.lower(root.get(searchCriteria.getFilterKey())), strToSearch + "%");

            case ENDS_WITH:
                if(searchCriteria.getFilterKey().equals("deptName")){
                    return cb.like(cb.lower(departmentJoin(root).<String>get(searchCriteria.getFilterKey())), "%" + strToSearch);
                }
                return cb.like(cb.lower(root.get(searchCriteria.getFilterKey())), "%" + strToSearch);

            case DOES_NOT_END_WITH:
                if(searchCriteria.getFilterKey().equals("deptName")){
                    return cb.notLike(cb.lower(departmentJoin(root).<String>get(searchCriteria.getFilterKey())), "%" + strToSearch);
                }
                return cb.notLike(cb.lower(root.get(searchCriteria.getFilterKey())), "%" + strToSearch);

            case EQUAL:
                if(searchCriteria.getFilterKey().equals("deptName")){
                    System.out.println(searchCriteria.getValue());
                    return cb.equal(departmentJoin(root).<String>get(searchCriteria.getFilterKey()), searchCriteria.getValue());
                }
                return cb.equal(root.get(searchCriteria.getFilterKey()), searchCriteria.getValue());

            case NOT_EQUAL:
                if(searchCriteria.getFilterKey().equals("deptName")){
                    return cb.notEqual(departmentJoin(root).<String>get(searchCriteria.getFilterKey()), (Comparable)  searchCriteria.getValue() );
                }
                return cb.notEqual(root.get(searchCriteria.getFilterKey()), searchCriteria.getValue());

            case NUL:
                return cb.isNull(root.get(searchCriteria.getFilterKey()));

            case NOT_NULL:
                return cb.isNotNull(root.get(searchCriteria.getFilterKey()));

            case GREATER_THAN:
                if(searchCriteria.getFilterKey().equals("deptName")) {
                    System.out.println(searchCriteria.getValue());
                     cb.greaterThan(departmentJoin(root).<Number>get(searchCriteria.getFilterKey()).as(long.class),((Number) searchCriteria.getValue()).longValue());
                }
                  if (searchCriteria.getValue() instanceof Comparable<?>){
                      System.out.println(searchCriteria.getValue());
                      return cb.greaterThan(root.<Comparable>get(searchCriteria.getFilterKey()),(Comparable) searchCriteria.getValue());
                  }

            case GREATER_THAN_EQUAL:
                System.out.println(searchCriteria.getValue());
                return cb.greaterThanOrEqualTo(root.<String> get(searchCriteria.getFilterKey()), searchCriteria.getValue().toString());

            case LESS_THAN:
                System.out.println(searchCriteria.getValue());
                return cb.lessThan(root.<String> get(searchCriteria.getFilterKey()), searchCriteria.getValue().toString());

            case LESS_THAN_EQUAL:
                System.out.println(searchCriteria.getValue());
                return cb.lessThanOrEqualTo(root.<String> get(searchCriteria.getFilterKey()),  searchCriteria.getValue().toString());
        }
        return null;
    }

    private Join<Client, T> departmentJoin(Root<T> root){
        return root.join("department");
    }



}
