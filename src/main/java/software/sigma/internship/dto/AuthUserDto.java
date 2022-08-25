package software.sigma.internship.dto;

public record AuthUserDto(
        long id,
        String username,
        String email,
        String password
) {
}
