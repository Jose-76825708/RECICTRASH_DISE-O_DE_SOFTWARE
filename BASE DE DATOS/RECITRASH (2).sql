-- BASE DE DATOS RECICTRASH: 

CREATE DATABASE RecicTrashDB;
USE RecicTrashDB;


-- TABLAS:

CREATE TABLE dbo.usuarios (
    usuario_id INT IDENTITY(1,1) PRIMARY KEY,
    rol VARCHAR(50) NOT NULL, 
    correo_usuario NVARCHAR(255) NOT NULL UNIQUE,
    contrasena_usuario NVARCHAR(512) NOT NULL,
    nombre_completo NVARCHAR(200) NULL,
    telefono NVARCHAR(32) NULL,
    activo BIT NOT NULL DEFAULT 1,
    consentimiento_datos BIT NOT NULL DEFAULT 0,
    puntos_acumulados INT NOT NULL DEFAULT 0,
    fecha_creacion DATETIMEOFFSET NOT NULL DEFAULT SYSDATETIMEOFFSET(),
    ultimo_login DATETIMEOFFSET NULL
);

CREATE TABLE dbo.centros (
    centro_id INT IDENTITY(1,1) PRIMARY KEY,
    nombre NVARCHAR(200) NOT NULL,
    direccion NVARCHAR(400) NULL,
    lat DECIMAL(10,7) NULL,
    lon DECIMAL(10,7) NULL,
    capacidad INT NULL,
    contacto_nombre NVARCHAR(200) NULL,
    contacto_correo NVARCHAR(200) NULL,
    contacto_telefono NVARCHAR(32) NULL,
    horario_atencion NVARCHAR(200) NULL,
    activo BIT NOT NULL DEFAULT 1,
);

CREATE TABLE dbo.reportes (
    reporte_id INT IDENTITY(1,1) PRIMARY KEY,
    reportante_id INT NOT NULL REFERENCES dbo.usuarios(usuario_id),
    centro_id INT NULL REFERENCES dbo.centros(centro_id),
    tipo_residuo_texto NVARCHAR(100) NULL,
    descripcion NVARCHAR(MAX) NULL,
    latitud DECIMAL(10,7) NULL,
    longitud DECIMAL(10,7) NULL,
    etiqueta_ia_predicha NVARCHAR(100) NULL,
    confianza_ia DECIMAL(5,4) NULL,
    peso_estimado_kg DECIMAL(9,2) NULL,
    prioridad INT NOT NULL DEFAULT 0,
    estado NVARCHAR(32) NOT NULL DEFAULT 'pendiente',
    fechacreacion_reporte DATETIMEOFFSET NOT NULL DEFAULT SYSDATETIMEOFFSET(),
    fechaactualizacion_reporte DATETIMEOFFSET NOT NULL DEFAULT SYSDATETIMEOFFSET()
);

CREATE TABLE dbo.fotos_reporte (
    foto_id INT IDENTITY(1,1) PRIMARY KEY,
    reporte_id INT NOT NULL REFERENCES dbo.reportes(reporte_id) ON DELETE CASCADE,
    url_archivo NVARCHAR(2000) NOT NULL,
    nombre_archivo NVARCHAR(255) NULL,
    tipo_archivo NVARCHAR(100) NULL,
    codigo_verificacion_archivo NVARCHAR(128) NULL,
    tamano_bytes BIGINT NULL,
    fecha_cargado DATETIMEOFFSET NOT NULL DEFAULT SYSDATETIMEOFFSET()
);

CREATE TABLE dbo.historial_estado_reporte (
    historial_id INT IDENTITY(1,1) PRIMARY KEY,
    reporte_id INT NOT NULL REFERENCES dbo.reportes(reporte_id) ON DELETE CASCADE,
    estado_anterior NVARCHAR(32) NULL,
    estado_nuevo NVARCHAR(32) NOT NULL,
    cambiado_por INT NULL REFERENCES dbo.usuarios(usuario_id),
    fecha_cambio DATETIMEOFFSET NOT NULL DEFAULT SYSDATETIMEOFFSET(),
    motivo_cambio NVARCHAR(200) NULL,
    nota NVARCHAR(MAX) NULL
);

