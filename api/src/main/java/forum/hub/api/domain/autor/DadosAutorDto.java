package forum.hub.api.domain.autor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosAutorDto(

        @NotBlank(message = "Nome é obrigatório.")
        String nome,
        @NotBlank(message = "Email é obrigatório.")
        @Email(message = "Formato do email é inválido")
        String email,
        @NotBlank(message = "Senha é obrigatória.")
        String senha,
        @NotBlank(message = "Perfil é obrigatório.")
        String perfis
) {
}
