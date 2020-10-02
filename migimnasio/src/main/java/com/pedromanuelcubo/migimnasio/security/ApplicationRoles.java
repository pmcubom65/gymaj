package com.pedromanuelcubo.migimnasio.security;

import java.util.Set;
import com.google.common.collect.Sets;

public enum ApplicationRoles {

	USUARIO(Sets.newHashSet(	
			ApplicationPermission.ACTIVIDAD_READ,
			ApplicationPermission.ACTIVIDAD_WRITE,
			ApplicationPermission.ENTRENAMIENTO_READ,
			ApplicationPermission.ENTRENAMIENTO_WRITE)),
	
	ENTRENADOR(Sets.newHashSet(
			ApplicationPermission.ACTIVIDAD_READ,
			ApplicationPermission.ACTIVIDAD_WRITE,
			ApplicationPermission.ENTRENAMIENTO_READ,
			ApplicationPermission.ENTRENAMIENTO_WRITE
			)),
	ADMIN(Sets.newHashSet(
			
			ApplicationPermission.USUARIO_READ,
			ApplicationPermission.USUARIO_WRITE,
//			ENTRENADOR_READ("entrenador:read"),
			ApplicationPermission.ENTRENADOR_WRITE,
			ApplicationPermission.ACTIVIDAD_READ,
			ApplicationPermission.ACTIVIDAD_WRITE,
			ApplicationPermission.ENTRENAMIENTO_READ,
			ApplicationPermission.ENTRENAMIENTO_WRITE
			
			
			
			
			
			
			));
	
	private final Set<ApplicationPermission> permissions;

	private ApplicationRoles(Set<ApplicationPermission> permissions) {
		this.permissions = permissions;
	}

	public Set<ApplicationPermission> getPermissions() {
		return permissions;
	}
	
	
	
}