CREATE TABLE dbo.perfil_reciclador (
    reciclador_id INT PRIMARY KEY REFERENCES dbo.usuarios(usuario_id),
    vehiculo NVARCHAR(100) NULL,
    disponibilidad BIT NOT NULL DEFAULT 1,
    ultima_actividad DATETIMEOFFSET NULL,
    fuente_ubicacion NVARCHAR(50) NULL,
    total_kilos_recolectados DECIMAL(9,2) NOT NULL DEFAULT 0.00
);

CREATE TABLE dbo.asignaciones (
    asignacion_id INT IDENTITY(1,1) PRIMARY KEY,
    reporte_id INT NOT NULL UNIQUE REFERENCES dbo.reportes(reporte_id) ON DELETE CASCADE,
    reciclador_id INT NULL REFERENCES dbo.perfil_reciclador(reciclador_id),
    estado_asignacion NVARCHAR(32) NOT NULL DEFAULT 'pendiente',
    fecha_asignacion DATETIMEOFFSET NOT NULL DEFAULT SYSDATETIMEOFFSET(),
    fecha_aceptado DATETIMEOFFSET NULL,
    fecha_completado DATETIMEOFFSET NULL,
    fecha_rechazado DATETIMEOFFSET NULL,
    motivo_rechazo NVARCHAR(200) NULL,
    notas NVARCHAR(MAX) NULL
);

CREATE TABLE dbo.notificaciones (
    notificacion_id INT IDENTITY(1,1) PRIMARY KEY,
    usuario_id INT NULL REFERENCES dbo.usuarios(usuario_id),
    canal NVARCHAR(32) NOT NULL, -- 'push','email' (si en el futuro)
    tipo NVARCHAR(64) NULL,
    mensaje NVARCHAR(500) NULL,
    detalle_notificacion NVARCHAR(MAX) NULL,
    intentos_envio INT NOT NULL DEFAULT 0,
    fecha_enviado DATETIMEOFFSET NULL,
    estado_notificacion NVARCHAR(32) NOT NULL DEFAULT 'pendiente',
    fecha_creacion_notificacion DATETIMEOFFSET NOT NULL DEFAULT SYSDATETIMEOFFSET()
);



CREATE TABLE dbo.transacciones_recompensas (
    transaccion_id INT IDENTITY(1,1) PRIMARY KEY,
    usuario_id INT NOT NULL REFERENCES dbo.usuarios(usuario_id),
    cambio_puntos INT NOT NULL,
    motivo NVARCHAR(200) NULL,
    reporte_origen_id INT NULL REFERENCES dbo.reportes(reporte_id),
    tipo_recompensa NVARCHAR(50) NOT NULL DEFAULT 'puntos',
    recompensa_codigo NVARCHAR(50) NULL,
    es_monetaria BIT NOT NULL DEFAULT 0,
    estado_transaccion NVARCHAR(32) NOT NULL DEFAULT 'confirmado',
    fecha_transaccion DATETIMEOFFSET NOT NULL DEFAULT SYSDATETIMEOFFSET()
);

CREATE TABLE dbo.detecciones_ia (
    ia_id INT IDENTITY(1,1) PRIMARY KEY,
    reporte_id INT NULL REFERENCES dbo.reportes(reporte_id),
    modelo_ia NVARCHAR(100) NULL,
    etiqueta_predicha NVARCHAR(100) NULL,
    confianza DECIMAL(5,4) NULL,
    fuente_imagen_id INT NULL REFERENCES dbo.fotos_reporte(foto_id),
    fecha_procesamiento DATETIMEOFFSET NOT NULL DEFAULT SYSDATETIMEOFFSET()
);

CREATE TABLE dbo.logs_auditoria (
    log_id INT IDENTITY(1,1) PRIMARY KEY,
    tabla NVARCHAR(128) NOT NULL,
    registro_id INT NULL,
    operacion_hecha NVARCHAR(16) NOT NULL,
    responsable_cambio INT NULL REFERENCES dbo.usuarios(usuario_id),
    fecha_cambio DATETIMEOFFSET NOT NULL DEFAULT SYSDATETIMEOFFSET(),
    valor_anterior NVARCHAR(400) NULL,
    valor_nuevo NVARCHAR(400) NULL
);


-- ÍNDICES PARA LAS TABLAS:

