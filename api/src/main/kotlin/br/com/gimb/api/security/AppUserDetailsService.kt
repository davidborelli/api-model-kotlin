package br.com.gimb.api.security

import br.com.gimb.api.model.Usuario
import br.com.gimb.api.services.UsuarioService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.util.*

@Service
class AppUserDetailsService: UserDetailsService {

    @Autowired
    lateinit var usuarioService: UsuarioService

    override fun loadUserByUsername(usuarioLogin: String): UserDetails {
        val usuarioOptional = usuarioService.buscarPorUsuario(usuarioLogin)
        if (usuarioOptional!!.isEmpty)
            throw Exception("Usuário e/ou senha incorretos")

        val usuario = usuarioOptional.get()
        return User(usuario.usuario, usuario.senha, permissoes(usuario))
    }

    private fun permissoes(usuario: Usuario): Collection<GrantedAuthority> { //TODO: Implementar a busca das permissoes no BD
        val authorities = HashSet<SimpleGrantedAuthority>()
        authorities.add(SimpleGrantedAuthority("ROLE_ADMIN"))
        return authorities
    }
}
