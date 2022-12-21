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
public class Project{
    private String projectId;
    private String projectName;
}