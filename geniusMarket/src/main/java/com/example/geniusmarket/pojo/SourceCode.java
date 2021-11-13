package com.example.geniusmarket.pojo;

import java.util.Objects;

public class SourceCode {
    private  int codeId;
    private  String filePath;
    private String moduleName;
    private String code;
    public SourceCode(){}

    public SourceCode(int codeId, String filePath, String moduleName, String code) {
        this.codeId = codeId;
        this.filePath = filePath;
        this.moduleName = moduleName;
        this.code = code;
    }

    public int getCodeId() {
        return codeId;
    }

    public void setCodeId(int codeId) {
        this.codeId = codeId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SourceCode)) return false;
        SourceCode that = (SourceCode) o;
        return codeId == that.codeId && Objects.equals(filePath, that.filePath) && Objects.equals(moduleName, that.moduleName) && Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codeId, filePath, moduleName, code);
    }

    @Override
    public String toString() {
        return "SourceCode{" +
                "codeId=" + codeId +
                ", filePath='" + filePath + '\'' +
                ", moduleName='" + moduleName + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
