package com.foodorder.domain;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Item {

    private Producto producto;
    private Integer cantidad;

    public BigDecimal getSubtotal() {
        return producto.getPrecio().multiply(BigDecimal.valueOf(cantidad));
    }

}