CREATE NONCLUSTERED INDEX IX_usuarios_correo ON dbo.usuarios(correo_usuario);
CREATE NONCLUSTERED INDEX IX_usuarios_rol_activo ON dbo.usuarios(rol, activo) INCLUDE (nombre_completo, telefono);
CREATE NONCLUSTERED INDEX IX_usuarios_ultimo_login ON dbo.usuarios(ultimo_login) INCLUDE (correo_usuario);

CREATE NONCLUSTERED INDEX IX_centros_geo ON dbo.centros(lat, lon) INCLUDE (nombre, direccion, activo);
CREATE NONCLUSTERED INDEX IX_centros_nombre ON dbo.centros(nombre);

CREATE NONCLUSTERED INDEX IX_reportes_estado ON dbo.reportes(estado) INCLUDE (reporte_id, prioridad, fechaactualizacion_reporte);
CREATE NONCLUSTERED INDEX IX_reportes_reportante_prioridad ON dbo.reportes(reportante_id, prioridad) INCLUDE (estado, fechacreacion_reporte);
CREATE NONCLUSTERED INDEX IX_reportes_fecha ON dbo.reportes(fechacreacion_reporte) INCLUDE (reporte_id, estado, reportante_id);

CREATE NONCLUSTERED INDEX IX_fotos_reporte_reporte ON dbo.fotos_reporte(reporte_id) INCLUDE (foto_id, url_archivo, nombre_archivo);
CREATE NONCLUSTERED INDEX IX_fotos_reporte_hash ON dbo.fotos_reporte(codigo_verificacion_archivo) INCLUDE (reporte_id, foto_id);

CREATE NONCLUSTERED INDEX IX_historial_reporte_reporte ON dbo.historial_estado_reporte(reporte_id) INCLUDE (historial_id, fecha_cambio, estado_nuevo);
CREATE NONCLUSTERED INDEX IX_historial_reporte_cambiado_por ON dbo.historial_estado_reporte(cambiado_por) INCLUDE (reporte_id, fecha_cambio);

CREATE NONCLUSTERED INDEX IX_perfil_reciclador_disponibilidad ON dbo.perfil_reciclador(disponibilidad) INCLUDE (reciclador_id, ultima_actividad, total_kilos_recolectados);
CREATE NONCLUSTERED INDEX IX_perfil_reciclador_ultima_actividad ON dbo.perfil_reciclador(ultima_actividad) INCLUDE (reciclador_id, disponibilidad);

CREATE NONCLUSTERED INDEX IX_asignaciones_estado ON dbo.asignaciones(estado_asignacion) INCLUDE (asignacion_id, reporte_id, reciclador_id);
CREATE NONCLUSTERED INDEX IX_asignaciones_reciclador ON dbo.asignaciones(reciclador_id) INCLUDE (asignacion_id, reporte_id, estado_asignacion);
CREATE NONCLUSTERED INDEX IX_asignaciones_pendientes ON dbo.asignaciones(reporte_id) WHERE estado_asignacion = 'pendiente';

CREATE NONCLUSTERED INDEX IX_notificaciones_usuario ON dbo.notificaciones(usuario_id) INCLUDE (notificacion_id, estado_notificacion, fecha_creacion_notificacion);
CREATE NONCLUSTERED INDEX IX_notificaciones_estado_creacion ON dbo.notificaciones(estado_notificacion, fecha_creacion_notificacion) INCLUDE (usuario_id);

CREATE NONCLUSTERED INDEX IX_transacciones_usuario_fecha ON dbo.transacciones_recompensas(usuario_id, fecha_transaccion) INCLUDE (transaccion_id, cambio_puntos);

CREATE NONCLUSTERED INDEX IX_detecciones_reporte_fecha ON dbo.detecciones_ia(reporte_id, fecha_procesamiento) INCLUDE (ia_id, etiqueta_predicha, confianza);

CREATE NONCLUSTERED INDEX IX_logs_fecha_tabla ON dbo.logs_auditoria(fecha_cambio) INCLUDE (tabla, registro_id, operacion_hecha);


-- PROCEDIMIENTOS ALMACENADOS:

--MOSTRAR DATOS DE USUARIO
CREATE PROCEDURE dbo.MostrarUsuarios
AS
BEGIN
	SELECT
		usuario_id,
		nombre_completo,
		rol
	from usuarios
