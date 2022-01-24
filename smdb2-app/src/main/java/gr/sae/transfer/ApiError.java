package gr.sae.transfer;

import lombok.ToString;
import lombok.Value;

/**
 * This class represents an error occurred while serving the corresponding request
 */
@Value
@ToString
public class ApiError {
    Integer status;
    String message;
    String path;
}
