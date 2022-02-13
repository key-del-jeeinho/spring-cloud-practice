package com.velocia.gatewayserver.global.data;

import com.velocia.gatewayserver.global.data.type.FilterUsage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class FilterConfig {
    private FilterUsage usage;
}
