package com.common;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Fan QingChuan
 * @since 2022/12/9 11:14
 */

@Data
@EqualsAndHashCode(callSuper=false)
public class BidUcUser extends UcUser {

    //节点node code
    private String bidNodeCode;
    // 1-采购过程节点经办人  2-计划经办人（采购过程经办人）（采购外层）   3-评标打分参与人
    private String type;
}
