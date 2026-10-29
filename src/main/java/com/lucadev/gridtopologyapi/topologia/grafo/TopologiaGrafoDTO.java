package com.lucadev.gridtopologyapi.topologia.grafo;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TopologiaGrafoDTO {
    List<NodeDTO> nodes = new ArrayList<>();
    List<EdgeDTO> edges = new ArrayList<>();
}
