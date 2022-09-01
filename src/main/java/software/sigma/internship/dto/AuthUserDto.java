package software.sigma.internship.dto;

public record AuthUserDto(
        long id,
        String name,
        String email,
        String password
) {
}
