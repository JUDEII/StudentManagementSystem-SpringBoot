package com.jude.StudentManagementSystem.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PagingRequest {

    @NotNull(message = "Page number is required")
    @Min(value = 0, message = "Invalid page number")
    private Integer pageNumber;
    @NotNull(message = "Page number is required")
    @Min(value = 0, message = "Invalid page size")
    private Integer pageSize;
    @Pattern(regexp = "((19|20)\\d\\d)-(0?[1-9]|1[12])-(0?[1-9]|[12][0-9]|3[01])", message = "Unsupported date format. Kindly use: 'yyyy-mm-dd'")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", lenient = false)
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String startDate;
    @Pattern(regexp = "((19|20)\\d\\d)-(0?[1-9]|1[12])-(0?[1-9]|[12][0-9]|3[01])", message = "Unsupported date format. Kindly use: 'yyyy-mm-dd'")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String endDate;

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
