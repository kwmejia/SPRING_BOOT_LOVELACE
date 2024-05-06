package com.riwi.vacants.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.vacants.services.interfaces.IVacantService;
import com.riwi.vacants.utils.dto.errors.ErrorResponse;
import com.riwi.vacants.utils.dto.request.VacantRequest;
import com.riwi.vacants.utils.dto.response.VacantResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/vacants")
@AllArgsConstructor
// Con Tag podemos cambiar el nombre en swagger
@Tag(name = "Vacantes")
public class VacantController {

    @Autowired
    private final IVacantService vacantService;

    @Operation(summary = "Lista todas las vancantes con paginación", description = "Debes enviar la pagina y el tamaño de la pagina para recibir todas la vacantes correspondientes")
    @GetMapping
    public ResponseEntity<Page<VacantResponse>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size) {

        return ResponseEntity.ok(this.vacantService.getAll(page - 1, size));
    }

    // ApiResponse nos ayuda a crear un nuevo esquema de respuesta
    @ApiResponse(responseCode = "400", description = "Cuando el id no es válido", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
    })

    @Operation(summary = "Lista una vacante por id", description = "Debes enviar el id de la vacante a buscar")
    @GetMapping(path = "/{id}")
    public ResponseEntity<VacantResponse> get(@PathVariable Long id) {
        return ResponseEntity.ok(this.vacantService.getById(id));
    }

    @Operation(summary = "Crea una vacante y la asocia una compañia", description = "Crea una vacante y la asocia una compañia")
    @PostMapping
    public ResponseEntity<VacantResponse> insert(
            @Validated @RequestBody VacantRequest vacant) {

        return ResponseEntity.ok(this.vacantService.create(vacant));
    }

    @Operation(summary = "Elimina una vacante por Id", description = "Elimina una vacante por Id")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Map<String, String>> delete(
            @PathVariable Long id) {

        // Eliminar
        this.vacantService.delete(id);

        // Crear el map
        Map<String, String> response = new HashMap<>();

        // Agregar mensaje
        response.put("message", "Se eliminó la vacante correctamente");

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Actualiza una vacante", description = "Actualiza una vacante")
    @PutMapping(path = "/{id}")
    public ResponseEntity<VacantResponse> update(
            @PathVariable Long id,
            @Validated @RequestBody VacantRequest vacant) {

        return ResponseEntity.ok(this.vacantService.update(id, vacant));
    }
}
