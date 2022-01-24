package gr.sae.controller;

import gr.sae.base.AbstractLogComponent;
import gr.sae.domain.BaseModel;
import gr.sae.service.BaseService;
import gr.sae.transfer.ApiResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public abstract class AbstractController<T extends BaseModel> extends AbstractLogComponent {
    protected abstract BaseService<T, Long> getService();

    @PostMapping
    public ResponseEntity<ApiResponse<T>> create(@Valid @RequestBody final T entity) {
        return new ResponseEntity<>(ApiResponse.<T>builder().data(getService().create(entity)).build(),
                getNoCacheHeaders(), HttpStatus.CREATED);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody final T entity) {
        getService().update(entity);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@Valid @RequestBody final T entity) {
        if (getService().exists(entity)) {
            getService().delete(entity);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") final Long id) {
        getService().deleteById(id);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<T>>> findAll() {
        return ResponseEntity.ok(ApiResponse.<List<T>>builder().data(getService().findAll()).build());
    }

    @GetMapping("find/{id}")
    public ResponseEntity<ApiResponse<T>> find(@PathVariable final Long id) {
        return ResponseEntity.ok(ApiResponse.<T>builder().data(getService().find(id)).build());
    }

    @GetMapping("get/{id}")
    public ResponseEntity<ApiResponse<T>> get(@PathVariable final Long id) {
        return ResponseEntity.ok(ApiResponse.<T>builder().data(getService().get(id)).build());
    }

    protected HttpHeaders getNoCacheHeaders() {
        final HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        return headers;
    }
}