END
--hallar reportes y ubicacion en base a su categoria

CREATE PROCEDURE dbo.ObtenerReportesUbicacionFiltro
    @tipo_residuo NVARCHAR(100) = NULL  -- parámetro opcional
AS
BEGIN
    SET NOCOUNT ON;

    SELECT 
        descripcion,
        latitud,
        longitud,
        tipo_residuo_texto
    FROM dbo.reportes
    WHERE latitud IS NOT NULL 
      AND longitud IS NOT NULL
      AND (@tipo_residuo IS NULL OR tipo_residuo_texto = @tipo_residuo);
END
GO

--Hallar reportes y ubicacion
CREATE PROCEDURE dbo.ObtenerReportesUbicacion
AS
BEGIN
    SET NOCOUNT ON;

    SELECT 
        descripcion,
        latitud,
        longitud
    FROM dbo.reportes
    WHERE latitud IS NOT NULL AND longitud IS NOT NULL; -- opcional: solo registros con coordenadas

END
GO

select * from reportes

-- Hallar el rol del usuario
CREATE PROCEDURE dbo.ObtenerRolPorCorreo
    @CorreoUsuario NVARCHAR(255)
AS
BEGIN
    SET NOCOUNT ON;

    SELECT rol
    FROM dbo.usuarios
    WHERE correo_usuario = @CorreoUsuario;
END;


-- Hallar el nombre del usuario
CREATE PROCEDURE dbo.ObtenerNombrePorCorreo
    @CorreoUsuario NVARCHAR(255)
AS
BEGIN
    SET NOCOUNT ON;

    SELECT nombre_completo
    FROM dbo.usuarios
    WHERE correo_usuario = @CorreoUsuario;
END;


select * from reportes


--Hallar ID del usuario
CREATE PROCEDURE dbo.ObtenerIDPorNombre
    @Nombre_completo NVARCHAR(255)
AS
BEGIN
    SET NOCOUNT ON;

    SELECT usuario_id
    FROM dbo.usuarios
    WHERE nombre_completo = @Nombre_completo;
END;

-- Registro de usuario

ALTER PROCEDURE dbo.sp_registrar_usuario
    @correo_usuario NVARCHAR(50),
    @nombre VARCHAR(80),
    @contrasenia VARCHAR(MAX),
    @rol VARCHAR(10),
    @telefono VARCHAR(9),
    @consentimiento INT
AS
BEGIN
    SET NOCOUNT ON;

    IF EXISTS (SELECT 1 FROM dbo.usuarios WHERE correo_usuario = @correo_usuario)
    BEGIN
        -- Puedes manejar esto devolviendo NULL o lanzando error; ahora solo salimos
        RETURN;
    END

    INSERT INTO usuarios(correo_usuario, nombre_completo, contrasena_usuario, rol, telefono, activo, consentimiento_datos, puntos_acumulados, fecha_creacion, ultimo_login)
    VALUES(@correo_usuario, @nombre, @contrasenia, @rol, @telefono, 1, @consentimiento, 0, SYSUTCDATETIME(), SYSUTCDATETIME());

    -- Devuelve el id insertado en forma de result set
    SELECT CAST(SCOPE_IDENTITY() AS INT) AS NuevoId;
END;

-- Inicio de sesión
CREATE PROCEDURE dbo.sp_login_usuario
    @correo_usuario NVARCHAR(255),
    @contrasenia NVARCHAR(512)
AS
BEGIN
    SET NOCOUNT ON;

    IF EXISTS (
        SELECT 1 
        FROM dbo.usuarios
        WHERE correo_usuario = @correo_usuario
          AND contrasena_usuario = @contrasenia
    )
	BEGIN
        PRINT 'ta bien';
		RETURN 0;
		END
    ELSE
	BEGIN
        PRINT 'ta malito';
		RETURN -1;
	END
END;

select * from reportes


--Crear reporte
-- Crear o modificar reporte con tipo de residuo
ALTER PROCEDURE dbo.sp_crear_reporte
    @reportante_id INT,
    @descripcion NVARCHAR(MAX) = NULL,
    @latitud DECIMAL(10,7) = NULL,
    @longitud DECIMAL(10,7) = NULL,
    @peso_estimado_kg DECIMAL(9,2) = NULL,
    @tipo_residuo_texto NVARCHAR(100) = NULL -- nuevo parámetro
