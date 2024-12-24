package com.paya.pleaserservice.service.personnel;

import com.paya.pleaserservice.dto.PersonnelPriorityDto;
import com.paya.pleaserservice.dto.PleaserRequestServiceResponseDto;
import com.paya.pleaserservice.entity.TblPleaserService;
import com.paya.pleaserservice.entity.TblPleaserServicePriority;
import com.paya.pleaserservice.entity.TblPleaserServicePriorityConfirmation;
import com.paya.pleaserservice.service.priority.PleaserServicePriorityService;
import com.paya.pleaserservice.service.request.PleaserServiceRequestConfirmationService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@AllArgsConstructor
public class PersonnelFacadeService {
    private final PleaserServicePriorityService priorityService;
    private final EntityManager entityManager;
    private final PleaserServiceRequestConfirmationService confirmationService;


    public PleaserRequestServiceResponseDto getPersonnelPriority(String id) {
        return priorityService.getPersonnelPriority(id);
    }


    // TODO : get tokens
    public Page<PersonnelPriorityDto> getPersonnelPriorities(
            Integer serviceType,
            String serviceTitle,
            Integer status,
            Long serviceAmountFrom,
            Long serviceAmountTo,
            Pageable pageable
    ) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<TblPleaserService> criteria = builder.createQuery(TblPleaserService.class);
        Root<TblPleaserService> pleaserServiceRoot = criteria.from(TblPleaserService.class);
        List<Predicate> predicates = new ArrayList<>();

        // TODO : add conditions
        setServicePredicates(
                serviceTitle,
                serviceType,
                builder,
                pleaserServiceRoot,
                predicates
        );

        Subquery<TblPleaserServicePriority> prioritySubquery = criteria.subquery(TblPleaserServicePriority.class);
        Root<TblPleaserServicePriority> priorityRoot = prioritySubquery.from(TblPleaserServicePriority.class);

        Subquery<TblPleaserServicePriorityConfirmation> confirmationSubquery = criteria.subquery(TblPleaserServicePriorityConfirmation.class);
        Subquery<TblPleaserServicePriorityConfirmation> notExistsConfSubQuery = criteria.subquery(TblPleaserServicePriorityConfirmation.class);
        Root<TblPleaserServicePriorityConfirmation> confirmationRoot = confirmationSubquery.from(TblPleaserServicePriorityConfirmation.class);

        List<Predicate> subQueryPredicates = new ArrayList<>();
        if (serviceAmountFrom != null)
            subQueryPredicates.add(builder.greaterThanOrEqualTo(priorityRoot.get("pleaserServicePriorityAmount"), serviceAmountFrom));
        if (serviceAmountTo != null)
            subQueryPredicates.add(builder.lessThanOrEqualTo(priorityRoot.get("pleaserServicePriorityAmount"), serviceAmountTo));

        // TODO : fix this for get priorities by request with accepted confirmation or no confirmation
//        if (status == null) {
//            subQueryPredicates.add(builder.or(
//                    builder.and(
//                            builder.equal(confirmationRoot.get("pleaserServicePriorityConfirmationPleaserServicePriorityId"), priorityRoot.get("pleaserServicePriorityId")),
//                            builder.equal(confirmationRoot.get("pleaserServicePriorityConfirmationConfirm"), 2)
//                    ),
//                    builder.not(
//                            builder.exists(
//                                    notExistsConfSubQuery.where(
//                                            builder.equal(confirmationRoot.get("pleaserServicePriorityConfirmationPleaserServicePriorityId"), priorityRoot.get("pleaserServicePriorityId")))
//                            )
//                    )
//            ));
//
//            confirmationSubquery.where(
//                    subQueryPredicates.toArray(new Predicate[0])
//            );
//
//            prioritySubquery.where(
//                    builder.equal(priorityRoot.get("pleaserServicePriorityPleaserServiceId"), pleaserServiceRoot.get("pleaserServiceId")),
//                    builder.exists(confirmationSubquery)
//            );
//        } else {
//            if (status == 2)
//                subQueryPredicates.add(
//                        builder.and(
//                                builder.equal(confirmationRoot.get("pleaserServicePriorityConfirmationPleaserServicePriorityId"), priorityRoot.get("pleaserServicePriorityId")),
//                                builder.equal(confirmationRoot.get("pleaserServicePriorityConfirmationConfirm"), 2)
//                        )
//                );
//
//            else
//                subQueryPredicates.add(
//                        builder.not(
//                                builder.exists(
//                                        notExistsConfSubQuery.where(
//                                                builder.equal(confirmationRoot.get("pleaserServicePriorityConfirmationPleaserServicePriorityId"), priorityRoot.get("pleaserServicePriorityId")))
//                                )
//                        )
//                );
//
//            confirmationSubquery.where(
//                    subQueryPredicates.toArray(new Predicate[0])
//            );
//
//            prioritySubquery.where(
//                    builder.equal(priorityRoot.get("pleaserServicePriorityPleaserServiceId"), pleaserServiceRoot.get("pleaserServiceId")),
//                    builder.exists(confirmationSubquery)
//            );
//        }

        predicates.add(builder.exists(prioritySubquery));


//        predicates.add(builder.equal(priorityRoot.get("PleaserServicePriorityPleaserServiceId"), pleaserServiceRoot.get("pleaserServiceId")));
//        predicates.add(builder.equal(priorityConfirmationRoot.get("pleaserServicePriorityConfirmationPleaserServicePriorityId"), priorityRoot.get("pleaserServicePriorityId")));
//        predicates.add(builder.equal(priorityConfirmationRoot.get("pleaserServicePriorityConfirmationStatus"), 1));


        criteria.where(predicates.toArray(new Predicate[0]));

        TypedQuery<TblPleaserService> query = entityManager.createQuery(criteria);
        System.out.println(query.getResultList());
        int totalRows = query.getResultList().size();

        List<TblPleaserService> queryResult = query
                .setFirstResult(pageable.getPageNumber() * pageable.getPageSize())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        // JUST FOR TEST
        AtomicInteger statusResponse = new AtomicInteger(2);
        return new PageImpl<>(
                queryResult.stream().map(service -> {
                    // TODO : change these!!!
                    PersonnelPriorityDto result = new PersonnelPriorityDto();
                    TblPleaserServicePriority priority = priorityService.findLastPriority(service.getPleaserServiceId());
                    result.setPleaserServicePriorityId(priority != null ? priority.getPleaserServicePriorityId() : null);
                    result.setPleaserServicePriorityPleaserServiceId(service.getPleaserServiceId());
                    result.setPleaserServiceTitle(service.getPleaserServiceTitle());
                    result.setPleaserServicePriorityWeight(10);
                    result.setTypeDto(service.getPleaserServiceType());
                    // TODO : get confirmation
                    int s = statusResponse.get();
                    result.setStatus(s);
                    statusResponse.set(3);
                    return result;
                }).toList(),
                pageable,
                totalRows
        );
    }


    private void setServicePredicates(
            String title,
            Integer type,
            CriteriaBuilder builder,
            Root<TblPleaserService> root,
            List<Predicate> predicates
    ) {
        if (title != null)
            predicates.add(builder.like(root.get("pleaserServiceTitle"), "%" + title + "%"));
        if (type != null)
            predicates.add(builder.equal(root.get("pleaserServiceType"), type));
    }

}
