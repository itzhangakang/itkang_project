package com.itkang.itkang_utils.utis.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
public class BeanUpdateModel implements Serializable {

    /**
     * 修改人代码
     */
    //String updateEId = UserUtils.getOperator().getOperateId();
    String updateEId = "1";

    /**
     * 修改人姓名
     */
    //String updateEName = UserUtils.getOperator().getOperateName();
    String updateEName = "1";

    /**
     * 修改时间
     */
    Date updateDate = new Date();
}
