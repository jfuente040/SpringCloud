package com.jfuente040.springcloud.msvc.items.models;

public class ItemDTO {

    private ProductDTO product;
    private Integer quantity;

    public ItemDTO() {
    }

    public ItemDTO(ProductDTO product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public ProductDTO getProduct() {
        return product;
    }
    public void setProduct(ProductDTO product) {
        this.product = product;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotal() {
        return product.getPrice() * this.quantity.doubleValue();
    }   
    
}
