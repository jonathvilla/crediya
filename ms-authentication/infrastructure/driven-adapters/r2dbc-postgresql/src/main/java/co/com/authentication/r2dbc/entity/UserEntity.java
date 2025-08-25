package co.com.authentication.r2dbc.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import org.springframework.data.relational.core.mapping.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("users")
public class UserEntity {

    @Id
    private Long id;
    
    @Column("user_id")
    private String userId;
    
    @Column("nombres")
    private String names;
    
    @Column("apellidos")
    private String lastName;
    
    @Column("fecha_nacimiento")
    private LocalDate birthDate;
    
    @Column("tipo_documento")
    private String documentType;

    @Column("numero_documento")
    private String documentNumber;

    @Column("direccion")
    private String address;
    
    @Column("telefono")
    private String phoneNumber;
    
    @Column("correo_electronico")
    private String email;
    
    @Column("salario_base")
    private Double baseSalary;
    
    @Column("ocupacion")
    private String occupation;

    @Column("empresa")
    private String company;

    @Column("fecha_creacion")
    private LocalDateTime creationDate;

    @Column("fecha_actualizacion")
    private LocalDateTime updateDate;
}
