package org.adaschool.TaskPlanner.config

import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.SignatureException
import org.adaschool.TaskPlanner.utils.CLAIMS_ROLES_KEY
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtFilter(@Value("\${app.secret}") val secret: String) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        val authHeader = request.getHeader(HttpHeaders.AUTHORIZATION)

        if (HttpMethod.OPTIONS.name == request.method) {
            response.status = HttpServletResponse.SC_OK
            return filterChain.doFilter(request, response)
        } else {
            try {
                val jwtCookie = request.cookies?.find { it.name == "ac-JWT" }
                val headerJWT = if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader.substring(7)
                } else null

                val token = if (jwtCookie != null) jwtCookie.value else headerJWT

                if (token != null) {
                    val claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token)
                    val claimsBody = claims.body
                    val subject = claimsBody.subject
                    val rolesInBody = claims.body[CLAIMS_ROLES_KEY]

                    val roles = if (rolesInBody is List<*>) rolesInBody.filterIsInstance<String>() else null

                    if (roles == null) {
                        response.sendError(HttpStatus.UNAUTHORIZED.value(), "Invalid token roles")
                    } else {
                        SecurityContextHolder.getContext().authentication = TokenAuthentication(token, subject, roles)
                    }

                    request.setAttribute("claims", claimsBody)
                    request.setAttribute("jwtUserId", subject)
                    request.setAttribute("jwtUserRoles", roles)
                }

                filterChain.doFilter(request, response)
            } catch (e: SignatureException) {
                response.sendError(HttpStatus.UNAUTHORIZED.value(), "Invalid token")
            } catch (e: MalformedJwtException) {
                response.sendError(HttpStatus.BAD_REQUEST.value(), "Missing or wrong token")
            } catch (e: ExpiredJwtException) {
                response.sendError(HttpStatus.UNAUTHORIZED.value(), "Token expired or malformed")
            } catch (e: AccessDeniedException) {
                response.sendError(HttpStatus.FORBIDDEN.value(), "Access denied")
            }
        }
    }
}