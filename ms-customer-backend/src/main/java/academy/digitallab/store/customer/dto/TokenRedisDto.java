package academy.digitallab.store.customer.dto;

import lombok.Data;

@Data
public class TokenRedisDto {
    String username;
    Long dateTime;
}