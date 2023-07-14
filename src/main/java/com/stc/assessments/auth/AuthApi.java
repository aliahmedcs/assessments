package com.stc.assessments.auth;

import java.net.URI;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.stc.assessments.enumeration.PermissionLevel;

import com.stc.assessments.model.Permissions;

@RestController
@RequestMapping("/api/any")
public class AuthApi {
	@Autowired
	AuthenticationManager authManager;
	@Autowired
	JwtTokenUtil jwtUtil;
	@Autowired
	private com.stc.assessments.repository.PermissionsRepository repo;
	@Autowired
	private com.stc.assessments.repository.PermissionGroupsRepository permissionGroupsRepository;

	@PostMapping("/register")

	public ResponseEntity<?> register(@RequestBody @Validated AuthRequest request) {
		try {

			int strength = 10; // work factor of bcrypt
			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(strength, new SecureRandom());
			String encodedPassword = bCryptPasswordEncoder.encode(request.getPassword());
			Permissions permissions = new Permissions();
			permissions.setUserEmail(request.getUserEmail());
			permissions.setUserName(request.getUserEmail());
			permissions.setPermissionLevel(PermissionLevel.valueOf(request.getPermissionLevel()));

			permissions.setPassword(encodedPassword);

			Permissions savedUser = repo.saveAndFlush(permissions);
			URI pictureURI = URI.create("/auth/register/" + savedUser.getId());
			return ResponseEntity.created(pictureURI).body(savedUser);
		} catch (BadCredentialsException ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}

		catch (Exception e) {
			e.printStackTrace();
			Map<Integer, String> map = new HashMap();
			map.put(209, e.getMessage());
			return ResponseEntity.ok(map);
		}

	}

	@PostMapping("/login")

	public ResponseEntity<?> login(@RequestBody @Validated AuthRequest request) {
		try {
			Authentication authentication = authManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUserEmail(), request.getPassword()));

			Permissions permissions = (Permissions) authentication.getPrincipal();
			String accessToken = jwtUtil.generateAccessToken(permissions);
			AuthResponse response = new AuthResponse(permissions.getUserEmail(), accessToken);

			return ResponseEntity.ok().body(response);
		} catch (Exception e) {

			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}

}
