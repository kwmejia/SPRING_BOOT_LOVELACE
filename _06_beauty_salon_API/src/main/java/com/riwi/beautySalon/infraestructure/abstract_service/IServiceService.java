
package com.riwi.beautySalon.infraestructure.abstract_service;

import com.riwi.beautySalon.api.dto.request.ServiceReq;
import com.riwi.beautySalon.api.dto.response.ServiceResp;

/**
 * IServiceService
 */
public interface IServiceService  
    extends CrudService<ServiceReq,ServiceResp, Long>{

    public final String FIELD_BY_SORT = "price";
}