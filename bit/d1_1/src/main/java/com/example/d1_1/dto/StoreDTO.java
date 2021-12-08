package com.example.d1_1.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class StoreDTO {

    private Long sno;
    private String name;
    private double lat, lng;

}
