package com.lucadev.gridtopologyapi.topologia.grafo;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EdgeDTO {
    public String id;
    public String source;
    public String target;
    public String type;
}
