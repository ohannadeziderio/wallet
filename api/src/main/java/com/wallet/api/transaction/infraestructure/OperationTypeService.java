package com.wallet.api.transaction.infraestructure;

import com.wallet.api.account.domain.Account;
import com.wallet.api.transaction.domain.OperationType;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class OperationTypeService {

    OperationTypeRepository operationTypeRepository;

    public OperationTypeService(OperationTypeRepository operationTypeRepository) {
        this.operationTypeRepository = operationTypeRepository;
    }

    public OperationType findById(int operationTypeId) {
        OperationType operationType = operationTypeRepository.findById(operationTypeId).orElseThrow(EntityNotFoundException::new);

        return operationType;
    }

}
