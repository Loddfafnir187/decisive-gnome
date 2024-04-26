package com.example.storage.models.exceptions;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * ОБЕРТКА СОБСТВЕННОГО ИСКЛЮЧЕНИЯ
 */
@Data
public class ExceptionBody {
    private String message;
    private LocalDateTime dateTime;
}
