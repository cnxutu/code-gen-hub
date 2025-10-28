/**
 * Copyright © 2019 dream horse Info. Tech Ltd. All rights reserved.
 *
 * @Package: com.github.mybatis.fl.convert
 * @author: flying-cattle
 * @date: 2019年4月9日 下午8:15:25
 */
package com.cv.generator.util;


import com.cv.generator.entity.BasisInfo;
import com.cv.generator.entity.PropertyInfo;
import com.cv.generator.entity.ResultJson;

import java.util.List;


/**
 * Copyright: Copyright (c) 2019
 *
 * <p>说明：  获取文件路径调用创建文件</P>
 *
 * @version: v3.0.0
 * @author: flying-cattle
 * <p>
 * Modification History:
 * Date         	Author          Version          Description
 * ---------------------------------------------------------------*
 * 2019年4月9日      		flying-cattle   v3.0.0           initialize
 */
public class Generator {
    //路径信息
    public static final String ENTITY = "entity";
    public static final String QUERY = "query";
    public static final String PARAM = "param";
    public static final String VO = "vo";
    public static final String DTO = "dto";
    public static final String CONVERT = "convert";
    public static final String DAO = "dao";
    public static final String DAO_IMPL = "daoImpl";
    public static final String SERVICE = "service";
    public static final String SERVICE_IMPL = "serviceImpl";
    public static final String CONTROLLER = "controller";
    public static final String SWAGGER_CONFIG = "swaggerConfig";

    //①创建实体类
    public static ResultJson createEntity(String url, BasisInfo bi) {
        String fileUrl = getGeneratorFileUrl(url, bi.getEntityUrl(), bi.getEntityName() + bi.getAddSuffix(), ENTITY);
        return FreemarkerUtil.createFile(bi, "entity.ftl", fileUrl);
    }

    //②创建DAO
    public static ResultJson createDao(String url, BasisInfo bi) {
        String fileUrl = getGeneratorFileUrl(url, bi.getDaoUrl(), bi.getEntityName(), DAO);
        return FreemarkerUtil.createFile(bi, "dao.ftl", fileUrl);
    }

    //③创建mapper配置文件
    public static ResultJson createDaoImpl(String url, BasisInfo bi) {
        String fileUrl = getGeneratorFileUrl(url, bi.getMapperUrl(), bi.getEntityName(), DAO_IMPL);
        List<PropertyInfo> list = bi.getCis();
        StringBuilder agile = new StringBuilder();
        for (PropertyInfo propertyInfo : list) {
            agile.append(propertyInfo.getColumn()).append(", ");
        }
        agile = new StringBuilder(agile.substring(0, agile.length() - 2));
        bi.setAgile(agile.toString());
        return FreemarkerUtil.createFile(bi, "mapper.ftl", fileUrl);
    }

    //④创建SERVICE
    public static ResultJson createService(String url, BasisInfo bi) {
        String fileUrl = getGeneratorFileUrl(url, bi.getServiceUrl(), bi.getEntityName(), SERVICE);
        return FreemarkerUtil.createFile(bi, "service.ftl", fileUrl);
    }

    //⑤创建SERVICE_IMPL
    public static ResultJson createServiceImpl(String url, BasisInfo bi) {
        String fileUrl = getGeneratorFileUrl(url, bi.getServiceImplUrl(), bi.getEntityName(), SERVICE_IMPL);
        return FreemarkerUtil.createFile(bi, "serviceImpl.ftl", fileUrl);
    }

    //⑥创建CONTROLLER
    public static ResultJson createController(String url, BasisInfo bi) {
//		createAbstractController( url, bi); //保证父类一直存在
        String fileUrl = getGeneratorFileUrl(url, bi.getControllerUrl(), bi.getEntityName(), CONTROLLER);
        return FreemarkerUtil.createFile(bi, "controller.ftl", fileUrl);
    }

    //⑦创建抽象的CONTROLLER
    public static ResultJson createAbstractController(String url, BasisInfo bi) {
        String fileUrl = getGeneratorFileUrl(url, bi.getAbstractControllerUrl(), "Abstract", CONTROLLER);
        return FreemarkerUtil.createFile(bi, "AbstractController.ftl", fileUrl);
    }

