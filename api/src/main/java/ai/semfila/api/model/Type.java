package ai.semfila.api.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


public enum Type   {
    ALCOOLICO,
    LANCHE,
    PETISCOS,
    SOBREMESA,
    NAO_ALCOOLICO,
    VEGANO,
    VEGETARIANO,
    OUTROS
}
