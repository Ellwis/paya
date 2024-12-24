package com.paya.pleaserservice.repository.v2.Impl;

import com.paya.pleaserservice.entity.TblPleaserServiceRequestConfirmation;
import com.paya.pleaserservice.repository.v2.RequestConfirmationDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class RequestConfirmationDAOImpl implements RequestConfirmationDAO {

    @Autowired
    EntityManager entityManager;

    @Override
    public List<TblPleaserServiceRequestConfirmation> getList(HashMap<String, Object> filters, HashMap<String, String> sorting, Integer pageSize, Integer pageNumber) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<TblPleaserServiceRequestConfirmation> criteriaQuery = criteriaBuilder.createQuery(TblPleaserServiceRequestConfirmation.class);
        Root<TblPleaserServiceRequestConfirmation> root = criteriaQuery.from(TblPleaserServiceRequestConfirmation.class);
        List<Predicate> predicates = new ArrayList<>();
        if (filters != null && !filters.isEmpty()) {
            for (HashMap.Entry<String, Object> entry : filters.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                if (value != null) {
                    if (value instanceof String) {
                        predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get(key)), "%" + value.toString().toLowerCase() + "%"));
                    } else {
                        predicates.add(criteriaBuilder.equal(root.get(key), value));
                    }
                }
            }
        }
        if (sorting != null && !sorting.isEmpty()) {
            for (HashMap.Entry<String, String> entry : sorting.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (value != null) {
                    if (value.contains("desc"))
                        criteriaBuilder.desc(root.get(key));
                }
                if (value.contains("asc")) ;
                criteriaBuilder.asc(root.get(key));
            }
        }
        criteriaQuery.select(root).where(predicates.toArray(new Predicate[0]));
        TypedQuery<TblPleaserServiceRequestConfirmation> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult((pageNumber - 1) * pageSize).setMaxResults(pageSize);
        return typedQuery.getResultList();
    }
}
