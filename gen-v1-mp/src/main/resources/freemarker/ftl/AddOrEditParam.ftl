/**
 * Copyright (c) 2025 Tu Personal Research
 * All rights reserved.
 */
package ${paramUrl};

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**   
 * ${entityComment} 新增接口入参
 *
 * @author ${author}
 * @date ${createTime}
 */
@Data
public class ${entityName}AddOrEditParam {

    /**
     * 主键 id
     */
    private Long id;

<#list cis as ci>
    /**
     * ${ci.comment}
     */
    <#if ci.javaType!="String">
    @NotNull(message = "${ci.comment}不能为空")
    </#if>
    <#if ci.javaType=="String">
    @NotBlank(message = "${ci.comment}不能为空")
    </#if>
    <#-- 针对 LocalDate 查询字段 -->
    <#if ci.javaType=="LocalDate">
    <#if ci.notNull?? && ci.notNull>
    @NotNull(message="${ci.columnComment}不能为空")
    </#if>
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    <#elseif ci.javaType=="LocalDateTime">
    <#if ci.notNull?? && ci.notNull>
    @NotNull(message="${ci.columnComment}不能为空")
    </#if>
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    </#if>
	private ${ci.javaType} ${ci.property};
</#list>

}
	