package com.akrams.cafemanagement.wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserWrapper {

    private Integer id;
    private String name;
    private String email;
    private String role;
    private String contactNumber;
}
