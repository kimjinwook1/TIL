package com.example.d1_1.domain;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class StoreVO {

    private Long sno;
    private String name;
    private double lat, lng;
}
