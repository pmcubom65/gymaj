package com.pedromanuelcubo.migimnasio.security;

public enum ApplicationPermission {
	
	USUARIO_READ("usuario:read"),
	USUARIO_WRITE("usuario:write"),
//	ENTRENADOR_READ("entrenador:read"),
	ENTRENADOR_WRITE("entrenador:write"),
	ACTIVIDAD_READ("actividad:read"),
	ACTIVIDAD_WRITE("actividad:write"),
	ENTRENAMIENTO_READ("entrenamiento:read"),
	ENTRENAMIENTO_WRITE("entrenamiento:write");
	
	private final String permission;

	private ApplicationPermission(String permission) {
		this.permission = permission;
	}

	public String getPermission() {
		return permission;
	}
	


}