AS
BEGIN
    SET NOCOUNT ON;
    BEGIN TRY
        IF NOT EXISTS (SELECT 1 FROM dbo.usuarios WHERE usuario_id = @reportante_id AND activo = 1)
            THROW 50001, 'Reportante no existe o no está activo.', 1;

        BEGIN TRANSACTION;

        DECLARE @centro_id INT = NULL;
        IF @latitud IS NOT NULL AND @longitud IS NOT NULL
        BEGIN
            SELECT TOP (1) @centro_id = centro_id
            FROM dbo.centros
            WHERE activo = 1 AND lat IS NOT NULL AND lon IS NOT NULL
            ORDER BY (POWER(CAST(lat - @latitud AS FLOAT),2) + POWER(CAST(lon - @longitud AS FLOAT),2));
        END

        INSERT INTO dbo.reportes (
            reportante_id, centro_id, tipo_residuo_texto, descripcion,
            latitud, longitud, etiqueta_ia_predicha, confianza_ia,
            peso_estimado_kg, prioridad, estado, fechacreacion_reporte, fechaactualizacion_reporte
        )
        VALUES (
            @reportante_id, @centro_id, @tipo_residuo_texto, @descripcion,
            @latitud, @longitud, NULL, NULL,
            @peso_estimado_kg, 0, N'pendiente', SYSDATETIMEOFFSET(), SYSDATETIMEOFFSET()
        );

        DECLARE @reporte_id INT = SCOPE_IDENTITY();

        INSERT INTO dbo.historial_estado_reporte (
            reporte_id, estado_anterior, estado_nuevo, cambiado_por, fecha_cambio, motivo_cambio
        )
        VALUES (
            @reporte_id, NULL, N'pendiente', @reportante_id, SYSDATETIMEOFFSET(), N'Creación inicial por ciudadano'
        );

        INSERT INTO dbo.notificaciones (
            usuario_id, canal, tipo, mensaje, detalle_notificacion, estado_notificacion, fecha_creacion_notificacion
        )
        VALUES (
            @reportante_id,
            N'push',
            N'reporte_creado',
            CONCAT(N'Tu reporte #', @reporte_id, N' fue creado y está pendiente.'),
            N'',
            N'pendiente',
            SYSDATETIMEOFFSET()
        );

        COMMIT TRANSACTION;

        SELECT @reporte_id AS reporte_id;
    END TRY
    BEGIN CATCH
        IF XACT_STATE() <> 0
            ROLLBACK TRANSACTION;
        DECLARE @ErrMsg NVARCHAR(4000) = ERROR_MESSAGE();
        DECLARE @ErrNum INT = ERROR_NUMBER();
        THROW @ErrNum, @ErrMsg, 1;
    END CATCH
END


--Guardar respuestas de la IA
CREATE PROCEDURE dbo.sp_guardar_deteccion_ia
    @reporte_id INT,
    @modelo_ia NVARCHAR(100),
    @etiqueta_predicha NVARCHAR(100),
    @confianza DECIMAL(5,4) = NULL,
    @fuente_imagen_id INT = NULL
AS
BEGIN
    SET NOCOUNT ON;
    BEGIN TRY
        IF NOT EXISTS (SELECT 1 FROM dbo.reportes WHERE reporte_id = @reporte_id)
            THROW 50011, 'Reporte no encontrado.', 1;

        INSERT INTO dbo.detecciones_ia (reporte_id, modelo_ia, etiqueta_predicha, confianza, fuente_imagen_id, fecha_procesamiento)
        VALUES (@reporte_id, @modelo_ia, @etiqueta_predicha, @confianza, @fuente_imagen_id, SYSDATETIMEOFFSET());

        IF @confianza IS NOT NULL
        BEGIN
            UPDATE dbo.reportes
            SET etiqueta_ia_predicha = @etiqueta_predicha,
                confianza_ia = @confianza,
                fechaactualizacion_reporte = SYSDATETIMEOFFSET()
            WHERE reporte_id = @reporte_id
              AND (confianza_ia IS NULL OR @confianza > confianza_ia);
        END
        ELSE
        BEGIN
            UPDATE dbo.reportes
            SET etiqueta_ia_predicha = COALESCE(etiqueta_ia_predicha, @etiqueta_predicha),
                fechaactualizacion_reporte = SYSDATETIMEOFFSET()
            WHERE reporte_id = @reporte_id
              AND (etiqueta_ia_predicha IS NULL OR etiqueta_ia_predicha = '');
        END

        SELECT SCOPE_IDENTITY() AS deteccion_ia_id;
    END TRY
    BEGIN CATCH
        DECLARE @ErrMsg NVARCHAR(4000) = ERROR_MESSAGE();
        DECLARE @ErrNum INT = ERROR_NUMBER();
        THROW @ErrNum, @ErrMsg, 1;
    END CATCH
