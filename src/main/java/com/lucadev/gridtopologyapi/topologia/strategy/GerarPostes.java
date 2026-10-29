package com.lucadev.gridtopologyapi.topologia.strategy;

import com.lucadev.gridtopologyapi.topologia.Topologia;
import com.lucadev.gridtopologyapi.poste.Poste;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class GerarPostes implements GerarRede {
    @Override
    public void gerarContexto(Topologia rede) {
        if(rede.getQntdPoste() == 0 || rede.getQntdPoste() == rede.getPostes().size()) {
            return;
        }
        Poste poste = new Poste();
        poste.setProjeto(rede.getProjeto());
        rede.getPostes().add(poste);
        gerarContexto(rede);
    }
}
