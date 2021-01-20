package com.ibm.demo.security;

import com.ibm.demo.entity.ResponseToken;
import com.ibm.demo.entity.Role;
import com.ibm.demo.entity.User;
import com.ibm.demo.util.Helper;
import com.jwtutil.JWTUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;


public class TokenAuthenticationService {
	static final String TOKEN_PREFIX = "Bearer";
	static final String HEADER_STRING = "Authorization";


	private TokenAuthenticationService() {

	}

	public static String generateJWTToken(User user,List<Role> roles) {
		if(user==null || user.getUserName()==null || roles==null || roles.isEmpty()) {
			return "";
		}
		List<String> _roles = new ArrayList<>();
		for(Role role:roles) {
			_roles.add(role.getRole());
		}
		return generateJWTToken(user.getUserName(), _roles);
	}
	public static String generateJWTToken( String username,List<String> roller) {
		Map<String, String> param = new HashMap<>();
        param.put("username", username);
        param.put("roles",Arrays.toString(roller.toArray()).replace("[", "").replace("]",""));

		JWTUtil jwtUtil = JWTUtil.create().setEnvironment("TEST").build();//dk
        String token = "";
		try {
			token = jwtUtil.generateTokenWithJWT(param);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return token;
	}

	public static void addAuthentication(HttpServletResponse res, String username,List<String> roller) {

		String token = generateJWTToken(username, roller);
		res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + token);
		try {
			ResponseToken _token=new ResponseToken();
			_token.setAccessToken(token);
			_token.setTokenExpires(15);
			res.getOutputStream().println(Helper.convert2Json(_token));
			res.setContentType("application/json");

		} catch (IOException e) {
		}
	}

	public static Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(HEADER_STRING);

		if (token != null) {
			token = token.replace(TOKEN_PREFIX,"").trim();
			String user         = JWTUtil.getDataFromKey(token,"username");
			String strRoller    = JWTUtil.getDataFromKey(token,"roles");

			List<Role> roller = new ArrayList<>();
			Role role=null;
			if(strRoller!=null) {
				String[] arryRol = strRoller.split(",");
				for(String strRol:arryRol){
					role=new Role();
					role.setRole(strRol.trim().toUpperCase());
					roller.add(role);
				}
			}
			return user != null ? new UsernamePasswordAuthenticationToken(user, roller) : null;
		}else{
			System.out.println("Token is null");
			return null;
		}

	}
}
