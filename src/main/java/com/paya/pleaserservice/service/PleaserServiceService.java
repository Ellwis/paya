package com.paya.pleaserservice.service;

import com.paya.pleaserservice.dto.*;
import com.paya.pleaserservice.dto.request.PleaserServiceRequestDto;
import com.paya.pleaserservice.entity.TblPleaserService;
import com.paya.pleaserservice.entity.TblPleaserServicePriority;
import com.paya.pleaserservice.entity.TblPleaserServicePriorityConfirmation;
import com.paya.pleaserservice.enums.AttachmentType;
import com.paya.pleaserservice.repository.PleaserServiceRepository;
import com.paya.pleaserservice.service.condition.PriorityConditionServiceBridge;
import com.paya.pleaserservice.service.payment.PaymentMethodServiceBridge;
import com.paya.pleaserservice.service.priority.PriorityServiceBridge;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PleaserServiceService {
    private final EntityManager entityManager;
    private final PriorityServiceBridge priorityService;
    private final PleaserServiceRepository repository;
    private final PleaserServicePriorityConfirmationService confirmationService;
    private final AttachmentService attachmentService;
    private final PaymentMethodServiceBridge paymentMethodService;
    private final PriorityConditionServiceBridge conditionService;

    private final static List<ServiceTypeDto> types = List.of(
            new ServiceTypeDto(1, "خودرو"),
            new ServiceTypeDto(2, "ارزاق"),
            new ServiceTypeDto(3, "البسه"),
            new ServiceTypeDto(4, "نوع خدمت 1"),
            new ServiceTypeDto(5, "نوع خدمت 2"),
            new ServiceTypeDto(6, "نوع خدمت 3"),
            new ServiceTypeDto(7, "لاستیک")
    );


    public ExpertPleaserServiceDto create(PleaserServiceRequestDto request) {
        TblPleaserService service = new TblPleaserService();
        service.setPleaserServiceCount(request.getPleaserServiceCount());
        service.setPleaserServiceTitle(request.getPleaserServiceTitle());
        service.setPleaserServiceType(request.getPleaserServiceType());

        // TODO : set personnel ID & service number
        service.setPleaserServicePersonnelId(UUID.randomUUID().toString());
        Random rand = new Random();
        service.setPleaserServiceServiceNumber(String.valueOf(rand.nextInt(100000 - 10000 + 1) + 10000));

        return get(save(service));
    }


    public ExpertPleaserServiceDto update(String id, PleaserServiceRequestDto request) {
        TblPleaserService service = findById(id);
        checkUpdatable(service);


        if (request.getPleaserServiceCount() != null)
            service.setPleaserServiceCount(request.getPleaserServiceCount());
        if (request.getPleaserServiceTitle() != null)
            service.setPleaserServiceTitle(request.getPleaserServiceTitle());
        if (request.getPleaserServiceType() != null)
            service.setPleaserServiceType(request.getPleaserServiceType());

        return get(save(service));
    }


    public ExpertPleaserServiceDto get(String id) {
        return get(findById(id));
    }


    private ExpertPleaserServiceDto get(TblPleaserService service) {
        return new ExpertPleaserServiceDto(service, isUpdatable(service));
    }


    @Transactional
    public GenericMessageDto delete(String id) {
        checkDeletable(id);
        repository.deleteById(id);
        priorityService.deleteAllByServiceId(id);
        return new GenericMessageDto("خدمت مورد نظر با موفقیت حذف شد!");
    }


    private void checkDeletable(String id) {
        checkUpdatable(findById(id));
    }


    public void checkUpdatable(String id) {
        checkUpdatable(findById(id));
    }


    private void checkUpdatable(TblPleaserService service) {
        if (confirmationService.hasEnabledPriority(service.getPleaserServiceId()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "امکان تغییر خدمت نیست!");
        if (!service.getPleaserServiceIsDraft())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "امکان تغییر خدمت نیست!");
    }


    private Boolean isUpdatable(TblPleaserService service) {
        boolean result = !confirmationService.hasEnabledPriority(service.getPleaserServiceId());
        if (!service.getPleaserServiceIsDraft())
            result = false;
        return result;
    }


    public Page<PleaserServiceDto> getServices(
            String title,
            String number,
            LocalDateTime from,
            LocalDateTime to,
            Integer type,
            Long serviceCountFrom,
            Long serviceCountTo,
            Pageable pageable
    ) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<TblPleaserService> criteria = builder.createQuery(TblPleaserService.class);
        Root<TblPleaserService> root = criteria.from(TblPleaserService.class);
        List<Predicate> predicates = new ArrayList<>();

        setPredicates(
                title,
                number,
                from,
                to,
                type,
                serviceCountFrom,
                serviceCountTo,
                builder,
                root,
                predicates
        );
        criteria.where(predicates.toArray(new Predicate[0]));

        TypedQuery<TblPleaserService> query = entityManager.createQuery(criteria);
        int totalRows = query.getResultList().size();
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());
        List<TblPleaserService> queryResult = query.getResultList();

        List<PleaserServiceDto> result = new ArrayList<>();
        for (TblPleaserService pleaserService : queryResult) {
            result.add(
                    new PleaserServiceDto(
                            pleaserService,
                            priorityService.findAllBy(pleaserService.getPleaserServiceId())
                    )
            );
        }

        return new PageImpl<>(result, pageable, totalRows);
    }


    public Page<PleaserServiceDto> getCurrentServices(
            String title,
            String number,
            LocalDateTime from,
            LocalDateTime to,
            Integer type,
            Long serviceCountFrom,
            Long serviceCountTo,
            Pageable pageable
    ) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<TblPleaserService> criteria = builder.createQuery(TblPleaserService.class);
        Root<TblPleaserService> pleaserServiceRoot = criteria.from(TblPleaserService.class);
        List<Predicate> predicates = new ArrayList<>();

        setPredicates(
                title,
                number,
                from,
                to,
                type,
                serviceCountFrom,
                serviceCountTo,
                builder,
                pleaserServiceRoot,
                predicates
        );

        Subquery<TblPleaserServicePriority> prioritySubquery = criteria.subquery(TblPleaserServicePriority.class);
        Root<TblPleaserServicePriority> priorityRoot = prioritySubquery.from(TblPleaserServicePriority.class);

        Subquery<TblPleaserServicePriorityConfirmation> confirmationSubquery = criteria.subquery(TblPleaserServicePriorityConfirmation.class);
        Root<TblPleaserServicePriorityConfirmation> confirmationRoot = confirmationSubquery.from(TblPleaserServicePriorityConfirmation.class);
        confirmationSubquery.where(
                builder.equal(confirmationRoot.get("pleaserServicePriorityConfirmationPleaserServicePriorityId"), priorityRoot.get("pleaserServicePriorityId")),
                builder.equal(confirmationRoot.get("pleaserServicePriorityConfirmationConfirm"), 1)
        );
        prioritySubquery.where(
                builder.equal(priorityRoot.get("pleaserServicePriorityPleaserServiceId"), pleaserServiceRoot.get("pleaserServiceId")),
                builder.exists(confirmationSubquery)
        );

        predicates.add(builder.exists(prioritySubquery));

        criteria.where(predicates.toArray(new Predicate[0]));

        TypedQuery<TblPleaserService> query = entityManager.createQuery(criteria);
        int totalRows = query.getResultList().size();

        List<TblPleaserService> queryResult = query
                .setFirstResult(pageable.getPageNumber() * pageable.getPageSize())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        // Result
        List<PleaserServiceDto> result = new ArrayList<>();
        for (TblPleaserService pleaserService : queryResult) {
            result.add(
                    new PleaserServiceDto(
                            pleaserService,
                            priorityService.findAllBy(pleaserService.getPleaserServiceId())
                    )
            );
        }


        return new PageImpl<>(result, pageable, totalRows);
    }


    private void setPredicates(
            String title,
            String number,
            LocalDateTime from,
            LocalDateTime to,
            Integer type,
            Long serviceCountFrom,
            Long serviceCountTo,
            CriteriaBuilder builder,
            Root<TblPleaserService> root,
            List<Predicate> predicates
    ) {
        if (title != null)
            predicates.add(builder.like(root.get("pleaserServiceTitle"), "%" + title + "%"));
        if (number != null)
            predicates.add(builder.like(root.get("pleaserServiceServiceNumber"), "%" + number + "%"));
        if (from != null)
            predicates.add(builder.greaterThanOrEqualTo(root.get("pleaserServiceCreatedAt"), from));
        if (to != null)
            predicates.add(builder.lessThanOrEqualTo(root.get("pleaserServiceCreatedAt"), to));
        if (type != null)
            predicates.add(builder.equal(root.get("pleaserServiceType"), type));
        if (serviceCountFrom != null)
            predicates.add(builder.greaterThanOrEqualTo(root.get("pleaserServiceCount"), serviceCountFrom));
        if (serviceCountTo != null)
            predicates.add(builder.lessThanOrEqualTo(root.get("pleaserServiceCount"), serviceCountTo));
    }


    @Transactional
    public void submit(String id) {
        if (repository.changeDraftStatus(id) <= 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "آپدیت پیش نویس با مشکل مواجه شد!");
    }


    public PleaserServiceDetailDto getForAdmin(String id, List<PleaserServiceFieldDto> fields) {
        TblPleaserService service = findById(id);
        PleaserServiceDetailDto result = new PleaserServiceDetailDto(service, attachmentService.getAll(id, AttachmentType.PLEASER_SERVICE));
        result.setPleaserServiceFields(fields);
        List<TblPleaserServicePriority> priorities = priorityService.findAllBy(id);
        List<PleaserServiceDetailDto.PriorityDto> priorityDtos = priorities
                .stream()
                .map(priority -> {
                    PleaserPaymentMethodDto methodDto = paymentMethodService.get(priority.getPleaserServicePriorityId());
                    return new PleaserServiceDetailDto.PriorityDto(priority, new AdminPleaserPaymentMethodDto(
                            methodDto,
                            attachmentService.getAll(priority.getPleaserServicePriorityId(), AttachmentType.REGIME)),
                            conditionService.getAll(priority.getPleaserServicePriorityId()),
                            attachmentService.getAll(priority.getPleaserServicePriorityId(), AttachmentType.PLEASER_SERVICE_PRIORITY),
                            confirmationService.findByPriorityId(priority.getPleaserServicePriorityId())
                    );
                })
                .toList();
        result.setPriorities(priorityDtos);

        return result;
    }


    public Boolean existsById(String id) {
        return repository.existsById(id);
    }


    public TblPleaserService findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "خدمت موردنظر یافت نشد!"));
    }


    private TblPleaserService save(TblPleaserService service) {
        return repository.save(service);
    }


    public List<ServiceTypeDto> getServiceTypes() {
        return types;
    }


    public static ServiceTypeDto getServiceType(Integer id) {
        return types.stream().filter(type -> type.getId().equals(id)).findFirst().get();
    }

}
