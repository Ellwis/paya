package com.paya.pleaserservice.service.priority;

import com.paya.pleaserservice.dto.*;
import com.paya.pleaserservice.dto.request.PleaserServicePriorityRequestDto;
import com.paya.pleaserservice.entity.TblPleaserServicePriority;
import com.paya.pleaserservice.entity.TblPleaserServicePriorityConfirmation;
import com.paya.pleaserservice.repository.PleaserServicePriorityRepository;
import com.paya.pleaserservice.service.PleaserServicePriorityConfirmationService;
import com.paya.pleaserservice.service.PleaserServiceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;


@Service
@AllArgsConstructor
public class PleaserServicePriorityService {
    private final PleaserServicePriorityRepository repository;
    private final PleaserServiceService pleaserServiceService;
    private final PleaserServicePriorityConfirmationService confirmationService;


    public ExpertPleaserServicePriorityDto create(String serviceId, PleaserServicePriorityRequestDto request) {
        if (existsByNumber(request.getPleaserServicePriorityNumber(), serviceId))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "اولویتی با این شماره در گذشته ثبت شده است!");
        return get(save(request.toPriority(serviceId)));
    }


    private boolean existsByNumber(Integer number, String serviceId) {
        return repository.existsByPleaserServicePriorityNumberAndPleaserServicePriorityPleaserServiceId(number, serviceId);
    }


    public ExpertPleaserServicePriorityDto update(String id, PleaserServicePriorityRequestDto request) {
        TblPleaserServicePriority priority = findById(id);
        checkUpdatable(priority);

        if (request.getPleaserServicePriorityAmount() != null)
            priority.setPleaserServicePriorityAmount(request.getPleaserServicePriorityAmount());
        if (request.getPleaserServicePriorityDescription() != null)
            priority.setPleaserServicePriorityDescription(request.getPleaserServicePriorityDescription());
        if (request.getPleaserServicePriorityNumber() != null)
            priority.setPleaserServicePriorityNumber(request.getPleaserServicePriorityNumber());
        if (request.getPleaserServicePriorityWeight() != null)
            priority.setPleaserServicePriorityWeight(request.getPleaserServicePriorityWeight());
        if (request.getPleaserServicePriorityAllowedCount() != null)
            priority.setPleaserServicePriorityAllowedCount(request.getPleaserServicePriorityAllowedCount());
        if (request.getPleaserServicePriorityCancelOption() != null)
            priority.setPleaserServicePriorityCancelOption(request.getPleaserServicePriorityCancelOption());
        if (request.getPleaserServicePriorityMaximumCancellationDate() != null)
            priority.setPleaserServicePriorityMaximumCancellationDate(request.getPleaserServicePriorityMaximumCancellationDate());
        if (request.getPleaserServicePriorityStartDate() != null)
            priority.setPleaserServicePriorityStartDate(request.getPleaserServicePriorityStartDate());
        if (request.getPleaserServicePriorityEndDate() != null)
            priority.setPleaserServicePriorityEndDate(request.getPleaserServicePriorityEndDate());

        return get(save(priority));
    }


    private ExpertPleaserServicePriorityDto get(TblPleaserServicePriority priority) {
        return new ExpertPleaserServicePriorityDto(priority, isUpdatable(priority));

    }


    @Transactional
    public List<PleaserServicePriorityDetailDto> delete(String id) {
        checkDeletable(id);
        String serviceId = findById(id).getPleaserServicePriorityPleaserServiceId();
        repository.deleteById(id);
        return getAll(serviceId);
    }


    private void checkDeletable(String id) {
        checkUpdatable(id);
    }


    private Boolean isUpdatable(TblPleaserServicePriority priority) {
        if (!priority.isPleaserServicePriorityIsDraft())
            return false;
        TblPleaserServicePriorityConfirmation confirmation = confirmationService.findByPriorityId(priority.getPleaserServicePriorityId());

        return confirmation == null || confirmation.getPleaserServicePriorityConfirmationConfirm() != 1;
    }


    public void checkUpdatable(String id) {
        TblPleaserServicePriority priority = findById(id);
        if (!priority.isPleaserServicePriorityIsDraft())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "اجازه تغییر اولویت را ندارید!");
        checkApproved(priority.getPleaserServicePriorityId());
    }


    private void checkUpdatable(TblPleaserServicePriority priority) {
        if (!priority.isPleaserServicePriorityIsDraft())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "اجازه تغییر اولویت را ندارید!");
        checkApproved(priority.getPleaserServicePriorityId());
    }


    private void checkApproved(String id) {
        TblPleaserServicePriorityConfirmation confirmation = confirmationService.findByPriorityId(id);
        if (confirmation != null && confirmation.getPleaserServicePriorityConfirmationConfirm() == 1)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "اولویت تایید شده و شما اجازه تغییر آن را ندارید!");
    }


    public List<PleaserServicePriorityDetailDto> getAll(String serviceId) {
        return repository
                .findAllByPleaserServicePriorityPleaserServiceId(serviceId)
                .stream()
                .map(priority -> new PleaserServicePriorityDetailDto(priority, isUpdatable(priority.getPleaserServicePriorityId()), null))
                .sorted((p1, p2) -> p1.getPleaserServicePriorityNumber().compareTo(p2.getPleaserServicePriorityNumber()))
                .toList();
    }


    // TODO : change block code
    public PleaserServicePriorityDetailDto getPriority(String priorityId) {
        PleaserServicePriorityDetailDto priority = repository.getPriority(priorityId);
//        priority.set
        priority.setIsChangeable(isUpdatable(priorityId));
        return priority;
    }


    private Boolean isUpdatable(String priorityId) {
        TblPleaserServicePriority priority = findById(priorityId);
        if (!priority.isPleaserServicePriorityIsDraft())
            return false;
        TblPleaserServicePriorityConfirmation confirmation = confirmationService.findByPriorityId(priority.getPleaserServicePriorityId());

        return confirmation == null || confirmation.getPleaserServicePriorityConfirmationConfirm() != 1;
    }


    @Transactional
    public void updatePaymentFields(
            BigDecimal prepayment,
            Integer countOfMonthInstallments
    ) {
        if (repository.updatePaymentFields(prepayment, countOfMonthInstallments) <= 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "آپدیت فیلد های پرداخت اولویت بامشکل مواجه شد!");
    }


    private TblPleaserServicePriority save(TblPleaserServicePriority priority) {
        checkPriority(priority);
        return repository.save(priority);
    }


    private void checkPriority(TblPleaserServicePriority priority) {
        if (!pleaserServiceService.existsById(priority.getPleaserServicePriorityPleaserServiceId()))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "خدمت مورد نظر یافت نشد!");
        if (priority.getPleaserServicePriorityAmount().compareTo(BigDecimal.ZERO) <= 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "مبلغ خدمت به درستی وارد نشده است!");
        if (priority.getPleaserServicePriorityNumber() <= 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "شماره اولویت اشتباه هست!");
        if (priority.getPleaserServicePriorityWeight() <= 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "وزن اولویت اشتباه هست!");
        if (priority.getPleaserServicePriorityAllowedCount() <= 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "تعداد مجاز وارد شده اشتباه هست!");
        if (priority.getPleaserServicePriorityStartDate().isAfter(priority.getPleaserServicePriorityEndDate()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "تاریخ شروع از پایان جلوتر است!");
        if (priority.getPleaserServicePriorityCancelOption()) {
            if (priority.getPleaserServicePriorityMaximumCancellationDate() == null)
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "حداکثر تاریخ لغو رو مشخص کنید!");
            if (priority.getPleaserServicePriorityStartDate().isAfter(priority.getPleaserServicePriorityMaximumCancellationDate().toLocalDate()))
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "تاریخ شروع از حداکثر تاریخ لغو جلوتر است!");

        }
    }


    @Transactional
    public GenericMessageDto submit(String id) {
        TblPleaserServicePriority priority = findById(id);
        pleaserServiceService.submit(priority.getPleaserServicePriorityPleaserServiceId());
        changeDraftStatus(id);
        return new GenericMessageDto("اولویت با موفقیت ارسال شد!");
    }


    @Transactional
    public void changeDraftStatus(String priorityId) {
        if (repository.changeDraftStatus(priorityId) <= 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "آپدیت پیش نویس اولویت با مشکل مواجه شد!");
    }


    public PleaserPaymentMethodDto getPaymentFields(String priorityId) {
        return repository.getPaymentFields(priorityId);
    }


    public boolean existsById(String id) {
        return repository.existsById(id);
    }


    public TblPleaserServicePriority findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "اولویت مورد نظر یافت نشد!"));
    }


    public void checkAllowedForRequest(String priorityId) {
        TblPleaserServicePriorityConfirmation confirmation = confirmationService.findByPriorityId(priorityId);

        if (confirmation == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "اولویت هنوز تایید نشده است!");
        if (confirmation.getPleaserServicePriorityConfirmationConfirm() == 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "اولویت موردنظر تایید نشده است!");

    }


    public PleaserRequestServiceResponseDto getPersonnelPriority(String id) {
        TblPleaserServicePriority priority = findById(id);
        return new PleaserRequestServiceResponseDto(priority,
                pleaserServiceService.findById(findById(id).getPleaserServicePriorityPleaserServiceId())
        );
    }


    // TODO : remove this
    public TblPleaserServicePriority findLastPriority(String pleaserServiceId) {
        return repository.findLastPriority(pleaserServiceId)
                .orElse(null);
    }

}
