package jp.co.axa.apidemo.models;

/**
 * Jwt token bean which is used to return jwt token to client.
 */
public class AuthenticationResponse {

	private final String jwt;

	public AuthenticationResponse(String jwt) {
		this.jwt = jwt;
	}

	public String getJwt() {
		return jwt;
	}

}
