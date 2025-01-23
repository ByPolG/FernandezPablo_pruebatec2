package com.example.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Turno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer numero;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecha;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "ciudadano_id", nullable = false)
    private Ciudadano ciudadano;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Estado estado;

    // Para comprobar que los estados son válidos

    public enum Estado {
        EN_ESPERA("En espera"),
        YA_ATENDIDO("Ya atendido");

        private final String descripcion;

        Estado(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public static Estado fromString(String estadoStr) {
            if (estadoStr != null) {
                for (Estado estado : Estado.values()) {
                    if (estadoStr.equalsIgnoreCase(estado.name())) {
                        return estado;
                    }
                }
            }
            return null; // Lanzamos una excepción personalizada si el estado es inválido
        }
    }

    public Turno() {

    }

    public Turno(Integer numero, Date fecha, String descripcion, Ciudadano ciudadano, Estado estado) {
        this.numero = numero;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.ciudadano = ciudadano;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Ciudadano getCiudadano() {
        return ciudadano;
    }

    public void setCiudadano(Ciudadano ciudadano) {
        this.ciudadano = ciudadano;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Turno{" +
                "id=" + id +
                ", numero=" + numero +
                ", fecha=" + fecha +
                ", descripcion='" + descripcion + '\'' +
                ", ciudadano=" + ciudadano.getNombre() + " " + ciudadano.getApellido() +  // Para mostrar el nombre y apellidos del ciudadano combinados
                ", estado=" + estado +
                '}';
    }
}
