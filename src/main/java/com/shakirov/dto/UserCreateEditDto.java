package com.shakirov.dto;

import com.shakirov.validation.UserInfo;
import com.shakirov.model.Role;
import com.shakirov.validation.group.CreateAction;
import lombok.Value;
import lombok.experimental.FieldNameConstants;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * @author igor@shakirov-dev.ru on 26.12.2022
 * @version 1.0
 */

@Value
@FieldNameConstants  // Создает внутренний тип, содержащий константы String, содержащие имя поля для каждого
// поля. Кроме того, создается внутренняя перечисление со значениями перечисления, соответствующими
// имени каждого поля.
@UserInfo (groups = CreateAction.class)
public class UserCreateEditDto {

    @Email
    String username;
    @NotBlank(groups = CreateAction.class)
    String rawPassword;

    //    @DateTimeFormat(pattern = "yyyy-MM-dd")  // Для установки формата даты, переделали на конвертер в WebConfig
    LocalDate birthDate;

//    @NotNull
    @Size(min = 3, max = 64)
    String firstname;
//    @NotNull
    String lastname;
    Role role;
    Integer companyId;
    
    MultipartFile image;

//    public static final class Fields {
//        public static final String username = "username";
//        public static final String birthDate = "birthDate";
//        public static final String firstname = "firstname";
//        public static final String lastname = "lastname";
//        public static final String role = "role";
//        public static final String companyId = "companyId";
//    }
}
