package com.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Fan QingChuan
 * @since 2022/12/21 11:23
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseMethod{
    private String purchaseMethodName;
    private String purchaseMethodCode;
    private Boolean ifControlPriceFloor;
    private Boolean ifEvaluationStaffGteThree;
    private Boolean ifEvaluationStaffOdd;
    private Boolean ifSignMoneyControl;
    private Boolean ifStrategic;
    private Boolean ifTn;
}