END;

-- Asignaciones solicitudes
CREATE PROCEDURE dbo.sp_asignar_solicitudes
    @reporte_id INT,
    @reciclador_id INT
AS
BEGIN
    SELECT TOP 1 @reporte_id = r.reporte_id
    FROM reportes as r
    LEFT JOIN asignaciones AS a ON r.reporte_id = a.reporte_id
    WHERE r.estado = 'pendiente'
      AND a.reporte_id IS NULL
    ORDER BY r.fechacreacion_reporte;

    IF @reporte_id IS NULL
    BEGIN
        PRINT 'No hay reportes pendientes para asignar.';
        RETURN;
    END

    SELECT TOP 1 @reciclador_id = pr.reciclador_id
    FROM perfil_reciclador AS pr
    WHERE pr.disponibilidad = 1
    ORDER BY pr.ultima_actividad DESC;

    IF @reciclador_id IS NULL
    BEGIN
        PRINT 'No hay recicladores disponibles.';
        RETURN;
    END

    INSERT INTO asignaciones (reporte_id, reciclador_id, estado_asignacion)
    VALUES (@reporte_id, @reciclador_id, 'pendiente');

    INSERT INTO notificaciones (usuario_id, canal, tipo, mensaje)
    VALUES (@reciclador_id, 'email', 'asignacion', CONCAT('Tienes una nueva solicitud: reporte #', @reporte_id));

    PRINT CONCAT('Reporte ', @reporte_id, ' asignado al reciclador ', @reciclador_id);
END;

    -- Respuesta del reciclador
    CREATE PROCEDURE dbo.sp_respuesta_reciclador
        @asignacion_id INT,
        @rpta BIT
    AS
    BEGIN
        DECLARE @reporte_id INT;
        DECLARE @reciclador_id INT;
    
        SELECT @reporte_id = reporte_id, @reciclador_id = reciclador_id
        FROM asignaciones
        WHERE asignacion_id = @asignacion_id;

        IF @reporte_id IS NULL
        BEGIN
            PRINT 'Asignación no encontrada.';
            RETURN;
        END

        IF @rpta = 1
        BEGIN
            UPDATE asignaciones
            SET estado_asignacion = 'asignado', fecha_aceptado = SYSDATETIMEOFFSET()
            WHERE asignacion_id = @asignacion_id;

            UPDATE reportes
            SET estado = 'asignado'
            WHERE reporte_id = @reporte_id;

            INSERT INTO dbo.notificaciones (usuario_id, canal, tipo, mensaje)
            SELECT reportante_id, 'email', 'aceptacion', 'Tu reporte fue asignado a un reciclador'
            FROM dbo.reportes WHERE reporte_id = @reporte_id;

            PRINT 'Reciclador aceptó la asignación.';
            RETURN;
        END
 
        UPDATE dbo.asignaciones
        SET estado_asignacion = 'rechazado', fecha_rechazado = SYSDATETIMEOFFSET()
        WHERE asignacion_id = @asignacion_id;

        UPDATE dbo.reportes
        SET estado = 'pendiente'
        WHERE reporte_id = @reporte_id;

        PRINT 'Reciclador rechazó, se volverá a asignar.';
    END;

