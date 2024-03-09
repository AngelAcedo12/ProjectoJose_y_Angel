package com.example.projectojose_y_angel.models.DTO;

import java.util.Objects;

public class ImageDeleteDTO {
    private Boolean borrado;
    private Integer id;
    private Integer posicionImagen;

    public ImageDeleteDTO(Integer id, Integer posicionImagen) {
        this.id = id;
        this.posicionImagen = posicionImagen;
        this.borrado=false;
    }

    public Boolean isBorrado() {
        return borrado;
    }

    public Integer getId() {
        return id;
    }

    public Integer getPosicionImagen() {
        return posicionImagen;
    }

    public void setBorrado(Boolean borrado) {
        this.borrado = borrado;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPosicionImagen(Integer posicionImagen) {
        this.posicionImagen = posicionImagen;
    }

    @Override
    public String toString() {
        return "ImageDeleteDTO{" +
                "borrado=" + borrado +
                ", id=" + id +
                ", posicionImagen=" + posicionImagen +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImageDeleteDTO that = (ImageDeleteDTO) o;
        return Objects.equals(borrado, that.borrado) && Objects.equals(id, that.id) && Objects.equals(posicionImagen, that.posicionImagen);
    }

    @Override
    public int hashCode() {
        return Objects.hash(borrado, id, posicionImagen);
    }
}
