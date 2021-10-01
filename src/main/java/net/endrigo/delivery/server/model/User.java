package net.endrigo.delivery.server.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.endrigo.delivery.server.model.converter.UserStatusConverter;
import net.endrigo.delivery.server.model.enumeration.RoleEnum;
import net.endrigo.delivery.server.model.enumeration.UserStatusEnum;


@Entity
@Table(	name = "USERS", 
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "email") 
		})
@Inheritance ( strategy = InheritanceType.SINGLE_TABLE )
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", unique = true, updatable = false, nullable = false)
	private UUID id;

	@NotBlank
	@Size(max = 128)
	@Column(length = 128, nullable = false)
	private String name;

	@NotBlank
	@Size(max = 128)
	@Email
	@Column(length = 128, nullable = false, unique = true)
	private String email;

	@NotBlank
	@Column(length = 128, nullable = false)
	private String password;


	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private RoleEnum role;
	
    @Convert(converter = UserStatusConverter.class)
    @Column(length = 1, nullable = false)
	private UserStatusEnum status = UserStatusEnum.PENDING;

	public User(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}

}