--MAPAS Y RUTAS
    --Pins con filtros
    CREATE PROCEDURE dbo.sp_pins_filtros
        @tipo_residuo NVARCHAR(100) = NULL,
        @estado NVARCHAR(32) = NULL
    AS
    BEGIN
        SELECT
            r.reporte_id,
            r.latitud,
            r.longitud,
            r.tipo_residuo_texto,
            r.estado,
            r.fechacreacion_reporte,
            r.reportante_id,
            u.nombre_completo AS reportante_nombre,
            fr.url_archivo AS foto_url
        FROM reportes AS r
        LEFT JOIN fotos_reporte AS fr ON fr.reporte_id = r.reporte_id
        LEFT JOIN usuarios AS u ON u.usuario_id = r.reportante_id
        WHERE (@tipo_residuo IS NULL OR r.tipo_residuo_texto = @tipo_residuo)
          AND (@estado IS NULL OR r.estado = @estado)
        GROUP BY
            r.reporte_id, r.latitud, r.longitud, r.tipo_residuo_texto, r.estado, r.fechacreacion_reporte, r.reportante_id, u.nombre_completo
        ORDER BY r.fechacreacion_reporte DESC, r.reporte_id;
    END;

    --Detalles Pins
    CREATE PROCEDURE dbo.sp_pin_detalle
        @reporte_id INT
    AS
    BEGIN
        SELECT
            r.reporte_id,
            r.tipo_residuo_texto,
            r.descripcion,
            r.estado,
            r.fechacreacion_reporte,
            r.fechaactualizacion_reporte,
            r.latitud,
            r.longitud,
            u.usuario_id AS reportante_id,
            u.nombre_completo AS reportante_nombre,
            u.correo_usuario AS reportante_correo
        FROM reportes AS r
        LEFT JOIN usuarios AS u ON u.usuario_id = r.reportante_id
        WHERE r.reporte_id = @reporte_id;

        --Si hay un panel para mostrar la lista de fotos
        SELECT foto_id, url_archivo, nombre_archivo, tipo_archivo, fecha_cargado
        FROM dbo.fotos_reporte
        WHERE reporte_id = @reporte_id
        ORDER BY fecha_cargado;
    END;

    --Puntos para generar ruta
    CREATE PROCEDURE dbo.sp_get_puntos_para_reciclador
        @reciclador_id INT
    AS
    BEGIN
        SET NOCOUNT ON;

        SELECT
            a.asignacion_id,
            r.reporte_id,
            r.latitud,
            r.longitud,
            r.tipo_residuo_texto,
            r.descripcion,
            r.estado,
            r.fechacreacion_reporte
        FROM asignaciones AS a
        JOIN reportes AS r ON r.reporte_id = a.reporte_id
        WHERE a.reciclador_id = @reciclador_id
          AND a.estado_asignacion IN ('asignado')
        ORDER BY a.fecha_asignacion;
    END;

-- NOTIFICACIONES ESTADO
    -- Crear notificación estado
    CREATE PROCEDURE dbo.sp_notificacion_estado
        @reporte_id INT,
        @nuevo_estado NVARCHAR(32)
    AS
    BEGIN
        DECLARE @reportante INT, @mensaje NVARCHAR(500), @asignacion_id INT, @reciclador INT;

        SELECT @reportante = reportante_id
        FROM reportes
        WHERE reporte_id = @reporte_id;

        IF @reportante IS NULL
        BEGIN
            RETURN;
        END

        SET @mensaje = CONCAT('El estado de tu reporte #', @reporte_id, ' cambió a: ', @nuevo_estado);

        IF EXISTS (SELECT 1 FROM usuarios WHERE usuario_id = @reportante)
        BEGIN
            INSERT INTO notificaciones (usuario_id, canal, tipo, mensaje, estado_notificacion, fecha_creacion_notificacion)
            VALUES (@reportante, 'email', 'estado_reporte', @mensaje, 'pendiente', SYSUTCDATETIME());
        END

        SELECT TOP 1 @asignacion_id = asignacion_id, @reciclador = reciclador_id
        FROM dbo.asignaciones
        WHERE reporte_id = @reporte_id AND estado_asignacion IN ('pendiente','asignado')
        ORDER BY fecha_asignacion DESC;

        IF @reciclador IS NOT NULL
        BEGIN
            SET @mensaje = CONCAT('Tienes una actualización para el reporte #', @reporte_id, ': ', @nuevo_estado);

            IF EXISTS (SELECT 1 FROM dbo.usuarios WHERE usuario_id = @reciclador)
            BEGIN
                INSERT INTO dbo.notificaciones (usuario_id, canal, tipo, mensaje, estado_notificacion, fecha_creacion_notificacion)
                VALUES (@reciclador, 'email', 'estado_reporte', @mensaje, 'pendiente', SYSUTCDATETIME());
            END
        END
    END;

    -- Resultado notificacion
    CREATE PROCEDURE dbo.sp_registrar_resultado_envio
        @notificacion_id INT,
        @exitoso BIT,
        @detalle NVARCHAR(1000) = NULL,
        @reportante_id INT
    AS
    BEGIN
        SELECT reportante_id
        FROM reportes
        WHERE reporte_id = @reportante_id;

        DECLARE @estado NVARCHAR(32) = CASE WHEN @exitoso = 1 THEN 'entregado' ELSE 'fallido' END;

        UPDATE dbo.notificaciones
        SET estado_notificacion = @estado,
            intentos_envio = COALESCE(intentos_envio,0) + 1,
            fecha_enviado = SYSUTCDATETIME()
        WHERE notificacion_id = @notificacion_id;

        INSERT INTO dbo.logs_auditoria (tabla, registro_id, operacion_hecha, responsable_cambio, valor_anterior, valor_nuevo)
        VALUES ('notificaciones', @notificacion_id, 'envio_' + @estado, @reportante_id, 'Pendiente', @estado);
    END;

