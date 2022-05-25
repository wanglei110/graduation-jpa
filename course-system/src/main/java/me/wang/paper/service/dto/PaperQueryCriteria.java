
package me.wang.paper.service.dto;

import lombok.Data;
import me.wang.annotation.Query;


/**
* @website https://el-admin.vip
* @author wang
* @date 2022-05-18
**/
@Data
public class PaperQueryCriteria{

    /** 精确 */
    @Query
    private Integer orderNo;

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String name;
}