    //⑦创建AddQuery
    public static ResultJson createAddParam(String url, BasisInfo bi) {
        String fileUrl = getGeneratorFileUrl(url, bi.getParamUrl(), bi.getEntityName() + "AddParam", PARAM);
        return FreemarkerUtil.createFile(bi, "AddParam.ftl", fileUrl);
    }


    //创建PageQuery
    public static ResultJson createPageQuery(String url, BasisInfo bi) {
        String fileUrl = getGeneratorFileUrl(url, bi.getQueryUrl(), bi.getEntityName() + "PageQuery", QUERY);
        return FreemarkerUtil.createFile(bi, "PageQuery.ftl", fileUrl);
    }

    //创建PageVO
    public static ResultJson createPageVO(String url, BasisInfo bi) {
        String fileUrl = getGeneratorFileUrl(url, bi.getVoUrl(), bi.getEntityName() + "PageVO", VO);
        return FreemarkerUtil.createFile(bi, "PageVO.ftl", fileUrl);
    }

    //创建VO
    public static ResultJson createVO(String url, BasisInfo bi) {
        String fileUrl = getGeneratorFileUrl(url, bi.getVoUrl(), bi.getEntityName() + "VO", VO);
        return FreemarkerUtil.createFile(bi, "VO.ftl", fileUrl);
    }

    public static Object createDto(String url, BasisInfo bi) {
        String fileUrl = getGeneratorFileUrl(url, bi.getDtoUrl(), bi.getEntityName() + "DTO", DTO);
        return FreemarkerUtil.createFile(bi, "dto.ftl", fileUrl);
    }

    public static Object createConvert(String url, BasisInfo bi) {
        String fileUrl = getGeneratorFileUrl(url, bi.getConvertUrl(), bi.getEntityName() + "Convert", CONVERT);
        return FreemarkerUtil.createFile(bi, "convert.ftl", fileUrl);
    }

    //⑧创建CONTROLLER
    public static ResultJson createSwaggerConfig(String url, BasisInfo bi) {
        String fileUrl = getGeneratorFileUrl(url, bi.getSwaggerConfigUrl(), "Swagger", SWAGGER_CONFIG);
        return FreemarkerUtil.createFile(bi, "SwaggerConfig.ftl", fileUrl);
    }

    //生成文件路径和名字
    public static String getGeneratorFileUrl(String url, String packageUrl, String entityName, String type) {
        if ("entity".equals(type)) {
            return url + pageToUrl(packageUrl) + entityName + ".java";
        } else if ("query".equals(type)) {
            return url + pageToUrl(packageUrl) + entityName + ".java";
        } else if ("param".equals(type)) {
            return url + pageToUrl(packageUrl) + entityName + ".java";
        } else if ("vo".equals(type)) {
            return url + pageToUrl(packageUrl) + entityName + ".java";
        } else if ("dto".equals(type)) {
            return url + pageToUrl(packageUrl) + entityName + ".java";
        } else if ("convert".equals(type)) {
            return url + pageToUrl(packageUrl) + entityName + ".java";
        } else if ("dao".equals(type)) {
            return url + pageToUrl(packageUrl) + entityName + "Mapper.java";
        } else if ("daoImpl".equals(type)) {
            return url + pageToUrl(packageUrl) + entityName + "Mapper.xml";
        } else if ("service".equals(type)) {
            return url + pageToUrl(packageUrl) + "I" + entityName + "Service.java";
        } else if ("serviceImpl".equals(type)) {
            return url + pageToUrl(packageUrl) + entityName + "ServiceImpl.java";
        } else if ("controller".equals(type)) {
            return url + pageToUrl(packageUrl) + entityName + "Controller.java";
        } else if ("swaggerConfig".equals(type)) {
            return url + pageToUrl(packageUrl) + entityName + "Config.java";
        }
        return null;
    }

    public static String pageToUrl(String url) {
        return url.replace(".", "/") + "/";
    }

}
