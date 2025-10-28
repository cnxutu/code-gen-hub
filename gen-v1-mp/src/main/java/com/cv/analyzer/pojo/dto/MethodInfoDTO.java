package com.cv.analyzer.pojo.dto;

import lombok.Data;

import java.util.List;

/**
 * @author: xutu
 * @since: 2025/9/1 20:21
 */
@Data
public class MethodInfoDTO {

    private String methodName;
    private String returnType;
    private String comment; // 方法注释
    private List<Parameter> parameters;

    public static class Parameter {
        private String name;
        private String type;
        private List<FieldInfo> fields; // 参数对象内字段

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getType() { return type; }
        public void setType(String type) { this.type = type; }
        public List<FieldInfo> getFields() { return fields; }
        public void setFields(List<FieldInfo> fields) { this.fields = fields; }
    }

    public static class FieldInfo {
        private String name;
        private String type;
        private String comment; // 字段注释

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getType() { return type; }
        public void setType(String type) { this.type = type; }
        public String getComment() { return comment; }
        public void setComment(String comment) { this.comment = comment; }
    }

    public String getMethodName() { return methodName; }
    public void setMethodName(String methodName) { this.methodName = methodName; }
    public String getReturnType() { return returnType; }
    public void setReturnType(String returnType) { this.returnType = returnType; }
    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
    public List<Parameter> getParameters() { return parameters; }
    public void setParameters(List<Parameter> parameters) { this.parameters = parameters; }


}
