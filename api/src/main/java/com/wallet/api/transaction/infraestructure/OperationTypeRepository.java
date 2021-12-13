package com.wallet.api.transaction.infraestructure;

import com.wallet.api.transaction.domain.OperationType;
import org.springframework.data.repository.CrudRepository;

public interface OperationTypeRepository extends CrudRepository<OperationType, Integer> {

}