-- GESTION ROLES PERMISOS
CREATE PROCEDURE dbo.sp_cambiar_rol_usuario
(
    @usuario_id INT,
    @nuevo_rol VARCHAR(50)
)
AS
BEGIN
    DECLARE @rol_anterior VARCHAR(50);

    SELECT @rol_anterior = rol
    FROM usuarios
    WHERE usuario_id = usuario_id;

    IF @rol_anterior IS NULL
    BEGIN
        PRINT 'Usuario no encontrado';
        RETURN;
    END

    UPDATE usuarios
    SET rol = @nuevo_rol
    WHERE usuario_id = @usuario_id;

    INSERT INTO dbo.logs_auditoria(tabla, registro_id, operacion_hecha, fecha_cambio, valor_anterior, valor_nuevo)
    VALUES (
        'usuarios', 
        @usuario_id, 
        'CAMBIO_ROL', 
        SYSUTCDATETIME(),
        @rol_anterior, 
        @nuevo_rol
    );

    PRINT 'Rol actualizado correctamente';
END;

EXEC dbo.sp_registrar_usuario
	@correo_usuario = 'correoprueba@gmail.com',
    @nombre = 'Alessandro Marcelo',
    @contrasenia = 'contrasenia',
    @rol = 'ciudadano',
    @telefono = '987654321',
    @consentimiento = 1;

EXEC dbo.sp_login_usuario
	@correo_usuario = 'correoprueba@gmail.com',
    @contrasenia = 'contrasenia';

EXEC dbo.ObtenerRolPorCorreo @CorreoUsuario = '76825708@continental.edu.pe';

CREATE LOGIN RECIC_ADMIN WITH PASSWORD = '76825708';
CREATE USER RECIC_ADMIN FOR LOGIN RECIC_ADMIN;

ALTER ROLE db_owner ADD MEMBER RECIC_ADMIN;


select * from usuarios


select * from usuarios


--simulacion de 10 registros seguidos



SELECT reporte_id FROM dbo.reportes;


ALTER TABLE dbo.usuarios
ALTER COLUMN rol VARCHAR(13) NOT NULL;


SELECT * FROM reportes


SELECT TOP (20) reporte_id, reportante_id, fechacreacion_reporte
FROM dbo.reportes
ORDER BY reporte_id DESC;

SELECT IDENT_CURRENT('dbo.reportes') AS CurrentIdentity;

SELECT MAX(reporte_id) AS MaxId FROM dbo.reportes;

DBCC CHECKIDENT ('dbo.reportes', RESEED, 13);


TRUNCATE TABLE usuarios;
TRUNCATE TABLE reportes;
TRUNCATE TABLE asignaciones;
TRUNCATE TABLE centros;
TRUNCATE TABLE notificaciones;
TRUNCATE TABLE historial_estado_reporte;

DELETE FROM usuarios;
DELETE FROM reportes;
DELETE FROM asignaciones;
DELETE FROM centros;
DELETE FROM notificaciones;
DELETE FROM historial_estado_reporte;

DBCC CHECKIDENT ('reportes', RESEED, 0);

select * from usuarios



